import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


/**
 * <h3>Bin Packing Problem class</h3>
 *
 * Deze class voert het Best fit decreasing algoritme uit op de meegegeven items.
 *
 * @author Krijn Grimme
 */
public class Bpp {
	private ArrayList<ArrayList<Artikel>> bins;

	public boolean isLeeg() {
		for (int i = 0; i < bins.size(); i++) {
			ArrayList<Artikel> bin = bins.get(i);
			if(!bin.isEmpty()) return false;
		};
		return true;
	}

	/**
	 * Returnt en verwijdert de eerste instantie van
	 * {@code item} uit de gegenereerde bins.
	 * <br><br>
	 * Wordt gebruikt na het scannen van een nieuw item
	 * zodat het in het juiste bakje gevult kan worden.
	 *
	 * @param 	artikel	Het item om te zoeken
	 * @return 			De index van het eerst voorkomen object in de bins. <br>
	 * 		  			-1 Als er geen bin gevonden is.
	 * @author 			Krijn Grimme
	 */
	public int findBinNum(Artikel artikel) {
		int binNum = 0;                               	// Variabele om bij te houden welke bin gecheckt wordt begint bij 0

		for (ArrayList<Artikel> bin : this.bins) {   	// Foreach bin in bins
			if (bin.contains(artikel)) {
				// todo: Delete doen in de find functie of apart (zie remBinItem())
				bin.remove(artikel);		// Verwijder het integer object uit de bin
														// Integer.valueOf() omdat als er een int wordt meegegeven
														// dan wordt die index verwijderd ipv dat object.
				return binNum;                        	// Return het nummer van huidige bin.
			}
			binNum++;                                	// Check volgende bin
		}
		return -1;                                    	// Retrun -1 als item niet in bins zit
	}

	/**
	 * Uitvoeren van het best fit aloritme en vult dit in op {@code bins} dus gebonden aan deze class
	 *
	 * @param items			items die verdeeld moeten worden of de bins
	 * @param binGrootte	maximale inhoud van de bins
	 *
	 * @author Krijn Grimme
	 */
	private void bestFit(Object[] items, int binGrootte) {
		int maxAant = items.length;

		// Aantal bins
		int aantBins = 0;

		// Arraylist vol met arraylists waar de inhoud van de bins in staat
		ArrayList<ArrayList<Artikel>> resultaat = new ArrayList<>();

		// Lege array om overgebleven ruimte in op te slaan
		// Er zijn maximaal "maxAant" dozen
		int[] binRuimte = new int[maxAant];

		// 1 voor 1 alle items plaatsen
		for (Object obj : items) {
			Artikel artikel = (Artikel) obj;
			int item = artikel.getGewicht();
			// Zoek een bin waar het item het best in past
			int controleBin;

			// Minimale ruimte en de bin die gevuld moet worden initialiseren
			int min = binGrootte + 1, bin = 0;

			// Check voor elke aangemaakte bin welke het minste ruimte over heeft
			for (controleBin = 0; controleBin < aantBins; controleBin++) {
				if (binRuimte[controleBin] >= item &&                	// Als het item in de bin past &&
						binRuimte[controleBin] - item < min)         	// Als de overgebleven waarde in de bin minder is
				{                                                    	// dan het hiervoor minimale gemeten waarde
					bin = controleBin;                               	// Stel dan de bin in op die bin
					min = binRuimte[controleBin] - item;             	// En sla het nieuwe minimum in
				}
			}

			// Als er geen bin was waar het item in paste
			// Maak dan een nieuwe bin in
			if (min == binGrootte + 1) {
				ArrayList<Artikel> bInhoud = new ArrayList<>();      	// Maak nieuwe bin inhoud lijst aan
				bInhoud.add(artikel);                                   	// Voeg het item dat niet paste hieraan toe
				resultaat.add(bInhoud);                              	// Voeg deze nieuwe lijst toe aan het resultaat
				binRuimte[aantBins] = binGrootte - item;             	// Stel de overgebleven ruimte in voor de nieuwe bin
				aantBins++;                                           	// Voeg 1 bin toe aan het aantal bins

			} else { // Voeg het item toe aan de bin waar hij hoort
				resultaat.get(bin).add(artikel);                         	// Pak de lijst met items van de geselecteerde bin
																		// voeg daar het nieuwe item aan toe.

				binRuimte[bin] -= item;                               	// Sla de overgebleven ruimte op voor de huidige bin
			}
		}
		this.bins = resultaat;
	}


	/**
	 * Maakt een nieuw Bpp object en voert gelijk het Bpp algoritme uit op de meegegeven items.
	 * Dit wordt opgeslagen in dit object.
	 *
	 * @param items         de items die verdeeld moeten worden
	 * @param maxBinGrootte de maximale bin grootte voor 1 bin
	 *
	 * @author Krijn Grimme
	 */
	public Bpp(ArrayList<Artikel> items, int maxBinGrootte) {

		// Sorteer op decreasing
		items.sort(Collections.reverseOrder());

		// BestFit met de gesorteerde items
		this.bestFit(items.toArray(), maxBinGrootte);
	}

	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();
		for (ArrayList<Artikel> bin: bins) {
			for (Artikel artikel : bin) {
				retString.append(artikel.getKleur()).append("\n");
			}
			retString.append("\n");
		}
		return retString.toString();
	}
}
