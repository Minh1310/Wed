/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author tungl
 */
public class User {
    private int id;
    private String email;
    private String password;
    private int role_id;
    private int status_id;
    private String first_name;
    private String last_name;
    private boolean gender;
    private String telephone;
    private Date created_at;
    private Date modified_at;
    private String token;
    

    private Roles role;
    private UserStatus usersStatus;
    private UserAddress userAddress;

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public UserStatus getUsersStatus() {
        return usersStatus;
    }

    public void setUsersStatus(UserStatus usersStatus) {
        this.usersStatus = usersStatus;
    }

    public User() {
    }

    public User(String email, String password, int role_id, int status_id, String first_name, String last_name, boolean gender, String telephone, Date created_at, Date modified_at) {
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.status_id = status_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.telephone = telephone;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public User(String email, String password, int role_id, int status_id, String first_name, String last_name, boolean gender, String telephone, Date created_at, Date modified_at, String token) {
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.status_id = status_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.telephone = telephone;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.token = token;
    }

    public User(int id, String email, String password, int role_id, int status_id, String first_name, String last_name, boolean gender, String telephone, Date created_at, Date modified_at, String token) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.status_id = status_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.telephone = telephone;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.token = token;
    }

    public User(int id, String email, String password, int role_id, int status_id, String first_name, String last_name, boolean gender, String telephone, Date created_at, Date modified_at, String token, Roles role, UserStatus usersStatus, UserAddress userAddress) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.status_id = status_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.telephone = telephone;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.token = token;
        this.role = role;
        this.usersStatus = usersStatus;
        this.userAddress = userAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public User(int id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

}
