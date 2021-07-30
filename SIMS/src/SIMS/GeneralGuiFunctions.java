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
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.RowFilter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;

public class GeneralGuiFunctions {

    //https://www.youtube.com/watch?v=hFv2Uay0qj0
    static void closeWindow(Window toClose, javax.swing.JFrame toOpen) {
        WindowEvent closeWindow = new WindowEvent(toClose, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
        toOpen.setVisible(true);
    }
    
    // Given a 2D list, add the item and the quantity of the list to the selected table
    static void addItemAndQuantityToTable(DefaultTableModel model, List<List> resultsFromItemQuery) {
        for (List l : resultsFromItemQuery) {
        	model.addRow(new Object[]{l.get(1), String.format("%,d", Integer.parseInt((String) l.get(6)))});
        }
    }
    
    // Method found from: https://stackoverflow.com/a/37989058
    // Given a table, filter the results of the table based on input of the textfield
    static void filterTable(javax.swing.JTable table, javax.swing.JTextField filterField) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterField.getText()));

        table.setRowSorter(sorter);
    }
    
    //Display this JOptionpane whenever a field is missing input or has negative values
    static void displayErrorPane(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage,
                "Invalid Field Entry", JOptionPane.ERROR_MESSAGE);
    }
    
    //Display this JOptionpane whenever a field is missing input or has negative values
    static void displayHelpPane(String helpInformation) {
        JOptionPane.showMessageDialog(null, helpInformation,
                "Help", JOptionPane.QUESTION_MESSAGE);
    }
    
    //Display this JOptionpane whenever a field is missing input or has negative values
    static String stringToPrice(String unparsedPrice) {
    	String parsedPrice = "";
        try {
        	double price = Double.parseDouble(unparsedPrice);  
        	NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        	String finalAmount = formatter.format(price);
        	if (price < 1) {
        		parsedPrice = "�" + price;
        	} else {
        		parsedPrice = "$" + price;
        	}
        } catch (Exception e) {
        	displayErrorPane("That is not a number");
        }
        return parsedPrice;
    }
    
    static void thing(javax.swing.JTable table) {
        table.getModel().addTableModelListener(new TableModelListener()
		{
			@Override
			public void tableChanged(TableModelEvent e)
			{

				int col = e.getColumn();
				int row = e.getFirstRow();

			}
		});
    }
    
//    static List<String> determineCategories(List<List> items){
//    	List<String> categories = new List<String>();
//    	return items;
//    }
}
