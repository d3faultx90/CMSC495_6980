/*
 * File: OrderOrWasteRequestPanel.java
 * Author: Ben Sutter
 * Date: July 27th, 2021
 * Purpose:
 */
package SIMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class OrderAndWasteRequestPanel extends javax.swing.JPanel {

	String tableTitle;
	List<List> resultsFromQuery; // Holds the results from the query (order or waste)

	// Variables declaration
	private javax.swing.JButton approveButton;
	private javax.swing.JButton denyButton;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel requestLabel;
	protected javax.swing.JTable requestTable;
	private javax.swing.JButton viewButton;
	// End of variables declaration

	protected OrderAndWasteRequestPanel(String tableTitle) {
		this.tableTitle = tableTitle;
		initComponents();
		// If table is order, grab order table, else grab waste
		this.resultsFromQuery = tableTitle.equals("Order") ? Database.getOrderTable() : Database.getWasteTable();
		addIdAndRequestingUserToTable((DefaultTableModel) requestTable.getModel());
	}

	// Populate the table with the date/time of request and the requesting user.
	private void addIdAndRequestingUserToTable(DefaultTableModel model) {
		Map usernames = Database.getUserIdMap();
		// Keeps track of requests already added since they only need to be seen once.
		ArrayList<String> alreadyAdded = new ArrayList<String>();
		for (List l : resultsFromQuery) {
			if (GeneralGuiFunctions.castObjectToInteger(l.get(9)) == 0) {
				String date = (String) l.get(8);
				if (!alreadyAdded.contains(date)) {
					model.addRow(new Object[] { date, usernames.get(l.get(2)) });
					alreadyAdded.add(date);
				}
			}
		}
	}

	// Passes off the approval to the approveOrDeny method
	private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		approveOrDeny(true);
	}

	private void approveOrDeny(boolean isApproved) {
		try {
			Object selectedCellValue = requestTable.getValueAt(requestTable.getSelectedRow(), 0);
			for (List request : resultsFromQuery) {

				if (selectedCellValue == request.get(8)) {
					// Approved = 1, denied = 2
					int status = isApproved ? 1 : 2;

					// Need to differentiate which table is being updated
					if (tableTitle.contains("Order")) {
						Database.getConnector().updateOrderStatus(status, request.get(1).toString());
						displayApprovalOrDenial(isApproved);
					} else if (tableTitle.contains("Waste")) {
						Database.getConnector().updateWasteStatus(status, request.get(1).toString());
						displayApprovalOrDenial(isApproved);
					} else {
						GeneralGuiFunctions.displayErrorPane("Problem in approveOrDeny in OrderOrWastePanel.java");
					}

					// Removes the row that was selected from the table
					((DefaultTableModel) requestTable.getModel()).removeRow((requestTable.getSelectedRow()));

					// Refresh the reorder table to show the new approved/denied order and refresh item tables.
					SupervisorWindow.refreshReorderTable();
					SupervisorWindow.refreshAllItemTables();

				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {

			GeneralGuiFunctions.displayErrorPane("Please select a request");

		}
	}

	// Passes off the denial to the approveOrDeny method
	private void denyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		approveOrDeny(false);
	}

	// Display a confirmation pane based on the selection chosen
	private void displayApprovalOrDenial(boolean isApproved) {
		if (isApproved) {
			GeneralGuiFunctions.displayConfirmationPane(tableTitle + " reqest was approved.");
		} else {
			GeneralGuiFunctions.displayConfirmationPane(tableTitle + " reqest was denied.");
		}
	}

	// Refreshes the table (is only called from the home panel's refresh inventory button)
	public void refreshTable() {
		GeneralGuiFunctions.clearTable(requestTable);
		this.resultsFromQuery = tableTitle.equals("Order") ? Database.getOrderTable() : Database.getWasteTable();
		addIdAndRequestingUserToTable((DefaultTableModel) requestTable.getModel());
	}

	// Displays the details of the selected request
	private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {
		if (tableTitle.equals("Order")) {
			OrderAndWasteDetailWindow.displayDetails(requestTable, 0, Database.getOrderTable());
		} else {
			OrderAndWasteDetailWindow.displayDetails(requestTable, 0, Database.getWasteTable());
		}

	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		requestTable = new javax.swing.JTable();
		requestLabel = new javax.swing.JLabel();
		viewButton = new javax.swing.JButton();
		approveButton = new javax.swing.JButton();
		denyButton = new javax.swing.JButton();

		requestTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Date", "Requesting User" }) {
			boolean[] canEdit = new boolean[] { false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		requestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jScrollPane1.setViewportView(requestTable);

		requestLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		requestLabel.setText(tableTitle + " Requests");

		viewButton.setText("View");
		viewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewButtonActionPerformed(evt);
			}
		});

		approveButton.setBackground(new java.awt.Color(0, 102, 0));
		approveButton.setText("Approve");
		approveButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				approveButtonActionPerformed(evt);
			}
		});

		denyButton.setBackground(new java.awt.Color(153, 0, 0));
		denyButton.setText("Deny");
		denyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				denyButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addGroup(layout.createSequentialGroup()
												.addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(approveButton, javax.swing.GroupLayout.PREFERRED_SIZE,
														118, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(denyButton, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(0, 0, Short.MAX_VALUE))
						.addComponent(requestLabel, javax.swing.GroupLayout.Alignment.TRAILING,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(requestLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(viewButton).addComponent(approveButton).addComponent(denyButton))
						.addGap(0, 6, Short.MAX_VALUE)));
	}

}
