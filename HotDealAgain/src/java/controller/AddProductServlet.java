/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import category.CategoryDAO;
import category.CategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import product.ProductDAO;
import product.ProductDTO;

/**
 *
 * @author HP
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {
 private static final String LIST_PRODUCT_PAGE = "list-product.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Retrieve product information from request parameters
    
       
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int discount = Integer.parseInt(request.getParameter("discount"));
        String expirationParam = request.getParameter("expiration");
        int category = Integer.parseInt(request.getParameter("category"));
        int inventory = Integer.parseInt(request.getParameter("inventory"));
        boolean isActive = request.getParameter("isActive") != null; // Checkbox returns "true" if checked
        // Get the current date and time
        LocalDateTime createdAt = LocalDateTime.now();
        // Parse the expiration date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime expirationDateTime = LocalDate.parse(expirationParam, formatter).atStartOfDay();
        
        // Assuming you have a ProductDAO class to handle database operations
        ProductDAO productDAO = new ProductDAO();
        // Assuming you have a Product class to represent a product
        ProductDTO product = new ProductDTO(0, name, description, price, discount, expirationDateTime, category, createdAt, inventory, isActive);
        
        try {
            // Add the product to the database
            productDAO.addProduct(product);
            // Redirect to the list product page after successful addition
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            // Handle exceptions here if needed
            // Optionally, you can set an error message attribute and redirect back to the add product page
        }
        
        // Redirect to the specified URL
        request.getRequestDispatcher(LIST_PRODUCT_PAGE).forward(request, response);
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
