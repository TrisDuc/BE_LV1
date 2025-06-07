package manage.view;

import java.util.Scanner;
import manage.controllers.EmployeeController;
import manage.controllers.MenuController;
import manage.model.Employee;
import manage.model.IMenu;
import manage.model.Utils;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 01duc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MenuController menu = new MenuController();
        menu.addItem("1. Create user account.");
        menu.addItem("2. Check if user exists.");
        menu.addItem("3. Search user by name.");
        menu.addItem("4. Update user information.");
        menu.addItem("5. Delete user.");
        menu.addItem("6. Save user data to file.");
        menu.addItem("7. Load & display user list from file.");
        menu.addItem("8. Exit program.");
        Scanner sc = new Scanner(System.in);
        Employee employee = new Employee();
        EmployeeView employeeView = new EmployeeView();
        EmployeeController employeeController = new EmployeeController();
        employeeView.init();
        
        employeeController.readEmployee();
        boolean condition = true;
        do {      
            menu.showMenu();
            int choice = Utils.getInt("Enter your choice: ", 1, 8);
            boolean repeat = true;
            switch (choice) {
                case 1:
                    do {     
                        repeat = Utils.askForRetry(employeeView.addEmployee(), "Registed Successfuil", "");
                    } while (repeat);
                    break;
                case 2:
                    do {                        
                        repeat = Utils.askForRetry(employeeView.checkExit(), "Exist employee", "No employee Found");
                    } while (repeat);
                    break;
                case 3:
                    do {
                        repeat = Utils.askForRetry(employeeView.SearchByName(), "", "Have no any employee");
                    } while (repeat);
                    break;
                case 4:
                    employeeView.printAllEmployee();
            }
        } while (condition);
        
        
    }
    
}
