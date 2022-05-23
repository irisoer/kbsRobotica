package GUI;

import java.awt.*;

public class DoosOrder extends VerwerkDoos {
    public Product huidigProduct;
    public Color huidigeKleur = Color.RED;             //laatst gescande product voor order (geen restbak) todo: communication
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
        int x = 0; //x positie product
        int y = 0; //y positie product
        int aantal;
        int totaalAantal = aantalKleur1 + aantalKleur2 + aantalKleur3;

        switch (doos) {         //x positie doos
            case 1 -> doos = xDoos1;
            case 2 -> doos = xDoos2;
            case 3 -> doos = xDoos3;
        }

        for (aantal = totaalAantal; aantal > 0; aantal--) {
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

            //kleur
            if (aantalKleur1 > 0){
                g.setColor(kleur1);
                aantalKleur1--;
            }
            else if (aantalKleur1 == 0 && aantalKleur2 > 0) {
                g.setColor(kleur2);
                aantalKleur2--;
            }
            else if (aantalKleur1 == 0 && aantalKleur2 == 0 && aantalKleur3 > 0) {
                g.setColor(kleur3);
                aantalKleur3--;
            }
            this.huidigProduct = new Product();
            huidigProduct.drawOrderProduct(g, g.getColor(), x, y, doos);
        }
    }
}
