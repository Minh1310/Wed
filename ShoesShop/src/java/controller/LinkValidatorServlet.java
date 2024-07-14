/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.User_addressDAO;
import dal.UsersDAO;
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
 * @author Admin
 */
@WebServlet(name = "LinkValidatorServlet", urlPatterns = {"/linkvalidator"})
public class LinkValidatorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LinkValidatorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LinkValidatorServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get email, verify code and time expired
        String email = request.getParameter("email");
        String verifyCode = request.getParameter("code");
        String expires = request.getParameter("expirationTimeMillis");

        // Send error null parameter
        if (email == null || verifyCode == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
            return;
        }

        // Send error invalid time expired
        long expirationTimeMillis;
        try {
            expirationTimeMillis = Long.parseLong(expires);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid expiration time");
            return;
        }

        // Send error time expired
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > expirationTimeMillis) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Link has expired");
            return;
        }

        UsersDAO udb = new UsersDAO();
        
        String code = udb.getUserByEmail(email).getToken();

        // Send error invalid verify code
        if (!code.equals(verifyCode)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid signature");
            return;
        }

        // Link is valid send to resetpassword form
        HttpSession session = request.getSession(true);
        session.setAttribute("email", email);
        session.setAttribute("code", code);
        response.sendRedirect("resetpassword");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
