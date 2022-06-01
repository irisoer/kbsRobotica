package GUI;

import Applicatie.ArduinoInpak;
import Applicatie.ArduinoSorteer;
import com.sun.tools.jconsole.JConsoleContext;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class SorteerScherm extends Scherm implements Layout, ArtikelStandaard {
    private Color gescandeKleur; //todo: aantallen meegeven met setter, if gescande kleur is rood, aantalRood++ etc etc
    int aantalRood = 0;
    int aantalGeel = 0;
    int aantalBlauw = 0;

    Graphics g;

    public void setRood(int rood) {
        this.aantalRood = rood;
    }
    public void setGeel(int geel) {
        this.aantalGeel = geel;
    }
    public void setBlauw(int blauw) {
        this.aantalBlauw = blauw;
    }

    public void moduleData(char payload) {
        switch (payload) {
            case 'r':
                this.aantalRood++;
                break;
            case 'g':
                this.aantalGeel++;
                break;
            case 'b':
                this.aantalBlauw++;
                break;
            default:
                break;
        }
        System.out.println("payload" + payload);
        repaint(); // todo: Het werkt niet als het opnieuw geladen wordt (Eventlistener?)
    }

    public SorteerScherm() {
        setLayout (new FlowLayout());

        //componenten
//        jlHeading = new JLabel();
        jlHeading.setText("Uw voorraad wordt gesorteerd:");

        //toevoegen
        add(jlHeading);

        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("paintComponent");
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

    public void startSorteren() {
        try {
            ArduinoSorteer.sorteer(Frame.arduinoInpak, Frame.arduinoSorteer, this);

        } catch (Exception e) {}
    }
}

//<Sylvia