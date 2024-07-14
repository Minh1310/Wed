
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.Data;

import java.sql.Date;

@Data
public class Brand {
    private int id;
    private int statusId;
    private String name;
    private Date createdAt;
    private Date modifiedAt;

    private BrandStatus brandStatus;

    public Brand() {
    }

    public Brand(int statusId, String name, Date createdAt, Date modifiedAt) {
        this.statusId = statusId;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    
    
}