package GUI;

import Applicatie.Order;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

//Sylvia >
public class VerwerkScherm extends Scherm {
    private CardLayout cards;
    private JPanel jpSchermen;
    private Carrousel huidigScherm;
    private int index = 9999; // zodat als je naar links gaat niet negatief wordt en het % 3 nog 0 is

    public enum Carrousel {
        VerwerkVoorraadScherm(new VerwerkVoorraadScherm()),
        VerwerkKleurScherm(new VerwerkKleurScherm()),
        VerwerkDoosScherm(new VerwerkDoosScherm());

        public GUI.VerwerkCarrouselScherm scherm;

        Carrousel(GUI.VerwerkCarrouselScherm scherm) {
            this.scherm = scherm;
        }

        @Override
        public String toString() {
            return this.scherm.getName();
        }
    }



    public VerwerkScherm() {
        setLayout(null);
        cards = new CardLayout();
        jpSchermen = new JPanel(cards);
        huidigScherm = Carrousel.values()[0];

        for (Carrousel scherm: Carrousel.values()) {
            System.out.println(scherm);
            addSchermToCards(scherm, jpSchermen);
        }

        JButton button = new JButton();

        button.addActionListener(e -> {
            cards.previous(jpSchermen);
            index--;
            repaint();
        });
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBounds(0, 0, 250, 480);

        add(button);

        button = new JButton();
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(e -> {
            cards.next(jpSchermen);
            index++;
            repaint();
        });
        button.setBounds(550, 0, 250, 480);
        add(button);

        jpSchermen.setBounds(0, 50, 800, 350);
//        jpSchermen.setBorder(new LineBorder(Color.BLACK, 5));

        add(jpSchermen);

        JLabel jlTitel = new JLabel();
        jlTitel.setText("Uw order wordt verwerkt:");
        jlTitel.setFont(fontHeading);
        jlTitel.setHorizontalAlignment(SwingConstants.CENTER);
        jlTitel.setBounds(0, 0, 800, 50);
        add(jlTitel);
//
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //status
        int width = getWidth();
        int middle = width / 2;
        int circle = 25;
        int circleMargin = circle + 15;
        int circleY = getHeight() - circle*2;
        g.setColor(Color.BLUE);
        for (int i = 0; i < Carrousel.values().length; i++) {
            int circleX = middle + ((i - 1) * circleMargin);
            if(index % Carrousel.values().length == i) g.fillOval(circleX,circleY,circle,circle);
            else g.drawOval(circleX, circleY,circle,circle);
        }
    }

    public void setScherm(Carrousel scherm) {
        cards.show(jpSchermen, scherm.toString());
        this.huidigScherm = scherm;
    }

    public void reload() {
        for (Carrousel carrousel: Carrousel.values()) {
            carrousel.scherm.reload();
        }
    }

    private void addSchermToCards(Carrousel scherm, JPanel cardPanel) {
        cardPanel.add(scherm.scherm, scherm.scherm.getName());
    }

    public static void startInpakken() {
        try {
            Frame.arduinoSorteer.getKleur(Order.getBpp());

        } catch (Exception e ) {

        }
    }
}