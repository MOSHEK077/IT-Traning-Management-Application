
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class viewtrans extends JFrame implements ActionListener {
    Container c;
    Connection cn;
    Statement st;
    ResultSet rs;
    JButton back, quit;

    public viewtrans() {
        // database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jclick", "root", "");
            st = cn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }

        c = getContentPane();

        Color clr = new Color(102, 255, 102);
        // getContentPane().setBackground(clr);
        // Create Table Model
        Font f2 = new Font("Verdana", Font.BOLD, 16);
        Color cl2 = new Color(193, 70, 68);
        DefaultTableModel model = new DefaultTableModel();
        // setFont(f2);
        c.setFont(f2);

        model.addColumn("Payment ID");
        model.addColumn("Student ID ");
        model.addColumn("Student Name");
        model.addColumn("Course");
        model.addColumn("Amount Paid");
        model.addColumn("Total Paid");
        model.addColumn("Pending Amount");
        model.addColumn("Payment Date");
        model.addColumn("Payment Mode");
        model.addColumn("Transaction ID");
        model.addColumn("Pending Amount");
        // model.addColumn("Remarks");
        model.addColumn("Status");

        try {
            String sql = "select * from payment ";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String sid = rs.getString(2);
                String na = rs.getString(3);
                String crs = rs.getString(4);
                String p = rs.getString(5);
                String to = rs.getString(6);
                String pen = rs.getString(7);
                String dt = rs.getString(8);
                String mode = rs.getString(9);
                String tid = rs.getString(10);
                String rm = rs.getString(11);
                String sts = rs.getString(12);

                model.addRow(new Object[] { id, sid, na, crs, p, to, pen, dt, mode, tid, rm, sts });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Add initial data
        // model.addRow(new Object[] { "Alice", 25, "HR" });

        // Create JTable with the model
        JTable table = new JTable(model);
        // Add JScrollPane for scrolling

        // Set table font and row height
        table.setFont(new Font("Arial", Font.BOLD, 16));
        table.setRowHeight(30);
        table.setBackground(clr);
        // Add table to scroll pane

        table.setFont(f2);
        table.setRowHeight(30);
        table.setBackground(clr);
        table.setForeground(Color.black);
        Color cl = new Color(160, 108, 202);

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(Color.black);
        tableHeader.setForeground(Color.white);
        Font headerFont = new Font("Verdana", Font.PLAIN, 20);
        tableHeader.setFont(headerFont);
        quit = new JButton("QUIT");
        quit.setFocusPainted(false);
        quit.setBackground(cl2);
        quit.setForeground(Color.WHITE);
        quit.setFont(headerFont);
        quit.setBounds(700, 800, 130, 50);
        c.add(quit);
        quit.addActionListener(this);

        back = new JButton("BACK");
        back.setFont(headerFont);
        back.setFocusPainted(false);
        back.setBackground(cl);
        back.setForeground(Color.WHITE);
        back.setBounds(1100, 800, 130, 50);
        c.add(back);
        back.addActionListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        c.add(scrollPane);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        setVisible(true);
        setTitle("PAYMENT REPORT");
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (str.equals("BACK")) {
            new paymenu();
            this.setVisible(false);
            System.out.println("Back to Payment Menu");
        } else {

            dispose();
            System.out.println("Closed!");
        }
    }

    public static void main(String[] args) {
        // Create JFrame

        new viewtrans();
    }
}
