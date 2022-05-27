package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//Sylvia >
public class VerwerkDoosScherm extends VerwerkScherm implements ProductStandaard{
    ArrayList<Product> producten;
//    Product huidigProduct; //laatst gepickte product
    //Posities product in doos
    int yRij1 = 270;            //y positie producten rij 1
    int yRij2 = 220;            //y positie producten rij 2
    int yRij3 = 170;            //y positie producten rij 3
    int marge = 5;              //producten uitlijnen binnen doos
    //posities doos
    int xDoos1 = 150;           //x positie doos 1
    int xDoos2 = 350;           //x positie doos 2
    int xDoos3 = 550;           //x positie doos 3
    int yDoos = 165;            //y positie van dozen

    private JPanel jpVerwerkDoos = new JPanel();        //teken panel

    public VerwerkDoosScherm() {
        super();
        add(jlHeading);
        add(jpVerwerkDoos);
        setVisible(true);
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
        drawOrder(g,2, ProductStandaard.blauw, 2,ProductStandaard.rood,2,ProductStandaard.geel,1);
        drawOrder(g,1,ProductStandaard.rood,3);
    }

    Product huidigProduct;                               //todo: weghalen na testen
    public Color huidigeKleur = rood;             //laatst gescande product voor order (geen restbak) todo: communication
    int huidigeDoos = 2;

    //    public void setKleurHuidigProduct(Color kleur){
//        this.huidigeKleur = kleur;
//    }
    public Product getHuidigProduct(){
        return this.huidigProduct;
    }

    public int getHuidigeDoos() {
        return huidigeDoos;
    }
    public Color getKleurHuidigProduct() {
        return huidigeKleur;
    }

    public void drawOrder(Graphics g, int doos, Color kleur1, int aantalKleur1) {
        this.drawOrder(g, doos, kleur1, aantalKleur1, Color.WHITE, 0, Color.WHITE, 0);
    }

    public void drawOrder(Graphics g, int doos, Color kleur1, int aantalKleur1, Color kleur2, int aantalKleur2) {
        this.drawOrder(g, doos, kleur1, aantalKleur1, kleur2, aantalKleur2, Color.WHITE, 0);
    }

    public void drawOrder(Graphics g, int doos, Color kleur1, int aantalKleur1, Color kleur2, int aantalKleur2, Color kleur3, int aantalKleur3) {
        int aantal;
        int totaalAantal = aantalKleur1 + aantalKleur2 + aantalKleur3;
        producten = new ArrayList<Product>();

        switch (doos) {         //x positie doos
            case 1 -> doos = xDoos1;
            case 2 -> doos = xDoos2;
            case 3 -> doos = xDoos3;
        }

        for (aantal = totaalAantal; aantal > 0; aantal--) {
            Product p = new Product();
            int x = 0; //x positie product
            int y = 0; //y positie product
            Color kleur = Color.WHITE;

            //x positie
            if (aantal == 1 || aantal == 3 || aantal == 5) {
                x = doos + marge;                   //kolom 1 in doos
            }
            if (aantal == 2 || aantal == 4 || aantal == 6) {
                x = doos + breedteDoos / 2 + marge; //kolom 2 in doos
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
            if (aantalKleur1 > 0){
                g.setColor(kleur1);
                p.setKleur(kleur1);
                aantalKleur1--;
            }
            else if (aantalKleur1 == 0 && aantalKleur2 > 0) {
                g.setColor(kleur2);
                p.setKleur(kleur2);
                aantalKleur2--;
            }
            else if (aantalKleur1 == 0 && aantalKleur2 == 0 && aantalKleur3 > 0) {
                g.setColor(kleur3);
                p.setKleur(kleur3);
                aantalKleur3--;
            }
            producten.add(p);
        }
        for (Product p : producten)
        {
            p.drawOrderProduct(g,p.getKleur(),p.getPosX(),p.getPosY(),doos);
        }
    }

    public void fillOrder(Product product){


    }
}
//Sylvia <