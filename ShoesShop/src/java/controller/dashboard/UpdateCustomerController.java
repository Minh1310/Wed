/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import model.User;

/**
 *
 * @author tungl
 */
@WebServlet(name = "UpdateCustomerController", urlPatterns = {"/updateCustomer"})
public class UpdateCustomerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data

        String idRaw = request.getParameter("id");

        UserDAO uDAO = new UserDAO();
        
        // Lấy danh sách tất cả các vai trò và trạng thái người dùng và đặt vào thuộc tính request
        request.setAttribute("listRole", uDAO.getAllRoles());
        request.setAttribute("listStatus", uDAO.getAllStatus());
        
        // Nếu không có tham số id (trường hợp tạo mới người dùng)
        if (idRaw == null) {
            request.getRequestDispatcher("viewsAdmin/updateCustomer.jsp").forward(request, response);

        } else {
            
            // Nếu có tham số id (trường hợp cập nhật thông tin người dùng)
            // Lấy người dùng từ cơ sở dữ liệu theo id
            User u = uDAO.getUserById(Integer.parseInt(idRaw));
            
            // Kiểm tra nếu người dùng không tồn tại, chuyển hướng đến trang lỗi
            if (u == null) {
                response.sendRedirect("Error.jsp");
            } else {
                // Nếu người dùng tồn tại, đặt thuộc tính user cho request
                request.setAttribute("user", u);
                request.getRequestDispatcher("viewsAdmin/updateCustomer.jsp").forward(request, response);
            }
        }
    }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Retrieve form data
        String idRaw = request.getParameter("id");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telephone = request.getParameter("telephone");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        int roleId = Integer.parseInt(request.getParameter("role"));
        int satusId = Integer.parseInt(request.getParameter("status"));

        UserDAO uDAO = new UserDAO();
        // list role
        request.setAttribute("listRole", uDAO.getAllRoles());
        request.setAttribute("listStatus", uDAO.getAllStatus());

        // Create a User object
        User user = new User();
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setGender(gender);
        user.setRole_id(roleId);
        user.setStatus_id(satusId);
        user.setCreated_at(new Date());
        user.setModified_at(new Date());

        if (idRaw.isBlank() || idRaw.isEmpty() || idRaw == null) {
            try {
                uDAO.addUser(user);
                session.setAttribute("messSusess", "Create User Success!");
                response.sendRedirect("customerList");
                return;
            } catch (IOException e) {
                session.setAttribute("messError", "Create User Failed!");
            }
        } else {
            User uUpdate = uDAO.getUserById(Integer.parseInt(idRaw));
            uUpdate.setFirst_name(firstName);
            uUpdate.setLast_name(lastName);
            uUpdate.setEmail(email);
            uUpdate.setPassword(password);
            uUpdate.setTelephone(telephone);
            uUpdate.setGender(gender);
            uUpdate.setRole_id(roleId);
            uUpdate.setStatus_id(satusId);
//            uUpdate.setCreated_at(new Date());
            uUpdate.setModified_at(new Date());

            try {
                session.setAttribute("messSuccess", "Update User Success!");
                uDAO.updateUser(uUpdate);
                
            } catch (Exception e) {
                session.removeAttribute("messSuccess");
                session.setAttribute("messError", "Update User Failed!");
            }
        }
        response.sendRedirect("updateCustomer?id=" + idRaw);

    }
}
