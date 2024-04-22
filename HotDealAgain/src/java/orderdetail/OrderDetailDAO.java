/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orderdetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DBUtil;

public class OrderDetailDAO {

    private static final String INSERT_ORDER_DETAIL = "INSERT INTO OrderDetail (quantity, price, product_id, order_id, created_at) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ORDER_DETAILS_BY_ORDER_ID = "SELECT * FROM OrderDetail WHERE order_id = ?";

    public void addOrderDetail(OrderDetailDTO orderDetail) throws SQLException, ClassNotFoundException {
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(INSERT_ORDER_DETAIL)) {
            stmt.setInt(1, orderDetail.getQuantity());
            stmt.setDouble(2, orderDetail.getPrice());
            stmt.setInt(3, orderDetail.getProductId());
            stmt.setInt(4, orderDetail.getOrderId());
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(orderDetail.getCreatedAt()));
            stmt.executeUpdate();
        }
    }

    public List<OrderDetailDTO> getOrderDetailsByOrderId(int orderId) throws SQLException, ClassNotFoundException {
        List<OrderDetailDTO> orderDetails = new ArrayList<>();
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(SELECT_ORDER_DETAILS_BY_ORDER_ID)) {
            stmt.setInt(1, orderId);
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orderDetails.add(mapResultSetToOrderDetail(rs));
                }
            }
        }
        return orderDetails;
    }

    private OrderDetailDTO mapResultSetToOrderDetail(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        int productId = rs.getInt("product_id");
        int orderId = rs.getInt("order_id");
        java.time.LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        return new OrderDetailDTO(id, quantity, price, productId, orderId, createdAt);
    }
}
