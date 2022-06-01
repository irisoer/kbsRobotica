package GUI;

import javax.swing.*;
import java.awt.*;

public class EindschermSorteren extends Scherm implements Layout {
    private JLabel Titel;
    private JLabel Eindbericht;
    private JButton jbTerugNaarBeginScherm;


    public EindschermSorteren() {
        setLayout(null);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        Titel = new JLabel("Uw voorraad is gesorteerd");
        this.Titel.setFont(fontTitel);
        this.Titel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(Titel);
        panel.add(new ProductSchower(), BorderLayout.CENTER);
        Eindbericht = new JLabel("Druk op het scherm om een nieuw order in te vullen");
        this.Eindbericht.setFont(fontTekst);
        this.Eindbericht.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setBounds(0,0,800,480);
        panel.add(Eindbericht);
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

        public ProductSchower(){
            setLayout(new GridLayout(3,1));
            add(new  ProductRegel("Rood",7));
            add(new ProductRegel("Geel",5));
            add(new ProductRegel("Blauw",3));
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
}