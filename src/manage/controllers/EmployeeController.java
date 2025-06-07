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
import java.util.List;
import manage.model.Utils;
/**
 *
 * @author 01duc
 */
public class EmployeeController extends ArrayList<Employee> implements IEmployee{
    public static Comparator<Employee> compareEmployeeByFirstName = Comparator.comparing(Employee::getFirstName);
    
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
    public boolean deleteEmployee(Employee employee) {
        boolean result = false;
        try {
            this.remove(employee);
            result = true;
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public Employee checkExit(String username) {
        Employee cus = new Employee();
        for (Employee i : this) {;
            if (i.getUsername().equals(username)) {
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

}
