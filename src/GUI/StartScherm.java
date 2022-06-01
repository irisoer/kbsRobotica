package GUI;

import Applicatie.Database;
import Applicatie.Order;
import JPanels.Panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class StartScherm extends Scherm {
	JLabel titel;

	private JButton jbSorteer;
	private JButton jbBevestig;
	public ProductSelector productSelector;

	public StartScherm(SorteerScherm sorteerScherm) {

		setLayout(null);
		this.titel = new JLabel("Wat moet er in de order komen");
		this.titel.setFont(fontHeading);
		this.titel.setHorizontalAlignment(SwingConstants.CENTER);
		this.titel.setBounds(0, 0, 800, 50);
		try {
			add(titel);
			productSelector = new ProductSelector();
			add(new DBLoader(productSelector));
			add(productSelector);
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
		}
		jbSorteer = new JButton("Sorteermodule");
		jbSorteer.addActionListener(e -> {
			Frame.setScherm(Frame.Schermen.SorteerScherm);
			sorteerScherm.startSorteren();
		});
		jbSorteer.setBounds(100, 415, 200, 50);
		jbSorteer.setFont(fontSubTekst);
		add(jbSorteer);
		jbBevestig = new JButton("Bevestig");
		jbBevestig.addActionListener(e -> {
			Frame.setScherm(Frame.Schermen.VerwerkScherm);
			Frame.setOrder(productSelector.getProductAantallen());
			for (VerwerkScherm.Carrousel carrousel: VerwerkScherm.Carrousel.values()){
				carrousel.scherm.reload();
			}
			VerwerkScherm.startInpakken();
		});
		jbBevestig.setBounds(500, 415, 200, 50);
		jbBevestig.setFont(fontSubTekst);
		add(jbBevestig);

		//todo: Exporten van data uit dit scherm naar een BPP algoritme
	}


	static class DBLoader extends Panel {
		private JLabel jlOrderSelecteren;

		JComboBox<Integer> box = new JComboBox<>(Database.selecteerOrderNums());
		JButton jbLoad = new JButton("Inladen");

		public DBLoader(ProductSelector productSelector) {
			setLayout(null);
			jlOrderSelecteren = new JLabel("Selecteer order: ");
			jlOrderSelecteren.setBounds(0,0,200,100);
			jlOrderSelecteren.setFont(fontSubTekst);
			add(jlOrderSelecteren);
			box.setBounds(150, 0, 100, 100);
			box.setFont(fontTekst);
			add(box);
			jbLoad.setFont(fontTekst);
			jbLoad.setBounds(300, 0, 200, 100);
			jbLoad.addActionListener(e -> {
				int[] databaseItems;
				if(this.box.getSelectedItem() == null) databaseItems = new int[]{0, 0, 0};
				else databaseItems = Database.selecteerOrder((Integer) this.box.getSelectedItem());
				productSelector.setProductAantallen(databaseItems);
			});
			add(jbLoad);
			setBounds(175, 50, 800, 100);
		}

	}


	private class ProductSelector extends Scherm {
        static ProductRegel[] productRegels;
        {
                productRegels = new ProductRegel[]{
                    new ProductRegel("Rood", Database.getVoorraad(73)),
                    new ProductRegel("Geel", Database.getVoorraad(71)),
                    new ProductRegel("Blauw", Database.getVoorraad(60))
                };
        }

        public ProductSelector() throws SQLException {
			setLayout(new GridLayout(3, 1));
            for (ProductRegel productRegel: productRegels){
                add(productRegel);
            }
			setBorder(new EmptyBorder(0, 30, 0, 30));
			setBounds(0, 150, 800, 250);
		}

        public void setProductAantallen(int[] rgbAantallen) {
            for (int i = 0; i < rgbAantallen.length; i++) {
                productRegels[i].setAantal(rgbAantallen[i]);
            }
        }

		public int[] getProductAantallen() {
			int[] rgbAantallen = new int[3];
			for (int i = 0; i < productRegels.length; i++) {
				rgbAantallen[i] = productRegels[i].aantal();
			}
			return rgbAantallen;
		}

		private class ProductRegel extends Scherm {
			protected int voorraad;
			private JLabel Product;
			private PlusMinKnop plusMinKnop;

			public ProductRegel(String kleur, int voorraad) {
				this.voorraad = voorraad;
				this.Product = new JLabel(kleur + " Product (" + voorraad + ")");
				this.Product.setFont(fontTekst);
				setSize(800, 250);
				setLayout(new GridLayout(1, 2));
				add(Product);
				this.plusMinKnop = new PlusMinKnop(0, voorraad);
				add(plusMinKnop);
			}

			public int aantal() {
				return this.plusMinKnop.value();
			}

            public void setAantal(int aantal) {
				this.plusMinKnop.setValue(aantal);
            }
		}

		private class PlusMinKnop extends Scherm implements ActionListener {
			private int aantal;
			private JLabel JLaantal;
			private JButton plus = new JButton("+");
			private JButton min = new JButton("-");
			private int maxVoorraad;

			public PlusMinKnop(int aantal, int maxVoorraad) {
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

            public void setValue(int aantal) {
                if (this.aantal + aantal >= 0 && this.aantal + aantal <= maxVoorraad) {
                    this.aantal = aantal;
                    JLaantal.setText(String.valueOf(aantal));
                } else {
					System.out.println("JA");
				}
                // todo: Throw error naar errorscherm
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




