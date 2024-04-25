/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import util.DBUtil;

/**
 *
 * @author HP
 */
public class UserDAO {

    private static final String LOGIN = "SELECT * FROM Users WHERE username = ? AND password = ?";
    private static final String UPDATE_LAST_LOGIN = "UPDATE Users SET last_loginDate = ? WHERE id = ?";

    public  UserDTO loginUser(String username, String password) throws ClassNotFoundException, SQLException {
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
            // Process result set
            // Process result set
            if (rs.next()) {
                // Retrieve user data from result set
                int userId = rs.getInt("id");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String telephone = rs.getString("telephone");
                int role = rs.getInt("role_id");
                boolean isActive = rs.getBoolean("is_actived");

                // Update last login date
                LocalDateTime currentDateTime = LocalDateTime.now();
                PreparedStatement updatePs = conn.prepareStatement(UPDATE_LAST_LOGIN);
                updatePs.setTimestamp(1, Timestamp.valueOf(currentDateTime));
                updatePs.setInt(2, userId);
                updatePs.executeUpdate();
                updatePs.close();

                // Create UserDTO object
                user = new UserDTO(userId, username, password, firstname, lastname, telephone, role, isActive);
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

    private static final String GET_ALL = "SELECT * FROM Users WHERE is_actived = ?";

    public List<UserDTO> getListUser(boolean is_actived) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UserDTO> userList = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL);
                ps.setBoolean(1, is_actived);

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

        return userList;
    }

    private static final String REMOVE_USER_QUERY = "UPDATE Users SET is_actived = ? WHERE id = ?";

    public void removeBook(int userId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();
            if (conn != null) {
                ps = conn.prepareStatement(REMOVE_USER_QUERY);
                ps.setBoolean(1, false); // Set status to false
                ps.setInt(2, userId);

                // Execute the update query
                ps.executeUpdate();
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean addUser(UserDTO user) throws ClassNotFoundException {
        String sql = "INSERT INTO Users(username, password, first_name, last_name, telephone, email, created_at, last_loginDate, modified_at, role_id, is_actived) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        boolean result = false;

        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirst_name());
            ps.setString(4, user.getLast_name());
            ps.setString(5, user.getTelephone());
            ps.setString(6, user.getEmail());
            ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now())); // Set created_date to current time
            ps.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now())); // Set last_loginDate to current time
            ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now())); // Set last_modified to current time
            ps.setInt(10, 2);
            ps.setBoolean(11, true);

            int effectRows = ps.executeUpdate();
            //5. Process result
            if (effectRows > 0) {
                result = true;
            }

        } catch (SQLException ex) {
            System.out.println("Insert user error: " + ex.getMessage());
        }
        return result;
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
//    public static void main(String[] args) {
//        String username = "kaka";
//        String password = "123";
//
//        try {
//            // Attempt to log in the user
//            UserDTO user = loginUser(username, password);
//
//            // Check if login was successful
//            if (user != null) {
//                // Print user information
//                System.out.println("Login successful!");
//                System.out.println("User ID: " + user.getId());
//                System.out.println("Username: " + user.getUsername());
//                System.out.println("Password: " + user.getPassword());
//                System.out.println("First Name: " + user.getFirst_name());
//                System.out.println("Last Name: " + user.getLast_name());
//                System.out.println("Last Login: " + user.getLast_loginDate());
//                // Print login failure message
//
//            }else{
//                System.err.println("Login fail!!!!");
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            // Print any exceptions that occur during the login process
//            e.printStackTrace();
//        }
//    }
}
