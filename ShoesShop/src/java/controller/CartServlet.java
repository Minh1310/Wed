package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dao.CartItemDAO;
import dao.ProductDAO;
import dao.ShoppingSessionDAO;
import dao.SlidersDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartItem;
import model.Product;
import model.User;

/**
 *
 * @author Nhat Anh
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

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

        if (request.getParameter("service") == null) {
            SlidersDAO daoSlider = new SlidersDAO();
            SubProductServlet.dataForSider(request, response);
            request.setAttribute("slider", daoSlider.getRadom());
            RequestDispatcher dispatch = request.getRequestDispatcher("cart.jsp");
            dispatch.forward(request, response);
        } else {
            switch (request.getParameter("service")) {
                case "loadCart" ->
                    loadCart(request, response);
                case "addCart" ->
                    addCart(request, response);
                case "remove" ->
                    remove(request, response);
                case "update" ->
                    update(request, response);
                default ->
                    response.sendRedirect("404.jsp");
            }
        }
    }

    /**
     *
     * @param request
     * @param response
     */
    public void addCart(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String color = request.getParameter("color");
            String size = request.getParameter("size");
            String quantity = request.getParameter("quantity");
            if (color.equalsIgnoreCase("color")) {
                color = null;
            }
            if (size.equalsIgnoreCase("size")) {
                size = null;
            }
            if (color == null || size == null || name == null) {
                return;
            }
            HttpSession session = request.getSession(true);
            quantity = quantity == null ? "1" : quantity;
            if (session.getAttribute("account") == null) {
                out.print(addCartForGuest(request, name, color,
                        Integer.parseInt(size),
                        Integer.parseInt(quantity)));
            } else {
                out.print(addCartForUser(request, name, color,
                        Integer.parseInt(size),
                        Integer.parseInt(quantity)));
            }

        } catch (IOException ex) {
            Logger.getLogger(SubProductServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param request
     * @param name
     * @param color
     * @param size
     * @param quantity
     * @return
     */
    public boolean addCartForGuest(HttpServletRequest request,
            String name, String color,
            int size, int quantity) {
        HttpSession session = request.getSession(true);
        ProductDAO productDAO = new ProductDAO();
        List<CartItem> ls = (List<CartItem>) session.getAttribute("cart");
        ls = ls == null ? new LinkedList<>() : ls;
        Product product = productDAO.getProductDetailForCart(
                name, color, size);
        if (product == null) {
            return false;
        }
        if (quantity > product.getQuantity()) {
            return false;
        }
        for (CartItem a : ls) {
            if (a.getProductId() == product.getId()) {
                a.setQuantity(a.getQuantity() + quantity);
                session.setAttribute("cart", ls);
                return true;
            }
        }
        ls.add(new CartItem(
                ls.size() + 1,
                product.getId(),
                quantity,
                product));
        session.setAttribute("cart", ls);
        return true;
    }

    /**
     *
     * @param request
     * @param name
     * @param color
     * @param size
     * @param quantity
     * @return
     */
    public boolean addCartForUser(HttpServletRequest request,
            String name, String color,
            int size, int quantity) {
        HttpSession session = request.getSession(true);
        ShoppingSessionDAO shoppingSessionDAO = new ShoppingSessionDAO();
        CartItemDAO cartItemDAO = new CartItemDAO();
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductDetailForCart(
                name, color, size);
        int idSession = shoppingSessionDAO
                .getIdShoppingSessionByUser(
                        (User) session.getAttribute("account"));
        CartItem cartItem = cartItemDAO.checkExistInSession(
                idSession,
                product.getId());
        if (cartItem != null) {
            return cartItemDAO.update(cartItem.getId(),
                    quantity + cartItem.getQuantity());
        }
        return cartItemDAO.add(idSession,
                product, quantity);
    }

    /**
     *
     * @param request
     * @param response
     */
    public void remove(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("account");
        int id = Integer.parseInt(request.getParameter("id"));
        boolean flag = false;
        if (user != null) {
            CartItemDAO cartItemDAO = new CartItemDAO();
            flag = cartItemDAO.remove(id);
        } else {
            List<CartItem> ls = (List<CartItem>) session.getAttribute("cart");
            flag = ls.remove(id - 1) != null;
            int index = 1;
            for (CartItem a : ls) {
                a.setId(index);
                index++;
            }
            session.setAttribute("cart", ls);
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(flag);
        } catch (IOException ex) {
            Logger.getLogger(CartServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param request
     * @param response
     */
    public void update(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("account");
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        boolean flag = false;
        if (user != null) {
            CartItemDAO cartItemDAO = new CartItemDAO();
            flag = cartItemDAO.update(id,quantity);
        } else {
            List<CartItem> ls = (List<CartItem>) session.getAttribute("cart");
            ls.get(id-1).setQuantity(quantity);
            flag = quantity == ls.get(id-1).getQuantity();
            session.setAttribute("cart", ls);
        }
        try (PrintWriter out = response.getWriter()) {
            out.print(flag);
        } catch (IOException ex) {
            Logger.getLogger(CartServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param request
     * @param response
     * @param user
     * @return
     */
    public List<CartItem> loadByCustomer(HttpServletRequest request,
            HttpServletResponse response, User user) {
        ShoppingSessionDAO shoppingSessionDAO = new ShoppingSessionDAO();
        CartItemDAO cartItemDAO = new CartItemDAO();
        int idSession = shoppingSessionDAO.getIdShoppingSessionByUser(user);
        return cartItemDAO.getAllBySession(idSession);
    }

    /**
     *
     * @param request
     * @param response
     */
    public void loadCart(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        List<CartItem> ls = new LinkedList<>();
        User user = (User) session.getAttribute("account");
        if (user != null) {
            ls = loadByCustomer(request, response, user);
        } else {
            ls = (List<CartItem>) session.getAttribute("cart");
        }
        try (PrintWriter out = response.getWriter()) {
            if (ls == null || ls.isEmpty()) {
                out.println("<p class=\"d-flex justify-content-center my-4 text-danger\">Dont have any product</p>");
                return;
            }
            getFormCart(response, ls, out);
        } catch (IOException ex) {
            Logger.getLogger(SubProductServlet.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param response
     * @param ls
     * @param out
     */
    public void getFormCart(HttpServletResponse response, List<CartItem> ls,
            PrintWriter out) {
        ProductDAO productDAO = new ProductDAO();
        boolean outStock = true;
        boolean stopBuy = true;
        float price = 0;
        int index = 1;
        for (CartItem a : ls) {
            Product product = productDAO.getInforProductById(a.getProductId());
            if (product == null) {
                continue;
            }
            outStock = a.getQuantity() > product.getQuantity() ? true : false;
            stopBuy = product.getProductStatus().getName()
                    .equalsIgnoreCase("off") ? true : false;
            out.println("<tr>");
            // checkbox                   
            if (!outStock && !stopBuy) {
                out.println("<th><input type=\"checkbox\" class=\"product form-check-input\" style=\"margin-left:0px;\" onchange=\"getToTal()\" value=\"" + a.getId() + "\"></th>");
            } else {
                out.println("<th><i class=\"ti-alert\" style=\"color: red;font-weight: bold;\"></i></th>");
            }
            //ID
            out.println("<th scope=\"row\">" + index + "</th>");
            //Name
            out.println("<td class=\"wider-col\">" + product.getName() + "</td>");
            // Color
            out.println(" <td>" + product.getColor() + "</td>");
            //Size
            out.println(" <td>" + product.getSize() + "</td>");
            //Price
            price = product.getDiscount().isActive() ? product.getPrice() * (100 - product.getDiscount().getDiscountPercent()) / 100 : product.getPrice();
            out.println(" <td>$" + String.format("%.2f", price) + "</td>");
            //Quantity
            out.println("<td class=\"wider-col\">");
            out.println("<div class=\"qty-input\">");
            out.println("<button class=\"qty-count qty-count_minus\" data-action=\"minus\" type=\"button\" onclick=\"update('minus','" + a.getId() + "')\">-</button>");
            out.println("<input class=\"product-qty\" type=\"number\" id=\"quantity_" + a.getId() + "\" min=\"1\" value=\"" + a.getQuantity() + "\" onchange=\"update('','" + a.getId() + "')\">");
            out.println("<button class=\"qty-count qty-count_add\" data-action=\"add\" type=\"button\" onclick=\"update('add','" + a.getId() + "')\">+</button>");
            out.println("</div>");
            out.println("</td>");
            //Subtotal or message
            if (stopBuy) {
                out.println("<td style=\"color: red;font-weight: bold;\">Product stop buy</td>");
            } else if (outStock) {
                out.println("<td style=\"color: red;font-weight: bold;\">Out stock "+product.getQuantity()+"</td>");
            } else {
                out.println("<td id=\"subtotal_product_"+a.getId()+"\">" + String.format("%.2f", price * a.getQuantity()) + "</td>");
            }
            //Remove
            out.println("<td>");
            out.println("<i class=\"ti-trash\" onclick=\"remove(" + a.getId() + ")\"></i>");
            out.println("</td>");
            //end
            out.println("</tr>");

            index++;
        }
    }

    public static void main(String[] args) {
        List<CartItem> ls = new LinkedList<>();
        System.out.println(ls.size());
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
