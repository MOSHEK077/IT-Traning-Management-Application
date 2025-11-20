import java.awt.*;
import java.awt.event.*;
import java.nio.channels.Pipe.SourceChannel;
import java.rmi.Naming;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.*;

public class clientstumenu extends JFrame {
    JTabbedPane tb1, tb2, tb3;
    JButton clear, submit;
    JLabel email;
    JTextField eid;
    JLabel name;
    JLabel h1;
    Connection cn;
    Statement st;
    ResultSet rs;
    String mail;
    JTextArea ta;
    profile profileTab;
    JLabel stuid, sname, course, mid, edc, enda;
    JTextField sid, sna, crs, mailid, edu, enroldate;
    String id;
    Color cl2;
    JButton back, quit, update, view;

    public clientstumenu() {
        try {
            // data base connection;
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jclick", "root", "");
            st = cn.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        tb1 = new JTabbedPane();
        tb2 = new JTabbedPane();
        tb1.addTab("Login", new log());
        profileTab = new profile();
        tb1.addTab("Profile", profileTab);
        tb1.addTab("Payments", new payments());

        c.add(tb1, BorderLayout.CENTER);

        setVisible(true);
        setTitle("STUDENT MANAGEMENT");
        // olor clr = new Color(102, 255, 102);
        getContentPane().setBackground(Color.BLACK);

    }

    class RoundedButton extends JButton {
        private static final int RADIUS = 20;

        public RoundedButton(String label) {
            super(label);
            setFocusPainted(false);
            setContentAreaFilled(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), RADIUS, RADIUS);
            super.paintComponent(g);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getForeground());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, RADIUS, RADIUS);
            g2.dispose();

        }
    }

    public class log extends JPanel implements ActionListener {
        public log() {
            setLayout(null);

            cl2 = new Color(193, 70, 68);
            Color cl3 = new Color(51, 153, 255);
            setBackground(cl3);
            Color cl4 = new Color(0, 51, 0);
            Font f = new Font("Algerian", Font.BOLD, 50);
            h1 = new JLabel("STUDENT LOGIN");
            h1.setFont(f);
            h1.setForeground(Color.white);
            // l1.setBackground(clr);
            h1.setBounds(760, 50, 900, 50);
            add(h1);

            Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 25);
            Font f3 = new Font("Copperplate Gothic Bold", Font.BOLD, 15);
            email = new JLabel("Mail-id:");
            email.setForeground(Color.WHITE);
            email.setFont(f2);
            email.setBounds(735, 300, 140, 40);
            add(email);

            eid = new JTextField();

            eid.setForeground(Color.BLACK);
            eid.setBounds(970, 305, 200, 30);
            add(eid);

            clear = new JButton("CLEAR");
            clear.setBackground(cl2);
            clear.setFont(f3);
            clear.setFocusPainted(false);
            clear.setForeground(Color.WHITE);
            clear.setBounds(730, 400, 106, 30);
            add(clear);
            clear.addActionListener(this);

            submit = new JButton("SUBMIT");
            submit.setBackground(cl4);
            submit.setFont(f3);
            submit.setFocusPainted(false);
            submit.setForeground(Color.WHITE);
            submit.setBounds(1065, 400, 106, 30);
            add(submit);
            submit.addActionListener(this);

        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            String str = ae.getActionCommand();
            if (str.equals("CLEAR")) {
                eid.setText("");
            } else if (str.equals("SUBMIT")) {
                try {
                    String sql = "select * from student where Mailid ='" + eid.getText() + "'";
                    rs = st.executeQuery(sql);
                    if (rs.next()) {
                        mail = rs.getString("Mailid");
                        if (mail.equals(eid.getText())) {
                            profileTab.setMail(mail);
                            tb1.setSelectedIndex(1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Mail ID");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class profile extends JPanel implements ActionListener {
        public profile() {
            Color cl3 = new Color(51, 153, 255);

            setBackground(cl3);
            setLayout(null);
            Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 20);
            Font f3 = new Font("Copperplate Gothic Bold", Font.BOLD, 15);
            Font f = new Font("Algerian", Font.BOLD, 50);
            h1 = new JLabel("STUDENT PROFILE");

            h1.setFont(f);
            h1.setForeground(Color.white);
            // l1.setBackground(clr);
            h1.setBounds(750, 50, 900, 50);
            add(h1);
            name = new JLabel();

            name.setText(mail);
            add(name);
            // JLabel stuid, sname, course, mid, edc, enda;

            stuid = new JLabel("STUDENT ID:");
            stuid.setFont(f2);
            stuid.setForeground(Color.white);
            stuid.setBounds(700, 200, 400, 50);
            add(stuid);

            sname = new JLabel("STUDENT NAME:");
            sname.setFont(f2);
            sname.setForeground(Color.white);
            sname.setBounds(700, 290, 400, 50);
            add(sname);

            course = new JLabel("COURSE:");
            course.setFont(f2);
            course.setForeground(Color.white);
            course.setBounds(700, 380, 400, 50);
            add(course);

            mid = new JLabel("MAIL ID:");
            mid.setFont(f2);
            mid.setForeground(Color.white);
            mid.setBounds(700, 470, 400, 50);
            add(mid);

            edc = new JLabel("EDUCATION:");
            edc.setFont(f2);
            edc.setForeground(Color.white);
            edc.setBounds(700, 560, 400, 50);
            add(edc);

            enda = new JLabel("ENROLLMENT DATE:");
            enda.setFont(f2);
            enda.setForeground(Color.white);
            enda.setBounds(700, 650, 500, 50);
            add(enda);

            // JTextField sid, sna, crs, mailid, edu, enroldate;

            sid = new JTextField();
            sid.setEditable(false);
            sid.setFont(f3);
            sid.setForeground(Color.BLACK);
            sid.setBounds(1050, 200, 200, 35);
            add(sid);

            sna = new JTextField();
            sna.setFont(f3);
            sna.setForeground(Color.BLACK);
            sna.setBounds(1050, 290, 200, 35);
            add(sna);

            crs = new JTextField();
            crs.setEditable(false);
            crs.setFont(f3);
            crs.setForeground(Color.BLACK);
            crs.setBounds(1050, 380, 200, 35);
            add(crs);

            mailid = new JTextField();
            mailid.setEditable(false);
            mailid.setFont(f3);
            mailid.setForeground(Color.BLACK);
            mailid.setBounds(1050, 470, 200, 35);
            add(mailid);

            edu = new JTextField();
            edu.setFont(f3);
            edu.setForeground(Color.BLACK);
            edu.setBounds(1050, 560, 200, 35);
            add(edu);

            enroldate = new JTextField();
            enroldate.setEditable(false);
            enroldate.setFont(f3);
            enroldate.setForeground(Color.BLACK);
            enroldate.setBounds(1050, 650, 200, 35);
            add(enroldate);

            quit = new JButton("QUIT");
            quit.setFont(f2);
            quit.setForeground(Color.WHITE);
            quit.setFocusPainted(false);
            quit.setBackground(cl2);
            quit.setBounds(700, 750, 130, 35);
            add(quit);
            quit.addActionListener(this);

            // JButton back, quit, update, view;BACK

            Color bac = new Color(153, 51, 255);
            Color viewC = new Color(51, 0, 255);
            Color up = new Color(0, 204, 51);

            back = new JButton("BACK");
            back.setFont(f2);
            back.setBackground(bac);
            back.setForeground(Color.WHITE);
            back.setFocusPainted(false);

            back.setBounds(910, 750, 130, 35);
            add(back);
            back.addActionListener(this);

            view = new JButton("STATUS");
            view.setFont(f2);
            view.setForeground(Color.WHITE);
            view.setFocusPainted(false);
            view.setBackground(viewC);
            view.setBounds(1120, 750, 130, 35);
            add(view);
            view.addActionListener(this);

            update = new JButton("EDIT DETAILS");
            update.setFont(f2);
            update.setForeground(Color.WHITE);
            update.setFocusPainted(false);
            update.setBackground(up);
            update.setBounds(1450, 410, 200, 50);
            add(update);
            update.addActionListener(this);

        }

        public void setMail(String m) {
            mail = m;
            name.setText(m);
            loaddatas();

        }

        public void loaddatas() {
            try {
                String sql = "SELECT * FROM student WHERE Mailid='" + mail + "'";
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    sid.setText(rs.getString("StudentID"));
                    sna.setText(rs.getString("StudentName"));
                    crs.setText(rs.getString("Course"));
                    mailid.setText(rs.getString("Mailid"));
                    edu.setText(rs.getString("Education"));
                    enroldate.setText(rs.getString("EnrollDate"));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void actionPerformed(ActionEvent ae) {
            try {
                String com = ae.getActionCommand();
                if (com.equals("BACK")) {
                    tb1.setSelectedIndex(0);
                } else if (com.equals("QUIT")) {
                    dispose();
                } else if (com.equals("EDIT DETAILS")) {
                    String serverurl = "rmi://" + "localhost" + "/addstudentserver";
                    addstudentinf in = (addstudentinf) Naming.lookup(serverurl);
                    System.out.println(in.studentupdate(sid.getText(), sna.getText(), edu.getText()));
                } else if (com.equals("STATUS")) {

                    tb1.setSelectedIndex(2);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public class payments extends JPanel implements FocusListener {
        Color cl = new Color(0, 255, 0);

        public payments() {
            setLayout(null);
            // setBackground(Color.GREEN);
            Color cl3 = new Color(51, 153, 255);
            setBackground(cl3);

            setLayout(null);
            Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 25);
            Font f3 = new Font("Copperplate Gothic Bold", Font.BOLD, 15);
            Font f = new Font("Algerian", Font.BOLD, 50);
            h1 = new JLabel("PAYMENT STATUS");

            h1.setFont(f);
            h1.setForeground(Color.white);
            // l1.setBackground(clr);
            h1.setBounds(750, 50, 900, 50);
            add(h1);
            Border border = BorderFactory.createLineBorder(Color.WHITE, 2);

            ta = new JTextArea();
            ta.setFont(f2);
            ta.setBorder(border);
            ta.setEditable(false);
            ta.setBounds(725, 200, 500, 600);
            add(ta);
            ta.addFocusListener(this);

        }

        public void focusGained(FocusEvent fe) {
            loadstatus();
        }

        public void focusLost(FocusEvent fe) {

        }

        public void loadstatus() {
            try {
                String sql = "SELECT * FROM payment WHERE StudentID='" + sid.getText()
                        + "'ORDER BY PaymentID DESC LIMIT 1";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    ta.setText(
                            "\n" + "\n" + "  STUDENT ID:" + rs.getString(2) + "\n" + "\n" + "  STUDENT NAME:"
                                    + rs.getString(3) + "\n" + "\n" + "  COURSE:" + rs.getString(4) + "\n" + "\n" +
                                    "  TOTAL PAID:" + rs.getString(6) + "\n" + "\n" + "  PENDING AMOUNT:"
                                    + rs.getString(7) + "\n" + "\n" + "  STATUS:" + rs.getString(12));

                    if (rs.getString(12).equals("PENDING")) {
                        ta.setBackground(Color.red);
                        ta.setForeground(Color.white);
                    } else if (rs.getString(12).equals("FULLY PAID")) {
                        ta.setBackground(cl);
                        ta.setForeground(Color.WHITE);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        new clientstumenu();
    }

}