/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nhat Anh
 */
public class SupplierTransactions {

    private int supplierTransactionID;
    private int supplierID;
    private String transactionType;
    private int purchaseOrderID;
    private String paymentMethod;
    private String supplierInvoiceNumber;
    private String transactionDate;
    private double amountExcludingTax;
    private double taxAmount;
    private double transactionAmount;
    private String finalizationDate;
    private int isFinalized;

    public SupplierTransactions(int supplierTransactionID, int supplierID, String transactionType, 
            int purchaseOrderID, String paymentMethod, String supplierInvoiceNumber, 
            String transactionDate, double amountExcludingTax, double taxAmount, 
            double transactionAmount, String finalizationDate, int isFinalized) {
        this.supplierTransactionID = supplierTransactionID;
        this.supplierID = supplierID;
        this.transactionType = transactionType;
        this.purchaseOrderID = purchaseOrderID;
        this.paymentMethod = paymentMethod;
        this.supplierInvoiceNumber = supplierInvoiceNumber;
        this.transactionDate = transactionDate;
        this.amountExcludingTax = amountExcludingTax;
        this.taxAmount = taxAmount;
        this.transactionAmount = transactionAmount;
        this.finalizationDate = finalizationDate;
        this.isFinalized = isFinalized;
    }

    public SupplierTransactions() {
    }

    public int getSupplierTransactionID() {
        return supplierTransactionID;
    }

    public void setSupplierTransactionID(int supplierTransactionID) {
        this.supplierTransactionID = supplierTransactionID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(int purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSupplierInvoiceNumber() {
        return supplierInvoiceNumber;
    }

    public void setSupplierInvoiceNumber(String supplierInvoiceNumber) {
        this.supplierInvoiceNumber = supplierInvoiceNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmountExcludingTax() {
        return amountExcludingTax;
    }

    public void setAmountExcludingTax(double amountExcludingTax) {
        this.amountExcludingTax = amountExcludingTax;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getFinalizationDate() {
        return finalizationDate;
    }

    public void setFinalizationDate(String finalizationDate) {
        this.finalizationDate = finalizationDate;
    }

    public int getIsFinalized() {
        return isFinalized;
    }

    public void setIsFinalized(int isFinalized) {
        this.isFinalized = isFinalized;
    }

    @Override
    public String toString() {
        return "SupplierTransactions{" + "supplierTransactionID=" + supplierTransactionID + ", supplierID=" + supplierID + ", transactionType=" + transactionType + ", purchaseOrderID=" + purchaseOrderID + ", paymentMethod=" + paymentMethod + ", supplierInvoiceNumber=" + supplierInvoiceNumber + ", transactionDate=" + transactionDate + ", amountExcludingTax=" + amountExcludingTax + ", taxAmount=" + taxAmount + ", transactionAmount=" + transactionAmount + ", finalizationDate=" + finalizationDate + ", isFinalized=" + isFinalized + '}';
    }
    
    
}
