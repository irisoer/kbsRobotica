package JPanels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScherm extends Panel implements ActionListener {
	JButton jbSorteer = new JButton("Start sorteermodule");
	JButton jbInpak = new JButton("Start inpakken");
	DBLoader dbLoader = new DBLoader();
	JPanel rgbSelectie = rgbSelectie(new int[]{10, 5, 7});

	public StartScherm() {
		setLayout(null);
		jlHeading.setText("Wat moet er in de order komen?");
		jlHeading.setBounds(0,0, 800, 50);
		add(jlHeading);
		add(dbLoader);
		add(rgbSelectie);
		jbSorteer.setBounds(100, 475, 250, 75);
		jbSorteer.addActionListener(this);
		jbSorteer.setFont(fontSubTekst);
		add(jbSorteer);
		jbInpak.setBounds(450, 475, 250, 75);
		jbInpak.addActionListener(this);
		jbInpak.setFont(fontSubTekst);
		add(jbInpak);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	private JPanel rgbSelectie(int[] aantalRGB) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3,2, 0, 10));
		PlusMin[] plusMins = new PlusMin[3];
		JLabel[] jLabels = new JLabel[3];
		String[] labelTekst = getLabels(aantalRGB);
		panel.setBorder(new EmptyBorder(10, 100, 10, 50));
		for (int i = 0; i < 3; i++) {
			jLabels[i] = new JLabel(labelTekst[i]);
			jLabels[i].setFont(fontTekst);
			plusMins[i] = new PlusMin(0, aantalRGB[i]);
			panel.add(jLabels[i]);
			panel.add(plusMins[i]);
		}
		panel.setBounds(0, 150,  800, 300);
		return panel;
	}

	private String[] getLabels(int[] rgb) {
		String[] labels = new String[3];
		labels[0] = "Rood Product (" + String.valueOf(rgb[0]) + ")";
		labels[1] = "Groen Product (" + String.valueOf(rgb[1]) + ")";
		labels[2] = "Blauw Product (" + String.valueOf(rgb[2]) + ")";
		return labels;
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
			// todo: Order nummer inladen en toepassen
		}
	}

	class PlusMin extends Panel implements ActionListener {
		private JButton jbPlus = new JButton("+");
		private JButton jbMin = new JButton("-");
		private JLabel aantal;
		private int max;
		private int huidig;
		private Font aantalFont = new Font("Calibri", Font.PLAIN, 35);
		private Font buttonFont = new Font("Calibri", Font.BOLD, 40);


		public PlusMin(int startGetal, int maxGetal) {
			this.huidig = startGetal;
			this.max = maxGetal;
			setLayout(null);
			aantal = new JLabel(String.valueOf(huidig));
			aantal.setBounds(100, 0, 100, 100);
			aantal.setHorizontalAlignment(SwingConstants.CENTER);
			aantal.setFont(aantalFont);
			jbMin.setFont(buttonFont);
			jbMin.setHorizontalAlignment(SwingConstants.CENTER);
			jbMin.setBounds(0, 5, 90, 90);
			jbMin.addActionListener(this);
			jbPlus.setHorizontalAlignment(SwingConstants.CENTER);
			jbPlus.setBounds(200, 5, 90, 90);
			jbPlus.setFont(buttonFont);
			jbPlus.addActionListener(this);
			add(jbMin);
			add(aantal);
			add(jbPlus);
		}

		public int getAantal() {
			return Integer.parseInt(this.aantal.getText());
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(jbPlus)) {
				plus(aantal, 1);
			} else if(e.getSource().equals(jbMin)) {
				plus(aantal, -1);
			}
			updateUI();
		}

		private void plus(JLabel label, int aantal) {
			if(huidig + aantal <= max && huidig + aantal >= 0) {
				label.setText(String.valueOf(huidig += aantal));
			}
		}


	}
}
