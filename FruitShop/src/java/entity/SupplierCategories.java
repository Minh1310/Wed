/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Nhat Anh
 */
public class SupplierCategories {

    private int supplierCategoryID;
    private String supplierCategoryName;

    public SupplierCategories() {
    }

    public SupplierCategories(int supplierCategoryID, String supplierCategoryName) {
        this.supplierCategoryID = supplierCategoryID;
        this.supplierCategoryName = supplierCategoryName;
    }

    public int getSupplierCategoryID() {
        return supplierCategoryID;
    }

    public void setSupplierCategoryID(int supplierCategoryID) {
        this.supplierCategoryID = supplierCategoryID;
    }

    public String getSupplierCategoryName() {
        return supplierCategoryName;
    }

    public void setSupplierCategoryName(String supplierCategoryName) {
        this.supplierCategoryName = supplierCategoryName;
    }

    @Override
    public String toString() {
        return "SupplierCategories{" + "supplierCategoryID=" + supplierCategoryID + ", supplierCategoryName=" + supplierCategoryName + '}';
    }
    
}
