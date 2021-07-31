package SIMS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

    private static LocalDateTime getDateTime() {
        /**
         * Method to retrieve local system date and time
         * 
         * @return date  as LocalDateTime object
         */
    	
         LocalDateTime date = LocalDateTime.now();
         return  date;
         
    } // end of getDate()
    
    protected static String getTodaysDateUser() {
        return formatDateForUser(LocalDateTime.now());
    } // end of formatDate()
    
    protected static String getTodaysDateSql() {
        return formatDateForSql(LocalDateTime.now());
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
    
    //Make it universal?????
    protected static String formatDateForSql(LocalDateTime date) {
        /**
         * Method to format local system date
         * 
         * @return dateToString as String object
         */

        // convert 'df' variable to String and format like YYYY-MM-dd
        String dateToString = date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        return dateToString;
    } // end of formatDate()
    

    
    protected String formatDateTime() {
        /**
         * Method to format local system date and time
         * 
         * @return dateTimeToString as String object
         */
    	
    	LocalDateTime df = getDateTime();

        // convert 'df' variable to String and format like MM-dd-yyyy HH:mm:ss
        String dateTimeToString = df.format(DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss"));
        return dateTimeToString;
    } // end of formatDateTime()

    protected static String sqlDateTime() {
        /**
         * Method to format local system date and time
         * 
         * @return dateTimeToString as String object
         */
    	
    	LocalDateTime df = getDateTime();

        // convert 'df' variable to String and format like '2011-12-03+01:00'
        String dateTimeToString = df.format(DateTimeFormatter.ISO_OFFSET_DATE);
        return dateTimeToString;
        
    } // end of formatDateTime()
    
        
}
