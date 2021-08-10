/*
 * File: SupervisorWindow.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Master GUI that holds many of the panels.
 * Utilizes the singleton design pattern to allow for refreshing elements of the UI.
 */

package SIMS;

import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.HashMap;

public class SupervisorWindow extends javax.swing.JFrame {

	static String username;
	static int userID; 
	private static SupervisorWindow singleton;
	
	// Variables declaration - do not modify
	private SIMS.AddNewItemPanel addNewItemPanel;
	private SIMS.HomePanel homePanel;
	private SIMS.ExportPanel importAndExportPanel;
	private javax.swing.JTabbedPane inventorySubTabs;
	private javax.swing.JPanel inventoryTab;
	private javax.swing.JTabbedPane jTabbedPane1;
	private SIMS.OrderPanel orderPanel;
	private SIMS.ReorderPanel reorderPanel;
	private SIMS.ReportPanel reportPanel;
	private SIMS.RequestsPanel requestsPanel;
	private SIMS.SalesPanel salesPanel;
	private SIMS.ViewInventoryPanel viewInventoryPanel;
	private SIMS.WastePanel wastePanel;
	// End of variables declaration

	private SupervisorWindow(Connector connector, String username) {
		this.username = username;
		Database database = new Database(connector);
		initComponents();
	}
	
	// Setter method for the singleton (only called via login window)
    protected static SupervisorWindow createWindow(Connector connector, String username) {
    	singleton = new SupervisorWindow(connector, username);
    	return singleton;
    }
    
	protected static String getUsername() {
		return username;
	}
	
	// Getter method for the singleton instance
    protected static SupervisorWindow getWindow() {
        return singleton;
    }
    
	// Refresh all elements within the GUI (called when HomePanel's refresh button is hit).
    protected static void refreshAllElements() {
    	refreshAllItemTables();
    	refreshAllRequestTables();
    	refreshReorderTable();
    	refreshReportComboBox();
    }
    
	// Refreshes each itemTable within the various nested panels
    protected static void refreshAllItemTables() {
    	ItemFilterPanel.updateCurrentInventory();
    	singleton.salesPanel.salesPanel.itemFilterPanel.refreshTable();
    	singleton.orderPanel.orderPanel.itemFilterPanel.refreshTable();
    	singleton.wastePanel.itemFilterPanel.refreshTable();
    	singleton.viewInventoryPanel.itemFilterPanel.refreshTable();
    }
    
	// Refreshes both request panels so they reflect the state of the database
    protected static void refreshAllRequestTables() {
    	singleton.requestsPanel.orderRequestPanel.refreshTable();
    	singleton.requestsPanel.wasteRequestPanel.refreshTable();
    }
    
	// Refresh only the reorder table (called when a request is approved/denied)
    protected static void refreshReorderTable() {
    	singleton.reorderPanel.orderFilterPanel.refreshTable();
    }
    
	// Refreshes the report combo box in case a new item was added.
    protected static void refreshReportComboBox() {
    	singleton.reportPanel.refreshComboBox();
    }
    
	// Given a 2D array with the order info, populate the order table and tab to it.
    protected static void reorder(Object [][] previousOrder) {
    	GeneralGuiFunctions.clearTable(singleton.orderPanel.orderPanel.orderTable);
    	for (Object[] o :  previousOrder) {
			DefaultTableModel model = (DefaultTableModel) singleton.orderPanel.orderPanel.orderTable.getModel();
			model.addRow(o);
    	}
    	singleton.inventorySubTabs.setSelectedIndex(2);
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
		addNewItemPanel = new SIMS.AddNewItemPanel();
		salesPanel = new SIMS.SalesPanel();
		reportPanel = new SIMS.ReportPanel();
		importAndExportPanel = new SIMS.ExportPanel();
		requestsPanel = new SIMS.RequestsPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("SIMS - Supervisor Window");
		setResizable(false);

		jTabbedPane1.setPreferredSize(new java.awt.Dimension(419, 418));
		jTabbedPane1.addTab("Home", homePanel);

		inventorySubTabs.setBackground(new java.awt.Color(102, 102, 102));
		inventorySubTabs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		boolean isMac = (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0);
		if (isMac) {
			inventorySubTabs.addTab("View Inventory", viewInventoryPanel);
			inventorySubTabs.addTab("Waste Item(s)", wastePanel);
			inventorySubTabs.addTab("Order", orderPanel);
			inventorySubTabs.addTab("Reorder", reorderPanel);
			inventorySubTabs.addTab("Add New Item", addNewItemPanel);
		} else {
			inventorySubTabs.addTab("View Inventory", viewInventoryPanel);
			inventorySubTabs.addTab("Waste Item(s)", wastePanel);
			inventorySubTabs.addTab(" Order ", orderPanel);
			inventorySubTabs.addTab("Reorder", reorderPanel);
			inventorySubTabs.addTab(" Add New Item", addNewItemPanel);
		}

		javax.swing.GroupLayout inventoryTabLayout = new javax.swing.GroupLayout(inventoryTab);
		inventoryTab.setLayout(inventoryTabLayout);
		inventoryTabLayout
				.setHorizontalGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(inventoryTabLayout.createSequentialGroup().addComponent(inventorySubTabs,
								javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));
		inventoryTabLayout
				.setVerticalGroup(inventoryTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(inventoryTabLayout.createSequentialGroup().addComponent(inventorySubTabs,
								javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));
		if (isMac) {
	        jTabbedPane1.addTab("Inventory", inventoryTab);
	        jTabbedPane1.addTab("Sales", salesPanel);
	        jTabbedPane1.addTab("Reports", reportPanel);
	        jTabbedPane1.addTab("Export", importAndExportPanel);
	        jTabbedPane1.addTab("Requests", requestsPanel);
		} else {
			jTabbedPane1.addTab("  Inventory  ", inventoryTab);
			jTabbedPane1.addTab("  Sales  ", salesPanel);
			jTabbedPane1.addTab("  Reports  ", reportPanel);
			jTabbedPane1.addTab("  Export  ", importAndExportPanel);
			jTabbedPane1.addTab("  Requests ", requestsPanel);
		}

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        int preferredSize = isMac ? 440 : 419;
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, preferredSize, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Main");

        pack();
        setLocationRelativeTo(null);
	}

}
