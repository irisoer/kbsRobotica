package GUI;

import Applicatie.Order;
import Applicatie.Artikel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Sylvia >

public class VerwerkKleurScherm extends VerwerkCarrouselScherm implements ArtikelStandaard {
    private int aantalRood = 6;  //todo: hardcode
    private int aantalGeel = 4;
    private int aantalBlauw = 3;
    private int pickedRood = 2;
    private int pickedGeel = 3;
    private int pickedBlauw = 1;

    private ArrayList<Artikel> producten = new ArrayList<>();

    public VerwerkKleurScherm() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        drawKleur(g, rood);
        drawKleur(g, geel);
        drawKleur(g, blauw);
        fillKleur(g,rood,Frame.aantalRood);
        fillKleur(g,geel,Frame.aantalGeel);
        fillKleur(g,blauw,Frame.aantalBlauw);

        g.setFont(fontTekst);
        g.setColor(Color.BLACK);
        g.drawString("Rood", 180, 290);
        g.drawString("Geel", 380, 290);
        g.drawString("Blauw", 580, 290);
    }

    public void drawKleur(Graphics g, Color kleur) {        //tekenen lege artikelen

        // y positites voor tekenen
        int yRij1 = 220;            //y positie producten rij 1
        int yRij2 = 170;            //y positie producten rij 2
        int yRij3 = 120;            //y positie producten rij 3

        //x posities producten
        int xRood = 160;
        int xGeel = 360;
        int xBlauw = 560;

        int aantalKleur = 0;

        if (kleur.equals(rood)) {
            aantalKleur = Order.aantalRood;
        }
        if (kleur.equals(geel)) {
            aantalKleur = Order.aantalGeel;
        }
        if (kleur.equals(blauw)) {
            aantalKleur = Order.aantalBlauw;
        }

        for (int aantal = aantalKleur; aantal > 0; aantal--) {      //tekenen artikelen
            Artikel a = new Artikel();
            a.setKleur(String.valueOf(kleur));
            g.setColor(a.getJavaKleur());
            int x;
            int y = 0;
            int marge = 55;

            //x positie
            if (kleur.equals(rood)) {
                a.setPosX(xRood);
            }
            if (kleur.equals(geel)) {
                a.setPosX(xGeel);
            }
            if (kleur.equals(blauw)) {
                a.setPosX(xBlauw);
            }

            if (aantal %2 != 0) {           //kolom 1 (oneven aantallen)
                aantalKleur--;
            }
            else {                          //kolom 2 (even aantallen)
                x = a.getPosX() + marge;
                a.setPosX(x);
                aantalKleur--;
            }

            //y positie, gelijk voor alle kleuren
            if (aantal <= 2) {
                y = yRij1;
            }
            if (aantal > 2 && aantal <= 4) {
                y = yRij2;
            }
            if (aantal > 4 && aantal <= 6) {
                y = yRij3;
            }
            a.setPosY(y);
            producten.add(a);
        }
        for (Artikel p : producten) {
            p.drawKleinArtikel(g);
        }
    }

    public void fillKleur(Graphics g, Color kleur, int aantalPicked){       //tekenen gepickte producten
        int tePicken = 0;
        int aantalKleur = 0;

        if (kleur.equals(rood)) {
            aantalKleur = aantalRood;
        }
        if (kleur.equals(geel)) {
            aantalKleur = aantalGeel;
        }
        if (kleur.equals(blauw)) {
            aantalKleur = aantalBlauw;
        }
        for(tePicken = aantalKleur; aantalPicked <= tePicken; tePicken--){
            for (Artikel p : producten) {
                if(p.getKleur().equals(kleur) && aantalPicked > 0) {
                    p.fillArtikel(g);
                    aantalPicked--;
                }
            }
        }

    }

    @Override
    public void reload() {
        repaint();
        super.reload();
    }
}
//Sylvia <