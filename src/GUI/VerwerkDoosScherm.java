package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Sylvia >
public class VerwerkDoosScherm extends VerwerkScherm implements ProductStandaard{
    private Product huidigProduct; //laatst gepickte product todo:
    private ArrayList<Product> producten;
    private Color huidigeKleur = rood;             //laatst gescande product voor order (geen restbak) todo:
    private int huidigeDoos = 2;

    private int pickedRood = 0;
    private int pickedGeel = 0 ;
    private int pickedBlauw = 0;

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
//        drawOrder(g,2, ProductStandaard.blauw, 2,ProductStandaard.rood,2,ProductStandaard.geel,1);
//        drawOrder(g,1,ProductStandaard.rood,3);
        drawOrder(g,2, 2,2,1);
        drawOrder(g,1,1,0,0);
    }

        public void setKleurHuidigProduct(Color kleur){
        this.huidigeKleur = kleur;
    }
    public Product getHuidigProduct(){
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
        //aantallen te tekenen/picken per kleur
        int tekenRood = aantalRood;                     //note to self: beter arraylist doorlopen??
        int tekenGeel = aantalGeel;
        int tekenBlauw = aantalBlauw;

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
            Product p = new Product();
            p.setDoos(doos);
            int x = 0; //x positie product
            int y = 0; //y positie product

            //x positie
            if (aantal == 1 || aantal == 3 || aantal == 5) {
                x = xDoos + marge;                                //kolom 1 in doos
            }
            if (aantal == 2 || aantal == 4 || aantal == 6) {
                x = xDoos + breedteDoos / 2 + marge;              //kolom 2 in doos
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
                g.setColor(rood);  //switched from kleur1, kleur2 etc
                p.setKleur(rood);
                aantalRood--;
            }
            else if (aantalRood == 0 && aantalGeel > 0) {
                g.setColor(geel);
                p.setKleur(geel);
                aantalGeel--;
            }
            else if (aantalRood == 0 && aantalGeel == 0 && aantalBlauw > 0) {
                g.setColor(blauw);
                p.setKleur(blauw);
                aantalBlauw--;
            }
            producten.add(p);
        }
        for (Product p : producten)
        {
            p.drawKleinProduct(g,p);
            if(true/*item picked*/ && huidigeKleur == rood){ //todo: remove hardcode
                System.out.println(aantalRood);

                fillOrder(g,p);
            }
        }
    }

    public void fillOrder(Graphics g,Product product){  //todo: kleurt alle producten van één kleur in doos in ipv 1.
        if(product.getKleur().equals(huidigeKleur) && product.getDoos() == huidigeDoos){
            product.fillProduct(g,product);
        }
    }
}
//Sylvia <