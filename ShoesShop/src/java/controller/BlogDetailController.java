/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Blog;

/**
 *
 * @author 84355
 */
@WebServlet(name = "BlogDetailController", urlPatterns = {"/blogDetail"})
public class BlogDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Chuyển tiếp yêu cầu tới trang JSP để hiển thị
        String idRaw = request.getParameter("id");

        BlogDAO bDAO = new BlogDAO();

        try {
            // Chuyển đổi tham số id từ chuỗi sang số nguyên            
            int id = Integer.parseInt(idRaw);
            Blog b = bDAO.getByID(id);
            // Chuyển đổi tham số id từ chuỗi sang số nguyên            
            if (b == null) {
                response.sendRedirect("Error.jsp");
            }
            // Thiết lập thuộc tính blog cho request
            request.setAttribute("blog", b);
        } catch (Exception e) {
            response.sendRedirect("Error.jsp");
        }
        // Chuyển tiếp request và response đến trang blog_detail.jsp để hiển thị chi tiết blog
        request.getRequestDispatcher("blog_detail.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
