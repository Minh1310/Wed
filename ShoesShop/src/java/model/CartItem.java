/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import lombok.Data;

import java.sql.Date;

@Data
public class CartItem {
    private int id;
    private int sessionId;
    private int productId;
    private int quantity;
    private Date createAt;
    private Date modifiedAt;

    private ShoppingSession shopingSession;
    private Product product;

    public CartItem(int id, int sessionId, int productId, int quantity, Date createAt, Date modifiedAt, ShoppingSession shopingSession, Product product) {
        this.id = id;
        this.sessionId = sessionId;
        this.productId = productId;
        this.quantity = quantity;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.shopingSession = shopingSession;
        this.product = product;
    }

    public CartItem(int id, int productId, int quantity, Product product) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public CartItem() {
    }
    
    public boolean checkExist(int[] arr){
        if(arr==null||arr.length ==0){
            return false;
        }             
        for(int a : arr){
            if(a == id){
                return true;
            }
        }
        return false;
    }
    
    
    
    
}