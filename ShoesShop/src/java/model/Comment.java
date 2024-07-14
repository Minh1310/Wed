/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


import java.util.Date;

/**
 *
 * @author Admin
 */
public class Comment {

    private int id;
    private int userId;
    private int blogId;
    private String context;
    private Date createDate;
    private Date modifyDate;

    public Comment() {
    }

    public Comment(int id, int userId, int blogId, String context, Date createDate, Date modifyDate) {
        this.id = id;
        this.userId = userId;
        this.blogId = blogId;
        this.context = context;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", userId=" + userId + ", blogId=" + blogId + ", context=" + context + ", createDate=" + createDate + ", modifyDate=" + modifyDate + '}';
    }
}
