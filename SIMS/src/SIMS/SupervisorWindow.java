/*
 * File: SupervisorWindow.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Master GUI that holds many of the elements
 */

package SIMS;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class SupervisorWindow extends javax.swing.JFrame {

	static String username;
	static int userID; // THIS NEEDS TO GET FILLED OUT FOR USE IN SALES/ORDER/WASTE panel.
	// resultsFromOrderQuery
	// resultsFromSalesQuery
	// resultsFromUsersQuery

	public SupervisorWindow(Connector connector, String username) {
		username = username;
		Database database = new Database(connector);
		initComponents();
	}

	static String getUsername() {
		return username;
	}

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

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
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
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Main");

        pack();
        setLocationRelativeTo(null);
	}// </editor-fold>

}
