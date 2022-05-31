package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class VerwerkVoorraadScherm extends VerwerkCarrouselScherm {

    private int aantalRood = Frame.order.aantalRood;
    private int aantalGeel = Frame.order.aantalGeel;
    private int aantalBlauw = Frame.order.aantalBlauw;

    private JLabel jlRood = new JLabel("Rode producten: " + aantalRood);
    private JLabel jlGroen = new JLabel("Gele producten: " + aantalGeel);
    private JLabel jlBlauw = new JLabel("Blauwe producten: " + aantalBlauw);

    private Color gescandeKleur;

    public VerwerkVoorraadScherm(){
        setLayout(new GridLayout(2,1));
//        setPreferredSize(new Dimension(800,100));

        JPanel jpVoorraad = new JPanel();
//        jpVoorraad.setMinimumSize(new Dimension(700,480));
        jpVoorraad.setLayout(new GridLayout(3,1));

        jlRood.setFont(fontTekst);
        jlGroen.setFont(fontTekst);
        jlBlauw.setFont(fontTekst);

//        add(jlHeading);

       jpVoorraad.add(jlRood);
       jpVoorraad.add(jlGroen);
       jpVoorraad.add(jlBlauw);

       jlRood.setHorizontalAlignment(JLabel.CENTER);
       jlGroen.setHorizontalAlignment(JLabel.CENTER);
       jlBlauw.setHorizontalAlignment(JLabel.CENTER);

       add(jpVoorraad);

    }

    @Override
    public void reload() {
        removeAll();
        aantalRood = Frame.order.aantalRood;
        aantalGeel = Frame.order.aantalGeel;
        aantalBlauw = Frame.order.aantalBlauw;
        JLabel jlRood = new JLabel("Rode producten: " + aantalRood);
        JLabel jlGroen = new JLabel("Gele producten: " + aantalGeel);
        JLabel jlBlauw = new JLabel("Blauwe producten: " + aantalBlauw);

        JPanel jpVoorraad = new JPanel();
//        jpVoorraad.setMinimumSize(new Dimension(700,480));
        jpVoorraad.setLayout(new GridLayout(3,1));

        jlRood.setFont(fontTekst);
        jlGroen.setFont(fontTekst);
        jlBlauw.setFont(fontTekst);

//        add(jlHeading);

        jpVoorraad.add(jlRood);
        jpVoorraad.add(jlGroen);
        jpVoorraad.add(jlBlauw);

        jlRood.setHorizontalAlignment(JLabel.CENTER);
        jlGroen.setHorizontalAlignment(JLabel.CENTER);
        jlBlauw.setHorizontalAlignment(JLabel.CENTER);

        add(jpVoorraad);
    }
}
//Sylvia <