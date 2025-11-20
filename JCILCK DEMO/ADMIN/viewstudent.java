
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class viewstudent extends JFrame implements ActionListener {
    Container c;
    Connection cn;
    Statement st;
    ResultSet rs;
    JButton back, quit;

    public viewstudent() {
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
        model.addColumn("STUDENT ID");
        model.addColumn("STUDENT NAME");
        model.addColumn("COURSE");
        model.addColumn("COURSE-FEES");
        model.addColumn("Mailid");
        model.addColumn("Education");
        model.addColumn("EnrollDate");

        try {
            String sql = "select * from student";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String cn = rs.getString(2);
                String cd = rs.getString(3);
                String des = rs.getString(4);
                String fee = rs.getString(5);
                String ed = rs.getString(6);
                String en = rs.getString(7);

                model.addRow(new Object[] { id, cn, cd, des, fee, ed, en });
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
        setTitle("COURSE REPORT");
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (str.equals("BACK")) {
            new studentmenu();
            this.setVisible(false);
            System.out.println("Back to Student Menu");
        } else {

            dispose();
            System.out.println("Closed!");
        }
    }

    public static void main(String[] args) {
        // Create JFrame

        new viewstudent();
    }
}
