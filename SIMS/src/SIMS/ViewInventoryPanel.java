/*
 * File: ViewInventoryPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the view inventory subtab
 */
package SIMS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

public class ViewInventoryPanel extends javax.swing.JPanel {

	// Variables declaration - do not modify
	private javax.swing.JButton helpButton;
	protected SIMS.ItemFilterPanel itemFilterPanel;
	private javax.swing.JPanel panel;
	private javax.swing.JButton viewDetailButton;
	// End of variables declaration

	protected ViewInventoryPanel() {
		initComponents();
	}

	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		GeneralGuiFunctions.displayHelpPane("Select the item you want to view and click on view details!");

	}

	// When the button is pressed, grab all details about the item and display them so they can see all info about the item
	private void viewDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			JTable table = itemFilterPanel.itemTable;
			String selectedCellValue = (String) table.getValueAt(table.getSelectedRow(), 0);

			for (List l : Database.getItemTable()) {
				// If there is match, create the window and break early
				if (l.get(1) == selectedCellValue) {

					// https://www.codermag.net/2016/07/how-to-convert-list-to-list.html
					List<String> stringList = (List<String>) (Object) l;
					new ItemDetailsWindow(stringList.get(1), stringList.get(2), stringList.get(3), stringList.get(4),
							stringList.get(5), stringList.get(6)).setVisible(true);
					break;
				}
			}

		} catch (Exception e) {
			GeneralGuiFunctions.displayErrorPane("Please select an item");
		}

	}

	private void initComponents() {

		panel = new javax.swing.JPanel();
		viewDetailButton = new javax.swing.JButton();
		helpButton = new javax.swing.JButton();
		itemFilterPanel = new SIMS.ItemFilterPanel();

		panel.setPreferredSize(new java.awt.Dimension(436, 234));

		viewDetailButton.setText("View Details");
		viewDetailButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewDetailButtonActionPerformed(evt);
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

		javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
		panel.setLayout(panelLayout);
		panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup().addComponent(helpButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(viewDetailButton, javax.swing.GroupLayout.DEFAULT_SIZE, 348,
										Short.MAX_VALUE)
								.addComponent(itemFilterPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(25, Short.MAX_VALUE)));
		panelLayout.setVerticalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLayout.createSequentialGroup()
						.addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(panelLayout.createSequentialGroup().addComponent(helpButton).addGap(0, 248,
										Short.MAX_VALUE))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										panelLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(
												itemFilterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 278,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(viewDetailButton).addGap(40, 40, 40)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 417, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 417,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 354, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 354,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))));
	}

}
