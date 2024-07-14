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
import model.Ratting;

/**
 *
 * @author 84355
 */
@WebServlet(name = "FeedbackDetailController", urlPatterns = {"/feedbackDetail"})
public class FeedbackDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FeedbackDAO fD = new FeedbackDAO();
        String idRaw = request.getParameter("id");
        try {

            // Parse the 'id' parameter to an integer
            int id = Integer.parseInt(idRaw);
            
            // Retrieve the feedback details using the id
            Ratting r = fD.getFeedbackById(id);

            if (r == null) {
                response.sendRedirect("Error.jsp");
            }
            request.setAttribute("feedback", r);
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
        }
        request.getRequestDispatcher("viewsAdmin/feedbackDetail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
