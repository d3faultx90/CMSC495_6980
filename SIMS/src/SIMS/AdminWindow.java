/*
 * File: AdminWindow.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: This is the window that the user sees when they login if they have admin permissions
 */

package SIMS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import enums.UserRole;

public class AdminWindow extends javax.swing.JFrame {

	static List<List> userInfoTable = new ArrayList<List>();
	String username;

	// Variables declaration - do not modify
	private javax.swing.JButton addUserButton;
	private javax.swing.JButton editUserButton;
	private javax.swing.JButton helpButton;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JButton logoutButton;
	private javax.swing.JButton removeUserButton;
	private javax.swing.JLabel userLabel;
	private javax.swing.JTable userTable;
	// End of variables declaration

	protected AdminWindow(Connector connector, String username) {
		Database database = new Database(connector);
		this.username = username;
		userInfoTable = Database.getUsersTable();
		initComponents();
		populateUserTable();
	}

	// This method was deprecated because it could not be implemented in time due to security concerns.
	private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
		GeneralGuiFunctions.displayErrorPane("Was unable to implement in time due to security concerns");
	}

	// When the use presses the edit user button, grab the selected user and display their information.
	private void editUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			String selectedCellValue = (String) userTable.getValueAt(userTable.getSelectedRow(), 0);
			String[] userInfo = new String[4];
			for (List userRow : userInfoTable) {
				// If the username is not the admin's username, add it to table (don't want
				// admin locking themselves out)
				if (selectedCellValue == userRow.get(1)) {
					userInfo[0] = (String) userRow.get(1);
					userInfo[1] = (String) userRow.get(2);
					userInfo[2] = (String) userRow.get(3);
					userInfo[3] = (String) userRow.get(4);

				}
			}
			new EditUserWindow(userInfo).setVisible(true);
		} catch (Exception e) {
			GeneralGuiFunctions.displayErrorPane("Please select a user");
		}

	}

	private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
		GeneralGuiFunctions.displayHelpPane("In this panel, you can edit current users or add a new user."
				+ "\nWhy do I not show up on the list? So you don't accidentally demote yourself.");
	}

	private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
		GeneralGuiFunctions.closeAndOpenWindow(this, new LoginWindow());
	}

	// Populates the user table with the current users in the database.
	private void populateUserTable() {
		DefaultTableModel model = (DefaultTableModel) userTable.getModel();
		for (List userRow : userInfoTable) {
			// If the username is not the admin's username, add it to table (don't want admin locking themselves out)
			if (!username.equals(userRow.get(1))) {
				// Use the enum to show the name of their role
				int permissionLevel = GeneralGuiFunctions.castObjectToInteger(userRow.get(4));
				model.addRow(new Object[] { userRow.get(1), UserRole.values()[permissionLevel] });
			}
		}
	}

	// This method was deprecated because it could not be implemented in time due to security concerns.
	private void removeUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
		GeneralGuiFunctions.displayErrorPane("Was unable to implement in time due to security concerns");
	}

	private void initComponents() {

		jScrollPane2 = new javax.swing.JScrollPane();
		userTable = new javax.swing.JTable();
		helpButton = new javax.swing.JButton();
		userLabel = new javax.swing.JLabel();
		editUserButton = new javax.swing.JButton();
		addUserButton = new javax.swing.JButton();
		removeUserButton = new javax.swing.JButton();
		logoutButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("SIMS - Admin");

		userTable.setModel(
				new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "Username", "Role" }));
		jScrollPane2.setViewportView(userTable);

		helpButton.setBackground(new java.awt.Color(255, 255, 153));
		helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
		helpButton.setForeground(new java.awt.Color(0, 0, 0));
		helpButton.setText("?");
		helpButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				helpButtonActionPerformed(evt);
			}
		});

		userLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		userLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		userLabel.setText("Active Users");

		editUserButton.setText("View/Edit");
		editUserButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editUserButtonActionPerformed(evt);
			}
		});

		addUserButton.setText("Add New User");
		addUserButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addUserButtonActionPerformed(evt);
			}
		});

		removeUserButton.setBackground(new java.awt.Color(153, 0, 0));
		removeUserButton.setText("Remove");
		removeUserButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeUserButtonActionPerformed(evt);
			}
		});

		logoutButton.setText("Logout");
		logoutButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logoutButtonActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addComponent(helpButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(logoutButton, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(removeUserButton, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(addUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(editUserButton, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING,
										javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(24, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(12, 12, 12).addComponent(userLabel))
								.addComponent(helpButton))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(editUserButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(addUserButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(removeUserButton).addGap(26, 26, 26).addComponent(logoutButton)
						.addContainerGap(22, Short.MAX_VALUE)));

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>

}
