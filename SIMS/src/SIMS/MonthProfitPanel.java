/*
 * File: MonthProfitPanel.java
 * Author: Ben Sutter
 * Date: July 31st, 2021
 * Purpose: Used in MonthViewPanel to represent monthly break down of sales
 */

package SIMS;

public class MonthProfitPanel extends javax.swing.JPanel {

    String month;
    // Need to be int?
    String profits;
    
    public MonthProfitPanel(String month, String profits) {
        this.month = month;
        this.profits = profits;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        monthProfitButton = new javax.swing.JButton();
        monthLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setMaximumSize(new java.awt.Dimension(147, 80));
        setMinimumSize(new java.awt.Dimension(147, 80));

        monthProfitButton.setText(profits);
        monthProfitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthProfitButtonActionPerformed(evt);
            }
        });

        monthLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        monthLabel.setText(month);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(monthProfitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(monthLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthProfitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>                        

    private void monthProfitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        System.out.println("You hit the button for " + month);
    }                                                 


    // Variables declaration - do not modify                     
    private javax.swing.JLabel monthLabel;
    private javax.swing.JButton monthProfitButton;
    // End of variables declaration                   
}
