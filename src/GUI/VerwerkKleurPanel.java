package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia

public class VerwerkKleurPanel extends JPanel {

    public VerwerkKleurPanel() {
        setPreferredSize(new Dimension(800,200));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        //product
        g.setColor(Color.GREEN);
        g.fillOval(160, 40, 80,80);
        g.setColor(Color.BLUE);
        g.fillOval(360, 40,80,80);
        g.setColor(Color.RED);
        g.fillOval(560,40,80,80);
    }

}
