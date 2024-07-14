/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.Data;
/**
 *
 * @author Admin
 */
@Data
public class SubCategory {
    private int id;
    private int category_id;
    private String name;

    public SubCategory() {
    }

    public SubCategory(int id, int category_id, String name) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
    }
    
    
}
