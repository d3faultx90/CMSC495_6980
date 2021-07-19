/*
 * File: MainWindow.java
 * Author: Ben Sutter
 * Date: July 2nd, 2021
 * Purpose: Hold the GUI
 */
package SIMS;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SupervisorWindow extends javax.swing.JFrame {

	private List<String> itemNames = new ArrayList<String>(); 
    /**
     * Creates new form Main
     */
    public SupervisorWindow() {
    	initialize();
        initComponents();
    }
    
    private void initialize() {
        Connector c = new Connector();
        c.connect();
        itemNames = c.getItemNames();
    }   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        homePanel = new SIMS.HomePanel();
        inventoryTab = new javax.swing.JPanel();
        inventorySubTabs = new javax.swing.JTabbedPane();
        viewInventoryPanel1 = new SIMS.ViewInventoryPanel(itemNames);
        addNewItemPanel = new SIMS.AddNewItemPanel();
        wastePanel1 = new SIMS.WastePanel(itemNames);
        orderPanel1 = new SIMS.OrderPanel(itemNames);
        reorderPanel = new SIMS.ReorderPanel(itemNames);
        salesPanel = new SIMS.SalesPanel(itemNames);
        reportPanel = new SIMS.ReportPanel();
        importAndExportPanel = new SIMS.ImportAndExportPanel();
        requestsPanel = new SIMS.RequestsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sales Inventory Management System (SIMS) - Supervisor");

        jTabbedPane1.setPreferredSize(new java.awt.Dimension(419, 418));
        jTabbedPane1.addTab("Home", homePanel);

        inventorySubTabs.setBackground(new java.awt.Color(102, 102, 102));
        inventorySubTabs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        inventorySubTabs.addTab("View Inventory", viewInventoryPanel1);
        inventorySubTabs.addTab("Add New Item", addNewItemPanel);
        inventorySubTabs.addTab("Waste Item(s)", wastePanel1);
        inventorySubTabs.addTab("Order", orderPanel1);
        inventorySubTabs.addTab("Reorder", reorderPanel);

        javax.swing.GroupLayout inventoryTabLayout = new javax.swing.GroupLayout(inventoryTab);
        inventoryTab.setLayout(inventoryTabLayout);
        inventoryTabLayout.setHorizontalGroup(
            inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryTabLayout.createSequentialGroup()
                .addComponent(inventorySubTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        inventoryTabLayout.setVerticalGroup(
            inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryTabLayout.createSequentialGroup()
                .addComponent(inventorySubTabs, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inventory", inventoryTab);
        jTabbedPane1.addTab("Sales", salesPanel);
        jTabbedPane1.addTab("Reports", reportPanel);
        jTabbedPane1.addTab("Import/Export", importAndExportPanel);
        jTabbedPane1.addTab("Requests", requestsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Main");

        pack();
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(SupervisorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SupervisorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SupervisorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SupervisorWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupervisorWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private SIMS.AddNewItemPanel addNewItemPanel;
    private SIMS.HomePanel homePanel;
    private SIMS.ImportAndExportPanel importAndExportPanel;
    private javax.swing.JTabbedPane inventorySubTabs;
    private javax.swing.JPanel inventoryTab;
    private javax.swing.JTabbedPane jTabbedPane1;
    private SIMS.OrderPanel orderPanel1;
    private SIMS.ReorderPanel reorderPanel;
    private SIMS.ReportPanel reportPanel;
    private SIMS.RequestsPanel requestsPanel;
    private SIMS.SalesPanel salesPanel;
    private SIMS.ViewInventoryPanel viewInventoryPanel1;
    private SIMS.WastePanel wastePanel1;
    // End of variables declaration                   
}