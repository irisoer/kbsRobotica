package GUI;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame implements Layout{
        JPanel JPschermen;
        public StartScherm startScherm = new StartScherm();
        CardLayout cards;
        Schermen huidigScherm;


        public enum Schermen {
                START,
                CAROUSSEL,
                EIND
        }
        public Frame(){
                super("GUI");
                cards = new CardLayout();
                JPschermen = new JPanel(cards);
                setSize(800, 480);
                setLayout(cards);
                setDefaultCloseOperation(EXIT_ON_CLOSE);


                setPreferredSize(new Dimension(800, 480));
                setMaximumSize(new Dimension(800, 480));
                setMinimumSize(new Dimension(800, 480));
                setLayout(new CardLayout());
                setDefaultCloseOperation(EXIT_ON_CLOSE);

                JPschermen.add(startScherm, String.valueOf(Schermen.START));
                JPschermen.add(new VerwerkScherm(), String.valueOf(Schermen.CAROUSSEL));
                JPschermen.add(new Panel(), String.valueOf(Schermen.EIND));


                add(startScherm);
                setScherm(Schermen.START);
                setResizable(false);
                setVisible(true);
        }

        public void setScherm(Schermen scherm) {
                cards.show(JPschermen, String.valueOf(scherm));
                huidigScherm = scherm;
        }
}
