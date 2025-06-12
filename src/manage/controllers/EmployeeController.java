/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.controllers;

import manage.model.Employee;
import manage.model.IEmployee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import manage.model.Utils;
/**
 *
 * @author 01duc
 */
public class EmployeeController extends ArrayList<Employee> implements IEmployee{
    public static Comparator<Employee> compareEmployeeByFirstName = Comparator.comparing(Employee::getFirstName);
    
    @Override
    public boolean addEmployee(Employee employee) {
        boolean result = false;
        if (this.contains(employee)) {
            JOptionPane.showMessageDialog(null, "Duplicated Employee", "Validation Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(result);
            return result;
        } else {
            result = true;
            this.add(employee);
            return result;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee, String username, String firstName, String lastName, String password, String phone, String email) {
        boolean result = false; 
        try {
            employee.setUsername(username);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setPassword(password);
            employee.setPhone(phone);
            employee.setEmail(email);
            
            result = true;
        } catch (Exception e) {
        }
        return result;       
    }
    
    @Override
    public boolean deleteEmployee(int idToDelete) {
        Iterator<Employee> iterator = this.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (Integer.parseInt(emp.getID()) == idToDelete) { // So sánh ID
                iterator.remove(); // Xóa đối tượng Employee
                return true; // Đã xóa thành công
            }
        }
        return false; // Không tìm thấy Employee với ID này
    }


    @Override
    public List<Employee> searchEmployeeByName(String value) {
        List arr = new ArrayList();
        for (Employee i : this) {
            String firstLastName = i.getFirstName().toLowerCase() + i.getLastName().toLowerCase();
            if (firstLastName.contains(value)) {
                arr.add(i);
            }
        }
        
        return arr;
    }

    @Override
    public String passwordEncryption() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean writeDataToFile() {
        return Utils.writeListToTextFile("Employee.txt", this); // this là List<Employee>
    }


    public void readEmployee() {
        this.clear();
        List<Employee> list = Utils.readListFromTextFile("Employee.txt");
        this.addAll(list); // this là một danh sách Employee
    }

    public void sortbyFirdtname() {
        Collections.sort(this, EmployeeController.compareEmployeeByFirstName);
    }    
    
    public void changeOrder(int idRemoved) {
        System.out.println(this.size());
        for (int i = idRemoved - 1; i < this.size(); i++) {
            this.get(i).setID(String.format("%s", i + 1));
        }
    }
    
}
