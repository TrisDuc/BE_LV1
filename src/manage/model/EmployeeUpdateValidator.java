package manage.model;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeUpdateValidator extends DefaultTableModel {

    // Regex patterns (chỉ khai báo một lần duy nhất)
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


    @Override
    public boolean isCellEditable(int row, int column) {
        // Cho phép chỉnh sửa tất cả các ô trong ví dụ này
        // Bạn có thể tùy chỉnh để chỉ cho phép chỉnh sửa các cột/hàng cụ thể
        return true;
    }
    
    public static boolean isValidField(String value, Pattern pattern) {
        if (value == null) {
            return false;
        }
        try {
            return pattern.matcher(value).matches();
        } catch (Exception e) {
            return false; //unknown pattern
        }
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {

                // Đối với các cột khác, cập nhật giá trị bình thường
                super.setValueAt(aValue, row, column);
        }
        // fireTableCellUpdated(row, column); // setValueAt của DefaultTableModel đã gọi this.fireTableChanged
    }