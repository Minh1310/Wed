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
import model.UserStatus;

/**
 *
 * @author Admin
 */
public class User_statusDAO extends DBContext {

    // lay tat ca cac user status
    public List<UserStatus> getAllUserStatus() {
        List<UserStatus> list = new ArrayList<>();
        String sql = "SELECT [id]\n"//1
                + "      ,[name]\n"//2
                + "  FROM [dbo].[user_status]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserStatus us = new UserStatus();

                us.setId(rs.getInt(1));
                us.setName(rs.getString(2));

                list.add(us);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
