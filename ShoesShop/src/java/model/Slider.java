/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nhat Anh
 */
public class Slider {
    private int id;
    private int user_id;
    private String title;
    private String content;
    private String image;
    private String backLink;
    private String status;

    public Slider(int id, int user_id, String title, String content, String image, String backLink, String status) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.backLink = backLink;
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Slider() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBackLink() {
        return backLink;
    }

    public void setBackLink(String backLink) {
        this.backLink = backLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Slider{" + "id=" + id + ", user_id=" + user_id + ", title=" + title + ", content=" + content + ", image=" + image + ", backLink=" + backLink + ", status=" + status + '}';
    }

   
    
}
