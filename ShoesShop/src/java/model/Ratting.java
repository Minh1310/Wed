/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.Data;

import java.sql.Date;

@Data
public class Ratting {
    private int id;
    private int product_id;
    private int user_id;
    private int ratting;
    private String comment;
    private Date created_at;
    private Date modified_at;
    private String status;
    private String status_image;

    private User user;
    private Product product;

    public Ratting() {
    }
    
    public Ratting(int ratting) {
        this.ratting = ratting;
    }

}
