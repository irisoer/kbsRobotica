import javax.swing.*;
import java.awt.*;

public class PanelVerwerken extends JPanel {

    public PanelVerwerken() {
        setPreferredSize(new Dimension(300,100));
    }
    int offset = 10;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        //product
        g.setColor(Color.GREEN);
        g.fillOval(70, 40, 30, 30);
        g.setColor(Color.BLUE);
        g.fillOval(130, 40,30, 30);
        g.setColor(Color.RED);
        g.fillOval(190,40,30,30);


    }

}
