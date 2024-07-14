/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.Data;

@Data
public class Order {
    private int id;
    private int userId;
    private int statusId;
    private String email;
    private String address;
    private double orderTotal;
    private String recipient;
    private String recipientPhone;
    private Date createdAt;
    private Date modifiedAt;
    private int saleId;
    private String notes;
    
    private User user;
    private OrderStatus orderStatus;

    public Order() {
    }

    
    
    public Order(int id, int userId, int statusId, String email, String address, double orderTotal, String recipient, String recipientPhone, Date createdAt, Date modifiedAt, User user, OrderStatus orderStatus) {
        this.id = id;
        this.userId = userId;
        this.statusId = statusId;
        this.email = email;
        this.address = address;
        this.orderTotal = orderTotal;
        this.recipient = recipient;
        this.recipientPhone = recipientPhone;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.user = user;
        this.orderStatus = orderStatus;
    }
    
    
}