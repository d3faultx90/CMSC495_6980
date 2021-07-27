/*
 * File: ReportPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the report tab of the main GUI
 */
package SIMS;

public class ReportPanel extends javax.swing.JPanel {

    public ReportPanel() {
        initComponents();
    }

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        GeneralGuiFunctions.displayHelpPane("Here is how this panel works!");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel fakeYearPanel;
    private javax.swing.JButton helpButton;
    private javax.swing.JPanel reportsTab;
    private javax.swing.JLabel saleAmountLabel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JLabel yearLookLabel;
    // End of variables declaration//GEN-END:variables
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reportsTab = new javax.swing.JPanel();
        yearLookLabel = new javax.swing.JLabel();
        fakeYearPanel = new javax.swing.JPanel();
        yearLabel = new javax.swing.JLabel();
        saleAmountLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();

        yearLookLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        yearLookLabel.setText("Year Look");

        fakeYearPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        yearLabel.setText("2021");

        saleAmountLabel.setText("$100,000");

        javax.swing.GroupLayout fakeYearPanelLayout = new javax.swing.GroupLayout(fakeYearPanel);
        fakeYearPanel.setLayout(fakeYearPanelLayout);
        fakeYearPanelLayout.setHorizontalGroup(
            fakeYearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fakeYearPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(fakeYearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fakeYearPanelLayout.createSequentialGroup()
                        .addComponent(yearLabel)
                        .addGap(9, 9, 9))
                    .addComponent(saleAmountLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        fakeYearPanelLayout.setVerticalGroup(
            fakeYearPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fakeYearPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(yearLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saleAmountLabel)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        helpButton.setBackground(new java.awt.Color(255, 255, 153));
        helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        helpButton.setForeground(new java.awt.Color(0, 0, 0));
        helpButton.setText("?");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportsTabLayout = new javax.swing.GroupLayout(reportsTab);
        reportsTab.setLayout(reportsTabLayout);
        reportsTabLayout.setHorizontalGroup(
            reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsTabLayout.createSequentialGroup()
                .addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportsTabLayout.createSequentialGroup()
                        .addComponent(helpButton)
                        .addGap(90, 90, 90)
                        .addComponent(yearLookLabel))
                    .addGroup(reportsTabLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(fakeYearPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        reportsTabLayout.setVerticalGroup(
            reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsTabLayout.createSequentialGroup()
                .addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpButton)
                    .addGroup(reportsTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(yearLookLabel)))
                .addGap(18, 18, 18)
                .addComponent(fakeYearPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(253, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(reportsTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(reportsTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

}
