/*
 /*
 * File: WastePanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the Waste Subtab
 */

package SIMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class WastePanel extends javax.swing.JPanel {

	public WastePanel() {
		initComponents();

	}

	private void wasteButtonActionPerformed(java.awt.event.ActionEvent evt) {

		try {

			JTable table = itemFilterPanel.itemTable; // Access the filter panel's item table
			String selectedCellValue = (String) table.getValueAt(table.getSelectedRow(), 0);

			if (quantityTextfield.getText().isEmpty()) {

				GeneralGuiFunctions.displayErrorPane("Please enter a quantity amount");

			}

			if (!(Integer.parseInt(quantityTextfield.getText()) >= 1)) {

				GeneralGuiFunctions.displayErrorPane("Please enter a quantity amount greater than 0");

			} else {

				System.out.println(selectedCellValue + " quantity: " + quantityTextfield.getText());

			}

			Connector connector = Database.getConnector();

			// employeeId connecter.userID
			Map<Object, Object> itemIds = new HashMap<Object, Object>();
			Map<Object, Object> itemPrices = new HashMap<Object, Object>();
			Object id = itemIds.get(selectedCellValue);
			Object price = itemPrices.get(selectedCellValue);

			int itemID = GeneralGuiFunctions.castObjectToInteger(id);
			double wholeSalePrice = GeneralGuiFunctions.castObjectToDouble(price);
			String quantity = quantityTextfield.getText();
			int removalQuantity = GeneralGuiFunctions.castObjectToInteger(quantity);
			String date = DateHandler.getTodaysDateUser();
			int status = 0;

			// createWaste(int itemID, double wholeSalePrice, int removalQuantity,String
			// date, int status)
			Database.getConnector().createWaste(itemID, wholeSalePrice, removalQuantity, date, status);

		} catch (NumberFormatException d) {

		} catch (ArrayIndexOutOfBoundsException e) {

			GeneralGuiFunctions.displayErrorPane("Please select an item");

		}

	} // end wasteButtonActionPerformed

	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		GeneralGuiFunctions.displayHelpPane("Here is how this panel works!");
	}

	// Variables declaration - do not modify
	private javax.swing.JButton helpButton;
	private SIMS.ItemFilterPanel itemFilterPanel;
	private javax.swing.JPanel panel;
	private javax.swing.JLabel quantityLabel;
	private javax.swing.JTextField quantityTextfield;
	private javax.swing.JButton wasteButton;
	// End of variables declaration

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		panel = new javax.swing.JPanel();
		wasteButton = new javax.swing.JButton();
		quantityLabel = new javax.swing.JLabel();
		quantityTextfield = new javax.swing.JTextField();
		helpButton = new javax.swing.JButton();
		itemFilterPanel = new SIMS.ItemFilterPanel();

		wasteButton.setText("Waste Item(s)");
		wasteButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				wasteButtonActionPerformed(evt);
			}
		});

		quantityLabel.setText("Quantity to waste ");

		helpButton.setBackground(new java.awt.Color(255, 255, 153));
		helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
		helpButton.setText("?");
		helpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				helpButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
		panel.setLayout(panelLayout);
		panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup()
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(panelLayout.createSequentialGroup().addComponent(helpButton).addGap(134, 134,
										134))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										panelLayout.createSequentialGroup().addComponent(quantityLabel).addGap(18, 18,
												18)))
						.addComponent(quantityTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
						.addComponent(wasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 154,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						panelLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(itemFilterPanel,
								javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)));
		panelLayout.setVerticalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup()
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(panelLayout.createSequentialGroup().addComponent(helpButton)
										.addGap(216, 216, 216))
								.addComponent(itemFilterPanel, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(wasteButton)
								.addComponent(quantityTextfield, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(quantityLabel))
						.addContainerGap(66, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 417, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(0, 14, Short.MAX_VALUE)
								.addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 15, Short.MAX_VALUE))));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 357, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
								.addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))));
	}// </editor-fold>

}
