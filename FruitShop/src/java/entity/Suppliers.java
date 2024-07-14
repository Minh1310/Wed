/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nhat Anh
 */
public class Suppliers {

    private int supplierID;
    private String supplierName;
    private Integer supplierCategoryID;
    private String deliveryMethod;
    private String deliveryCity;
    private String supplierReference;
    private String bankAccountName;
    private String bankAccountBranch;
    private String bankAccountCode;
    private String bankAccountNumber;
    private String bankInternationalCode;
    private Integer paymentDays;
    private String phoneNumber;
    private String faxNumber;
    private String websiteURL;
    private String deliveryAddressLine1;
    private String deliveryAddressLine2;
    private String deliveryPostalCode;

    public Suppliers() {
    }

    public Suppliers(int supplierID, String supplierName, Integer supplierCategoryID, String deliveryMethod, String deliveryCity, String supplierReference, String bankAccountName, String bankAccountBranch, String bankAccountCode, String bankAccountNumber, String bankInternationalCode, Integer paymentDays, String phoneNumber, String faxNumber, String websiteURL, String deliveryAddressLine1, String deliveryAddressLine2, String deliveryPostalCode) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierCategoryID = supplierCategoryID;
        this.deliveryMethod = deliveryMethod;
        this.deliveryCity = deliveryCity;
        this.supplierReference = supplierReference;
        this.bankAccountName = bankAccountName;
        this.bankAccountBranch = bankAccountBranch;
        this.bankAccountCode = bankAccountCode;
        this.bankAccountNumber = bankAccountNumber;
        this.bankInternationalCode = bankInternationalCode;
        this.paymentDays = paymentDays;
        this.phoneNumber = phoneNumber;
        this.faxNumber = faxNumber;
        this.websiteURL = websiteURL;
        this.deliveryAddressLine1 = deliveryAddressLine1;
        this.deliveryAddressLine2 = deliveryAddressLine2;
        this.deliveryPostalCode = deliveryPostalCode;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getSupplierCategoryID() {
        return supplierCategoryID;
    }

    public void setSupplierCategoryID(Integer supplierCategoryID) {
        this.supplierCategoryID = supplierCategoryID;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getSupplierReference() {
        return supplierReference;
    }

    public void setSupplierReference(String supplierReference) {
        this.supplierReference = supplierReference;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountBranch() {
        return bankAccountBranch;
    }

    public void setBankAccountBranch(String bankAccountBranch) {
        this.bankAccountBranch = bankAccountBranch;
    }

    public String getBankAccountCode() {
        return bankAccountCode;
    }

    public void setBankAccountCode(String bankAccountCode) {
        this.bankAccountCode = bankAccountCode;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankInternationalCode() {
        return bankInternationalCode;
    }

    public void setBankInternationalCode(String bankInternationalCode) {
        this.bankInternationalCode = bankInternationalCode;
    }

    public Integer getPaymentDays() {
        return paymentDays;
    }

    public void setPaymentDays(Integer paymentDays) {
        this.paymentDays = paymentDays;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getDeliveryAddressLine1() {
        return deliveryAddressLine1;
    }

    public void setDeliveryAddressLine1(String deliveryAddressLine1) {
        this.deliveryAddressLine1 = deliveryAddressLine1;
    }

    public String getDeliveryAddressLine2() {
        return deliveryAddressLine2;
    }

    public void setDeliveryAddressLine2(String deliveryAddressLine2) {
        this.deliveryAddressLine2 = deliveryAddressLine2;
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    public void setDeliveryPostalCode(String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    @Override
    public String toString() {
        return "Suppliers{" + "supplierID=" + supplierID + ", supplierName=" 
                + supplierName + ", supplierCategoryID=" + supplierCategoryID 
                + ", deliveryMethod=" + deliveryMethod + ", deliveryCity=" 
                + deliveryCity + ", supplierReference=" + supplierReference 
                + ", bankAccountName=" + bankAccountName + ", bankAccountBranch=" 
                + bankAccountBranch + ", bankAccountCode=" + bankAccountCode 
                + ", bankAccountNumber=" + bankAccountNumber 
                + ", bankInternationalCode=" + bankInternationalCode 
                + ", paymentDays=" + paymentDays + ", phoneNumber=" 
                + phoneNumber + ", faxNumber=" + faxNumber + ", websiteURL=" 
                + websiteURL + ", deliveryAddressLine1=" + deliveryAddressLine1 
                + ", deliveryAddressLine2=" + deliveryAddressLine2 
                + ", deliveryPostalCode=" + deliveryPostalCode + '}';
    }
    
    
}
