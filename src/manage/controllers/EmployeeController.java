/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.controllers;

import java.io.IOException;
import manage.model.Employee;
import manage.model.IEmployee;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import manage.model.Utils;
/**
 *
 * @author 01duc
 */
public class EmployeeController extends ArrayList<Employee> implements IEmployee{

    @Override
    public boolean addEmployee(String username, String firstName, String lastName, String password, String phone, String email) {
        boolean result = false;
        Employee employee = new Employee(username, firstName, lastName, password, phone, email);
        if (this.contains(employee)) {
            System.out.println("Duplicate Employee");
            return result;
        } else {
            result = true;
            this.add(employee);
            return result;
        }
    }

    @Override
    public Employee updateDeleteEmployee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Employee checkExit(String username) {
        Employee cus = new Employee();
        for (Employee i : this) {;
            if (i.getUsername().equalsIgnoreCase(username)) {
                cus = i;
                break;
            }
        }
        return cus;
    }

    @Override
    public List searchEmployeeByName(String value) {
        List arr = new ArrayList();
        for (Employee i : this) {
            String liLaName = i.getFirstName().toLowerCase() + i.getLastName().toLowerCase();
            if (liLaName.contains(value)) {
                arr.add(i);
            }
        }
        
        return arr;
    }

    @Override
    public void displayAllEmployee() {
        System.out.printf("%-15s %-15s %-15s %-15s %-30s %-20s\n", 
            "Username", "First Name", "Last Name", "Phone", "Email", "Password");
        
        for (int i = 0; i < 110; i++) {
            System.out.print("-");
        }
        System.out.println("");
        for (Employee i : this) {
            System.out.println(i.displayEmployee());
        }
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

}
