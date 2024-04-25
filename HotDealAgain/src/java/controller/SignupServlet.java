/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import user.UserCreateError;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author HP
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/SignupServlet"})
public class SignupServlet extends HttpServlet {

    private final String ERROR_PAGE = "sign-up.jsp";
    private final String LOGIN_PAGE = "LoginServlet";

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

        // Retrieve form data
        String url = ERROR_PAGE;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirmPassword");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        boolean foundErr = false;
        UserCreateError errors = new UserCreateError();
        try  {
            //1.Check validation of all user's errors;
            if(username.trim().length() < 3 || username.trim().length() > 20){
                foundErr = true;
                errors.setUsernameLengthErr("Username is required to type length from 3 to 20 characters");
            }
            if(password.trim().length() < 8 || password.trim().length() > 30){
                foundErr = true;
                errors.setPasswordLengthErr("Password is required to type length from 8 to 30 characters");
            } else if(!confirm.trim().equals(password.trim())){
                foundErr = true;
                errors.setConfirmNotMacthed("Confirm must match password");
            }
            if(firstName.trim().length() < 2 || username.trim().length() > 50){
                foundErr = true;
                errors.setFirstnameLengthErr("First name is required to type length from 2 to 20 characters");
            }
             if(lastName.trim().length() < 2 || username.trim().length() > 50){
                foundErr = true;
                errors.setLastnameLengthErr("First name is required to type length from 2 to 20 characters");
            }
            if(foundErr){
                request.setAttribute("CREATE_ERROR", errors);
            } else{
            //2.Call DAO
                UserDAO dao = new UserDAO();
                UserDTO account = new UserDTO( username, password, firstName, lastName, telephone, email);
                boolean result = dao.addUser(account);
            //3.Process result
            if(result){
                url = LOGIN_PAGE;
            }
            } //no errors occur
        } catch (ClassNotFoundException ex) {
            log("CreateAccountServlet _SQL" + ex.getMessage());
        } 
         finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
