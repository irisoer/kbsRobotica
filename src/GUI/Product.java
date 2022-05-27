package GUI;

import java.awt.*;

public class Product implements ProductStandaard {
    private Color kleur;
    private int posX;
    private int posY;
    int doos;

    public Product(){

    }

    public Product(Color kleur, int x, int y){ //todo: wel nodig?

    }

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

    public void drawProduct(Graphics g, Color kleur, int posX, int posY) {  //gebruik bij sorteren
        this.kleur = kleur;
        this.posX = posX;
        this.posY = posY;
        g.setColor(kleur);
        g.fillOval(posX, posY, productFormaat, productFormaat);
    }

    public void drawOrderProduct(Graphics g, Color kleur, int posX, int posY, int doos) { //gebruik bij verwerken order
        int productFormaatOrder = productFormaat/2; //kleinere items in dozen da de standaard
        this.kleur = kleur;
        this.posX = posX;
        this.posY = posY;
        this.doos = doos;      //waarde van doos verdwijnt ergens, hardcode werkt, aanroepen functie niet
        g.setColor(kleur);
        g.drawOval(posX, posY, productFormaatOrder, productFormaatOrder);
    }

//    public void fillProduct(Graphics g, Color kleur, int doos) {        //ook te gebruiken voor sorteren?
//        doos = 2; //todo hardcode weghalen
//        int productFormaatOrder = productFormaat/2; //kleinere items in dozen da de standaard
//
//
////        if (kleur.equals(huidigeKleur) && doos == /*huidigeDoos*/ 2) {
////            g.fillOval(posX, posY, productFormaatOrder, productFormaatOrder);
////            }
//    }
}
