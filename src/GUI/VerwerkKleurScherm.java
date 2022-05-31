package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Sylvia >

public class VerwerkKleurScherm extends VerwerkCarrouselScherm implements ProductStandaard {
    private int aantalRood = 6;  //todo: hardcode
    private int aantalGeel = 4;
    private int aantalBlauw = 3;
    private int pickedRood = 2;
    private int pickedGeel = 3;
    private int pickedBlauw = 1;

    private JPanel jpVerwerkKleur = new JPanel();
    private ArrayList<Product> producten = new ArrayList<>();

    public VerwerkKleurScherm() {
        setLayout(new FlowLayout());
        add(jlHeading);
        add(jpVerwerkKleur);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        drawKleur(g, rood);
        drawKleur(g, geel);
        drawKleur(g, blauw);
        fillKleur(g,rood,pickedRood);
        fillKleur(g,geel,pickedGeel);
        fillKleur(g,blauw,pickedBlauw);

        g.setFont(fontSubTekst);
        g.setColor(Color.BLACK);
        g.drawString("Rood", 190, 290);
        g.drawString("Geel", 390, 290);
        g.drawString("Blauw", 590, 290);
    }

    public void drawKleur(Graphics g, Color kleur) {


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
            aantalKleur = aantalRood;
        }
        if (kleur.equals(geel)) {
            aantalKleur = aantalGeel;
        }
        if (kleur.equals(blauw)) {
            aantalKleur = aantalBlauw;
        }

        for (int aantal = aantalKleur; aantal > 0; aantal--) {
            Product p = new Product();
            p.setKleur(kleur);
            g.setColor(p.getKleur());
            int x;
            int y = 0;
            int marge = 55;

            //x positie
            if (kleur.equals(rood)) {
                p.setPosX(xRood);
            }
            if (kleur.equals(geel)) {
                p.setPosX(xGeel);
            }
            if (kleur.equals(blauw)) {
                p.setPosX(xBlauw);
            }
            if (aantalKleur == 1 || aantalKleur == 3 || aantalKleur == 5) {
//                x = p.getPosX();
                aantalKleur--;
            }
            else if (aantalKleur == 2 || aantalKleur == 4 || aantalKleur == 6) {
                x = p.getPosX() + marge;
                p.setPosX(x);
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
            p.setPosY(y);
            producten.add(p);
        }
        for (Product p : producten) {
            p.drawKleinProduct(g, p);
        }
    }

    public void fillKleur(Graphics g, Color kleur, int pickedAantal){
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
        for(tePicken = aantalKleur; pickedAantal <= tePicken; tePicken--){
            for (Product p : producten) {
                if(p.getKleur().equals(kleur) && pickedAantal > 0) {
                    p.fillProduct(g, p);
                    pickedAantal--;
                }
            }
        }

    }

    @Override
    public void reload() {

    }
}
//Sylvia <