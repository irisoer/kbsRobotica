package GUI;

import Applicatie.ArduinoInpak;
import Applicatie.ArduinoSorteer;
import Applicatie.Database;
import Applicatie.Order;

import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class Frame extends JFrame implements Layout {
        private static JPanel jpSchermen;
        private static CardLayout cards;
        public static Order order = new Order();
        static OpstartScherm opstartScherm = new OpstartScherm();
        static int[] voorraad;
        static String huidigeKleur;
        static int huidigeDoos;
        static ArduinoInpak arduinoInpak;
        static ArduinoSorteer arduinoSorteer;

        static {
                voorraad = new int[]{Database.getVoorraad(73), Database.getVoorraad(71), Database.getVoorraad(60)};
        }

        public enum Schermen {
                OpstartScherm(opstartScherm),
                StartScherm(new StartScherm()),
                VerwerkScherm(new VerwerkScherm()),
                SorteerScherm(new SorteerScherm()),
                ErrorScherm(new ErrorScherm());
                public GUI.Scherm scherm;
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
                setResizable(false);
//                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setUndecorated(true);
                setVisible(true);

                cards = new CardLayout();
                jpSchermen = new JPanel(cards);
                setSize(800, 480);
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
                opstartScherm.runStatussen();
        }

        public static void setOrder(int[] rgbAantallen) {
                System.out.println(Arrays.toString(rgbAantallen));
                Frame.order = new Order(rgbAantallen);
        }

        public static void setScherm(Schermen scherm) {
                cards.show(jpSchermen, scherm.toString());
                scherm.scherm.repaint();
        }

        private void addSchermToCards(Schermen scherm, JPanel cardPanel) {
                cardPanel.add(scherm.scherm, scherm.scherm.getName());
        }

        public static int[] getVoorraad() {
                return voorraad;
        }

        public static void setVoorraad(int[] voorraad) {
                Frame.voorraad = voorraad;
        }

        public static Color getHuidigeKleur() {
                return switch (huidigeKleur) {
                        case "Red" -> Color.RED;
                        case "Yellow" -> Color.YELLOW;
                        case "Blue" -> Color.BLUE;
                        default -> null;
                };
        }

        public static void setHuidigeKleur(String huidigeKleur) {
                Frame.huidigeKleur = huidigeKleur;
        }

        public static int getHuidigeDoos() {
                return huidigeDoos;
        }

        public static void setHuidigeDoos(int huidigeDoos) {
                Frame.huidigeDoos = huidigeDoos;
        }
}
