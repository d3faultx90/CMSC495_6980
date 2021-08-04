/*
 * File: ImportAndExportPanel.java
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

public class ImportAndExportPanel extends javax.swing.JPanel {

    public ImportAndExportPanel() {
        initComponents();
    }
    
    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	GeneralGuiFunctions.displayHelpPane("To import data click on the import button. "
        		+ "\nTo export database information,click on the export button ");
    }                                          

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        List<List> sales = Database.getSalesTable();
        List<List> items = Database.getItemTable();
        List<List> orders = Database.getOrderTable();
        List<List> wastes = Database.getWasteTable();
        
        LocalDateTime date = LocalDateTime.now();
        String today = DateHandler.formatDateForSql(date);
        today = today.substring(0, 10);
        
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
        importButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        importLabel = new javax.swing.JLabel();
        exportLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();

        importButton.setText("Import");
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        exportButton.setText("Export");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
				exportButtonActionPerformed(evt);
            }
        });

        importLabel.setText("Import data from a CSV file");

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
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(exportLabel)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(importButton, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                        .addComponent(exportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 56, Short.MAX_VALUE))
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(helpButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(importLabel)
                .addGap(124, 124, 124))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(helpButton)
                .addGap(42, 42, 42)
                .addComponent(importLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(importButton)
                .addGap(128, 128, 128)
                .addComponent(exportLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>                        

}
