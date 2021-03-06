/*
 * File: MonthlySaleBreakdownWindow.java
 * Author: Ben Sutter
 * Date: August 4th, 2021
 * Purpose: Given a formatted 2D array, display its contents in a table
 */

package SIMS;

import javax.swing.table.TableColumnModel;

public class MonthlySaleBreakdownWindow extends javax.swing.JFrame {

	Object[][] masterArray; // Used to populate the JTable
	String title; // Title of the window

	// Variables declaration
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable saleTable;
	private javax.swing.JLabel titleLabel;
	// End of variables declaration

	protected MonthlySaleBreakdownWindow(Object[][] masterArray, String title) {
		this.masterArray = masterArray;
		this.title = title;
		convertColumnsForSorting();
		initComponents();
	}

	// Converts indexes 2 and 3 to doubles so sorting works correctly
	private void convertColumnsForSorting() {
		for (int i = 0; i < masterArray.length; i++) {
			masterArray[i][2] = GeneralGuiFunctions.castObjectToDouble(masterArray[i][2]);
			masterArray[i][3] = GeneralGuiFunctions.castObjectToDouble(masterArray[i][3]);
		}
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		saleTable = new javax.swing.JTable();
		titleLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Sales during " + title);
		setResizable(false);

		saleTable.setModel(new javax.swing.table.DefaultTableModel(masterArray,
				new String[] { "Date and Time", "Item", "Quantity", "Total Sales Price" }) {
			Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Double.class,
					java.lang.Double.class };
			boolean[] canEdit = new boolean[] { false, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		saleTable.setAutoCreateRowSorter(true);

		saleTable.getRowSorter().toggleSortOrder(0);

		jScrollPane1.setViewportView(saleTable);

		if (saleTable.getColumnModel().getColumnCount() > 0) {
			saleTable.getColumnModel().getColumn(0).setPreferredWidth(75);
			saleTable.getColumnModel().getColumn(2).setPreferredWidth(10);
			saleTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		}

		titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titleLabel.setText(title);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jScrollPane1,
								javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addComponent(titleLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		pack();
		setLocationRelativeTo(null);

	}

}
