package GUI;

import JPanels.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartScherm extends Scherm {
    JLabel titel;

    public StartScherm() {
        setLayout(new GridLayout(4, 1));
        this.titel = new JLabel("Wat moet er in de order komen");
        this.titel.setFont(fontHeading);
        this.titel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titel);
        add(new DBLoader());
        add(new productSelector());
    }


    class DBLoader extends Panel implements ActionListener {
        JComboBox<Integer> box = new JComboBox<>(getOrderNums());
        JButton jbLoad = new JButton("Laden");

        public DBLoader() {
            setLayout(null);
            box.setBounds(0, 0, 100, 100);
            box.setFont(fontTekst);
            add(box);
            jbLoad.setFont(fontTekst);
            jbLoad.setBounds(150, 0, 100, 100);
            jbLoad.addActionListener(this);
            add(jbLoad);
            setBounds(275, 50, 250, 100);
        }

        private Integer[] getOrderNums() {
            // todo: OrderNums ophalen uit de database
            return new Integer[]{null, 12, 14, 24};
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


        private class productSelector extends Scherm {

            public productSelector() {
                setLayout(new GridLayout(3, 1));
                add(new productRegel("Rood", 5));
                add(new productRegel("Geel", 3));
                add(new productRegel("Blauw", 2));
            }

            private class productRegel extends Scherm {
                private JLabel Product;
                private String kleur;
                protected int voorraad;

                public productRegel(String kleur, int voorraad) {
                    this.kleur = kleur;
                    this.voorraad = voorraad;
                    this.Product = new JLabel(kleur + " Product (" + voorraad + ")");
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




