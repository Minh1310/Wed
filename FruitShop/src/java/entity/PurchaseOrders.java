/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nhat Anh
 */
public class PurchaseOrders {

    private int purchaseOrderID;
    private int supplierID;
    private String orderDate;
    private String deliveryMethod;
    private String expectedDeliveryDate;
    private String supplierReference;
    private int isOrderFinalized;
    
    
    public PurchaseOrders(int purchaseOrderID, int supplierID, String orderDate
            , String deliveryMethod, String expectedDeliveryDate
            , String supplierReference, int isOrderFinalized) {
        this.purchaseOrderID = purchaseOrderID;
        this.supplierID = supplierID;
        this.orderDate = orderDate;
        this.deliveryMethod = deliveryMethod;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.supplierReference = supplierReference;
        this.isOrderFinalized = isOrderFinalized;
    }

    public PurchaseOrders() {
    }

    public int getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(int purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getSupplierReference() {
        return supplierReference;
    }

    public void setSupplierReference(String supplierReference) {
        this.supplierReference = supplierReference;
    }

    public int getIsOrderFinalized() {
        return isOrderFinalized;
    }

    public void setIsOrderFinalized(int isOrderFinalized) {
        this.isOrderFinalized = isOrderFinalized;
    }

    @Override
    public String toString() {
        return "PurchaseOrders{" + "purchaseOrderID=" + purchaseOrderID 
                + ", supplierID=" + supplierID + ", orderDate=" + orderDate 
                + ", deliveryMethod=" + deliveryMethod + ", expectedDeliveryDate=" 
                + expectedDeliveryDate + ", supplierReference=" + supplierReference 
                + ", isOrderFinalized=" + isOrderFinalized + '}';
    }
    
}
