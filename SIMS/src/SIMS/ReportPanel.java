/*
 * File: ReportPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the report tab of the main GUI
 */

package SIMS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class ReportPanel extends javax.swing.JPanel {

	ArrayList<String> uniqueYears = new ArrayList<String>();
	String[] itemNames;

	// Variables declaration - do not modify
	private javax.swing.JButton helpButton;
	private javax.swing.JRadioButton itemRadioButton;
	private javax.swing.JScrollPane itemScrollPane;
	private javax.swing.JComboBox<String> itemComboBox;
	private javax.swing.JButton monthlyBreakdownButton;
	private javax.swing.ButtonGroup radioButtonGroup;
	private javax.swing.JPanel reportsTab;
	private javax.swing.JRadioButton salesRadioButton;
	protected javax.swing.JTable yearAndProfitTable;
	private javax.swing.JLabel yearLookLabel;
	// End of variables declaration

	protected ReportPanel() {
		parseItemNames();
		initComponents();
		getUniqueYears();
		populateTableWithSales();
	}

	// Goes through all sales and parses the unique years
	private void getUniqueYears() {
		ArrayList<String> uniqueYears = new ArrayList<String>();
		for (List l : Database.getSalesTable()) {
			String year = l.get(8).toString().substring(0, 4);
			if (!uniqueYears.contains(year)) {
				// System.out.println(year);
				uniqueYears.add(year);
			}
		}
		Collections.sort(uniqueYears); // Sort the years
		Collections.reverse(uniqueYears); // Reverse them (so recent year is first)
		this.uniqueYears = uniqueYears;
	}

	// Gives a year to connector to retrieve
	private List<List> getYearsSales(String year) {
		return Database.getConnector().retrieveSalesOnDate(year);
	}

	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		GeneralGuiFunctions.displayHelpPane("All years with sale data will be displayed here. "
				+ "\nSelect a year and press the button to view the monthly breakdown of sales."
				+ "\nPress the month button in the new window to view even further details (FEATURE MAY BE CUT)");
	}
	
	// When the current combobox changes, the table is automatically populated with year and quantity sold
	private void itemComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		populateTableWithQuantitySold(itemComboBox.getSelectedItem());
	}

	
	private void itemRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
		getUniqueYears();
		itemComboBox.setVisible(true);
		yearAndProfitTable.getColumnModel().getColumn(1).setHeaderValue("Quantity Sold");
		yearAndProfitTable.getTableHeader().resizeAndRepaint();
		populateTableWithQuantitySold(itemComboBox.getSelectedItem());
	}

	private void monthlyBreakdownButtonActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			String selectedCellValue = (String) yearAndProfitTable.getValueAt(yearAndProfitTable.getSelectedRow(), 0);
			if (itemRadioButton.isSelected()) {
				Object itemId = Database.getItemIdMap().get(itemComboBox.getSelectedItem());
				new CalendarViewWindow(selectedCellValue, itemId).setVisible(true);
			} else if (salesRadioButton.isSelected()) {
				new CalendarViewWindow(selectedCellValue).setVisible(true);
			}
		} catch (ArrayIndexOutOfBoundsException f) {
			GeneralGuiFunctions.displayErrorPane("Please select an item");
		}

	}

	// Given the ItemTable, find all unique categories.
	private void parseItemNames() {
		ArrayList<String> categoryArrayList = new ArrayList<String>();
		for (List l : Database.getItemTable()) {

			// If category hasn't been added yet, then add it
			if (!categoryArrayList.contains(l.get(1))) {
				categoryArrayList.add(l.get(1).toString());
			}
		}
		// Convert to an Array because that is what the JComboBox expects
		itemNames = categoryArrayList.toArray(String[]::new);
	}

	private void populateTableWithQuantitySold(Object itemName) {

		GeneralGuiFunctions.clearTable(yearAndProfitTable);
		DefaultTableModel model = (DefaultTableModel) yearAndProfitTable.getModel();
		Map<Object, Object> itemIds = Database.getItemIdMap();
		for (String year : uniqueYears) {
			Object itemId = itemIds.get(itemName);

			int quantitySold = GeneralGuiFunctions.parseQuantitySold(getYearsSales(year), itemId);
			model.addRow(new Object[] { year, GeneralGuiFunctions.addThousandsPlaces(quantitySold) });
		}

	}

	private void populateTableWithSales() {
		GeneralGuiFunctions.clearTable(yearAndProfitTable);
		DefaultTableModel model = (DefaultTableModel) yearAndProfitTable.getModel();
		for (String year : uniqueYears) {
			double profits = GeneralGuiFunctions.parseSales(getYearsSales(year));
			model.addRow(new Object[] { year, GeneralGuiFunctions.doubleToDollarRepresentation(profits) });
		}

	}

	protected void refreshComboBox() {
		parseItemNames();
		itemComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(itemNames));
	}

	private void salesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {
		getUniqueYears();
		yearAndProfitTable.getColumnModel().getColumn(1).setHeaderValue("Profits");
		yearAndProfitTable.getTableHeader().resizeAndRepaint();
		itemComboBox.setVisible(false);
		populateTableWithSales();
	}

	private void yearAndProfitTableMouseClicked(java.awt.event.MouseEvent evt) {
		String selectedCellValue = (String) yearAndProfitTable.getValueAt(yearAndProfitTable.getSelectedRow(), 1);
		if (selectedCellValue.equals("0")) {
			monthlyBreakdownButton.setEnabled(false);
		} else {
			monthlyBreakdownButton.setEnabled(true);
		}
	}

	private void initComponents() {

		radioButtonGroup = new javax.swing.ButtonGroup();
		reportsTab = new javax.swing.JPanel();
		yearLookLabel = new javax.swing.JLabel();
		helpButton = new javax.swing.JButton();
		itemScrollPane = new javax.swing.JScrollPane();
		yearAndProfitTable = new javax.swing.JTable();
		monthlyBreakdownButton = new javax.swing.JButton();
		salesRadioButton = new javax.swing.JRadioButton();
		itemRadioButton = new javax.swing.JRadioButton();
		itemComboBox = new javax.swing.JComboBox<>();

		yearLookLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
		yearLookLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		yearLookLabel.setText("Year Look");

		helpButton.setBackground(new java.awt.Color(255, 255, 153));
		helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
		helpButton.setForeground(new java.awt.Color(0, 0, 0));
		helpButton.setText("?");
		helpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				helpButtonActionPerformed(evt);
			}
		});

		yearAndProfitTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
		}, new String[] { "Year", "Profits" }) {
			Class[] types = new Class[] { java.lang.String.class, java.lang.String.class };
			boolean[] canEdit = new boolean[] { false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		yearAndProfitTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				yearAndProfitTableMouseClicked(evt);
			}
		});
		itemScrollPane.setViewportView(yearAndProfitTable);
		itemComboBox.setVisible(false);
		yearAndProfitTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		monthlyBreakdownButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		monthlyBreakdownButton.setText("View Monthly Breakdown");
		monthlyBreakdownButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				monthlyBreakdownButtonActionPerformed(evt);
			}
		});

		radioButtonGroup.add(salesRadioButton);
		salesRadioButton.setSelected(true);
		salesRadioButton.setText("Total Sales");
		salesRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				salesRadioButtonActionPerformed(evt);
			}
		});

		radioButtonGroup.add(itemRadioButton);
		itemRadioButton.setText("Items Sold");
		itemRadioButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				itemRadioButtonActionPerformed(evt);
			}
		});

		itemComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(itemNames));
		itemComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				itemComboBoxActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout reportsTabLayout = new javax.swing.GroupLayout(reportsTab);
		reportsTab.setLayout(reportsTabLayout);
		reportsTabLayout.setHorizontalGroup(reportsTabLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(reportsTabLayout.createSequentialGroup().addComponent(helpButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(yearLookLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 325,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(reportsTabLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(reportsTabLayout.createSequentialGroup().addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
												.addGroup(reportsTabLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(itemScrollPane,
																javax.swing.GroupLayout.PREFERRED_SIZE, 0,
																Short.MAX_VALUE)
														.addComponent(monthlyBreakdownButton,
																javax.swing.GroupLayout.PREFERRED_SIZE, 325,
																javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(reportsTabLayout.createSequentialGroup().addGap(6, 6, 6)
												.addGroup(reportsTabLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(itemComboBox, 0,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(reportsTabLayout.createSequentialGroup()
																.addComponent(salesRadioButton,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(itemRadioButton,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 167,
																		javax.swing.GroupLayout.PREFERRED_SIZE))))))
						.addGap(44, 44, 44)));
		reportsTabLayout.setVerticalGroup(reportsTabLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(reportsTabLayout.createSequentialGroup()
						.addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(helpButton)
								.addGroup(reportsTabLayout.createSequentialGroup().addContainerGap()
										.addComponent(yearLookLabel)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(itemRadioButton).addComponent(salesRadioButton))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(itemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(itemScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(monthlyBreakdownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 54,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(8, 8, 8)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 419, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(reportsTab, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 392, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(reportsTab, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))));
	}

}