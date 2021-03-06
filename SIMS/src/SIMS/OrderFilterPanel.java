/*
 * File: OrderFilterPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used to filter orders
 */
package SIMS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import enums.OrderStatus;

public class OrderFilterPanel extends javax.swing.JPanel {

	// Variables declaration - do not modify
	private javax.swing.JTextField filterField;
	private javax.swing.JLabel filterLabel;
	private javax.swing.JScrollPane itemScrollPane;
	protected javax.swing.JTable orderTable;
	// End of variables declaration

	protected OrderFilterPanel() {
		initComponents();
		changeTableHeaderWidth();
		addOrderAndIdToTable();

	}

	// Given a 2D list, add the item and the quantity of the list to the selected table
	private void addOrderAndIdToTable() {

		ArrayList<String> alreadyAdded = new ArrayList<String>();
		DefaultTableModel model = (DefaultTableModel) orderTable.getModel();

		for (List l : Database.getOrderTable()) {

			int status = GeneralGuiFunctions.castObjectToInteger(l.get(9)); // Index 9 = Status
			// 0 = pending, so if it is not pending (Approved/Denied) then show it
			if (status > 0) {
				String date = (String) l.get(8);

				if (!alreadyAdded.contains(date)) {
					model.addRow(new Object[] { l.get(1), l.get(8), OrderStatus.values()[status] });
					alreadyAdded.add(date);

				}
			}
		}
	}
	
	// Adjustst the width of the columns in the table
	private void changeTableHeaderWidth() {
		TableColumnModel columnModel = orderTable.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(35);
		columnModel.getColumn(2).setPreferredWidth(20);
	}
	
	// Filters the table based on input from the textfield
	private void filterFieldKeyReleased(java.awt.event.KeyEvent evt) {
		GeneralGuiFunctions.filterTable(orderTable, filterField);
	}
	
	// Refreshes the table (called when a request is approved)
	public void refreshTable() {
		GeneralGuiFunctions.clearTable(orderTable);
		addOrderAndIdToTable();
	}
	
	private void initComponents() {

		filterLabel = new javax.swing.JLabel();
		filterField = new javax.swing.JTextField();
		itemScrollPane = new javax.swing.JScrollPane();
		orderTable = new javax.swing.JTable();

		filterLabel.setText("Filter");

		filterField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				filterFieldKeyReleased(evt);
			}
		});

		orderTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "Order ID", "Order Date", "Status" }) {
			Class[] types = new Class[] { java.lang.String.class, java.lang.String.class, java.lang.Object.class };
			boolean[] canEdit = new boolean[] { false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		itemScrollPane.setViewportView(orderTable);
		orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(itemScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup().addComponent(filterLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(filterField, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(filterLabel).addComponent(filterField, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(itemScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
				.addContainerGap()));
	}

}
