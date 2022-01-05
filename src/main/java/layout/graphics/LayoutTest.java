package layout.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LayoutTest extends JFrame {

    GraphPanel graphPanel;
    public LayoutTest(){
        setSize(600,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Soubor");
        menuBar.add(menu);
        menu.add(new AbstractAction("Test") {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(menuBar,BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(new JLabel("Test"));
        panel.add(new JTextField(10));
        panel.add(new JLabel("Test"));
        panel.add(new JTextField(10));
        panel.add(new JLabel("Test"));
        panel.add(new JTextField(10));
        panel.add(new JButton("Klik"));
        mainPanel.add(panel,BorderLayout.NORTH);
        mainPanel.add((graphPanel = new GraphPanel(600,400)),BorderLayout.CENTER);
        Graphics g = graphPanel.getGraphics();
        g.setColor(Color.RED);
        g.drawLine(20,20,100,100);
        add(mainPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new LayoutTest();
                frame.setVisible(true);
            }
        });
    }
}

