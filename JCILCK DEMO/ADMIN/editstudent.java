// Aim to Edit the course details with Course Id;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//client programm
public class editstudent extends JFrame implements ActionListener, FocusListener {
    JButton add, clear, quit;
    JTextField t1, t2, t3, t5, t6, t7; // Course Name,
    JLabel h1, l1, l2, l3, l4, l5, l6, l7;
    JTextArea t4;
    Connection cn;
    Statement st;
    JComboBox jc;
    ResultSet rs;
    String str;

    public editstudent() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jclick", "root", "");
            st = cn.createStatement();
            System.out.println("Connection Established....");
        } catch (Exception e) {
            System.out.println("Connection lost....");
            e.printStackTrace();
        }

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setVisible(true);
        Container c = getContentPane();
        c.setLayout(null);
        Color cl2 = new Color(193, 70, 68);// red
        Color cl3 = new Color(51, 51, 255);// blue
        Color cl4 = new Color(0, 51, 0);// green
        Font f = new Font("Algerian", Font.BOLD, 50);
        h1 = new JLabel("UPDATE STUDENT");
        h1.setFont(f);
        // l1.setBackground(clr);
        h1.setBounds(720, 50, 800, 50);
        c.add(h1);

        Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 25);

        l1 = new JLabel("Student ID:");
        l1.setFont(f2);
        l1.setBounds(600, 240, 250, 40);
        c.add(l1);

        t1 = new JTextField();
        t1.setFont(f2);
        t1.setBounds(1000, 330, 300, 40);
        c.add(t1);
        t1.addFocusListener(this);

        l2 = new JLabel("Student Name:");
        l2.setFont(f2);
        l2.setBounds(600, 330, 250, 40);
        c.add(l2);

        l3 = new JLabel("Course:");
        l3.setFont(f2);
        l3.setBounds(600, 420, 280, 40);
        c.add(l3);

        l4 = new JLabel("Maild:");
        l4.setFont(f2);
        l4.setBounds(600, 510, 350, 40);
        c.add(l4);

        l5 = new JLabel("Education:");
        l5.setFont(f2);
        l5.setBounds(600, 650, 250, 40);
        c.add(l5);

        /*
         * t1 = new JTextField();
         * t1.setFont(f2);
         * t1.setBounds(1000, 250, 300, 40);
         * c.add(t1);
         */
        jc = new JComboBox();
        jc.addItem("SELECT");
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
        jc.setBounds(1000, 240, 300, 40);
        c.add(jc);

        t3 = new JTextField();
        t3.setFont(f2);
        t3.setBounds(1000, 420, 300, 40);
        c.add(t3);
        t3.addFocusListener(this);

        t4 = new JTextArea();
        t4.setFont(f2);
        t4.setLineWrap(true);
        t4.setWrapStyleWord(true);
        t4.setBounds(1000, 510, 300, 90);
        c.add(t4);
        t4.addFocusListener(this);

        t5 = new JTextField();
        t5.setFont(f2);
        t5.setBounds(1000, 650, 300, 40);
        c.add(t5);
        t5.addFocusListener(this);

        clear = new JButton("CLEAR");
        clear.setFont(f2);
        clear.setFocusPainted(false);
        clear.setForeground(Color.WHITE);
        clear.setBackground(cl3);
        clear.setBounds(600, 750, 170, 40);
        c.add(clear);
        clear.addActionListener(this);
        quit = new JButton("BACK");
        quit.setFont(f2);
        quit.setFocusPainted(false);
        quit.setBackground(cl2);
        quit.setForeground(Color.WHITE);
        quit.setBounds(860, 750, 170, 40);
        c.add(quit);
        quit.addActionListener(this);

        add = new JButton("EDIT");
        add.setFont(f2);
        add.setFocusPainted(false);
        add.setForeground(Color.WHITE);
        add.setBackground(cl4);
        add.setBounds(1130, 750, 170, 40);
        c.add(add);
        add.addActionListener(this);
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

        // addcourse ac = new addcourse();

        setTitle("UPDATING THE STUDENT DETAILS");
        Color clr = new Color(102, 255, 102);
        getContentPane().setBackground(clr);
    }

    public void focusGained(FocusEvent fe) {
        str = (String) jc.getSelectedItem();
        try {
            String qr2 = "select * from student where StudentID='" + str + "'";
            rs = st.executeQuery(qr2);
            if (rs.next()) {

                t1.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(5));
                t5.setText(rs.getString(6));

            }
        } catch (Exception e) {

        }
    }

    public void focusLost(FocusEvent fe) {

    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (str.equals("CLEAR")) {
            t1.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            jc.setSelectedItem("SELECT");

        } else if (str.equals("BACK")) {
            new studentmenu();
            this.setVisible(false);
        } else if (str.equals("EDIT")) {
            try {
                String sql = "update student set StudentName='" + t1.getText() + "', Course='" + t3.getText()
                        + "',Mailid='" + t4.getText() + "',Education='" + t5.getText() + "' WHERE StudentID  ='"
                        + jc.getSelectedItem() + "'";
                System.out.println("Updated....");
                st.executeUpdate(sql);
            } catch (Exception e) {
            }

        }
    }

    public static void main(String[] args) {
        new editstudent();

    }
}