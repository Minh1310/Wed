/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.*;

@Data
public class ProductStatus {

    private int id;
    private String name;


    public ProductStatus() {
    }

    public ProductStatus(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public ProductStatus(String name) {
        this.name = name;
    }

    
}
