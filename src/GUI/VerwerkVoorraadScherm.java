package GUI;

import Applicatie.Database;

import javax.swing.*;
import java.awt.*;

//Sylvia >
public class VerwerkVoorraadScherm extends VerwerkCarrouselScherm {
    private JLabel jlRood;
    private JLabel jlGeel;
    private JLabel jlBlauw;
    private JLabel jlHuidigeVoorraad;
    private JLabel jlRoodAantal;
    private JLabel jlGeelAantal;
    private JLabel jlBlauwAantal;
    private JLabel jlLeeg;

    JPanel jpVoorraad;

    public VerwerkVoorraadScherm(){
        setLayout(new GridLayout(2,1));
        jpVoorraad = new JPanel(new GridLayout(4,2));
        jpVoorraad.setBounds(300,0,800,480);
        jlHuidigeVoorraad = new JLabel();
        jlRood = new JLabel();
        jlGeel = new JLabel();
        jlBlauw = new JLabel();
        jlRoodAantal = new JLabel();
        jlGeelAantal = new JLabel();
        jlBlauwAantal = new JLabel();
        jlLeeg = new JLabel(" ");
        reload();
    }

    @Override
    public void reload() {
        removeAll();
        jlHuidigeVoorraad.setText("Huidige voorraad");
        jlRood.setText("Rode producten: ");
        Frame.updateVoorraad();
        jlRoodAantal.setText(Integer.toString((Frame.voorraad[0]-Frame.aantalRood)));
        jlGeel.setText("Gele producten: ");
        jlGeelAantal.setText(Integer.toString((Frame.voorraad[1]-Frame.aantalGeel)));
        jlBlauw.setText("Blauwe producten: ");
        jlBlauwAantal.setText(Integer.toString(Frame.voorraad[2]-Frame.aantalBlauw));

        jlHuidigeVoorraad.setFont(fontTekst);
        jlRood.setFont(fontTekst);
        jlGeel.setFont(fontTekst);
        jlBlauw.setFont(fontTekst);
        jlRoodAantal.setFont(fontTekst);
        jlGeelAantal.setFont(fontTekst);
        jlBlauwAantal.setFont(fontTekst);

        jpVoorraad.add(jlHuidigeVoorraad);
        jpVoorraad.add(jlLeeg);
        jpVoorraad.add(jlRood);
        jpVoorraad.add(jlRoodAantal);
        jpVoorraad.add(jlGeel);
        jpVoorraad.add(jlGeelAantal);
        jpVoorraad.add(jlBlauw);
        jpVoorraad.add(jlBlauwAantal);

        jlHuidigeVoorraad.setHorizontalAlignment(JLabel.CENTER);
        jlRood.setHorizontalAlignment(JLabel.LEFT);
        jlGeel.setHorizontalAlignment(JLabel.LEFT);
        jlBlauw.setHorizontalAlignment(JLabel.LEFT);

        add(jpVoorraad);
        repaint();
    }
}
//Sylvia <