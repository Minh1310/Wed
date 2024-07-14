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
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/updateProduct"})
public class UpdateProductServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProductServlet at " + request.getContextPath() + "</h1>");
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

        ProductsDAO pdb = new ProductsDAO();
        HttpSession session = request.getSession();

        String status_raw = request.getParameter("status");
        String productName = request.getParameter("productName");
        int status = 0;

        try {
            status = Integer.parseInt(status_raw);
            pdb.updateProductStatus(status, productName);

            session.setAttribute("messSuccess", "Update successfuly!");
        } catch (NumberFormatException e) {
            session.setAttribute("messError", "Update Failed!");
            response.sendRedirect("productList");
        }
        response.sendRedirect("productList");
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
        HttpSession session = request.getSession();
        CategoryDAO cdb = new CategoryDAO();
        SubCategoryDAO scdb = new SubCategoryDAO();
        ProductStatusDAO psdb = new ProductStatusDAO();
        ProductsDAO pdb = new ProductsDAO();
        DiscountDAO ddb = new DiscountDAO();
        BrandDAO bdb = new BrandDAO();

        String img1 = request.getParameter("thumbnail");
        String img2 = request.getParameter("img2");
        String name = request.getParameter("title");
        String oldName = request.getParameter("oldName");
        String[] subCategoryId_raw = request.getParameterValues("subcategory");
        String description = request.getParameter("description");
        String price_raw = request.getParameter("price");
        String discount_raw = request.getParameter("discount");
        String status_raw = request.getParameter("status");
        String brand_raw = request.getParameter("brand");
        List<Integer> subCategoryId = new ArrayList<>();

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
        try {
            listBrand = bdb.getAll();
        } catch (SQLException e) {
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
        ProductDTO product = pdb.getProductByName(oldName);

        //List size
        List<String> listSize = new ArrayList<>();
        listSize.add("39");
        listSize.add("40");
        listSize.add("41");
        listSize.add("42");
        listSize.add("43");

        //List color
        List<String> listColor = pdb.getListColorByProduct(oldName);

        // List one product
        List<ProductDTO> listOneProduct = pdb.getListProductByName(oldName);

        try {
            for (String s : subCategoryId_raw) {
                int number = Integer.parseInt(s);
                subCategoryId.add(number);
            }
            session.setAttribute("messSuccess", "Update successfuly!");
        } catch (NumberFormatException e) {
            session.removeAttribute("messSuccess");
            session.setAttribute("messError", "Update Failed!");
            request.getRequestDispatcher("viewsAdmin/updateProduct.jsp").forward(request, response);
        }

        //Update category
        pdb.deleteProductSubCate(oldName);

        for (ProductDTO p : listOneProduct) {
            for (int n : subCategoryId) {
                pdb.insertProductSubCate(p.getId(), n);
            }
        }

        // List of subcategory of one product after update category
        int[] listSubCategoryOfProduct = scdb.getSubCategoryByProduct(oldName);

        //Update quantity
        for (ProductDTO p : listOneProduct) {
            String quantity_raw = request.getParameter(p.getSize() + "of" + p.getColor());
            try {
                int quantity = Integer.parseInt(quantity_raw);
                
                if(quantity<0){
                    throw new NumberFormatException();
                }
                
                pdb.updateQuantityProduct(quantity, p.getName(), p.getSize(), p.getColor());
                session.setAttribute("messSuccess", "Update successfuly!");
            } catch (NumberFormatException e) {

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

                session.removeAttribute("messSuccess");
                session.setAttribute("messError", "Update Failed!");
                request.getRequestDispatcher("viewsAdmin/updateProduct.jsp").forward(request, response);
            }
        }

        //Update
        int discountId = 0;
        int statusId = 0;
        float price = 0;
        int brandId = 0;

        if (pdb.getProductByName(name) != null && !oldName.equals(name)) {
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

            session.removeAttribute("messSuccess");
            session.setAttribute("messError", "Product Existed!");
            request.getRequestDispatcher("viewsAdmin/updateProduct.jsp").forward(request, response);
        } else {
            if (img1 == null || img1.equals("")
                    || img2 == null || img2.equals("")
                    || discount_raw == null || discount_raw.equals("")
                    || status_raw == null || status_raw.equals("")
                    || price_raw == null || price_raw.equals("")
                    || brand_raw == null || brand_raw.equals("")
                    || name == null || name.equals("")
                    || description == null || description.equals("")) {
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

                session.removeAttribute("messSuccess");
                session.setAttribute("messError", "Update Failed!");
                request.getRequestDispatcher("viewsAdmin/updateProduct.jsp").forward(request, response);
            }

            try {
                discountId = Integer.parseInt(discount_raw);
                statusId = Integer.parseInt(status_raw);
                price = Float.parseFloat(price_raw);
                brandId = Integer.parseInt(brand_raw);
                
                if(price<0){
                    throw new NumberFormatException();
                }

                ProductDTO p = new ProductDTO(discountId, statusId, brandId, name, price, description, img1, img2);

                pdb.updateProduct(p, oldName);

                session.setAttribute("messSuccess", "Update successfuly!");
            } catch (NumberFormatException e) {
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

                session.removeAttribute("messSuccess");
                session.setAttribute("messError", "Update Failed!");
                request.getRequestDispatcher("viewsAdmin/updateProduct.jsp").forward(request, response);
            }
        }

        //after update
        listOneProduct = pdb.getListProductByName(name);
        product = pdb.getProductByName(name);

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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
