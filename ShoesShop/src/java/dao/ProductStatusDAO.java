/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProductStatus;

/**
 *
 * @author Admin
 */
public class ProductStatusDAO extends DBContext {

    // Method to retrieve all product status
    public List<ProductStatus> getAll() throws SQLException {
        List<ProductStatus> listProductStatus = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // SQL query to select all categories
            String query = "SELECT [id]\n"
                    + "      ,[name]\n"
                    + "  FROM [dbo].[product_status]";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Populate the list with Category objects
            while (resultSet.next()) {
                ProductStatus productStatus = new ProductStatus();
                productStatus.setId(resultSet.getInt("id"));
                productStatus.setName(resultSet.getString("name"));

                listProductStatus.add(productStatus);
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

        return listProductStatus;
    }

    //Get status name by id
    public ProductStatus getStatusByID(int id) {
        String sql = "SELECT [id]\n"//1
                + "      ,[name]\n"//2
                + "  FROM [dbo].[product_status]\n"
                + "  WHERE [id] =?";//3

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ProductStatus ps = new ProductStatus();
                ps.setId(rs.getInt(1));
                ps.setName(rs.getString(2));
                
                return ps;
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception properly, maybe rethrow or return an error code
        }

        return null;
    }
    
    public static void main(String[] args) {
        ProductStatusDAO psdb = new ProductStatusDAO();
        System.out.println(psdb.getStatusByID(1));
    }
}
