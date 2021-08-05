/*
 * File: MonthlySaleBreakdownWindow.java
 * Author: Ben Sutter
 * Date: August 4th, 2021
 * Purpose: Given a formatted 2D array, display its contents in a table
 */

package SIMS;

public class MonthlySaleBreakdownWindow extends javax.swing.JFrame {

	Object[][] masterArray;
	String title;

	public MonthlySaleBreakdownWindow(Object[][] masterArray, String title) {
		this.masterArray = masterArray;
		this.title = title;
		convertColumnsForSorting();
		initComponents();
	}

	private void convertColumnsForSorting() {
		for (int i = 0; i < masterArray.length; i++) {
			masterArray[i][2] = GeneralGuiFunctions.castObjectToInteger(masterArray[i][2]);
			masterArray[i][3] = GeneralGuiFunctions.castObjectToDouble(masterArray[i][3]);
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		saleTable = new javax.swing.JTable();
		titleLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Sales during " + title);

		saleTable.setModel(new javax.swing.table.DefaultTableModel(

				masterArray, new String[] { "Date and Time", "Item", "Quantity", "Total Sales Price" }) {
			Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class,
					java.lang.Double.class };
			boolean[] canEdit = new boolean[] { true, false, false, false };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});

		saleTable.setAutoCreateRowSorter(true);

		jScrollPane1.setViewportView(saleTable);

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

	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable saleTable;
	private javax.swing.JLabel titleLabel;
	// End of variables declaration//GEN-END:variables
}
