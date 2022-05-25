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

        //componenten aanmaken
        jlHeading.setText("Uw voorraad wordt gesorteerd:");
        add(jlHeading);
        JPanel sorteerPanel = new JPanel();
        setRood(roodAantal);
        setGeel(geelAantal);
        setBlauw(blauwAantal);

        //toevoegen
        add(jlHeading);
        jlHeading.setHorizontalAlignment(JLabel.CENTER);
        add(sorteerPanel);

        setVisible(true);
    }

//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        //producten         //geen object, deze zijn afgestemd op verwerk schermen, niet sorteren
//        Product roodProduct = new Product();
//        Product geelProduct = new Product();
//        Product blauwproduct = new Product();
//
//
//        roodProduct.drawProduct(g,ProductStandaard.rood, 160,110);
//        geelProduct.drawProduct(g,ProductStandaard.geel, 360,110);
//        blauwproduct.drawProduct(g,ProductStandaard.blauw, 560, 110);
//
//        //aantallen
//
//        g.setColor(Color.BLACK);
//        g.setFont(fontSubTekst);
//        g.drawString(String.valueOf(roodAantal), 195,225);         //aantal rood
//        g.drawString(String.valueOf(geelAantal), 395,225);        //aantal geel
//        g.drawString(String.valueOf(blauwAantal), 595,225);        //aantal blauw

//    }

}

//<Sylvia