
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class batchmenu extends JFrame implements ActionListener {

    JLabel h1;
    JButton add, edit, delete, view, back;

    public batchmenu() {
        Container c = getContentPane();
        c.setLayout(null);

        Color cl = new Color(160, 108, 202);
        Color cl2 = new Color(193, 70, 68);
        Font f = new Font("Algerian", Font.BOLD, 60);
        Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 20);
        h1 = new JLabel("BATCH MENU");
        h1.setFont(f);
        // l1.setBackground(clr);
        h1.setBounds(750, 50, 800, 50);
        c.add(h1);

        add = new JButton("ADD BATCHES");
        add.setFont(f2);
        add.setBackground(cl);
        add.setFocusPainted(false);
        add.setForeground(Color.WHITE);
        add.setBounds(830, 280, 250, 50);
        c.add(add);
        add.addActionListener(this);
        edit = new JButton("EDIT BATCHES");
        edit.setFont(f2);
        edit.setBackground(cl);
        edit.setFocusPainted(false);
        edit.setForeground(Color.WHITE);
        edit.setBounds(830, 380, 250, 50);
        c.add(edit);
        edit.addActionListener(this);

        delete = new JButton("REMOVE BATCHES");
        delete.setFont(f2);
        delete.setBackground(cl);
        delete.setFocusPainted(false);
        delete.setForeground(Color.WHITE);
        delete.setBounds(830, 480, 250, 50);
        c.add(delete);
        delete.addActionListener(this);

        view = new JButton("VIEW BATCHES");
        view.setFont(f2);
        view.setBackground(cl);
        view.setFocusPainted(false);
        view.setForeground(Color.WHITE);
        view.setBounds(830, 580, 250, 50);
        c.add(view);
        view.addActionListener(this);
        back = new JButton("BACK");
        back.setFont(f2);
        back.setBackground(cl2);
        back.setFocusPainted(false);
        back.setForeground(Color.WHITE);
        back.setBounds(830, 680, 250, 50);
        c.add(back);
        back.addActionListener(this);

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

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);
        setVisible(true);
        setTitle("BATCHES MENU");
        Color clr = new Color(102, 255, 102);
        getContentPane().setBackground(clr);

    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (str.equals("ADD BATCHES")) {
            new addbatchclient();
            this.setVisible(false);
        } else if (str.equals("BACK")) {
            new home2();
            this.setVisible(false);
        } else if (str.equals("EDIT BATCHES")) {
            new updateaddbatchclient();
            this.setVisible(false);

        } else if (str.equals("REMOVE BATCHES")) {
            new deletebat();
            this.setVisible(false);
        } else if (str.equals("VIEW BATCHES")) {
            new viewbatch();
            this.setVisible(false);
        }

    }

    public static void main(String[] args) {
        new batchmenu();
    }
}
