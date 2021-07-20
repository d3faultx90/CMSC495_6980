/*
 * File: GeneralGuiFunctions.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Holds functions that are used in multiple panels/windows of the GUI
 */
package SIMS;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class GeneralGuiFunctions {

    //https://www.youtube.com/watch?v=hFv2Uay0qj0
    static void closeWindow(Window toClose, javax.swing.JFrame toOpen) {
        WindowEvent closeWindow = new WindowEvent(toClose, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
        new SupervisorWindow().setVisible(true);
    }
    
    // Given a 2D list, add the item and the quantity of the list to the selected table
    static void addItemAndQuantityToTable(DefaultTableModel model, List<List> resultsFromItemQuery) {
        for (List l : resultsFromItemQuery) {
        	model.addRow(new Object[]{l.get(1), l.get(6)});
        }
    }
    
    // Method found from: https://stackoverflow.com/a/37989058
    // Given a table, filter the results of the table based on input of the textfield
    static void filterTable(javax.swing.JTable table, javax.swing.JTextField filterField) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterField.getText()));

        table.setRowSorter(sorter);
    }
}
