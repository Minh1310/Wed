/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import dao.*;
import jakarta.servlet.http.HttpSession;
import model.*;
import util.Filter;

/**
 *
 * @author Nhat Anh
 */
@WebServlet(name = "PaginationProductList", urlPatterns = {"/pagination"})
public class PaginationProductList extends HttpServlet {

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
            ProductDAO productDAO = new ProductDAO();
            List<Product> list = new LinkedList<>();
            int pagination = Integer.parseInt(request.getParameter("pagination"));
            HttpSession session = request.getSession(true);
            Filter filter = (Filter)session.getAttribute("filter");
            switch (filter.getService()) {
                case "view":   
                    list = productDAO.
                            getProductOrderByDate(5 * pagination - 5, 5);
                    break;
                case "search":   
                    list = productDAO.search(filter.getNameSearch(),
                            5 * pagination - 5, 5);
                    break;
                case "viewByCategory":   
                    list = productDAO.getProductByCategory(
                            filter.getCategory(), 
                            filter.getSubCategory(), 
                            5 * pagination - 5, 5);
                    break;
                case "viewByFilter":
                    list = productDAO.getProductByFilter(filter, 
                            5 * pagination - 5, 5);
                    break;
                default:
                    response.sendRedirect("404.html");
            }       
            list.forEach(a -> {
                out.println("<div class=\"row row_item\">");
                //Status, image
                out.println("<div class=\"col-sm-4\">");
                out.println("<figure>");
                switch (a.getProductStatus().getName()) {
                    case "new" ->
                        out.println("<span class=\"ribbon new\">" + a.getProductStatus().getName() + "</span>");
                    case "hot" ->
                        out.println("<span class=\"ribbon hot\">" + a.getProductStatus().getName() + "</span>");
                    default -> {
                        if (a.getDiscount().isActive()) {
                            out.println("<span class=\"ribbon off\">-" + a.getDiscount().getDiscountPercent() + "%</span>");
                        }
                    }
                }
                out.println("<a href=\"product?service=detail&name=" + a.getName() + "\">\n"
                        + "                             <img class=\"img-fluid lazy\" src=\"" + a.getImg1() + "\" data-src=\"" + a.getImg1() + "\" alt=\"\">\n"
                        + "                              </a>");
                out.println("</figure>");
                out.println("</div>");
                //End Status, image

                //product
                out.println("<div class=\"col-sm-8\">");
                int rating = (int) a.getRatting().getRatting();

                //rating
                out.println("<div class=\"rating\">");
                for (int i = 1; i <= rating; i++) {
                    out.println("<i class=\"icon-star voted\"></i>");
                }
                for (int i = rating + 1; i <= 5; i++) {
                    out.println("<i class=\"icon-star\"></i>");
                }
                out.println("</div>");
                //End rating

                //Name, description
                out.println("<a href=\"product?service=detail&name=" + a.getName() + "\">\n"
                        + "                                            <h3>" + a.getName() + "</h3>\n"
                        + "                                        </a>");
                out.println("<p>Description for " + a.getName() + "</p>");
                //End Name, description

                //Price
                if(!a.getProductStatus().getName().equalsIgnoreCase("off")){
                    if (a.getDiscount().isActive()) {
                        out.println("<span class=\"new_price\">$" + String.format("%.2f", a.getPrice() * (100 - a.getDiscount().getDiscountPercent()) / 100) + "</span>");
                        out.println("<span class=\"old_price\">$" + a.getPrice() + "</span>");
                    } else {
                        out.println("<span class=\"new_price\">$" + a.getPrice() + "</span>");
                    }
                }
                else{
                     out.println("<p>Cant buy this product </p>");
                }
                
                //End Price

                //Add to cart
                out.println("<ul >\n"
                        + "                               <li><a href=\"\" style=\"text-decoration: none;\" class=\"btn_1\" type=\"button\" data-toggle=\"modal\" data-target=\"#exampleModal\" \n"
                        + "                                    data-name=\"" + a.getName() + "\">\n"
                        + "                                         Add to cart</a>\n"
                        + "                                    </li>\n"
                        + "<li><a class=\"btn_1 gray tooltip-1\" style=\"text-decoration: none;\" href=\"feedback?name=" + a.getName() + "\">\n"
                        + "                                                    <i class=\"ti-comment-alt\"></i>\n"
                        + "                                                    <span>Feedback</span>\n"
                        + "                                                </a>\n"
                        + "                                            </li>"
                        + "                                </ul>");
                //End add to cart

                out.println("</div>");
                //End product
                out.println("</div>");
            });
            if(list.isEmpty()){
                out.println("<p class=\"d-flex justify-content-center my-4\">Dont have any product</p>");
            }

            /* TODO output your page here. You may use following sample code. */
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
