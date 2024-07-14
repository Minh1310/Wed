/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Nhat Anh
 */
public class DBconnect {
    public Connection conn = null;
    public String mess = "no";

    public DBconnect(String url, String userName, String password) {
        try {
            //Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("connected");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBconnect.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * 
     * @param sql
     * @return 
     */
    public int querySql(String sql){
        Statement state;
        try {
            state = conn.createStatement();
            return state.executeUpdate(sql);
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
    public int getValue(String sql){
        Statement state;
        try {
            state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                return rs.getInt(1);
            }                 
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;                     
    }
    
    /**
     * 
     */
    public DBconnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=SE1841",
                "sa","123456");
    }
    public static void main(String[] args){
        new DBconnect();
    }
    
    
    
}
