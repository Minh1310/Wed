/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.SupplierCategories;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOSupplierCategories;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nhat Anh
 */
@WebServlet(name = "SupplierCategoriesControl", urlPatterns = {"/SupplierCategoriesControl"})
public class SupplierCategoriesControl extends HttpServlet {

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
            //Code to display here
            DAOSupplierCategories dao = new DAOSupplierCategories();
            switch (request.getParameter("service")) {
                case "getAll" -> { 
                    display(request, response, 
                            dao.getAll("SELECT * FROM SupplierCategories"),
                            "categories_supplier.jsp", 
                            null);
                    
                }
                case "add" -> {
                    add(request, response
                            , new SupplierCategories(
                                    Integer.parseInt(request.getParameter("SupplierCategoryID"))                           
                                    , request.getParameter("SupplierCategoryName")
                            )                                                     
                        , "categories_supplier.jsp"
                        );
                }
                case "remove" -> {
                    remove(request, response
                            , request.getParameter("SupplierCategoryID")
                            , "categories_supplier.jsp");
                }
                case "update" -> {
                    update(request, response, 
                        new SupplierCategories(
                            Integer.parseInt(request.getParameter("SupplierCategoryID"))                           
                            , request.getParameter("SupplierCategoryName")
                            )  
                    , "categories_supplier.jsp"
                    );
            }
                case "search" -> {
                    search(request, response
                            , request.getParameter("search")
                            , "categories_supplier.jsp");
                }
                default -> throw new AssertionError();
            }
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param pro
     * @param url
     */
    public void add(HttpServletRequest request
            , HttpServletResponse response
            ,SupplierCategories pro 
            , String url) {
        DAOSupplierCategories dao = new DAOSupplierCategories();
        dao.mess = dao.add(pro)>0 ? "Add success":"Add faile";
        display(request, response, 
                dao.getAll("select * from SupplierCategories"), 
                url, dao.mess);
    }

    /**
     *
     * @param request
     * @param response
     * @param id
     * @param url
     */
    public void remove(HttpServletRequest request
            , HttpServletResponse response
            ,String id 
            , String url) {
        DAOSupplierCategories dao = new DAOSupplierCategories();
        dao.mess = dao.remove(id)>0 ? "Remove success":"Remove faile";
        display(request, response, 
                dao.getAll("select * from SupplierCategories") , 
                url, dao.mess);
    }

    /**
     *
     * @param request
     * @param response
     * @param pro
     * @param url
     */
    public void update(HttpServletRequest request
            , HttpServletResponse response
            ,SupplierCategories pro 
            , String url) {
        DAOSupplierCategories dao = new DAOSupplierCategories();
        dao.mess = dao.update(pro)>0 ? "Update success":"Update faile";
        display(request, response, 
                dao.getAll("select * from SupplierCategories") , 
                url, dao.mess);   
    }
    
    /**
     * 
     * @param request
     * @param response 
     * @param search 
     * @param url 
     */
    public void search(HttpServletRequest request
            , HttpServletResponse response
            , String search,String url){
        String sql = "select * from SupplierCategories where SupplierCategoryName like '%"
                +search+"%'";
        DAOSupplierCategories dao = new DAOSupplierCategories();
        display(request, response, dao.getAll(sql), url,null);
    }

    /**
     *
     * @param request
     * @param response
     * @param ls
     * @param url
     * @param mess
     * @
     */
    public void display(HttpServletRequest request, HttpServletResponse response,
             List<SupplierCategories> ls, String url, String mess) {
        try {           
            //list elelement
            request.setAttribute("title", "SupplierCategories");
            request.setAttribute("mess", mess);
            request.setAttribute("dataAll",ls);       
            double result = (double) ls.size()/9;
            String formattedResult = String.format("%.1f", result);
            int pagination = (int)Math.ceil(Double.parseDouble(formattedResult));
            request.setAttribute("total", String.valueOf(pagination));          
            RequestDispatcher dispatch = request.getRequestDispatcher(url);
            dispatch.forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ProductsControl.class.getName())
                    .log(Level.SEVERE, null, ex);
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
