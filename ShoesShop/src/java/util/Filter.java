/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Nhat Anh
 */
public class Filter {
    private String service;
    private String nameSearch;
    private String category;
    private String subCategory;
    private String[] color;
    private String[] brand;
    private String[] price;
    private int size;

    public Filter(String nameSearch, String[] color, int size) {
        this.nameSearch = nameSearch;
        this.color = color;
        this.size = size;
    }

    public Filter() {
    }

    public Filter(String service, String nameSearch, String category, String subCategory, String[] color, String[] brand, String[] price) {
        this.service = service;
        this.nameSearch = nameSearch;
        this.category = category;
        this.subCategory = subCategory;
        this.color = color;
        this.brand = brand;
        this.price = price;
    }

    
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public String[] getBrand() {
        return brand;
    }

    public void setBrand(String[] brand) {
        this.brand = brand;
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }   
}
