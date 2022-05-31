package GUI;

import Applicatie.Database;

import javax.swing.*;
import java.awt.*;

//Sylvia >
public class VerwerkVoorraadScherm extends VerwerkCarrouselScherm {

    private int aantalRood;
    private int aantalGeel;
    private int aantalBlauw;

    private JLabel jlRood;
    private JLabel jlGeel;
    private JLabel jlBlauw;
    private JLabel jlHuidigeVoorraad;
    JLabel jlLeeg = new JLabel();       //nodig voor uitlijning

    JPanel jpVoorraad;

    public VerwerkVoorraadScherm(){
        setLayout(new GridLayout(3,1));
        jpVoorraad = new JPanel(new GridLayout(4,1));
        jlHuidigeVoorraad = new JLabel();
        jlRood = new JLabel();
        jlGeel = new JLabel();
        jlBlauw = new JLabel();
        reload();
    }

    @Override
    public void reload() {
        removeAll();
        jlHuidigeVoorraad.setText("Huidige voorraad: ");
        aantalRood = Frame.order.aantalRood;
        aantalGeel = Frame.order.aantalGeel;
        aantalBlauw = Frame.order.aantalBlauw;
        jlRood.setText("Rode producten: " + Database.getVoorraad(73));
        jlGeel.setText("Gele producten: " + Database.getVoorraad(71));
        jlBlauw.setText("Blauwe producten: " + Database.getVoorraad(60));

        jlHuidigeVoorraad.setFont(fontTekst);
        jlRood.setFont(fontTekst);
        jlGeel.setFont(fontTekst);
        jlBlauw.setFont(fontTekst);

        jpVoorraad.add(jlHuidigeVoorraad);
        jpVoorraad.add(jlRood);
        jpVoorraad.add(jlGeel);
        jpVoorraad.add(jlBlauw);

        jlHuidigeVoorraad.setHorizontalAlignment(JLabel.CENTER);
        jlRood.setHorizontalAlignment(JLabel.CENTER);
        jlGeel.setHorizontalAlignment(JLabel.CENTER);
        jlBlauw.setHorizontalAlignment(JLabel.CENTER);

        add(jlLeeg);
        add(jpVoorraad);
        repaint();
    }
}
//Sylvia <