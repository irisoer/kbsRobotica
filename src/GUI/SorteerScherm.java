package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class SorteerScherm extends Scherm implements Layout, ProductStandaard{
    private Color gescandeKleur; //todo: aantallen meegeven met setter, if gescande kleur is rood, roodAantal++ etc etc
    int roodAantal = 0;
    int geelAantal = 0;
    int blauwAantal = 0;

    public void setRood(int rood) {
        this.roodAantal = rood;
    }
    public void setGeel(int geel) {
        this.geelAantal = geel;
    }
    public void setBlauw(int blauw) {
        this.blauwAantal = blauw;
    }

    public SorteerScherm() {
        setLayout (new FlowLayout());

        //componenten
//        jlHeading = new JLabel();
        jlHeading.setText("Uw voorraad wordt gesorteerd:");
        JPanel sorteerPanel = new JPanel();
        setRood(roodAantal);
        setGeel(geelAantal);
        setBlauw(blauwAantal);

        //toevoegen
        add(jlHeading);
        add(sorteerPanel);

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //producten
        Product roodProduct = new Product(ProductStandaard.rood);
        Product geelProduct = new Product(ProductStandaard.geel);
        Product blauwProduct = new Product(ProductStandaard.blauw);

        roodProduct.drawProduct(g,roodProduct,160,110);
        geelProduct.drawProduct(g,geelProduct, 360,110);
        blauwProduct.drawProduct(g,blauwProduct, 560, 110);

        //aantallen
        g.setColor(Color.BLACK);
        g.setFont(fontSubTekst);
        g.drawString(String.valueOf(roodAantal), 195,225);         //aantal rood
        g.drawString(String.valueOf(geelAantal), 395,225);        //aantal geel
        g.drawString(String.valueOf(blauwAantal), 595,225);        //aantal blauw

    }

}

//<Sylvia