package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class VerwerkKleur extends VerwerkScherm implements ProductStandaard{

    private JPanel jpVerwerkKleur = new JPanel();

    public VerwerkKleur(){
        super();
        setLayout (new FlowLayout());
        add(jpVerwerkKleur);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        //product
        int formaat = ProductStandaard.productFormaat/2; //todo: gelijk trekken met verwerkDoos
        int xGroen = 160;
        int xBlauw = 360;
        int xRood = 560;


        g.setColor(Color.GREEN);                    //todo: vervangen met Product > drawProduct, fillProduct etc
        g.drawOval(xGroen, 120, formaat,formaat);
        g.drawOval(xGroen, 170, formaat,formaat);
        g.drawOval(xGroen, 220, formaat,formaat);
        g.fillOval(xGroen,220,formaat,formaat);

        g.setColor(Color.BLUE);
//        g.drawOval(xBlauw, 20,formaat, formaat);
        g.drawOval(xBlauw, 170,formaat, formaat);
        g.drawOval(xBlauw, 220,formaat, formaat);
        g.fillOval(xBlauw, 220,formaat, formaat);

        g.setColor(Color.RED);
//        g.drawOval(xRood,20,formaat, formaat);
        g.drawOval(xRood, 170,formaat, formaat);
        g.drawOval(xRood, 220,formaat, formaat);
//        g.fillOval(xRood, 120,formaat, formaat);
    }

}
//Sylvia <