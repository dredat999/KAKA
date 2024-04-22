/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package orderdetail;

import java.time.LocalDateTime;

/**
 *
 * @author ADMIN-PC
 */
public class OrderDetailDTO {
    private int id;
    private int quantity;
    private double price;
    private int productId;
    private int orderId;
    private LocalDateTime createdAt;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int id, int quantity, double price, int productId, int orderId, LocalDateTime createdAt) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.orderId = orderId;
        this.createdAt = createdAt;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
