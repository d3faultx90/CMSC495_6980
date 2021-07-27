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

    public ViewInventoryPanel() {
        initComponents();
    }  

    private void helpButtoonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton helpButtoon;
    private SIMS.ItemFilterPanel itemFilterPanel;
    private javax.swing.JPanel panel;
    private javax.swing.JButton viewDetailButton;
    // End of variables declaration//GEN-END:variables
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel = new javax.swing.JPanel();
        viewDetailButton = new javax.swing.JButton();
        helpButtoon = new javax.swing.JButton();
        itemFilterPanel = new SIMS.ItemFilterPanel();

        panel.setPreferredSize(new java.awt.Dimension(436, 234));

        viewDetailButton.setText("View Details");
        viewDetailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailButtonActionPerformed(evt);
            }
        });

        helpButtoon.setBackground(new java.awt.Color(255, 255, 153));
        helpButtoon.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        helpButtoon.setText("?");
        helpButtoon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtoonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(helpButtoon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(viewDetailButton, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(itemFilterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(helpButtoon)
                        .addGap(0, 248, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(itemFilterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewDetailButton)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>                        

    private void viewDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        JTable table = itemFilterPanel.itemTable;
        String selectedCellValue = (String) table.getValueAt(table.getSelectedRow(), 0);
        
        for (List l : SupervisorWindow.getItemTable()) {
        	if (l.get(1) == selectedCellValue) {
        		// https://www.codermag.net/2016/07/how-to-convert-list-to-list.html
        		List<String> stringList = (List<String>)(Object)l;
        		new ItemDetailsWindow(stringList.get(1), stringList.get(2), stringList.get(3), stringList.get(4), stringList.get(5), stringList.get(6)).setVisible(true); 
        		break;
        	}
        }

    }                                                

}
