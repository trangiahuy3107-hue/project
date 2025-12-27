package view;

import dao.BookDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BookPanel extends JPanel {

    private JTable table;
    private DefaultTableModel model;
    private JTextField txtId, txtTitle, txtAuthor, txtQty;
    private JButton btnAdd, btnUpdate;

    public BookPanel() {
        setLayout(new BorderLayout(10, 10));

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"Mã sách", "Tên sách", "Tác giả", "Số lượng"}, 0
        );
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== INPUT PANEL =====
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        txtId = new JTextField();
        txtTitle = new JTextField();
        txtAuthor = new JTextField();
        txtQty = new JTextField();

        inputPanel.add(new JLabel("Mã sách:"));
        inputPanel.add(txtId);
        inputPanel.add(new JLabel("Tên sách:"));
        inputPanel.add(txtTitle);
        inputPanel.add(new JLabel("Tác giả:"));
        inputPanel.add(txtAuthor);
        inputPanel.add(new JLabel("Số lượng:"));
        inputPanel.add(txtQty);

        btnAdd = new JButton("Thêm sách");
        btnUpdate = new JButton("Cập nhật số lượng");

        inputPanel.add(btnAdd);
        inputPanel.add(btnUpdate);

        add(inputPanel, BorderLayout.SOUTH);

        // ===== LOAD DATA =====
        BookDAO.load(model);

        // ===== ADD BOOK =====
        btnAdd.addActionListener(e -> {
            String id = txtId.getText().trim();
            String title = txtTitle.getText().trim();
            String author = txtAuthor.getText().trim();

            int qty;
            try {
                qty = Integer.parseInt(txtQty.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
                return;
            }

            if (id.isEmpty() || title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không được để trống mã hoặc tên sách!");
                return;
            }

            BookDAO.insert(id, title, author, qty);
            BookDAO.load(model);
            clearInput();
        });

        // ===== UPDATE QUANTITY =====
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();   
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Chọn sách để cập nhật!");
                return;
            }

            String bookId = model.getValueAt(row, 0).toString(); 
            int qty;

            try {
                qty = Integer.parseInt(txtQty.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
                return;
            }

            BookDAO.updateQuantity(bookId, qty);
            BookDAO.load(model);
            clearInput();
        });
    }

    // ===== CLEAR INPUT =====
    private void clearInput() {
        txtId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtQty.setText("");
    }

    // ===== GET MODEL =====
    public DefaultTableModel getModel() {
        return model;
    }
}
