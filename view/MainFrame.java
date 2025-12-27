package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("QUẢN LÝ THƯ VIỆN");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tab = new JTabbedPane();

        BookPanel bookPanel = new BookPanel();
        ReportPanel reportPanel = new ReportPanel();
        StudentPanel studentPanel = new StudentPanel();
        BorrowPanel borrowPanel = new BorrowPanel(reportPanel, bookPanel);
        SearchBookPanel searchBookPanel = new SearchBookPanel();

        tab.add("Sách", bookPanel);
        tab.add("Sinh viên", studentPanel);
        tab.add("Tìm kiếm sách", searchBookPanel);
        tab.add("Mượn/Trả", borrowPanel);
        tab.add("Báo cáo", reportPanel);

        add(tab);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
