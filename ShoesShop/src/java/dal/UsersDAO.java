/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Roles;
import model.User;
import model.UserStatus;

/**
 *
 * @author Admin
 */
public class UsersDAO extends DBContext {

    // Insert user vao database
    public void insertUser(User u) {
        String sql = "INSERT INTO [dbo].[users]\n"
                + "           ([email]\n"//1
                + "           ,[password]\n"//2
                + "           ,[role_id]\n"//3
                + "           ,[status_id]\n"//4
                + "           ,[first_name]\n"//5
                + "           ,[last_name]\n"//6
                + "           ,[telephone]\n"//7
                + "           ,[created_at]\n"//8
                + "           ,[modified_at]\n"//9
                + "           ,[gender]\n"//10
                + "           ,[token])\n"//11
                + "     VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, u.getEmail());
            st.setString(2, u.getPassword());
            st.setInt(3, u.getRole_id());
            st.setInt(4, u.getStatus_id());
            st.setString(5, u.getFirst_name());
            st.setString(6, u.getLast_name());
            st.setString(7, u.getTelephone());

            java.util.Date utilDate = u.getCreated_at();
            java.sql.Date created_at = new java.sql.Date(utilDate.getTime());
            st.setDate(8, created_at);

            utilDate = u.getModified_at();
            java.sql.Date modified_at = new java.sql.Date(utilDate.getTime());
            st.setDate(9, modified_at);

            st.setBoolean(10, u.isGender());
            st.setString(11, u.getToken());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Get user by email
    public User getUserByEmail(String email) {
        String sql = "SELECT [id]\n"//1
                + "      ,[email]\n"//2
                + "      ,[password]\n"//3
                + "      ,[role_id]\n"//4
                + "      ,[status_id]\n"//5
                + "      ,[first_name]\n"//6
                + "      ,[last_name]\n"//7
                + "      ,[telephone]\n"//8
                + "      ,[created_at]\n"//9
                + "      ,[modified_at]\n"//10
                + "      ,[gender]\n"//11
                + "      ,[token]\n"//12
                + "  FROM [dbo].[users]\n"
                + "  WHERE email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User();

                u.setId(rs.getInt(1));
                u.setEmail(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setRole_id(rs.getInt(4));
                u.setStatus_id(rs.getInt(5));
                u.setFirst_name(rs.getString(6));
                u.setLast_name(rs.getString(7));
                u.setTelephone(rs.getString(8));
                u.setCreated_at(rs.getDate(9));
                u.setModified_at(rs.getDate(10));
                u.setGender(rs.getBoolean(11));
                u.setToken(rs.getString(12));

                return u;
            }
        } catch (SQLException e) {

        }
        return null;
    }
    
    // Update an user
    public void updateUser(User u) {
        String sql = "UPDATE [dbo].[users]\n"
                + "   SET [email] = ?\n"//1
                + "      ,[password] = ?\n"//2
                + "      ,[role_id] = ?\n"//3
                + "      ,[status_id] = ?\n"//4
                + "      ,[first_name] = ?\n"//5
                + "      ,[last_name] = ?\n"//6
                + "      ,[telephone] = ?\n"//7
                + "      ,[created_at] = ?\n"//8
                + "      ,[modified_at] = ?\n"//9
                + "      ,[gender] = ?\n"//10
                + " WHERE id = ?";//11
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, u.getEmail());
            st.setString(2, u.getPassword());
            st.setInt(3, u.getRole_id());
            st.setInt(4, u.getStatus_id());
            st.setString(5, u.getFirst_name());
            st.setString(6, u.getLast_name());
            st.setString(7, u.getTelephone());

            java.util.Date utilDate = new Date();
            java.sql.Date created_at = new java.sql.Date(utilDate.getTime());
            st.setDate(8, created_at);

            utilDate = new Date();
            java.sql.Date modified_at = new java.sql.Date(utilDate.getTime());
            st.setDate(9, modified_at);

            st.setBoolean(10, u.isGender());
            st.setInt(11, u.getId());

            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    // Update user active and modify date
    public void updateUserActive(User u) {
        String sql = "UPDATE [dbo].[users]\n"
                + "   SET [status_id] = ?\n"//1
                + "      ,[modified_at] = ?\n"//2
                + " WHERE id = ?";//3
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, u.getStatus_id());

            java.util.Date utilDate = new Date();
            java.sql.Date modified_at = new java.sql.Date(utilDate.getTime());
            st.setDate(2, modified_at);

            st.setInt(3, u.getId());

            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    // Update user token and modify date
    public void updateUserToken(User u) {
        String sql = "UPDATE [dbo].[users]\n"
                + "   SET [password] = ?\n"//1
                + "	  ,[modified_at] = ?\n"//2
                + "      ,[token] = ?\n"//3
                + " WHERE id = ?";//4
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, u.getPassword());
            
            java.util.Date utilDate = new Date();
            java.sql.Date modified_at = new java.sql.Date(utilDate.getTime());
            st.setDate(2, modified_at);

            st.setString(3, u.getToken());

            st.setInt(4, u.getId());

            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public static void main(String[] args) {
        UsersDAO udb = new UsersDAO();
        User u = new User("aaa", "bbb", 1, 1, "tu", "bui", true,"0123456789", new Date(), new Date(), "222222");
        udb.updateUserToken(u);
        
    }
}
