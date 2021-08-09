/*
 * File: ItemFilterPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used to filter items
 */
package SIMS;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.util.ArrayList;
import java.util.List;

public class ItemFilterPanel extends javax.swing.JPanel {
	
	static List<List> currentInventory = new ArrayList<List>();

	// Variables declaration
	private javax.swing.JTextField filterField;
	private javax.swing.JLabel filterLabel;
	private javax.swing.JScrollPane itemScrollPane;
	protected javax.swing.JTable itemTable;
	// End of variables declaration
	

	protected ItemFilterPanel() {
		initComponents();
		initializeInventory();
		populateTable();
	}

	// Filters the table based on the text field
	private void filterFieldKeyReleased(java.awt.event.KeyEvent evt) {
		GeneralGuiFunctions.filterTable(itemTable, filterField);
	}
	
	// Since currentInventory is static, it should only be initialized once.
	private void initializeInventory() {
		if (currentInventory.isEmpty()) {
			currentInventory = Database.getItemTable();
		}
	}
	
	// Takes the current inventory and appends it to the item filter table
	private void populateTable() {
		GeneralGuiFunctions.addItemAndQuantityToTable(itemTable, currentInventory);
	}

	// Clears the table and then repopulates it.
	protected void refreshTable() {
		GeneralGuiFunctions.clearTable(itemTable);
		populateTable();
	}
	
	// Updates the currentInventory variable to the most recent state
	static void updateCurrentInventory() {
		currentInventory = Database.getItemTable();
	}
	
	private void initComponents() {

		filterLabel = new javax.swing.JLabel();
		filterField = new javax.swing.JTextField();
		itemScrollPane = new javax.swing.JScrollPane();
		itemTable = new javax.swing.JTable();

		filterLabel.setText("Filter");

		filterField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				filterFieldKeyReleased(evt);
			}
		});

		itemTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "Item Name", "Current Quantity" }) {

			boolean[] canEdit = new boolean[] { false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		itemScrollPane.setViewportView(itemTable);
		itemTable.changeSelection(0, 0, false, false);
		itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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

	}// </editor-fold>//GEN-END:initComponents

}
