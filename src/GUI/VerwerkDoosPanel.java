package GUI;
import javax.swing.*;
import java.awt.*;

//Sylvia >
public class VerwerkDoosPanel extends JPanel {

    int hoogteDoos = 155;       //hoogte doos
    int breedteDoos = 110;      //breedte doos
    int productFormaat = 40;    //formaat product
    int yRij1 = 170;            //y positie producten rij 1
    int yRij2 = 120;            //y positie producten rij 2
    int yRij3 = 70;             //y positie producten rij 3
    int marge = 5;              //producten uitlijnen binnen doos
    int xDoos1 = 150;           //x positie doos 1
    int xDoos2 = 350;           //x positie doos 2
    int xDoos3 = 550;           //x positie doos 3
    int yDoos = 65;             //y positie van dozen

    public VerwerkDoosPanel(){
            setPreferredSize(new Dimension(800,350));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.WHITE);

        //pijl
        int grootte = 20;       //grootte van pijl
        int xHuidigeDoos = xDoos2; //todo: input
        g.setColor(Color.BLACK);
        Polygon pijl = new Polygon();
        pijl.addPoint(xHuidigeDoos+breedteDoos/2-grootte/2,30);     //linker punt
        pijl.addPoint(xHuidigeDoos+breedteDoos/2+grootte/2,30);     //rechter punt
        pijl.addPoint( xHuidigeDoos+breedteDoos/2,50);              //onderste punt
        g.fillPolygon(pijl);

        //dozen
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g.drawRect(xDoos1,yDoos,breedteDoos,hoogteDoos);     //doos1
        g.drawRect(xDoos2,yDoos,breedteDoos,hoogteDoos);     //doos2
        g.drawRect(xDoos3,yDoos,breedteDoos,hoogteDoos);     //doos3

        //producten
        g2.setStroke(new BasicStroke(1));
        drawOrder order = new drawOrder();
        order.drawProduct(g, Color.BLUE, 2,Color.RED,3,Color.GREEN,1);

//        g.setColor(Color.GREEN);                    //objecten maken van ovals?
//        g.drawOval(xDoos1+marge, yRij1, productFormaat,productFormaat);
//        g.drawOval(xDoos1+marge, yRij2, productFormaat,productFormaat);
//        g.drawOval(xDoos1+marge, yRij3, productFormaat,productFormaat);
//        g.drawOval(xDoos1+breedteDoos/2+marge, 70, productFormaat,productFormaat);   //tweede lijn
//        g.drawOval(xDoos1+breedteDoos/2+marge, 120, productFormaat,productFormaat);
//        g.drawOval(xDoos1+breedteDoos/2+marge, 170, productFormaat,productFormaat);

//        g.fillOval(xDoos1+marge,120,productFormaat,productFormaat);


//        g.setColor(Color.BLUE);
//        g.drawOval(xDoos2+marge, 70,productFormaat, productFormaat);
//        g.drawOval(xDoos2+marge, 120,productFormaat, productFormaat);
//        g.fillOval(xDoos2+marge, 120,productFormaat, productFormaat);
//
//        g.setColor(Color.RED);
//        g.drawOval(xDoos3+marge, 70,productFormaat, productFormaat);
//        g.drawOval(xDoos3+marge, 120,productFormaat, productFormaat);
//        g.fillOval(xDoos3, 120,productFormaat, productFormaat);

        setVisible(true);

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



}
//Sylvia <