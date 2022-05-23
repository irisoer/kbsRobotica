package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >
public class VerwerkDoos extends VerwerkScherm implements Layout, ProductStandaard {
    private JPanel jpVerwerkDoos = new JPanel();

    public VerwerkDoos() {
        super();
        setLayout(new FlowLayout());
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
        DoosOrder order = new DoosOrder();
        order.drawOrder(g,2, Color.BLUE, 2,Color.RED,2,Color.ORANGE,1); // todo: hardcode > communication
        order.drawOrder(g,1,Color.RED,3);
//        if(true) {       //picked product
//
//        }

        setVisible(true);

    }


}
//Sylvia <