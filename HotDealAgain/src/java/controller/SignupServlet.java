/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author HP
 */
@WebServlet(name="SignupServlet", urlPatterns={"/SignupServlet"})
public class SignupServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet SignupServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
    // Retrieve form data
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String firstName = request.getParameter("firstname");
    String lastName = request.getParameter("lastname");
    String telephone = request.getParameter("telephone");

    // Validate form data
    if (username == null || username.isEmpty() || password == null || password.isEmpty()
            || firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()
            || telephone == null || telephone.isEmpty()) {
        // Handle validation errors, perhaps by showing an error message to the user
        request.setAttribute("error", "Please fill in all fields");
        request.getRequestDispatcher("index.jsp").forward(request, response);
        return;
    }

    // Perform additional validation (e.g., check username uniqueness, validate email format)
    // Process the sign-up (e.g., save user to database)
    try {
        UserDTO user = new UserDTO();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setTelephone(telephone);

        UserDAO userDao = new UserDAO();
        boolean success = userDao.addUser(user);
        if (success) {
            // Redirect to a success page
            response.sendRedirect("customer.jsp");
        } else {
            // Handle database insertion failure
            request.setAttribute("error", "Failed to sign up. Please try again later.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    } catch (Exception e) {
        // Handle exceptions (e.g., database connection error)
        e.printStackTrace();
        request.setAttribute("error", "An unexpected error occurred. Please try again later.");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

        processRequest(request, response);
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
