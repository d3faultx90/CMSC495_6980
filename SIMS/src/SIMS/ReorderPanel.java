/*
 * File: ReorderPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the reorder subtab
 */
package SIMS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class ReorderPanel extends javax.swing.JPanel {

	protected ReorderPanel() {
		initComponents();
	}

	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		GeneralGuiFunctions.displayHelpPane(
				"Select the order Id and click on view details to display more details or click on reorder to reorder");
	}

	private void viewOrderDetailsButtonActionPreformed(java.awt.event.ActionEvent evt) {
		OrderAndWasteDetailWindow.displayDetails(orderFilterPanel.orderTable, 1, Database.getOrderTable());
	}

	private void reorderButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Object[][] previousOrder = parseItemAndQuantity(orderFilterPanel.orderTable);
		if (Database.getRole() == 1) {
			SupervisorWindow.reorder(previousOrder);
		} else {
			UserWindow.reorder(previousOrder);
		}

	}

	static Object[][] parseItemAndQuantity(JTable table) {
		try {
			Map<Object, Object> itemNames = Database.getItemNamesMap();
			ArrayList<Object[]> masterList = new ArrayList<Object[]>();

			JTable viewItem = table;
			Object selectedCellValue = viewItem.getValueAt(viewItem.getSelectedRow(), 1);

			for (List request : Database.getOrderTable()) {

				if (selectedCellValue.equals(request.get(8))) {
					Object name = itemNames.get(request.get(4));
					Object quantity = request.get(7);
					Object[] grouped = new Object[] { name, quantity };
					masterList.add(grouped);
				}
			}
			// Convert 2D arraylist into 2D array
			Object[][] masterArray = new Object[masterList.size()][2];
			for (int i = 0; i < masterList.size(); i++) {

				masterArray[i] = masterList.get(i);

			}

			return masterArray;

		} catch (ArrayIndexOutOfBoundsException e) {

			GeneralGuiFunctions.displayErrorPane("Please select an item");

		}
		return new Object[1][1];
	}

	// Variables declaration - do not modify
	private javax.swing.JButton helpButton;
	protected SIMS.OrderFilterPanel orderFilterPanel;
	private javax.swing.JButton reorderButton1;
	private javax.swing.JButton viewOrderDetailsButton;
	// End of variables declaration

	private void initComponents() {

		orderFilterPanel = new SIMS.OrderFilterPanel();
		helpButton = new javax.swing.JButton();
		viewOrderDetailsButton = new javax.swing.JButton();
		reorderButton1 = new javax.swing.JButton();

		helpButton.setBackground(new java.awt.Color(255, 255, 153));
		helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
		helpButton.setText("?");
		helpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				helpButtonActionPerformed(evt);
			}
		});

		viewOrderDetailsButton.setText("View Order Details");
		viewOrderDetailsButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewOrderDetailsButtonActionPreformed(evt);
			}
		});

		reorderButton1.setText("Reorder");
		reorderButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				reorderButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addComponent(helpButton)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(orderFilterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addComponent(viewOrderDetailsButton, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(reorderButton1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(
										orderFilterPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(helpButton))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(viewOrderDetailsButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(reorderButton1).addContainerGap(44, Short.MAX_VALUE)));
	}// </editor-fold>

}
