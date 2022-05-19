package GUI;

import javax.swing.*;
import java.awt.*;

//Sylvia >

public class SorteerScherm extends Scherm{
    private JLabel jlTitel;
    private SorteerPanel panel;
    private JPanel jpAantallen;

    public SorteerScherm() {
        super("Sorteren");
        setLayout (new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //componenten aanmaken      todo: variabelen voor aantallen
        jlTitel = new JLabel("Uw voorraad wordt gesorteerd");
        jlTitel.setFont(fontHeading);
        panel = new SorteerPanel();
        jpAantallen = new JPanel();
        jpAantallen.setLayout(new GridLayout(0,3));

        //toevoegen
        add(jlTitel);
        jlTitel.setHorizontalAlignment(JLabel.CENTER);
        jlTitel.setFont(fontHeading);
        add(panel);

        setVisible(true);

    }

}

//<Sylvia