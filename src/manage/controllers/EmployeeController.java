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
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import manage.model.Utils;
/**
 *
 * @author 01duc
 */
public class EmployeeController extends ArrayList<Employee> implements IEmployee{
    
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
    
    //Delete Employee By Index
    @Override
    public boolean deleteEmployee(int idToDelete) {
        Iterator<Employee> iterator = this.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (Integer.parseInt(emp.getID()) == idToDelete) {
                iterator.remove(); // Remove Employee Object based on ID
                return true; // return message true
            }
        }
        return false; // Cannot find Employee ID
    }

    @Override
    public List<Employee> searchEmployeeByName(String value) {
        List arr = new ArrayList();
        for (Employee i : this) {
            //Compare value to string including first name and last name
            String firstLastName = i.getFirstName().toLowerCase() + i.getLastName().toLowerCase();
            if (firstLastName.contains(value)) {
                arr.add(i);
            }
        }
        return arr;
    }

    
    public boolean writeDataToFile() {
        return Utils.writeListToTextFile("Employee.txt", this); // this is List<Employee>
    }


    public void readEmployee() {
        this.clear();
        List<Employee> list = Utils.readListFromTextFile("Employee.txt");
        this.addAll(list); // this is a Employee List
    }
    
    //Function to decrease ID when an employee is deleted
    public void changeOrder(int idRemoved) {
        for (int i = idRemoved - 1; i < this.size(); i++) {
            this.get(i).setID(String.format("%s", i + 1));
        }
    }
    
}
