package layout.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class GraphPanel extends JComponent {
    BufferedImage bufferedImage;

    public GraphPanel(int width, int height){
        setPreferredSize(new Dimension(width,height));
        bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bufferedImage,0,0,this);
    }

    public Graphics2D getGraphics(){
        return (Graphics2D) bufferedImage.getGraphics();
    }

    public void clear(){
        Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.BLACK);
        g.drawRect(0,0,bufferedImage.getWidth(),bufferedImage.getHeight());
    }
}
