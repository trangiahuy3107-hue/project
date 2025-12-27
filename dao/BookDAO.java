package dao;

import database.DBConnection;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {

    // ================= LOAD BOOKS =================
    public static void load(DefaultTableModel model) {
        model.setRowCount(0);
        String sql = "SELECT id, title, author, quantity FROM Books";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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

    // ================= INSERT BOOK =================
    public static boolean insert(String id, String title, String author, int qty) {
        String sql = "INSERT INTO Books(id, title, author, quantity) VALUES (?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);     
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setInt(4, qty);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= UPDATE QUANTITY =================
    public static boolean updateQuantity(String bookId, int qty) {
        String sql = "UPDATE Books SET quantity=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, qty);
            ps.setString(2, bookId); 

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

