/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.*;

public class UserAddressDAO extends DBContext {

    // Create method
    public void addUserAddress(UserAddress userAddress) {
        String sql = "INSERT INTO user_address (user_id, address_line, city, country) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userAddress.getUserId());
            statement.setString(2, userAddress.getAddressLine());
            statement.setString(3, userAddress.getCity());
            statement.setString(4, userAddress.getCountry());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user address was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read method
    public List<UserAddress> getAllUserAddresses() {
        List<UserAddress> userAddresses = new ArrayList<>();
        String sql = "SELECT id, user_id, address_line, city, country FROM user_address";
        try (PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                UserAddress userAddress = new UserAddress();
                userAddress.setId(resultSet.getInt("id"));
                userAddress.setUserId(resultSet.getInt("user_id"));
                userAddress.setAddressLine(resultSet.getString("address_line"));
                userAddress.setCity(resultSet.getString("city"));
                userAddress.setCountry(resultSet.getString("country"));
                userAddresses.add(userAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAddresses;
    }

    public void updateUserAddress(UserAddress userAddress) {
        String selectSql = "SELECT COUNT(*) FROM user_address WHERE user_id = ?";
        String updateSql = "UPDATE user_address SET address_line = ?, city = ?, country = ? WHERE user_id = ?";
        String insertSql = "INSERT INTO user_address (user_id, address_line, city, country) VALUES (?, ?, ?, ?)";

        try {
            // Kiểm tra xem bản ghi đã tồn tại hay không
            try (PreparedStatement selectStatement = connection.prepareStatement(selectSql)) {
                selectStatement.setInt(1, userAddress.getUserId());
                ResultSet resultSet = selectStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                    // Nếu bản ghi tồn tại, thực hiện câu lệnh UPDATE
                    try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                        updateStatement.setString(1, userAddress.getAddressLine());
                        updateStatement.setString(2, userAddress.getCity());
                        updateStatement.setString(3, userAddress.getCountry());
                        updateStatement.setInt(4, userAddress.getUserId());
                        int rowsAffected = updateStatement.executeUpdate();
                        if (rowsAffected == 0) {
                            System.out.println("No rows updated.");
                        } else {
                            System.out.println("User address updated successfully.");
                        }
                    }
                } else {
                    // Nếu không tồn tại, thực hiện câu lệnh INSERT
                    try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                        insertStatement.setInt(1, userAddress.getUserId());
                        insertStatement.setString(2, userAddress.getAddressLine());
                        insertStatement.setString(3, userAddress.getCity());
                        insertStatement.setString(4, userAddress.getCountry());
                        int rowsAffected = insertStatement.executeUpdate();
                        if (rowsAffected == 0) {
                            System.out.println("No rows inserted.");
                        } else {
                            System.out.println("User address inserted successfully.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserAddress getUserAddressById(int addressId) {
        UserAddress userAddress = null;
        String sql = "SELECT id, user_id, address_line, city, country FROM user_address WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, addressId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userAddress = new UserAddress();
                    userAddress.setId(resultSet.getInt("id"));
                    userAddress.setUserId(resultSet.getInt("user_id"));
                    userAddress.setAddressLine(resultSet.getString("address_line"));
                    userAddress.setCity(resultSet.getString("city"));
                    userAddress.setCountry(resultSet.getString("country"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAddress;
    }

    public static void main(String[] args) {
        UserAddressDAO uaDAO = new UserAddressDAO();
        UserAddress ud = new UserAddress();
        ud.setUserId(51);
        ud.setCity("HN");
        uaDAO.updateUserAddress(ud);
    }
}
