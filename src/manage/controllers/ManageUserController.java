/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.controllers;

import manage.model.Employee;
import manage.model.TableEditListener;
import manage.view.ManageUserView;
import java.util.List;
import javax.swing.JOptionPane;
import manage.model.CustomTableEmployee;
import manage.model.EmployeeValidator;
import manage.view.Login; // Ensure the correct path to your Login class
import manage.view.addNewEmployee; // Reference to the View (JFrame)

/**
 *
 * @author 01duc
 */
public class ManageUserController implements TableEditListener{
    // Reference to the Model (EmployeeController)
    private EmployeeController employeeController;
    // Reference to the View (ManageUserView JFrame)
    private ManageUserView manageuserView;

    public ManageUserController(ManageUserView view, EmployeeController controller) {
        this.manageuserView = view;
        this.employeeController = controller;
    }

    // This method will be called by CustomTableEmployee when a cell is successfully edited
    @Override
    public void onCellEdited(int row, int column, Object newValue) {
        // 1. Get the original Employee object from employeeController's list
        // Note: The row index in JTable corresponds to the index in employeeController's list
        if (row >= 0 && row < employeeController.size()) {
            Employee employeeToUpdate = employeeController.get(row);
            String columnName = manageuserView.getCustomTableModel().getColumnName(column); // Get column name from View's model

            // 2. Update the specific field of the Employee object
            switch (columnName) {
                case "Username":
                    employeeToUpdate.setUsername((String) newValue);
                    break;
                case "First Name":
                    employeeToUpdate.setFirstName((String) newValue);
                    break;
                case "Last Name":
                    employeeToUpdate.setLastName((String) newValue);
                    break;
                case "Phone":
                    employeeToUpdate.setPhone((String) newValue);
                    break;
                case "Email":
                    employeeToUpdate.setEmail((String) newValue);
                    break;
                default:
                    // If you have non-standard columns, handle them here or do nothing
                    break;
            }
        } else {
            System.err.println("Error: Row index out of bounds in employeeController during update.");
        }
    }

    public void handleSaveAction() {
        int save = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Selection", JOptionPane.YES_NO_OPTION);
        if (save == 0) {
            employeeController.writeDataToFile();
            JOptionPane.showMessageDialog(manageuserView, "Save successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void handleResetAction() {
        employeeController.readEmployee();
        manageuserView.updateTable(employeeController);
    }

    public void handleAddAction() {
        // Pass 'this' reference (which is the current ManageUserView) to the addNewEmployee constructor
        addNewEmployee addNewEmployee1Frame = new addNewEmployee(manageuserView, this);
        addNewEmployee1Frame.setVisible(true);
    }

    public void addNewEmployeeToList(Employee employee) {
        boolean result = employeeController.addEmployee(employee);
        if (result) {
            manageuserView.updateTable(employeeController); // Request View to update the table
            JOptionPane.showMessageDialog(manageuserView, "Employee '" + employee.getUsername() + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(manageuserView, "Failed to add employee '" + employee.getUsername() + "'!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleDeleteAction(int selectedRow) {
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(manageuserView, "Please select an employee to delete.", "No Employee Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idToDelete = (String) manageuserView.getCustomTableModel().getValueAt(selectedRow, 0);
        String usernameToDelete = (String) manageuserView.getCustomTableModel().getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(manageuserView,
                "Are you sure you want to delete employee: " + usernameToDelete + "(ID: " + idToDelete + ")?",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = employeeController.deleteEmployee(Integer.parseInt(idToDelete));

            if (deleted) {
                employeeController.changeOrder(Integer.parseInt(idToDelete)); // Adjust ID order in Model
                JOptionPane.showMessageDialog(manageuserView, "Employee '" + usernameToDelete + "' deleted successfully!", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
                manageuserView.updateTable(employeeController); // Request View to update the table
            } else {
                JOptionPane.showMessageDialog(manageuserView, "Could not delete employee '" + usernameToDelete + "'. Employee not found.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void handleLogoutAction() {
        int out = JOptionPane.showConfirmDialog(manageuserView, "Do you want to log out?", "Selection", JOptionPane.YES_NO_OPTION);
        if (out == 0){
            new Login().setVisible(true);
            manageuserView.dispose(); // Close the current View
        }
    }

    public void handleSearchAction(String keywords) {
        List<Employee> searchList = employeeController.searchEmployeeByName(keywords);
        // Controller decides which data to display
        if (searchList.isEmpty() && !keywords.trim().isEmpty()) { // Only show message if search is not empty but no results
            JOptionPane.showMessageDialog(manageuserView, "No employees found with keywords: " + keywords, "Not Found", JOptionPane.INFORMATION_MESSAGE);
        }
        manageuserView.updateSearchTable(searchList); // Request View to update table with search results
    }

    public void handleAddEmployeeFromForm(String username, String firstName, String lastName,
                                          String password, String confirmedPassword, String phoneNumber, String email) {
        // ID should be auto-generated by the Model or Controller
        // Here, we create a temporary ID based on the current number of employees.
        String newId = String.valueOf(employeeController.size());

        // [Changed]: Controller calls Validator and processes the result
        Employee newEmployee = EmployeeValidator.isValid(newId, username, firstName, lastName, password, confirmedPassword, phoneNumber, email);

        if (newEmployee != null) {
            boolean result = employeeController.addEmployee(newEmployee);
            if (result) {
                // [Changed]: Controller instructs the View to update the table
                manageuserView.updateTable(employeeController);
                JOptionPane.showMessageDialog(manageuserView, "Employee '" + newEmployee.getUsername() + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(manageuserView, "Failed to add employee '" + newEmployee.getUsername() + "'! (Perhaps username already exists)", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // EmployeeValidator.isValid has already displayed the error message, no need to do anything further here
        }
    }

    // This method can be called when the View is first displayed
    public void initializeData() {
        employeeController.readEmployee(); // Read initial data
        manageuserView.updateTable(employeeController); // Request View to display data
    }

    // Provide the number of employees (may be needed for addNewEmployee to generate new IDs)
    public int getNumEmployees() {
        return employeeController.size();
    }
}