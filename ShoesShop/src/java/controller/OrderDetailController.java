/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BlogCategoriesDAO;
import dao.BlogDAO;
import dao.OrderDAO;
import dao.UserAddressDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import model.BlogCategories;
import model.Order;
import model.OrderDetail;
import model.Pagination;
import model.User;
import model.UserAddress;

/**
 *
 * @author 
 */
@WebServlet(name = "OrderDetailController", urlPatterns = {"/orderDetail"})
public class OrderDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idRaw = request.getParameter("id");
            double totalPrice = 0;
            int totalUnit = 0;

            OrderDAO oDAO = new OrderDAO();
            List<OrderDetail> list = oDAO.getOrderDetailsByOrderId(Integer.parseInt(idRaw));
            for (OrderDetail o : list) {
                totalPrice += o.getPrice() * o.getQuantity();
                totalUnit += o.getQuantity();
            }
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("totalUnit", totalUnit);
            request.setAttribute("order", oDAO.getOrderById(Integer.parseInt(idRaw)));
            request.setAttribute("list", list);
        } catch (Exception ex) {
            Logger.getLogger(OrderDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);

    }
/**
     * Handles the POST request, currently not implemented for this controller.
     * Forwards the request to the profile page (profile.jsp).
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
