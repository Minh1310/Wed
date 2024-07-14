/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Roles;
import model.User;

/**
 *
 * @author Admin
 */
public class RolesDAO extends DBContext {

    // lay tat ca cac role
    public List<Roles> getAllRoles() {
        List<Roles> list = new ArrayList<>();
        String sql = "SELECT [id]\n"//1
                + "      ,[name]\n"//2
                + "  FROM [dbo].[roles]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Roles r = new Roles();
                
                r.setId(rs.getInt(1));
                r.setName(rs.getString(2));
                
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
}
