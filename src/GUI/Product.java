package GUI;

import java.awt.*;

public class Product extends DoosOrder implements ProductStandaard {
    private Color kleur;
    private int posX;
    private int posY;
    int doos;

    public Color getKleur() {
        return kleur;
    }

    public int getDoos() {
        return doos;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }

    public void setDoos(int doos) {
        this.doos = doos;
    }

    public void setPositie(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void drawProduct(Graphics g, Color kleur, int posX, int posY) { //
        this.kleur = kleur;
        this.posX = posX;
        this.posY = posY;
        g.setColor(kleur);
        g.fillOval(posX, posY, productFormaat, productFormaat);
    }

    public void drawOrderProduct(Graphics g, Color kleur, int posX, int posY, int doos) { //
        int productFormaatOrder = productFormaat/2; //kleinere items in dozen da de standaard
        this.kleur = kleur;
        this.posX = posX;
        this.posY = posY;
        this.doos = doos;      //waarde van doos verdwijnt ergens, hardcode werkt, aanroepen functie niet
        g.setColor(kleur);
        g.drawOval(posX, posY, productFormaatOrder, productFormaatOrder);

        fillProduct(g, kleur, doos); //int doos = 2 verdwijnt

//        if(huidigeKleur.equals(kleur) && huidigeDoos == 2) {   werktish
//            fillProduct(g,kleur,2);
//        }
//
//        if(true) {       //todo: communication
//            fillProduct(g,kleur,doos);
//        }
//
    }

    public void fillProduct(Graphics g, Color kleur, int doos) {        //ook te gebruiken voor sorteren?
        doos = 2;
        int productFormaatOrder = productFormaat/2; //kleinere items in dozen da de standaard
        if (kleur.equals(huidigeKleur) && doos == huidigeDoos) {
            g.fillOval(posX, posY, productFormaatOrder, productFormaatOrder);
            }
    }
}
