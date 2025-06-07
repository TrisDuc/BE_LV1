  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package manage.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  

/**
 *
 * @author 01duc
 */
public class Utils {
    Scanner sc = new Scanner(System.in);

    public static String getString(String value) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(value);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }
    
    public static String getStringEnableEnter(String value) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(value);
            result = sc.nextLine();
            if (result.isEmpty()) {
                return "";
            } else {
                check = false;
            }
        } while (check);
        return result;
    }
    
    public static int getInt(String value, int min, int max) {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(value);
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check || number > max || number < min);
        return number;
    }
    
    public static String checkValidInfo(String value, String cases) {
        String result = "";
        do {            
            result = getString(value);
            if (EmployeeValidator.isValid(result, cases)) {
                break;
            } else {
                System.out.println("Invalid value for " + cases);
            }
        } while (true);
        return result;
    }
    
    public static String updateValidInfo(String value, String cases, String oldAns) {
        String result = "";
        do {
            result = getStringEnableEnter(value);
            if (result.equals("")) {
                return oldAns;
            } else {
                if (EmployeeValidator.isValid(result, cases)) {
                    break;
                } else {
                    System.out.println("Invalid value for " + cases);
                }
            }
        } while (true);
        return result;
    }
    
    public static void displayStatus(boolean status, String successMsg, String failedMsg) {
        if (status) {
            System.out.println(successMsg);
        } else {
            System.out.println(failedMsg);
        }
    }
    
    public static boolean confirmYesNo(String ask) {
        String answer = getString(ask);
        boolean result = true;
        if ("y".equalsIgnoreCase(answer)) {
            result = false;
        }
        return result;
    }
    
    public static boolean askForRetry(boolean result, String successedMsg, String failedMsg) {
        displayStatus(result, successedMsg, failedMsg);
        boolean check = confirmYesNo("Do you want to return menu? (y/n)");
        return check;
    }
    
    public static boolean writeListToTextFile(String path, List<Employee> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Employee emp : list) {
                String hashedPassword = PasswordUtils.encryptSHA256(emp.getPassword());
                String line = String.join(",", emp.getUsername(), emp.getFirstName(), emp.getLastName(),
                                           hashedPassword, emp.getPhone(), emp.getEmail());
                writer.write(line);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Employee> readListFromTextFile(String path) {
        List<Employee> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Employee emp = new Employee(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    list.add(emp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static String UpperFirstCharacter(String value) {
        return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
    }
    
    
}
