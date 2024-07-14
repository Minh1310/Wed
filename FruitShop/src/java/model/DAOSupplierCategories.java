/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.SupplierCategories;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class DAOSupplierCategories extends DBconnect{
    
    /**
     * 
     * @param sup
     * @return 
     */
    public int add(SupplierCategories sup){
        String sql = """
                    INSERT INTO [dbo].[SupplierCategories]
                                ([SupplierCategoryID]
                                ,[SupplierCategoryName])
                    VALUES(?,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, sup.getSupplierCategoryID());
            pre.setString(2, sup.getSupplierCategoryName());
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplierCategories.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * 
     * @param id 
     * @return  
     */
    public int remove(String id){
        String sql = """
                     DELETE FROM [dbo].[SupplierCategories]
                       WHERE SupplierCategoryID = ? 
                     AND SupplierCategoryID NOT IN (SELECT DISTINCT SupplierID FROM Products)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, Integer.parseInt(id));
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplierCategories.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * 
     * @param sup
     * @return 
     */
    public int update(SupplierCategories sup){
        String sql="""
                    UPDATE [dbo].[SupplierCategories]
                    SET [SupplierCategoryID] = ?
                         ,[SupplierCategoryName] = ?
                    WHERE SupplierCategoryID = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, sup.getSupplierCategoryID());
            pre.setString(2, sup.getSupplierCategoryName());
            pre.setString(3, String.valueOf(sup.getSupplierCategoryID()));
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplierCategories.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * 
     * @param sql 
     * @return  
     */
    public List<SupplierCategories> getAll(String sql){
        List<SupplierCategories> sc = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                sc.add(new SupplierCategories(rs.getInt(1), 
                        rs.getString(2)));
            }
            return sc;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrders.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return sc;
    }
    
    public static void main(String[] args) {
        DAOSupplierCategories dao = new DAOSupplierCategories();
       // =dao.add(new SupplierCategories(11, "k"));
        int n = dao.remove("13");
        if (n > 0) {
            System.out.println("inserted");
        }
    }
}
