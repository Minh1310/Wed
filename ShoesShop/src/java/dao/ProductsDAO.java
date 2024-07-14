/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import dto.ProductDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.UserStatus;

/**
 *
 * @author Admin
 */
public class ProductsDAO extends DBContext {

    // get all product
    public List<ProductDTO> getAllProduct() {
        List<ProductDTO> list = new ArrayList<>();
        String sql = "SELECT DISTINCT P.[discount_id]\n"//1
                + "      ,P.[status_id]\n"//2
                + "      ,P.[brand_id]\n"//3
                + "      ,P.[name]\n"//4
                + "	  ,P.[price]\n"//5
                + "      ,P.[description]\n"//6
                + "      ,P.[img1]\n"//7
                + "      ,P.[img2]\n"//8
                + "	  ,C.[id] AS category_id\n"//9
                + "  FROM [dbo].[products] AS P\n"
                + "  FULL JOIN [dbo].[product_subcate] AS PS\n"
                + "  ON P.id = PS.product_id\n"
                + "  FULL JOIN [dbo].[subcategories] AS S\n"
                + "  ON PS.subcategory_id = S.id\n"
                + "  FULL JOIN [dbo].[categories] AS C\n"
                + "  ON C.id = S.category_id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductDTO p = new ProductDTO();

                p.setDiscountId(rs.getInt(1));
                p.setStatusId(rs.getInt(2));
                p.setBrandId(rs.getInt(3));
                p.setName(rs.getString(4));
                p.setPrice(rs.getFloat(5));
                p.setDescription(rs.getString(6));
                p.setImg1(rs.getString(7));
                p.setImg2(rs.getString(8));
                p.setCategoryId(rs.getInt(9));

                SubCategoryDAO sdb = new SubCategoryDAO();
                p.setSubCategoryId(sdb.getSubCategoryByProduct(p.getName()));

                if (p.getName() != null) {
                    list.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ProductDTO> filterProductAndSearch(String category, String subcategory, String search, Integer status) {
        List<ProductDTO> listProduct = new ArrayList<>();
        String sql = "SELECT DISTINCT P.[discount_id]\n"
                + "      ,P.[status_id]\n"
                + "      ,P.[brand_id]\n"
                + "      ,P.[name]\n"
                + "	 ,P.[price]\n"
                + "      ,P.[description]\n"
                + "      ,P.[img1]\n"
                + "      ,P.[img2]\n"
                + "	 ,C.[id] AS category_id\n"
                + "  FROM [dbo].[products] AS P\n"
                + "  FULL JOIN [dbo].[product_subcate] AS PS\n"
                + "  ON P.id = PS.product_id\n"
                + "  FULL JOIN [dbo].[subcategories] AS S\n"
                + "  ON PS.subcategory_id = S.id\n"
                + "  FULL JOIN [dbo].[categories] AS C\n"
                + "  ON C.id = S.category_id\n"
                + "  WHERE (C.name LIKE ? OR ? IS NULL)\n"//1,2
                + "		AND (S.name LIKE ? OR ? IS NULL)\n"//3,4
                + "		AND (P.status_id = ? OR ? IS NULL)\n"//5,6
                + "		AND (P.name LIKE ? OR ? IS NULL)";//7,8
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            // Setting parameters for category
            if (category != null && !category.equals("")) {
                statement.setString(1, category + "%");
                statement.setString(2, category);
            } else {
                statement.setNull(1, java.sql.Types.VARCHAR);
                statement.setNull(2, java.sql.Types.VARCHAR);
            }

            // Setting parameters for subcategory
            if (subcategory != null && !subcategory.equals("")) {
                statement.setString(3, "%" + subcategory + "%");
                statement.setString(4, subcategory);
            } else {
                statement.setNull(3, java.sql.Types.VARCHAR);
                statement.setNull(4, java.sql.Types.VARCHAR);
            }

            // Setting parameters for search
            if (search != null && !search.equals("")) {
                statement.setString(7, "%" + search + "%");
                statement.setString(8, search);
            } else {
                statement.setNull(7, java.sql.Types.VARCHAR);
                statement.setNull(8, java.sql.Types.VARCHAR);
            }

            // Setting parameters for status
            if (status != 0) {
                statement.setInt(5, status);
                statement.setInt(6, status);
            } else {
                statement.setNull(5, java.sql.Types.INTEGER);
                statement.setNull(6, java.sql.Types.INTEGER);
            }

            // Execute query and process results
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ProductDTO p = new ProductDTO();

                    p.setDiscountId(resultSet.getInt(1));
                    p.setStatusId(resultSet.getInt(2));
                    p.setBrandId(resultSet.getInt(3));
                    p.setName(resultSet.getString(4));
                    p.setPrice(resultSet.getFloat(5));
                    p.setDescription(resultSet.getString(6));
                    p.setImg1(resultSet.getString(7));
                    p.setImg2(resultSet.getString(8));
                    p.setCategoryId(resultSet.getInt(9));

                    SubCategoryDAO sdb = new SubCategoryDAO();
                    p.setSubCategoryId(sdb.getSubCategoryByProduct(p.getName()));

                    if (p.getName() != null) {
                        listProduct.add(p);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ, có thể throw hoặc log
        }
        return listProduct;
    }

    public void updateProductStatus(int status, String productName) {
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [status_id] = ?\n"//1
                + " WHERE [name] = ?";//2
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, status);
            st.setString(2, productName);

            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
    
    public void updateProductThumbnail(String img, String productName) {
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [img1] = ?\n"//1
                + " WHERE [name] = ?";//2
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, img);
            st.setString(2, productName);

            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
    
    public void updateProductImage(String img, String productName) {
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [img2] = ?\n"//1
                + " WHERE [name] = ?";//2
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, img);
            st.setString(2, productName);

            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    // get product by name
    public ProductDTO getProductByName(String productName) {

        String sql = "SELECT DISTINCT P.[discount_id]\n"//1
                + "      ,P.[status_id]\n"//2
                + "      ,P.[brand_id]\n"//3
                + "      ,P.[name]\n"//4
                + "	  ,P.[price]\n"//5
                + "      ,P.[description]\n"//6
                + "      ,P.[img1]\n"//7
                + "      ,P.[img2]\n"//8
                + "	  ,C.[id] AS category_id\n"//9
                + "  FROM [dbo].[products] AS P\n"
                + "  FULL JOIN [dbo].[product_subcate] AS PS\n"
                + "  ON P.id = PS.product_id\n"
                + "  FULL JOIN [dbo].[subcategories] AS S\n"
                + "  ON PS.subcategory_id = S.id\n"
                + "  FULL JOIN [dbo].[categories] AS C\n"
                + "  ON C.id = S.category_id\n"
                + "  WHERE P.name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ProductDTO p = new ProductDTO();

                p.setDiscountId(rs.getInt(1));
                p.setStatusId(rs.getInt(2));
                p.setBrandId(rs.getInt(3));
                p.setName(rs.getString(4));
                p.setPrice(rs.getFloat(5));
                p.setDescription(rs.getString(6));
                p.setImg1(rs.getString(7));
                p.setImg2(rs.getString(8));
                p.setCategoryId(rs.getInt(9));

                SubCategoryDAO sdb = new SubCategoryDAO();
                p.setSubCategoryId(sdb.getSubCategoryByProduct(p.getName()));

                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // get list product by name
    public List<ProductDTO> getListProductByName(String productName) {
        List<ProductDTO> listProduct = new ArrayList<>();
        String sql = "SELECT DISTINCT C.[id] AS category_id\n"//1
                + "	  ,P.[id]\n"//2
                + "	  ,P.[discount_id]\n"//3
                + "      ,P.[status_id]\n"//4
                + "      ,P.[brand_id]\n"//5
                + "      ,P.[name]\n"//6
                + "	  ,P.[quantity]\n"//7
                + "	  ,P.[price]\n"//8
                + "	  ,P.[size]\n"//9
                + "	  ,P.[color]\n"//10
                + "      ,P.[description]\n"//11
                + "      ,P.[img1]\n"//12
                + "      ,P.[img2]\n"//13
                + "  FROM [dbo].[products] AS P\n"
                + "  FULL JOIN [dbo].[product_subcate] AS PS\n"
                + "  ON P.id = PS.product_id\n"
                + "  FULL JOIN [dbo].[subcategories] AS S\n"
                + "  ON PS.subcategory_id = S.id\n"
                + "  FULL JOIN [dbo].[categories] AS C\n"
                + "  ON C.id = S.category_id\n"
                + "  WHERE P.name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductDTO p = new ProductDTO();

                p.setCategoryId(rs.getInt(1));
                p.setId(rs.getInt(2));
                p.setDiscountId(rs.getInt(3));
                p.setStatusId(rs.getInt(4));
                p.setBrandId(rs.getInt(5));
                p.setName(rs.getString(6));
                p.setQuantity(rs.getInt(7));
                p.setPrice(rs.getFloat(8));
                p.setSize(rs.getString(9));
                p.setColor(rs.getString(10));
                p.setDescription(rs.getString(11));
                p.setImg1(rs.getString(12));
                p.setImg2(rs.getString(13));

                SubCategoryDAO sdb = new SubCategoryDAO();
                p.setSubCategoryId(sdb.getSubCategoryByProduct(p.getName()));

                listProduct.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listProduct;
    }

    // get list color by product name
    public List<String> getListColorByProduct(String productName) {
        List<String> listColor = new ArrayList<>();
        String sql = "SELECT DISTINCT [color]\n"//1
                + "  FROM [dbo].[products]\n"
                + "  WHERE [name] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                listColor.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listColor;
    }

    public void deleteProductSubCate(String productName) {
        String sql = "DELETE FROM [dbo].[product_subcate]\n"
                + "      WHERE product_id IN (SELECT [id]\n"
                + "	  FROM [dbo].[products]\n"
                + "	  WHERE [name] = ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, productName);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertProductSubCate(int productId, int subCategoryId) {
        String sql = "INSERT INTO [dbo].[product_subcate]\n"
                + "           ([product_id]\n"
                + "           ,[subcategory_id])\n"
                + "     VALUES (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, productId);
            st.setInt(2, subCategoryId);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateProduct(ProductDTO p, String oldProductName) {
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [discount_id] = ?\n"//1
                + "      ,[status_id] = ?\n"//2
                + "      ,[brand_id] = ?\n"//3
                + "      ,[name] = ?\n"//4
                + "      ,[price] = ?\n"//5
                + "      ,[description] = ?\n"//6
                + "      ,[img1] = ?\n"//7
                + "      ,[img2] = ?\n"//8
                + " WHERE [name] = ?";//9
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, p.getDiscountId());
            st.setInt(2, p.getStatusId());
            st.setInt(3, p.getBrandId());
            st.setString(4, p.getName());
            st.setFloat(5, p.getPrice());
            st.setString(6, p.getDescription());
            st.setString(7, p.getImg1());
            st.setString(8, p.getImg2());
            st.setString(9, oldProductName);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateQuantityProduct(int quantity, String name, String size, String color) {
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [quantity] = ?\n"//1
                + " WHERE [name] = ? \n"//2
                + " AND [color] = ?\n"//3
                + " AND [size] = ?";//4
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, quantity);
            st.setString(2, name);
            st.setString(3, color);
            st.setString(4, size);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void insertProduct(ProductDTO p) {
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([discount_id]\n"//1
                + "           ,[status_id]\n"//2
                + "           ,[brand_id]\n"//3
                + "           ,[name]\n"//4
                + "           ,[price]\n"//5
                + "           ,[size]\n"//6
                + "           ,[color]\n"//7
                + "           ,[description]\n"//8
                + "           ,[created_at]\n"//9
                + "           ,[modified_at])\n"//10
                + "     VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, p.getDiscountId());
            st.setInt(2, p.getStatusId());
            st.setInt(3, p.getBrandId());
            st.setString(4, p.getName());
            st.setFloat(5, p.getPrice());
            st.setString(6, p.getSize());
            st.setString(7, p.getColor());
            st.setString(8, p.getDescription());
            
            java.util.Date utilDate = p.getCreatedAt();
            java.sql.Date created_at = new java.sql.Date(utilDate.getTime());
            st.setDate(9, created_at);
            
            utilDate = p.getModifiedAt();
            java.sql.Date modified_at = new java.sql.Date(utilDate.getTime());
            st.setDate(10, modified_at);

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ProductsDAO p = new ProductsDAO();
        System.out.println(p.filterProductAndSearch("Men", "", "", 0));
    }
}
