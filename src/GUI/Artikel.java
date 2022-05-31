package GUI;

import java.awt.*;

public class Artikel implements ArtikelStandaard {
    private Color kleur;
    private int posX;
    private int posY;
    int doos;       //IDnr doos, gebruik voor vergelijken bij inkleuren producten
    boolean gevuld = true;

    public Artikel(){

    }

    public Artikel(Color kleur){
        this.kleur = kleur;
    }

    public Artikel(Color kleur, int x, int y){
        this.kleur = kleur;
        this.posX = x;
        this.posY = y;
    }

    public Color getKleur() {
        return kleur;
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

    public void drawArtikel(Graphics g, Artikel artikel) {  //gebruik bij sorteren
        g.setColor(this.kleur);
        g.fillOval(this.posX, this.posY, grootArtikel, grootArtikel);
    }
    public void drawArtikel(Graphics g, Artikel artikel, int x , int y) {  //gebruik bij sorteren
        artikel.setPositie(x,y);
        artikel.drawArtikel(g,artikel);
    }

        public void drawKleinArtikel(Graphics g, Artikel artikel) { //gebruik bij verwerken order
        g.setColor(this.kleur);
        g.drawOval(this.posX, this.posY, kleinArtikel, kleinArtikel);
    }

    public void fillArtikel(Graphics g, Artikel artikel) {        //invullen van gepickte items (zijn altijd klein)
        g.setColor(this.kleur);
        g.fillOval(this.posX, this.posY, kleinArtikel, kleinArtikel);
    }
}