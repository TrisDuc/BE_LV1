  
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
 
    public static boolean writeListToTextFile(String path, List<Employee> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Employee emp : list) {
                String hashedPassword = PasswordUtils.encryptSHA256(emp.getPassword());
                String line = String.join(",", emp.getID(), emp.getUsername(), emp.getFirstName(), emp.getLastName(),
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
                if (parts.length == 7) {
                    Employee emp = new Employee(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
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
    
    public static String trim(String value) {
        return (value != null) ? value.trim() : "";
    }
}
