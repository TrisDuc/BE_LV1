/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.model;

import java.util.Objects;

/**
 *
 * @author 01duc
 */
public class Employee {
    private String ID;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;

    public Employee() {
    }

    public Employee(String ID, String username, String firstName, String lastName, String password, String phone, String email) {
        this.ID = ID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "--------------------------------------------------------------------------------------\n"
             + "ID             : " + this.getID() + "\n"
             + "Username       : " + this.getUsername() + "\n"
             + "First name     : " + this.getFirstName() + "\n"
             + "Last name      : " + this.getLastName() + "\n"
             + "Password       : " + this.getPassword() + "\n"
             + "Phone number   : " + this.getPhone() + "\n"
             + "Email          : " + this.getEmail();
    }
}
