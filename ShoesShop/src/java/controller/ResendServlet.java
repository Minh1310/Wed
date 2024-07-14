/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.UsersDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.Email;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResendServlet", urlPatterns = {"/resend"})
public class ResendServlet extends HttpServlet {

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
            out.println("<title>Servlet ResendServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResendServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true); // create a session if one doesn't exist
        UsersDAO udb = new UsersDAO();

        // get email forgot from form
        Object obj = session.getAttribute("email_forgot");
        String email_forgot = "";
        if (obj != null) {
            email_forgot = (String) obj;
        }

        // Check null email forgot
        if (email_forgot.equals("")) {
            session.setAttribute("error", "Reset Password Failed!");
            request.getRequestDispatcher("home").forward(request, response);
        }
        
        // Check null email 
        if(udb.getUserByEmail(email_forgot) == null){
            session.setAttribute("error", "Account Not Existed!");
            request.getRequestDispatcher("home").forward(request, response);
        }

        // Set timr expired for email
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + (60 * 1000);

        // Link of resetpassword
        String code = Email.getRandomNumber();
        String context = request.getScheme()
                + "://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + "/SWP391/linkvalidator";

        User u = udb.getUserByEmail(email_forgot);
        u.setToken(code);

        //send email
        Email.sendEmail2(email_forgot, "Reset Your Password", context, code, expirationTimeMillis);
        session.setAttribute("code", code);

        udb.updateUserToken(u);

        request.getRequestDispatcher("resend.jsp").forward(request, response);

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
