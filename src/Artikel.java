import java.sql.ResultSet;
import java.sql.SQLException;

public class Artikel {
	private int gewicht;
	private int id;
	private String kleur;
	private String naam;

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
		return "Artikel{" +
				"gewicht=" + gewicht +
				", id=" + id +
				", kleur='" + kleur + '\'' +
				", naam='" + naam + '\'' +
				'}';
	}
}
