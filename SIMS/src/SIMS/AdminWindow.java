/*
 * File: AdminWindow.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: This is the window that the user sees if they have admin permissions
 */

package SIMS;

public class AdminWindow extends javax.swing.JFrame {

    public AdminWindow() {
        initComponents();
    }
    
    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_helpButtonActionPerformed

    private void editUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserButtonActionPerformed
    	new ViewOrAddUserWindow().setVisible(true);
    }//GEN-LAST:event_editUserButtonActionPerformed

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
    	new ViewOrAddUserWindow().setVisible(true);
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void removeUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeUserButtonActionPerformed
        // Confirmation
    }//GEN-LAST:event_removeUserButtonActionPerformed
    
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	GeneralGuiFunctions.closeWindow(this, new LoginWindow());
    } 

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

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"bdsutte", "<role>"},
                {null, "SUPERVISOR"}
            },
            new String [] {
                "Username", "Role"
            }
        ));
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
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(helpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(removeUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(editUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(userLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(helpButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editUserButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addUserButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeUserButton)
                .addGap(26, 26, 26)
                .addComponent(logoutButton)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>  
}
