package Model;

import java.util.ArrayList;

public class Benutzer extends Besucher {

	private String name;

	private String mailadresse;

	private String passwort;
	private ArrayList<String> rezepte;

	public Benutzer(String name,String email){
		this.name = name;
		this.mailadresse = email;

	}

	public ArrayList<String> getRezepte() {
		return rezepte;
	}

	public void setRezepte(ArrayList<String> rezepte) {
		this.rezepte = rezepte;
	}

	public void setName(String name) {

	}

	public String getName() {
		return name;
	}

	public void setMailadresse(String mailadresse) {

	}

	public String getMailadresse() {
		return mailadresse;
	}

	public void setPasswort(String passwort) {

	}

	public String getPasswort() {
		return null;
	}

	public void setRolle(Enum rolle) {

	}



}
