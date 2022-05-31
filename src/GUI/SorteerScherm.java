package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class SorteerScherm extends Scherm implements Layout, ArtikelStandaard {
    private Color gescandeKleur; //todo: aantallen meegeven met setter, if gescande kleur is rood, aantalRood++ etc etc
    int aantalRood = 0;
    int aantalGeel = 0;
    int aantalBlauw = 0;

    public void setRood(int rood) {
        this.aantalRood = rood;
    }
    public void setGeel(int geel) {
        this.aantalGeel = geel;
    }
    public void setBlauw(int blauw) {
        this.aantalBlauw = blauw;
    }

    public SorteerScherm() {
        setLayout (new FlowLayout());

        //componenten
//        jlHeading = new JLabel();
        jlHeading.setText("Uw voorraad wordt gesorteerd:");
        JPanel sorteerPanel = new JPanel();
        setRood(aantalRood);
        setGeel(aantalGeel);
        setBlauw(aantalBlauw);

        //toevoegen
        add(jlHeading);
        add(sorteerPanel);

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //producten
        Product roodProduct = new Product(ArtikelStandaard.rood);
        Product geelProduct = new Product(ArtikelStandaard.geel);
        Product blauwProduct = new Product(ArtikelStandaard.blauw);

        roodProduct.drawArtikel(g,roodProduct,160,110);
        geelProduct.drawArtikel(g,geelProduct, 360,110);
        blauwProduct.drawArtikel(g,blauwProduct, 560, 110);

        //aantallen
        g.setColor(Color.BLACK);
        g.setFont(fontSubTekst);
        g.drawString(String.valueOf(aantalRood), 195,225);         //aantal rood
        g.drawString(String.valueOf(aantalGeel), 395,225);        //aantal geel
        g.drawString(String.valueOf(aantalBlauw), 595,225);        //aantal blauw

    }

}

//<Sylvia