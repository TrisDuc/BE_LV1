/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import manage.controllers.EmployeeController;
import manage.model.Employee;
import manage.model.Utils;
import manage.model.CustomTableEmployee;
import manage.model.TableEditListener; 
/**
 *
 * @author 01duc
 */
public class Manageuser extends javax.swing.JFrame implements TableEditListener{
    EmployeeController employeeController = new EmployeeController();
    
    private CustomTableEmployee customTableModel;
    
    public Manageuser() {
        initComponents(); // Khởi tạo tất cả các thành phần UI trước
        setLocationRelativeTo(null);
        
        // QUAN TRỌNG: Đặt customTableModel thành model của JTable của bạn
        // Dòng này phải đứng SAU initComponents() đã đặt model
        customTableModel = (CustomTableEmployee) tableEmployee.getModel();
        // Đăng ký thể hiện Manageuser này làm listener cho các chỉnh sửa bảng
        customTableModel.setTableEditListener(this);
        
        loadEmployeeData(); // Gọi tải dữ liệu sau khi các thành phần được khởi tạo
    }

    // Phương thức này sẽ được CustomTableEmployee gọi khi một ô được chỉnh sửa thành công
    @Override
    public void onCellEdited(int row, int column, Object newValue) {
        // 1. Lấy đối tượng Employee gốc từ danh sách của employeeController
        // Lưu ý: Chỉ số hàng trong JTable tương ứng với chỉ số trong danh sách của employeeController
        if (row >= 0 && row < employeeController.size()) {
            Employee employeeToUpdate = employeeController.get(row);
            String columnName = customTableModel.getColumnName(column); // Lấy tên cột

            // 2. Cập nhật trường cụ thể của đối tượng Employee
            switch (columnName) {
                case "Username":
                    employeeToUpdate.setUsername((String) newValue);
                    break;
                case "First Name":
                    employeeToUpdate.setFirstName((String) newValue);
                    break;
                case "Last Name":
                    employeeToUpdate.setLastName((String) newValue);
                    break;
                case "Phone":
                    employeeToUpdate.setPhone((String) newValue);
                    break;
                case "Email":
                    employeeToUpdate.setEmail((String) newValue);
                    break;
                // Thêm các trường hợp cho các cột có thể chỉnh sửa khác nếu cần
                default:
                    // Nếu bạn có các cột không chuẩn, hãy xử lý chúng ở đây hoặc không làm gì
                    break;
            }
            
            // Tùy chọn: Bạn có thể muốn cung cấp phản hồi ngay lập tức cho người dùng
            // JOptionPane.showMessageDialog(this, "Dữ liệu nhân viên đã được cập nhật cục bộ cho " + columnName + ".", "Cập nhật thành công", JOptionPane.INFORMATION_MESSAGE);
            // Đây chỉ là một bản cập nhật cục bộ. Việc lưu tệp thực tế xảy ra khi nút "Save" được nhấp.

            // Bản thân bảng đã được cập nhật bởi setValueAt, vì vậy không cần gọi updateDataToTable() ở đây
            // trừ khi bạn có sự phụ thuộc lẫn nhau phức tạp.
        } else {
            System.err.println("Lỗi: Chỉ số hàng nằm ngoài giới hạn trong employeeController trong quá trình cập nhật.");
        }
    }

    // HÀM ĐỂ NẠP DỮ LIỆU VÀO JTABLE
    private void loadEmployeeData() {
        // Get the model that was already set up by initComponents()
        DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();

        // Clear existing rows in the table
        model.setRowCount(0);

        employeeController.readEmployee();
        
        // Populate the model with employee data
        int idCounter = 1; // Counter to generate sequential IDs
        for (Employee emp : employeeController) {
            Object[] row = {
                idCounter++,        // Auto-incrementing ID
                emp.getUsername(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getPhone(),
                emp.getEmail()
            };
            model.addRow(row); // Add the row to the table model
        }
    }

    private void updateDataToTable(EmployeeController employeeController) {
        // Get the model that was already set up by initComponents()
        DefaultTableModel model = (DefaultTableModel) tableEmployee.getModel();

        // Clear existing rows in the table
        model.setRowCount(0);

        // Populate the model with employee data
        int idCounter = 1; // Counter to generate sequential IDs
        for (Employee emp : employeeController) {
            Object[] row = {
                idCounter++,        // Auto-incrementing ID
                emp.getUsername(),
                emp.getFirstName(),
                emp.getLastName(),
                emp.getPhone(),
                emp.getEmail()
            };
            model.addRow(row); // Add the row to the table model
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableEmployee = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        tableEmployee.setModel(new manage.model.CustomTableEmployee( // <-- Use your custom model here
            new Object [][] {
                // You can leave this empty, as loadEmployeeData() will populate it
            },
            new String [] {
                "ID", "Username", "First Name", "Last Name", "Phone", "Email"
            }
        ));
        tableEmployee.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tableEmployeeComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(tableEmployee);

        btnAdd.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        btnAdd.setText("Add New Employee");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close - Copy.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Logout.png"))); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/reset.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(47, 47, 47)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset)
                        .addGap(54, 54, 54)
                        .addComponent(btnSave)
                        .addGap(43, 43, 43)
                        .addComponent(btnLogout))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnDelete)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleParent(tableEmployee);

        pack();
    }// </editor-fold>//GEN-END:initComponents
      
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        int save = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Selection", JOptionPane.YES_NO_OPTION);
        if (save == 0) {
            employeeController.writeDataToFile();
            JOptionPane.showMessageDialog(this, "Save successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        loadEmployeeData();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Truyền tham chiếu 'this' (chính là Manageuser hiện tại) vào constructor của addNewEmployee
        addNewEmployee addNewEmployee1Frame = new addNewEmployee(this);
        addNewEmployee1Frame.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableEmployee.getSelectedRow(); // Lấy chỉ số của hàng được chọn
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.", "No Employee Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy Username từ cột "Username" 
        String usernameToDelete = (String) tableEmployee.getModel().getValueAt(selectedRow, 1); // Cột Username là chỉ số 1

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete employee: " + usernameToDelete + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = employeeController.deleteEmployee(selectedRow);

            if (deleted) {
                JOptionPane.showMessageDialog(this, "Employee '" + usernameToDelete + "' deleted successfully!", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
                updateDataToTable(employeeController); // Cập nhật lại bảng sau khi xóa
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete employee '" + usernameToDelete + "'. Employee not found.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        int out = JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Selection", JOptionPane.YES_NO_OPTION);
        if (out == 0){
            new Login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    public void addNewEmployeeToList(Employee employee) {
        boolean result = employeeController.addEmployee(employee);
        if (result == true) {
            updateDataToTable(employeeController);
            JOptionPane.showMessageDialog(this, "Employee '" + employee.getUsername() + "' added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void tableEmployeeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableEmployeeComponentShown
        // TODO add your handling code here:
        try {
            loadEmployeeData();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_tableEmployeeComponentShown

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manageuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manageuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manageuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manageuser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manageuser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableEmployee;
    // End of variables declaration//GEN-END:variables
}
