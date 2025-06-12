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
public interface TableEditListener {
    // Phương thức này sẽ được gọi khi một ô được chỉnh sửa và xác thực thành công.
    // Nó truyền chỉ số hàng, chỉ số cột và giá trị mới.
    void onCellEdited(int row, int column, Object newValue);
}
