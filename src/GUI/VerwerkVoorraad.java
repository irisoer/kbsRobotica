package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class VerwerkVoorraad extends VerwerkScherm{

    private int roodAantal = 0;
    private int geelAantal = 0;
    private int blauwAantal = 0;
    private Color gescandeKleur;

    public VerwerkVoorraad(){
        setLayout(new GridLayout(2,1));
//        setPreferredSize(new Dimension(800,100));
        jlHeading.setText("Huidige voorraad:");

        JLabel jlRood = new JLabel("Rode producten: " + roodAantal);
        JLabel jlGroen = new JLabel("Gele producten: " + geelAantal);
        JLabel jlBlauw = new JLabel("BLauwe producten: " + blauwAantal);

        JPanel jpVoorraad = new JPanel();
        jpVoorraad.setMinimumSize(new Dimension(800,400));
        jpVoorraad.setLayout(new GridLayout(3,1));


        while (true/*sorteermmodule aan*/ && gescandeKleur != null) { //todo: true statement

            if (gescandeKleur.equals(Color.RED)){
                roodAantal++;
            }
            if(gescandeKleur.equals((Color.ORANGE))) {
                geelAantal++;
            }
            if(gescandeKleur.equals(Color.BLUE)) {
                blauwAantal++;
            }
        }

        jlRood.setFont(fontTekst);
        jlGroen.setFont(fontTekst);
        jlBlauw.setFont(fontTekst);

       jpVoorraad.add(jlRood);
       jpVoorraad.add(jlGroen);
       jpVoorraad.add(jlBlauw);

       jlRood.setHorizontalAlignment(JLabel.CENTER);
       jlGroen.setHorizontalAlignment(JLabel.CENTER);
       jlBlauw.setHorizontalAlignment(JLabel.CENTER);

       add(jpVoorraad);

       setVisible(true);
    }
}
//Sylvia <