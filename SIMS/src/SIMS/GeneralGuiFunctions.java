/*
 * File: GeneralGuiFunctions.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Holds functions that are used in multiple panels/windows of the GUI
 */
package SIMS;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowEvent;

public class GeneralGuiFunctions {

    //https://www.youtube.com/watch?v=hFv2Uay0qj0
    static void close(Window toClose, javax.swing.JFrame toOpen) {
        WindowEvent closeWindow = new WindowEvent(toClose, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
        new SupervisorWindow().setVisible(true);
    }
}
