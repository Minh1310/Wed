/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;
import entity.Cart;
import entity.Products;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.DAOProducts;

/**
 *
 * @author DELL
 */
@WebServlet(name = "Cart", urlPatterns = {"/CartControl"})
public class CartControl extends HttpServlet {
    private String key = "listCart";
    
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
            List<Cart> list = new Vector<>();
            session.setAttribute(key, list);
        }
        switch(request.getParameter("service")){
            case "getAll" ->{            
                List<Cart> data = ( List<Cart>) session.getAttribute(key);
               // data.add(new Cart(2, "dcv", 1, 1));
//                session.setAttribute(key, data);
                display(request, response, session, "cart.jsp", data, null);
            }
            case "add" ->{               
                add(request, response, session,
                        "home.jsp",
                        Integer.parseInt(request.getParameter("ProductID")));
            }
            case "remove" ->{
                remove(request, response, session, "cart.jsp", 
                        Integer.parseInt(request.getParameter("ProductID")));
            }
            case "removeAll" ->{
                removeAll(request, response, session, "home.jsp");
            }
            case "update" ->{
                update(request, response, session, "cart.jsp", 
                        Integer.parseInt(request.getParameter("ProductID")));
            }      
        }
    }  
    public void add(HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            String url,
            int ProductID
    ) {
//        try {
            DAOProducts dao = new DAOProducts();
            List<Products> vector
                    = dao.getAll("select * from Products where ProductID = " + ProductID);
            Products pro = vector.get(0);          
            Cart cart = new Cart(pro.getProductID(),
                    pro.getProductName(), 1,
                    pro.getUnitPrice());
            //check id : key
            List<Cart> data = ( Vector<Cart>) session.getAttribute(key);
            
            boolean flag = true;
            for(Cart ca : data){
                if(ca.getProductID()==ProductID){
                    ca.setQuantity(ca.getQuantity()+1);
                    flag = false;
                }
            }
            
            if(flag){data.add(cart);}
       //    if(!data.isEmpty()){url=data.get(0).getProductName();}
            session.setAttribute(key, data);
            display(request, response, session, url, data, "Add success");

    }
    
    public void remove(HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession session,
            String url,
            int ProductID){
        List<Cart> data = (Vector<Cart>) session.getAttribute(key);
        for(Cart ca : data){
            if(ca.getProductID()==ProductID){
                data.remove(ca);    
                break;
                }
        }  
        
        session.setAttribute(key, data);
        display(request, response, session, url,data, null);               
    }
    
    public void removeAll(HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession session,
            String url
            ){       
        List<Cart> data = (Vector<Cart>) session.getAttribute(key);
        data.clear();
        session.setAttribute(key, data);
        display(request, response, session, "cart.jsp",data, null);
    }
    
    public void update(HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession session,
            String url,
            int ProductID){
        
        List<Cart> data = (Vector<Cart>) session.getAttribute(key);
        for(Cart ca : data){
            if(ca.getProductID()==ProductID){
                    ca.setQuantity(
                        Integer.parseInt((String)request.getParameter("quantity")));
                }
        }    
        session.setAttribute(key, data);
        display(request, response, session, "cart.jsp",data, null);
    }
    
    public void display(HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession session,
            String url,
            List<Cart> ls,
            String mess){
        try {
            int total = 0;
            if(ls!=null){
                for(Cart a : ls){
                    total+=(a.getUnitPrice()*a.getQuantity());
                }
            }
            request.setAttribute("total", total);
            request.setAttribute("key", key);
            request.setAttribute("mess", mess);
            RequestDispatcher dispath
                    = request.getRequestDispatcher(url);
            //view : run
            dispath.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CartControl.class.getName())
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
