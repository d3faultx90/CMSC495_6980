/*
 * File: ExportPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the Import/Export tab of the Main GUI
 */
package SIMS;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JFileChooser;

public class ExportPanel extends javax.swing.JPanel {

    protected ExportPanel() {
        initComponents();
    }                                     

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	GeneralGuiFunctions.displayHelpPane("To import data click on the import button. "
        		+ "\nTo export database information,click on the export button ");
    }                                           

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	Connector connector = Database.getConnector();
    	
    	List<List> sales = connector.exportResultsofQuery("sales");
    	List<List> items = connector.exportResultsofQuery("inventory");
    	List<List> orders = connector.exportResultsofQuery("orders");
    	List<List> wastes = connector.exportResultsofQuery("waste");
        
        String today = DateHandler.getTodaysDateSql();
        today = today.replaceAll(" ", "_").replaceAll(":", ";");
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Specify a folder to export the files");
        fileChooser.setCurrentDirectory(new File(".\\"));
        
        int userSelection = fileChooser.showOpenDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
        
	        try {
	        	CSVWriter writer = new CSVWriter(new FileWriter(fileToSave.getAbsolutePath() + "\\" + today + "_sales.csv"));
	        	for (List sale: sales) {
	        		writer.writeNext((String[])sale.toArray(new String[sale.size()]));
	        	}
	        	writer.close();
	        	
	        	writer = new CSVWriter(new FileWriter(fileToSave.getAbsolutePath() + "\\" + today + "_items.csv"));
	        	for (List item: items) {
	        		writer.writeNext((String[])item.toArray(new String[item.size()]));
	        	}
	        	writer.close();
	        	
	        	writer = new CSVWriter(new FileWriter(fileToSave.getAbsolutePath() + "\\" + today + "_orders.csv"));
	        	for (List order: orders) {
	        		writer.writeNext((String[])order.toArray(new String[order.size()]));
	        	}
	        	writer.close();
	        	
	        	writer = new CSVWriter(new FileWriter(fileToSave.getAbsolutePath() + "\\" + today + "_waste.csv"));
	        	for (List waste: wastes) {
	        		writer.writeNext((String[])waste.toArray(new String[waste.size()]));
	        	}
	        	writer.close();
	        	GeneralGuiFunctions.displayConfirmationPane("Data exported successfully!");
	        	
	        } 
	        catch (IOException e) {
	        	e.printStackTrace();
	        	GeneralGuiFunctions.displayErrorPane("File IO Error");
	        }
        }
    }        
       


    // Variables declaration - do not modify
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel exportLabel;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel importLabel;
    private javax.swing.JPanel panel;
    // End of variables declaration

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        panel = new javax.swing.JPanel();
        exportButton = new javax.swing.JButton();
        exportLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();

        exportButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        exportLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        exportLabel.setText("Export current database to CSV file");

        helpButton.setBackground(new java.awt.Color(255, 255, 153));
        helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        helpButton.setForeground(new java.awt.Color(0, 0, 0));
        helpButton.setText("?");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(helpButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(exportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                .addGap(0, 54, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(helpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>    
}
