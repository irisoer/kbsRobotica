package GUI;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame implements Layout {
        private JPanel jpSchermen;
        private CardLayout cards;



        public enum Schermen {
                OpstartScherm(new OpstartScherm()),
                StartScherm(new StartScherm()),
                VerwerkScherm(new VerwerkScherm()),
                ErrorScherm(new ErrorScherm());
                private GUI.Scherm scherm;
                Schermen(GUI.Scherm scherm) {
                        this.scherm = scherm;
                }

                @Override
                public String toString() {
                        return this.scherm.getName();
                }
        }

        private Schermen huidigScherm;
        public Frame(){
                super("GUI");
                cards = new CardLayout();
                jpSchermen = new JPanel(cards);
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
                        addSchermToCards(scherm, jpSchermen);
                }

                add(jpSchermen);
                setScherm(Schermen.StartScherm);
                setResizable(false);
                setVisible(true);
        }

        public void setScherm(Schermen scherm) {
                cards.show(jpSchermen, scherm.toString());
                this.huidigScherm = scherm;
        }

        private void addSchermToCards(Schermen scherm, JPanel cardPanel) {
                cardPanel.add(scherm.scherm, scherm.scherm.getName());
        }
}
