/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;
import java.util.List;
import lombok.Data;
import model.Brand;
import model.Discount;
import model.ProductStatus;

/**
 *
 * @author Admin
 */
@Data
public class ProductDTO {
    private int id;
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
    
    private Brand brand;
    private Discount discount;
    private ProductStatus productStatus;
    
    private int categoryId;
    private int[] subCategoryId;

    public ProductDTO() {
    }

    public ProductDTO(int discountId, int statusId, int brandId, String name, float price, String description, String img1, String img2) {
        this.discountId = discountId;
        this.statusId = statusId;
        this.brandId = brandId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img1 = img1;
        this.img2 = img2;
    }

    public ProductDTO(int discountId, int statusId, int brandId, String name, float price, String size, String color, String description, Date createdAt, Date modifiedAt) {
        this.discountId = discountId;
        this.statusId = statusId;
        this.brandId = brandId;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    
    

    public ProductDTO(int discountId, int statusId, int brandId, String name, float price, String size, String color, String description, String img1, String img2, Date createdAt, Date modifiedAt) {
        this.discountId = discountId;
        this.statusId = statusId;
        this.brandId = brandId;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.description = description;
        this.img1 = img1;
        this.img2 = img2;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    
    

    public ProductDTO(int discountId, int statusId, int brandId, String name, float price, String description, String img1, String img2, int categoryId, int[] subCategoryId) {
        this.discountId = discountId;
        this.statusId = statusId;
        this.brandId = brandId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.img1 = img1;
        this.img2 = img2;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }
    
    
}
