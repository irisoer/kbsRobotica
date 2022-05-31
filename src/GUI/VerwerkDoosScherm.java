package GUI;

import java.awt.*;
import java.util.ArrayList;

//Sylvia >
public class VerwerkDoosScherm extends VerwerkCarrouselScherm implements ArtikelStandaard {
    private Artikel huidigProduct; //laatst gepickte product todo:
    private ArrayList<Artikel> producten;
    private Color huidigeKleur = rood;             //laatst gescande product voor order (geen restbak) todo:
    private int huidigeDoos = 2;

    //Posities product in doos
    private int yRij1 = 270;            //y positie producten rij 1
    private int yRij2 = 220;            //y positie producten rij 2
    private int yRij3 = 170;            //y positie producten rij 3
    private int marge = 5;              //producten uitlijnen binnen doos

    //posities doos
    int xDoos1 = 150;           //x positie doos 1
    int xDoos2 = 350;           //x positie doos 2
    int xDoos3 = 550;           //x positie doos 3
    int yDoos = 165;            //y positie van dozen

    public VerwerkDoosScherm() {
//        add(jlHeading);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //pijl
        int grootte = 20;       //grootte van pijl
        int xHuidigeDoos = xDoos2; //todo: communication
        g.setColor(Color.BLACK);
        Polygon pijl = new Polygon();
        pijl.addPoint(xHuidigeDoos+breedteDoos/2-grootte/2,130);     //linker punt
        pijl.addPoint(xHuidigeDoos+breedteDoos/2+grootte/2,130);     //rechter punt
        pijl.addPoint( xHuidigeDoos+breedteDoos/2,150);              //onderste punt
        g.fillPolygon(pijl);

        //dozen
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.setFont(fontTekst);

        g.drawRect(xDoos1,yDoos,breedteDoos,hoogteDoos);     //doos1
        g.drawRect(xDoos2,yDoos,breedteDoos,hoogteDoos);     //doos2
        g.drawRect(xDoos3,yDoos,breedteDoos,hoogteDoos);     //doos3

        g.drawString("Rood", xDoos1+breedteDoos/4,yDoos+hoogteDoos+marge*5);
        g.drawString("Geel",xDoos2+breedteDoos/4,yDoos+hoogteDoos+marge*5);
        g.drawString("Blauw",xDoos3+breedteDoos/4,yDoos+hoogteDoos+marge*5);


        //producten        // todo: hardcode > communication
//        drawOrder(g,2, ArtikelStandaard.blauw, 2,ArtikelStandaard.rood,2,ArtikelStandaard.geel,1);
//        drawOrder(g,1,ArtikelStandaard.rood,3);
        drawOrder(g,2, 2,2,1);
        drawOrder(g,1,1,0,0);
    }

    public void setKleurHuidigProduct(Color kleur){
        this.huidigeKleur = kleur;
    }
    public Artikel getHuidigProduct(){
        return this.huidigProduct;
    }

    public int getHuidigeDoos() {
        return huidigeDoos;
    }

    public Color getKleurHuidigProduct() {
        return huidigeKleur;
    }

/*    public void drawOrder(Graphics g, int doos, Color kleur1, int aantalKleur1) {
        this.drawOrder(g, doos, kleur1, aantalKleur1, Color.WHITE, 0, Color.WHITE, 0);
    }

    public void drawOrder(Graphics g, int doos, Color kleur1, int aantalKleur1, Color kleur2, int aantalKleur2) {
        this.drawOrder(g, doos, kleur1, aantalKleur1, kleur2, aantalKleur2, Color.WHITE, 0);
    }*/

    public void drawOrder(Graphics g, int doos, int aantalRood, int aantalGeel, int aantalBlauw
            /*Graphics g, int doos, Color kleur1, int aantalKleur1, Color kleur2, int aantalKleur2, Color kleur3, int aantalKleur3*/) {
        int aantal;
        int totaalAantal = aantalRood + aantalGeel + aantalBlauw;

        producten = new ArrayList<>();
        int xDoos = 0; //xPositie huidige doos

        //x positie doos
        if (doos == 1) {
            xDoos = xDoos1;
        } if (doos == 2) {
            xDoos = xDoos2;
        } if (doos == 3) {
            xDoos = xDoos3;
        }

        for (aantal = totaalAantal; aantal > 0; aantal--) {
            Artikel p = new Artikel();
            p.setDoos(doos);
            int x = 0; //x positie product
            int y = 0; //y positie product

            //x positie
            if (aantal %2 != 0) {
                x = xDoos + marge;                          //kolom 1 in doos (oneven aantallen)
            }
            else {
                x = xDoos + breedteDoos / 2 + marge;       //kolom 2 in doos (even aantallen)
            }

            //y positie
            if (aantal <= 2) {
                y = yRij1;
            }
            if (aantal > 2 && aantal <= 4) {
                y = yRij2;
            }
            if (aantal > 4 && aantal <= 6) {
                y = yRij3;
            }
            p.setPositie(x,y);

            //kleur
            if (aantalRood > 0){
                p.setKleur(rood);
                aantalRood--;
            }
            else if (aantalRood == 0 && aantalGeel > 0) {
                p.setKleur(geel);
                aantalGeel--;
            }
            else if (aantalRood == 0 && aantalGeel == 0 && aantalBlauw > 0) {
                p.setKleur(blauw);
                aantalBlauw--;
            }
            g.setColor(p.getKleur());
            producten.add(p);
        }
        for (Artikel p : producten)
        {
            p.drawKleinArtikel(g,p);
            if(p.gevuld){ //todo: remove hardcode
                p.fillArtikel(g,p);
            }
        }
    }
}
//Sylvia <