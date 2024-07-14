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

/**
 *
 * @author 84355
 */
@WebServlet(name = "ChangeStatusController", urlPatterns = {"/feedbackStatus"})
public class ChangeStatusController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("status");
            String mode = request.getParameter("mode");
            FeedbackDAO dao = new FeedbackDAO();
            HttpSession session = request.getSession();
            String messSuccess;
            switch (mode) {
                case "status":
                    // Change the feedback status in the database
                    dao.changeRatingStatus(id, status);

                    // Set the success message
                    messSuccess = "Change feedback status successfully!";

                    // Store the success message in the session
                    session.setAttribute("messSuccess", messSuccess);

                    // Redirect the user to the feedback list page
                    response.sendRedirect("feedbackList");

                    break;
                case "image":
                    // Change the feedback image status in the database
                    dao.changeRatingStatusImage(id, status);
                    
                    // Set the success message
                    messSuccess = "Change image status successfully!";
                    
                    // Store the success message in the session
                    session.setAttribute("messSuccess", messSuccess);
                    
                    // Redirect the user to the feedback detail page with the specific feedback id
                    response.sendRedirect("feedbackDetail?id=" + id);
                    break;
            }
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }
}
