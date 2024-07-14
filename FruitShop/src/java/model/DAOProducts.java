/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Products;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nhat Anh
 */
public class DAOProducts extends DBconnect {

//    /**
//     * Use to insert product use Statement
//     *
//     * @param pro is information of product
//     * @return 0 if insert false or 1
//     */
//    public int insert(Products pro) {
//        String sql = """
//                     INSERT INTO [dbo].[Products]
//                     ([ProductID],[ProductName],[SupplierID],[Color],[PackageType],
//                     [Size],[TaxRate],[UnitPrice],[RecommendedRetailPrice],
//                     [TypicalWeightPerUnit])
//                      VALUES(""" + pro.getProductID() + ",'" + pro.getProductName()
//                + "'," + pro.getSupplierID() + ",'"
//                + pro.getColor() + "','" + pro.getPackageType() + "','" + pro.getSize()
//                + "'," + pro.getTaxRate() + "," + pro.getUnitPrice()
//                + "," + pro.getRecommendedRetailPrice() + ","
//                + pro.getTypicalWeightPerUnit() + ")";
//
//        System.out.println("x:sql");
//        Statement state;
//        try {
//            state = conn.createStatement();
//            return state.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(DAOProducts.class.getName())
//                    .log(Level.SEVERE, null, ex);
//        }
//        return 0;
//    }

    /**
     * Use to insert product use Prepare Statement
     *
     * @param pro is information of product
     * @return 0 if insert false or 1
     */
    public int add(Products pro) {
        String sql = """
                     INSERT INTO [dbo].[Products]
                     ([ProductID],[ProductName],[SupplierID],[Color],[PackageType],
                     [Size],[TaxRate],[UnitPrice],[RecommendedRetailPrice],
                     [TypicalWeightPerUnit])
                      VALUES(?,?,?,?,?,?,?,?,?,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pro.getProductID());
            pre.setString(2, pro.getProductName());
            pre.setInt(3, pro.getSupplierID());
            pre.setString(4, pro.getColor());
            pre.setString(5, pro.getPackageType());
            pre.setString(6, pro.getSize());
            pre.setDouble(7, pro.getTaxRate());
            pre.setDouble(8, pro.getUnitPrice());
            pre.setDouble(9, pro.getRecommendedRetailPrice());
            pre.setDouble(10, pro.getTypicalWeightPerUnit());
            return pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public int remove(String id) {
        String sql = "delete from Products where ProductID = ? and( ProductID not in "
                + "(select distinct ProductID from PurchaseOrderLines))";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, Integer.parseInt(id));
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    /**
     *
     * @param pro
     * @return
     */
    public int update(Products pro) {
        String sql = """
                    UPDATE [dbo].[Products]
                    SET 
                        [ProductName] = ?
                        ,[SupplierID] = ?
                        ,[Color] = ?
                        ,[PackageType] = ?
                        ,[Size] = ?
                        ,[TaxRate] = ?
                        ,[UnitPrice] = ?
                        ,[RecommendedRetailPrice] = ?
                        ,[TypicalWeightPerUnit] = ?
                    WHERE [ProductID] = ?""";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setString(3, pro.getColor());
            pre.setString(4, pro.getPackageType());
            pre.setString(5, pro.getSize());
            pre.setDouble(6, pro.getTaxRate());
            pre.setDouble(7, pro.getUnitPrice());
            pre.setDouble(8, pro.getRecommendedRetailPrice());
            pre.setDouble(9, pro.getTypicalWeightPerUnit());
            pre.setInt(10, pro.getProductID());
            return pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    

    /**
     *
     * @param sql
     * @return
     */
    public List<Products> getAll(String sql) {
        List<Products> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                int id = rs.getInt(1);
                // int id= rs.getInt(1);
                String pname = rs.getString(2);
                //=rs.getString("ProductName");
                int sid = rs.getInt(3);
                String color = rs.getString(4);
                String packagetype = rs.getString(5);
                String size = rs.getString(6);
                int tax = rs.getInt(7);
                int unitprice = rs.getInt(8);
                int recoment = rs.getInt(9);
                int type = rs.getInt(10);

                Products pro = new Products(id, pname, sid,
                        color, packagetype, size,
                        tax, unitprice,
                        recoment, type);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
//        int n = dao.add(new Products(233, "minh",
//                1, "green", "sjdn",
//                "sgb", 0, 1,
//                1, 1));
//        dao.showProduct("231");

//        System.out.println(n);
//        System.out.println(dao.getValue("SELECT COUNT(ProductID)\n" +
//"FROM Products"));
//        List<Products> rs = dao.getAll("""
//                       SELECT * FROM products
//                       ORDER BY ProductID
//                       OFFSET 24 ROWS
//                       FETCH NEXT 16 ROWS ONLY;""");
//        if (!rs.isEmpty()) {
//            System.out.println("update");
//        }
//        List<Products> ls = dao.getAll("select * from products");
//        rs.forEach( a -> System.out.println(a.toString()));
//          System.out.println(dao.remove(1)>0 ?"Deleted" : "Cant deleted");

        System.out.println();
    }
}
