/*
 * File: SalesPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Holds the sales panel that is nested in User/Supervisor window
 */

package SIMS;

import java.util.Map;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

public class SalesPanel extends javax.swing.JPanel {

	protected SalesPanel() {
		initComponents();
	}

	private void saveSaleButtonActionPerformed(java.awt.event.ActionEvent evt) {
		/**
		 * Method grabs values from the JTable and gives them to createSales() to create a sale on the SQL database.
		 */

		// Copy variables here for easier reading
		javax.swing.JTable saleTable = salesPanel.orderTable;
		Map<Object, Object> itemIds = Database.getItemIdMap();
		Map<Object, Object> itemPrices = Database.getRetailItemPricesMap();

		if (saleTable.getRowCount() == 0) {

			GeneralGuiFunctions.displayErrorPane("Please select an item to order");

		} else {

			// DELETE THIS AND GRAB IT FROM ELSEWHERE
			int employeeId = Database.getConnector().userID;
			double salesTax = .08; // Don't hardcode
			String date = DateHandler.formatDateForSql(dateChooser.getDate());
			// 2D list that is passed into createSale() index 0 = itemID, index 1 = quantity
			List<List> itemIdsAndQuantity = new ArrayList<List>();
			// Iterates through the whole table to grab each row
			for (int i = 0; i < saleTable.getRowCount(); i++) {

				List<Object> list = new ArrayList<Object>();
				Object itemID = itemIds.get(saleTable.getValueAt(i, 0));
				Object unitPrice = itemPrices.get(saleTable.getValueAt(i, 0));
				Object quantity = saleTable.getValueAt(i, 1);
				list.add(itemID);
				list.add(unitPrice);
				list.add(quantity);
				itemIdsAndQuantity.add(list);

				// System.out.println("Sale happened for " + quantity + " items with the id of "
				// + itemID);
			}
			// System.out.println(itemIdsAndQuantity);
			// Pass the 2D list here once method is updated
			Database.getConnector().createSales(itemIdsAndQuantity, salesTax, date);

			// Clears the order table after the success
			GeneralGuiFunctions.clearTable(salesPanel.orderTable);

			Database.refreshAllTables();

			GeneralGuiFunctions.displayConfirmationPane("Sale completed sucessfully");
		}

	}

	// Variables declaration - do not modify
	protected com.toedter.calendar.JDateChooser dateChooser;
	protected SIMS.OrderAndSalesPanel salesPanel;
	private javax.swing.JPanel salesTab;
	protected javax.swing.JButton saveSaleButton;
	// End of variables declaration

	private void initComponents() {

		salesTab = new javax.swing.JPanel();
		saveSaleButton = new javax.swing.JButton();
		salesPanel = new SIMS.OrderAndSalesPanel(true);
		dateChooser = new com.toedter.calendar.JDateChooser();

		salesTab.setToolTipText("[187,187,187]");

		saveSaleButton.setBackground(new java.awt.Color(0, 102, 0));
		saveSaleButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
		saveSaleButton.setText("Save Sale");
		saveSaleButton.setToolTipText("[187,187,187]");
		saveSaleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveSaleButtonActionPerformed(evt);
			}
		});

		java.util.Date date = new java.util.Date();
		dateChooser.setDate(date);

		javax.swing.GroupLayout salesTabLayout = new javax.swing.GroupLayout(salesTab);
		salesTab.setLayout(salesTabLayout);
		salesTabLayout.setHorizontalGroup(salesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(salesTabLayout.createSequentialGroup().addContainerGap().addGroup(salesTabLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(saveSaleButton, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								salesTabLayout.createSequentialGroup().addGap(0, 7, Short.MAX_VALUE)
										.addGroup(salesTabLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(salesPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addContainerGap()));
		salesTabLayout.setVerticalGroup(salesTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(salesTabLayout.createSequentialGroup().addContainerGap()
						.addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(salesPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(saveSaleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE)
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 419, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(salesTab, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 385, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(salesTab, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))));
	}

}
