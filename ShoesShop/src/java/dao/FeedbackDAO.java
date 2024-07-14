/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.Ratting;
import model.User;

/**
 *
 * @author 84355
 */
public class FeedbackDAO extends DBContext {

    public List<Ratting> getAllRatingFilter(String status, Integer rating, String userName, String comment, String productname) throws SQLException {
        List<Ratting> ratings = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder(
                "SELECT r.id, r.product_id, r.user_id, r.rating, r.comment, r.created_at, r.modified_at, r.status,"
                + "u.first_name, u.last_name, p.name AS product_name "
                + "FROM rating r "
                + "JOIN users u ON r.user_id = u.id "
                + "JOIN products p ON r.product_id = p.id "
                + "WHERE 1=1"
        );

        if (status != null) {
            queryBuilder.append(" AND r.status LIKE ?");
        }

        if (rating != null) {
            queryBuilder.append(" AND r.rating = ?");
        }

        if (userName != null && !userName.isEmpty()) {
            String[] nameParts = userName.split(" ");
            if (nameParts.length >= 2) {
                String firstName = nameParts[0];
                String lastName = nameParts[1];
                queryBuilder.append(" AND (u.first_name LIKE ? AND u.last_name LIKE ?)");
            } else {
                queryBuilder.append(" AND (u.first_name LIKE ? OR u.last_name LIKE ?)");
            }
        }

        if (comment != null && !comment.isEmpty()) {
            queryBuilder.append(" AND r.comment LIKE ?");
        }
        if (productname != null && !productname.isEmpty()) {
            queryBuilder.append(" AND p.name LIKE ?");
        }

        String query = queryBuilder.toString();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int paramIndex = 1;

            if (status != null) {
                statement.setString(paramIndex++, "%" + status + "%");
            }

            if (rating != null) {
                statement.setInt(paramIndex++, rating);
            }

            if (userName != null && !userName.isEmpty()) {
                String[] nameParts = userName.split(" ");
                if (nameParts.length >= 2) {
                    String firstName = nameParts[0];
                    String lastName = nameParts[1];
                    statement.setString(paramIndex++, "%" + firstName + "%");
                    statement.setString(paramIndex++, "%" + lastName + "%");
                } else {
                    statement.setString(paramIndex++, "%" + userName + "%");
                    statement.setString(paramIndex++, "%" + userName + "%");
                }
            }

            if (comment != null && !comment.isEmpty()) {
                statement.setString(paramIndex++, "%" + comment + "%");
            }

            if (productname != null && !productname.isEmpty()) {
                statement.setString(paramIndex++, "%" + productname + "%");
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ratting ratingDTO = new Ratting();
                    ratingDTO.setId(resultSet.getInt("id"));
                    ratingDTO.setProduct_id(resultSet.getInt("product_id"));
                    ratingDTO.setUser_id(resultSet.getInt("user_id"));
                    ratingDTO.setRatting(resultSet.getInt("rating"));
                    ratingDTO.setComment(resultSet.getString("comment"));
                    ratingDTO.setCreated_at(resultSet.getDate("created_at"));
                    ratingDTO.setModified_at(resultSet.getDate("modified_at"));
                    ratingDTO.setStatus(resultSet.getString("status"));

                    User user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setFirst_name(resultSet.getString("first_name"));
                    user.setLast_name(resultSet.getString("last_name"));
                    ratingDTO.setUser(user);

                    Product product = new Product();
                    product.setId(resultSet.getInt("product_id"));
                    product.setName(resultSet.getString("product_name"));
                    ratingDTO.setProduct(product);

                    ratings.add(ratingDTO);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ratings;
    }

    public Ratting getFeedbackById(int feedbackId) throws SQLException {
        String query = "SELECT r.id, r.product_id, r.user_id, r.rating, r.comment, r.created_at, r.modified_at, r.status_image, "
                + "u.first_name, u.last_name, u.email, u.telephone, u.gender, u.status_id, "
                + "p.name AS product_name, p.size, p.color "
                + "FROM rating r "
                + "JOIN users u ON r.user_id = u.id "
                + "JOIN products p ON r.product_id = p.id "
                + "WHERE r.id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, feedbackId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Ratting ratingDTO = new Ratting();
                    ratingDTO.setId(resultSet.getInt("id"));
                    ratingDTO.setProduct_id(resultSet.getInt("product_id"));
                    ratingDTO.setUser_id(resultSet.getInt("user_id"));
                    ratingDTO.setRatting(resultSet.getInt("rating"));
                    ratingDTO.setComment(resultSet.getString("comment"));
                    ratingDTO.setCreated_at(resultSet.getDate("created_at"));
                    ratingDTO.setModified_at(resultSet.getDate("modified_at"));
                    ratingDTO.setStatus_image(resultSet.getString("status_image"));

                    User user = new User();
                    user.setId(resultSet.getInt("user_id"));
                    user.setFirst_name(resultSet.getString("first_name"));
                    user.setLast_name(resultSet.getString("last_name"));
                    user.setEmail(resultSet.getString("email"));
                    user.setTelephone(resultSet.getString("telephone"));
                    user.setStatus_id(resultSet.getInt("status_id"));
                    ratingDTO.setUser(user);

                    Product product = new Product();
                    product.setId(resultSet.getInt("product_id"));
                    product.setName(resultSet.getString("product_name"));
                    product.setSize(resultSet.getString("size"));
                    product.setColor(resultSet.getString("color"));
                    ratingDTO.setProduct(product);

                    return ratingDTO;
                }
            }
        } catch (SQLException ex) {
        }

        return null;
    }

    public void changeRatingStatus(int ratingId, String newStatus) throws SQLException {
        String updateQuery = "UPDATE rating SET status = ? WHERE id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, newStatus);
            updateStatement.setInt(2, ratingId);
            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Rating status updated successfully.");
            } else {
                System.out.println("No rating found with the given ID.");
            }
        } catch (SQLException ex) {
        }
    }

    public void changeRatingStatusImage(int ratingId, String newStatus) throws SQLException {
        String updateQuery = "UPDATE rating SET status_image = ? WHERE id = ?";
        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, newStatus);
            updateStatement.setInt(2, ratingId);
            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Status Image updated successfully.");
            } else {
                System.out.println("Do not change status image.");
            }
        } catch (SQLException ex) {
        }
    }

    public static void main(String[] args) {
        try {
            FeedbackDAO dao = new FeedbackDAO();
            dao.getAllRatingFilter("", 3, null, null, "Running");
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
