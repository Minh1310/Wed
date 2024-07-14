/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Roles;
import model.User;
import model.UserStatus;

@WebServlet(name = "UserListController", urlPatterns = {"/user-list"})
public class UserListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("account");
        currentUser = new User();
        currentUser.setId(1);
//        if (currentUser != null) {
            UserDAO uDAO = new UserDAO();
            List<Roles> role = uDAO.getAllRoles();
            List<UserStatus> listStatus = uDAO.getAllStatus();
            String pageParam = request.getParameter("page");
            String searchParam = request.getParameter("search");
            String roleParam = request.getParameter("role");
            String statusParam = request.getParameter("status");
            Integer role_id = (roleParam != null && !roleParam.isEmpty()) ? Integer.valueOf(roleParam) : null;
            Integer status = (statusParam != null && !statusParam.isEmpty()) ? Integer.valueOf(statusParam) : null;

            int page = 1; // Default to the first page
            int pageSize = 5; // Set the desired page size
            if (pageParam != null && !pageParam.isEmpty()) {
                page = Integer.parseInt(pageParam);
            }

            List<User> subjects = uDAO.getAllUserWithParam(currentUser.getId(), searchParam, status, role_id);
            List<User> pagingUsers = uDAO.Paging(subjects, page, pageSize);

            request.setAttribute("users", pagingUsers);
            System.out.println(pagingUsers.size());
            request.setAttribute("listRole", role);
            request.setAttribute("listStatus", listStatus);
            request.setAttribute("totalPages", subjects.size() % pageSize == 0 ? (subjects.size() / pageSize) : (subjects.size() / pageSize + 1));
            request.setAttribute("currentPage", page);
            request.setAttribute("searchParam", searchParam);
            request.setAttribute("roleParam", roleParam);
            request.setAttribute("statusParam", statusParam);
            request.getRequestDispatcher("viewsAdmin/viewUsers.jsp").forward(request, response);
//        }else{
//            response.sendRedirect("home");
//        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
