package dao;

import context.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.UserHistory;

/**
 *
 * @author Admin
 */
public class UserHistoryDAO extends DBContext {

    public List<UserHistory> getAll(Integer userId, String search, int currentPage, int limitPage) {
        List<UserHistory> userHistories = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM UserHistory WHERE 1=1");

        if (userId != null) {
            query.append(" AND userId = ?");
        }
        if (search != null && !search.isEmpty()) {
            query.append(" AND (roleName LIKE ? OR status LIKE ? OR firstName LIKE ? OR lastName LIKE ? OR telephone LIKE ?)");
        }

        try (
                PreparedStatement statement = connection.prepareStatement(query.toString())) {

            int parameterIndex = 1;

            if (userId != null) {
                statement.setInt(parameterIndex++, userId);
            }
            if (search != null && !search.isEmpty()) {
                String searchPattern = "%" + search + "%";
                statement.setString(parameterIndex++, searchPattern);
                statement.setString(parameterIndex++, searchPattern);
                statement.setString(parameterIndex++, searchPattern);
                statement.setString(parameterIndex++, searchPattern);
                statement.setString(parameterIndex++, searchPattern);
            }

            ResultSet resultSet = statement.executeQuery();

            int start = (currentPage - 1) * limitPage;
            int end = start + limitPage;
            int index = 0;

            while (resultSet.next()) {
                if (index >= start && index < end) {
                    UserHistory userHistory = new UserHistory();
                    userHistory.setId(resultSet.getInt("id"));
                    userHistory.setUserId(resultSet.getInt("userId"));
                    userHistory.setRoleName(resultSet.getString("roleName"));
                    userHistory.setStatus(resultSet.getString("status"));
                    userHistory.setFirstName(resultSet.getString("firstName"));
                    userHistory.setLastName(resultSet.getString("lastName"));
                    userHistory.setTelephone(resultSet.getString("telephone"));
                    userHistory.setModifyAt(resultSet.getTimestamp("modifyAt"));
                    userHistory.setPassword(resultSet.getString("password"));

                    userHistories.add(userHistory);
                }
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userHistories;
    }

    public static void main(String[] args) {
        UserHistoryDAO uhDAO = new UserHistoryDAO();
        System.out.println(uhDAO.getAll(null, null, 1, 10).size());
    }
}
