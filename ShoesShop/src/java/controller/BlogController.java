/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import controller.dashboard.*;
import dao.BlogCategoriesDAO;
import dao.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Blog;
import model.BlogCategories;
import model.Pagination;

/**
 *
 * @author tungl
 */
@WebServlet(name = "BlogController", urlPatterns = {"/blogs"})
public class BlogController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BlogDAO bDAO = new BlogDAO();
        BlogCategoriesDAO blogCDAO = new BlogCategoriesDAO();
        HttpSession session = request.getSession();
        // start param
        String search = request.getParameter("search");
        String cate = request.getParameter("cate");
        // Lấy tất cả các blog ban đầu
        List<Blog> list = bDAO.getAllBlog(null);

        session.setAttribute("listBlogCategoriess", bDAO.getBlogsByCategory());
//        session.setAttribute("listCate", blogCDAO.getAll());
        session.setAttribute("listLastBlog", bDAO.getAllBlog(3));
        // Lọc blog dựa trên tham số tìm kiếm nếu nó tồn tại
        if (search != null) {
            request.setAttribute("paramSearch", search);
            list = bDAO.filterBlog(search, null, null);
        }
        // Lọc blog dựa trên tham số danh mục nếu nó tồn tại
        if (cate != null) {
            request.setAttribute("paramCate", cate);
            list = bDAO.filterBlog(search, null, Integer.parseInt(cate));
        }
        // Xử lý phân trang
        int limitPage = 4;
        if (request.getParameter("cp") == null) {
            Pagination Page = new Pagination(list, limitPage, 1);
            Pagination<Blog> pagination = new Pagination<>(list, limitPage, 1);
            list = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
            request.setAttribute("list", pagination.getItemsOnPage());
        } else if (request.getParameter("cp") != null) {
            int cp = Integer.parseInt(request.getParameter("cp"));
            Pagination Page = new Pagination(list, limitPage, cp);
            Pagination<Blog> pagination = new Pagination<>(list, limitPage, cp);
            list = pagination.getItemsOnPage();
            session.setAttribute("page", Page);
        }
        // set URL
        request.setAttribute("pagging", "blogs");
        // Chuyển tiếp yêu cầu tới trang JSP để hiển thị
        request.setAttribute("listBlog", list);
        // Chuyển tiếp yêu cầu tới trang JSP để hiển thị
        request.getRequestDispatcher("blog.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
