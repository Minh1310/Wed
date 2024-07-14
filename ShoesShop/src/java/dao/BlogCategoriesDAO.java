/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.BlogCategories;

public class BlogCategoriesDAO extends DBContext{

    public List<BlogCategories> getAll() {
        List<BlogCategories> categories = new ArrayList<>();
        String query = "SELECT id, name FROM blog_categories";

        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                BlogCategories category = new BlogCategories(id, name);
                categories.add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
        }

        return categories;
    }

    public BlogCategories getByID(int id) {
        String query = "SELECT name FROM blog_categories WHERE id = ?";
        BlogCategories category = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    category = new BlogCategories(id, name);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the exception as needed
        }

        return category;
    }
}
