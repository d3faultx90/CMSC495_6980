package SIMS;

import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.UUID;

public class Connector {
	
	// constructor variables
	protected String user;
	private char[] password;
	private String trustStoreFilePath;
	private String trustStorePassword;
	private String mySqlPath;	
	protected int role;
	
	// constructor
	protected Connector(String user, char[] password) {
		this.user = user;
		this.password = password;
		this.trustStoreFilePath = "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore";
		this.trustStorePassword = "password";
		this.mySqlPath = "jdbc:mysql://sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com:3306/";
	}
    
    // This key store has only the prod root ca.
    /* This should be started with
     * 
     * -Djavax.net.ssl.keyStore="C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore" -Djavax.net.ssl.keyStorePassword="password"
     */ 
    
	private Connection buildJDBCConnecter() {
		/**
		 * Method creates connection to AWS cloud using 
		 * 	Connector object properites. 
		 * 
		 * @ return Connection object for sql queries
		 */
		
		System.setProperty("javax.net.ssl.trustStore", trustStoreFilePath);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    	String pass = String.valueOf(this.password);
    	
    	Connection con = null;
    	
        try {
  
        	con = DriverManager.getConnection(mySqlPath, this.user, pass);
        	
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }
        
        return con;
        
	} // end of buildJDBCConnecter()
	
	
    protected List<List> getResultsofQuery(String tableName) {
        /**
         * Method returns List<List> containing MySQL SELECT query.
         * 
         * @param tableName This is the name of the table you wish to query.
         * @return results 
         */

        List<List> results = new ArrayList<List>();
        String query = null;

        switch(tableName.toLowerCase())
        {
            case "inventory":
            	query = "SELECT * FROM SIMS_app_data.inventory";
                break;
            case "users":
            	query ="SELECT * FROM SIMS_app_data.users";
                break;
            case "orders":
            	query = "SELECT * FROM SIMS_app_data.orders";
                break;
            case "sales":
            	query = "SELECT * FROM SIMS_app_data.sales";
                break;
            case "waste":
            	query = "SELECT * FROM SIMS_app_data.waste";
                break;
            default:
                System.out.println("Table does not exist");
                //return something else
        }

        // 
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
        	Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
        	
        	ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();  
            
            while (rs.next()) {
            
            	ArrayList<String> row = new ArrayList<String>();
            	
            	for (int i = 1; i <= columnsNumber; i++) {
            		row.add(rs.getString(i));
                }
            	
            	results.add(row);
            }
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }

        return results;
        
    } // end of getAllInventoryItems()
    
    
    protected Boolean verifyUser() {
        /**
         * Method attempts to login using Username provided and verify if 
         * userName exist in SIMS_app_data.users.
         * 
         * @param userName This is a String object used to log into the MySQL instance.
         * @param password This is a char[] object used to log into the MySQL instance.
         * @return result This returns a boolean value based on whether the user can log in. 
         */
    	
    	Boolean result = true;
                                        
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
        	
        	PreparedStatement st = con.prepareStatement("SELECT Username FROM SIMS_app_data.users WHERE Username = ?");
        	st.setString(1, this.user);
            ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();  
            
            if (columnsNumber != 1) {
            	result = false;
            } else {
            	result = true;
            }
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        	result = false;
        }
        
    	return result;
    	
    } // end of verifyUser()
    
    
    protected void getUserRole() {
	    /**
	     * Method returns user role based on entry in SIMS_app_data.users.
	     * 
	     * @param userName This is a String object used to log into the MySQL instance.
	     * @param password This is a char[] object used to log into the MySQL instance.
	     * sets role as integer. 0 = Admin, 1 = Supervisor, 2 = Employee/User
	     */
    	
    	// set to 3 for default invalid user.
    	int role = 3;
    	
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
        	
        	PreparedStatement st = con.prepareStatement("SELECT role FROM SIMS_app_data.users WHERE Username = ?");
        	st.setString(1, this.user);
            ResultSet rs = st.executeQuery();
        	
            while (rs.next()) {
            	role = (Integer) rs.getInt(1);
            }
                        
            // close con, rs, and st
            rs.close();
            st.close();
        	con.close();
        	
        	this.role = role;
        	
        } catch (SQLException e) {
            e.printStackTrace();
            this.role = 3;
        }
                
    } // end of getUserRole()
    
    
    protected Boolean verifyItemExists(String itemName) {
	    /**
	     * Method determines whether item exist in SIMS_app_data.inventory.
	     * 
	     * @param itemName This is a String object to look up item in inventory.
	     * 
	     * @return exists Returns values based on unique name in table.
	     */
		
		// invalid role
		Boolean exists = false;
		
	    try {
	    	
	    	// connect to database via JDBC
	    	Connection con = buildJDBCConnecter();
	    	
	    	PreparedStatement st = con.prepareStatement("SELECT name FROM SIMS_app_data.users WHERE name = ?");
	    	st.setString(1, itemName);
	        ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();  
            
            if (columnsNumber != 1) {
            	exists = false;
            } else {
            	exists = true;
            }
	                    
	        // close con, rs, and st
	        rs.close();
	        st.close();
	    	con.close();
	    	
	    } catch (SQLException e) {
	        e.printStackTrace();
	        exists = false;
	    }
	    
	    return exists;
            
	}  // end of verifyItemExists()

    
    protected List<List> createInventoryItem(String name, String description, String foodCategory, double wholeSalePrice, double retailPrice, int quantity){
	    /**
	     * Method determines whether item exist in SIMS_app_data.inventory.
	     * 
	     * @param name This is a String object to look up item in inventory.
	     * @param description This is a String object to look up item in inventory.
	     * @param foodCategory This is a String object for the type of inventory items. 
	     * @param wholeSalePrice This is a Double object for the price a company paid for a product. 
	     * @param retailPrice This is a Double object for the sale price of an object.
	     * @param quantity This is an Integer object for the amount of new objects.
	     * 
	     * @return List of List which is new result of all inventory items.
	     */
    
    	List<List> results = new ArrayList<List>();
    	

    	// if (verifyItemExists(name)) {
    	// 		// results = updated 
    	// 
        // } else { ... 
    	
        // 
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
	    	
	    	PreparedStatement st = con.prepareStatement("INSERT INTO SIMS_app_data.inventory(Name, Description, FoodCategory , WholeSalePrice, RetailPrice, Quantity) VALUES (?, ?, ?, ?, ?, ?)");
	    	st.setString(1, name);
	    	st.setString(2, description);
	    	st.setString(3, foodCategory);
	    	st.setDouble(4, wholeSalePrice);
	    	st.setDouble(5, retailPrice);
	    	st.setInt(6, quantity);
	        ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData(); 
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }
    	
        results = getResultsofQuery("inventory");
        
    	return results;
    			
    } // end of updatedInventory()
    
    
    protected List<List> createOrder(int employeeID, int itemID, double saleTax, double wholeSalePrice, int quantity, String date, int status) {
	    /**
	     * Method determines whether item exist in SIMS_app_data.orders.
	     * 
	     * @param OrderEventID This is a String object to look up item in inventory.
	     * @param employeeID This is a String object to look up item in inventory.
	     * @param itemID This is a String object for the type of inventory items. 
	     * @param saleTax This is a Double object for the price a company paid for a product. 
	     * @param wholeSalePrice This is a Double object for the sale price of an object.
	     * @param quantity This is an Integer object for the amount of new objects.
	     * @param date This is an Integer object for the amount of new objects.
	     * @param status This is an Integer object for the amount of new objects.
	     * 
	     * @return List of List which is new result of all order items.
	     */
    
    	List<List> results = new ArrayList<List>();
    	String orderEventID = UUID.randomUUID().toString();
    
    	// 
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
	    	
	    	PreparedStatement st = con.prepareStatement("INSERT INTO SIMS_app_data.orders(OrderEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, OrderDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	    	st.setString(1, orderEventID);
	    	st.setInt(2, employeeID);
	    	st.setInt(3, itemID);
	    	st.setDouble(4, saleTax);
	    	st.setDouble(5, wholeSalePrice);
	    	st.setInt(6, quantity);
	    	st.setString(7, date);
	    	st.setInt(8, status);
	        ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData(); 
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }
    	
        results = getResultsofQuery("order");
        
    	return results;
    			
	} // end of createOrder()
    
    
    protected List<List> createWaste(int employeeID, int itemID, double saleTax, double wholeSalePrice, int quantity, String date, int status){
	    /**
	     * Method determines whether item exist in SIMS_app_data.waste.
	     * 
	     * @param WasteEventID This is a String object to look up item in inventory.
	     * @param employeeID This is a String object to look up item in inventory.
	     * @param itemID This is a String object for the type of inventory items. 
	     * @param saleTax This is a Double object for the price a company paid for a product. 
	     * @param wholeSalePrice This is a Double object for the sale price of an object.
	     * @param quantity This is an Integer object for the amount of new objects.
	     * @param date This is an Integer object for the amount of new objects.
	     * @param status This is an Integer object for the amount of new objects.
	     * 
	     * @return List of List which is new result of all order items.
	     */
    
    	List<List> results = new ArrayList<List>();
    	String wasteEventID = UUID.randomUUID().toString();
    
    	// 
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
	    	
	    	PreparedStatement st = con.prepareStatement("INSERT INTO SIMS_app_data.waste(WasteEventID, EmployeeID, ItemID, SalesTax, WholeSaleUnitPrice, Quantity, WasteDate, Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
	    	st.setString(1, wasteEventID);
	    	st.setInt(2, employeeID);
	    	st.setInt(3, itemID);
	    	st.setDouble(4, saleTax);
	    	st.setDouble(5, wholeSalePrice);
	    	st.setInt(6, quantity);
	    	st.setString(7, date);
	    	st.setInt(8, status);
	        ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData(); 
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }
    	
        results = getResultsofQuery("waste");
        
    	return results;
    			
	} // end of createWaste()
    
    
    protected List<List> createSales(int employeeID, int itemID, double saleTax, int quantity){
	    /**
	     * Method determines whether item exist in SIMS_app_data.waste.
	     * 
	     * @param employeeID This is a int object for employee.
	     * @param itemID This is a String object for the type of inventory items. 
	     * @param saleTax This is a Double object for the price a company paid for a product.
	     * @param quantity This is an Integer object for the amount of new objects.
	     * 
	     * @return List of List which is new result of all order items.
	     */
    
    	List<List> results = new ArrayList<List>();
    	String salesEventID = UUID.randomUUID().toString();
    	String salesDate = Date.sqlDateTime();
    
    	// 
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
	    	
	    	PreparedStatement st = con.prepareStatement("INSERT INTO SIMS_app_data.sales(SalesEventID, EmployeeID, ItemID, SalesPrice, Quantity, SalesDate) VALUES (?, ?, ?, ?, ?, ?)");
	    	st.setString(1, salesEventID);
	    	st.setInt(2, employeeID);
	    	st.setInt(3, itemID);
	    	st.setDouble(4, saleTax);
	    	st.setInt(5, quantity);
	    	st.setString(6, salesDate);
	        ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData(); 
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }
    	
        results = getResultsofQuery("sales");
        
    	return results;
    			
	} // end of createSales() 
 
    
    protected List<List> updatedInventory(int updateQuantity, String name){
	    /**
	     * Method adds to existing quantity in inventory. 
	     * 
	     * @param updateQuantity This is an integer object update the inventory item quantity.
	     * @param name This is a String object to look up item in inventory.
	     * 
	     * @return List of List which is new result of all inventory items.
	     */
   
    	List<List> results = new ArrayList<List>();
    	
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
	    	
	    	PreparedStatement st = con.prepareStatement("UPDATE SIMS_app_data.inventory SET quantity = quantity + ? WHERE name = ?");
	    	st.setInt(1, updateQuantity);
	    	st.setString(2, name);
	        ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData(); 
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }
    	
    	results = getResultsofQuery("inventory");
        
    	return results;
    		
    } // end of updatedInventory()//
   
    
    protected List<List> updateOrderStatus(int status, String orderEventID) {
	    /**
	     * Method adds to existing quantity in inventory. 
	     * 
	     * @param status 
	     * @param orderEventID This is String object
	     *
	     * @return List of List which is new result of all inventory items.
	     */
	
		List<List> results = new ArrayList<List>();
		
	    try {
	    	
	    	// connect to database via JDBC
	    	Connection con = buildJDBCConnecter();
	    	
	    	PreparedStatement st = con.prepareStatement("UPDATE SIMS_app_data.orders SET status = ? WHERE OrderEventID = ?");
	    	st.setInt(1, status);
	    	st.setString(2, orderEventID);
	        ResultSet rs = st.executeQuery();
	    	
	    	ResultSetMetaData rsmd = rs.getMetaData(); 
	        
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
	        
	    } catch (SQLException e) {
	    	// handle and stop print for production
	        e.printStackTrace();
	    }
		
		results = getResultsofQuery("orders");
	    
		return results;
		
	}// end of updateOrderStatus()
    
    
    protected List<List> updateWasteStatus(int status, String wasteEventID) {
	    /**
	     * Method adds to existing quantity in inventory. 
	     * 
	     * @param status 
	     * @param wasteEventID This is String object
	     * 
	     * @return List of List which is new result of all inventory items.
	     */
	
		List<List> results = new ArrayList<List>();
		
	    try {
	    	
	    	// connect to database via JDBC
	    	Connection con = buildJDBCConnecter();
	    	
	    	PreparedStatement st = con.prepareStatement("UPDATE SIMS_app_data.waste SET status = ? WHERE WasteEventID = ?");
	    	st.setInt(1, status);
	    	st.setString(2, wasteEventID);
	        ResultSet rs = st.executeQuery();
	    	
	    	ResultSetMetaData rsmd = rs.getMetaData(); 
	        
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
		        
	    } catch (SQLException e) {
	    	// handle and stop print for production
	        e.printStackTrace();
	    }
			
		results = getResultsofQuery("waste");
	    
		return results;
			
	} // end of updateWasteStatus()

    
    protected List<List> retrieveSalesOnDate(String time) {
    	/**
    	 * DESCRIPTION
    	 * 
    	 * @param time
    	 * 
    	 * @return results List of List
    	 */
    	
    	
    	List<List> results = new ArrayList<List>();

        // 
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
        	
        	// preferred format 2020-06-19%
	    	PreparedStatement st = con.prepareStatement("SELECT * FROM SIMS_app_data.sales WHERE SalesDate LIKE \"?%\"");
	    	st.setString(1, time);
        	
            ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();  
            
            while (rs.next()) {
            
            	ArrayList<String> row = new ArrayList<String>();
            	
            	for (int i = 1; i <= columnsNumber; i++) {
            		row.add(rs.getString(i));
                }
            	
            	results.add(row);
            }
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }

        return results;
        
    } // end of retrieveSalesByDate()

    
    protected List<List> retrieveSalesSinceDate(String time) {
    	/**
    	 * DESCRIPTION
    	 * 
    	 * @param time
    	 * 
    	 * @return results List of List
    	 */
    	
    	
    	List<List> results = new ArrayList<List>();

        // 
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
        	

	    	PreparedStatement st = con.prepareStatement("SELECT * FROM SIMS_app_data.sales WHERE SalesDate >= ? ");
	    	st.setString(1, time);
        	
            ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();  
            
            while (rs.next()) {
            
            	ArrayList<String> row = new ArrayList<String>();
            	
            	for (int i = 1; i <= columnsNumber; i++) {
            		row.add(rs.getString(i));
                }
            	
            	results.add(row);
            }
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }

        return results;
        
    } // end of retrieveSalesByDate()

    
    protected List<List> retrieveSalesByDateRange(String startTime, String endTime) {
    	/**
    	 * DESCRIPTION
    	 * 
    	 * @param startTime
    	 * @param endTime
    	 * 
    	 * @return results List of List
    	 */
    	
    	
    	List<List> results = new ArrayList<List>();

        // 
        try {
        	
        	// connect to database via JDBC
        	Connection con = buildJDBCConnecter();
        	

	    	PreparedStatement st = con.prepareStatement("SELECT * FROM SIMS_app_data.sales WHERE SalesDate BETWEEN startTime AND endTime");
	    	st.setString(1, startTime);
	    	st.setString(1, endTime);
        	
            ResultSet rs = st.executeQuery();
        	
        	ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();  
            
            while (rs.next()) {
            
            	ArrayList<String> row = new ArrayList<String>();
            	
            	for (int i = 1; i <= columnsNumber; i++) {
            		row.add(rs.getString(i));
                }
            	
            	results.add(row);
            }
            
			// close con, rs, and st
			rs.close();
			st.close();
			con.close();
            
        } catch (SQLException e) {
        	// handle and stop print for production
            e.printStackTrace();
        }

        return results;
        
    } // end of retrieveSalesByDate()
    
    
    /** Java.sql.SQLExcpetion errors:
     * java.sql.SQLException: Access denied for user ....
     */
}
