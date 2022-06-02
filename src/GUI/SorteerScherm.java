package GUI;

import jssc.SerialPortException;
import Applicatie.*;

import java.awt.*;

//Sylvia >

public class SorteerScherm extends Scherm implements Layout, ArtikelStandaard {
	static int aantalRood = 0;
	static int aantalGeel = 0;
	static int aantalBlauw = 0;

	public SorteerScherm() {
		setLayout(new FlowLayout());

		//componenten
		jlHeading.setText("Uw voorraad wordt gesorteerd:");

		//toevoegen
		add(jlHeading);

		repaint();
	}

	public static void moduleData(char payload) {
		System.out.println(payload);
		if (payload == 's') {
			Frame.setScherm(Frame.Schermen.EindSchermSorteren);
			eindSorteren();
		}
		switch (payload) {
			case 'r' -> aantalRood++;
			case 'g' -> aantalGeel++;
			case 'b' -> aantalBlauw++;
			default -> {
			}
		}
		Frame.sorteerScherm.repaint();
	}

	public static void eindSorteren() {
		Arduino.MyPortListener.huidigeTaak = Arduino.MyPortListener.Taak.Geen;
		try {
			Frame.arduinoSorteer.getSerialPort().writeString("2:");
		} catch (SerialPortException e) {
		}
		Order.uploadVoorraadNaarDatabase();
		Frame.voorraad = new int[]{Frame.aantalRood, Frame.aantalGeel, Frame.aantalBlauw};
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//productenj
		Artikel roodProduct = new  Artikel();
		Artikel geelProduct = new  Artikel();
		Artikel blauwProduct = new Artikel();

		roodProduct.setKleur("Red");
		geelProduct.setKleur("Yellow");
		blauwProduct.setKleur("Blue");

		roodProduct.drawArtikel(g, roodProduct, 160, 110);
		geelProduct.drawArtikel(g, geelProduct, 360, 110);
		blauwProduct.drawArtikel(g, blauwProduct, 560, 110);

		//aantallen
		g.setColor(Color.BLACK);
		g.setFont(fontSubTekst);
		g.drawString(String.valueOf(aantalRood), 195, 225);         //aantal rood
		g.drawString(String.valueOf(aantalGeel), 395, 225);        //aantal geel
		g.drawString(String.valueOf(aantalBlauw), 595, 225);        //aantal blauw
	}

	public void startSorteren() {
		try {
			Frame.arduinoSorteer.getSerialPort().writeString("0:");
			Arduino.MyPortListener.huidigeStaat = Arduino.MyPortListener.Staat.WachtOpScan;
			Arduino.MyPortListener.huidigeTaak = Arduino.MyPortListener.Taak.Sorteer;
		} catch (Exception e) {
		}
	}
}

//<Sylvia