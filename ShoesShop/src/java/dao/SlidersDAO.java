/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.List;
import model.Slider;
import java.sql.*;
/**
 *
 * @author Nhat Anh
 */
public class SlidersDAO extends DBContext{
    
    /**
     * Use to get all element slider
     * 
     * @return list slider
     */
    public List<Slider> getAll() {
        List<Slider> list = new LinkedList<>();
        String sql = "SELECT * FROM sliders";
        try (PreparedStatement pre = connection.prepareStatement(sql, 
                 ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pre.executeQuery()) {
            while (rs.next()) {
                    list.add(new Slider(rs.getInt(1), rs.getInt(2),
                            rs.getString(3), rs.getString(4), 
                            rs.getString(5), rs.getString(6), 
                            rs.getString(7)));
                }           
        } catch (SQLException ex) {
            Logger.getLogger(SlidersDAO.class.getName())
                    .log(Level.SEVERE, "Error fetching products", ex);
        }
        return list;
    }
    
    /**
     * Use to get all information random slider
     * 
     * @return all information of a random slider
     */
    public Slider getRadom(){
        List<Slider> ls = getAll();
        Slider slider = ls.get((int) (Math.random() * (ls.size()-1) + 1));
        return slider;
    }
    
    public static void main(String[] args) {
        SlidersDAO cs = new SlidersDAO();
        System.out.println(cs.getRadom());
    }
}
