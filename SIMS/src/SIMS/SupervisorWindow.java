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

	/*
	 * These are used to hold results of the queries so only one connection is
	 * needed. Once the connection is made, these are filled with the various
	 * queries (item, sales, and orders)
	 */
	static List<String> itemNames = new ArrayList<String>();
	static List<List> resultsFromItemQuery = new ArrayList<List>();
	static List<List> resultsFromOrderQuery = new ArrayList<List>();
	static List<List> resultsFromWasteQuery = new ArrayList<List>();
	static List<List> resultsFromSalesQuery = new ArrayList<List>();
	static Map<String, Integer> itemIds = new HashMap<String, Integer>();
	static Connector connector;
	static String username;
	static int userID; // THIS NEEDS TO GET FILLED OUT FOR USE IN SALES/ORDER/WASTE panel.
	// resultsFromOrderQuery
	// resultsFromSalesQuery
	// resultsFromUsersQuery

	public SupervisorWindow(Connector connector, String user) {
		this.connector = connector;
		username = user;
		initialize();
		initComponents();
		// System.out.println(connector.retrieveSalesOnDate("2020-06-19"));
		// System.out.println(resultsFromSalesQuery);
		// System.out.println(connector.retrieveSalesOnDate(Date.getTodaysDateSql()));
	}

	private void initialize() {
		resultsFromItemQuery = connector.getResultsofQuery("inventory");
		resultsFromOrderQuery = connector.getResultsofQuery("orders");
		resultsFromWasteQuery = connector.getResultsofQuery("waste");
		resultsFromSalesQuery = connector.getResultsofQuery("sales");
		populateItemIdMap();
	}

	private void populateItemIdMap() {
		try {
			for (List l : resultsFromItemQuery) {
				itemIds.put((String) l.get(1), Integer.parseInt((String) l.get(0)));
			}
		} catch (Exception e) {
			GeneralGuiFunctions.displayErrorPane(e + "\nSomething went wrong in populateItemIDMap (SupervisorWindow)");
		}
	}
	
	static Connector getConnector() {
		return connector;
	}
	
	static Map getItemIdMap() {
		return itemIds;
	}

	static List<List> getItemTable() {
		return resultsFromItemQuery;
	}

	static List<List> getOrderTable() {
		return resultsFromOrderQuery;
	}

	static List<List> getWasteTable() {
		return resultsFromWasteQuery;
	}

	static List<List> getSalesTable() {
		return resultsFromSalesQuery;
	}

	static String getUsername() {
		return username;
	}

	// Variables declaration - do not modify
	private SIMS.AddNewItemPanel addNewItemPanel1;
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

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		homePanel = new SIMS.HomePanel();
		inventoryTab = new javax.swing.JPanel();
		inventorySubTabs = new javax.swing.JTabbedPane();
		viewInventoryPanel1 = new SIMS.ViewInventoryPanel();
		wastePanel1 = new SIMS.WastePanel();
		orderPanel1 = new SIMS.OrderPanel();
		reorderPanel = new SIMS.ReorderPanel();
		addNewItemPanel1 = new SIMS.AddNewItemPanel();
		salesPanel = new SIMS.SalesPanel();
		reportPanel = new SIMS.ReportPanel();
		importAndExportPanel = new SIMS.ImportAndExportPanel();
		requestsPanel = new SIMS.RequestsPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Sales Inventory Management System (SIMS) - Supervisor");
		setResizable(false);

		jTabbedPane1.setPreferredSize(new java.awt.Dimension(419, 418));
		jTabbedPane1.addTab("Home", homePanel);

		/* Some piece of code */
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				homePanel.time.stopThread();
			}
		});

		inventorySubTabs.setBackground(new java.awt.Color(102, 102, 102));
		inventorySubTabs.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		inventorySubTabs.addTab("View Inventory", viewInventoryPanel1);
		inventorySubTabs.addTab("Waste Item(s)", wastePanel1);
		inventorySubTabs.addTab("Order", orderPanel1);
		inventorySubTabs.addTab("Reorder", reorderPanel);
		inventorySubTabs.addTab("Add New Item", addNewItemPanel1);

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
								javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Inventory", inventoryTab);
		jTabbedPane1.addTab("Sales", salesPanel);
		jTabbedPane1.addTab("Reports", reportPanel);
		jTabbedPane1.addTab("Import/Export", importAndExportPanel);
		jTabbedPane1.addTab("Requests", requestsPanel);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(jTabbedPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addComponent(jTabbedPane1,
						javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE)));

		jTabbedPane1.getAccessibleContext().setAccessibleName("Main");

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>

}
