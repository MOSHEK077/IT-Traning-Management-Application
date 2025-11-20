
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class login extends JFrame implements ActionListener {
    // This is just Home Page

    JLabel title, frm, un, ps;
    JTextField una;
    JPasswordField psw;
    JButton clear, lg;

    public login() {

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        Color clr = new Color(102, 255, 102);
        getContentPane().setBackground(clr);
        Container c = getContentPane();
        c.setLayout(null);

        Font f = new Font("Algerian", Font.BOLD, 50);
        Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 25);
        Font f3 = new Font("Copperplate Gothic Bold", Font.BOLD, 15);

        title = new JLabel("JCLICK SOLUTION,NAGERCOIL");
        title.setFont(f);
        title.setBounds(550, 50, 800, 50);
        c.add(title);

        frm = new JLabel("ADMIN LOGIN ");
        frm.setFont(f2);
        frm.setBounds(805, 190, 800, 50);
        c.add(frm);

        un = new JLabel("USERNAME :");
        un.setFont(f3);
        un.setBounds(700, 300, 140, 50);
        c.add(un);

        ps = new JLabel("PASSWORD :");
        ps.setFont(f3);
        ps.setBounds(700, 350, 140, 50);
        c.add(ps);

        una = new JTextField();
        una.setFont(f3);
        una.setBounds(950, 310, 150, 30);
        c.add(una);

        psw = new JPasswordField();
        psw.setFont(f3);
        psw.setEchoChar('*');
        psw.setBounds(950, 360, 150, 30);
        c.add(psw);

        clear = new JButton("CLEAR");
        clear.setFont(f3);
        clear.setForeground(Color.WHITE);
        clear.setBackground(Color.RED);
        clear.setFocusPainted(false);
        clear.setBounds(700, 450, 150, 30);
        c.add(clear);

        lg = new JButton("LOGIN");
        lg.setFont(f3);
        lg.setForeground(Color.WHITE);
        lg.setBackground(Color.BLUE);
        lg.setFocusPainted(false);
        lg.setBounds(950, 450, 150, 30);
        c.add(lg);
        clear.addActionListener(this);
        lg.addActionListener(this);

        /*
         * ImageIcon img = new ImageIcon("");
         * Image scle = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
         * ImageIcon logo = new ImageIcon(scle);
         * 
         * JLabel lb = new JLabel(logo);
         * lb.setBounds(60, 60, 100, 100);
         * c.add(lb);
         */
        setVisible(true);
        setTitle("ADMIN LOGIN");

    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (str.equals("CLEAR")) {
            una.setText("");
            psw.setText("");

        } else if (str.equals("LOGIN")) {
            String user = una.getText();
            String pass = psw.getText();
            if (user.equalsIgnoreCase("Jones007") & pass.equalsIgnoreCase("Jon$jon12")) {
                new home2();// userdashboard
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }

        }
    }

    public static void main(String[] args) {
        new login();
    }
}