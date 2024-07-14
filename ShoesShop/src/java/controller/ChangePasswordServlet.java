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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.User;
import util.Encrypt;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/changepassword"})
public class ChangePasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangePasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    public static boolean isValidPassword(String password) {
        /*
        It contains at least one digit (1).
        It contains at least one lowercase letter (b).
        It contains at least one uppercase letter (A).
        It contains at least one special character (@).
        It has no whitespace.
        Its length is within the specified range (8 characters).
         */

        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }

        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);

        // Return if the password
        // matched the ReGex
        return m.matches();
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
        processRequest(request, response);
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
        
        // Get password from Change password form
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        HttpSession session = request.getSession();
        UsersDAO udb = new UsersDAO();
        
        // Get aacount from session
        Object obj2 = session.getAttribute("account");
        User u = new User();
        if (obj2 != null) {
            u = (User) obj2;
        }
        if(obj2 == null){
            session.setAttribute("error", "Login Before Change Password");
            request.getRequestDispatcher("home").forward(request, response);
        }
        
        // Check null parameter
        if(currentPassword == null || currentPassword.equals("")
                || newPassword == null || newPassword.equals("")
                || confirmNewPassword == null || confirmNewPassword.equals("")){
            session.setAttribute("error", "Not Empty");
            request.getRequestDispatcher("home").forward(request, response);
            
            // Check new password and confirm new password
        }else if(!(newPassword.equals(confirmNewPassword))){
            session.setAttribute("error", "New Password and Comfirm Password don't match");
            request.getRequestDispatcher("home").forward(request, response);
            
            // Check newpassword format and confirm new password format
        }else if(!isValidPassword(newPassword) || !isValidPassword(confirmNewPassword)){
            session.setAttribute("error", "Wrong Password Format");
            request.getRequestDispatcher("home").forward(request, response);
            
            //Check current password
        }else if(!(u.getPassword().equals(Encrypt.toSHA1(currentPassword)))){
            session.setAttribute("error", "Wrong Current Password");
            request.getRequestDispatcher("home").forward(request, response);
            
            // set new password
        }else{
            u.setPassword(Encrypt.toSHA1(newPassword));
            
            udb.updateUser(u);
            response.sendRedirect("logout");
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
