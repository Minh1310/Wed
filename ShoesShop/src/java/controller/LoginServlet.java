/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.Encrypt;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("home").forward(request, response);
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
        //Get parameter from login form
        String email = request.getParameter("email");
        String password_raw = request.getParameter("password");
        String password = Encrypt.toSHA1(password_raw);
        String email_forgot = request.getParameter("email_forgot");

        UsersDAO udb = new UsersDAO();
        User u = udb.getUserByEmail(email);
        HttpSession session = request.getSession();
        
        //Check null parameter
        if (email_forgot == null || email_forgot.equals("")) {
            
            //If email forgot null send to home
            if (email == null || email.equals("")
                    || password == null || password.equals("")) {
                session.setAttribute("error", "Username or Password Don't Allow Blank!");
                request.getRequestDispatcher("home").forward(request, response);
                
                // Check password
            } else if (u.getPassword().equals(password)) {
                
                //Check active account
                if (u.getStatus_id() == 1) {
                    session.setAttribute("account", u);
                    response.sendRedirect("home");
                } else {
                    session.setAttribute("error", "Account is InActive!");
                    request.getRequestDispatcher("home").forward(request, response);
                }
            } else {
                session.setAttribute("error", "Username or Password Invalid!");
                request.getRequestDispatcher("home").forward(request, response);
            }
            
        // if forgot email not null send to reset password
        } else {
            session.setAttribute("email_forgot", email_forgot);
            response.sendRedirect("resend");
        }
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
