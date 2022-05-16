package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia

public class SorteerScherm extends Scherm{
    private JLabel jlTitel;
    private SorteerPanel panel;
    private JLabel jlGroenAantal;
    private JLabel jlBlauwAantal;
    private JLabel jlRoodAantal;
    private JPanel jpAantallen;

    public SorteerScherm() {
        super("Sorteren");
        setLayout (new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //componenten aanmaken      todo: variabelen voor aantallen
        jlTitel = new JLabel("Uw voorraad wordt gesorteerd");
        jlTitel.setFont(fontHeading);
        panel = new SorteerPanel();
        jlGroenAantal = new JLabel("Groen                                   ");
        jlBlauwAantal = new JLabel("Blauw                                   ");
        jlRoodAantal = new JLabel("Rood");
        jpAantallen = new JPanel();
        jpAantallen.setLayout(new GridLayout(0,3));


        //toevoegen
        add(jlTitel);
        jlTitel.setHorizontalAlignment(JLabel.CENTER);
        jlTitel.setFont(fontHeading);
        add(panel);

        //voorraad aantallen todo: goed uitlijnen en pijltje toevoegen
        jpAantallen.add(jlGroenAantal);
        jpAantallen.add(jlBlauwAantal);
        jpAantallen.add(jlRoodAantal);
        add(jpAantallen);

        //setComponentOrientation(ComponentOrientation.);

        setVisible(true);
    }

}
