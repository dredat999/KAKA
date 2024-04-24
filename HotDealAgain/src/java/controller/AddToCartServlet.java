/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import cart.Cart;
import cart.CartItem;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import product.ProductDTO;
import product.ProductDAO;

/**
 *
 * @author HP
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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
        try {
            // Lấy HttpSession từ request
            HttpSession session = request.getSession();

            // Lấy thuộc tính "cart" từ session
            Object object = session.getAttribute("cart");

            // Khởi tạo các đối tượng cần thiết
            ProductDAO productDao = new ProductDAO();
            Cart cart = null;
            List<CartItem> items = new ArrayList<>();
            // Kiểm tra xem thuộc tính "cart" có khác null không
            if (object != null) {
                // Nếu không null, gán giá trị giỏ hàng hiện tại cho biến
                cart = (Cart) object;
            } else {
                // Nếu null, tạo một giỏ hàng mới
                cart = new Cart(items);
            }
            // Lấy các tham số từ request
            int productId = Integer.parseInt(request.getParameter("pid"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // Lấy thông tin sản phẩm dựa trên ID sản phẩm
            ProductDTO product = productDao.getProductById(productId);

            // Tạo một đối tượng CartItem mới với sản phẩm và số lượng đã lấy
            CartItem item = new CartItem(product, quantity);

            // Thêm mục vào giỏ hàng
            cart.addItem(item);

            // Cập nhật thuộc tính "cart" trong session
            session.setAttribute("cart", cart);

            response.sendRedirect("GetShopPageServlet");
        } catch (SQLException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
