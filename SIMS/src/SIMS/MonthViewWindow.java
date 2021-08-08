/*
 * File: MonthViewPanel.java
 * Author: Ben Sutter
 * Date: July 31st, 2021
 * Purpose: Opens from the report panel to show a monthly breakdown of sales.
 */

package SIMS;

import java.util.ArrayList;
import java.util.List;

import enums.Months;

public class MonthViewWindow extends javax.swing.JFrame {
	
	String year;
	List<List> januarySales = new ArrayList<List>();
	List<List> februarySales = new ArrayList<List>();
	List<List> marchSales = new ArrayList<List>();
	List<List> aprilSales = new ArrayList<List>();
	List<List> maySales = new ArrayList<List>();
	List<List> juneSales = new ArrayList<List>();
	List<List> julySales = new ArrayList<List>();
	List<List> augustSales = new ArrayList<List>();
	List<List> septemberSales = new ArrayList<List>();
	List<List> octoberSales = new ArrayList<List>();
	List<List> novemberSales = new ArrayList<List>();
	List<List> decemberSales = new ArrayList<List>();
	

    public MonthViewWindow(String year) {
    	this.year = year;
    	List<List> sales = Database.getSalesTable();
        // Iterative over the whole year
    	parseMonthlySales(sales);
    	initComponents();
    }
    
    private void parseMonthlySales(List<List> sales) {
    	for (List l : sales) {
        	String month = l.get(8).toString().substring(5, 7);
        	if (l.get(8).toString().substring(0, 4).equals(this.year)) {
        		// If month add whole list to 2d array
	        	if (month.equals("01")) {
	        		
	        		januarySales.add(l);
	        	}
	        	else if (month.equals("02")) {
	        		februarySales.add(l);
	        	}
	        	else if (month.equals("03")) {
	        		marchSales.add(l);
	        	}
	        	else if (month.equals("04")) {
	        		aprilSales.add(l);
	        	}
	        	else if (month.equals("05")) {
	        		maySales.add(l);
	        	}
	        	else if (month.equals("06")) {
	        		juneSales.add(l);
	        	}
	        	else if (month.equals("07")) {
	        		
	        		julySales.add(l);
	        	}
	        	else if (month.equals("08")) {
	        		augustSales.add(l);
	        	}
	        	else if (month.equals("09")) {
	        		septemberSales.add(l);
	        	}
	        	else if (month.equals("10")) {
	        		octoberSales.add(l);
	        	}
	        	else if (month.equals("11")) {
	        		novemberSales.add(l);
	        	}
	        	else if (month.equals("12")) {
	        		decemberSales.add(l);
	        	}
        	}
        }
    }
                       
    private void initComponents() {

        yearLabel = new javax.swing.JLabel();
               
        januaryPanel = new SIMS.MonthProfitPanel(Months.January, year, januarySales);
        februaryPanel = new SIMS.MonthProfitPanel(Months.February, year, februarySales);
        marchPanel = new SIMS.MonthProfitPanel(Months.March, year, marchSales);
        aprilPanel = new SIMS.MonthProfitPanel(Months.April, year, aprilSales);
        mayPanel = new SIMS.MonthProfitPanel(Months.May, year, maySales);
        junePanel = new SIMS.MonthProfitPanel(Months.June, year, juneSales);
        julyPanel = new SIMS.MonthProfitPanel(Months.July, year, julySales);
        augustPanel = new SIMS.MonthProfitPanel(Months.August, year, augustSales);
        septemberPanel = new SIMS.MonthProfitPanel(Months.September, year, septemberSales);
        octoberPanel = new SIMS.MonthProfitPanel(Months.October, year, octoberSales);
        novemberPanel = new SIMS.MonthProfitPanel(Months.November, year, novemberSales);
        decemberPanel = new SIMS.MonthProfitPanel(Months.December, year, decemberSales);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(year + " Monthly Breakdown");
        setMaximumSize(new java.awt.Dimension(417, 396));
        setMinimumSize(new java.awt.Dimension(417, 396));
        setResizable(false);

        yearLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        yearLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        yearLabel.setText(year);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(januaryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(februaryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(marchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(aprilPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(junePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(julyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(augustPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(septemberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(octoberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(novemberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(decemberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(yearLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(marchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(januaryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(februaryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(junePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aprilPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(septemberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(julyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(augustPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(decemberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(octoberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(novemberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>       
    

    // Variables declaration - do not modify                     
    private SIMS.MonthProfitPanel aprilPanel;
    private SIMS.MonthProfitPanel augustPanel;
    private SIMS.MonthProfitPanel decemberPanel;
    private SIMS.MonthProfitPanel februaryPanel;
    private SIMS.MonthProfitPanel januaryPanel;
    private SIMS.MonthProfitPanel julyPanel;
    private SIMS.MonthProfitPanel junePanel;
    private SIMS.MonthProfitPanel marchPanel;
    private SIMS.MonthProfitPanel mayPanel;
    private SIMS.MonthProfitPanel novemberPanel;
    private SIMS.MonthProfitPanel octoberPanel;
    private SIMS.MonthProfitPanel septemberPanel;
    private javax.swing.JLabel yearLabel;
    // End of variables declaration                   
}
