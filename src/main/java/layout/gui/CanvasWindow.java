package layout.gui;

import javax.swing.*;
import java.awt.*;

public class CanvasWindow extends JFrame {
    JPanel panel = new JPanel();
    Canvas canvas = new MyCanvas();
    public CanvasWindow(){
        setSize(new Dimension(600,480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setSize(this.getWidth(), this.getHeight());
        canvas.setBackground(Color.BLACK);
        canvas.setVisible(true);
        panel.add(canvas,BorderLayout.CENTER);
        add(panel);

    }
}
