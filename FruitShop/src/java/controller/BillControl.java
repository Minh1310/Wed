/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Bill;
import entity.Cart;
import entity.PurchaseOrderLines;
import entity.PurchaseOrders;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOPurchaseOrderLines;
import model.DAOPurchaseOrders;

/**
 *
 * @author Nhat Anh
 */
@WebServlet(name = "BillControl", urlPatterns = {"/BillControl"})
public class BillControl extends HttpServlet {
    private String key = "bill";
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
        HttpSession session=request.getSession(true);
        if(session.getAttribute(key)==null){
                List<Bill> bill = new Vector<>();
                session.setAttribute(key, bill);
            }
        try (PrintWriter out = response.getWriter()) {
            switch(request.getParameter("service")){
                case "add" ->{
                    add(request, response, session, 
                        (List<Cart>)session.getAttribute("listCart"),
                        "home.jsp");
                }
                case "update" ->{
                    update(request, response, session,
                            (List<Bill>)session.getAttribute(key), 
                            "billDetail.jsp");
                }
                
                case "detail" ->{
                    display(request, response, session, "billDetail.jsp");
                }
            }
        }
    }
//    public static void main(String[] args) {
//        DAOPurchaseOrders dao = new DAOPurchaseOrders();
//        
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String date = currentDateTime.format(formatter);
//        String dateExpect = currentDateTime.plusDays(5).format(formatter);
//        
//        if(dao.add(new PurchaseOrders(id, 1, date,
//                "oki", dateExpect,
//                "0123456", 0))==0){
//                System.out.println("hello");
//                
//            }
//    }
    
    
    public void add(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            List<Cart> ls,
            String url){
        if(ls.isEmpty()){
            display(request, response, session, url);
        }
        DAOPurchaseOrderLines dao = new DAOPurchaseOrderLines();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = currentDateTime.format(formatter);
        String dateExpect = currentDateTime.plusDays(10).format(formatter);
        int id = dao.getValue("SELECT MAX(PurchaseOrderLineID) FROM PurchaseOrderLines");
        ls.forEach( a ->{
            dao.add(new PurchaseOrderLines(1, 1,
                    a.getProductID(), a.getQuantity(),
                    "oki", a.getQuantity(), 
                    dateExpect, 0));
        });
        
        List<Bill> list = (List<Bill>)session.getAttribute(key);
        
        list.add(new Bill(id+1,
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("phone"), 
                request.getParameter("address"), 
                date, 
                (String)request.getParameter("total"), 
                0, ls));
        
        session.setAttribute(key, list);
        display(request, response, session,url);
    }
    
    public void update(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            List<Bill> ls,
            String url){
        DAOPurchaseOrderLines daopcl = new DAOPurchaseOrderLines();
        DAOPurchaseOrders daopo = new DAOPurchaseOrders();
        int index = Integer.parseInt(request.getParameter("index"));
        int billID = Integer.parseInt(request.getParameter("id"));
        ls.get(index).setStatus(1);
        int purchaseOrderID = daopcl.getValue("SELECT MAX(PurchaseOrderID) FROM PurchaseOrders");
        int supplierID = Integer.parseInt((String)session.getAttribute("login"));
        for(int i = billID; i<=billID-1+ls.size();i++){
            purchaseOrderID++;
            daopcl.querySql("""
                            UPDATE [dbo].[PurchaseOrderLines]
                               SET [PurchaseOrderID] = """+purchaseOrderID+"\n" +
                            "      ,[IsOrderLineFinalized] = 1\n" +
                            " WHERE [PurchaseOrderLineID] = "+billID);
            daopo.add(new PurchaseOrders(
                    purchaseOrderID, supplierID,
                    ls.get(index).getDate(),
                    "oki", ls.get(index).getDate(), 
                    "13102003", 0)); 
        } 
        display(request, response, session, url);
    }
    
    public void display(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,String url){
        try {
            String index = request.getParameter("index");
            List<Bill> list = (List<Bill>)session.getAttribute(key);
            if(index!=null){
                request.setAttribute("data", 
                        list.get(Integer.parseInt(index)));
                request.setAttribute("index", index);
            }
            RequestDispatcher dispath
                    = request.getRequestDispatcher(url);
            //view : run
            dispath.forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(BillControl.class.getName())
                    .log(Level.SEVERE, null, ex);
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
