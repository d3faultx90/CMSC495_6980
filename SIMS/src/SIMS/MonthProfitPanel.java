/*
 * File: MonthProfitPanel.java
 * Author: Ben Sutter
 * Date: July 31st, 2021
 * Purpose: Used in MonthViewPanel to represent monthly break down of sales
 */

package SIMS;

import java.util.List;
import java.util.Map;

import enums.Months;

public class MonthProfitPanel extends javax.swing.JPanel {

	private String month;
	private String year;
	private String profits;
	private List<List> salesFromQuery;
	
	
//	public MonthProfitPanel(salesFromQuery) {
//		this.month = month.name();
//		this.year = year;
//		this.salesFromQuery = salesFromQuery
//		this.profits = profits;
//		initComponents();
//	}

	public MonthProfitPanel(Months month, String year) {
		this.month = month.name();
		this.year = year;
		salesFromQuery = getMonthsProfits(year, month.getNumericalRepresentation());
		Double value = GeneralGuiFunctions.parseSales(salesFromQuery);
		String profits = GeneralGuiFunctions.priceToString(value);
		this.profits = profits;
		initComponents();
	}

	private List<List> getMonthsProfits(String year, String month) {
		return Database.getConnector().retrieveSalesOnDate(year + "-" + month);
	}

	private Object[][] parseSalesForBreakdownWindow() {
		Object[][] parsed = new Object[salesFromQuery.size()][4];

		Map<Object, Object> itemNames = Database.getItemNamesMap();
		for (int i = 0; i < salesFromQuery.size(); i++) {
			Object dateAndTime = salesFromQuery.get(i).get(8);
			Object name = itemNames.get(salesFromQuery.get(i).get(3));
			Object quantity = salesFromQuery.get(i).get(7);
			Object price = salesFromQuery.get(i).get(6);
			parsed[i] = new Object[] {dateAndTime, name, quantity, price};
		}
		return parsed;
	}
	
	private void monthProfitButtonActionPerformed(java.awt.event.ActionEvent evt) {
		new MonthlySaleBreakdownWindow(parseSalesForBreakdownWindow(), month + " " + year).setVisible(true);
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel monthLabel;
	private javax.swing.JButton monthProfitButton;
	// End of variables declaration
	
	private void initComponents() {

		monthProfitButton = new javax.swing.JButton();
		monthLabel = new javax.swing.JLabel();

		setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		setMaximumSize(new java.awt.Dimension(147, 80));
		setMinimumSize(new java.awt.Dimension(147, 80));

		monthProfitButton.setText(profits);
		monthProfitButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				monthProfitButtonActionPerformed(evt);
			}
		});

		monthLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		monthLabel.setText(month);
		
		if (salesFromQuery.isEmpty()) {
			monthProfitButton.setEnabled(false);
		}

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(monthLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(monthProfitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap().addComponent(monthLabel)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(monthProfitButton,
						javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap(10, Short.MAX_VALUE)));
	}// </editor-fold>

}
