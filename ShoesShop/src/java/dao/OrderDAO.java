/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;

import context.DBContext;
import java.security.Timestamp;
import java.util.List;
import model.Tag;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

public class OrderDAO extends DBContext {

    private UserDAO uDAO;

    public OrderDAO() {
        uDAO = new UserDAO();
    }

    // Phương thức lấy Order theo id
    public Order getOrderById(int id) throws SQLException {
        Order order = null;
        String query = "SELECT * FROM shop_orders WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setUserId(resultSet.getInt("user_id"));
                    order.setStatusId(resultSet.getInt("status_id"));
                    order.setEmail(resultSet.getString("email"));
                    order.setAddress(resultSet.getString("address"));
                    order.setOrderTotal(resultSet.getDouble("order_total"));
                    order.setRecipient(resultSet.getString("recipient"));
                    order.setRecipientPhone(resultSet.getString("recipient_phone"));
                    order.setCreatedAt(resultSet.getTimestamp("created_at"));
                    order.setModifiedAt(resultSet.getTimestamp("modified_at"));
                    order.setOrderStatus(getStatusById(order.getStatusId()));
                    order.setUser(new UserDAO().getUserById(order.getUserId()));
                }
            }
        }
        return order;
    }

    public void updateOrderStatus(int orderId, int newStatusId) throws SQLException {
        String query = "UPDATE shop_orders SET status_id = ?, modified_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newStatusId);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
    }

    public List<Order> getOrdersByUserId(int userId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM shop_orders WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setUserId(resultSet.getInt("user_id"));
                    order.setStatusId(resultSet.getInt("status_id"));
                    order.setEmail(resultSet.getString("email"));
                    order.setAddress(resultSet.getString("address"));
                    order.setOrderTotal(resultSet.getDouble("order_total"));
                    order.setRecipient(resultSet.getString("recipient"));
                    order.setRecipientPhone(resultSet.getString("recipient_phone"));
                    order.setCreatedAt(resultSet.getTimestamp("created_at"));
                    order.setModifiedAt(resultSet.getTimestamp("modified_at"));
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    // Phương thức lấy tất cả Order
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM shop_orders";
        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setStatusId(resultSet.getInt("status_id"));
                order.setEmail(resultSet.getString("email"));
                order.setAddress(resultSet.getString("address"));
                order.setOrderTotal(resultSet.getDouble("order_total"));
                order.setRecipient(resultSet.getString("recipient"));
                order.setRecipientPhone(resultSet.getString("recipient_phone"));
                order.setCreatedAt(resultSet.getTimestamp("created_at"));
                order.setModifiedAt(resultSet.getTimestamp("modified_at"));

                order.setUser(uDAO.getUserById(order.getUserId()));
                order.setOrderStatus(getStatusById(order.getStatusId()));
                orders.add(order);
            }
        }
        return orders;
    }

    // Phương thức thêm Order
    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO shop_orders (user_id, status_id, email, address, order_total, recipient, recipient_phone, created_at, modified_at, sale_id, notes) VALUES (?, ?, ?, ?, ?, ?, ?, getdate(), getdate(), ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getStatusId());
            statement.setString(3, order.getEmail());
            statement.setString(4, order.getAddress());
            statement.setDouble(5, order.getOrderTotal());
            statement.setString(6, order.getRecipient());
            statement.setString(7, order.getRecipientPhone());
            // statement.setDate(8, new java.sql.Date(order.getCreatedAt().getTime())); // Thiết lập thời gian hiện tại cho created_at
            // statement.setDate(9, new java.sql.Date(order.getModifiedAt().getTime())); // Thiết lập thời gian hiện tại cho modified_at
            statement.setInt(8, order.getSaleId()>0?order.getSaleId():0);
            statement.setString(9, order.getNotes()==null?null:order.getNotes());
            statement.executeUpdate();
        }
    }

    // Phương thức xóa Order
    public void deleteOrder(int id) throws SQLException {
        String query = "DELETE FROM shop_orders WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Phương thức lấy tất cả OrderDetail
    public List<OrderDetail> getAllOrderDetails() throws SQLException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM order_details";
        try (PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(resultSet.getInt("id"));
                orderDetail.setProductId(resultSet.getInt("product_id"));
                orderDetail.setShopOrderId(resultSet.getInt("shop_order_id"));
                orderDetail.setQuantity(resultSet.getInt("quantity"));
                orderDetail.setPrice(resultSet.getDouble("price"));
                orderDetail.setCreatedAt(resultSet.getTimestamp("created_at"));
                orderDetail.setModifiedAt(resultSet.getTimestamp("modified_at"));
                orderDetails.add(orderDetail);
            }
            statement.close();
        }
        return orderDetails;
    }

    // Phương thức lấy danh sách OrderDetail dựa trên OrderId
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT * FROM order_details WHERE shop_order_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setId(resultSet.getInt("id"));
                    orderDetail.setProductId(resultSet.getInt("product_id"));
                    orderDetail.setShopOrderId(resultSet.getInt("shop_order_id"));
                    orderDetail.setQuantity(resultSet.getInt("quantity"));
                    orderDetail.setPrice(resultSet.getDouble("price"));
                    orderDetail.setCreatedAt(resultSet.getTimestamp("created_at"));
                    orderDetail.setModifiedAt(resultSet.getTimestamp("modified_at"));
                    orderDetail.setProduct(new ProductDAO().getProductById(orderDetail.getProductId()));
                    orderDetails.add(orderDetail);
                }
                statement.close();
            }
        }
        return orderDetails;
    }

    // Phương thức thêm OrderDetail
    public void addOrderDetail(OrderDetail orderDetail) throws SQLException {
        String query = "INSERT INTO order_details (product_id, shop_order_id, quantity, price, created_at, modified_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderDetail.getProductId());
            statement.setInt(2, orderDetail.getShopOrderId());
            statement.setInt(3, orderDetail.getQuantity());
            statement.setDouble(4, orderDetail.getPrice());
            statement.executeUpdate();
        }
    }

    // Phương thức xóa OrderDetail
    public void deleteOrderDetail(int id) throws SQLException {
        String query = "DELETE FROM order_details WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Phương thức cập nhật OrderDetail
    public void updateOrderDetail(OrderDetail orderDetail) throws SQLException {
        String query = "UPDATE order_details SET product_id = ?, shop_order_id = ?, quantity = ?, price = ?, modified_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderDetail.getProductId());
            statement.setInt(2, orderDetail.getShopOrderId());
            statement.setInt(3, orderDetail.getQuantity());
            statement.setDouble(4, orderDetail.getPrice());
            statement.setInt(5, orderDetail.getId());
            statement.executeUpdate();
        }
    }
    // Method to retrieve all order statuses

    public List<OrderStatus> getAllStatus() throws SQLException {
        List<OrderStatus> statuses = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT id, name FROM order_status";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderStatus status = new OrderStatus();
                status.setId(resultSet.getInt("id"));
                status.setName(resultSet.getString("name"));
                statuses.add(status);
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return statuses;
    }

    // Method to retrieve order status by ID
    public OrderStatus getStatusById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        OrderStatus status = null;

        try {
            String query = "SELECT id, name FROM order_status WHERE id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                status = new OrderStatus();
                status.setId(resultSet.getInt("id"));
                status.setName(resultSet.getString("name"));
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

        return status;
    }
    
    public int getLatestIdShopOrder(){
        String sql="select max(id) from shop_orders";
        try {
            PreparedStatement pre = connection.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
               return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        try {
            OrderDAO oDAO = new OrderDAO();
            OrderDetail detail = new OrderDetail(5, 10, 5, 45, null, null);
            oDAO.addOrderDetail(detail);
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
