/*
 * File: .java
 * Author: Ben Sutter
 * Date: Month day, 2021
 * Purpose:
 */
package SIMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

public class OrderAndWasteDetailWindow extends javax.swing.JFrame {

	Object[][] masterArray;
	String date;
	String wasteOrOrder;
	String totalPrice;

	protected OrderAndWasteDetailWindow(String wasteOrOrder, String date, Object[][] masterArray, String totalPrice) {
		this.wasteOrOrder = wasteOrOrder;
		this.date = date;
		this.masterArray = masterArray;
		this.totalPrice = totalPrice;
		initComponents();
	}

	private void filterTextfieldKeyReleased(java.awt.event.KeyEvent evt) {
		GeneralGuiFunctions.filterTable(detailsTable, filterTextfield);
	}

	static void displayDetails(JTable table, int dateIndex) {

		try {

			Map<Object, Object> itemNames = Database.getItemNamesMap();
			ArrayList<Object[]> masterList = new ArrayList<Object[]>();

			JTable viewItem = table;
			Object selectedCellValue = viewItem.getValueAt(viewItem.getSelectedRow(), dateIndex);

			double totalPrice = 0;

			for (List request : Database.getOrderTable()) {

				if (selectedCellValue.equals(request.get(8))) {

					Object name = itemNames.get(request.get(4));
					Object quantity = request.get(7);
					Object price = request.get(6);
					totalPrice += GeneralGuiFunctions.castObjectToDouble(price);
					Object[] grouped = new Object[] { name, quantity, price };
					masterList.add(grouped);
				}
			}
			// Convert 2D arraylist into 2D array
			Object[][] masterArray = new Object[masterList.size()][3];
			for (int i = 0; i < masterList.size(); i++) {

				masterArray[i] = masterList.get(i);

			}

			String formattedTotalPrice = GeneralGuiFunctions.doubleToDollarRepresentation(totalPrice);
			new OrderAndWasteDetailWindow("Order", (String) selectedCellValue, masterArray, formattedTotalPrice)
					.setVisible(true);

		} catch (ArrayIndexOutOfBoundsException e) {

			GeneralGuiFunctions.displayErrorPane("Please select an item");

		}
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel dateLabel;
	private javax.swing.JTable detailsTable;
	private javax.swing.JLabel filterLabel;
	private javax.swing.JTextField filterTextfield;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel totalPriceTextfield;
	// End of variables declaration

	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		detailsTable = new javax.swing.JTable();
		dateLabel = new javax.swing.JLabel();
		filterLabel = new javax.swing.JLabel();
		filterTextfield = new javax.swing.JTextField();
		totalPriceTextfield = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Details of " + wasteOrOrder + " on " + date);
		setResizable(false);

		detailsTable.setModel(new javax.swing.table.DefaultTableModel(masterArray,
				new String[] { "Item Name", "Quantity", "Total Price" }) {
			boolean[] canEdit = new boolean[] { false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jScrollPane1.setViewportView(detailsTable);

		dateLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		dateLabel.setText(date);

		filterLabel.setText("Filter");

		filterTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				filterTextfieldKeyReleased(evt);
			}
		});

		totalPriceTextfield.setText("Total price of order: " + totalPrice);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addComponent(totalPriceTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 213,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup()
										.addComponent(filterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(filterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 303,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 347,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap().addComponent(dateLabel).addGap(5, 5, 5)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(filterLabel).addComponent(filterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(totalPriceTextfield)
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>

}
