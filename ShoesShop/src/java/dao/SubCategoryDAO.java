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
import model.Category;
import model.SubCategory;

/**
 *
 * @author Admin
 */
public class SubCategoryDAO extends DBContext {

    // Method to retrieve all subcategories
    public List<SubCategory> getAll() throws SQLException {
        List<SubCategory> subCategories = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // SQL query to select all categories
            String query = "SELECT [id]\n"
                    + "      ,[category_id]\n"
                    + "      ,[name]\n"
                    + "  FROM [dbo].[subcategories]";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            // Populate the list with Category objects
            while (resultSet.next()) {
                SubCategory subCategory = new SubCategory();
                subCategory.setId(resultSet.getInt("id"));
                subCategory.setCategory_id(resultSet.getInt("category_id"));
                subCategory.setName(resultSet.getString("name"));
                subCategories.add(subCategory);
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

        return subCategories;
    }

    // Get subcategory by product
    public int[] getSubCategoryByProduct(String productName) {
        String sql = "SELECT DISTINCT S.[id] "
                + "FROM [dbo].[subcategories] AS S "
                + "JOIN [dbo].[product_subcate] AS PS "
                + "ON S.id = PS.subcategory_id "
                + "JOIN [dbo].[products] AS P "
                + "ON P.id = PS.product_id "
                + "WHERE P.name = ?";
        ArrayList<Integer> subcategoryIds = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                subcategoryIds.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception properly, maybe rethrow or return an error code
        }
        // Convert ArrayList to int[]
        int[] result = new int[subcategoryIds.size()];
        for (int i = 0; i < subcategoryIds.size(); i++) {
            result[i] = subcategoryIds.get(i);
        }
        return result;
    }
    
    
    public static void main(String[] args) {
        SubCategoryDAO s = new SubCategoryDAO();
        System.out.println(s.getSubCategoryByProduct("Running Shoes"));
    }
}
