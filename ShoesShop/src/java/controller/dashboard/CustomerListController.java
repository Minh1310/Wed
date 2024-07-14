/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Pagination;
import model.User;

@WebServlet(name = "CustomerListController", urlPatterns = {"/customerList"})
public class CustomerListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO uDAO = new UserDAO();
        String search = request.getParameter("search");
        String statusString = request.getParameter("status");
        // Lấy danh sách tất cả người dùng
        List<User> list = uDAO.filterCustomersByStatusAndSearch(null, null);
        HttpSession session = request.getSession();
        
        if (search != null) {
            list = uDAO.filterCustomersByStatusAndSearch(null, search);
        }
        
        if(statusString != null && !statusString.isEmpty()){
            int status = Integer.parseInt(statusString);
            list = uDAO.filterCustomersByStatusAndSearch(status, null);
        }

        // start pagging
        int limitPage = 5;
        if (request.getParameter("cp") == null) {
            Pagination Page = new Pagination(list, limitPage, 1);
            Pagination<User> pagination = new Pagination<>(list, limitPage, 1);
            list = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
            request.setAttribute("list", pagination.getItemsOnPage());
        } else if (request.getParameter("cp") != null) {
            int cp = Integer.parseInt(request.getParameter("cp"));
            Pagination Page = new Pagination(list, limitPage, cp);
            Pagination<User> pagination = new Pagination<>(list, limitPage, cp);
            list = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
        }
        // set URL
        request.setAttribute("pagging", "customerList");
        session.setAttribute("paramSearch", search);
        session.setAttribute("paramStatus", statusString);
        // end pagging
        request.setAttribute("listUser", list);
        request.getRequestDispatcher("viewsAdmin/viewCustomer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
