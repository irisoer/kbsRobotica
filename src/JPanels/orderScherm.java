package JPanels;

import javax.lang.model.element.PackageElement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class orderScherm extends JPanel {

    public orderScherm() {
        add()
        add(new productSelector());
    }


        private class productSelector extends JPanel {

            public productSelector() {
                setLayout(new GridLayout(3,1));
                add(new productRegel("Rood",5));
                add(new productRegel("Geel",3));
                add(new productRegel("Blauw",2));
            }

            private class productRegel extends JPanel{
                private JLabel Product;
                private String kleur;
                protected int voorraad;

                public productRegel(String kleur, int voorraad){
                    this.kleur = kleur;
                    this.voorraad = voorraad;
                    this.Product = new JLabel(kleur +" Product (" + voorraad +")");
                    setSize(800,250);
                    setLayout(new GridLayout(1,2));
                    add(Product);
                    add(new plusMinKnop(0,voorraad));



                }
            }
            private class plusMinKnop extends JPanel implements ActionListener {
                private int aantal;
                private JLabel JLaantal;
                private JButton plus = new JButton("+");
                private JButton min = new JButton("-");
                private int maxVoorraad;

                public plusMinKnop(int aantal ,int maxVoorraad) {
                    this.aantal = aantal;
                    this.JLaantal = new JLabel(String.valueOf(aantal));
                    this.maxVoorraad = maxVoorraad;
                    setSize(150, 50);
                    setLayout(new GridLayout(1,3));
                    min.addActionListener(this);
                    add(min);
                    JLaantal.setHorizontalAlignment(SwingConstants.CENTER);
                    add(JLaantal);
                    plus.addActionListener(this);
                    add(plus);


                }

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource().equals(min)){
                        if (aantal - 1 >= 0){
                            aantal = aantal - 1;
                            JLaantal.setText(String.valueOf(aantal));
                        }
                    }
                    if (e.getSource().equals(plus)){
                        if (aantal + 1 <= maxVoorraad ){
                            aantal = aantal + 1;
                            JLaantal.setText(String.valueOf(aantal));
                        }
                    }
                }
            }


        }
    }


