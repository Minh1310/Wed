/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nhat Anh
 */
public class Products {
    private int productID;
    private String productName;
    private int supplierID;
    private String color, packageType, size;
    private double taxRate, unitPrice, recommendedRetailPrice, typicalWeightPerUnit;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getRecommendedRetailPrice() {
        return recommendedRetailPrice;
    }

    public void setRecommendedRetailPrice(int recommendedRetailPrice) {
        this.recommendedRetailPrice = recommendedRetailPrice;
    }

    public double getTypicalWeightPerUnit() {
        return typicalWeightPerUnit;
    }

    public void setTypicalWeightPerUnit(int typicalWeightPerUnit) {
        this.typicalWeightPerUnit = typicalWeightPerUnit;
    }

    public Products(int productID, String productName, int supplierID, 
            String color, String packageType, String size, 
            double taxRate, double unitPrice, double recommendedRetailPrice, 
            double typicalWeightPerUnit) {
        this.productID = productID;
        this.productName = productName;
        this.supplierID = supplierID;
        this.color = color;
        this.packageType = packageType;
        this.size = size;
        this.taxRate = taxRate;
        this.unitPrice = unitPrice;
        this.recommendedRetailPrice = recommendedRetailPrice;
        this.typicalWeightPerUnit = typicalWeightPerUnit;
    }

    public Products() {
    }

    @Override
    public String toString() {
        return "Products{" + "productID=" + productID + ", productName=" 
                + productName + ", supplierID=" + supplierID + ", color=" 
                + color + ", packageType=" + packageType + ", size=" + size 
                + ", taxRate=" + taxRate + ", unitPrice=" + unitPrice 
                + ", recommendedRetailPrice=" + recommendedRetailPrice 
                + ", typicalWeightPerUnit=" + typicalWeightPerUnit + '}';
    }
    
}
