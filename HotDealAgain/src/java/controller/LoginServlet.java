package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author HP
 */
public class LoginServlet extends HttpServlet {

    private static final String LOGIN = "index.jsp";
    private static final String USER_PAGE = "customer.jsp";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final int USER_ROLE = 2;
    private static final int ADMIN_ROLE = 1;

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
        String url = LOGIN;
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.loginUser(username, password);
            if (loginUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                String fullName = loginUser.getFirst_name() + " " + loginUser.getLast_name();
                session.setAttribute("FULL_NAME", fullName);

                int role = loginUser.getRole_id();
                session.setAttribute("USER_ROLE", role);
                if (role == USER_ROLE) {
                    url = USER_PAGE;
                } else if (role == ADMIN_ROLE) {
                    url = ADMIN_PAGE;
                    List<UserDTO> listUser = dao.getListUser();
                    session.setAttribute("USER_LIST", listUser);
                } else {
                    request.setAttribute("ERROR_ROLE", "Your role is not support...");
                }
            } else {
                request.setAttribute("ERROR", "Incorrect UserID/Password...");
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
