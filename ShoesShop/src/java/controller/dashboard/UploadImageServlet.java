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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
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
@WebServlet("/uploadImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 15)    // 15 MB
public class UploadImageServlet extends HttpServlet {
   
    private static final String UPLOAD_DIR = "img/products";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CategoryDAO cdb = new CategoryDAO();
        SubCategoryDAO scdb = new SubCategoryDAO();
        ProductsDAO pdb = new ProductsDAO();
        ProductStatusDAO psdb = new ProductStatusDAO();
        DiscountDAO ddb = new DiscountDAO();
        BrandDAO bdb = new BrandDAO();

        String productName = request.getParameter("title");
        String productName2 = productName.replaceAll("\\s", "");

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

        // Lấy tên san pham từ form
        String nameFileString = "Image2_" + productName2;

        // Đường dẫn thực tế trên máy chủ
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        // Tạo thư mục nếu chưa tồn tại
        File uploadDir = new File(uploadFilePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Nhận file từ form
        Part filePart = request.getPart("file");
        
        String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.')).toLowerCase();
        
        String newFileName = nameFileString + fileExtension;

        // Đường dẫn lưu file
        String filePath = uploadFilePath + File.separator + newFileName;
        filePart.write(filePath);

        try {
            pdb.updateProductImage("img/products/" + newFileName, productName);
        } catch (NullPointerException e) {
            session.removeAttribute("messSuccess");
            session.setAttribute("messError", "Update Failed!");

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
            session.setAttribute("messSuccess", "Update successfuly!");

            request.getRequestDispatcher("viewsAdmin/updateProduct.jsp").forward(request, response);
        }

        product = pdb.getProductByName(productName);
        
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
        session.setAttribute("messSuccess", "Update successfuly!");

        request.getRequestDispatcher("viewsAdmin/updateProduct.jsp").forward(request, response);
    }

}
