/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author 84355
 */
public class Blog {

    private int id;
    private int user_id;
    private int blog_cate_id;
    private String title;
    private String context;
    private String cover_img;
    private String main_img;
    private String description;
    private Date created_at;
    private Date modified_at;

    private BlogCategories blogCategory;
    private BlogTag blogTag;

    public Blog() {

    }

    public Blog(int id, int user_id, int blog_cate_id, String title, String context, String cover_img, String main_img, String description, Date created_at, Date modified_at) {
        this.id = id;
        this.user_id = user_id;
        this.blog_cate_id = blog_cate_id;
        this.title = title;
        this.context = context;
        this.cover_img = cover_img;
        this.main_img = main_img;
        this.description = description;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public BlogCategories getBlogCategory() {
        return blogCategory;
    }

    public void setBlogCategory(BlogCategories blogCategory) {
        this.blogCategory = blogCategory;
    }

    public BlogTag getBlogTag() {
        return blogTag;
    }

    public void setBlogTag(BlogTag blogTag) {
        this.blogTag = blogTag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBlog_cate_id() {
        return blog_cate_id;
    }

    public void setBlog_cate_id(int blog_cate_id) {
        this.blog_cate_id = blog_cate_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String getDescription() {
        return description;
    }

    public String getShort() {
        // Split the description into words
        String[] words = description.split("\\s+");

        // Check if the description has less than or equal to 30 words
        if (words.length <= 30) {
            // If so, return the entire description
            return description;
        } else {
            // If not, concatenate the first 30 words and append "..."
            StringBuilder shortDescription = new StringBuilder();
            for (int i = 0; i < 30; i++) {
                shortDescription.append(words[i]).append(" ");
            }
            shortDescription.append("...");
            return shortDescription.toString().trim();
        }
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Blog{" + "id=" + id + ", user_id=" + user_id + ", blog_cate_id=" + blog_cate_id + ", title=" + title + ", context=" + context + ", cover_img=" + cover_img + ", main_img=" + main_img + ", description=" + description + ", created_at=" + created_at + ", modified_at=" + modified_at + '}';
    }

}
