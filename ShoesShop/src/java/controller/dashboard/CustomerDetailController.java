/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import dao.UserDAO;
import dao.UserHistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author tungl
 */
@WebServlet(name = "CustomerDetailController", urlPatterns = {"/customerDetail"})
public class CustomerDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO uDAO = new UserDAO();

        try {
// Lấy tham số id từ request
            String idRaw = request.getParameter("id");
            // Chuyển đổi tham số id từ chuỗi sang số nguyên
            int id = Integer.parseInt(idRaw);

            // Lấy người dùng từ cơ sở dữ liệu theo id            
            User b = uDAO.getUserById(id);
            if (b == null) {
                response.sendRedirect("Error.jsp");
            }
            request.setAttribute("userHistories", new UserHistoryDAO().getAll(id, null, 1, 10));
            // Thiết lập thuộc tính người dùng cho request
            request.setAttribute("u", b);
            request.getRequestDispatcher("viewsAdmin/customerDetail.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
