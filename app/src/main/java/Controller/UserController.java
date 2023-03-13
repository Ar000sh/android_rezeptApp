package Controller;

import Model.BesucherRepo;
import Model.BenutzerRepo;
import Model.AdminRepo;
import java.util.List;
import Model.Benutzer;
import Model.Rezept;
import Model.Zutat;

public class UserController {

	private BesucherRepo besucherRepo;

	private BenutzerRepo benutzerRepo;

	private AdminRepo adminRepo;


	public boolean registrieren(String username, String mailadresse, String passwort) {
		return false;
	}

	public List<Rezept> rezeptSuchen(String name) {
		return null;
	}

	public Benutzer anmelden(String username, String passwort) {
		return null;
	}

	/**
	 * Default Method
	 */
	public void sucheFiltern(String kategorie, String ersteller, List<Zutat> zutaten, int mindestbewertung) {

	}

}
