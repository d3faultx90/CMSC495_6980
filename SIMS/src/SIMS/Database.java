/*
 * File: Database.java
 * Author: Ben Sutter
 * Date: July 31st, 2021
 * Purpose: Data object that imitates/replicates the SQL database so limited connections are required.
 */

package SIMS;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {

	/*
	 * These are used to hold results of the queries so only one connection is needed. 
	 * Once the connection is made, these are filled with the various queries (item, sales, waste and orders)
	 */
	static List<List> resultsFromItemQuery = new ArrayList<List>();
	static List<List> resultsFromOrderQuery = new ArrayList<List>();
	static List<List> resultsFromWasteQuery = new ArrayList<List>();
	static List<List> resultsFromSalesQuery = new ArrayList<List>();
	static List<List> resultsFromUserQuery = new ArrayList<List>();

	// Maps are used to translate in between columns based on their key
	static Map<Object, Object> itemIds = new HashMap<Object, Object>();
	static Map<Object, Object> itemRetailPrices = new HashMap<Object, Object>();
	static Map<Object, Object> itemWholesalePrices = new HashMap<Object, Object>();
	static Map<Object, Object> itemNames = new HashMap<Object, Object>();
	static Map<Object, String> userIds = new HashMap<Object, String>();

	// Connector is tied to the window and dictates permissions and what actions can be preformed
	static Connector connector;
	// Locks user out of certain methods if their role does not match.
	static int role;
	
	public Database(Connector connector) {
		this.connector = connector;
		role = connector.role;
		initialize();
	}

	static Connector getConnector() {
		return connector;
	}

	static Map getItemIdMap() {
		return itemIds;
	}

	static Map getItemNamesMap() {
		return itemNames;
	}

	static List<List> getItemTable() {
		return resultsFromItemQuery;
	}

	static List<List> getOrderTable() {
		return resultsFromOrderQuery;
	}

	static Map getRetailItemPricesMap() {
		return itemRetailPrices;
	}

	static int getRole() {
		return role;
	}

	static List<List> getSalesTable() {
		return resultsFromSalesQuery;
	}

	static Map getUserIdMap() {
		return userIds;
	}

	static List<List> getUsersTable() {
		return resultsFromUserQuery;
	}

	static List<List> getWasteTable() {
		return resultsFromWasteQuery;
	}

	static Map getWholesaleItemPricesMap() {
		return itemWholesalePrices;
	}

	// Checks to see the role of the user, then performs the appropriate refresh method
	static void refreshAllComboBoxes() {
		if (role == 1) {
			SupervisorWindow.refreshReportComboBox();
		} else {
			UserWindow.refreshReportComboBox();
		}
	}

	// Checks to see the role of the user, then performs the appropriate refresh method
	static void refreshAllTables() {
		if (role == 1) {
			SupervisorWindow.refreshAllTables();
		} else {
			UserWindow.refreshAllTables();
		}
	}

	private void initialize() {
		connector.getAllResults();
		populateItemMaps();
		populateUserIdMap();

	}

	private void populateItemMaps() {
		/**
		 * Creates various dictionaries to make translating certain columns easier
		 */
		try {
			for (List l : resultsFromItemQuery) {
				// Index 1 - name, Index 0 = ID
				itemIds.put(l.get(1), l.get(0));
				// Index 0 = ID, Index 1 - name
				itemNames.put(l.get(0), l.get(1));
				// Index 1 - name, Index 4 = wholesale price
				itemWholesalePrices.put(l.get(1), l.get(4));
				// Index 1 - name, Index 5 = retail price
				itemRetailPrices.put(l.get(1), l.get(5));

			}
		} catch (Exception e) {
			GeneralGuiFunctions.displayErrorPane(e + "\nSomething went wrong in populateItemIDMap (Database.java)");
		}
	}

	private void populateUserIdMap() {
		/**
		 * Creates a dictionary where the userID is the key, and their username is the value
		 */
		try {
			for (List l : resultsFromUserQuery) {
				userIds.put(l.get(0), (String) l.get(1));
			}
		} catch (Exception e) {
			GeneralGuiFunctions.displayErrorPane(e + "\nSomething went wrong in populateUserIdMap (Database.java)");
		}
	}

}
