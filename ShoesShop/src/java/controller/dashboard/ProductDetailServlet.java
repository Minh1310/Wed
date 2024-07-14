/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import dao.BrandDAO;
import dao.CategoryDAO;
import dao.DiscountDAO;
import dao.ProductStatusDAO;
import dao.ProductsDAO;
import dao.SubCategoryDAO;
import dto.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Category;
import model.Discount;
import model.ProductStatus;
import model.SubCategory;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/productDetail"})
public class ProductDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet ProductDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDetailServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        CategoryDAO cdb = new CategoryDAO();
        SubCategoryDAO scdb = new SubCategoryDAO();
        ProductStatusDAO psdb = new ProductStatusDAO();
        ProductsDAO pdb = new ProductsDAO();
        DiscountDAO ddb = new DiscountDAO();
        BrandDAO bdb = new BrandDAO();

        String productName = request.getParameter("productName");

        //get list category
        List<Category> categories = new ArrayList<>();
        try {
            categories = cdb.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get list sub category
        List<SubCategory> subCategories = new ArrayList<>();
        try {
            subCategories = scdb.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        //get list brand
        List<Brand> listBrand = new ArrayList<>();
        try{
            listBrand = bdb.getAll();
        }catch(SQLException e){
            e.printStackTrace();
        }

        //get list product status
        List<ProductStatus> listProductStatus = new ArrayList<>();
        try {
            listProductStatus = psdb.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //get list discount
        List<Discount> listDiscount = ddb.getAllDiscount();

        // get product by name
        ProductDTO product = pdb.getProductByName(productName);

        //List size
        List<String> listSize = new ArrayList<>();
        listSize.add("39");
        listSize.add("40");
        listSize.add("41");
        listSize.add("42");
        listSize.add("43");

        //List color
        List<String> listColor = pdb.getListColorByProduct(productName);
        
        // List one product
        List<ProductDTO> listOneProduct = pdb.getListProductByName(productName);
        
        // List of subcategory of one product
        int[] listSubCategoryOfProduct = scdb.getSubCategoryByProduct(productName);

        request.setAttribute("product", product);

        session.setAttribute("categories", categories);
        session.setAttribute("subCategories", subCategories);
        session.setAttribute("listProductStatus", listProductStatus);
        session.setAttribute("listDiscount", listDiscount);
        session.setAttribute("listSize", listSize);
        session.setAttribute("listColor", listColor);
        session.setAttribute("listOneProduct", listOneProduct);
        session.setAttribute("listBrand", listBrand);
        session.setAttribute("listSubCategoryOfProduct", listSubCategoryOfProduct);

        request.getRequestDispatcher("viewsAdmin/updateProduct.jsp").forward(request, response);
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
