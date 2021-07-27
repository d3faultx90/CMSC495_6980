package SIMS;

/*
 * File: TimeThread.java
 * Author: Ben Sutter
 * Date: May 4th, 2021
 * Purpose: While the thread is running/not paused display the current time in seconds.
 */
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TimeThread extends Thread {

    private JLabel label;//Thread will update this label every second
	boolean isRunning = true;

    //Store the date format for easy use
    private SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");

    //The thread takes the label it will update each second as a parameter
    public TimeThread(JLabel label) {
        this.label = label;
    }

    public void stopThread() {
    	isRunning = false;
    }

    @Override
    public void run() {
    	// Need better logic
    	while(isRunning) {
	        try {
	            Date date = new Date(System.currentTimeMillis());
	            //label.setText("Current time: " + time.format(date));
	            label.setText(time.format(date));
	            Thread.sleep(1000);//Sleeps every second so it only increments in seconds
	        } catch (InterruptedException e) {
	        }
        }
    }

}
