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
import java.util.Date;
import java.util.List;
import model.Blog;
import model.BlogCategories;
import model.Pagination;
import model.User;
import model.UserAddress;

/**
 *
 * @author tungl
 */
@WebServlet(name = "CancelController", urlPatterns = {"/cancelOrder"})
public class CancelController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        OrderDAO odao = new OrderDAO();
        HttpSession session = request.getSession();
        try {
            odao.updateOrderStatus(id, 3);
            session.setAttribute("messSuccess", "Update Successfuly!");
        } catch (Exception e) {
            session.setAttribute("messError", "Update Failed!");
        }
        response.sendRedirect("myOrder");
    }
}
