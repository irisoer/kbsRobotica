package GUI;

import Applicatie.Database;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class EindschermSorteren extends Scherm implements Layout {
    private JLabel Titel;
    private JLabel Eindbericht;

    private String test3;

    public EindschermSorteren() {
        setLayout(new GridLayout(3, 1));
        Titel = new JLabel("Uw voorraad is gesorteerd");
        this.Titel.setFont(fontHeading);
        this.Titel.setHorizontalAlignment(SwingConstants.CENTER);
        add(Titel);
        add(new ProductSchower(), BorderLayout.CENTER);
        Eindbericht = new JLabel("Druk op het scherm om een nieuw order in te vullen");
        this.Eindbericht.setFont(fontTekst);
        this.Eindbericht.setHorizontalAlignment(SwingConstants.CENTER);
        add(Eindbericht);

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