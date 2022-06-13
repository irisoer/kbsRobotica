package GUI;

import Applicatie.Artikel;
import Applicatie.Order;

import java.awt.*;
import java.util.ArrayList;

//Sylvia >
public class VerwerkDoosScherm extends VerwerkCarrouselScherm implements ArtikelStandaard {
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

    }

    public void reload() {
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //pijl
        int grootte = 20;       //grootte van pijl
        int xHuidigeDoos = 160 + (Frame.huidigeDoos == -1 ? -99 : Frame.huidigeDoos) * 200; //geen pijl als doos nog niet geselecteerd is
        g.setColor(Color.BLACK);
        Polygon pijl = new Polygon();
        pijl.addPoint(xHuidigeDoos+breedteDoos/2-grootte/2,80);     //linker punt
        pijl.addPoint(xHuidigeDoos+breedteDoos/2+grootte/2,80);     //rechter punt
        pijl.addPoint( xHuidigeDoos+breedteDoos/2,100);             //onderste punt
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


        //producten
        ArrayList<ArrayList<Artikel>> bppbins = Order.getBpp().getBins();
        for (int i = 0; i < bppbins.size(); i++) {
            drawOrder(g,i + 1, bppbins.get(i));
        }
    }

    public void drawOrder(Graphics g, int doos, ArrayList<Artikel> doosInhoud) {

        int xDoos = 0; //xPositie huidige doos

        //x positie doos v tekenen
        if (doos == 1) {
            xDoos = xDoos1;
        } if (doos == 2) {
            xDoos = xDoos2;
        } if (doos == 3) {
            xDoos = xDoos3;
        }
        int aantal = 1;                                     //1 want produict 0 bestaat niet in order
        for (Artikel a : doosInhoud)
        {
            int x;              //x positie product
            int y = 0;          //y positie product
            if (aantal %2 != 0) {
                x = xDoos + marge;                          //kolom 1 in doos (oneven aantallen)
            }
            else {
                x = xDoos + breedteDoos / 2 + marge;       //kolom 2 in doos (even aantallen)
            }
            if (aantal <= 2) {
                y = yRij1;
            }
            else if (aantal <= 4) {
                y = yRij2;
            }
            else if (aantal <= 6) {
                y = yRij3;
            }
            a.setPositie(x, y);
            g.setColor(a.getJavaKleur());
            a.drawKleinArtikel(g);
            if(a.isIngepakt()){
                a.fillArtikel(g);
            }
            aantal++;
        }
    }
}
//Sylvia <