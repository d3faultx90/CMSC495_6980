/*
 * File: LoginWindow.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Window where the user logs in to the application
 */
package SIMS;

import java.util.Arrays;

public class LoginWindow extends javax.swing.JFrame {

    public LoginWindow() {
        initComponents();
    }
    
    private void usernameTextfieldActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	String serverAddress = addressTextfield.getText();
    	// need verify this is numbers 1-65535
    	int serverPort = Integer.parseInt(portTextfield.getText());
    	    	
        String username = usernameTextfield.getText();
        char[] password =  passwordTextfield.getPassword(); //Risk
        String trustStorePath = trustStorePathTextfield.getText();
        char[] trustStorePassword =  trustStorePasswordTextfield.getPassword(); //Risk
        
        Connector connector = new Connector(username, password, serverAddress, serverPort, 
        		trustStorePath, trustStorePassword); //Will accept trustStoreFilePath,trustStorePassword,mySqlPath
        
        boolean result = connector.verifyUser();
        // If the user is valid, then get their role
        if (result == true) {
            connector.getUserRole(); // Based on their role, show them their appropriate window
            if (connector.role == 0) {
                GeneralGuiFunctions.closeWindow(this, new AdminWindow(connector, username));
            }
            else if (connector.role == 1) {
                GeneralGuiFunctions.closeWindow(this, new SupervisorWindow(connector, username));
            }
            else if (connector.role == 2) {
            	// We do not have a user window currently
                GeneralGuiFunctions.closeWindow(this, new SupervisorWindow(connector, username));//Change this to normal user
            }

        }
        else {
            GeneralGuiFunctions.displayHelpPane("Credentials incorrect. Try again.");
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify                     
    private javax.swing.JPanel panel;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField addressTextfield;
    private javax.swing.JLabel portLabel;
    private javax.swing.JTextField portTextfield;
    private javax.swing.JLabel trustStorePathLabel;
    private javax.swing.JTextField trustStorePathTextfield;
    private javax.swing.JLabel trustStorePasswordLabel;
    private javax.swing.JPasswordField trustStorePasswordTextfield;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextfield;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextfield;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton changePasswordButton;
    // End of variables declaration    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        usernameTextfield = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        passwordTextfield = new javax.swing.JPasswordField();
        portLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        portTextfield = new javax.swing.JTextField();
        addressTextfield = new javax.swing.JTextField();
        trustStorePathLabel = new javax.swing.JLabel();
        trustStorePasswordLabel = new javax.swing.JLabel();
        trustStorePathTextfield = new javax.swing.JTextField();
        trustStorePasswordTextfield = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sales Inventory Management System (SIMS) - Login Window");
        setResizable(false);

        usernameLabel.setText("Username");

        passwordLabel.setText("Password");

        usernameTextfield.setText("bsutte");
        usernameTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextfieldActionPerformed(evt);
            }
        });

        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        passwordTextfield.setText("P@ssw0rd");

        portLabel.setText("Port");

        addressLabel.setText("SQL Address");

        portTextfield.setText("3306");

        addressTextfield.setText("sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com");

        trustStorePathLabel.setText("TrustStore Path:");

        trustStorePasswordLabel.setText("TrustStore Password:");

        trustStorePathTextfield.setText("C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore");

        trustStorePasswordTextfield.setText("password");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordLabel)
                            .addComponent(usernameLabel)
                            .addComponent(portLabel)
                            .addComponent(addressLabel)
                            .addComponent(trustStorePathLabel)
                            .addComponent(trustStorePasswordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usernameTextfield)
                            .addComponent(passwordTextfield)
                            .addComponent(portTextfield)
                            .addComponent(addressTextfield)
                            .addComponent(trustStorePathTextfield)
                            .addComponent(trustStorePasswordTextfield))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portLabel)
                    .addComponent(portTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trustStorePathLabel)
                    .addComponent(trustStorePathTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trustStorePasswordLabel)
                    .addComponent(trustStorePasswordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>  

}