package Applicatie;

import GUI.Product;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Artikel implements Comparable<Artikel> {
	int hoogteDoos = 155;       //hoogte doos
	int breedteDoos = 110;      //breedte doos
	int grootArtikel = 80;    //Groot formaat, voor sorteren
	int kleinArtikel = grootArtikel /2;      //Klein formaat voor verwerk/order schermen
	Color rood = Color.RED;     //todo: kleuren verwerken in statements in subklasses
	Color geel = Color.ORANGE;
	Color blauw = Color.BLUE;
	private int gewicht;
	private int id;
	private String kleur;
	private String naam;
	private boolean ingepakt = false;
	private int doos;

	private int posX;
	private int posY;

	public boolean isIngepakt() {
		return ingepakt;
	}

	public void setIngepakt() {
		this.ingepakt = true;
	}

	public Artikel(ResultSet set) throws SQLException {
// ColorName, StockItemID, StockItemName, TypicalWeightPerUnit
		this.gewicht = set.getInt("TypicalWeightPerUnit");
		this.id = set.getInt("StockItemID");
		this.kleur = set.getString("ColorName");
		this.naam = set.getString("StockItemName");
	}

	public int getGewicht() {
		return gewicht;
	}

	public int getId() {
		return id;
	}

	public String getKleur() {
		return kleur;
	}

	public Color getJavaKleur() {
		return switch (kleur) {
			case "Red" -> Color.RED;
			case "Yellow" -> Color.YELLOW;
			case "Blue" -> Color.BLUE;
			default -> null;
		};
	}

	public String getNaam() {
		return naam;
	}

	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setKleur(String kleur) {
		this.kleur = kleur;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Override
	public String toString() {
		return "Applicatie.Product{" +
				"gewicht=" + gewicht +
				", id=" + id +
				", kleur='" + kleur + '\'' +
				", naam='" + naam + '\'' +
				", doos='" + doos + '\'' +
				'}';
	}


	public int getDoos() {
		return doos;
	}

	public void setDoos(int doos) {
		this.doos = doos + 1;
	}

	@Override
	public int compareTo(Artikel compareArtikel) {
		int compareGewicht = compareArtikel.getGewicht();

		// For Descending order do like this
		 return this.gewicht-compareGewicht;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Artikel)) return false;
		Artikel artikel = (Artikel) o;
		return id == artikel.id
				&& (ingepakt == artikel.ingepakt);
	}

//	public Product(Color kleur){
//		this.kleur = kleur;
//	}
//
//	public Product(Color kleur, int x, int y){
//		this.kleur = kleur;
//		this.posX = x;
//		this.posY = y;
//	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPositie(int x, int y) {
		this.posX = x;
		this.posY = y;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void drawArtikel(Graphics g) {  //gebruik bij sorteren
		g.setColor(this.getJavaKleur());
		g.fillOval(this.posX, this.posY, grootArtikel, grootArtikel);
	}
	public void drawArtikel(Graphics g, Artikel artikel, int x , int y) {  //gebruik bij sorteren
		artikel.setPositie(x,y);
		artikel.drawArtikel(g);
	}

	public void drawKleinArtikel(Graphics g) { //gebruik bij verwerken order
		g.setColor(this.getJavaKleur());
		g.drawOval(this.posX, this.posY, kleinArtikel, kleinArtikel);
	}

	public void fillArtikel(Graphics g) {        //invullen van gepickte items (zijn altijd klein)
		g.setColor(this.getJavaKleur());
		g.fillOval(this.posX, this.posY, kleinArtikel, kleinArtikel);
	}
}
