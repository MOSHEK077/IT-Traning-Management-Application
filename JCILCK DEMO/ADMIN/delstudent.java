import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class delstudent extends JFrame implements ActionListener, FocusListener {
    Connection cn;
    Statement st;
    JComboBox jc;
    ResultSet rs;
    JLabel l1;
    JLabel h1;
    JButton back, del;
    JTextField t1;

    public delstudent() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jclick", "root", "");
            st = cn.createStatement();
            System.out.println("Connection Established....");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Connection lost....");
        }
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        Container c = getContentPane();
        Color cl = new Color(160, 108, 202);
        c.setLayout(null);
        Font f = new Font("Algerian", Font.BOLD, 50);
        h1 = new JLabel("REMOVE STUDENT");
        h1.setFont(f);
        // l1.setBackground(clr);
        h1.setBounds(800, 50, 800, 50);
        c.add(h1);
        Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 20);
        Color cl2 = new Color(193, 70, 68);
        l1 = new JLabel("Student ID:");
        l1.setFont(f2);
        l1.setBounds(600, 240, 250, 40);
        c.add(l1);
        jc = new JComboBox();
        try {
            String qr = "select * from student";
            rs = st.executeQuery(qr);
            while (rs.next()) {

                jc.addItem(rs.getString(1));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        jc.setFont(f2);
        jc.setBounds(1000, 240, 430, 40);
        c.add(jc);
        t1 = new JTextField();
        t1.setFont(f2);
        t1.setBounds(600, 330, 830, 60);
        c.add(t1);
        t1.addFocusListener(this);

        back = new JButton("BACK");
        back.setFont(f2);
        back.setBackground(Color.RED);
        back.setFocusPainted(false);
        back.setBackground(cl);
        back.setForeground(Color.WHITE);
        back.setBounds(600, 420, 150, 40);
        c.add(back);

        del = new JButton("DELETE");
        del.setFont(f2);
        del.setBackground(Color.RED);
        del.setFocusPainted(false);
        del.setBackground(cl2);
        del.setForeground(Color.WHITE);
        del.setBounds(1280, 420, 150, 40);
        c.add(del);
        del.addActionListener(this);
        back.addActionListener(this);

        setTitle("DELETE THE STUDENT DETAILS");
        Color clr = new Color(102, 255, 102);
        getContentPane().setBackground(clr);
        ImageIcon icon = new ImageIcon("logo2.png"); // put your logo file path here
        Image img = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        // Create logo label
        JLabel logoLabel = new JLabel(icon);
        logoLabel.setBounds(20, 10, 350, 200); // position left side

        // Adjust your title position to come right beside the logo
        // title.setBounds(120, 40, 400, 40); // move it a bit right so it doesn’t
        // overlap
        // title.setFont(new Font("Arial", Font.BOLD, 26));

        // Add both to frame
        add(logoLabel);
        setVisible(true);

    }

    public void focusGained(FocusEvent fe) {
        try {
            String str = (String) jc.getSelectedItem();
            String qr = "select * from student where StudentID ='" + str + "'";
            rs = st.executeQuery(qr);
            if (rs.next()) {
                t1.setText("Student Name :" + rs.getString(2) + "\nCourse: " + rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void focusLost(FocusEvent fe) {

    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (str.equals("BACK")) {
            new studentmenu();
            this.setVisible(false);
        } else if (str.equals("DELETE")) {

            try {
                String st = (String) jc.getSelectedItem(); // id
                String serverurl = "rmi://localhost/addstudentserver";
                addstudentinf in = (addstudentinf) Naming.lookup(serverurl);
                in.studentdel(st);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        new delstudent();
    }
}