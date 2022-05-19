package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >
public class VerwerkDoosPanel extends JPanel {

    public VerwerkDoosPanel(){
            setPreferredSize(new Dimension(800,200));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.WHITE);

        //dozen
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        int hoogte = 105;   //hoogte doos
        int breedte = 60;   //breedte doos
        int formaat = 40;   //formaat product
        int xDoos1 = 160;
        int xDoos2 = 360;
        int xDoos3 = 560;
        g.drawRect(xDoos1-10,65,breedte,hoogte);     //doos1  (10 is voor uitlijnen doos rond producten)
        g.drawRect(xDoos2-10,65,breedte,hoogte);     //doos2
        g.drawRect(xDoos3-10,65,breedte,hoogte);     //doos3


        g2.setStroke(new BasicStroke(1));
        g.setColor(Color.GREEN);                    //objecten maken van ovals?
        g.drawOval(xDoos1, 70, formaat,formaat);
        g.drawOval(xDoos1, 120, formaat,formaat);
        g.fillOval(xDoos1,120,formaat,formaat);

        g.setColor(Color.BLUE);
        g.drawOval(xDoos2, 70,formaat, formaat);
        g.drawOval(xDoos2, 120,formaat, formaat);
        g.fillOval(xDoos2, 120,formaat, formaat);

        g.setColor(Color.RED);
        g.drawOval(xDoos3, 70,formaat, formaat);
        g.drawOval(xDoos3, 120,formaat, formaat);
//        g.fillOval(xDoos3, 120,formaat, formaat);


        //pijl
        int grootte = 20;       //grootte van pijl
        g.setColor(Color.BLACK);
        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(xDoos2+10,30);     //linker punt
        arrowHead.addPoint(xDoos2+30,30);     //rechter punt
        arrowHead.addPoint( xDoos2+20,50);     //onderste punt
        g.fillPolygon(arrowHead);

        setVisible(true);

    }


}
//Sylvia <