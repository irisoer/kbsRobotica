package GUI;

import java.awt.*;

public class Product implements ProductStandaard {
    private Color kleur;
    private int posX;
    private int posY;
    int doos;       //IDnr doos, gebruik voor vergelijken bij inkleuren producten

    public Product(){

    }

    public Product(Color kleur){
        this.kleur = kleur;
    }

    public Product(Color kleur, int x, int y){ //todo: wel nodig?
        this.kleur = kleur;
        this.posX = x;
        this.posY = y;
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

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void drawProduct(Graphics g, Product product) {  //gebruik bij sorteren
        g.setColor(product.getKleur());
        g.fillOval(product.getPosX(), product.getPosY(), grootProduct, grootProduct);
    }
    public void drawProduct(Graphics g, Product product, int x , int y) {  //gebruik bij sorteren
        product.setPositie(x,y);
        product.drawProduct(g,product);
    }

        public void drawKleinProduct(Graphics g, Product product) { //gebruik bij verwerken order
        g.setColor(product.getKleur());
        g.drawOval(product.getPosX(), product.getPosY(), kleinProduct, kleinProduct);
    }

    public void fillProduct(Graphics g, Product product) {        //invullen van gepickte items (zijn altijd klein)
        g.setColor(product.getKleur());
        g.fillOval(product.getPosX(), product.getPosY(), kleinProduct, kleinProduct);
    }
}
