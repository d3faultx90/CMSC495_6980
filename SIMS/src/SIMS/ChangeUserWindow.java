/*
 * File: .java
 * Author: Ben Sutter
 * Date: Month day, 2021
 * Purpose:
 */
package SIMS;

public class ChangeUserWindow extends javax.swing.JFrame {
	
	// Used to signal whether or not the textfields should be pre-populated
	boolean isEditing;

    public ChangeUserWindow(boolean isEditing, String[] userInfo) {
    	this.isEditing = isEditing;
        initComponents();
        populateTestFieldCheck(userInfo);
    }

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpButtonActionPerformed

    private void addOrEditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOrEditButtonActionPerformed
        
    	String userName  = usernameTextfield.getText();
    	int newRole = roleComboBox.getSelectedIndex();
        String fName = firstNameTextfield.getText();
        String lName = lastNameTextfield.getText();
        
    	Database.getConnector().updateUserInfo(userName, newRole, fName, lName);
    	}//GEN-LAST:event_addOrEditButtonActionPerformed
    
    private void populateTestFieldCheck(String[] userInfo) {
    	if (isEditing) {
    		usernameTextfield.setText(userInfo[0]);
    		firstNameTextfield.setText(userInfo[1]);
    		lastNameTextfield.setText(userInfo[2]);
    		int permissionLevel = GeneralGuiFunctions.castObjectToInteger(userInfo[3]);
    		roleComboBox.setSelectedIndex(permissionLevel);
    		addOrEditButton.setText("Edit User");
    		usernameLabel.setVisible(false);
    		usernameTextfield.setVisible(false);
    	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addOrEditButton;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JTextField firstNameTextfield;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTextfield;
    private javax.swing.JLabel permissionLabel;
    private javax.swing.JComboBox<String> roleComboBox;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextfield;
    // End of variables declaration//GEN-END:variables

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roleComboBox = new javax.swing.JComboBox<>();
        permissionLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        usernameTextfield = new javax.swing.JTextField();
        lastNameTextfield = new javax.swing.JTextField();
        firstNameTextfield = new javax.swing.JTextField();
        addOrEditButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SIMS");

        roleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Supervisor", "User" }));

        permissionLabel.setText("Permission Level");

        usernameLabel.setText("Username");

        lastNameLabel.setText("Last Name");

        firstNameLabel.setText("First Name");
        
        roleComboBox.setSelectedIndex(2);

        addOrEditButton.setText("Add User");
        addOrEditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOrEditButtonActionPerformed(evt);
            }
        });

        helpButton.setBackground(new java.awt.Color(255, 255, 153));
        helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        helpButton.setForeground(new java.awt.Color(0, 0, 0));
        helpButton.setText("?");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addOrEditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lastNameLabel)
                                .addComponent(usernameLabel)
                                .addComponent(permissionLabel))
                            .addComponent(firstNameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lastNameTextfield)
                            .addComponent(usernameTextfield)
                            .addComponent(roleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(firstNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(permissionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addOrEditButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>    
    
}
