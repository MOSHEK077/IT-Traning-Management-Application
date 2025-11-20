
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class home2 extends JFrame implements ActionListener {
    JButton crs, stn, bch, pay, lout;
    JLabel h1;

    public home2() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setVisible(true);
        Container c = getContentPane();
        c.setLayout(null);

        Color cl = new Color(101, 130, 237);

        Color cl2 = new Color(193, 70, 68);
        Font f = new Font("Algerian", Font.BOLD, 60);
        Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 20);
        h1 = new JLabel("ADMIN MENU");
        h1.setFont(f);
        // l1.setBackground(clr);
        h1.setBounds(750, 50, 800, 50);
        c.add(h1);

        crs = new JButton(" COURSE ");
        crs.setFont(f2);
        crs.setBackground(cl);
        crs.setForeground(Color.WHITE);
        crs.setFocusPainted(false);
        crs.setBounds(829, 320, 220, 50);
        c.add(crs);
        crs.addActionListener(this);
        stn = new JButton(" BATCHES ");
        stn.setFont(f2);
        stn.setBackground(cl);
        stn.setForeground(Color.WHITE);
        stn.setFocusPainted(false);
        stn.setBounds(829, 390, 220, 50);
        c.add(stn);
        stn.addActionListener(this);

        bch = new JButton(" STUDENT ");
        bch.setFont(f2);
        bch.setBackground(cl);
        bch.setForeground(Color.WHITE);
        bch.setFocusPainted(false);
        bch.setBounds(829, 460, 220, 50);
        c.add(bch);
        bch.addActionListener(this);

        pay = new JButton(" PAYMENTS ");
        pay.setFont(f2);
        pay.setBackground(cl);
        pay.setForeground(Color.WHITE);
        pay.setFocusPainted(false);
        pay.setBounds(829, 530, 220, 50);
        c.add(pay);
        pay.addActionListener(this);
        lout = new JButton(" LOGOUT ");
        lout.setFont(f2);
        lout.setBackground(cl2);
        lout.setForeground(Color.WHITE);
        lout.setFocusPainted(false);
        lout.setBounds(829, 600, 220, 50);
        c.add(lout);
        lout.addActionListener(this);
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

        setTitle("ADMIN MENU");
        Color clr = new Color(102, 255, 102);
        getContentPane().setBackground(clr);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (str.equals(" COURSE ")) {
            new course2();
            this.setVisible(false);
        } else if (str.equals(" LOGOUT ")) {
            new login();
            this.setVisible(false);
        }

        else if (str.equals(" STUDENT ")) {
            new studentmenu();
            this.setVisible(false);
        } else if (str.equals(" BATCHES ")) {
            new batchmenu();
            this.setVisible(false);
        } else if (str.equals(" PAYMENTS ")) {
            new paymenu();
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new home2();

    }
}
