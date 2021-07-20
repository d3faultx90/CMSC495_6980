/*
 * File: OrderFilterPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Panel that is used to filter orders
 */
package SIMS;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class OrderFilterPanel extends javax.swing.JPanel {

	private List<List> results = new ArrayList<List>();
	
    public OrderFilterPanel(List<List> results) {
    	this.results = results;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filterLabel = new javax.swing.JLabel();
        filterField = new javax.swing.JTextField();
        itemScrollPane = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();

        filterLabel.setText("Filter");

        filterField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterFieldActionPerformed(evt);
            }
        });
        filterField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                filterFieldKeyReleased(evt);
            }
        });

        itemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "7-16-21"},
                {"2", "7-17-21"},
                {"3", "7-16-21"},
                {"4", "7-15-21"},
                {"5", "7-14-21"}
            },
            new String [] {
                "Order ID", "Order Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        itemScrollPane.setViewportView(itemTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filterLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterField, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filterLabel)
                    .addComponent(filterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(itemScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                .addContainerGap())
        );
//        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
//        for (List l : results) {
//        	model.addRow(new Object[]{l.get(1), l.get(6)});
//        }
    }// </editor-fold>//GEN-END:initComponents

    private void filterFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filterFieldKeyReleased
        // https://stackoverflow.com/a/37989058
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(((DefaultTableModel) itemTable.getModel()));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + filterField.getText()));

        itemTable.setRowSorter(sorter);
    }//GEN-LAST:event_filterFieldKeyReleased

    private void filterFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filterField;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JScrollPane itemScrollPane;
    public javax.swing.JTable itemTable;
    // End of variables declaration//GEN-END:variables
}
