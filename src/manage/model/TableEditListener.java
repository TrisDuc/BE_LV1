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
    // This method will be called when a cell is successfully edited and validated. 
    // It passes the row index, column index and new value.
    void onCellEdited(int row, int column, Object newValue);
}
