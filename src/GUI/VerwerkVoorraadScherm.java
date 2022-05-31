package GUI;

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

    JPanel jpVoorraad;
    public VerwerkVoorraadScherm(){
        setLayout(new GridLayout(2,1));
        jpVoorraad = new JPanel(new GridLayout(3,1));
        jlRood = new JLabel();
        jlGeel = new JLabel();
        jlBlauw = new JLabel();
        reload();
    }

    @Override
    public void reload() {
        removeAll();

        aantalRood = Frame.order.aantalRood;
        aantalGeel = Frame.order.aantalGeel;
        aantalBlauw = Frame.order.aantalBlauw;
        jlRood.setText("Rode producten: " + aantalRood);
        jlGeel.setText("Gele producten: " + aantalGeel);
        jlBlauw.setText("Blauwe producten: " + aantalBlauw);

        jlRood.setFont(fontTekst);
        jlGeel.setFont(fontTekst);
        jlBlauw.setFont(fontTekst);

        jpVoorraad.add(jlRood);
        jpVoorraad.add(jlGeel);
        jpVoorraad.add(jlBlauw);

        jlRood.setHorizontalAlignment(JLabel.CENTER);
        jlGeel.setHorizontalAlignment(JLabel.CENTER);
        jlBlauw.setHorizontalAlignment(JLabel.CENTER);

        add(jpVoorraad);
        repaint();
    }
}
//Sylvia <