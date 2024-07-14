/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nhat Anh
 */
public class PurchaseOrderLines {

    private int purchaseOrderLineID;
    private int purchaseOrderID;
    private int productID;
    private int orderedQuantity;
    private String description;
    private int receivedQuantity;
    private String lastReceiptDate;
    private int isOrderLineFinalized;

    public PurchaseOrderLines() {
    }

    public PurchaseOrderLines(int purchaseOrderLineID, int purchaseOrderID, int productID, int orderedQuantity, String description, int receivedQuantity, String lastReceiptDate, int isOrderLineFinalized) {
        this.purchaseOrderLineID = purchaseOrderLineID;
        this.purchaseOrderID = purchaseOrderID;
        this.productID = productID;
        this.orderedQuantity = orderedQuantity;
        this.description = description;
        this.receivedQuantity = receivedQuantity;
        this.lastReceiptDate = lastReceiptDate;
        this.isOrderLineFinalized = isOrderLineFinalized;
    }

    public int getPurchaseOrderLineID() {
        return purchaseOrderLineID;
    }

    public void setPurchaseOrderLineID(int purchaseOrderLineID) {
        this.purchaseOrderLineID = purchaseOrderLineID;
    }

    public int getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(int purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReceivedQuantity() {
        return receivedQuantity;
    }

    public void setReceivedQuantity(int receivedQuantity) {
        this.receivedQuantity = receivedQuantity;
    }

    public String getLastReceiptDate() {
        return lastReceiptDate;
    }

    public void setLastReceiptDate(String lastReceiptDate) {
        this.lastReceiptDate = lastReceiptDate;
    }

    public int isIsOrderLineFinalized() {
        return isOrderLineFinalized;
    }

    public void setIsOrderLineFinalized(int isOrderLineFinalized) {
        this.isOrderLineFinalized = isOrderLineFinalized;
    }

    @Override
    public String toString() {
        return "PurchaseOrderLines{" + "purchaseOrderLineID=" + purchaseOrderLineID + ", purchaseOrderID=" + purchaseOrderID + ", productID=" + productID + ", orderedQuantity=" + orderedQuantity + ", description=" + description + ", receivedQuantity=" + receivedQuantity + ", lastReceiptDate=" + lastReceiptDate + ", isOrderLineFinalized=" + isOrderLineFinalized + '}';
    }
    
    
}
