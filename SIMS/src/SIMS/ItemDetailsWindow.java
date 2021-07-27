/*
 * File: ItemDetailsWindow.java
 * Author: Ben Sutter
* Date: July 26th, 2021
 * Purpose: Is used to display more details about the selected item when the view detail button is pressed in ViewInventoryPanel.java
 */
package SIMS;


public class ItemDetailsWindow extends javax.swing.JFrame {

    public ItemDetailsWindow(String name, String description, String category, String wholesalePrice, String retailPrice, String quantity) {
        initComponents();
        this.setTitle(name);
        itemNameLabel.setText(name);
        descriptionText.setText(description);
        categoryText.setText(category);
        wholesalePriceText.setText(GeneralGuiFunctions.stringToPrice(wholesalePrice));
        retailPriceText.setText(GeneralGuiFunctions.stringToPrice(retailPrice));
        quantityText.setText(quantity);
        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        itemNameLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        categoryText = new javax.swing.JLabel();
        wholesalePriceLabel = new javax.swing.JLabel();
        wholesalePriceText = new javax.swing.JLabel();
        retailPriceLabel = new javax.swing.JLabel();
        retailPriceText = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        quantityText = new javax.swing.JLabel();
        descriptionText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Title Here");

        itemNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        itemNameLabel.setText("Item Name");

        descriptionLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        descriptionLabel.setText("Description:");

        categoryLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        categoryLabel.setText("Category:");

        categoryText.setText("desc here");

        wholesalePriceLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        wholesalePriceLabel.setText("Wholesale Price:");

        wholesalePriceText.setText("desc here");

        retailPriceLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        retailPriceLabel.setText("Retail Price:");

        retailPriceText.setText("desc here");

        quantityLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quantityLabel.setText("Quantity:");

        quantityText.setText("desc here");

        descriptionText.setText("desc here");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(descriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemNameLabel)
                            .addComponent(descriptionText)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(quantityLabel)
                            .addComponent(retailPriceLabel)
                            .addComponent(categoryLabel)
                            .addComponent(wholesalePriceLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryText)
                            .addComponent(wholesalePriceText)
                            .addComponent(retailPriceText)
                            .addComponent(quantityText))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(itemNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel)
                    .addComponent(descriptionText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryLabel)
                    .addComponent(categoryText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wholesalePriceLabel)
                    .addComponent(wholesalePriceText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(retailPriceLabel)
                    .addComponent(retailPriceText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(quantityText))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JLabel categoryText;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel descriptionText;
    private javax.swing.JLabel itemNameLabel;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JLabel quantityText;
    private javax.swing.JLabel retailPriceLabel;
    private javax.swing.JLabel retailPriceText;
    private javax.swing.JLabel wholesalePriceLabel;
    private javax.swing.JLabel wholesalePriceText;
    // End of variables declaration//GEN-END:variables
}
