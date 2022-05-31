package GUI;

import Applicatie.Order;

import javax.swing.*;
import java.awt.*;

public class EindschermOrderKlaarmaken extends Scherm implements Layout {
    private JLabel jlTitel;
    private JLabel jlEindbericht;
    private JButton jbTerugNaarBeginScherm;


    public EindschermOrderKlaarmaken() {
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        jlTitel = new JLabel("Uw Order is klaargemaakt");
        this.jlTitel.setFont(fontHeading);
        this.jlTitel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(jlTitel);

        panel.add(new ProductSchower(), BorderLayout.CENTER);
        jlEindbericht = new JLabel("Druk op het scherm om een nieuw order in te vullen");
        this.jlEindbericht.setFont(fontTekst);
        this.jlEindbericht.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setBounds(0,0,800,480);
        panel.add(jlEindbericht);
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

        public ProductSchower() {
            setLayout(new GridLayout(3, 1));
            add(new ProductRegel("Rood", Order.aantalRood));
            add(new ProductRegel("Geel", Order.aantalGeel));
            add(new ProductRegel("Blauw", Order.aantalBlauw));
        }

        public class ProductRegel extends Scherm {
            protected int voorraad;
            private JLabel Product;

            public ProductRegel(String kleur, int voorraad) {
                this.voorraad = voorraad;
                this.Product = new JLabel(kleur + " Product (" + voorraad + ")");
                this.Product.setFont(fontTekst);
                setSize(800, 250);
                setLayout(new GridLayout(1, 2));
                add(Product);
                this.Product.setFont(fontTekst);
                this.Product.setHorizontalAlignment(SwingConstants.CENTER);

            }
        }
    }

    public static void runEindProces() {
        Order.maakPakbon();
        Order.uploadOrderNaarDatabase();
        Order.uploadVoorraadNaarDatabase();
    }
}