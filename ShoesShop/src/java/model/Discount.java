/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author tungl
 */
@Data
public class Discount {
    private int id;
    private String name;
    private String description;
    private int discountPercent;
    private boolean active;
    private Date createdAt;
    private Date modifiedAt;


    public Discount() {
    }

    public Discount(int id, String name, String description, int discountPercent, boolean active, Date createdAt, Date modifiedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discountPercent = discountPercent;
        this.active = active;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Discount(int discountPercent, boolean active) {
        this.discountPercent = discountPercent;
        this.active = active;
    }
    
    
}