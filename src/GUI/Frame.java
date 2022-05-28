package GUI;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame implements Layout {
        JPanel JPschermen;
        CardLayout cards;
        Schermen huidigScherm;


        public enum Schermen {
                StartScherm(new StartScherm()),
                VerwerkScherm(new VerwerkScherm());
                private GUI.Scherm scherm;
                Schermen(GUI.Scherm scherm) {
                        this.scherm = scherm;
                }

                @Override
                public String toString() {
                        return this.scherm.getName();
                }
        }
        public Frame(){
                super("GUI");
                cards = new CardLayout();
                JPschermen = new JPanel(cards);
                setSize(800, 480);
                setResizable(false);

                setLayout(null);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setPreferredSize(new Dimension(800, 480));
                setMaximumSize(new Dimension(800, 480));
                setMinimumSize(new Dimension(800, 480));
                setLayout(new CardLayout());
                setDefaultCloseOperation(EXIT_ON_CLOSE);

                for (Schermen scherm: Schermen.values()) {
                        addSchermToCards(scherm, JPschermen);
                }


                add(JPschermen);
                setScherm(Schermen.StartScherm);
                setResizable(false);
                setVisible(true);
        }

        public void setScherm(Schermen scherm) {
                cards.show(JPschermen, scherm.toString());
                huidigScherm = scherm;
        }

        private void addSchermToCards(Schermen scherm, JPanel cardPanel) {
                cardPanel.add(scherm.scherm, scherm.scherm.getName());
        }
}
