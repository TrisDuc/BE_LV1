// File: manage/util/EmployeeValidator.java (or similar package)
package manage.model;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import manage.model.Employee; // Import the Employee class

public class EmployeeValidator {

    // Regex patterns (compiled for efficiency)
    private static final String USERNAME_REGEX = "^[^\\s]{5,}$"; // Username: at least 5 chars, no spaces
    private static final String FIRSTNAME_REGEX = "^[A-Za-z]{2,25}$"; // First name: only letters, 2-25 chars
    private static final String LASTNAME_REGEX = "^[A-Za-z]{2,25}$"; // Last name: only letters, 2-25 chars
    private static final String PASSWORD_REGEX = "^[^\\s]{6,}$"; // Password: at least 6 chars, no spaces
    // Phone: must contain 10 numbers and follow Vietnamese format (+84 or 0 prefix, valid carrier code, 7 digits)
    private static final String PHONE_REGEX = "^(?:\\+84|0)(3[2-9]|5[2689]|7[0-9]|8[1-9]|9[0-9])\\d{7}$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"; // Standard email format

    // Compiled Pattern objects for efficiency
    private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);
    private static final Pattern FIRSTNAME_PATTERN = Pattern.compile(FIRSTNAME_REGEX);
    private static final Pattern LASTNAME_PATTERN = Pattern.compile(LASTNAME_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX); // Although not directly used for validation in JTable cell edit, it's good to have it.
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    /**
     * Validates a single value against a specific type.
     * @param value The string to validate.
     * @param type  The type of field (e.g., "username", "firstname").
     * @return true if valid, false otherwise.
     */
    public static boolean isValidField(String value, String type) {
        if (value == null) {
            return false;
        }
        switch (type.toLowerCase()) {
            case "username":    return USERNAME_PATTERN.matcher(value).matches();
            case "firstname":   return FIRSTNAME_PATTERN.matcher(value).matches();
            case "lastname":    return LASTNAME_PATTERN.matcher(value).matches();
            case "password":    return PASSWORD_PATTERN.matcher(value).matches();
            case "phone":       return PHONE_PATTERN.matcher(value).matches();
            case "email":       return EMAIL_PATTERN.matcher(value).matches();
            default:            return false; // Unknown type
        }
    }
    /**
     * Validates all fields of an Employee and returns an Employee object if valid,
     * or a validation error message otherwise.
     * @param username Raw username string.
     * @param firstName Raw first name string.
     * @param lastName Raw last name string.
     * @param password Raw password string.
     * @param phone Raw phone number string.
     * @param email Raw email string.
     * @return A {@link ValidationResult} object containing either the validated Employee or an error message.
     */

    public static Employee isValid(String size, String username, String firstname, String lastname, String password, String phoneNumber, String email) {
        String trimmedUsername = Utils.trim(username);
        String trimmedFirstname = Utils.trim(firstname);
        String trimmedLastname = Utils.trim(lastname);
        String trimmedPassword = Utils.trim(password);
        String trimmedPhoneNumber = Utils.trim(phoneNumber);
        String trimmedEmail = Utils.trim(email);
        int newSize = Integer.parseInt(size);
        newSize += 1;
        if (!EmployeeValidator.isValidField(trimmedUsername, "username")) {
            // In a real application, you'd throw an exception, return a detailed error object,
            // or set an error message in a UI field. Returning null is a simple way to indicate failure.
            // For demonstration, we'll use JOptionPane, but this is generally avoided in core logic.
            JOptionPane.showMessageDialog(null, "Username must be at least 5 characters and no spaces.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return null; // Validation failed
        }

        if (!EmployeeValidator.isValidField(trimmedFirstname, "firstname")) {
            JOptionPane.showMessageDialog(null, "First Name must be 2-25 letters only.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return null; // Validation failed
        }

        if (!EmployeeValidator.isValidField(trimmedLastname, "lastname")) {
            JOptionPane.showMessageDialog(null, "Last Name must be 2-25 letters only.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return null; // Validation failed
        }

        if (!EmployeeValidator.isValidField(trimmedPassword, "password")) {
            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters and no spaces.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return null; // Validation failed
        }

        if (!EmployeeValidator.isValidField(trimmedPhoneNumber, "phone")) {
            JOptionPane.showMessageDialog(null, "Phone number must be 10 digits and valid Vietnamese format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return null; // Validation failed
        }

        if (!EmployeeValidator.isValidField(trimmedEmail, "email")) {
            JOptionPane.showMessageDialog(null, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return null; // Validation failed
        }

        // If all validations pass, create and return the Employee object
        // Apply formatting here before creating the object
        String formattedFirstname = Utils.UpperFirstCharacter(firstname);
        String formattedLastname = Utils.UpperFirstCharacter(lastname);
        
        return new Employee(String.format("%s", newSize), trimmedUsername, formattedFirstname, formattedLastname, trimmedPassword, trimmedPhoneNumber, trimmedEmail);
    }
}
