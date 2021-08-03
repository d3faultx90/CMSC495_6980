/*
 * File: OrderOrWasteRequestPanel.java
 * Author: Ben Sutter
 * Date: July 27th, 2021
 * Purpose:
 */
package SIMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderOrWasteRequestPanel extends javax.swing.JPanel {

	String tableTitle = "";
	List<List> resultsFromQuery = new ArrayList<List>();

	public OrderOrWasteRequestPanel(String tableTitle, List<List> resultsFromQuery) {
		this.tableTitle = tableTitle + " Requests";
		initComponents();
		this.resultsFromQuery = resultsFromQuery;
		//addIdAndRequestingUserToTable((DefaultTableModel) requestTable.getModel());
	}

	private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("Pressed view button");
	}

	private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println("Pressed edit button");
	}

	private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {
		approveOrDeny(true);
		GeneralGuiFunctions.displayConfirmationPane(tableTitle + " was approved.");
	}

	private void denyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		approveOrDeny(false);
		GeneralGuiFunctions.displayConfirmationPane(tableTitle + " was denied.");
	}
	
	private void approveOrDeny(boolean isApproved) {
		Object selectedCellValue = requestTable.getValueAt(requestTable.getSelectedRow(), 0);
		for (List request : resultsFromQuery) {
			
			if (selectedCellValue == request.get(9)) {
				int status = isApproved? 1 : 2;
				System.out.println(request.get(1).toString());
				if (tableTitle.contains("Order")) {
					Database.getConnector().updateOrderStatus(status, request.get(1).toString());
				} else if (tableTitle.contains("Waste")) {
					Database.getConnector().updateWasteStatus(status, request.get(1).toString());
				} else {
					GeneralGuiFunctions.displayErrorPane("Problem in approveOrDeny in OrderOrWastePanel.java");
				}
			}
		}
	}

//	// Given a 2D list, add the item and the quantity of the list to the selected
//	// table
//	private void addIdAndRequestingUserToTable(DefaultTableModel model) {
//		for (List l : resultsFromQuery) {
//			if (GeneralGuiFunctions.castSqlObjectToInteger(l.get(10)) == 0) {
//				model.addRow(new Object[] { l.get(9), l.get(2) });
//			}
//		}
//	}
	
	// IF SUPERVISORS CAN GET ACCESS TO USER TABLES DO THIS HERE!!!!!!!!!!!!
//	private void addIdAndRequestingUserToTable(DefaultTableModel model) {
//		Map usernames = Database.getUserIdMap();
//		for (List l : resultsFromQuery) {
//			if (GeneralGuiFunctions.castObjectToInteger(l.get(9)) == 0) {
//				System.out.println(usernames);
//				model.addRow(new Object[] { l.get(8), usernames.get(l.get(2)) });
//			}
//		}
//	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton approveButton;
	private javax.swing.JButton denyButton;
	private javax.swing.JButton editButton;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel requestLabel;
	private javax.swing.JTable requestTable;
	private javax.swing.JButton viewButton;
	// End of variables declaration//GEN-END:variables

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
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

		requestLabel.setText(tableTitle);

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
	}// </editor-fold>

}
