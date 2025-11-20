import java.rmi.*;
import java.rmi.server.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/*String sname, String course, String batch, String mid, String edq,
            String fee */
public class addbatchclient extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, h1;
    JTextField t1, t2, t3, t5, t6, t7;
    JComboBox t, t4;
    JButton clear, back, quit, save;
    Connection cn;
    Statement st;
    ResultSet rs;

    public addbatchclient() {
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
        Color cl4 = new Color(0x6ffc33);// green
        Color clr = new Color(102, 255, 102);
        Font f = new Font("Algerian", Font.BOLD, 50);
        h1 = new JLabel("BATCH MENU");
        h1.setFont(f);
        // l1.setBackground(clr);
        h1.setBounds(845, 50, 800, 50);
        c.add(h1);
        Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 25);

        // t1.addFocusListener(this);

        l2 = new JLabel("Batch Name:");
        l2.setFont(f2);
        l2.setBounds(650, 310, 250, 40);
        c.add(l2);

        l3 = new JLabel("Course :");
        l3.setFont(f2);
        l3.setBounds(650, 400, 280, 40);
        c.add(l3);

        l4 = new JLabel("Trainer Name:");
        l4.setFont(f2);
        l4.setBounds(650, 490, 350, 40);
        c.add(l4);

        l5 = new JLabel("Timing:");
        l5.setFont(f2);
        l5.setBounds(650, 590, 250, 40);
        c.add(l5);

        t1 = new JTextField();
        t1.setFont(f2);
        t1.setBounds(1050, 310, 330, 40);
        c.add(t1);

        t = new JComboBox();
        t.setFont(f2);
        t.setBounds(1050, 400, 330, 40);
        t.addItem("SELECT");

        try {
            String sql = "select * from course";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                t.addItem(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.add(t);
        t3 = new JTextField();
        t3.setFont(f2);
        t3.setBounds(1050, 490, 330, 40);
        c.add(t3);

        t4 = new JComboBox();
        t4.setFont(f2);
        t4.setBounds(1050, 590, 330, 40);
        t4.addItem("SELECT");
        t4.addItem("9:00 TO 11:00(Shift 1)");
        t4.addItem("12:00 TO 13:00(Shift 2)");
        t4.addItem("14:00 TO 16:00(Shift 3)");
        t4.addItem("16:00 TO 18:00(Shift 4)");
        c.add(t4);

        // clear, back, quit, save;
        back = new JButton("BACK");
        back.setFocusPainted(false);
        back.setFont(f2);
        back.setBackground(cl3);
        back.setForeground(Color.WHITE);
        back.setBounds(650, 750, 140, 40);
        c.add(back);
        back.addActionListener(this);
        clear = new JButton("CLEAR");
        clear.setFocusPainted(false);
        clear.setFocusPainted(false);
        clear.setBackground(cl3);
        clear.setForeground(Color.WHITE);
        clear.setFont(f2);
        clear.setBounds(850, 750, 140, 40);
        c.add(clear);
        clear.addActionListener(this);

        quit = new JButton("QUIT");
        quit.setFocusPainted(false);
        quit.setBackground(cl2);
        quit.setForeground(Color.WHITE);
        quit.setFont(f2);
        quit.setBounds(1050, 750, 130, 40);
        c.add(quit);
        quit.addActionListener(this);

        save = new JButton("SAVE");
        save.setFocusPainted(false);
        save.setFont(f2);
        save.setBackground(Color.GREEN);
        save.setForeground(Color.WHITE);
        save.setBounds(1250, 750, 130, 40);
        c.add(save);
        save.addActionListener(this);

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

        getContentPane().setBackground(clr);

    }

    public void actionPerformed(ActionEvent ae) {
        String ap = ae.getActionCommand();
        if (ap.equals("CLEAR")) {
            t1.setText("");
            t.setSelectedItem("SELECT");
            t3.setText("");
            t4.setSelectedItem("SELECT");

        } else if (ap.equals("BACK")) {
            new batchmenu();
            this.setVisible(false);
        } else if (ap.equals("QUIT")) {

            dispose();
            System.out.println("Window closed !");
        } else if (ap.equals("SAVE")) {
            try {
                String t2 = (String) t.getSelectedItem();
                String tt = (String) t4.getSelectedItem();
                String serverurl = "rmi://" + "localhost" + "/addstudentserver";
                addstudentinf in = (addstudentinf) Naming.lookup(serverurl);
                in.batch(t1.getText(), t2, t3.getText(), tt);
                System.out.println("Successfully added");
            } catch (Exception e) {
                // e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new addbatchclient();
    }
}
