/*
 * File: RequestsPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Holds the request panel that is nested in User/Supervisor window
 */
package SIMS;

public class RequestsPanel extends javax.swing.JPanel {

    /**
     * Creates new form RequestsPanel
     */
    public RequestsPanel() {
        initComponents();
    }

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          


    // Variables declaration - do not modify                     
    private javax.swing.JButton helpButton;
    private SIMS.OrderOrWasteRequestPanel orderRequestPanel;
    private javax.swing.JPanel requestPanel;
    private SIMS.OrderOrWasteRequestPanel wasteRequestPanel;
    // End of variables declaration   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        requestPanel = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        orderRequestPanel = new SIMS.OrderOrWasteRequestPanel("Order Requests");
        wasteRequestPanel = new SIMS.OrderOrWasteRequestPanel("Waste Requests");

        helpButton.setBackground(new java.awt.Color(255, 255, 153));
        helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        helpButton.setText("?");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout requestPanelLayout = new javax.swing.GroupLayout(requestPanel);
        requestPanel.setLayout(requestPanelLayout);
        requestPanelLayout.setHorizontalGroup(
            requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPanelLayout.createSequentialGroup()
                .addGroup(requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wasteRequestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(requestPanelLayout.createSequentialGroup()
                        .addComponent(helpButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(orderRequestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        requestPanelLayout.setVerticalGroup(
            requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPanelLayout.createSequentialGroup()
                .addGroup(requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpButton)
                    .addGroup(requestPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(orderRequestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wasteRequestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(requestPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(requestPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>                        
                
}
