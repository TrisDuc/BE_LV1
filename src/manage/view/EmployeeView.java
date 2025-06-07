/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.view;

import java.util.List;
import java.util.Scanner;
import manage.controllers.EmployeeController;
import manage.model.Employee;
import manage.model.Utils;

/**
 *
 * @author 01duc
 */
public class EmployeeView {
    private Employee employee = new Employee();
    private EmployeeController employeeController = new EmployeeController();
    
    public void init() {
        employeeController.readEmployee();
    }
    
    public boolean addEmployee() {
        String username = Utils.checkValidInfo("Please input username: ", "username");
        String firstname = Utils.checkValidInfo("Please input firstname: ", "firstname");
        String lastname = Utils.checkValidInfo("Please input lastname: ", "lastname");
        String password = Utils.checkValidInfo("Please input password: ", "password");
        String phone = Utils.checkValidInfo("Please input phone: ", "phone");
        String email = Utils.checkValidInfo("Please input email: ", "email");
        
        return employeeController.addEmployee(username, firstname, lastname, password, phone, email);
    }
    
    public boolean checkExit() {
        String username = Utils.getString("Input Username for search: ");        
        Employee employee = employeeController.checkExit(username);
        if (employee.getUsername() == null) {
            return false;
        } else {
            System.out.println(employee);
            return true;
        }  
    }
    
    public boolean SearchByName() {
        String name = Utils.getString("Input name for search: ").toLowerCase();
        List<Employee> employeeArray = employeeController.searchEmployeeByName(name);
        if (employeeArray.size() == 0) {
            return false;
        } else {
            System.out.printf("%-15s %-15s %-15s %-15s %-30s %-20s\n", 
                "Username", "First Name", "Last Name", "Phone", "Email", "Password");

            for (int i = 0; i < 110; i++) {
                System.out.print("-");
            }
            System.out.println(); 
            for (Employee i : employeeArray) {
                System.out.println(i.displayEmployee());
            }

            for (int i = 0; i < 110; i++) {
                System.out.print("-");
            }
            System.out.println(); 
            return true;
        }
    }
    
    public void printAllEmployee() {
        employeeController.displayAllEmployee();
    }
    
}
