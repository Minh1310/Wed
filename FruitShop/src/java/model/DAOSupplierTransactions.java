/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.SupplierTransactions;
import java.sql.*;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOSupplierTransactions extends DBconnect{
    
    /**
     * 
     * @param supp
     * @return 
     */
    public int add(SupplierTransactions supp){
        String sql ="""
                    INSERT INTO [dbo].[SupplierTransactions]
                               ([SupplierTransactionID]
                               ,[SupplierID]
                               ,[TransactionType]
                               ,[PurchaseOrderID]
                               ,[PaymentMethod]
                               ,[SupplierInvoiceNumber]
                               ,[TransactionDate]
                               ,[AmountExcludingTax]
                               ,[TaxAmount]
                               ,[TransactionAmount]
                               ,[FinalizationDate]
                               ,[IsFinalized])
                    VALUES(?,?,?,?,?,?,?,?,?,?,?,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, supp.getSupplierTransactionID());
            pre.setInt(2, supp.getSupplierID());
            pre.setString(3, supp.getTransactionType());
            pre.setInt( 4, supp.getPurchaseOrderID());
            pre.setString(5, supp.getPaymentMethod());
            pre.setString(6, supp.getSupplierInvoiceNumber());
            pre.setString(7, supp.getTransactionDate());
            pre.setDouble(8, supp.getAmountExcludingTax());
            pre.setDouble(9, supp.getTaxAmount());
            pre.setDouble(10, supp.getTransactionAmount());
            pre.setString(11, supp.getFinalizationDate());
            pre.setInt(12, supp.getIsFinalized());
            

            return pre.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplierTransactions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public int remove(String id){
        String sql = "delete from SupplierTransactions where SupplierTransactionID = ?";
        try {
            PreparedStatement state = conn.prepareStatement(sql);
            state.setInt(1, Integer.parseInt(id));
            return state.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplierTransactions.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    /**
     * 
     * @param supp
     * @return 
     */
    public int update(SupplierTransactions supp){
        String sql = """
                     UPDATE [dbo].[SupplierTransactions]
                        SET [SupplierID] = ?
                           ,[TransactionType] =?
                           ,[PurchaseOrderID] = ?
                           ,[PaymentMethod] = ?
                           ,[SupplierInvoiceNumber] = ?
                           ,[TransactionDate] = ?
                           ,[AmountExcludingTax] = ?
                           ,[TaxAmount] = ?
                           ,[TransactionAmount] = ?
                           ,[FinalizationDate] = ?
                           ,[IsFinalized] = ?
                      WHERE SupplierTransactionID = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, supp.getSupplierID());
            pre.setString(2, supp.getTransactionType());
            pre.setInt( 3, supp.getPurchaseOrderID());
            pre.setString(4, supp.getPaymentMethod());
            pre.setString(5, supp.getSupplierInvoiceNumber());
            pre.setString(6, supp.getTransactionDate());
            pre.setDouble(7, supp.getAmountExcludingTax());
            pre.setDouble(8, supp.getTaxAmount());
            pre.setDouble(9, supp.getTransactionAmount());
            pre.setString(10, supp.getFinalizationDate());
            pre.setInt(11, supp.getIsFinalized());
            pre.setInt(12, supp.getSupplierTransactionID());
            return pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOSupplierTransactions.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public List<SupplierTransactions> getAll(String sql){
        List<SupplierTransactions> st = new Vector<>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                st.add(new SupplierTransactions(rs.getInt(1),
                    rs.getInt(2), rs.getString(3),
                    rs.getInt(4), rs.getString(5),
                    rs.getString(6), rs.getString(7),
                    rs.getInt(8), rs.getInt(9),
                    rs.getInt(10), rs.getString(11),
                    rs.getInt(12)));
            }
            return st;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPurchaseOrders.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return st;
    }
    public static void main(String[] args) {
        DAOSupplierTransactions dao = new DAOSupplierTransactions();
        int n = dao.add(new SupplierTransactions(1111111, 7, "deo co dau",1, 
                "EFT", null, "2016-5-5", 0.00, 0, 0, "2016-6-6", 1));
        if (n > 0) {
            System.out.println("inserted");
        }
    }
}
