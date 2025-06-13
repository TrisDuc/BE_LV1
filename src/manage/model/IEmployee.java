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
    public boolean addEmployee(Employee employee);
    
    public boolean deleteEmployee(int indexToDelete);
    
    public List searchEmployeeByName(String value);    
}
