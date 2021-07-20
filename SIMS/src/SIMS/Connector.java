package SIMS;

import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;


public class Connector {
	
	private List<List> results = new ArrayList<List>();
    
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "OB3rwqzxqmLr4E8eTDTM";
    
    // This key store has only the prod root ca.
    /* This should be started with
     * 
     * -Djavax.net.ssl.keyStore="C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore1" -Djavax.net.ssl.keyStorePassword="password"
     */ 
    
    private static final String KEY_STORE_FILE_PATH = "C:\\Program Files\\Java\\jdk-16.0.1\\bin\\truststore1";
    private static final String KEY_STORE_PASS = "password";
    
    public void connect() {
    	System.setProperty("javax.net.ssl.trustStore", KEY_STORE_FILE_PATH);
        System.setProperty("javax.net.ssl.trustStorePassword", KEY_STORE_PASS);
        
        
        String url = "jdbc:mysql://sims-application-test-001.c17nei9nvbm9.us-east-2.rds.amazonaws.com:3306/";
        String user = DB_USER;
        String pass = DB_PASSWORD;

        String query = "SELECT * FROM SIMS_app_data.inventory;";
        try (Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {
                
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                
                
                
                while (rs.next()) {
                    
                    ArrayList<String> row = new ArrayList<String>();
                    
                    for (int i = 1; i <= columnsNumber; i++) {
                        row.add(rs.getString(i));
                    }
                    results.add(row);
                }

                System.out.println(results);
                
            } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public List<List> getResults(){
    	return results;
    }
    
    public List<String> getItemNames(){
    	List names = new ArrayList<String>();
    	
    	for (List l : results) {
    		//System.out.println("LOOOPED");
    		names.add(l.get(1));
    	}
    	//System.out.println(names);
    	return names;
    }
    
//    public List<String> getItemNamesAndQuantity(){
//    	List namesAndQuantity = new ArrayList<String>();
//    	
//    	for (List l : results) {
//    		//System.out.println("LOOOPED");
//    		List temp =
//    		names.add(l.get(1));
//    	}
//    	//System.out.println(names);
//    	return namesAndQuantity;
//    }
//      
//    public static void main(String[] args) throws Exception {
//        Connector c = new Connector();
//        c.connect();
//        c.getItemNames();
//    }

}