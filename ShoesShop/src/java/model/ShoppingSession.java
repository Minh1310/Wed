
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import lombok.Data;

import java.sql.Date;

@Data
public class ShoppingSession {
    private int id;
    private int userId;
    private int total;
    private Date createAt;
    private Date modifiedAt;

    private User user;

}

