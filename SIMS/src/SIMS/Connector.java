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
	
    // This array list of lists holds all items retrieved from the query
    private static List<List> resultsFromItemQuery = new ArrayList<List>();
    private static List<List> resultsFromOrderQuery = new ArrayList<List>();
	
    private static final String DB_USER = "SIMS_admin";
    private static final String DB_PASSWORD = "SIMS_Sup3r_C0mplex!";

    private static final String KEY_STORE_FILE_PATH = "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore1";
    private static final String KEY_STORE_PASS = "password";
        
    static String url = "jdbc:mysql://sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com:3306/";
    String user = DB_USER;
    String pass = DB_PASSWORD;
    
    // This key store has only the prod root ca.
    /* This should be started with
     * 
     * -Djavax.net.ssl.keyStore="C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore1" -Djavax.net.ssl.keyStorePassword="password"
     */ 
    
    private List<List> runAllQuery(String query) {
    	
    	System.setProperty("javax.net.ssl.trustStore", KEY_STORE_FILE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEY_STORE_PASS);
        
        List<List> results =  new ArrayList<List>();
        
        try {
        	
        	// connect to database via JDBC
        	Connection con = DriverManager.getConnection(url, user, pass);
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
            e.printStackTrace();
        }
        return results;
    } // // end of runQuery()
    
    // Return all items!
    protected List<List> getResultsofQuery(String query) {
    	
    	List<List> results = new ArrayList<List>();
    	System.out.println(query);
        switch(query.toLowerCase())
        {
            case "inventory":
            	resultsFromItemQuery = runAllQuery("SELECT * FROM SIMS_app_data.inventory");
                break;
            case "users":
            	results = runAllQuery("SELECT * FROM SIMS_app_data.users");
                break;
            case "orders":
            	resultsFromOrderQuery = runAllQuery("SELECT * FROM SIMS_app_data.orders");
                break;
            case "sales":
            	results = runAllQuery("SELECT * FROM SIMS_app_data.sales");
                break;
            default:
            	System.out.println("Table does not exists");
            	//return something else
        }
    	
    	return results;
    } // end of getAllInventoryItems()
   
    
    //
    protected static Boolean verifyUser(String userName, char[] password) {
        /**
         * Method attempts to login using Username provided and verify if 
         * userName exist in SIMS_app_data.users.
         * 
         * @param userName This is a String object used to log into the MySQL instance.
         * @param password This is a char[] object used to log into the MySQL instance.
         * @return result This returns a boolean value based on whether the user can log in. 
         */
    	
    	Boolean result = true;
    	String pass = String.valueOf(password);
    	
    	System.setProperty("javax.net.ssl.trustStore", KEY_STORE_FILE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEY_STORE_PASS);
                                        
        try {
        	
        	// connect to database via JDBC
        	Connection con = DriverManager.getConnection(url, userName, pass);
        	
        	PreparedStatement st = con.prepareStatement("SELECT Username FROM SIMS_app_data.users WHERE Username = ?");
        	st.setString(1, userName);
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
    
    // 
    protected static int getUserRole(String userName, char[] password) {
	    /**
	     * Method returns user role based on entry in SIMS_app_data.users.
	     * 
	     * @param userName This is a String object used to log into the MySQL instance.
	     * @param password This is a char[] object used to log into the MySQL instance.
	     * @return role This returns a integer. 0 = Admin, 1 = Supervisor, 2 = Employee/User
	     */
    	
    	// invalid role
    	int role = 3;
    	String pass = String.valueOf(password);

    	System.setProperty("javax.net.ssl.trustStore", KEY_STORE_FILE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEY_STORE_PASS);
                                        
        try {
        	
        	// connect to database via JDBC
        	Connection con = DriverManager.getConnection(url, userName, pass);
        	
        	PreparedStatement st = con.prepareStatement("SELECT role FROM SIMS_app_data.users WHERE Username = ?");
        	st.setString(1, userName);
            ResultSet rs = st.executeQuery();
        	
            while (rs.next()) {
            	role = (Integer) rs.getInt(1);
            }
                        
            // close con, rs, and st
            rs.close();
            st.close();
        	con.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            role = 3;
        }
        
    	return role;
    	    	
    } // end of getUserRole()
    
    static public List<List> getItemTable(){
    	return resultsFromItemQuery;
    }
    
    static public List<List> getOrderTable(){
    	return resultsFromOrderQuery;
    }

}
