package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Sylvia >

public class VerwerkKleurScherm extends Scherm implements ProductStandaard{
    ArrayList<Product> producten;
    int aantalRood = 4;  //todo:
    int aantalGeel = 3;
    int aantalBlauw = 2;

    private JPanel jpVerwerkKleur = new JPanel();
    Product roodProduct = new Product(ProductStandaard.rood);
    Product geelProduct = new Product(ProductStandaard.geel);
    Product blauwProduct = new Product(ProductStandaard.blauw);

    public VerwerkKleurScherm(){
        setLayout (new FlowLayout());
//        add(jlHeading);
        add(jpVerwerkKleur); //nodig?
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        //product
        int xRood = 160;
        int xGroen = 360;
        int xBlauw = 560;

        //        g.setColor(Color.GREEN);                    //todo: vervangen met Product > drawProduct, fillProduct etc
//        g.drawOval(xGroen, 120, formaat,formaat);
//        g.drawOval(xGroen, 170, formaat,formaat);
//        g.drawOval(xGroen, 220, formaat,formaat);
//        g.fillOval(xGroen,220,formaat,formaat);
//        roodProduct.drawOrderProduct();Product(g, xRood, 120);



//        g.setColor(Color.BLUE);
//        g.drawOval(xBlauw, 20,formaat, formaat);
//        g.drawOval(xBlauw, 170,formaat, formaat);
//        g.drawOval(xBlauw, 220,formaat, formaat);
//        g.fillOval(xBlauw, 220,formaat, formaat);

//        g.setColor(Color.RED);
//        g.drawOval(xRood,20,formaat, formaat);
//        g.drawOval(xRood, 170,formaat, formaat);
//        g.drawOval(xRood, 220,formaat, formaat);
//        g.fillOval(xRood, 120,formaat, formaat);
    }

}
//Sylvia <