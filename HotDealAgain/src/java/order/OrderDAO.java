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
    private static final String INSERT_ORDER = "INSERT INTO [Order] (quantity, created_at, total_price, user_id) VALUES (?, ?, ?, ?)";

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
            stmt.setInt(1, order.getQuantity());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(order.getCreatedAt()));
            stmt.setDouble(3, order.getTotalPrice());
            stmt.setInt(4, order.getUserId());
            stmt.executeUpdate();

            // Lấy khóa chính được tạo tự động (nếu có)
            try ( ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    newOrder = new OrderDTO(orderId, order.getQuantity(), order.getCreatedAt(), order.getTotalPrice(), order.getUserId());
                }
            }
        }

        return newOrder;
    }

    private OrderDTO mapResultSetToOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int quantity = rs.getInt("quantity");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        double totalPrice = rs.getDouble("total_price");
        int userId = rs.getInt("user_id");
        return new OrderDTO(id, quantity, createdAt, totalPrice, userId);
    }

    public OrderDTO getLatestOrder() throws SQLException, ClassNotFoundException {
        OrderDTO latestOrder = null;
        String selectLatestOrderQuery = "SELECT TOP 1 * FROM [Order] ORDER BY created_at DESC";

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(selectLatestOrderQuery);  ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                latestOrder = mapResultSetToOrder(rs);
            }
        }

        return latestOrder;
    }

}
