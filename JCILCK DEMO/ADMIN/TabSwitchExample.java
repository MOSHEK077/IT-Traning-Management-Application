import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TabSwitchExample {
    public static void main(String[] args) {
        // Create the JFrame
        JFrame frame = new JFrame("JTabbedPane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create the JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each tab
        JPanel tab1 = new JPanel();
        tab1.add(new JLabel("This is Tab 1"));
        JButton switchToTab2 = new JButton("Go to Tab 2");
        tab1.add(switchToTab2);

        JPanel tab2 = new JPanel();
        tab2.add(new JLabel("This is Tab 2"));
        JButton switchToTab1 = new JButton("Go to Tab 1");
        tab2.add(switchToTab1);

        // Add tabs to the JTabbedPane
        tabbedPane.addTab("Tab 1", tab1);
        tabbedPane.addTab("Tab 2", tab2);

        // Add action listeners to buttons to switch tabs
        switchToTab2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(1); // Switch to Tab 2
            }
        });

        switchToTab1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(0); // Switch to Tab 1
            }
        });

        // Add the tabbed pane to the frame
        frame.add(tabbedPane, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }
}
