package GUI;

import Applicatie.Database;
import JPanels.Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class StartScherm extends Scherm {
    JLabel titel;

    private JButton jbSorteer;
    private JButton jbBevestig;

    public StartScherm() {

        setLayout(null);
        this.titel = new JLabel("Wat moet er in de order komen");
        this.titel.setFont(fontHeading);
        this.titel.setHorizontalAlignment(SwingConstants.CENTER);
        this.titel.setBounds(0, 0, 800, 50);
        try {
            add(titel);
            add(new DBLoader());
            add(new productSelector());
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
        }
        jbSorteer = new JButton("Sorteermodule");
        jbSorteer.addActionListener(e -> Frame.setScherm(Frame.Schermen.ErrorScherm));
        jbSorteer.setBounds(100, 415, 200, 50);
        jbSorteer.setFont(fontSubTekst);
        add(jbSorteer);
        jbBevestig = new JButton("Bevestig");
        jbBevestig.addActionListener(e -> Frame.setScherm(Frame.Schermen.VerwerkScherm));
        jbBevestig.setBounds(500, 415, 200, 50);
        jbBevestig.setFont(fontSubTekst);
        add(jbBevestig);

        //todo: Exporten van data uit dit scherm naar een BPP algoritme
    }


    class DBLoader extends Panel {
        JComboBox<Integer> box = new JComboBox<>(getOrderNums());
        JButton jbLoad = new JButton("Laden");

        public DBLoader() {
            setLayout(null);
            box.setBounds(0, 0, 100, 100);
            box.setFont(fontTekst);
            add(box);
            jbLoad.setFont(fontTekst);
            jbLoad.setBounds(150, 0, 100, 100);
            jbLoad.addActionListener(e -> {
                System.out.println(this.box.getSelectedItem());
            });
            add(jbLoad);
            setBounds(275, 50, 250, 100);
        }

        private Integer[] getOrderNums() {
            // todo: OrderNums ophalen uit de database
            return new Integer[]{null, 12, 14, 24};
        }
    }


        private class productSelector extends Scherm {

            public productSelector() throws SQLException {
                setLayout(new GridLayout(3, 1));
                add(new productRegel("Rood", Database.getVoorraad(73)));
                add(new productRegel("Geel", Database.getVoorraad(71)));
                add(new productRegel("Blauw", Database.getVoorraad(60)));
                setBorder(new EmptyBorder(0, 30, 0, 30));
                setBounds(0, 150, 800, 250);
            }

            private class productRegel extends Scherm {
                private JLabel Product;
                private String kleur;
                protected int voorraad;

                public productRegel(String kleur, int voorraad) {
                    this.kleur = kleur;
                    this.voorraad = voorraad;
                    this.Product = new JLabel(kleur + " Product (" + voorraad + ")");
                    this.Product.setFont(fontTekst);
                    setSize(800, 250);
                    setLayout(new GridLayout(1, 2));
                    add(Product);
                    add(new plusMinKnop(0, voorraad));


                }
            }

            private class plusMinKnop extends Scherm implements ActionListener {
                private int aantal;
                private JLabel JLaantal;
                private JButton plus = new JButton("+");
                private JButton min = new JButton("-");
                private int maxVoorraad;

                public plusMinKnop(int aantal, int maxVoorraad) {
                    this.aantal = aantal;
                    this.JLaantal = new JLabel(String.valueOf(aantal));
                    this.JLaantal.setFont(fontTekst);
                    this.maxVoorraad = maxVoorraad;
                    setSize(150, 50);
                    setLayout(new GridLayout(1, 3));
                    min.addActionListener(this);
                    add(min);
                    JLaantal.setHorizontalAlignment(SwingConstants.CENTER);
                    add(JLaantal);
                    plus.addActionListener(this);
                    add(plus);


                }

                public int value() {
                    return aantal;
                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource().equals(min)) {
                        if (aantal - 1 >= 0) {
                            aantal = aantal - 1;
                            JLaantal.setText(String.valueOf(aantal));
                        }
                    }
                    if (e.getSource().equals(plus)) {
                        if (aantal + 1 <= maxVoorraad) {
                            aantal = aantal + 1;
                            JLaantal.setText(String.valueOf(aantal));
                        }
                    }
                }
            }


        }
    }




