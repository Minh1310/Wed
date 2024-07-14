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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.User;
import util.Encrypt;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/resetpassword"})
public class ResetPasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet ResetPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordServlet at " + request.getContextPath() + "</h1>");
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
        UsersDAO udb = new UsersDAO();
        HttpSession session = request.getSession();

        // get email from session
        Object obj = session.getAttribute("email");
        String email = "";
        if (obj != null) {
            email = (String) obj;
        }

        // Get user by email
        Object obj2 = udb.getUserByEmail(email);
        User u = new User();
        if (obj2 != null) {
            u = (User) obj2;
        }
        
        // Get verify code form session
        Object obj3 = session.getAttribute("code");
        String verifyCode = "";
        if (obj3 != null) {
            verifyCode = (String) obj3;
        }

        // Check null user get by email
        if (obj2 == null) {
            session.setAttribute("error", "Account Not Existed!");
            request.getRequestDispatcher("home").forward(request, response);
        }

        // Check verify token from user
        if (verifyCode.equals(u.getToken())) {
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        } else {
            session.setAttribute("error", "Reset Password Failed!");
            request.getRequestDispatcher("home").forward(request, response);
        }

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
        
        //get parameter from resetpassword form
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        UsersDAO udb = new UsersDAO();
        HttpSession session = request.getSession();

        // Get email form session
        Object obj = session.getAttribute("email");
        String email = "";
        if (obj != null) {
            email = (String) obj;
        }

        //get user by email
        Object obj2 = udb.getUserByEmail(email);
        User u = new User();
        if (obj2 != null) {
            u = (User) obj2;
        }
        
        //Check null parameter
        if (password == null || password.equals("")
                || confirmPassword == null || confirmPassword.equals("")) {
            request.setAttribute("error", "Password And Confirm Password Empty!");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            
            // Check new password and confirm new password
        }else if(!password.equals(confirmPassword)){
            request.setAttribute("error", "Password And Confirm Password Don't Match!");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            
            // Check valid password form
        }else if(!isValidPassword(password)){
            request.setAttribute("error", "Wrong Password Format");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            
            // Set new password
        }else{
            u.setPassword(Encrypt.toSHA1(password));
            udb.updateUser(u);
            
            response.sendRedirect("home");
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
