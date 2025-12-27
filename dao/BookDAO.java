package dao;

import database.DBConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class BookDAO {

    // LOAD
    public static void load(DefaultTableModel model) {
        model.setRowCount(0);
        String sql = "SELECT id, title, author, quantity FROM Books";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // INSERT
    public static void insert(String id, String title, String author, int qty) {
        String sql = "INSERT INTO Books VALUES (?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setInt(4, qty);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE QUANTITY
    public static void updateQuantity(String id, int qty) {
        String sql = "UPDATE Books SET quantity=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, qty);
            ps.setString(2, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SEARCH (MỤC RIÊNG)
    public static void search(DefaultTableModel model, String keyword) {
        model.setRowCount(0);
        String sql = """
            SELECT id, title, author, quantity
            FROM Books
            WHERE title LIKE ? OR author LIKE ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

