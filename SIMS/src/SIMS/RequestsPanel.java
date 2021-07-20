/*
 * File: RequestsPanel.java
 * Author: Ben Sutter
 * Date: July 19th, 2021
 * Purpose: Holds the request panel that is nested in User/Supervisor window
 */
package SIMS;

public class RequestsPanel extends javax.swing.JPanel {

    /**
     * Creates new form RequestsPanel
     */
    public RequestsPanel() {
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

        requestPanel = new javax.swing.JPanel();
        orderRequestPane = new javax.swing.JScrollPane();
        orderRequestList = new javax.swing.JList<>();
        viewOrderButton = new javax.swing.JButton();
        editOrderButton = new javax.swing.JButton();
        deleteOrderbutton = new javax.swing.JButton();
        deleteWasteButton = new javax.swing.JButton();
        editWasteButton = new javax.swing.JButton();
        viewWasteButton = new javax.swing.JButton();
        wasteREquestTable = new javax.swing.JScrollPane();
        wasteRequestList = new javax.swing.JList<>();
        wasteLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        orderLabel = new javax.swing.JLabel();

        orderRequestList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "7/4/21 7:42 PM - Benjamin Sutter", "7/5/21 8:31 AM - Benjamin Sutter", "stuff", "stuff", "stuff", "stuff" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        orderRequestPane.setViewportView(orderRequestList);

        viewOrderButton.setText("View");

        editOrderButton.setText("Edit");

        deleteOrderbutton.setText("Delete");

        deleteWasteButton.setText("Delete");

        editWasteButton.setText("Edit");

        viewWasteButton.setText("View");

        wasteRequestList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "No waste requests" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        wasteREquestTable.setViewportView(wasteRequestList);

        wasteLabel.setText("Waste Requests");

        helpButton.setBackground(new java.awt.Color(255, 255, 153));
        helpButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        helpButton.setText("?");

        orderLabel.setText("Order Requests");

        javax.swing.GroupLayout requestPanelLayout = new javax.swing.GroupLayout(requestPanel);
        requestPanel.setLayout(requestPanelLayout);
        requestPanelLayout.setHorizontalGroup(
            requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPanelLayout.createSequentialGroup()
                .addComponent(helpButton)
                .addGroup(requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(requestPanelLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(requestPanelLayout.createSequentialGroup()
                                .addComponent(viewOrderButton)
                                .addGap(32, 32, 32)
                                .addComponent(editOrderButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(deleteOrderbutton))
                            .addGroup(requestPanelLayout.createSequentialGroup()
                                .addComponent(viewWasteButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editWasteButton)
                                .addGap(32, 32, 32)
                                .addComponent(deleteWasteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(requestPanelLayout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(wasteLabel))
                            .addComponent(orderRequestPane)
                            .addComponent(wasteREquestTable)))
                    .addGroup(requestPanelLayout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(orderLabel)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        requestPanelLayout.setVerticalGroup(
            requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPanelLayout.createSequentialGroup()
                .addGroup(requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(helpButton)
                    .addComponent(orderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderRequestPane, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewOrderButton)
                    .addComponent(editOrderButton)
                    .addComponent(deleteOrderbutton))
                .addGap(18, 18, 18)
                .addComponent(wasteLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wasteREquestTable, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewWasteButton)
                    .addComponent(editWasteButton)
                    .addComponent(deleteWasteButton))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(requestPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(requestPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteOrderbutton;
    private javax.swing.JButton deleteWasteButton;
    private javax.swing.JButton editOrderButton;
    private javax.swing.JButton editWasteButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel orderLabel;
    private javax.swing.JList<String> orderRequestList;
    private javax.swing.JScrollPane orderRequestPane;
    private javax.swing.JPanel requestPanel;
    private javax.swing.JButton viewOrderButton;
    private javax.swing.JButton viewWasteButton;
    private javax.swing.JLabel wasteLabel;
    private javax.swing.JScrollPane wasteREquestTable;
    private javax.swing.JList<String> wasteRequestList;
    // End of variables declaration//GEN-END:variables
}
