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
		this.trustStoreFilePath = "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore1";
		this.trustStorePassword = "password";
		this.mySqlPath = "jdbc:mysql://sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com:3306/";
	}
    
    // This key store has only the prod root ca.
    /* This should be started with
     * 
     * -Djavax.net.ssl.keyStore="C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore1" -Djavax.net.ssl.keyStorePassword="password"
     */ 
    
    private List<List> runAllQuery(String query) {
    	
    	System.setProperty("javax.net.ssl.trustStore", trustStoreFilePath);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
    	String pass = String.valueOf(this.password);
        
        List<List> results =  new ArrayList<List>();
        
        try {
        	
        	// connect to database via JDBC
        	Connection con = DriverManager.getConnection(mySqlPath, this.user, pass);
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
    } // // end of runQuery()
    
    // Return all the items!
    protected List<List> getResultsofQuery(String tableName) {
        /**
         * Method returns List<List> containing MySQL SELECT query.
         * 
         * @param tableName This is the name of the table you wish to query.
         * @return results 
         */

        List<List> results = new ArrayList<List>();

        switch(tableName.toLowerCase())
        {
            case "inventory":
                results = runAllQuery("SELECT * FROM SIMS_app_data.inventory");
                break;
            case "users":
                results = runAllQuery("SELECT * FROM SIMS_app_data.users");
                break;
            case "orders":
                results = runAllQuery("SELECT * FROM SIMS_app_data.orders");
                break;
            case "sales":
                results = runAllQuery("SELECT * FROM SIMS_app_data.sales");
                break;
            case "waste":
                results = runAllQuery("SELECT * FROM SIMS_app_data.waste");
                break;
            default:
                System.out.println("Table does not exists");
                //return something else
        }

        return results;
    } // end of getAllInventoryItems()
    
    // should only be called from LoginWindow
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
    	String pass = String.valueOf(this.password);
    	
    	System.setProperty("javax.net.ssl.trustStore", trustStoreFilePath);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
                                        
        try {
        	
        	// connect to database via JDBC
        	Connection con = DriverManager.getConnection(mySqlPath, this.user, pass);
        	
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
    
    // should only be called from LoginWindow
    protected void getUserRole() {
	    /**
	     * Method returns user role based on entry in SIMS_app_data.users.
	     * 
	     * @param userName This is a String object used to log into the MySQL instance.
	     * @param password This is a char[] object used to log into the MySQL instance.
	     * sets role as integer. 0 = Admin, 1 = Supervisor, 2 = Employee/User
	     */
    	
    	// invalid role
    	int role = 3;
    	String pass = String.valueOf(this.password);
    	
    	System.setProperty("javax.net.ssl.trustStore", trustStoreFilePath);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
                                        
        try {
        	
        	// connect to database via JDBC
        	Connection con = DriverManager.getConnection(mySqlPath, this.user, pass);
        	
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
    
    //protected Boolean verifyItemExists(String itemName){} // end of verifyItemExists()

    //protected List<List> retrieveInventoryCategories(){return sort and unique categories} // end of retrieveInventoryCategories()
    
    //protected List<List> createInventoryItem(String name, String category, double purchasePrice, 
		// double sellPrice, String description, int quantity){return getResultsofQuery(inventory)} // end of updatedInventory()
    
    //protected List<List> updatedInventory(String itemName, int quantity){return getResultsofQuery(inventory)} // end of updatedInventory()
    
    //protected List<List> retrieveSalesByDate(Date time){return all sales with unique MySQL query} // end of retrieveSalesByDate()
    
    //protected List<List> createOrder(List<List> orderData){return getResultsofQuery(orders)} // end of createOrder()
    
    //protected List<List> createWaste(List<List> orderData){return getResultsofQuery(Waste)} // end of createWaste()
    
    //protected List<List> createSales(Date time, List<List> saleData){return getResultsofQuery(sales)} // end of createSales()
    
    //protected List<List> updateOrderStatus(String status, int orderEventID){return getResultsofQuery(orders)} // end of updateOrderStatus()
    
    //protected List<List> updateWasteStatus(String status, int wasteEventID){return getResultsofQuery(waste)} // end of updateWasteStatus()
    
    /** Java.sql.SQLExcpetion errors:
     * java.sql.SQLException: Access denied for user ....
     */
}
