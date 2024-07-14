/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SlidersDAO;
import dao.*;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SubProducts;
import util.Filter;
import util.Support;

/**
 *
 * @author Nhat Anh
 */
@WebServlet(name = "SubProductServlet", urlPatterns = {"/product"})
public class SubProductServlet extends HttpServlet {

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
            switch (request.getParameter("service")) {
                case "detail" ->
                    detail(request, response);
                case "view" ->
                    view(request, response);
                case "viewByCategory" ->
                    viewByCategory(request, response);
                case "search" ->
                    search(request, response);
                case "getAllColorByName" ->
                    getAllColorByName(request, response);
                case "getAllSizeByName" ->
                    getAllSizeByName(request, response);
                case "viewByFilter" ->
                    viewByFilter(request, response);
                default ->
                    response.sendRedirect("404.jsp");
            }
        }
    }
    
    /**
     * 
     * @param request
     * @param response 
     */
    public void getAllColorByName(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            ProductDAO productDAO = new ProductDAO();
            String name = request.getParameter("name").replaceAll("_", " ");
            List<String> ls = productDAO.getAllColorByName(name);
            ls.forEach(a -> {
                out.print("<option value=\"" + a + "\">" + a + "</option>");
            });
        } catch (IOException ex) {
            Logger.getLogger(SubProductServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param request
     * @param response 
     */
    public void getAllSizeByName(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            ProductDAO productDAO = new ProductDAO();
            String name = request.getParameter("name").replaceAll("_", " ");
            List<Integer> ls = productDAO.getAllSizeByName(name);
            ls.forEach(a -> {
                out.print("<option value=\"" + a + "\">" + a + "</option>");
            });

        } catch (IOException ex) {
            Logger.getLogger(SubProductServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     *
     * @param request servlet request
     * @param response servlet response
     */
    public void view(HttpServletRequest request, HttpServletResponse response) {
        try {
            SlidersDAO daoSlider = new SlidersDAO();
            dataForSider(request, response);
            request.setAttribute("slider", daoSlider.getRadom());
            HttpSession session= request.getSession(true);  
            Filter filter = new Filter("view",null,null, 
                    null, null, null, null);
            session.setAttribute("filter", filter);       
            RequestDispatcher dispatch = request.getRequestDispatcher("product_list.jsp");
            dispatch.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(SubProductServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     *
     * @param request servlet request
     * @param response servlet response
     */
    public void viewByCategory(HttpServletRequest request, HttpServletResponse response) {
        try {
            SlidersDAO daoSlider = new SlidersDAO();
            dataForSider(request, response);
            request.setAttribute("slider", daoSlider.getRadom());
            HttpSession session= request.getSession(true); 
            Filter filter = new Filter("viewByCategory",
                    null,
                    request.getParameter("category"), 
                    request.getParameter("subCategory"), 
                    null, null, null);
            session.setAttribute("filter", filter);       
            RequestDispatcher dispatch = request.getRequestDispatcher("product_list.jsp");
            dispatch.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(SubProductServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Use take information of product for selected product from wed
     *
     * @param request servlet request
     * @param response servlet response
     */
    public void detail(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProductDAO productDAO = new ProductDAO();
            SlidersDAO daoSlider = new SlidersDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            dataForSider(request, response);
            List<SubProducts> ls = productDAO.searchUniqueName(
                    request.getParameter("name"));
            String category = Support.print(
                    categoryDAO.getCategoryByNameProduct(
                            request.getParameter("name")));
            request.setAttribute("category", category);
            request.setAttribute("product",ls==null||ls.isEmpty()?null:ls.get(0));
            request.setAttribute("slider", daoSlider.getRadom());
            RequestDispatcher dispatch = request.getRequestDispatcher("product_detail.jsp");
            dispatch.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(SubProductServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Use to set data for sider of wed
     *
     * @param request servlet request
     * @param response servlet response
     */
    public static void dataForSider(HttpServletRequest request, HttpServletResponse response) {
        ProductDAO productDAO = new ProductDAO();
        BrandDAO dAOBrands = new BrandDAO();
        CategoryDAO dAOCategories = new CategoryDAO();
        request.setAttribute("colorSider", productDAO.getAllColor().toArray());
        request.setAttribute("brandSider", dAOBrands.getAllByStatus());
        request.setAttribute("categorySider", dAOCategories.getHierarchyCategory());
        request.setAttribute("latestProduct",
                productDAO.getProductLatest( 3));
        request.setAttribute("TOTAL_PAGINATION", 
                productDAO.getTotalProduct());
    }

    /**
     *
     *
     * @param request servlet request
     * @param response servlet response
     */
    public void search(HttpServletRequest request, HttpServletResponse response) {
        try {   
            SlidersDAO daoSlider = new SlidersDAO();
            dataForSider(request, response);
            request.setAttribute("slider", daoSlider.getRadom());
            HttpSession session= request.getSession(true);  
            Filter filter = new Filter("search", 
                    request.getParameter("name"),
                    null, null, null, null, null);
            session.setAttribute("filter", filter);
            RequestDispatcher dispatch = request.getRequestDispatcher("product_list.jsp");
            dispatch.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(SubProductServlet.class.getName())
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
    /**
     * 
     * @param request
     * @param response 
     */
    private void viewByFilter(HttpServletRequest request, HttpServletResponse response) {
        try {
            SlidersDAO daoSlider = new SlidersDAO();
            dataForSider(request, response);
            request.setAttribute("slider", daoSlider.getRadom());
            HttpSession session= request.getSession(true);
            Filter filter = (Filter)session.getAttribute("filter");
            if(filter==null){
                filter = new Filter();
            }
            filter.setService("viewByFilter");
            filter.setColor(request.getParameterValues("color"));
            filter.setBrand(request.getParameterValues("brand"));
            filter.setPrice(request.getParameterValues("price"));
            session.setAttribute("filter", filter);       
            RequestDispatcher dispatch = request.getRequestDispatcher("product_list.jsp");
            dispatch.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(SubProductServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
