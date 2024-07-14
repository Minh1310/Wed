/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Nhat Anh
 */
public class ShoppingSessionDAO extends DBContext{
    
    public boolean addShoppingSessionOfUser(User user){
        try {
            String sql= """
                         INSERT INTO [dbo].[shopping_session]
                                                 ([user_id]
                                                 ,[total]
                                                 ,[created_at]
                                                 ,[modified_at])
                                           VALUES(
                                                 ?
                                                 ,?
                                                 ,GETDATE()
                                                 ,GETDATE())""";
            PreparedStatement pre = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre.setInt(1, user.getId());
            pre.setInt(2, 0);
            return pre.executeUpdate()==1;
            
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingSessionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getIdShoppingSessionByUser(User user){
        try {
            String sql= """
                                    SELECT *
                                      FROM [dbo].[shopping_session]
                                      where user_id = ?""";
            PreparedStatement pre = connection.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pre.setInt(1, user.getId());
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShoppingSessionDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return addShoppingSessionOfUser(user)?getIdShoppingSessionByUser(user):0;
    }
    
    public boolean updateToTal(int userID){
    
        return false;
    }
    
    public static void main(String[] args) {
        ShoppingSessionDAO dao = new ShoppingSessionDAO();
        User user = new User(18, "", "");
        System.out.println(dao.getIdShoppingSessionByUser(user));
    }
    
    
}
