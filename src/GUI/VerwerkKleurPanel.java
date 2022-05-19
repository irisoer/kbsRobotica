package GUI;

import javax.swing.*;
import java.awt.*;


//Sylvia >

public class VerwerkKleurPanel extends JPanel implements Lettertype {

    public VerwerkKleurPanel() {
        setPreferredSize(new Dimension(800,350));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        //product
        int formaat = 40;                           //todo: aantal ovals afhankelijk van order. Invullen wanneer object is ingepakt
        int xGroen = 160;
        int xBlauw = 360;
        int xRood = 560;


        g.setColor(Color.GREEN);                    //objecten maken van ovals?
        g.drawOval(xGroen, 20, formaat,formaat);
        g.drawOval(xGroen, 70, formaat,formaat);
        g.drawOval(xGroen, 120, formaat,formaat);
        g.fillOval(xGroen,120,formaat,formaat);

        g.setColor(Color.BLUE);
//        g.drawOval(xBlauw, 20,formaat, formaat);
        g.drawOval(xBlauw, 70,formaat, formaat);
        g.drawOval(xBlauw, 120,formaat, formaat);
        g.fillOval(xBlauw, 120,formaat, formaat);

        g.setColor(Color.RED);
//        g.drawOval(xRood,20,formaat, formaat);
        g.drawOval(xRood, 70,formaat, formaat);
        g.drawOval(xRood, 120,formaat, formaat);
//        g.fillOval(xRood, 120,formaat, formaat);



    }

}
//Sylvia <