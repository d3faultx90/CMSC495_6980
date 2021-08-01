/*
 * File: ReportPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the report tab of the main GUI
 */
package SIMS;

import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;

public class ReportPanel extends javax.swing.JPanel {

    public ReportPanel() {
        initComponents();
        TableColumnModel columnModel = yearAndProfitTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(20);
    }

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {
        GeneralGuiFunctions.displayHelpPane("All years with sale data will be displayed here. "
        		+ "\nSelect a year and press the button to view the monthly breakdown of sales."
        		+ "\nPress the month button in the new window to view even further details (FEATURE MAY BE CUT)");
    }

    private void monthlyBreakdownButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                       
    	new MonthViewWindow().setVisible(true);
    }                                                      

    // Variables declaration - do not modify                     
    private javax.swing.JButton helpButton;
    private javax.swing.JScrollPane itemScrollPane;
    private javax.swing.JButton monthlyBreakdownButton;
    private javax.swing.JPanel reportsTab;
    public javax.swing.JTable yearAndProfitTable;
    private javax.swing.JLabel yearLookLabel;
    // End of variables declaration  
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        reportsTab = new javax.swing.JPanel();
        yearLookLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        itemScrollPane = new javax.swing.JScrollPane();
        yearAndProfitTable = new javax.swing.JTable();
        monthlyBreakdownButton = new javax.swing.JButton();

        yearLookLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        yearLookLabel.setText("Year Look");

        helpButton.setBackground(new java.awt.Color(255, 255, 153));
        helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        helpButton.setForeground(new java.awt.Color(0, 0, 0));
        helpButton.setText("?");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        yearAndProfitTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"<year>", "$100,000,000,000,000,000.00"}
            },
            new String [] {
                "Year", "Profits"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        itemScrollPane.setViewportView(yearAndProfitTable);

        monthlyBreakdownButton.setText("View Monthly Breakdown");
        monthlyBreakdownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthlyBreakdownButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportsTabLayout = new javax.swing.GroupLayout(reportsTab);
        reportsTab.setLayout(reportsTabLayout);
        reportsTabLayout.setHorizontalGroup(
            reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsTabLayout.createSequentialGroup()
                .addComponent(helpButton)
                .addGap(90, 90, 90)
                .addComponent(yearLookLabel)
                .addContainerGap(125, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportsTabLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(itemScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addComponent(monthlyBreakdownButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );
        reportsTabLayout.setVerticalGroup(
            reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsTabLayout.createSequentialGroup()
                .addGroup(reportsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpButton)
                    .addGroup(reportsTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(yearLookLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(monthlyBreakdownButton)
                .addGap(89, 89, 89))
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
    }// </editor-fold>    

}
