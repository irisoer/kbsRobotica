package GUI;

import Applicatie.ArduinoInpak;
import Applicatie.ArduinoSorteer;
import Applicatie.Database;
import Applicatie.Order;

import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.*;

public class Frame extends JFrame implements Layout {
        static GraphicsDevice device = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getScreenDevices()[0];
        private static JPanel jpSchermen;
        private static CardLayout cards;
        public static Order order = new Order();
        static OpstartScherm opstartScherm = new OpstartScherm();
        static int[] voorraad;
        static String huidigeKleur;
        static int huidigeDoos;
        public static ArduinoInpak arduinoInpak;
        public static ArduinoSorteer arduinoSorteer;
        private static String errorMessage;

        static SorteerScherm sorteerScherm = new SorteerScherm();
        static StartScherm startScherm = new StartScherm(sorteerScherm);

        public enum Schermen {
                OpstartScherm(opstartScherm),
                StartScherm(startScherm),
                VerwerkScherm(new VerwerkScherm()),
                SorteerScherm(new SorteerScherm()),
                ErrorScherm(new ErrorScherm()),
                SorteerSchemr(sorteerScherm),
                EindSchermSorteren(new EindschermSorteren()),
                EindSchermOrderKlaarmaken(new EindschermOrderKlaarmaken());
                public GUI.Scherm scherm;
                Schermen(GUI.Scherm scherm) {
                        this.scherm = scherm;
                }

                @Override
                public String toString() {
                        return this.scherm.getName();
                }
        }

        private Schermen huidigScherm;          //todo: veel in deze klasse wordt (nog) niet gebruikt
        public Frame(){
                super("GUI");
                setResizable(false);
//                setExtendedState(JFrame.MAXIMIZED_BOTH);
                setUndecorated(true);
                device.setFullScreenWindow(this);
                setSize(800, 480);
                cards = new CardLayout();
                setLayout(cards);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setVisible(true);
                setPreferredSize(new Dimension(800, 480));
                setMaximumSize(new Dimension(800, 480));
                setMinimumSize(new Dimension(800, 480));

                jpSchermen = new JPanel(cards);
                for (Schermen scherm: Schermen.values()) {
                        addSchermToCards(scherm, jpSchermen);
                }
                add(jpSchermen);

                pack();
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

        public static void showError(String errorMessage) {
                Frame.errorMessage = errorMessage;
                setScherm(Schermen.ErrorScherm);
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
