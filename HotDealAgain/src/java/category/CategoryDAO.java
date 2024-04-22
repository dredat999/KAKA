/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import util.DBUtil;

/**
 *
 * @author ADMIN-PC
 */
public class CategoryDAO {

    private static final String SELECT_ALL_CATEGORIES = "SELECT * FROM Category";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM Category WHERE id = ?";
    private static final String INSERT_CATEGORY = "INSERT INTO Category (name) VALUES (?)";
    private static final String UPDATE_CATEGORY = "UPDATE Category SET name = ? WHERE id = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM Category WHERE id = ?";
    private static final String SELECT_CATEGORY_NAMES = "SELECT name FROM Category";

    public List<CategoryDTO> getAllCategories() throws ClassNotFoundException, SQLException {
        List<CategoryDTO> categories = new ArrayList<>();
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CATEGORIES);  ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CategoryDTO category = new CategoryDTO(rs.getInt("id"), rs.getString("name"));
                categories.add(category);
            }
        }
        return categories;
    }

    public Optional<CategoryDTO> getCategoryById(int id) throws SQLException, ClassNotFoundException {
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            stmt.setInt(1, id);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CategoryDTO category = new CategoryDTO(rs.getInt("id"), rs.getString("name"));
                    return Optional.of(category);
                }
            }
        }
        return Optional.empty();
    }

    public void addCategory(CategoryDTO category) throws SQLException, ClassNotFoundException {
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(INSERT_CATEGORY)) {
            stmt.setString(1, category.getName());
            stmt.executeUpdate();
        }
    }

    public void updateCategory(CategoryDTO category) throws SQLException, ClassNotFoundException {
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(UPDATE_CATEGORY)) {
            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteCategory(int id) throws SQLException, ClassNotFoundException {
        try ( Connection conn = DBUtil.getConnection();  PreparedStatement stmt = conn.prepareStatement(DELETE_CATEGORY)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static void main(String[] args) {
        CategoryDAO categoryDAO = new CategoryDAO();

        try {
            // Thêm một danh mục mới
            CategoryDTO newCategory = new CategoryDTO();
            newCategory.setName("New Category");
            categoryDAO.addCategory(newCategory);
            System.out.println("Category added successfully!");

            // Lấy tất cả các danh mục từ cơ sở dữ liệu và in ra màn hình
            List<CategoryDTO> allCategories = categoryDAO.getAllCategories();
            System.out.println("All Categories:");
            for (CategoryDTO category : allCategories) {
                System.out.println(category.getId() + ": " + category.getName());
            }

            // Lấy một danh mục theo ID và in ra màn hình (thay đổi ID theo nhu cầu)
            int categoryId = 1; // ID của danh mục cần lấy
            Optional<CategoryDTO> categoryById = categoryDAO.getCategoryById(categoryId);
            if (categoryById.isPresent()) {
                System.out.println("Category found by ID:");
                System.out.println(categoryById.get().getId() + ": " + categoryById.get().getName());
            } else {
                System.out.println("Category not found by ID: " + categoryId);
            }

            // Cập nhật tên của một danh mục (thay đổi ID và tên theo nhu cầu)
            CategoryDTO categoryToUpdate = new CategoryDTO();
            categoryToUpdate.setId(1); // ID của danh mục cần cập nhật
            categoryToUpdate.setName("Updated Category Name");
            categoryDAO.updateCategory(categoryToUpdate);
            System.out.println("Category updated successfully!");

            // Xóa một danh mục (thay đổi ID theo nhu cầu)
            int categoryToDeleteId = 1; // ID của danh mục cần xóa
            categoryDAO.deleteCategory(categoryToDeleteId);
            System.out.println("Category deleted successfully!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
