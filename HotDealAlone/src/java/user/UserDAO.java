/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import util.DBUtil;

/**
 *
 * @author HP
 */
public class UserDAO {

    
private static final String LOGIN = "SELECT * FROM [User] WHERE username = ? AND password = ?";


    public static UserDTO loginUser(String username, String password) throws ClassNotFoundException, SQLException {
        // Initialize variables
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        UserDTO user = null;

        try {
            // Get database connection
            conn = DBUtil.getConnection();

            // Prepare SQL statement
            ps = conn.prepareStatement(LOGIN);
            ps.setString(1, username);
            ps.setString(2, password);

            // Execute query
            rs = ps.executeQuery();

            // Process result set
            if (rs.next()) {
                // Retrieve user data from result set
                int userId = rs.getInt("id");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String telephone = rs.getString("telephone");
                int role = rs.getInt("role_id");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime lastLogin = rs.getTimestamp("last_loginDate").toLocalDateTime();
                boolean isActive = rs.getBoolean("is_actived");

                // Create UserDTO object
                user = new UserDTO(userId, username, password, firstname, lastname, telephone, createdAt, lastLogin, role, isActive);
            }
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return user;
    }
    
    private static final String GET_ALL = "SELECT * FROM [User]";
    
    public List<UserDTO> getListUser() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UserDTO> userList = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    int userId = rs.getInt("id");
                    String username = rs.getString("username");
                    String firstName = rs.getString("first_name");
                    
                    String lastName = rs.getString("last_name");
                    String telephone = rs.getString("telephone");
                    LocalDateTime createdDate = rs.getTimestamp("created_at").toLocalDateTime();
                    LocalDateTime lastLoginDate = rs.getTimestamp("last_loginDate").toLocalDateTime();
                    boolean isActive = rs.getBoolean("is_actived");
                    int roleId = rs.getInt("role_id");
                    
                    UserDTO user = new UserDTO(userId, username, firstName, lastName, telephone, createdDate, lastLoginDate, roleId, isActive);
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }

        return userList;
    }
    
    
//    public static void main(String[] args) {
//        try {
//            UserDAO userDAO = new UserDAO();
//            List<UserDTO> userList = userDAO.getListUser();
//
//            System.out.println("List of Users:");
//            for (UserDTO user : userList) {
//                System.out.println("User ID: " + user.getId());
//                System.out.println("Username: " + user.getUsername());
//                System.out.println("First Name: " + user.getFirst_name());
//                System.out.println("Last Name: " + user.getLast_name());
//                System.out.println("Telephone: " + user.getTelephone());
//                System.out.println("Created Date: " + user.getCreated_date());
//                System.out.println("Last Login Date: " + user.getLast_loginDate());
//                System.out.println("Role ID: " + user.getRole_id());
//                System.out.println("Is Active: " + user.isIs_actived());
//                System.out.println(); // For separating each user
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }


    
   public static void main(String[] args) {
    String username = "meomeo";
    String password = "123456";

    try {
        // Attempt to log in the user
        UserDTO user = loginUser(username, password);
        
        // Check if login was successful
        if (user != null) {
            // Print user information
            System.out.println("Login successful!");
            System.out.println("User ID: " + user.getId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Password: " + user.getPassword());
            System.out.println("First Name: " + user.getFirst_name());
            System.out.println("Last Name: " + user.getLast_name());
            System.out.println("Last Login: " + user.getLast_loginDate());
            // Print login failure message
            
        }
    } catch (ClassNotFoundException | SQLException e) {
        // Print any exceptions that occur during the login process
        e.printStackTrace();
    }
   }
}
