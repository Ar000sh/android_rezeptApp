package Controller;

import Model.AdminRepo;
import Model.Benutzer;

public class AdminController extends BenutzerController {

	private AdminRepo adminRepo;

	public boolean registrierungBestaetigen(Benutzer benutzer) {
		return false;
	}

	public Benutzer nutzerSuchen(String username) {
		return null;
	}

	public void benutzerEntsperren(Benutzer benutzer) {

	}

	public void benutzerSperren(Benutzer benutzer) {

	}

}
