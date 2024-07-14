/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import controller.*;
import context.DBContext;
import java.util.List;
import model.Tag;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Blog;
import model.BlogCategories;

/**
 *
 * @author tungl
 */
public class TagsDAO extends DBContext {

    public List<Tag> getAll() {
        List<Tag> tags = new ArrayList<>();
        String query = "SELECT id, name FROM tags";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Tag tag = new Tag(id, name);
                tags.add(tag);
                statement.close();
            }
        } catch (SQLException e) {

        }

        return tags;
    }

    public Tag getById(int id) {
        String query = "SELECT id, name FROM tags WHERE id = ?";
        Tag tag = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    tag = new Tag(id, name);
                    statement.close();
                }
            }
        } catch (SQLException e) {

        }

        return tag;
    }
    
    
    public static void main(String[] args) {
        TagsDAO dao = new TagsDAO();
        System.out.println(dao.getAll());
    }
}
