/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Admin
 */
@Data
public class UserHistory {
     private int id;
    private Integer userId;
    private String roleName;
    private String status;
    private String firstName;
    private String lastName;
    private String telephone;
    private Date modifyAt;
    private String password;
    private User user;
}
