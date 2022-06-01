package GUI;

import javax.swing.*;
import java.awt.*;

public class EindschermSorteren extends Scherm implements Layout {
    private JLabel Titel;
    private JLabel jlEindBericht;
    private JButton jbTerugNaarBeginScherm;


    public EindschermSorteren() {
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        Titel = new JLabel("Uw voorraad is gesorteerd");
        this.Titel.setFont(fontHeading);
        this.Titel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(Titel);
        panel.add(new ProductSchower(), BorderLayout.CENTER);
        this.jlEindBericht = new JLabel("Druk op het scherm om een nieuwe opdracht te starten");
        this.jlEindBericht.setFont(fontTekst);
        this.jlEindBericht.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setBounds(0,0,800,480);
        panel.add(jlEindBericht);
        add(panel);
        jbTerugNaarBeginScherm = new JButton();
        jbTerugNaarBeginScherm.addActionListener(e -> Frame.setScherm(Frame.Schermen.StartScherm));
        jbTerugNaarBeginScherm.setBounds(0,0,800,480);
        jbTerugNaarBeginScherm.setOpaque(false);
        jbTerugNaarBeginScherm.setBorderPainted(false);
        jbTerugNaarBeginScherm.setFocusPainted(false);
        jbTerugNaarBeginScherm.setContentAreaFilled(false);
        add(jbTerugNaarBeginScherm);
    }

    private class ProductSchower extends Scherm {
        JLabel jlRood = new JLabel("Rood product (" + SorteerScherm.aantalRood + ")");
        JLabel jlGeel = new JLabel("Geel product (" + SorteerScherm.aantalGeel + ")");
        JLabel jlBlauw = new JLabel("Blauw product (" + SorteerScherm.aantalBlauw + ")");

        public ProductSchower(){
            jlRood.setFont(fontTekst);
            jlRood.setHorizontalAlignment(SwingConstants.CENTER);
            jlGeel.setFont(fontTekst);
            jlGeel.setHorizontalAlignment(SwingConstants.CENTER);
            jlBlauw.setFont(fontTekst);
            jlBlauw.setHorizontalAlignment(SwingConstants.CENTER);
            setLayout(new GridLayout(3,1));
            add(jlRood);
            add(jlGeel);
            add(jlBlauw);
        }

        @Override
        public void paintComponent(Graphics g) {
            System.out.println("TEKENEN");
            super.paintComponent(g);
            jlRood.setText("Rood product (" + SorteerScherm.aantalRood + ")");
            jlGeel.setText("Geel product (" + SorteerScherm.aantalGeel + ")");
            jlBlauw.setText("Blauw product (" + SorteerScherm.aantalBlauw + ")");
        }
    }
}