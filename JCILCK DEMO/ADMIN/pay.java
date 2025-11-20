import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class pay extends JFrame implements FocusListener, ActionListener {
    JLabel h1, sid, sn, crs, crsfee, paid, totalpaid, pendingamount, date, paymentmode, tranid, rmks, sts;
    JComboBox<String> stid;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    JRadioButton r1, r2, r3;
    ButtonGroup bg;
    JTextArea ta;
    JButton quit, back, clear, submit;
    Connection cn;
    Statement st;
    ResultSet rs;
    String stuid;

    public pay() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jclick", "root", "");
            st = cn.createStatement();
            System.out.println("Connection Established...");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed!");
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
        h1 = new JLabel("payment window".toUpperCase());
        h1.setFont(f);
        h1.setBounds(720, 50, 800, 50);
        c.add(h1);

        Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 25);
        Font f3 = new Font("Copperplate Gothic Gothic Bold", Font.BOLD, 15);

        // Left side labels
        sid = new JLabel("Student ID:");
        sid.setFont(f2);
        sid.setBounds(300, 300, 250, 40);
        c.add(sid);

        sn = new JLabel("Student Name:");
        sn.setFont(f2);
        sn.setBounds(300, 390, 250, 40);
        c.add(sn);

        crs = new JLabel("Course:");
        crs.setFont(f2);
        crs.setBounds(300, 490, 250, 40);
        c.add(crs);

        crsfee = new JLabel("Course Fee:");
        crsfee.setFont(f2);
        crsfee.setBounds(300, 590, 250, 40);
        c.add(crsfee);

        paid = new JLabel("Paid Amount:");
        paid.setFont(f2);
        paid.setBounds(300, 690, 250, 40);
        c.add(paid);

        totalpaid = new JLabel("Total Paid:");
        totalpaid.setFont(f2);
        totalpaid.setBounds(300, 790, 250, 40);
        c.add(totalpaid);

        // Right side labels
        pendingamount = new JLabel("Pending Amount:");
        pendingamount.setFont(f2);
        pendingamount.setBounds(1100, 290, 280, 40);
        c.add(pendingamount);

        paymentmode = new JLabel("Payment Mode:");
        paymentmode.setFont(f2);
        paymentmode.setBounds(1100, 380, 250, 40);
        c.add(paymentmode);

        tranid = new JLabel("Transaction ID:");
        tranid.setFont(f2);
        tranid.setBounds(1100, 480, 250, 40);
        c.add(tranid);

        rmks = new JLabel("Remarks:");
        rmks.setFont(f2);
        rmks.setBounds(1100, 580, 250, 40);
        c.add(rmks);

        sts = new JLabel("Status:");
        sts.setFont(f2);
        sts.setBounds(1100, 780, 250, 40);
        c.add(sts);

        // Input fields
        stid = new JComboBox<>();
        stid.setFont(f2);
        stid.setBounds(600, 300, 300, 40);
        stid.addItem("SELECT");
        try {
            String sql = "SELECT StudentID from student";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                stuid = rs.getString(1);
                stid.addItem(stuid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        stid.addActionListener(this);
        c.add(stid);

        t1 = new JTextField();
        t1.setFont(f2);
        t1.setBounds(600, 390, 300, 40);
        t1.setEditable(false);
        c.add(t1);

        t2 = new JTextField();
        t2.setFont(f2);
        t2.setBounds(600, 490, 300, 40);
        t2.setEditable(false);
        c.add(t2);

        t3 = new JTextField();
        t3.setFont(f2);
        t3.setBounds(600, 590, 300, 40);
        t3.setEditable(false);
        c.add(t3);

        t4 = new JTextField();
        t4.setFont(f2);
        t4.setBounds(600, 690, 300, 40);
        c.add(t4);
        t4.addFocusListener(this);

        t5 = new JTextField();
        t5.setFont(f2);
        t5.setBounds(600, 790, 300, 40);
        t5.setEditable(false);
        c.add(t5);

        t6 = new JTextField();
        t6.setFont(f2);
        t6.setBounds(1400, 290, 300, 40);
        t6.setEditable(false);
        c.add(t6);

        Color clr = new Color(102, 255, 102);
        r1 = new JRadioButton("Cash");
        r2 = new JRadioButton("UPI");
        r3 = new JRadioButton("Card");
        bg = new ButtonGroup();
        r1.setFocusPainted(false);
        r2.setFocusPainted(false);
        r3.setFocusPainted(false);
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
        r1.setBackground(clr);
        r2.setBackground(clr);
        r3.setBackground(clr);
        r1.setFont(f3);
        r2.setFont(f3);
        r3.setFont(f3);

        r1.setBounds(1400, 380, 100, 40);
        r2.setBounds(1520, 380, 100, 40);
        r3.setBounds(1620, 380, 100, 40);
        c.add(r1);
        c.add(r2);
        c.add(r3);

        t7 = new JTextField();
        t7.setFont(f2);
        t7.setBounds(1400, 480, 300, 40);
        c.add(t7);

        ta = new JTextArea(10, 10);
        ta.setFont(f2);
        ta.setBounds(1400, 580, 300, 150);
        c.add(ta);

        t8 = new JTextField();
        t8.setFont(f2);
        t8.setBounds(1400, 780, 300, 40);
        t8.setEditable(false);
        c.add(t8);

        // Buttons
        clear = new JButton("CLEAR");
        clear.setFont(f2);
        clear.setFocusPainted(false);
        clear.setForeground(Color.white);
        clear.setBackground(cl4);
        clear.setBounds(475, 900, 170, 40);
        clear.addActionListener(this);
        c.add(clear);

        quit = new JButton("QUIT");
        quit.setFont(f2);
        quit.setFocusPainted(false);
        quit.setForeground(Color.WHITE);
        quit.setBackground(cl2);
        quit.setBounds(775, 900, 170, 40);
        quit.addActionListener(this);
        c.add(quit);

        back = new JButton("BACK");
        back.setFont(f2);
        back.setFocusPainted(false);
        back.setForeground(Color.WHITE);
        back.setBackground(cl3);
        back.setBounds(1075, 900, 170, 40);
        back.addActionListener(this);
        c.add(back);

        submit = new JButton("SUBMIT");
        submit.setFont(f2);
        submit.setFocusPainted(false);
        submit.setForeground(Color.WHITE);
        submit.setBackground(Color.GREEN);
        submit.setBounds(1375, 900, 170, 40);
        submit.addActionListener(this);
        c.add(submit);

        // Logo
        try {
            ImageIcon icon = new ImageIcon("logo2.png");
            Image img = icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            JLabel logoLabel = new JLabel(icon);
            logoLabel.setBounds(20, 10, 350, 200);
            add(logoLabel);
        } catch (Exception e) {
            System.out.println("Logo not found");
        }

        setTitle("PAYMENT MANAGEMENT SYSTEM");
        getContentPane().setBackground(clr);
    }

    public void focusGained(FocusEvent fe) {
        // Not needed for now
    }

    public void focusLost(FocusEvent fe) {
        if (fe.getSource() == t4) {
            calculatePendingAmount();
        }
    }

    private void calculatePendingAmount() {
        try {
            if (!t3.getText().isEmpty() && !t4.getText().isEmpty()) {
                double fee = Double.parseDouble(t3.getText());
                double currentPayment = Double.parseDouble(t4.getText());

                // Calculate previous total paid
                double previousPaid = getPreviousTotalPaid();

                // Calculate new total paid (previous + current)
                double totalPaid = previousPaid + currentPayment;
                t5.setText(String.format("%.2f", totalPaid));

                // Calculate pending amount (course fee - total paid)
                double pending = fee - totalPaid;
                t6.setText(String.format("%.2f", pending));

                // Set status
                if (pending <= 0) {
                    t8.setText("FULLY PAID");
                    t8.setBackground(Color.GREEN);
                } else {
                    t8.setText("PENDING");
                    t8.setBackground(Color.YELLOW);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!");
        }
    }

    private double getPreviousTotalPaid() {
        try {
            String selectedStudent = (String) stid.getSelectedItem();
            if (!selectedStudent.equals("SELECT")) {
                String sql = "SELECT SUM(AmountPaid) FROM payment WHERE StudentID = '" + selectedStudent + "'";
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    double sum = rs.getDouble(1);
                    return rs.wasNull() ? 0.0 : sum;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == stid) {
            loadStudentData();
        } else if (ae.getSource() == submit) {
            submitPayment();
        } else if (ae.getSource() == clear) {
            clearForm();
        } else if (ae.getSource() == back) {
            // Go back to previous window
            new paymenu();
            this.setVisible(false);
        } else if (ae.getSource() == quit) {
            System.exit(0);
        }
    }

    private void loadStudentData() {
        String str = (String) stid.getSelectedItem();
        if (!str.equals("SELECT")) {
            try {
                String sql = "SELECT * FROM student WHERE StudentID ='" + str + "'";
                rs = st.executeQuery(sql);
                if (rs.next()) {
                    t1.setText(rs.getString(2)); // Student Name
                    t2.setText(rs.getString(3)); // Course
                    t3.setText(rs.getString(4)); // Course Fee

                    // Calculate previous total paid
                    double previousPaid = getPreviousTotalPaid();
                    t5.setText(String.format("%.2f", previousPaid));

                    // Calculate pending amount based on previous payments
                    double fee = Double.parseDouble(t3.getText());
                    double pending = fee - previousPaid;
                    t6.setText(String.format("%.2f", pending));

                    if (pending <= 0) {
                        t8.setText("FULLY PAID");
                        t8.setBackground(Color.GREEN);
                    } else {
                        t8.setText("PENDING");
                        t8.setBackground(Color.YELLOW);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            clearForm();
        }
    }

    private void submitPayment() {
        try {
            String selectedStudent = (String) stid.getSelectedItem();
            if (selectedStudent.equals("SELECT")) {
                JOptionPane.showMessageDialog(this, "Please select a student!");
                return;
            }

            if (t4.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter paid amount!");
                return;
            }

            // Check if any radio button is selected
            if (!r1.isSelected() && !r2.isSelected() && !r3.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select payment mode!");
                return;
            }

            // Get payment mode
            String paymentMode = "";
            if (r1.isSelected())
                paymentMode = "Cash";
            else if (r2.isSelected())
                paymentMode = "UPI";
            else if (r3.isSelected())
                paymentMode = "Card";

            // Get current date
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            // Validate and get numeric values
            double courseFee = Double.parseDouble(t3.getText());
            double currentPayment = Double.parseDouble(t4.getText());
            double totalPaid = Double.parseDouble(t5.getText());
            double pendingAmount = Double.parseDouble(t6.getText());

            // Insert into database
            String sql = "INSERT INTO payment (StudentID, StudentName, Course, AmountPaid, TotalPaid, " +
                    "PendingAmount, PaymentDate, PaymentMode, TransactionID, Remarks, Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, selectedStudent);
            pst.setString(2, t1.getText());
            pst.setString(3, t2.getText());
            pst.setDouble(4, currentPayment);
            pst.setDouble(5, totalPaid);
            pst.setDouble(6, pendingAmount);
            pst.setString(7, currentDate);
            pst.setString(8, paymentMode);
            pst.setString(9, t7.getText());
            pst.setString(10, ta.getText());
            pst.setString(11, t8.getText());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Payment recorded successfully!");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to record payment!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void clearForm() {
        stid.setSelectedIndex(0);
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        t6.setText("");
        t7.setText("");
        t8.setText("");
        ta.setText("");
        bg.clearSelection();
        t8.setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        new pay();
    }
}