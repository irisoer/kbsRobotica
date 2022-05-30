package GUI;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame implements Layout {
        private static JPanel jpSchermen;
        private static CardLayout cards;

        static OpstartScherm opstartScherm = new OpstartScherm();


        public enum Schermen {
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
                setVisible(true);
                cards = new CardLayout();
                jpSchermen = new JPanel(cards);
                setSize(800, 480);
//                setResizable(false);
//                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setUndecorated(true);

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
                setScherm(Schermen.OpstartScherm);
                setResizable(false);
                opstartScherm.runStatussen();

        }

        public static void setScherm(Schermen scherm) {
                cards.show(jpSchermen, scherm.toString());
                this.huidigScherm = scherm;
        }

        private void addSchermToCards(Schermen scherm, JPanel cardPanel) {
                cardPanel.add(scherm.scherm, scherm.scherm.getName());
        }
}
