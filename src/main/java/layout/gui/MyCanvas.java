package layout.gui;

import java.awt.*;

public class MyCanvas extends Canvas {
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(12,12,100,100);
    }
}
