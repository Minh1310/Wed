/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import util.CategoryAggregation;

public class CategoryDAO extends DBContext {

    // Method to retrieve a Category by its ID
    public Category getById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Category category = null;

        try {
            // SQL query to select a category by its ID
            String query = "SELECT id, status_id, name, created_at, modified_at FROM categories WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, create a Category object
            if (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setStatusId(resultSet.getInt("status_id"));
                category.setName(resultSet.getString("name"));
                category.setCreateAt(resultSet.getDate("created_at"));
                category.setModifiedAt(resultSet.getDate("modified_at"));
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return category;
    }

    // Method to retrieve a Category ID by its name
    public int getCategoryId(String name) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int categoryId = -1;

        try {
            // SQL query to select a category ID by its name
            String query = "SELECT id FROM categories WHERE name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, retrieve the category ID
            if (resultSet.next()) {
                categoryId = resultSet.getInt("id");
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return categoryId;
    }

    // Method to retrieve all categories
    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // SQL query to select all categories
            String query = "SELECT id, status_id, name, created_at, modified_at FROM categories";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Populate the list with Category objects
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setStatusId(resultSet.getInt("status_id"));
                category.setName(resultSet.getString("name"));
                category.setCreateAt(resultSet.getDate("created_at"));
                category.setModifiedAt(resultSet.getDate("modified_at"));
                categories.add(category);
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return categories;
    }

    // Method to add a new category
    public void add(Category category) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to insert a new category
            String query = "INSERT INTO categories (status_id, name, created_at, modified_at) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, category.getStatusId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setDate(3, (Date) category.getCreateAt());
            preparedStatement.setDate(4, (Date) category.getModifiedAt());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to update an existing category
    public void update(Category category) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to update an existing category
            String query = "UPDATE categories SET status_id=?, name=?, modified_at=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, category.getStatusId());
            preparedStatement.setString(2, category.getName());
            preparedStatement.setDate(3, (Date) category.getModifiedAt());
            preparedStatement.setInt(4, category.getId());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to delete a category
    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to delete a category by its ID
            String query = "DELETE FROM categories WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    public CategoryStatus getStatusById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CategoryStatus categoryStatus = null;

        try {
            // SQL query to select a category status by its ID
            String query = "SELECT id, name FROM category_status WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, create a CategoryStatus object
            if (resultSet.next()) {
                categoryStatus = new CategoryStatus();
                categoryStatus.setId(resultSet.getInt("id"));
                categoryStatus.setName(resultSet.getString("name"));
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return categoryStatus;
    }

    // Method to retrieve all CategoryStatus entries
    public List<CategoryStatus> getAllCategory() throws SQLException {
        List<CategoryStatus> statuses = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // SQL query to select all category statuses
            String query = "SELECT id, name FROM category_status";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Populate the list with CategoryStatus objects
            while (resultSet.next()) {
                CategoryStatus status = new CategoryStatus();
                status.setId(resultSet.getInt("id"));
                status.setName(resultSet.getString("name"));
                statuses.add(status);
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return statuses;
    }

    // Method to add a new category status
    public void add(CategoryStatus categoryStatus) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to insert a new category status
            String query = "INSERT INTO category_status (name) VALUES (?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoryStatus.getName());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to update an existing category status
    public void update(CategoryStatus categoryStatus) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to update an existing category status
            String query = "UPDATE category_status SET name=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, categoryStatus.getName());
            preparedStatement.setInt(2, categoryStatus.getId());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to delete a category status
    public void deleteCategory(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to delete a category status by its ID
            String query = "DELETE FROM category_status WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    
    /**
     * Use to get all categories have status is active
     * 
     * @return list categories have status is active
     */
    public List<Category> getAllByStatus(){
        List<Category> list = new LinkedList<>();
        try {
            String sql = """
                        select c.id, c.name from Categories as c
                        join category_status as s on c.status_id = s.id
                        where s.name = 'Active'""";
            PreparedStatement pre = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {                
                list.add(new Category(rs.getInt(1), rs.getString(2),
                        null, null));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    /**
     * Use to get hierarchy category
     *
     * @return map hierarchy category
     */
    public Map<Category, CategoryAggregation> getHierarchyCategory() {
        Map<Category, CategoryAggregation> listMap = new HashMap<>();
        String sql = """
                     select categories.name, subcategories.name
                     from categories full outer join subcategories
                     on categories.id = subcategories.category_id""";
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Category key = new Category(rs.getString(1));
                CategoryAggregation categoryAggregation
                        = listMap.computeIfAbsent(key, k -> new CategoryAggregation());
                categoryAggregation.nameSubCategories.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return listMap;
    }

    /**
     * Use to get category of given name
     * 
     * @param name name of product
     * @return list string name category
     */
    public List<String> getCategoryByNameProduct(String name) {
        Set<String> set = new HashSet<>();
        List<String> categorys = new LinkedList<>();
        List<String> categorieses = new LinkedList<>();
        String sql = """
                     select categories.name, subcategories.name
                     from categories full outer join subcategories
                     on categories.id = subcategories.category_id
                     full outer join product_subcate
                     on product_subcate.subcategory_id = subcategories.id
                     full outer join products
                     on products.id = product_subcate.product_id
                     where products.name like ?""";
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setString(1, name);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                categorys.add(rs.getString(1));
                categorieses.add(rs.getString(2));
            }
            set.addAll(categorys);
            set.addAll(categorieses);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return new LinkedList<>(set);
    }


    public static void main(String[] args) {
        CategoryDAO cDAO = new CategoryDAO();
        List<Category> categories = new ArrayList<>();
        try{
            categories = cDAO.getAll();
        }catch(SQLException e){
            System.out.println(e);
        }
        System.out.println(categories);
    }
}
