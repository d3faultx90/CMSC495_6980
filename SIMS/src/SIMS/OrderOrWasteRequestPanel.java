/*
 * File: OrderOrWasteRequestPanel.java
 * Author: Ben Sutter
 * Date: July 27th, 2021
 * Purpose:
 */
package SIMS
;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class OrderOrWasteRequestPanel extends javax.swing.JPanel {

	String tableTitle;
	List<List> resultsFromQuery = new ArrayList<List>();

	public OrderOrWasteRequestPanel(String tableTitle, List<List> resultsFromQuery) {
		this.tableTitle = tableTitle;
		initComponents();
		this.resultsFromQuery = resultsFromQuery;
		addIdAndRequestingUserToTable((DefaultTableModel) requestTable.getModel());
	}

	private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {

		Object selectedCellValue = requestTable.getValueAt(requestTable.getSelectedRow(), 0);
		Map<Object, Object> itemNames = Database.getItemNamesMap();
		ArrayList<Object[]> masterList = new ArrayList<Object[]>();
		
		double totalPrice = 0;
		for (List request : resultsFromQuery) {
			
			if (selectedCellValue.equals(request.get(8))) {
				Object name = itemNames.get(request.get(4));
				Object quantity = request.get(7);
				Object price = request.get(6);
				totalPrice += GeneralGuiFunctions.castObjectToDouble(price);
				Object[] grouped = new Object[] {name, quantity, price};
				masterList.add(grouped);
			}
		}
		// Convert 2D arraylist into 2D array
		Object [][] masterArray = new Object[masterList.size()][3];
		for (int i = 0; i < masterList.size(); i ++) {
			masterArray[i] = masterList.get(i);
		}
		String formattedTotalPrice = GeneralGuiFunctions.priceToString(totalPrice);
		new OrderAndWasteDetailWindow(tableTitle, (String) selectedCellValue, masterArray, formattedTotalPrice).setVisible(true);
	}

	private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("Pressed edit button");
	}

	private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		approveOrDeny(true);
	}

	private void denyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		approveOrDeny(false);
	}

	private void approveOrDeny(boolean isApproved) {
		try {
			Object selectedCellValue = requestTable.getValueAt(requestTable.getSelectedRow(), 0);
			for (List request : resultsFromQuery) {

				if (selectedCellValue == request.get(8)) {
					int status = isApproved ? 1 : 2;

					if (tableTitle.contains("Order")) {
						Database.getConnector().updateOrderStatus(status, request.get(1).toString());
						printApprovalOrDenial(isApproved);
					} else if (tableTitle.contains("Waste")) {
						Database.getConnector().updateWasteStatus(status, request.get(1).toString());
						printApprovalOrDenial(isApproved);
					} else {
						GeneralGuiFunctions.displayErrorPane("Problem in approveOrDeny in OrderOrWastePanel.java");
					}

					((DefaultTableModel)requestTable.getModel()).removeRow((requestTable.getSelectedRow()));

				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {

			GeneralGuiFunctions.displayErrorPane("Please select a request");

		}
	}
	
	private void printApprovalOrDenial(boolean isApproved) {
		if (isApproved) {
			GeneralGuiFunctions.displayConfirmationPane(tableTitle + " reqest was approved.");
		} else {
			GeneralGuiFunctions.displayConfirmationPane(tableTitle + " reqest was denied.");
		}
	}

	// IF SUPERVISORS CAN GET ACCESS TO USER TABLES DO THIS HERE!!!!!!!!!!!!
	private void addIdAndRequestingUserToTable(DefaultTableModel model) {
		Map usernames = Database.getUserIdMap();
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

	// Variables declaration
	private javax.swing.JButton approveButton;
	private javax.swing.JButton denyButton;
	private javax.swing.JButton editButton;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel requestLabel;
	private javax.swing.JTable requestTable;
	private javax.swing.JButton viewButton;
	// End of variables declaration

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		requestTable = new javax.swing.JTable();
		requestLabel = new javax.swing.JLabel();
		viewButton = new javax.swing.JButton();
		editButton = new javax.swing.JButton();
		approveButton = new javax.swing.JButton();
		denyButton = new javax.swing.JButton();

		requestTable.setModel(
				new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "Date", "Requesting User" }));
		jScrollPane1.setViewportView(requestTable);

		requestLabel.setText(tableTitle + "Request(s)");

		viewButton.setText("View");
		viewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewButtonActionPerformed(evt);
			}
		});

		editButton.setText("Edit");
		editButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editButtonActionPerformed(evt);
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
		
		requestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addContainerGap()
										.addComponent(viewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(approveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(denyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(layout.createSequentialGroup().addContainerGap()
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap())
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE).addComponent(requestLabel).addGap(140, 140, 140)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(requestLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(viewButton).addComponent(editButton).addComponent(approveButton)
								.addComponent(denyButton))
						.addGap(0, 6, Short.MAX_VALUE)));
	}

}
