/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tungl
 */
public class BlogTag {
    
    private int tagId;
    private int blogId;
    private Tag tag;

    public BlogTag() {
    }

    public BlogTag(int tagId, int blogId) {
        this.tagId = tagId;
        this.blogId = blogId;
    }
    
    public Tag getTag(){
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
    
    @Override
    public String toString() {
        return "BlogTag{" + "tagId=" + tagId + ", blogId=" + blogId + '}';
    }
}
