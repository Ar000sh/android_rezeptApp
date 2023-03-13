package Controller;

import Model.BenutzerRepo;
import Model.Benutzer;
import Model.Kategorie;
import Model.Rezept;
import Model.Zutat;

import java.util.List;

public class BenutzerController {

	private BenutzerRepo benutzerRepo;

	public boolean abmelden(Benutzer benutzer) {
		return false;
	}

	public boolean kontodatenBearbeiten(Benutzer benutzer, String mailadresse, String passwort) {
		return false;
	}

	public boolean kontoLoeschen(Benutzer benutzer) {
		return false;
	}

	/**
	 * Default Method
	 */
	public List<Rezept> rezeptSuchen(String name) {
		return null;
	}

	/**
	 * Default Method
	 */
	public void sucheFiltern(List<Kategorie> kategorie, Benutzer ersteller, List<Zutat> zutaten, int mindestbewertung) {

	}

}
