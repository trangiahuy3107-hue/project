package view;

import dao.BookDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchBookPanel extends JPanel {

    public SearchBookPanel() {
        setLayout(new BorderLayout(10,10));

        JPanel top = new JPanel();
        JTextField txtKey = new JTextField(25);
        JButton btnSearch = new JButton("Tìm");

        top.add(new JLabel("Tên sách / Tác giả"));
        top.add(txtKey);
        top.add(btnSearch);
        add(top, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Mã sách","Tên sách","Tác giả","Số lượng"},0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnSearch.addActionListener(e ->
                BookDAO.search(model, txtKey.getText()));
    }
}
