/*
 * File: HomePanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is in the home panel of the main GUI
 */
package SIMS;

import java.time.LocalDateTime;
import java.util.List;

import javax.swing.SwingUtilities;

public class HomePanel extends javax.swing.JPanel {

	TimeThread time;
	
    public HomePanel() {
        initComponents();
        time = new TimeThread(currentTimeLabel);
        time.start();
    }
    
    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        GeneralGuiFunctions.displayHelpPane("Here is how this panel works!");
    }//GEN-LAST:event_helpButtonActionPerformed

    // When the user logs out, opens a new LoginWindow and closes the current window
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        GeneralGuiFunctions.closeWindow(SwingUtilities.getWindowAncestor(this), new LoginWindow());
    }//GEN-LAST:event_logoutButtonActionPerformed


    private void refreshDataButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    // Variables declaration - do not modify                     
    private javax.swing.JLabel annualSalesLabel;
    private javax.swing.JLabel currentDateLabel;
    private javax.swing.JLabel currentTimeLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel helloUsernameLabel;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel lastWeekSaleLabel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel monthlySalesLabel;
    private javax.swing.JButton refreshDataButton;
    private javax.swing.JLabel refreshLabel;
    private javax.swing.JLabel todaysSalesLabel;
    private javax.swing.JLabel todaysSalesLastYearLabel;
    private javax.swing.JLabel totalRecordedSalesLabel;
    private javax.swing.JLabel yesterdaysSalesLabel;
    // End of variables declaration 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        helloUsernameLabel = new javax.swing.JLabel();
        currentTimeLabel = new javax.swing.JLabel();
        currentDateLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        annualSalesLabel = new javax.swing.JLabel();
        monthlySalesLabel = new javax.swing.JLabel();
        yesterdaysSalesLabel = new javax.swing.JLabel();
        todaysSalesLastYearLabel = new javax.swing.JLabel();
        lastWeekSaleLabel = new javax.swing.JLabel();
        refreshDataButton = new javax.swing.JButton();
        refreshLabel = new javax.swing.JLabel();
        totalRecordedSalesLabel = new javax.swing.JLabel();
        todaysSalesLabel = new javax.swing.JLabel();

        headerPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        helloUsernameLabel.setText("Hello, " + SupervisorWindow.getUsername());

        currentTimeLabel.setText("");

        currentDateLabel.setText(DateHandler.getTodaysDateUser());
        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(helloUsernameLabel)
                .addGap(68, 68, 68)
                .addComponent(currentDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(currentTimeLabel)
                .addGap(20, 20, 20))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(helloUsernameLabel)
                    .addComponent(currentTimeLabel)
                    .addComponent(currentDateLabel))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        helpButton.setBackground(new java.awt.Color(255, 255, 153));
        helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        helpButton.setForeground(new java.awt.Color(0, 0, 0));
        helpButton.setText("?");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        logoutButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        
        LocalDateTime date = LocalDateTime.now();
        String minusDay = DateHandler.formatDateForSql(date.minusDays(1));
        String minusWeek = DateHandler.formatDateForSql(date.minusWeeks(1));
        String minusMonth = DateHandler.formatDateForSql(date.minusMonths(1));
        String thisYear = DateHandler.formatDateForSql(date).substring(0, 4);
        String lastYear = DateHandler.formatDateForSql(date.minusYears(1));
        String today = DateHandler.formatDateForSql(date);
        
        Connector c = Database.getConnector();
        List<List> sales = Database.getSalesTable();
        String salesMinusDay = GeneralGuiFunctions.stringToPrice(GeneralGuiFunctions.parseSales(c.retrieveSalesOnDate(minusDay)));
        String salesMinusWeek = GeneralGuiFunctions.stringToPrice(GeneralGuiFunctions.parseSales(c.retrieveSalesByDateRange(minusWeek, today)));
        String salesMinusMonth = GeneralGuiFunctions.stringToPrice(GeneralGuiFunctions.parseSales(c.retrieveSalesOnDate(minusMonth.substring(0, 7))));
        String salesLastYear = GeneralGuiFunctions.stringToPrice(GeneralGuiFunctions.parseSales(c.retrieveSalesOnDate(lastYear)));
        String salesThisYear = GeneralGuiFunctions.stringToPrice(GeneralGuiFunctions.parseSales(c.retrieveSalesOnDate(thisYear)));
        String total = GeneralGuiFunctions.stringToPrice(GeneralGuiFunctions.parseSales(sales));
        String salesToday = GeneralGuiFunctions.stringToPrice(GeneralGuiFunctions.parseSales(c.retrieveSalesOnDate(today)));

        annualSalesLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        annualSalesLabel.setText("Total sales for this year: " + salesThisYear);

        monthlySalesLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        monthlySalesLabel.setText("Last month's sales: " + salesMinusMonth);

        yesterdaysSalesLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        yesterdaysSalesLabel.setText("Yesterday's sales: " + salesMinusDay);

        todaysSalesLastYearLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        todaysSalesLastYearLabel.setText("Today's sales last year: " + salesLastYear);

        lastWeekSaleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lastWeekSaleLabel.setText("Sales this week: " + salesMinusWeek);

        refreshDataButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        refreshDataButton.setText("Refresh Data");
        refreshDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshDataButtonActionPerformed(evt);
            }
        });

        refreshLabel.setForeground(new java.awt.Color(255, 0, 0));
        refreshLabel.setText("In case manual refresh is needed ->");

        totalRecordedSalesLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        totalRecordedSalesLabel.setText("Total recorded sales: " + total);

        todaysSalesLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        todaysSalesLabel.setText("Today's sales: " + salesToday);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(helpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yesterdaysSalesLabel)
                    .addComponent(monthlySalesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(todaysSalesLastYearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(annualSalesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastWeekSaleLabel)
                    .addComponent(totalRecordedSalesLabel)
                    .addComponent(todaysSalesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(refreshDataButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(helpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshLabel)))
                .addGap(32, 32, 32)
                .addComponent(todaysSalesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(todaysSalesLastYearLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yesterdaysSalesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lastWeekSaleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(monthlySalesLabel)
                .addGap(8, 8, 8)
                .addComponent(annualSalesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalRecordedSalesLabel)
                .addGap(18, 18, 18)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>    
  
}
