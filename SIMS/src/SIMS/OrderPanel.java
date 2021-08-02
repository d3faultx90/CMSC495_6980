/*
 * File: OrderPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used in the order subtab
 */
package SIMS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderPanel extends javax.swing.JPanel {
	
    public OrderPanel() {
        initComponents();
    }  

    private void orderButtonActionPerformed(java.awt.event.ActionEvent evt) {
       
    	
    	javax.swing.JTable orderedItems = orderAndSalesPanel.orderTable;
    	Map <String, Integer> itemNum = Database.getItemIdMap();
    	
    	List<List> databBaseInfo = new ArrayList<List>();
    	
    	if(orderedItems.getRowCount()==0) 
    	{
    		GeneralGuiFunctions.displayErrorPane("Please select an item to order");
    		
    	}else{
    		
    		for (int i = 0; i < orderedItems.getRowCount(); i++) {
			
    		//2D List to pass to createOrder
			List<Integer> list = new ArrayList<Integer>();
			
			//int employeeID = ;
			int itemID = itemNum.get(orderedItems.getValueAt(i, 0));
			//double salesTax =;
			//double wholeSalePrice = ;
			int quantity = GeneralGuiFunctions.castSqlObjectToInteger(orderedItems.getValueAt(i, 1));
			//String date = ;
			//int status = ;
			
			
			
			//list.add(employeeID);
			list.add(itemID);
			//list.add(saleTax);
			//list.add(wholeSalePrice);
			list.add(quantity);
			//list.add(date);
			//list.add(status);
			
			databBaseInfo.add(list);
			
			System.out.println(databBaseInfo);
			
			
		    }//end for 
    		
    	 }//end else
    	
		//Database.getConnector().createOrder(employeeID, itemID, saleTax, wholeSalePrice, quantity, date, status);
		
  }//end orderButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private SIMS.OrderAndSalesPanel orderAndSalesPanel;
    private javax.swing.JButton orderButton;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        orderButton = new javax.swing.JButton();
        orderAndSalesPanel = new SIMS.OrderAndSalesPanel();

        orderButton.setBackground(new java.awt.Color(0, 102, 0));
        orderButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        orderButton.setText("Order");
        orderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderAndSalesPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addComponent(orderAndSalesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 417, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

}
