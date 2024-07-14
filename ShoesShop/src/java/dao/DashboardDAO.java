/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class DashboardDAO extends DBContext {

    public int countOrdersByStatusAndDate(Integer statusId, String startDate, String endDate) {
        List<Object> list = new ArrayList<>();
        int orderCount = 0;

        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                       SELECT COUNT(*) as order_count FROM shop_orders WHERE  1 = 1 """);

            if (statusId != null) {
                query.append(" AND  status_id = ? ");
                list.add(statusId);
            }
            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                query.append(" AND created_at BETWEEN ? AND ? ");
                list.add(startDate);
                list.add(endDate);
            }
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (java.sql.ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    orderCount = rs.getInt("order_count");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderCount;
    }

    public double totalRevenue(String startDate, String endDate) {
        List<Object> list = new ArrayList<>();
        double orderCount = 0;

        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                       SELECT SUM(od.price * od.quantity) AS total_revenue
                       FROM shop_orders so
                       JOIN order_details od ON so.id = od.shop_order_id
                       JOIN order_status os ON so.status_id = os.id """);

            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                query.append(" AND so.created_at BETWEEN ? AND ? ");
                list.add(startDate);
                list.add(endDate);
            }
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (java.sql.ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    orderCount = rs.getInt("total_revenue");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderCount;
    }

    public int totalUser(String startDate, String endDate) {
        List<Object> list = new ArrayList<>();
        int totalUser = 0;

        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                       SELECT COUNT(*) AS newly_registered_customers
                       FROM users
                       WHERE 1 = 1  """);

            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                query.append(" AND created_at BETWEEN ? AND ? ");
                list.add(startDate);
                list.add(endDate);
            }
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (java.sql.ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    totalUser = rs.getInt("newly_registered_customers");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalUser;
    }

    public int totalUserOrder(String startDate, String endDate) {
        List<Object> list = new ArrayList<>();
        int totalUser = 0;

        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                       SELECT COUNT(DISTINCT so.user_id) AS newly_bought_customers
                       FROM shop_orders so
                       JOIN users u ON so.user_id = u.id where 1 = 1  """);

            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                query.append(" AND so.created_at BETWEEN ? AND ? ");
                list.add(startDate);
                list.add(endDate);
            }
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (java.sql.ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    totalUser = rs.getInt("newly_bought_customers");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalUser;
    }

    public double avgRating(String startDate, String endDate) {
        List<Object> list = new ArrayList<>();
        int totalUser = 0;

        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                       SELECT AVG(rating) AS average_star
                       FROM rating where 1 = 1  """);

            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                query.append(" AND created_at BETWEEN ? AND ? ");
                list.add(startDate);
                list.add(endDate);
            }
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (java.sql.ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    totalUser = rs.getInt("average_star");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalUser;
    }

    public Map<String, Double> getRevenueByCategory(String startDate, String endDate) {
        List<Object> list = new ArrayList<>();
        Map<String, Double> revenueByCategory = new HashMap<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                       SELECT c.name AS category_name, SUM(od.price * od.quantity) AS category_revenue
                       FROM shop_orders so
                       JOIN order_details od ON so.id = od.shop_order_id
                       JOIN products p ON od.product_id = p.id
                       JOIN categories c ON p.category_id = c.id
                       JOIN order_status os ON so.status_id = os.id
                       WHERE os.id = 2   """);

            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                query.append(" AND so.created_at BETWEEN ? AND ? ");
                list.add(startDate);
                list.add(endDate);
            }
            query.append(" GROUP BY c.name");
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (java.sql.ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String categoryName = rs.getString("category_name");
                    double categoryRevenue = rs.getDouble("category_revenue");
                    revenueByCategory.put(categoryName, categoryRevenue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revenueByCategory;
    }

    public Map<String, Double> getAVGStarByCategory(String startDate, String endDate) {
        List<Object> list = new ArrayList<>();
        Map<String, Double> revenueByCategory = new HashMap<>();

        try {
            StringBuilder query = new StringBuilder();
            query.append("""
                       SELECT c.name AS category_name, AVG(f.rating) AS average_star
                       FROM rating f
                       JOIN products p ON f.product_id = p.id
                       JOIN categories c ON p.category_id = c.id
                       WHERE 1 = 1   """);

            if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
                query.append(" AND f.created_at BETWEEN ? AND ? ");
                list.add(startDate);
                list.add(endDate);
            }
            query.append(" GROUP BY c.name;");
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(query.toString());
            mapParams(preparedStatement, list);
            try (java.sql.ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String categoryName = rs.getString("category_name");
                    double categoryRevenue = rs.getDouble("average_star");
                    revenueByCategory.put(categoryName, categoryRevenue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return revenueByCategory;
    }

    public void mapParams(PreparedStatement ps, List<Object> args) throws SQLException {
        int i = 1;
        for (Object arg : args) {
            if (arg instanceof java.util.Date) {
                ps.setTimestamp(i++, new java.sql.Timestamp(((java.util.Date) arg).getTime()));
            } else if (arg instanceof Integer) {
                ps.setInt(i++, (Integer) arg);
            } else if (arg instanceof Long) {
                ps.setLong(i++, (Long) arg);
            } else if (arg instanceof Double) {
                ps.setDouble(i++, (Double) arg);
            } else if (arg instanceof Float) {
                ps.setFloat(i++, (Float) arg);
            } else {
                ps.setString(i++, (String) arg);
            }

        }
    }

    public static void main(String[] args) {
        DashboardDAO o = new DashboardDAO();
        System.out.println("total revenue" + o.totalRevenue(null, null));
        Map<String, Double> revenueByCategory = o.getRevenueByCategory(null, null);
        for (Map.Entry<String, Double> entry : revenueByCategory.entrySet()) {
            System.out.println("Category: " + entry.getKey() + ", Revenue: " + entry.getValue());
        }
        Map<String, Double> avgStar = o.getAVGStarByCategory(null, null);
        for (Map.Entry<String, Double> entry : avgStar.entrySet()) {
            System.out.println("Category: " + entry.getKey() + ", avgStar: " + entry.getValue());
        }
        System.out.println("User " + o.totalUser(null, null));
        System.out.println("A " + o.avgRating(null, null));

    }
}
