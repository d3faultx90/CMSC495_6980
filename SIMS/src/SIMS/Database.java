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
	 * These are used to hold results of the queries so only one connection is
	 * needed. Once the connection is made, these are filled with the various
	 * queries (item, sales, waste and orders)
	 */
	static List<String> itemNames = new ArrayList<String>();
	static List<List> resultsFromItemQuery = new ArrayList<List>();
	static List<List> resultsFromOrderQuery = new ArrayList<List>();
	static List<List> resultsFromWasteQuery = new ArrayList<List>();
	static List<List> resultsFromSalesQuery = new ArrayList<List>();
	static Map<String, Integer> itemIds = new HashMap<String, Integer>();
	static Connector connector;
	
	public Database(Connector connector) {
		this.connector = connector;
		initialize();
		//System.out.println(connector.retrieveSalesOnDate("2021"));
		//System.out.println(connector.retrieveSalesByDateRange("2020-01-01", "2021-12-31"));
//		System.out.println(connector.retrieveSalesOnDate("2020-06-19"));
//		System.out.println(connector.retrieveSalesSinceDate("2021-01-01"));
		// System.out.println(resultsFromOrderQuery);
//		System.out.println(resultsFromSalesQuery);
//		System.out.println(connector.retrieveSalesOnDate(DateHandler.getTodaysDateSql()));
	}
	
	private void initialize() {
		/**
		 * Initializes all of the tables.
		 */
		resultsFromItemQuery = connector.getResultsofQuery("inventory");
		resultsFromOrderQuery = connector.getResultsofQuery("orders");
		resultsFromWasteQuery = connector.getResultsofQuery("waste");
		resultsFromSalesQuery = connector.getResultsofQuery("sales");
		populateItemIdMap();
	}

	private void populateItemIdMap() {
		/**
		 * Creates a dictionary where the item name is the key, 
		 * and its ID is the value 
		 */
		try {
			for (List l : resultsFromItemQuery) {
				itemIds.put((String) l.get(1), Integer.parseInt((String) l.get(0)));
			}
		} catch (Exception e) {
			GeneralGuiFunctions.displayErrorPane(e + "\nSomething went wrong in populateItemIDMap (SupervisorWindow)");
		}
	}
	
	// Accessor methods
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
	// End of accessor methods
	
}
