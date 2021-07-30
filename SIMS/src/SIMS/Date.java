package SIMS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

	static LocalDateTime getDateTime() {
		/**
		 * Method to retrieve local system date and time
		 * 
		 * @return date as LocalDateTime object
		 */

		LocalDateTime date = LocalDateTime.now();
		return date;

	} // end of getDate()

	protected static String formatDate() {
		/**
		 * Method to format local system date
		 * 
		 * @return dateToString as String object
		 */

		LocalDateTime df = getDateTime();

		// convert 'date' variable to String and format like MM-dd-yyyy
		String dateToString = df.format(DateTimeFormatter.ofPattern("MM-dd-YYYY"));
		return dateToString;
	} // end of formatDate()

	protected String formatDateTime() {
		/**
		 * Method to format local system date and time
		 * 
		 * @return dateTimeToString as String object
		 */

		LocalDateTime df = getDateTime();

		// convert 'date' variable to String and format like MM-dd-yyyy
		String dateTimeToString = df.format(DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss"));
		return dateTimeToString;
	} // end of formatDateTime()

}