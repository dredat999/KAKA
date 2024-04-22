package order;

import java.time.LocalDateTime;


public class OrderDTO {
    private int id;
    private int quantity;
    private LocalDateTime createdAt;
    private double totalPrice;
    private int userId;

    public OrderDTO() {
    }

    public OrderDTO(int id, int quantity, LocalDateTime createdAt, double totalPrice, int userId) {
        this.id = id;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    
}
