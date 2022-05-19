package GUI;

import java.awt.*;

public class drawOrder extends VerwerkDoosPanel {
//    private Color kleur1, kleur2, kleur3;
//    private int aantalKleur1, aantalKleur2, AantalKleur3;


    public void drawProduct(Graphics g, Color kleur1, int aantalKleur1) {
        this.drawProduct(g, kleur1, aantalKleur1, Color.WHITE, 0, Color.WHITE, 0);
    }

    public void drawProduct(Graphics g, Color kleur1, int aantalKleur1, Color kleur2, int aantalKleur2) {
        this.drawProduct(g, kleur1, aantalKleur1, kleur2, aantalKleur2, Color.WHITE, 0);
    }


    public void drawProduct(Graphics g, Color kleur1, int aantalKleur1, Color kleur2, int aantalKleur2, Color kleur3, int aantalKleur3) {
        int x = 0;
        int y = 0;
        int aantal;
        int totaalAantal = aantalKleur1 + aantalKleur2 + aantalKleur3;
//        g.setColor(kleur1);

        for (aantal = totaalAantal; aantal > 0; aantal--) {
            //x positie
            if (aantal == 1 || aantal == 3 || aantal == 5) {
                x = xDoos1 + marge;               //kolom 1 in doos
            }
            if (aantal == 2 || aantal == 4 || aantal == 6) {
                x = xDoos1 + breedteDoos / 2 + marge; //kolom 2 in doos
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
            g.drawOval(x, y, productFormaat, productFormaat);

        }


//    public void drawLeegProduct(Graphics g, Color kleur, int aantalProducten){
//        g.setColor(kleur);
//        int x = 0;
//        int y = 0;
//        int aantal;
//        for (aantal = aantalProducten; aantal >0; aantal--) {
//            //x positie
//            if (aantal== 1 || aantal== 3 || aantal == 5){
//                x = xDoos1+marge;               //kolom 1 in doos
//            }
//            if (aantal == 2 || aantal == 4 || aantal == 6){
//                x = xDoos1+breedteDoos/2+marge; //kolom 2 in doos
//            }
//            //y positie
//            if (aantal <=2 ){
//                y = yRij1;
//            }
//            if (aantal >2 && aantal <=4 ){
//                y = yRij2;
//            }
//            if (aantal >4 && aantal <= 6){
//                y = yRij3;
//            }
//            g.drawOval(x,y,productFormaat,productFormaat);
//        }
//    }
//
//    public void drawProduct(Graphics g, Color kleur1, int aantalKleur1, Color kleur2, int aantalKluer2, Color kleur3, int aantalKleur3, int totaalAantal){
//        g.setColor(kleur1);
//        int x = 0;
//        int y = 0;
//        int aantal;
//        for (aantal = totaalAantal; aantal >0; aantal--) {
//            //x positie
//            if (aantal== 1 || aantal== 3 || aantal == 5){
//                x = xDoos1+marge;               //kolom 1 in doos
//            }
//            if (aantal == 2 || aantal == 4 || aantal == 6){
//                x = xDoos1+breedteDoos/2+marge; //kolom 2 in doos
//            }
//            //y positie
//            if (aantal <=2 ){
//                y = yRij1;
//            }
//            if (aantal >2 && aantal <=4 ){
//                y = yRij2;
//            }
//            if (aantal >4 && aantal <= 6){
//                y = yRij3;
//            }
//            g.fillOval(x,y,productFormaat,productFormaat);
//        }
//    }
//
//    public void drawProduct(Graphics g, Color kleur1, int aantalKleur1, Color kleur2, int aantalKleur2) {
//        drawProduct(g, kleur1, aantalKleur1, Color.WHITE, 0, Color.WHITE, 0,6);
//    }
//
//    public void drawProduct(Graphics g, Color color, Color kleur2, Color kleur1, int aantalProducten){
//        drawProduct(g, kleur1, Color.WHITE, Color.WHITE, aantalProducten);
//    }

    }
}
