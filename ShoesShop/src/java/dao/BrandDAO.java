/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.BlogTag;
import model.Brand;
import model.BrandStatus;
import model.Tag;

public class BrandDAO extends DBContext {

    // Method to retrieve all brands
    public List<Brand> getAll() throws SQLException {
        List<Brand> brands = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // SQL query to select all brands
            String query = "SELECT id, status_id, name, created_at, modified_at FROM brands";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Populate the list with Brand objects
            while (resultSet.next()) {
                Brand brand = new Brand();
                brand.setId(resultSet.getInt("id"));
                brand.setStatusId(resultSet.getInt("status_id"));
                brand.setName(resultSet.getString("name"));
                brand.setCreatedAt(resultSet.getDate("created_at"));
                brand.setModifiedAt(resultSet.getDate("modified_at"));
                
                brand.setBrandStatus(getStatusId(brand.getStatusId()));
                brands.add(brand);
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

        return brands;
    }

    // Method to retrieve a Brand by its ID
    public Brand getById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Brand brand = null;

        try {
            // SQL query to select a brand by its ID
            String query = "SELECT id, status_id, name, created_at, modified_at FROM brands WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, create a Brand object
            if (resultSet.next()) {
                brand = new Brand();
                brand.setId(resultSet.getInt("id"));
                brand.setStatusId(resultSet.getInt("status_id"));
                brand.setName(resultSet.getString("name"));
                brand.setCreatedAt(resultSet.getDate("created_at"));
                brand.setModifiedAt(resultSet.getDate("modified_at"));

                brand.setBrandStatus(getStatusId(brand.getStatusId()));
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

        return brand;
    }

    // Method to create a new Brand
    public void create(Brand brand) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to insert a new brand
            String query = "INSERT INTO brands (status_id, name, created_at, modified_at) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, brand.getStatusId());
            preparedStatement.setString(2, brand.getName());
            preparedStatement.setDate(3, brand.getCreatedAt());
            preparedStatement.setDate(4, brand.getModifiedAt());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to update an existing Brand
    public void update(Brand brand) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to update an existing brand
            String query = "UPDATE brands SET status_id=?, name=?, modified_at=? WHERE id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, brand.getStatusId());
            preparedStatement.setString(2, brand.getName());
            preparedStatement.setDate(3, brand.getModifiedAt());
            preparedStatement.setInt(4, brand.getId());
            preparedStatement.executeUpdate();
        } finally {
            // Close resources
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    // Method to delete a Brand
    public void delete(int id) throws SQLException {
        PreparedStatement preparedStatement = null;

        try {
            // SQL query to delete a brand by its ID
            String query = "DELETE FROM brands WHERE id=?";
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

    public List<BrandStatus> getAllStatuses() throws SQLException {
        List<BrandStatus> statuses = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // SQL query to select all brand statuses
            String query = "SELECT id, name FROM brand_status";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Populate the list with BrandStatus objects
            while (resultSet.next()) {
                BrandStatus status = new BrandStatus();
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

    public BrandStatus getStatusId(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BrandStatus bs = null;
        try {
            // SQL query to select status ID by name
            String query = "SELECT id, name FROM brand_status WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            // If a record is found, retrieve the status ID
            if (resultSet.next()) {
                bs = new BrandStatus();
                bs.setId(resultSet.getInt(1));
                bs.setName(resultSet.getString(2));
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

        return bs;
    }
    
    /**
     * Use to get all brand have status is active
     * 
     * @return list brand have brand status is active
     */
    public List<Brand> getAllByStatus(){
        List<Brand> list = new LinkedList<>();
        try {
            String sql = """
                         select c.id, c.name from Brands as c
                                     join brand_status as s on c.status_id = s.id
                                     where s.name = 'Active'""";
            
            PreparedStatement pre = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {                
                list.add(new Brand(rs.getInt(1), rs.getString(2), 
                        null, null));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    

    public static void main(String[] args) {
        BrandDAO bDAO = new BrandDAO();
        try {
            System.out.println(bDAO.getById(1));
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
