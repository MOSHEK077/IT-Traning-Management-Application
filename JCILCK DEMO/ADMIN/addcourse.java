
import javax.print.attribute.standard.MediaSize.NA;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.Naming;

//client programm
public class addcourse extends JFrame implements ActionListener {
    JButton add, clear, quit;
    JTextField t1, t2, t3, t5, t6, t7; // Course Name,
    JLabel h1, l1, l2, l3, l4, l5, l6, l7;
    JTextArea t4;

    public addcourse() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(false);

        Container c = getContentPane();
        c.setLayout(null);
        Color cl2 = new Color(193, 70, 68);
        Color cl3 = new Color(51, 51, 255);
        Color cl4 = new Color(0, 51, 0);
        Font f = new Font("Algerian", Font.BOLD, 50);
        h1 = new JLabel("ADD COURSE DETAILS");
        h1.setFont(f);
        // l1.setBackground(clr);
        h1.setBounds(675, 50, 800, 50);
        c.add(h1);

        Font f2 = new Font("Copperplate Gothic Bold", Font.BOLD, 25);
        /*
         * l1 = new JLabel("Course ID:");
         * l1.setFont(f2);
         * l1.setBounds(600, 250, 250, 40);
         * c.add(l1);
         */
        l2 = new JLabel("Course Name:");
        l2.setFont(f2);
        l2.setBounds(600, 330, 250, 40);
        c.add(l2);

        l3 = new JLabel("Course Duration:");
        l3.setFont(f2);
        l3.setBounds(600, 420, 280, 40);
        c.add(l3);

        l4 = new JLabel("Course Description:");
        l4.setFont(f2);
        l4.setBounds(600, 510, 350, 40);
        c.add(l4);

        l5 = new JLabel("Course Fees:");
        l5.setFont(f2);
        l5.setBounds(600, 650, 250, 40);
        c.add(l5);

        /*
         * t1 = new JTextField();
         * t1.setFont(f2);
         * t1.setBounds(1000, 250, 300, 40);
         * c.add(t1);
         */

        t2 = new JTextField();
        t2.setFont(f2);
        t2.setBounds(1000, 330, 300, 40);
        c.add(t2);

        t3 = new JTextField();
        t3.setFont(f2);
        t3.setBounds(1000, 420, 300, 40);
        c.add(t3);

        t4 = new JTextArea();
        t4.setFont(f2);
        t4.setLineWrap(true);
        t4.setWrapStyleWord(true);
        t4.setBounds(1000, 510, 300, 90);
        c.add(t4);

        t5 = new JTextField();
        t5.setFont(f2);
        t5.setBounds(1000, 650, 300, 40);
        c.add(t5);

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

        add = new JButton("ADD");
        add.setFont(f2);
        add.setFocusPainted(false);
        add.setForeground(Color.WHITE);
        add.setBackground(cl4);
        add.setBounds(1130, 750, 170, 40);
        c.add(add);
        add.addActionListener(this);

        // addcourse ac = new addcourse(); setVisible(true);
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

        setTitle("ADD COURSE");
        Color clr = new Color(102, 255, 102);
        getContentPane().setBackground(clr);
    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (str.equals("CLEAR")) {

            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");

        } else if (str.equals("BACK")) {
            new course2();
            this.setVisible(false);
        } else if (str.equals("ADD")) {
            try {
                String serverurl = "rmi://" + "localhost" + "/addstudentserver";
                addstudentinf in = (addstudentinf) Naming.lookup(serverurl);
                System.out.println(in.newcourse(t2.getText(), t3.getText(), t4.getText(), t5.getText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new addcourse();

    }
}