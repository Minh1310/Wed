/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartItem;
import model.Product;
import util.Filter;

/**
 *
 * @author Nhat Anh
 */
public class CartItemDAO extends DBContext{
    
    public boolean add(int idShoppingSession, Product product, int quantity){
        if(quantity > product.getQuantity()){
            return false;
        }
        try {
             String sql = """
                          INSERT INTO [dbo].[cart_item]
                                     ([session_id]
                                     ,[product_id]
                                     ,[quantity]
                                     ,[created_at]
                                     ,[modified_at])
                               VALUES
                                     (?
                                     ,?
                                     ,?
                                     ,GETDATE()
                                     ,GETDATE())""";
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setInt(1,idShoppingSession);
            pre.setInt(2,product.getId());
            pre.setInt(3,quantity);
            return pre.executeUpdate()==1;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public CartItem checkExistInSession(int idShoppingSession, int idProduct){
        // TO DO check product exist
        String sql = """
                     SELECT 
                     id, [quantity]
                       FROM [dbo].[cart_item]
                       where session_id = ?
                       and product_id = ?""";
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setInt(1,idShoppingSession);
            pre.setInt(2,idProduct);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                return new CartItem(rs.getInt(1), 0
                        , rs.getInt(2), null);
            }           
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean update(int idCartItem, int quantity){
        String sql = """
                     UPDATE [dbo].[cart_item]
                     SET 
                     [quantity] = ?
                     ,[modified_at] = getdate()
                     WHERE cart_item.id = ?""";
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setInt(1,quantity);
            pre.setInt(2,idCartItem);
            return pre.executeUpdate()==1;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean remove(int idCartItem){
        String sql = """
                     DELETE FROM [dbo].[cart_item]
                           WHERE cart_item.id = ?""";
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setInt(1,idCartItem);
            return pre.executeUpdate()==1;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public List<CartItem> getAllBySession(int idShoppingSession){
        List<CartItem> ls = new LinkedList<>();
        ProductDAO productDAO = new ProductDAO();
        String sql = """
                     SELECT *
                     FROM [dbo].[cart_item]
                     where session_id = ?""";
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            pre.setInt(1,idShoppingSession);
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                ls.add(new CartItem(
                        rs.getInt(1), 
                        rs.getInt(3),
                        rs.getInt(4), 
                        productDAO.getInforProductById(rs.getInt(3))));
            }
            return ls.isEmpty()?null:ls;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void main(String[] args) {
        CartItemDAO  cartItemDAO = new CartItemDAO();
        Product product = new Product();
        product.setId(1);
        product.setQuantity(10);
        List<CartItem> ls = cartItemDAO.getAllBySession(32);
        ls.forEach( a -> {
            System.out.println(a);
        });
    }
}
