/*
 * File: ReorderPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the reorder subtab
 */
package SIMS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

public class ReorderPanel extends javax.swing.JPanel {
	
    public ReorderPanel() {
        initComponents();
    }   

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        GeneralGuiFunctions.displayHelpPane("Here is how this panel works!");
    }                                          

    private void viewOrderDetailsButtonActionPreformed(java.awt.event.ActionEvent evt) {                                                       
        // TODO add your handling code here:
    }                                                      

    private void reorderButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                         


    // Variables declaration - do not modify                     
    private javax.swing.JButton helpButton;
    private SIMS.OrderFilterPanel orderFilterPanel1;
    private javax.swing.JButton reorderButton1;
    private javax.swing.JButton viewOrderDetailsButton;
    // End of variables declaration  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        orderFilterPanel1 = new SIMS.OrderFilterPanel();
        helpButton = new javax.swing.JButton();
        viewOrderDetailsButton = new javax.swing.JButton();
        reorderButton1 = new javax.swing.JButton();

        helpButton.setBackground(new java.awt.Color(255, 255, 153));
        helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        helpButton.setText("?");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        viewOrderDetailsButton.setText("View Order Details");
        viewOrderDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewOrderDetailsButtonActionPreformed(evt);
            }
        });

        reorderButton1.setText("Reorder");
        reorderButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reorderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(helpButton)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reorderButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewOrderDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderFilterPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpButton)
                    .addComponent(orderFilterPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewOrderDetailsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reorderButton1)
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }// </editor-fold>                        
               
}