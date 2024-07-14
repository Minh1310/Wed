/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import entity.PurchaseOrders;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class DAOPurchaseOrders extends DBconnect{
    
    /**
     * 
     * @param purc
     * @return 
     */
    public int add(PurchaseOrders purc){
         String sql = """
                      INSERT INTO [dbo].[PurchaseOrders]
                                 ([PurchaseOrderID]
                                 ,[SupplierID]
                                 ,[OrderDate]
                                 ,[DeliveryMethod]
                                 ,[ExpectedDeliveryDate]
                                 ,[SupplierReference]
                                 ,[IsOrderFinalized])
                           VALUES(?,?,?,?,?,?,?)""";
         try {
            DAOPurchaseOrders dao = new DAOPurchaseOrders();
            int id = dao.getValue("SELECT MAX(PurchaseOrderID) FROM PurchaseOrders")+1;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setInt(2, purc.getSupplierID());
            pre.setString(3, purc.getOrderDate());
            pre.setString( 4, purc.getDeliveryMethod());
            pre.setString(5, purc.getExpectedDeliveryDate());
            pre.setString(6, purc.getSupplierReference());
            pre.setInt(7, purc.getIsOrderFinalized());
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrders.class.getName())
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
        String sql = "delete from PurchaseOrders where PurchaseOrderID = ?"
//                + " and ( ? not in "
//                + "(select distinct PurchaseOrderID from SupplierTransactions))"
//                + " and ( ? not in "
//                + "(select distinct PurchaseOrderID from PurchaseOrderLines))"
                ;
        try {
            PreparedStatement state = conn.prepareStatement(sql);
            state.setInt(1, Integer.parseInt(id));
//            state.setInt(2, Integer.parseInt(id));
//            state.setInt(3, Integer.parseInt(id));
            return state.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrders.class.getName())
                    .log(Level.SEVERE, null, ex);
        }      
        return 0;
    }
    public static void main(String[] args) {
        DAOPurchaseOrders dao = new DAOPurchaseOrders();
        if(dao.remove("257")>0){
            System.out.println("hello");
        }
    }
    
    /**
     * 
     * @param purc
     * @return 
     */
    public int update(PurchaseOrders purc){
         int n = 0;
         String sql = """
                      UPDATE [dbo].[PurchaseOrders]
                         SET 
                            [SupplierID] = ?
                            ,[OrderDate] = ?
                            ,[DeliveryMethod] = ?
                            ,[ExpectedDeliveryDate] = ?
                            ,[SupplierReference] = ?
                            ,[IsOrderFinalized] = ?
                       WHERE PurchaseOrderID = ?""";
         try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, purc.getSupplierID());
            pre.setString( 2, purc.getOrderDate());
            pre.setString(3, purc.getDeliveryMethod());
            pre.setString(4, purc.getExpectedDeliveryDate());
            pre.setString(5, purc.getSupplierReference());
            pre.setInt(6, purc.getIsOrderFinalized());
            pre.setInt(7, purc.getPurchaseOrderID());
            
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrders.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
         return n;
     }
    
    /**
     * 
     * @param sql
     * @return 
     */
    public List<PurchaseOrders> getAll(String sql){
        List<PurchaseOrders> po = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                po.add(new PurchaseOrders(rs.getInt(1), 
                    rs.getInt(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), 
                    rs.getString(6), rs.getInt(7)));         
            }
            return po;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrders.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return po;
    }
//     public static void main(String[] args) {
//        DAOPurchaseOrders dao = new DAOPurchaseOrders();
//       // int n = dao.update(new PurchaseOrders(251, 7, "2013-2-2", "roadt", "2013-6-9", "012HHSAC", true));
////       String a = "true" ;
////       System.out.println(Boolean.parseBoolean(a));
//        if (dao.remove("252") > 0) {
//          System.out.println("inserted");
//        }
//    }
     }
    
