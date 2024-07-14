/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.Data;



@Data
public class Product {
    private int id;
    private int categoryId;
    private int discountId;
    private int statusId;
    private int brandId;
    private String name;
    private int quantity;
    private float price;
    private String size;
    private String color;
    private String description;
    private String img1;
    private String img2;
    private Date createdAt;
    private Date modifiedAt;
    
    private Ratting ratting;

    private Brand brand;
    private Discount discount;
    private ProductStatus productStatus;
    private Category categories;

    public Product() {
    }

    public Product(String name, float price, String img1, String img2, Ratting rating, Discount discount, ProductStatus productStatus) {
        this.name = name;
        this.price = price;
        this.img1 = img1;
        this.img2 = img2;
        this.ratting = rating;
        this.discount = discount;
        this.productStatus = productStatus;
    }   

    public Product(int id, int categoryId, int discountId, int statusId, int brandId, String name, int quantity, float price, String size, String color, String description, String img1, String img2, Date createdAt, Date modifiedAt) {
        this.id = id;
        this.categoryId = categoryId;
        this.discountId = discountId;
        this.statusId = statusId;
        this.brandId = brandId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
        this.color = color;
        this.description = description;
        this.img1 = img1;
        this.img2 = img2;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Product(int id, String name, int quantity, float price, String size, 
            String color, Discount discount, ProductStatus productStatus) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.size = size;
        this.color = color;
        this.discount = discount;
        this.productStatus = productStatus;
    }
    
    
   
}