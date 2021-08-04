/*
 * File: ChangePasswordWindow.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Window that allows the user to change their password
 */
package SIMS;

public class ChangePasswordWindow extends javax.swing.JFrame {


    public ChangePasswordWindow() {
        initComponents();
    }

    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        // TODO add your handling code here:
    }        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changePasswordButton;
    private javax.swing.JLabel confirmNewPasswordLabel;
    private javax.swing.JPasswordField confirmNewPasswordTextfield;
    private javax.swing.JLabel currentPasswordLabel;
    private javax.swing.JPasswordField currentPasswordTextfield;
    private javax.swing.JLabel newPasswordLabel;
    private javax.swing.JPasswordField newPasswordTextfield;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextfield;
    // End of variables declaration//GEN-END:variables
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        newPasswordLabel = new javax.swing.JLabel();
        confirmNewPasswordLabel = new javax.swing.JLabel();
        currentPasswordTextfield = new javax.swing.JPasswordField();
        newPasswordTextfield = new javax.swing.JPasswordField();
        confirmNewPasswordTextfield = new javax.swing.JPasswordField();
        currentPasswordLabel = new javax.swing.JLabel();
        usernameTextfield = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        changePasswordButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sales Inventory Management System (SIMS) - Reset");

        newPasswordLabel.setText("New password");

        confirmNewPasswordLabel.setText("Confirm new password");

        newPasswordTextfield.setToolTipText("Can't be old password. 8-20 characters, at least 1 capital, lowercase, and number. ");

        confirmNewPasswordTextfield.setToolTipText("Password must be over 8-20 characters and include a capital letter and number");

        currentPasswordLabel.setText("Current password");

        usernameLabel.setText("Username");

        changePasswordButton.setText("Change Password");
        changePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPasswordLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(confirmNewPasswordLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(currentPasswordLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(changePasswordButton, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(usernameTextfield, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(currentPasswordTextfield, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(newPasswordTextfield, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmNewPasswordTextfield, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currentPasswordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currentPasswordLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordLabel)
                    .addComponent(newPasswordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmNewPasswordLabel)
                    .addComponent(confirmNewPasswordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(changePasswordButton)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                            

}
