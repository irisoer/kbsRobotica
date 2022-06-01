package GUI;

import Applicatie.Artikel;
import Applicatie.Order;

import java.awt.*;
import java.util.ArrayList;

//Sylvia >
public class VerwerkDoosScherm extends VerwerkCarrouselScherm implements ArtikelStandaard {
    private Product huidigProduct; //laatst gepickte product todo:
    private ArrayList<Artikel> artikelen;

    //Posities product in doos
    private int yRij1 = 220;            //y positie producten rij 1
    private int yRij2 = 170;            //y positie producten rij 2
    private int yRij3 = 120;            //y positie producten rij 3
    private int marge = 5;              //producten uitlijnen binnen doos

    //posities doos
    int xDoos1 = 160;           //x positie doos 1
    int xDoos2 = 360;           //x positie doos 2
    int xDoos3 = 560;           //x positie doos 3
    int yDoos = 115;            //y positie van dozen

    public VerwerkDoosScherm() {
//        add(jlHeading);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //pijl
        int grootte = 20;       //grootte van pijl
        int xHuidigeDoos = 160 + Frame.huidigeDoos * 200; //todo: communication
        g.setColor(Color.BLACK);
        Polygon pijl = new Polygon();
        pijl.addPoint(xHuidigeDoos+breedteDoos/2-grootte/2,80);     //linker punt
        pijl.addPoint(xHuidigeDoos+breedteDoos/2+grootte/2,80);     //rechter punt
        pijl.addPoint( xHuidigeDoos+breedteDoos/2,100);              //onderste punt
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
        ArrayList<ArrayList<Artikel>> bppbins = Order.getBpp().getBins();
        for (int i = 0; i < bppbins.size(); i++) {
            drawOrder(g,i + 1, bppbins.get(i));
        }
    }

    public Product getHuidigProduct(){
        return this.huidigProduct;
    }

/*    public void drawOrder(Graphics g, int doos, Color kleur1, int aantalKleur1) {
        this.drawOrder(g, doos, kleur1, aantalKleur1, Color.WHITE, 0, Color.WHITE, 0);
    }

    public void drawOrder(Graphics g, int doos, Color kleur1, int aantalKleur1, Color kleur2, int aantalKleur2) {
        this.drawOrder(g, doos, kleur1, aantalKleur1, kleur2, aantalKleur2, Color.WHITE, 0);
    }*/

    public void drawOrder(Graphics g, int doos, ArrayList<Artikel> doosInhoud
//                          int aantalRood, int aantalGeel, int aantalBlauw
            /*Graphics g, int doos, Color kleur1, int aantalKleur1, Color kleur2, int aantalKleur2, Color kleur3, int aantalKleur3*/) {

        int xDoos = 0; //xPositie huidige doos

        //x positie doos
        if (doos == 1) {
            xDoos = xDoos1;
        } if (doos == 2) {
            xDoos = xDoos2;
        } if (doos == 3) {
            xDoos = xDoos3;
        }
        int num = 1;
        for (Artikel a : doosInhoud)
        {
            int x; //x positie product
            int y = 0; //y positie product
            if (num %2 != 0) {
                x = xDoos + marge;                          //kolom 1 in doos (oneven aantallen)
            }
            else {
                x = xDoos + breedteDoos / 2 + marge;       //kolom 2 in doos (even aantallen)
            }
            if (num <= 2) {
                y = yRij1;
            }
            else if (num <= 4) {
                y = yRij2;
            }
            else if (num <= 6) {
                y = yRij3;
            }
            a.setPositie(x, y);
            g.setColor(a.getJavaKleur());
            a.drawKleinArtikel(g);
            if(a.isIngepakt()){ //todo: remove hardcode
                a.fillArtikel(g);
            }
            num++;
        }
    }
}
//Sylvia <