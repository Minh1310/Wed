/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author 84355
 */
import controller.*;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BlogTag;
import model.Tag;

public class BlogTagDAO extends DBContext {

    public List<BlogTag> getAll() {
        List<BlogTag> blogTags = new ArrayList<>();
        String query = "SELECT tagId, blogId FROM blog_tags";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int tagId = resultSet.getInt("tagId");
                int blogId = resultSet.getInt("blogId");
                Tag tag = new TagsDAO().getById(tagId); // Assuming you have a TagDAO
                BlogTag blogTag = new BlogTag();
                blogTag.setBlogId(blogId);
                blogTag.setTag(tag);
                blogTag.setTagId(tagId);

                blogTags.add(blogTag);
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
        }

        return blogTags;
    }

    public BlogTag getByID(int blogId, int tagId) {
        String query = "SELECT tagId FROM blog_tags WHERE blogId = ? AND tagId = ?";
        BlogTag blogTag = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, blogId);
            statement.setInt(2, tagId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Tag tag = new TagsDAO().getById(tagId); // Assuming you have a TagDAO
                    blogTag.setBlogId(blogId);
                    blogTag.setTag(tag);
                    blogTag.setTagId(tagId);
                }
            }
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
        }

        return blogTag;
    }
}
