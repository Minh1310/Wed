/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.PurchaseOrders;
import entity.Suppliers;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOSuppliers;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOPurchaseOrders;

/**
 *
 * @author Nhat Anh
 */
@WebServlet(name = "SupplierControl", urlPatterns = {"/SupplierControl"})
public class SupplierControl extends HttpServlet {

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
            out.println("<title>Servlet SupplierControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form >\n"
                    + "        <input type=\"hidden\" id=\"autoClickButton\" onclick=\"showNotice()\">\n"
                    + "    </form>");
            DAOSuppliers dao = new DAOSuppliers();
            switch (request.getParameter("service")) {
                case "getAll":
                    display(request, response, 
                            dao.getAll("select * from Suppliers"),
                            "account.jsp","no");
                    break;
                case "login":
                    login(request,response, request.getParameter("userName"),
                            request.getParameter("password"));
                    break;
                case "logout":
                    logout(request, response);
                    break;
                case "add":
                    add(response, request.getParameter("userName"),
                            request.getParameter("password"));
                    break;
                case "forgotPass":
                    forgotPass(response,
                            request.getParameter("userName"));
                    break;
                case "search":
                    search(request, response, request.getParameter("search"));
                    break;
                case "remove":
                    remove(request,response,
                            request.getParameter("id"));
                    break;
                case "update":
                    update(request,response,
                            request.getParameter("userName"),
                            request.getParameter("password"));
                    break;
                case "detail":
                    DAOPurchaseOrders daop = new DAOPurchaseOrders();
                    int supplier = Integer.parseInt(request.getParameter("id"));
                    List<PurchaseOrders> ls =daop.getAll("select * from PurchaseOrders where SupplierID = "+supplier); 
                    
                    DAOSuppliers daoS = new DAOSuppliers();
                    request.setAttribute("title", "PurchaseOrders");
                    request.setAttribute("mess", null);
                    request.setAttribute("dataAll",ls);       
                    double result = (double) ls.size()/9;
                    String formattedResult = String.format("%.1f", result);
                    int pagination = (int)Math.ceil(Double.parseDouble(formattedResult));
                    request.setAttribute("total", String.valueOf(pagination)); 
                    request.setAttribute("Suppliers", daoS.getAll("select * from Suppliers")); 
                    RequestDispatcher dispatch = request.getRequestDispatcher("purchase_order.jsp");
                    dispatch.forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            out.println("<h1>Servlet SupplierControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    

    /**
     *
     * @param request
     * @param response
     * @param userName
     * @param password
     */
    public void login(HttpServletRequest request, 
            HttpServletResponse response,
            String userName, String password) {
        DAOSuppliers dao = new DAOSuppliers();
        try {
            if (dao.login(userName, password)) {
                HttpSession session=request.getSession(true);
                int login = dao.getValue("select SupplierID from Suppliers "
                        + "where SupplierName like '"+ userName+"'"
                        +" and SupplierReference like '"+password+"'");
                session.setAttribute("login", login>0?String.valueOf(login):null);
                //notice(response, login, "home.jsp");
                response.sendRedirect("home.jsp");
            } else {
                notice(response, userName + "||" + password, "index.html");
            }
        } catch (IOException ex) {
            Logger.getLogger(SupplierControl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param request
     * @param response 
     */
    public void logout(HttpServletRequest request, 
            HttpServletResponse response){
        HttpSession session=request.getSession(true);
        session.setAttribute("login", null);
        session.setAttribute("listCart", null);
        session.setAttribute("bill", null);
        try {
            response.sendRedirect("home.jsp");
        } catch (IOException ex) {
            Logger.getLogger(SupplierControl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param response
     * @param userName
     */
    public void forgotPass(HttpServletResponse response, String userName) {
        DAOSuppliers dao = new DAOSuppliers();
        List<Suppliers> ls = dao.getAll("select * from Suppliers where SupplierName like '%" + userName + "%'");
        if (ls.isEmpty()) {
            notice(response, "Account dont exist", "account_register.html");
        } else {
            notice(response, "Password: "+ls.get(0).getSupplierReference(), "index.html");
            
        }
    }

    /**
     *
     * @param response
     * @param userName
     * @param password
     */
    public void add(HttpServletResponse response, String userName, String password) {
        DAOSuppliers dao = new DAOSuppliers();
        List<Suppliers> ls = dao.getAll("SELECT MAX(SupplierID) FROM Suppliers");
        int id = 0;
        for (Suppliers a : ls) {
            id = a.getSupplierID() + 1;
        }

        try (PrintWriter out = response.getWriter()) {
            if (dao.add(new Suppliers(id, userName,
                    1,
                    "1",
                    "1", password,
                    "1",
                    "1", "1",
                    "1",
                    "1",
                    0,
                    "1",
                    "1",
                    "1",
                    "1",
                    "1",
                    "1")) > 0) {
                notice(response, "Add success", "index.html");
            } else {
                notice(response, "Add faise", "account_register.html");
            }
        } catch (IOException ex) {
            Logger.getLogger(PurchaseOrderControl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param mess
     */
    private void notice(HttpServletResponse response, String mess, String url) {
        try (PrintWriter out = response.getWriter()) {
            out.println("<script>\n"
                    + "    function showNotice() {\n"
                    + "        alert('" + mess + "');\n"
                    + "        window.location.href = '" + url + "';\n"
                    
                    + "    }\n"
                    + "    window.onload = function () {\n"
                    + "        var autoClickButton = document.getElementById('autoClickButton');\n"
                    + "        if (autoClickButton) {\n"
                    + "            autoClickButton.click();\n"
                    + "        }\n"
                    + "    };\n"
                    + "</script>");

        } catch (IOException ex) {
            Logger.getLogger(PurchaseOrderControl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param id
     */
    public void remove(HttpServletRequest request, 
            HttpServletResponse response, String id) {
        DAOSuppliers dao = new DAOSuppliers();
        dao.mess = dao.remove(id)>0 ? "Remove success":"Remove faile";
        display(request, response, 
                dao.getAll("select * from Suppliers") , 
                "account.jsp",dao.mess);

    }

    /**
     *
     * @param request
     * @param response
     * @param userName
     * @param password
     */
    public void update(HttpServletRequest request, 
            HttpServletResponse response, 
            String userName, 
            String password) {
        DAOSuppliers dao = new DAOSuppliers();
        HttpSession session=request.getSession(true);
        
        if(request.getParameter("submit")==null){
                String login = (String)session.getAttribute("login");
                int id = Integer.parseInt(login);
                List<Suppliers> ls = 
                        dao.getAll("select * from Suppliers where SupplierID = " + id );
                request.setAttribute("userName", ls.get(0).getSupplierName());
                request.setAttribute("password", (String)session.getAttribute("login"));
//                response.sendRedirect("account_update.jsp");
                display(request, response, 
                        null , 
                     "account_update.jsp",null);
        }
        else{
            int sId = Integer.parseInt(request.getParameter("ID"));   
            int mess = dao.querySql("UPDATE Suppliers\n" +
                                "SET \n" +
                                "SupplierName = '"+userName+"',\n" +
                                "SupplierReference = '"+password+"'\n" +
                                "WHERE SupplierID = "+ sId);
            //dao.mess = dao.update(pro)>0 ? "Update success":"Update faile";
            display(request, response, 
                    dao.getAll("select * from Suppliers") , 
                     "account.jsp",mess>0?"Update success":"Update fail");
        }
        
    }
    
    public static void main(String[] args) {
        DAOSuppliers dao = new DAOSuppliers();
        String userName = "hello";
        String password = "123";
        String id = "14";
        int sId = 14;
        System.out.println(
                dao.querySql("UPDATE Suppliers\n" +
                            "SET \n" +
                            "SupplierName = '"+userName+"',\n" +
                            "SupplierReference = '"+password+"'\n" +
                            "WHERE SupplierID = "+ sId));

    }
    
    /**
     *
     * @param request
     * @param response
     * @param search
     */
    public void search(HttpServletRequest request, HttpServletResponse response, String search) {
        String sql = "select * from Suppliers where SupplierName like '%"
                + search + "%'";
        DAOSuppliers dao = new DAOSuppliers();
        display(request, response, 
                dao.getAll(sql),"account.jsp",null);
    }

    /**
     *
     * @param request
     * @param response
     * @param ls
     * @param url
     * @param mess
     */
    public void display(HttpServletRequest request, 
            HttpServletResponse response, 
            List<Suppliers> ls, 
            String url, String mess) {
        try {
            //list elelement
            DAOSuppliers daoS = new DAOSuppliers();
            request.setAttribute("mess", mess);
            request.setAttribute("dataAll",ls);       
            double result = (double) ls.size()/9;
            String formattedResult = String.format("%.1f", result);
            int pagination = (int)Math.ceil(Double.parseDouble(formattedResult));
            request.setAttribute("total", String.valueOf(pagination)); 
            request.setAttribute("Suppliers", daoS.getAll("select * from Suppliers")); 
            request.setAttribute("title", "Supplier");
            RequestDispatcher dispatch = request.getRequestDispatcher(url);
            dispatch.forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(ProductsControl.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

//    public static void main(String[] args) {
//
//    }

//    public static void main(String[] args) {
//        SupplierControl control = new SupplierControl();
//        System.out.println(control.login("A Datum Corporation1", "AA20384"));
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
