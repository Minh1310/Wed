/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.PurchaseOrderLines;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class DAOPurchaseOrderLines extends DBconnect{
    
    /**
     * 
     * @param pur
     * @return 
     */
    public int add(PurchaseOrderLines pur){
        String sql = """
                    INSERT INTO [dbo].[PurchaseOrderLines]
                                ([PurchaseOrderLineID]
                                ,[PurchaseOrderID]
                                ,[ProductID]
                                ,[OrderedQuantity]
                                ,[Description]
                                ,[ReceivedQuantity]
                                ,[LastReceiptDate]
                                ,[IsOrderLineFinalized])
                    VALUES(?,?,?,?,?,?,?,?)""";
        try {
            DAOPurchaseOrderLines dao = new DAOPurchaseOrderLines();
            int id = dao.getValue("SELECT MAX(PurchaseOrderLineID) FROM PurchaseOrderLines");
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id+1);
            pre.setInt(2, pur.getPurchaseOrderID());
            pre.setInt(3, pur.getProductID());
            pre.setInt(4, pur.getOrderedQuantity());
            pre.setString(5, pur.getDescription());
            pre.setInt(6, pur.getReceivedQuantity());
            pre.setString(7, pur.getLastReceiptDate());
            pre.setInt(8, pur.isIsOrderLineFinalized());
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrderLines.class.getName())
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
        String sql = "delete from PurchaseOrderLines where PurchaseOrderLineID = ?";
        PreparedStatement state;
        try {
            state = conn.prepareStatement(sql);
            state.setInt(1, Integer.parseInt(id));
            return state.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrderLines.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * 
     * @param pur
     * @return 
     */
    public int update(PurchaseOrderLines pur){
        String sql = """
                     UPDATE [dbo].[PurchaseOrderLines]
                        SET[PurchaseOrderID] = ?
                           ,[ProductID] = ?
                           ,[OrderedQuantity] = ?
                           ,[Description] = ?
                           ,[ReceivedQuantity] = ?
                           ,[LastReceiptDate] = ?
                           ,[IsOrderLineFinalized] = ?
                      WHERE PurchaseOrderLineID =?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setInt(1, pur.getPurchaseOrderID());
            pre.setInt(2, pur.getProductID());
            pre.setInt(3, pur.getOrderedQuantity());
            pre.setString(4, pur.getDescription());
            pre.setInt(5, pur.getReceivedQuantity());
            pre.setString(6, pur.getLastReceiptDate());
            pre.setInt(7, pur.isIsOrderLineFinalized());
            pre.setInt(8, pur.getPurchaseOrderLineID());

            return pre.executeUpdate();
        } catch (SQLException ex) {
        }
        return 0;
    }
    
    /**
     * 
     * @param sql 
     * @return  
     */
    public List<PurchaseOrderLines> getAll(String sql){
        List<PurchaseOrderLines> pol = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                pol.add(new PurchaseOrderLines(rs.getInt(1), 
                        rs.getInt(2), rs.getInt(3), 
                        rs.getInt(4), rs.getString(5), 
                        rs.getInt(6), rs.getString(7),
                        rs.getInt(8)));          
            }
            return pol;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrderLines.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return pol;
    }
    
    
    public static void main(String[] args) {
        DAOPurchaseOrderLines dao = new DAOPurchaseOrderLines();
        int n = dao.update(new PurchaseOrderLines(1008, 8, 7, 6, "Gdsfg", 8, "12/12/2003", 1));
        if (n > 0) {
            System.out.println("inserted");
        }
    }
}