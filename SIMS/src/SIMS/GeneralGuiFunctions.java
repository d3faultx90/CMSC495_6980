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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class GeneralGuiFunctions {

	// Given a 2D list, add the item and the quantity of the list to the selected table
	static void addItemAndQuantityToTable(javax.swing.JTable table, List<List> resultsFromItemQuery) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (List l : resultsFromItemQuery) {
			model.addRow(new Object[] { l.get(1), addThousandsPlaces(l.get(6)) });
		}
	}

	// Given an int, add thousands place if needed and return as string
	static String addThousandsPlaces(int number) {
		return String.format("%,d", number);
	}

	// Overloaded. Given an Oject, add thousands place if needed and return as string
	static String addThousandsPlaces(Object number) {
		return String.format("%,d", Integer.parseInt((String) number));
	}

	// Given an object, return it as a double
	static double castObjectToDouble(Object obj) {
		double number = 0;
		try {
			number = Double.parseDouble((String) obj);
		} catch (Exception e) {
			customDisplayErrorPane("Error casting to double", "Failed Cast");
		}
		return number;
	}

	// Given an object, return it as a double
	static int castObjectToInteger(Object obj) {
		int number = 0;
		try {
			number = Integer.parseInt((String) obj);
		} catch (Exception e) {
			displayErrorPane("Error casting to double");
		}
		return number;
	}

	// Found from https://stackoverflow.com/a/4577820
	// Clears the table passed as a parameter
	static void clearTable(JTable table) {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();
		dm.fireTableDataChanged(); // notifies the JTable that the model has changed
	}

	// Found from https://www.youtube.com/watch?v=hFv2Uay0qj0
	// Closes the first window passed as a parameter and opens the window passed as the second parameter
	static void closeAndOpenWindow(Window toClose, javax.swing.JFrame toOpen) {
		WindowEvent closeWindow = new WindowEvent(toClose, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
		toOpen.setVisible(true);
	}

	// Used to easily display a JOptionPane to represent an error
	static void customDisplayErrorPane(String errorMessage, String headerMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, headerMessage, JOptionPane.ERROR_MESSAGE);
	}

	// Used to easily display confirmation messages given a string
	static void displayConfirmationPane(String confirmation) {
		try {
			ImageIcon icon = new ImageIcon("src/images/checkmark.png");
			JOptionPane.showMessageDialog(null, confirmation, "Confirmation", JOptionPane.INFORMATION_MESSAGE, icon);
		} catch (Exception e) {
			// If image fails to load, just show generic icon
			JOptionPane.showMessageDialog(null, confirmation, "Confirmation", JOptionPane.PLAIN_MESSAGE);
		}
	}

	// Used to easily display a JOptionPane to represent an error when given a string
	static void displayErrorPane(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Invalid Field Entry", JOptionPane.ERROR_MESSAGE);
	}

	// Used to easily display a JOptionPane to represent a help indow
	static void displayHelpPane(String helpInformation) {
		JOptionPane.showMessageDialog(null, helpInformation, "Help", JOptionPane.QUESTION_MESSAGE);
	}

	// Given a double, return it as a string formatted for prices
	static String doubleToDollarRepresentation(Double price) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
		return formatter.format(price);
	}

	// Method found from: https://stackoverflow.com/a/37989058
	// Given a table, filter the results of the table based on input of the textfield
	static void filterTable(javax.swing.JTable table, javax.swing.JTextField filterField) {
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) table.getModel()));
		sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterField.getText()));
		table.setRowSorter(sorter);
	}

	// Given the 2D list of sales, parse how many of the given object were sold
	static int parseQuantitySold(List<List> sales, Object itemId) {
		int itemsSold = 0;
		for (List l : sales) {
			// If the Id matches the Id in the sale (index 3), add the quantity
			if (itemId.equals(l.get(3))) {
				// Add the quantity (index 7) to total sold
				int sold = Integer.parseInt((String) l.get(7));
				itemsSold += sold;
			}
		}
		return itemsSold;
	}

	// This is called for when the table has been reduced by the unoverloaded method (item ID is not needed)
	static int parseQuantitySold(List<List> sales) {
		int itemsSold = 0;
		for (List l : sales) {
			itemsSold += Integer.parseInt((String) l.get(7));
		}
		return itemsSold;
	}

	// Given the 2D list of sales, parse total sales
	static double parseSales(List<List> sales) {
		double totalProfit = 0;
		for (List l : sales) {
			// In the given list, add up all values at index 6 (sales amount)
			totalProfit += Double.parseDouble((String) l.get(6));
		}
		return totalProfit;
	}

	// Given a string, attempt to parse it and display its dollar representation
	static String stringToDollarRepresentation(String unparsedPrice) {
		try {
			double price = Double.parseDouble(unparsedPrice);
			NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
			return formatter.format(price);
		} catch (Exception e) {
			displayErrorPane("That is not a number");
		}
		return "ERROR";
	}

}
