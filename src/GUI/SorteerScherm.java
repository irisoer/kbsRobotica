package GUI;

import Applicatie.Arduino;
import Applicatie.ArduinoInpak;
import Applicatie.ArduinoSorteer;
import com.sun.tools.jconsole.JConsoleContext;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class SorteerScherm extends Scherm implements Layout, ArtikelStandaard {
    private Color gescandeKleur; //todo: aantallen meegeven met setter, if gescande kleur is rood, aantalRood++ etc etc
    static int aantalRood = 0;
    static int aantalGeel = 0;
    static int aantalBlauw = 0;

    public static void moduleData(char payload) {
        System.out.println(payload);
        if(payload == 's') Frame.setScherm(Frame.Schermen.EindSchermSorteren);
        switch (payload) {
            case 'r' -> aantalRood++;
            case 'g' -> aantalGeel++;
            case 'b' -> aantalBlauw++;
            default -> {}
        }
        Frame.sorteerScherm.repaint();
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
            Arduino.MyPortListener.currentState = Arduino.MyPortListener.State.WachtOpScan;
            Arduino.MyPortListener.currentJob = Arduino.MyPortListener.Job.Sorteer;
        } catch (Exception e) {}
    }
}

//<Sylvia