package Model;

import java.util.List;

public class AdminRepo {

	private Admin admin;

	public Rezept getRezeptByName(String name) {
		return null;
	}

	/**
	 * Default Method
	 */
	public void setBestaetigungsFlag(Benutzer benutzer) {

	}

	public Benutzer findBenutzerByName(String username) {
		return null;
	}

	public void setGesperrtFlag(Benutzer benutzer) {

	}

	public void resetGesperrtFlag(Benutzer benutzer) {

	}

	/**
	 * Default Method
	 */
	public Rezept findRezeptByOptional(String kategorie, String ersteller, List<Zutat> zutaten, int mindestbewertung) {
		return null;
	}

}
