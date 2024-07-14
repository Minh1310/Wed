/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BlogCategoriesDAO;
import dao.BlogDAO;
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
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/changePassword"})
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        // DAO object call method CRUD  
        UserDAO uDAO = new UserDAO();
        UserAddressDAO uaDAO = new UserAddressDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Tam thoi chua co tai khoan login
        user = new User();
        user.setId(1);

        User profile = uDAO.getUserById(user.getId());
        if (!currentPassword.equals(profile.getPassword())) {
            request.setAttribute("messError", "Password inconrrect!");
            request.getRequestDispatcher("myProfile").forward(request, response);
            return;
        } else if (!newPassword.equals(confirmPassword)) {
            session.setAttribute("messError", "New password must be same confirm password!");
            response.sendRedirect("myProfile");
            return;
        } else {
            try {
                profile.setPassword(newPassword);
                profile.setModified_at(new Date());
                uDAO.updateUser(profile);
                session.setAttribute("messSuccess", "Update successfuly!");
            } catch (Exception e) {
                session.setAttribute("messError", "Update Failed!");
            }
            response.sendRedirect("myProfile");
        }

    }
}
