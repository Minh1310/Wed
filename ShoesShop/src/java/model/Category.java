/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.Data;

import java.util.Date;

@Data

public class Category {
    private int id;
    private int statusId;
    private String name;
    private Date createAt;
    private Date modifiedAt;

    private CategoryStatus categoryStatus;
    private SubCategories subCategories;

    public Category() {
    }

    
    public Category(String name) {
        this.name = name;
    }
    
    

    public Category(int statusId, String name, Date createAt, Date modifiedAt) {
        this.statusId = statusId;
        this.name = name;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
    
    
}
