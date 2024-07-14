/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author Nhat Anh
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
        if (request.getParameter("service") == null) {
            view(request, response);
        } else {
            try (PrintWriter out = response.getWriter()) {
                switch (request.getParameter("service")) {
                    case "view" ->
                        view(request, response);
                    default ->
                        response.sendRedirect("404.jsp");
                }
            }
        }
    }

    /**
     * Use to get element display in home page include title, slider, top selling 
     * product, hot blogs, featured product
     * 
     * @param request servlet request
     * @param response servlet response
     */
    public void view(HttpServletRequest request, HttpServletResponse response) {

        try {
            SlidersDAO daoSlider = new SlidersDAO();
            BlogDAO bDao = new BlogDAO();
            ProductDAO productDAO = new ProductDAO();
            request.setAttribute("title", "Home");
            request.setAttribute("slider", daoSlider.getAll());
            request.setAttribute("latestBlog", bDao.getLatestBlog());
            request.setAttribute("hotBlog", bDao.getHotBlog());
            request.setAttribute("featured",
                    productDAO.getProductByRating(3, 7));
            RequestDispatcher dispatch = request.getRequestDispatcher("home.jsp");
            dispatch.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(HomeServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        CategoryDAO cDAO = new CategoryDAO();
        List<Category> ls = cDAO.getAllByStatus();
        System.out.println(ls.get(0).toString());
        System.out.println("");
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
