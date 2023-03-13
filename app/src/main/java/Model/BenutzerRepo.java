package Model;

import java.util.List;

public class BenutzerRepo {

	private Benutzer benutzer;

	/**
	 * Default Method
	 */
	public Rezept getRezeptByName(String name) {
		return null;
	}

	/**
	 * Default Method
	 */
	public Rezept getRezeptByOptional(String kategorie, String ersteller, List<Zutat> zutaten, double mindestbewertung) {
		return null;
	}

	public void deleteKonto(Benutzer benutzer) {

	}

	public void changeKontodaten(Benutzer alterBenutzer, Benutzer neuerBenutzer) {

	}

}
