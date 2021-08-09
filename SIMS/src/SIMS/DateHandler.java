/*
 * File: DateHandler.java
 * Author: Ben Sutter
 * Date: July 31st, 2021
 * Purpose: Holds various helper methods to help display or pass various date formats.
 */

package SIMS;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;  

public class DateHandler {

	 // Takes a date and translates it to the format used for all SQL actions.
    protected static String formatDateForSql(Date date) {
        /**
         * Method to format local system date
         * 
         * @return dateToString as String object
         */

        // convert 'date' variable to String and format like YYYY-MM-dd
    	DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");  
        return dateFormat.format(date);  
    } // end of formatDate()
    
    // Takes a date and translates it to the format used for all SQL actions.
    protected static String formatDateForSql(LocalDateTime date) {
        /**
         * Method to format local system date
         * 
         * @return dateToString as String object
         */

        // convert 'date' variable to String and format like YYYY-MM-dd
        String dateToString = date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
        return dateToString;
    } // end of formatDate()
    
    protected static String formatDateForUser(LocalDateTime date) {
        /**
         * Method to format local system date
         * 
         * @return dateToString as String object
         */

        // convert 'df' variable to String and format like MM-dd-yyyy
        String dateToString = date.format(DateTimeFormatter.ofPattern("MM-dd-YYYY"));
        return dateToString;
    } // end of formatDate()
    
    private static LocalDateTime getDateTime() {
        /**
         * Method to retrieve local system date and time
         * 
         * @return date  as LocalDateTime object
         */
    	
         LocalDateTime date = LocalDateTime.now();
         return  date;
         
    } // end of getDateTime()
    
    // Returns todays date/time in the sql format
    protected static String getTodaysDateSql() {
        return formatDateForSql(LocalDateTime.now());
    } // end of formatDate()
    
    // Returns todays date/time in the sql format
    protected static String getTodaysDateUser() {
        return formatDateForUser(LocalDateTime.now());
    } // end of formatDate()
        
}
