/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.model;

/**
 *
 * @author 01duc
 */
public class EmployeeValidator {

    private static final String USERNAME = "^[^\\s]{5,}$"; // -	Username must be at least five characters and no spaces.
    private static final String FIRSTNAME = "^[A-Za-z]{2,25}$"; // Only word, 2-25 character
    private static final String LASTNAME = "^[A-Za-z]{2,25}$"; // Only word, 2-25 character
    private static final String PASSWORD = "^[^\\s]{6,}$"; // -	Password must be at least six characters and no spaces.
    private static final String PHONE = "^(?:\\+84|0)(3[2-9]|5[2689]|7[0-9]|8[1-9]|9[0-9])\\d{7}$"; // Phone number must contain 10 numbers.
    private static final String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // standard email format.

    public static boolean isValid(String value, String types) {
        boolean result = false;
        switch (types.toLowerCase()) {
            case "username":
                result = value.matches(EmployeeValidator.USERNAME);
                break;
            case "firstname":
                Utils.UpperFirstCharacter(value);
                result = value.matches(EmployeeValidator.FIRSTNAME);
                break;
            case "lastname":
                Utils.UpperFirstCharacter(value);
                result = value.matches(EmployeeValidator.LASTNAME);
                break;
            case "password":
                result = value.matches(EmployeeValidator.PASSWORD);
                break;
            case "phone":
                result = value.matches(EmployeeValidator.PHONE);
                break;
            case "email":
                result = value.matches(EmployeeValidator.EMAIL);
                break;
            default:
                throw new AssertionError();
        }
        return result;
    }

    public static boolean isConfirmedPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
