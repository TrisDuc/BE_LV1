package manage.model;

import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.util.regex.Pattern; // Matcher is not directly used in this class, so it can be removed.

public class CustomTableEmployee extends DefaultTableModel {

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
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Thêm một trường để giữ listener
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
        // "ID" không thể chỉnh sửa. Nếu bạn cũng có cột "Password" trong bảng,
        // bạn có thể muốn làm cho nó không thể chỉnh sửa hoặc xử lý xác thực riêng.
        return !columnName.equalsIgnoreCase("ID");
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        String columnName = getColumnName(column);
        // Quan trọng: Lấy giá trị cũ trước khi cố gắng đặt giá trị mới.
        // Điều này rất quan trọng nếu xác thực thất bại và bạn cần hoàn nguyên.
        Object oldValue = getValueAt(row, column);

        String newValue;
        // Xử lý giá trị null để tránh NullPointerException
        if (aValue != null) {
            newValue = Utils.trim(aValue.toString());
        } else {
            newValue = ""; // Hoặc xử lý phù hợp với xác thực của bạn
        }


        boolean validationSuccess = true; // Cờ để theo dõi xác thực
        String errorMessage = "";

        switch (columnName) {
            case "Username":
                if (!isValidPattern(newValue, USERNAME_PATTERN)) {
                    errorMessage = "Username phải có ít nhất 5 ký tự và không có khoảng trắng cho hàng " + (row + 1) + ".";
                    validationSuccess = false;
                }
                break;
            case "First Name":
                if (!isValidPattern(newValue, FIRSTNAME_PATTERN)) {
                    errorMessage = "First Name phải có 2-25 chữ cái cho hàng " + (row + 1) + ".";
                    validationSuccess = false;
                } else {
                    newValue = Utils.UpperFirstCharacter(newValue); // Áp dụng định dạng nếu hợp lệ
                }
                break;
            case "Last Name":
                if (!isValidPattern(newValue, LASTNAME_PATTERN)) {
                    errorMessage = "Last Name phải có 2-25 chữ cái cho hàng " + (row + 1) + ".";
                    validationSuccess = false;
                } else {
                    newValue = Utils.UpperFirstCharacter(newValue); // Áp dụng định dạng nếu hợp lệ
                }
                break;
            case "Phone":
                if (!isValidPattern(newValue, PHONE_PATTERN)) {
                    errorMessage = "Số điện thoại phải có 10 chữ số và định dạng Việt Nam hợp lệ cho hàng " + (row + 1) + ".";
                    validationSuccess = false;
                }
                break;
            case "Email":
                if (!isValidPattern(newValue, EMAIL_PATTERN)) {
                    errorMessage = "Định dạng email không hợp lệ cho hàng " + (row + 1) + ".";
                    validationSuccess = false;
                }
                break;
            // Không cần trường hợp default nếu tất cả các cột có thể chỉnh sửa được xử lý ở trên.
            // Nếu có các cột có thể chỉnh sửa khác, chúng sẽ đi qua đây mà không có xác thực.
        }

        if (validationSuccess) {
            // Chỉ cập nhật giá trị của model nếu xác thực thành công
            super.setValueAt(newValue, row, column);

            // Thông báo cho listener rằng ô đã được chỉnh sửa thành công
            if (tableEditListener != null) {
                tableEditListener.onCellEdited(row, column, newValue);
            }
        } else {
            // Nếu xác thực thất bại, hiển thị thông báo lỗi.
            JOptionPane.showMessageDialog(null, errorMessage, "Lỗi xác thực", JOptionPane.ERROR_MESSAGE);
            // Quan trọng, hoàn nguyên giá trị ô hiển thị về giá trị cũ của nó.
            // Nếu không, văn bản không hợp lệ vẫn còn trong ô, điều này không tốt cho trải nghiệm người dùng.
            super.setValueAt(oldValue, row, column);
        }
    }
}
