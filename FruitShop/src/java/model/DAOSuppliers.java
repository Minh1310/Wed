/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Suppliers;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author admin
 */
public class DAOSuppliers extends DBconnect{
    
    /**
     * 
     * @param supl
     * @return 
     */
    public int add(Suppliers supl){
        String sql = """
                    INSERT INTO [dbo].[Suppliers]
                                ([SupplierID]
                                ,[SupplierName]
                                ,[SupplierCategoryID]
                                ,[DeliveryMethod]
                                ,[DeliveryCity]
                                ,[SupplierReference]
                                ,[BankAccountName]
                                ,[BankAccountBranch]
                                ,[BankAccountCode]
                                ,[BankAccountNumber]
                                ,[BankInternationalCode]
                                ,[PaymentDays]
                                ,[PhoneNumber]
                                ,[FaxNumber]
                                ,[WebsiteURL]
                                ,[DeliveryAddressLine1]
                                ,[DeliveryAddressLine2]
                                ,[DeliveryPostalCode])
                    VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)""";
        try {
            String sqlID = "SELECT TOP 1 SupplierID FROM Suppliers order by SupplierID desc;";
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sqlID);
            PreparedStatement pre = conn.prepareStatement(sql);
            while(rs.next()){     
                pre.setInt(1, rs.getInt(1)+1);
                pre.setString(2, supl.getSupplierName());
                pre.setInt(3, supl.getSupplierCategoryID());
                pre.setString( 4, supl.getDeliveryMethod());
                pre.setString(5, supl.getDeliveryCity());
                pre.setString(6, supl.getSupplierReference());
                pre.setString(7, supl.getBankAccountName());
                pre.setString(8, supl.getBankAccountBranch());
                pre.setString(9, supl.getBankAccountCode());
                pre.setString(10, supl.getBankAccountNumber());
                pre.setString(11, supl.getBankInternationalCode());
                pre.setInt(12, supl.getPaymentDays());
                pre.setString(13, supl.getPhoneNumber());
                pre.setString(14, supl.getFaxNumber());
                pre.setString(15, supl.getWebsiteURL());
                pre.setString(16, supl.getDeliveryAddressLine1());
                pre.setString(17, supl.getDeliveryAddressLine2());
                pre.setString(18, supl.getDeliveryPostalCode()); 
            }
              
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName())
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
        String sql = "delete from Suppliers\n" +
        "                    where SupplierID = "+id+" and\n" +
        "        "+id+" not in (select distinct SupplierID from SupplierTransactions) \n" +
        "        and "+id+" not in (select distinct SupplierID from  Products) \n" +
        "        and "+id+" not in (select distinct SupplierID from PurchaseOrders)";
        try {
            Statement state = conn.createStatement();
            return state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName())
                    .log(Level.SEVERE, null, ex);
        }      
        return 0;
    }
    public static void main(String[] args) {
        DAOSuppliers dao = new DAOSuppliers();       
        if(dao.remove("15")>0){
            System.out.println("hello");
        }
    }
    
    /**
     * 
     * @param supl
     * @return 
     */
    public int update(Suppliers supl){
        int n = 0;
        String sql ="""
                    UPDATE [dbo].[Suppliers]
                    SET 
                          [SupplierName] = ?
                          ,[SupplierCategoryID] = ?
                          ,[DeliveryMethod] = ?
                          ,[DeliveryCity] = ?
                          ,[SupplierReference] = ?
                          ,[BankAccountName] = ?
                          ,[BankAccountBranch] = ?
                          ,[BankAccountCode] = ?
                          ,[BankAccountNumber] = ?
                          ,[BankInternationalCode] = ?
                          ,[PaymentDays] = ?
                          ,[PhoneNumber] = ?
                          ,[FaxNumber] = ?
                          ,[WebsiteURL] = ?
                          ,[DeliveryAddressLine1] = ?
                          ,[DeliveryAddressLine2] = ?
                          ,[DeliveryPostalCode] = ?
                    WHERE SupplierID =?""";
         try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, supl.getSupplierName());
            pre.setInt(2, supl.getSupplierCategoryID());
            pre.setString( 3, supl.getDeliveryMethod());
            pre.setString(4, supl.getDeliveryCity());
            pre.setString(5, supl.getSupplierReference());
            pre.setString(6, supl.getBankAccountName());
            pre.setString(7, supl.getBankAccountBranch());
            pre.setString(8, supl.getBankAccountCode());
            pre.setString(9, supl.getBankAccountNumber());
            pre.setString(10, supl.getBankInternationalCode());
            pre.setInt(11, supl.getPaymentDays());
            pre.setString(12, supl.getPhoneNumber());
            pre.setString(13, supl.getFaxNumber());
            pre.setString(14, supl.getWebsiteURL());
            pre.setString(15, supl.getDeliveryAddressLine1());
            pre.setString(16, supl.getDeliveryAddressLine2());
            pre.setString(17, supl.getDeliveryPostalCode());
            pre.setInt(18, supl.getSupplierID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public List<Suppliers> getAll(String sql){
        List<Suppliers> sup = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                sup.add(new Suppliers(rs.getInt(1), 
                        rs.getString(2), rs.getInt(3), 
                        rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), 
                        rs.getString(8), rs.getString(9), 
                        rs.getString(10), rs.getString(11), 
                        rs.getInt(12), rs.getString(13), 
                        rs.getString(14), rs.getString(15), 
                        rs.getString(16), rs.getString(17), 
                        rs.getString(18)));
            }
            return sup;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrders.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return sup;
    }
    
    /**
     * 
     * @param userName
     * @param password
     * @return 
     */
    public boolean login(String userName, String password){
        String sql = "select * from Suppliers where SupplierName like ? and SupplierReference like ?";
        try {
            PreparedStatement state = conn.prepareStatement(sql);
            state.setString(1, userName);
            state.setString(2, password);
            ResultSet rs = state.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSuppliers.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
//    public static void main(String[] args) {
//        DAOSuppliers dao = new DAOSuppliers();
//     //   int n = dao.add(new Suppliers(0, "jhv", null, null, null, "A123", null, null, null, null, null, 0, "deo co sdt", "cac", "dit me", "zsacas", "lz", "buoi"));
//        if (dao.login("A Datum Corporation", "AA20384")) {
//            System.out.println("inserted");
//        }
//        List<Suppliers> ls = dao.getAll("select * from suppliers");
//        ls.forEach(a -> {System.out.println(a.toString());});
//        
//    }
}
