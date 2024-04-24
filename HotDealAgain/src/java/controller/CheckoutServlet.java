/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import cart.Cart;
import cart.CartItem;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import order.OrderDAO;
import order.OrderDTO;
import orderdetail.OrderDetailDAO;
import orderdetail.OrderDetailDTO;

/**
 *
 * @author HP
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckoutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
        Float totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            cart.setTotalPrice(totalPrice);
            OrderDTO orderDTO = createOrder(cart);
            try {
                OrderDTO order = addOrder(orderDTO);
                List<CartItem> items = cart.getItems();
                for (CartItem item : items) {
                    int productId = item.getProduct().getId();
                    int quantity = item.getQuantity();
                    double price = item.getProduct().getPrice() * quantity;
                    OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
                    orderDetailDTO.setOrderId(order.getId());
                    orderDetailDTO.setProductId(productId);
                    orderDetailDTO.setQuantity(quantity);
                    orderDetailDTO.setPrice(price);
                    addOrderDetail(orderDetailDTO);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        response.sendRedirect("cart.jsp");
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

    private OrderDTO createOrder(Cart cart) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCreatedAt(LocalDateTime.now());
        orderDTO.setUserId(2);
        orderDTO.setTotalPrice(cart.getTotalPrice());
        return orderDTO;
    }
    
    private OrderDTO addOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        OrderDAO orderDAO = new OrderDAO();
        OrderDTO newOrderDTO = orderDAO.addOrder(orderDTO);
        return newOrderDTO;
    }
    
    private void addOrderDetail(OrderDetailDTO orderDetailDTO) throws SQLException, ClassNotFoundException {
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        orderDetailDTO.setCreatedAt(LocalDateTime.now());
        orderDetailDAO.addOrderDetail(orderDetailDTO);
    }
    
}
