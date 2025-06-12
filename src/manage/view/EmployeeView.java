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
    
//    public boolean addEmployee() {
//        String username = Utils.checkValidInfo("Please input username: ", "username");
//        String firstname = Utils.checkValidInfo("Please input firstname: ", "firstname");
//        String lastname = Utils.checkValidInfo("Please input lastname: ", "lastname");
//        String password = Utils.checkValidInfo("Please input password: ", "password");
//        String phone = Utils.checkValidInfo("Please input phone: ", "phone");
//        String email = Utils.checkValidInfo("Please input email: ", "email");
//        
//        return employeeController.addEmployee(username, firstname, lastname, password, phone, email);
//    }
//    
//    public boolean updateEmployee() {
//        this.displayAllEmployee();
//        String usName = Utils.getString("Input username to update: ");
//        Employee employee = employeeController.checkExit(usName);
//        if (employee.getUsername() == null) {
//            return false;
//        } else {
//            String username = Utils.updateValidInfo("Please input username: ", "username", employee.getUsername());
//            String firstname = Utils.updateValidInfo("Please input firstname: ", "firstname", employee.getFirstName());
//            String lastname = Utils.updateValidInfo("Please input lastname: ", "lastname", employee.getLastName());
//            String password = Utils.updateValidInfo("Please input password: ", "password", employee.getPassword());
//            String phone = Utils.updateValidInfo("Please input phone: ", "phone", employee.getPassword());
//            String email = Utils.updateValidInfo("Please input email: ", "email", employee.getEmail());
//            
//            return employeeController.updateEmployee(employee, username, firstname, lastname, password, phone, email);
//        }
//    }
//    
//    public boolean deleteEmployee() {
//        this.displayAllEmployee();
//        String username = Utils.getString("Input username to delete: ");
//        Employee employee = employeeController.checkExit(username);
//        return employeeController.deleteEmployee(employee);
//    }
    
    
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
        
    public void displayAllEmployee() {
        String format = "| %-3s | %-10s | %-12s | %-12s | %-15s | %-25s |%n";

        // In tiêu đề
        System.out.println("+-----+------------+--------------+--------------+-----------------+---------------------------+");
        System.out.printf("| No  | Username   | First Name   | Last Name    | Phone           | Email                     |%n");
        System.out.println("+-----+------------+--------------+--------------+-----------------+---------------------------+");
        // In từng dòng dữ liệu
        int index = 1;
        for (Employee employee : employeeController) {
            System.out.print(String.format("| %-3s | ", index++));
            System.out.println(employee.displayEmployee());
        }
    }
    
    public void sortByFirstName() {
        employeeController.sortbyFirdtname();
    }
}
