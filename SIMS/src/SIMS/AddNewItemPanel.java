/*
 * File: AddNewItemPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the Add New Item subtab
 */

package SIMS;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class AddNewItemPanel extends javax.swing.JPanel {

	String[] categories; // Holds all recognized categories to put in combo box.

	// Variables declaration - do not modify
	private javax.swing.JButton addItemButton;
	private javax.swing.JLabel barcodeLabel;
	private javax.swing.JComboBox<String> categoryComboBox;
	private javax.swing.JLabel categoryLabel;
	private javax.swing.JLabel descriptionLabel;
	private javax.swing.JButton helpButton;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea descriptionTextArea;
	private javax.swing.JPanel mainPanel;
	private javax.swing.JLabel nameLabel;
	private javax.swing.JTextField nameTextfield;
	private javax.swing.JLabel newCategoryLabel;
	private javax.swing.JTextField newCategoryTextfield;
	private javax.swing.JLabel purchasePriceLabel;
	private javax.swing.JTextField purchasePriceTextfield;
	private javax.swing.JLabel quantityLabel;
	private javax.swing.JTextField quantityTextfield;
	private javax.swing.JLabel sellPriceLabel;
	private javax.swing.JTextField sellPriceTextfield;
	// End of variables declaration

	protected AddNewItemPanel() {
		parseCategories(); // Parse categories before initializing combo box
		initComponents();
	}

	private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			String name = nameTextfield.getText();
			String description = descriptionTextArea.getText();
			// If new category (index 0) is seleted, grab string from textfield rather than combobox selection
			String category = categoryComboBox.getSelectedIndex() == 0 ? newCategoryTextfield.getText() : (String) categoryComboBox.getSelectedItem();
			double wholeSalePrice = GeneralGuiFunctions.castObjectToDouble(purchasePriceTextfield.getText());
			double retailPrice = GeneralGuiFunctions.castObjectToDouble(sellPriceTextfield.getText());
			int quantity = GeneralGuiFunctions.castObjectToInteger(quantityTextfield.getText());

			if (name.isEmpty() || description.isEmpty() || category.isEmpty()) {
				GeneralGuiFunctions.displayErrorPane("Please ensure all textfields are filled in");
			} else if (wholeSalePrice == 0 || retailPrice == 0 || quantity == 0 ) {
				GeneralGuiFunctions.displayErrorPane("Textfields must not have a value of 0");
			} else {
				try {
					Database.getConnector().createInventoryItem(name, description,
							category, wholeSalePrice, retailPrice, quantity);
					GeneralGuiFunctions.displayConfirmationPane("Item successfully added");
					Database.refreshAllTables();
					parseCategories();
					categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(categories));
					resetAllTextfields();
					Database.refreshAllComboBoxes();
				} catch (Exception e) {
					// This unique exception occurs when the item already exists in SQL
					GeneralGuiFunctions.customDisplayErrorPane(e.getMessage(), "Invalid item addition attempt.");
				}
			}

		} catch (Exception e) {
			GeneralGuiFunctions.displayErrorPane("Error encountered, please check all textfields.");
		}

	}
	
	private void resetAllTextfields() {
		nameTextfield.setText("");
		categoryComboBox.setSelectedIndex(0); // Not a textfield but should be reset regardleess
		newCategoryTextfield.setText("");
		purchasePriceTextfield.setText("");
		sellPriceTextfield.setText("");
		descriptionTextArea.setText("");
		quantityTextfield.setText("");
	}

	// If the index is new category (0), the show textfield, otherwise hide it.
	private void categoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
		if (categoryComboBox.getSelectedIndex() == 0) {
			newCategoryLabel.setVisible(true);
			newCategoryTextfield.setVisible(true);
		} else {
			newCategoryLabel.setVisible(false);
			newCategoryTextfield.setVisible(false);
		}
	}

	// This limits the textfield so the user can only type numbers or a single
	// period.
	private void ensureValidDoubleInput(java.awt.event.KeyEvent evt, javax.swing.JTextField textfield) {
		char c = evt.getKeyChar();
		if ((!Character.isDigit(c) && c != '.') || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
			evt.consume();
		} else if (textfield.getText().contains(".") && c == '.') {
			evt.consume();
		}
	}

	// Displays some help information for the user
	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		GeneralGuiFunctions.displayHelpPane("Fill the form and click on Add Item to add the item to the inventory");
	}

	// Given the ItemTable, find all unique categories.
	private void parseCategories() {
		ArrayList<String> categoryArrayList = new ArrayList<String>();
		categoryArrayList.add("New Category"); // Category field must be filled in
		for (List l : Database.getItemTable()) {

			// If category hasn't been added yet, then add it
			if (!categoryArrayList.contains(l.get(3))) {
				categoryArrayList.add(l.get(3).toString());
			}
		}
		// Convert to an Array because that is what the JComboBox expects
		categories = categoryArrayList.toArray(String[]::new);
	}

	// Ensures each input from the user is valid.
	private void purchasePriceTextfieldKeyTyped(java.awt.event.KeyEvent evt) {
		ensureValidDoubleInput(evt, purchasePriceTextfield);
	}

	// Ensures the textfield contains only digits
	private void quantityTextFieldKeyTyped(java.awt.event.KeyEvent evt) {
		char c = evt.getKeyChar();
		if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
			evt.consume();
		}
	}

	// Ensures each input from the user is valid.
	private void sellPriceTextfieldKeyTyped(java.awt.event.KeyEvent evt) {
		ensureValidDoubleInput(evt, sellPriceTextfield);
	}

	private void initComponents() {

		mainPanel = new javax.swing.JPanel();
		addItemButton = new javax.swing.JButton();
		nameLabel = new javax.swing.JLabel();
		categoryLabel = new javax.swing.JLabel();
		purchasePriceLabel = new javax.swing.JLabel();
		sellPriceLabel = new javax.swing.JLabel();
		barcodeLabel = new javax.swing.JLabel();
		descriptionLabel = new javax.swing.JLabel();
		categoryComboBox = new javax.swing.JComboBox<>();
		purchasePriceTextfield = new javax.swing.JTextField();
		sellPriceTextfield = new javax.swing.JTextField();
		nameTextfield = new javax.swing.JTextField();
		quantityLabel = new javax.swing.JLabel();
		quantityTextfield = new javax.swing.JTextField();
		helpButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		descriptionTextArea = new javax.swing.JTextArea();
		newCategoryLabel = new javax.swing.JLabel();
		newCategoryTextfield = new javax.swing.JTextField();

		addItemButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
		addItemButton.setText("Add Item");
		addItemButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addItemButtonActionPerformed(evt);
			}
		});

		nameLabel.setText("Name");

		categoryLabel.setText("Category");

		purchasePriceLabel.setText("Purchase Price");

		sellPriceLabel.setText("Sell Price");

		barcodeLabel.setText("Description");

		descriptionLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		descriptionLabel.setText("Add New Item To Database");

		categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(categories));
		categoryComboBox.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				categoryComboBoxActionPerformed(evt);
			}
		});

		purchasePriceTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				purchasePriceTextfieldKeyTyped(evt);
			}
		});

		sellPriceTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				sellPriceTextfieldKeyTyped(evt);
			}
		});

		quantityLabel.setText("Quantity");

		quantityTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				quantityTextFieldKeyTyped(evt);
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

		descriptionTextArea.setColumns(20);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setRows(5);
		descriptionTextArea.setAutoscrolls(false);
		descriptionTextArea.setMaximumSize(new java.awt.Dimension(13, 20));
		jScrollPane1.setViewportView(descriptionTextArea);

		newCategoryLabel.setText("New Category");

		javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(mainPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE).addComponent(helpButton)
						.addGroup(mainPanelLayout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
										javax.swing.GroupLayout.Alignment.TRAILING,
										mainPanelLayout.createSequentialGroup().addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE,
														361, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGroup(mainPanelLayout.createSequentialGroup().addGroup(mainPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(mainPanelLayout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(mainPanelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(mainPanelLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(purchasePriceLabel)
																.addComponent(sellPriceLabel,
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(categoryLabel,
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(nameLabel,
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(barcodeLabel,
																		javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(newCategoryLabel,
																		javax.swing.GroupLayout.Alignment.TRAILING))
														.addComponent(quantityLabel))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(mainPanelLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(quantityTextfield)
														.addComponent(categoryComboBox, 0,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(nameTextfield).addComponent(sellPriceTextfield)
														.addComponent(purchasePriceTextfield).addComponent(jScrollPane1)
														.addComponent(newCategoryTextfield)))
										.addGroup(mainPanelLayout.createSequentialGroup().addGap(37, 37, 37)
												.addComponent(addItemButton, javax.swing.GroupLayout.PREFERRED_SIZE,
														286, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup()
						.addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(mainPanelLayout.createSequentialGroup().addContainerGap()
										.addComponent(descriptionLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(nameLabel).addComponent(nameTextfield,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(categoryLabel).addComponent(categoryComboBox,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(newCategoryTextfield,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(newCategoryLabel))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(purchasePriceTextfield,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(purchasePriceLabel))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(sellPriceTextfield,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(sellPriceLabel))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(mainPanelLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(barcodeLabel)
												.addGroup(mainPanelLayout.createSequentialGroup()
														.addComponent(jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE, 48,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(mainPanelLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(quantityTextfield,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(quantityLabel)))))
								.addComponent(helpButton))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(addItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(49, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 6, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
	}

}