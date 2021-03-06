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
        public static int aantalRood;
        public static int aantalGeel;
        public static int aantalBlauw;
        static GraphicsDevice device = GraphicsEnvironment
                .getLocalGraphicsEnvironment().getScreenDevices()[0];
        private static JPanel jpSchermen;
        public static CardLayout cards;
        public static Order order = new Order();
        static OpstartScherm opstartScherm = new OpstartScherm();
        public static int[] voorraad;
        public static char huidigeKleur;
        public static int huidigeDoos = -1;
        public static ArduinoInpak arduinoInpak;
        public static ArduinoSorteer arduinoSorteer;
        private static String errorMessage;

        static SorteerScherm sorteerScherm = new SorteerScherm();
        static VerwerkScherm verwerkScherm = new VerwerkScherm();
        static StartScherm startScherm = new StartScherm(sorteerScherm, verwerkScherm);

        public enum Schermen {
                OpstartScherm(opstartScherm),
                StartScherm(startScherm),
                VerwerkScherm(verwerkScherm),
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

        private Schermen huidigScherm;
        public Frame(){
                super("GUI");
                setResizable(false);
                setUndecorated(true);
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

        public static void resetData() {
                order = null;
                updateVoorraad();
                huidigeDoos = -1;
                huidigeKleur = ' ';
                aantalBlauw = 0;
                aantalGeel = 0;
                aantalRood = 0;
                setScherm(Schermen.StartScherm);
        }
        public static int[] getVoorraad() {
                return voorraad;
        }

        public static void updateVoorraad() {
                voorraad = new int[]{Database.getVoorraad(73), Database.getVoorraad(71), Database.getVoorraad(60)};
        }

        public static void setVoorraad(int[] voorraad) {
                Frame.voorraad = voorraad;
        }

        public static Color getHuidigeKleur() {
                return switch (huidigeKleur) {
                        case 'r' -> Color.RED;
                        case 'g' -> Color.YELLOW;
                        case 'b' -> Color.BLUE;
                        default -> null;
                };
        }

        public static void setHuidigeKleur(char huidigeKleur) {
                Frame.huidigeKleur = huidigeKleur;
        }

        public static int getHuidigeDoos() {
                return huidigeDoos;
        }

        public static void setHuidigeDoos(int huidigeDoos) {
                Frame.huidigeDoos = huidigeDoos;
        }
}
