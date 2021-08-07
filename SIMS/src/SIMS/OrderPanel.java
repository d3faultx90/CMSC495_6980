/*
 * File: OrderPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the order subtab
 */
package SIMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class OrderPanel extends javax.swing.JPanel {

	public OrderPanel() {
		initComponents();
	}

	private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {

		javax.swing.JTable orderedItems = orderPanel.orderTable;
		Map<Object, Object> itemNum = Database.getItemIdMap();
		Map<Object, Object> itemPrices = Database.getWholesaleItemPricesMap();
		

		List<List> databBaseInfo = new ArrayList<List>();

		if (orderedItems.getRowCount() == 0) {
			
			GeneralGuiFunctions.displayErrorPane("Please select an item to order");

		} else {

			String date = DateHandler.getTodaysDateSql();

			for (int i = 0; i < orderedItems.getRowCount(); i++) {

				// 2D List to pass to createOrder
				List<Object> list = new ArrayList<Object>();

				
				Object itemID = itemNum.get(orderedItems.getValueAt(i, 0));
				Object price = itemPrices.get(orderedItems.getValueAt(i, 0));
				Object quantity = orderedItems.getValueAt(i, 1);
				
				
				
				list.add(itemID);
				list.add(price);
				list.add(quantity);
				

				databBaseInfo.add(list);
				
				

			} // end for
			
			// If they are a regular user (role = 2), the status should be 0 (pending)
			int status = 0; // 0 = pending, 1 = approved
			String confirmation = "Order request";
			// If they are a regular user (role = 2), the status should be 0 (pending)
			if (Database.getConnector().role == 1) {
				status = 1;
				confirmation = "Order";
			} 

			Database.getConnector().createOrder(databBaseInfo,date, status);
			
			GeneralGuiFunctions.clearTable(orderPanel.orderTable);
			
			GeneralGuiFunctions.displayConfirmationPane(confirmation + " was submitted succesfully");

		} // end else

		

	}// end orderButtonActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private SIMS.OrderAndSalesPanel orderPanel;
	protected javax.swing.JButton orderButton;
	private javax.swing.JPanel mainPanel;
	// End of variables declaration//GEN-END:variables

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        orderPanel = new SIMS.OrderAndSalesPanel();
        orderButton = new javax.swing.JButton();
       
        orderButton.setBackground(new java.awt.Color(0, 102, 0));
        orderButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        orderButton.setText("Order");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>  

}