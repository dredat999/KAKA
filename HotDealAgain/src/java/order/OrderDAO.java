package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import util.DBUtil;

public class OrderDAO {

    private static final String SELECT_ALL_ORDERS = "SELECT * FROM [Order]";
    private static final String SELECT_ORDER_BY_ID = "SELECT * FROM [Order] WHERE id = ?";
        private static final String INSERT_ORDER = "INSERT INTO [Order] (created_at, amount, address, note, user_id, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_STATUS = "UPDATE [Order] SET status = ? WHERE id = ?";

    public List<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        List<OrderDTO> orders = new ArrayList<>();
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_ORDERS);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                orders.add(mapResultSetToOrder(rs));
            }
        }
        return orders;
    }

    public OrderDTO getOrderById(int id) throws SQLException, ClassNotFoundException {
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(SELECT_ORDER_BY_ID)) {
            stmt.setInt(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToOrder(rs);
                }
            }
        }
        return null;
    }

    public OrderDTO addOrder(OrderDTO order) throws SQLException, ClassNotFoundException {
        OrderDTO newOrder = null;
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setObject(1, order.getCreatedAt());
            stmt.setDouble(2, order.getAmount());
            stmt.setString(3, order.getAddress());
            stmt.setString(4, order.getNote());
            stmt.setInt(5, order.getUserId());
            stmt.setString(6, order.getStatus());
            stmt.executeUpdate();
            try ( ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    newOrder = new OrderDTO(orderId, order.getCreatedAt(), order.getAmount(), order.getAddress(), order.getNote(), order.getUserId(), order.getStatus());
                }
            }
        }
        return newOrder;
    }

    public void updateOrderStatus(int orderId, String newStatus) throws SQLException, ClassNotFoundException {
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(UPDATE_ORDER_STATUS)) {
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        }
    }

    private OrderDTO mapResultSetToOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        double amount = rs.getDouble("amount");
        String address = rs.getString("address");
        String note = rs.getString("note");
        int userId = rs.getInt("user_id");
        String status = rs.getString("status");
        return new OrderDTO(id, createdAt, amount, address, note, userId, status);
    }

}
