/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Products;
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
import model.DAOProducts;
import model.DAOSuppliers;

/**
 *
 * @author Nhat Anh
 */
@WebServlet(name = "ProductsControl", urlPatterns = {"/ProductsControl"})
public class ProductsControl extends HttpServlet {

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
            
            DAOProducts dao = new DAOProducts();
            switch (request.getParameter("service")) {
                case "getAll" -> {
                    display(request, response, 
                            dao.getAll("SELECT * FROM products"), 
                            null);
                }    
                case "add" -> {
                    add(request, response
                            , new Products(
                            Integer.parseInt(request.getParameter("ProductID"))
                            , request.getParameter("ProductName")
                            , Integer.parseInt(request.getParameter("SupplierID"))
                            , request.getParameter("Color")
                            , request.getParameter("PackageType")
                            , request.getParameter("Size")
                            , Double.parseDouble(request.getParameter("TaxRate"))
                            , Double.parseDouble(request.getParameter("UnitPrice"))
                            , Double.parseDouble(request.getParameter("RecommendedRetailPrice"))
                            , Double.parseDouble(request.getParameter("TypicalWeightPerUnit")))
                            
                        , "product.jsp"
                        );
                }
                case "remove" -> {
                    remove(request, response
                            , request.getParameter("ProductID")
                            , "product.jsp");
                }
                case "update" -> {
                    update(request, response, new Products(
                        Integer.parseInt(request.getParameter("ProductID"))
                        , request.getParameter("ProductName")
                        , Integer.parseInt(request.getParameter("SupplierID"))
                        , request.getParameter("Color")
                        , request.getParameter("PackageType")
                        , request.getParameter("Size")
                        , Double.parseDouble(request.getParameter("TaxRate"))
                        , Double.parseDouble(request.getParameter("UnitPrice"))
                        , Double.parseDouble(request.getParameter("RecommendedRetailPrice"))
                        , Double.parseDouble(request.getParameter("TypicalWeightPerUnit")))
                    , "product.jsp"
                    );
                }
                case "searchPrice" ->{
                    searchPrice(request, response,
                            request.getParameter("from"),
                            request.getParameter("to"));
                }
                case "search" -> {
                    search(request, response, request.getParameter("search"));
                }
                default -> throw new AssertionError();
            }
            
        }
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param from
     * @param to 
     */
    public void searchPrice(
            HttpServletRequest request
            , HttpServletResponse response
            , String from, String to){
        String sql = "select * from Products where UnitPrice >= "+from +"and UnitPrice <= "+ to;
        DAOProducts dao = new DAOProducts();
        display(request, response, dao.getAll(sql),null);
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
            ,Products pro 
            , String url)   {
        DAOProducts dao = new DAOProducts();
        if(dao.add(pro)>0){
            dao.mess = "Add success";
        }
        else{
            dao.mess ="Add faile";
        }
        display(request, response, dao.getAll("select * from products") , dao.mess);
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
        DAOProducts dao = new DAOProducts();
        if(dao.remove(id)>0){
            dao.mess = "Remove success";
        }
        else{
            dao.mess ="Remove faile";
        }
        display(request, response, 
                dao.getAll("select * from products") , 
                dao.mess);
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
            ,Products pro 
            , String url) {
        DAOProducts dao = new DAOProducts();
        if(dao.update(pro)>0){
            dao.mess ="Update success";
        }
        else{
            dao.mess ="Update faile";
        }
        display(request, response, 
                dao.getAll("select * from products") , 
                dao.mess);  
    }
    
    /**
     * 
     * @param request
     * @param response 
     * @param search 
     */
    public void search(HttpServletRequest request, HttpServletResponse response, String search){
        String sql = "select * from Products where ProductName like '%"
                +search+"%'";
        DAOProducts dao = new DAOProducts();
        display(request, response, dao.getAll(sql),null);
    }

    /**
     *
     * @param request
     * @param response
     * @param ls
     * @param mess
     */
    public void display(HttpServletRequest request, 
            HttpServletResponse response, 
            List<Products> ls,
            String mess) {
        try {
            //list elelement
            DAOSuppliers daoS = new DAOSuppliers();
            request.setAttribute("title", "Manage Product");
            request.setAttribute("mess", mess);
            request.setAttribute("dataAll",ls);       
            request.setAttribute("Suppliers", daoS.getAll("select * from Suppliers")); 
            double result = (double) ls.size()/9;
            String formattedResult = String.format("%.1f", result);
            int pagination = (int)Math.ceil(Double.parseDouble(formattedResult));
            request.setAttribute("total", String.valueOf(pagination));
            RequestDispatcher dispatch = request.getRequestDispatcher("product.jsp");
            dispatch.forward(request, response);

        } catch (IOException | ServletException ex) {
            Logger.getLogger(ProductsControl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
//    private void notice(HttpServletResponse response, String mess) {
//        try (PrintWriter out = response.getWriter()) {
//            out.println("""
//                        <script>
//                            function showNotice() {
//                                alert('"""+ mess +"!');\n" +
//                            "    }\n" +
//                            "    window.onload = function () {\n" +
//                            "        var autoClickButton = document.getElementById('autoClickButton');\n" +
//                            "        if (autoClickButton) {\n" +
//                            "            autoClickButton.click();\n" +
//                            "        }\n" +
//                            "    };\n" +
//                            "</script>");
//        } catch (IOException ex) {
//            Logger.getLogger(ProductsControl.class.getName())
//                    .log(Level.SEVERE, null, ex);
//        }
//    }

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
