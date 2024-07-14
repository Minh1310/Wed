/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import dao.DashboardDAO;
import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Dashboard", urlPatterns = {"/dashboard"})
public class Dashboard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        if (startDate == null || startDate.isEmpty()) {
            startDate = "2001-01-01";
        }
        if (endDate == null || endDate.isEmpty()) {
            endDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        }
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        DashboardDAO dao = new DashboardDAO();

        int orderSuccess = dao.countOrdersByStatusAndDate(2, startDate, endDate);
        int orderCancel = dao.countOrdersByStatusAndDate(3, startDate, endDate);
        int orderProcess = dao.countOrdersByStatusAndDate(1, startDate, endDate);

        double totalRevenue = dao.totalRevenue(startDate, endDate);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        String formattedRevenue = currencyFormat.format(totalRevenue);

        // Replace the currency symbol with "VNĐ"
        formattedRevenue = formattedRevenue.replace("₫", "VNĐ");

        Map<String, Double> revenueByCategory = dao.getRevenueByCategory(startDate, endDate);
        Map<String, Double> avgStar = dao.getAVGStarByCategory(startDate, endDate);

        int newUser = dao.totalUser(startDate, endDate);
        int totalUserOrder = dao.totalUserOrder(startDate, endDate);
        double totalStar = dao.avgRating(startDate, endDate);
        System.out.println(totalStar);
        request.setAttribute("success", orderSuccess);
        request.setAttribute("cancel", orderCancel);
        request.setAttribute("process", orderProcess);
        request.setAttribute("totalRevenue", formattedRevenue);
        request.setAttribute("totalUserOrder", totalUserOrder);
        request.setAttribute("revenueByCategory", revenueByCategory);
        request.setAttribute("avgStar", avgStar);
        request.setAttribute("totalStar", totalStar);
        request.setAttribute("newUser", newUser);

        request.getRequestDispatcher("viewsAdmin/Dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
