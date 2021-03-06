/*
 * File: OrderAndSalesPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the order and sales panels
 */
package SIMS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class OrderAndSalesPanel extends javax.swing.JPanel {

	private boolean isSalesPanel; // Used to differentiate between order and sales (slight differences)

	// Variables declaration
	private javax.swing.JButton addToButton;
	private javax.swing.JPanel completionPanel;
	private javax.swing.JPanel filterPanel;
	private javax.swing.JButton helpButton;
	protected SIMS.ItemFilterPanel itemFilterPanel;
	private javax.swing.JScrollPane jScrollPane6;
	protected javax.swing.JTable orderTable;
	private javax.swing.table.DefaultTableModel previousModel;
	private javax.swing.JLabel quantityLabel;
	private javax.swing.JTextField quantityTextfield;
	private javax.swing.JButton removeFromButton;
	// End of variables declaration
	
	protected OrderAndSalesPanel(boolean isSalesPanel) {
		this.isSalesPanel = isSalesPanel;
		initComponents();
	}

	// When pressed,checks if item is selected, quantity entered and adds the selected item along with its quantity to the order table
	private void addToButtonActionPerformed(java.awt.event.ActionEvent evt) {

		// Used to keep track of items that have already been added to the list
		ArrayList<String> list = new ArrayList<String>();

		try {

			for (int i = 0; i < orderTable.getModel().getRowCount(); i++) {

				list.add((String) orderTable.getModel().getValueAt(i, 0)); // get the all row values at column index 0
			}

			JTable table = itemFilterPanel.itemTable; // Access the filter panel's item table
			String selectedCellValue = (String) table.getValueAt(table.getSelectedRow(), 0);
			String selectedCellQuantity = (String) table.getValueAt(table.getSelectedRow(), 1);

			int requestingQuantity = Integer.parseInt(quantityTextfield.getText());

			if (quantityTextfield.getText().isEmpty()) {

				GeneralGuiFunctions.displayErrorPane("Please enter a quantity amount");

			} else if (!(requestingQuantity >= 1)) {

				GeneralGuiFunctions.displayErrorPane("Please enter a quantity amount greater than 0");

			} else if (requestingQuantity > Integer.parseInt(selectedCellQuantity.replaceAll(",", ""))
					&& isSalesPanel) {

				GeneralGuiFunctions.displayErrorPane("Requested quantity is greater than stock on hand");

			} else if (list.contains(selectedCellValue)) {

				GeneralGuiFunctions.displayErrorPane("That item is already in the order list");

			} else {

				DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
				model.addRow(new Object[] { selectedCellValue, quantityTextfield.getText() });
				// System.out.println(selectedCellValue + " " + quantityTextfield.getText());

			}

		} catch (NumberFormatException d) {

			GeneralGuiFunctions.displayErrorPane("Please enter a valid quantity amount");

		} catch (ArrayIndexOutOfBoundsException e) {

			GeneralGuiFunctions.displayErrorPane("Please select an item");

		} catch (Exception f) {

			GeneralGuiFunctions.displayErrorPane("An error has occured");

		}

	} 

	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {

		GeneralGuiFunctions.displayHelpPane("In this form you can:\n" + "Filter the current order list.\n"
				+ "Select an item and quantity amount then add it to the order.\n"
				+ "When finshed, click the Order button to send the order.");
		
	}
	
	// Checks if an item is selected then removes the selected row from the table
	private void removeFromButtonActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_removeFromButtonActionPerformed

		try {

			DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
			model.removeRow(orderTable.getSelectedRow());

		} catch (ArrayIndexOutOfBoundsException f) {
			GeneralGuiFunctions.displayErrorPane("Please select an item");
		} catch (Exception e) {

			GeneralGuiFunctions.displayErrorPane("An error has occured");
		}
	} // end removeFromButtonActionPerformed

	private void initComponents() {

		filterPanel = new javax.swing.JPanel();
		quantityLabel = new javax.swing.JLabel();
		quantityTextfield = new javax.swing.JTextField();
		addToButton = new javax.swing.JButton();
		helpButton = new javax.swing.JButton();
		itemFilterPanel = new SIMS.ItemFilterPanel();
		completionPanel = new javax.swing.JPanel();
		removeFromButton = new javax.swing.JButton();
		jScrollPane6 = new javax.swing.JScrollPane();
		orderTable = new javax.swing.JTable();

		filterPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		quantityLabel.setText("Quantity");

		addToButton.setText("Add to order");
		addToButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addToButtonActionPerformed(evt);
			}
		});

		helpButton.setBackground(new java.awt.Color(255, 255, 153));
		helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
		helpButton.setText("?");
		helpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				helpButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(filterPanel);
		filterPanel.setLayout(filterPanelLayout);
		filterPanelLayout.setHorizontalGroup(filterPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(filterPanelLayout.createSequentialGroup().addComponent(helpButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(filterPanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(filterPanelLayout.createSequentialGroup().addComponent(quantityLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(quantityTextfield)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(addToButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(itemFilterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 338,
										Short.MAX_VALUE))
						.addContainerGap(16, Short.MAX_VALUE)));
		filterPanelLayout.setVerticalGroup(filterPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(filterPanelLayout.createSequentialGroup()
						.addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(helpButton).addComponent(itemFilterPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE, 139,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(addToButton)
								.addComponent(quantityTextfield, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(quantityLabel))
						.addContainerGap()));

		completionPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		removeFromButton.setBackground(new java.awt.Color(153, 0, 0));
		removeFromButton.setText("Remove from order");
		removeFromButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeFromButtonActionPerformed(evt);
			}
		});
		orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 

		orderTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "Item", "Quantity" }) {
			boolean[] canEdit = new boolean[] { false, true };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane6.setViewportView(orderTable);

		javax.swing.GroupLayout completionPanelLayout = new javax.swing.GroupLayout(completionPanel);
		completionPanel.setLayout(completionPanelLayout);
		completionPanelLayout
				.setHorizontalGroup(completionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(completionPanelLayout.createSequentialGroup().addGap(43, 43, 43)
								.addComponent(removeFromButton, javax.swing.GroupLayout.PREFERRED_SIZE, 338,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(17, Short.MAX_VALUE))
						.addGroup(completionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(completionPanelLayout.createSequentialGroup().addGap(43, 43, 43)
										.addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 339,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(16, Short.MAX_VALUE))));
		completionPanelLayout
				.setVerticalGroup(completionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								completionPanelLayout.createSequentialGroup().addContainerGap(91, Short.MAX_VALUE)
										.addComponent(removeFromButton).addContainerGap())
						.addGroup(completionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(completionPanelLayout.createSequentialGroup().addContainerGap()
										.addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 79,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(34, Short.MAX_VALUE))));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(filterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(completionPanel, javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(filterPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(completionPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
	}

}