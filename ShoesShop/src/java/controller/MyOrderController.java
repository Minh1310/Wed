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
import model.Pagination;
import model.User;
import model.UserAddress;

/**
 *
 * @author 
 */
@WebServlet(name = "MyOrderController", urlPatterns = {"/myOrder"})
public class MyOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("account");
            // Tam thoi chua co tai khoan login
            user = new User();
            user.setId(1);

            UserDAO uDAO = new UserDAO();
            OrderDAO oDAO = new OrderDAO();

            List<Order> list = oDAO.getAllOrders();

            // start pagging
            int limitPage = 1;
            if (request.getParameter("cp") == null) {
                Pagination Page = new Pagination(list, limitPage, 1);
                Pagination<Order> pagination = new Pagination<>(list, limitPage, 1);
                list = pagination.getItemsOnPage();
                session.setAttribute("page", Page);
                request.setAttribute("list", pagination.getItemsOnPage());
            } else if (request.getParameter("cp") != null) {
                int cp = Integer.parseInt(request.getParameter("cp"));
                Pagination Page = new Pagination(list, limitPage, cp);
                Pagination<Order> pagination = new Pagination<>(list, limitPage, cp);
                list = pagination.getItemsOnPage();
                session.setAttribute("page", Page);
            }
            // set URL
            request.setAttribute("pagging", "myOrder");
            // end pagging
            request.setAttribute("listBlog", list);

            request.setAttribute("orders", list);

            request.getRequestDispatcher("myOrder.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MyOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
