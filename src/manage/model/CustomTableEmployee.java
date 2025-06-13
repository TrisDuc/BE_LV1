package manage.model;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.regex.Pattern; // Matcher is not directly used in this class, so it can be removed.

public class CustomTableEmployee extends DefaultTableModel {

    // Regex patterns
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
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private TableEditListener tableEditListener;

    // Constructor
    public CustomTableEmployee(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public CustomTableEmployee(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    // Setter cho listener
    public void setTableEditListener(TableEditListener listener) {
        this.tableEditListener = listener;
    }

    public static boolean isValidPattern(String value, Pattern pattern) {
        if (value == null) {
            return false;
        }
        return pattern.matcher(value).matches();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        String columnName = getColumnName(column);
        // "ID" is not editable. 
        return !columnName.equalsIgnoreCase("ID");
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        String columnName = getColumnName(column);
        // Important: Get the old value before attempting to set the new value.
        // This is crucial if validation fails and you need to revert.
        Object oldValue = getValueAt(row, column);

        String newValue;
        // Handle null values to avoid NullPointerExceptions
        if (aValue != null) {
            newValue = Utils.trim(aValue.toString());
        } else {
            newValue = ""; // Or handle as appropriate for your validation
        }

        boolean validationSuccess = true; // Flag to track validation
        String errorMessage = "";

        switch (columnName) {
            case "Username":
                if (!isValidPattern(newValue, USERNAME_PATTERN)) {
                    errorMessage = "Username must be at least 5 characters long and contain no spaces for row " + (row + 1) + ".";
                    validationSuccess = false;
                }
                break;
            case "First Name":
                if (!isValidPattern(newValue, FIRSTNAME_PATTERN)) {
                    errorMessage = "First Name must contain 2-25 letters for row " + (row + 1) + ".";
                    validationSuccess = false;
                } else {
                    newValue = Utils.UpperFirstCharacter(newValue); // Apply formatting if valid
                }
                break;
            case "Last Name":
                if (!isValidPattern(newValue, LASTNAME_PATTERN)) {
                    errorMessage = "Last Name must contain 2-25 letters for row " + (row + 1) + ".";
                    validationSuccess = false;
                } else {
                    newValue = Utils.UpperFirstCharacter(newValue); // Apply formatting if valid
                }
                break;
            case "Phone":
                if (!isValidPattern(newValue, PHONE_PATTERN)) {
                    errorMessage = "Phone number must have 10 digits and a valid Vietnamese format for row " + (row + 1) + ".";
                    validationSuccess = false;
                }
                break;
            case "Email":
                if (!isValidPattern(newValue, EMAIL_PATTERN)) {
                    errorMessage = "Invalid email format for row " + (row + 1) + ".";
                    validationSuccess = false;
                }
                break;
            // No default case needed if all editable columns are handled above.
            // If there are other editable columns, they will pass through here without validation.
        }

        if (validationSuccess) {
            // Only update the model's value if validation is successful
            super.setValueAt(newValue, row, column);

            // Notify the listener that the cell has been successfully edited
            if (tableEditListener != null) {
                tableEditListener.onCellEdited(row, column, newValue);
            }
        } else {
            // If validation fails, display an error message.
            JOptionPane.showMessageDialog(null, errorMessage, "Validation Error", JOptionPane.ERROR_MESSAGE);
            // Important: revert the displayed cell value to its old value.
            // Otherwise, the invalid text remains in the cell, which is bad for user experience.
            super.setValueAt(oldValue, row, column);
        }
    }
}
