/*
 * File: SupervisorWindow.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Master GUI that holds many of the elements
 */
package SIMS;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public class UserWindow extends javax.swing.JFrame {

	static String username;
	private static UserWindow singleton;

	public UserWindow(Connector connector, String user) {
		username = user;
		Database database = new Database(connector);
		initComponents();
		changeTextForUser();
		
	}

	public void changeTextForUser() {
		salesPanel.dateChooser.setVisible(false);
		wastePanel.wasteButton.setText("Submit Waste Request");
		//salesPanel.saveSaleButton.setText("Submit Sales Request");
		orderPanel.orderButton.setText("Submit Order Request");
	}

	// Getter method for the singleton instance
    public static UserWindow getWindow() {
        return singleton;
    }
    
    // Setter method for the singleton (only called via login window)
    public static UserWindow createWindow(Connector connector, String username) {
    	singleton = new UserWindow(connector, username);
    	return singleton;
    }
    
    public static void refreshAllItemTables() {
    	List<List> inventory = Database.getItemTable();
    	singleton.salesPanel.salesPanel.itemFilterPanel.refreshTable(inventory);
    	singleton.orderPanel.orderPanel.itemFilterPanel.refreshTable(inventory);
    	singleton.wastePanel.itemFilterPanel.refreshTable(inventory);
    	singleton.viewInventoryPanel.itemFilterPanel.refreshTable(inventory);
    }
    
    public static void reorder(Object [][] previousOrder) {
    	GeneralGuiFunctions.clearTable(singleton.orderPanel.orderPanel.orderTable);
    	for (Object[] o :  previousOrder) {
			DefaultTableModel model = (DefaultTableModel) singleton.orderPanel.orderPanel.orderTable.getModel();
			model.addRow(o);
    	}
    	singleton.inventorySubTabs.setSelectedIndex(2);
    }
    
    public static void refreshReportComboBox() {
    	singleton.reportPanel.refreshComboBox();
    }
	
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		homePanel = new SIMS.HomePanel(username);
		inventoryTab = new javax.swing.JPanel();
		inventorySubTabs = new javax.swing.JTabbedPane();
		viewInventoryPanel = new SIMS.ViewInventoryPanel();
		wastePanel = new SIMS.WastePanel();
		orderPanel = new SIMS.OrderPanel();
		reorderPanel = new SIMS.ReorderPanel();
		salesPanel = new SIMS.SalesPanel();
		reportPanel = new SIMS.ReportPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("SIMS - User Window");
		setResizable(false);

		jTabbedPane1.setPreferredSize(new java.awt.Dimension(419, 418));
		jTabbedPane1.addTab("Home            ", homePanel);

		inventorySubTabs.setBackground(new java.awt.Color(102, 102, 102));
		inventorySubTabs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		inventorySubTabs.addTab("View Inventory", viewInventoryPanel);
		inventorySubTabs.addTab("         Waste Item(s)      ", wastePanel);
		inventorySubTabs.addTab("   Order   ", orderPanel);
		inventorySubTabs.addTab("       Reorder     ", reorderPanel);

		javax.swing.GroupLayout inventoryTabLayout = new javax.swing.GroupLayout(inventoryTab);
		inventoryTab.setLayout(inventoryTabLayout);
		inventoryTabLayout
				.setHorizontalGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(inventoryTabLayout.createSequentialGroup().addComponent(inventorySubTabs,
								javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));
		inventoryTabLayout
				.setVerticalGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(inventorySubTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE));

		jTabbedPane1.addTab("     Inventory            ", inventoryTab);
		jTabbedPane1.addTab("    Sales              ", salesPanel);
		jTabbedPane1.addTab("    Reports       ", reportPanel);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(jTabbedPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, Short.MAX_VALUE));

		jTabbedPane1.getAccessibleContext().setAccessibleName("Main");

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private SIMS.HomePanel homePanel;
	private javax.swing.JTabbedPane inventorySubTabs;
	private javax.swing.JPanel inventoryTab;
	private javax.swing.JTabbedPane jTabbedPane1;
	private SIMS.OrderPanel orderPanel;
	private SIMS.ReorderPanel reorderPanel;
	private SIMS.ReportPanel reportPanel;
	private SIMS.SalesPanel salesPanel;
	private SIMS.ViewInventoryPanel viewInventoryPanel;
	private SIMS.WastePanel wastePanel;
	// End of variables declaration//GEN-END:variables
}
