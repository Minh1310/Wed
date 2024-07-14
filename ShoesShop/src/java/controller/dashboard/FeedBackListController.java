/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import dao.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pagination;
import model.Ratting;

/**
 *
 * @author 84355
 */
@WebServlet(name = "FeedBackListController", urlPatterns = {"/feedbackList"})
public class FeedBackListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            FeedbackDAO fDAO = new FeedbackDAO();

            // Get the parameters from the request
            String comment = request.getParameter("comment");
            String name = request.getParameter("name");
            String status = request.getParameter("status");
            String ratingString = request.getParameter("rating");
            String productname = request.getParameter("productname");

            HttpSession session = request.getSession();
            Integer rating = null;

            // Convert ratingString to Integer if it's not null or empty
            if (ratingString != null && !ratingString.isEmpty()) {
                rating = Integer.parseInt(ratingString);
            }
            List<Ratting> list = fDAO.getAllRatingFilter(status, rating, name, comment, productname);

            // start pagging
            int limitPage = 10;
            if (request.getParameter("cp") == null) {
                Pagination Page = new Pagination(list, limitPage, 1);
                Pagination<Ratting> pagination = new Pagination<>(list, limitPage, 1);
                list = pagination.getItemsOnPage();
                session.setAttribute("page", Page);
                request.setAttribute("list", pagination.getItemsOnPage());
            } else if (request.getParameter("cp") != null) {
                int cp = Integer.parseInt(request.getParameter("cp"));
                Pagination Page = new Pagination(list, limitPage, cp);
                Pagination<Ratting> pagination = new Pagination<>(list, limitPage, cp);
                list = pagination.getItemsOnPage();
                session.setAttribute("page", Page);
            }
            // set URL
            request.setAttribute("pagging", "feedbackList");
            session.setAttribute("rating", ratingString);
            session.setAttribute("status", status);
            session.setAttribute("name", name);
            session.setAttribute("comment", comment);
            session.setAttribute("productname", productname);
            // end pagging
            request.setAttribute("listFeedBack", list);
            request.getRequestDispatcher("viewsAdmin/viewFeedback.jsp").forward(request, response);
        } catch (SQLException ex) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
