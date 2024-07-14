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
@WebServlet(name = "ProfileController", urlPatterns = {"/myProfile"})
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        // Tam thoi chua co tai khoan login
//        user = new User();
//        user.setId(1);

        UserDAO uDAO = new UserDAO();
        User profile = uDAO.getUserById(user.getId());
        if (profile == null) {
            response.sendRedirect("Error.jsp");
            return;
        }
        request.setAttribute("profile", profile);

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String country = request.getParameter("country");

        // DAO object call method CRUD  
        UserDAO uDAO = new UserDAO();
        UserAddressDAO uaDAO = new UserAddressDAO();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("account");
        // Tam thoi chua co tai khoan login
//        user = new User();
//        user.setId(1);

        User profile = uDAO.getUserById(user.getId());
        request.setAttribute("profile", profile);

        profile.setFirst_name(firstName);
        profile.setLast_name(lastName);
        UserAddress ua = new UserAddress();
        ua.setUserId(user.getId());
        ua.setCity(city);
        ua.setAddressLine(address);
        ua.setCountry(country);
        profile.setModified_at(new Date());

        try {
            uDAO.updateUser(profile);
            uaDAO.updateUserAddress(ua);
            session.setAttribute("messSuccess", "Update successfuly!");
        } catch (Exception e) {
            session.setAttribute("messError", "Update Failed!");
        }
        response.sendRedirect("home");
    }
}
