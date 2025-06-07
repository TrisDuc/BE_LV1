/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.model;

import java.util.List;

/**
 *
 * @author 01duc
 */
public interface IEmployee {
    public boolean addEmployee(String username, String firstName, String lastName, String password, String phone, String email);
    
    public boolean updateEmployee(Employee employee, String username, String firstName, String lastName, String password, String phone, String email);
    
    public boolean deleteEmployee(Employee employee);
    
    public Employee checkExit(String username);
    
    public List searchEmployeeByName(String value);
        
    public String passwordEncryption();
    
}
