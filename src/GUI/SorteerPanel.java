package GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

//Sylvia>

public class SorteerPanel extends JPanel implements Lettertype{

    public SorteerPanel() {
        setPreferredSize(new Dimension(800,200));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        //producten
        g.setColor(Color.GREEN);
        g.fillOval(160, 40, 80,80);
        g.setColor(Color.BLUE);
        g.fillOval(360, 40,80,80);
        g.setColor(Color.RED);
        g.fillOval(560,40,80,80);

        //aantallen
        int groen = 5;
        int blauw = 8;
        int rood = 9;
        g.setColor(Color.BLACK);
        g.setFont(fontSubTekst);
        g.drawString(String.valueOf(groen), 195,135);         //aantal groen
        g.drawString(String.valueOf(blauw), 395,135);         //aantal blauw
        g.drawString(String.valueOf(rood), 595,135);         //aantal rood

    }

}
//<Sylvia