package Model;

import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rezept {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String name;

	private String bild;

	private int anzahlPortionen;

	private String anleitung;


	private Timestamp erstelldatum;

	private double naehrwerte;

	//private Bewertung[] gehören;

	//private Mengeneinheit mengeneinheit;

	//private Bewertung bewertung;

	private ArrayList<Zutateninformation> zutateninformation;

	private String ernaehrungsform;

	private String kategorie;
/*
	public Rezept(String name, int anzahlPortionen, String anleitung, Ernaehrungsform ernaehrungsform, Kategorie kategorie) {
		this.name = name;
		this.anzahlPortionen = anzahlPortionen;
		this.anleitung = anleitung;
		this.ernaehrungsform = ernaehrungsform;
		this.kategorie = kategorie;
	}*/
	public Rezept(String id,String name, int anzahlPortionen, String anleitung,
				  Timestamp timestamp,ArrayList<Zutateninformation> zutateninformation,String kategorie,String ernaehrungsform,String bildid) {
		this.name = name;
		this.anzahlPortionen = anzahlPortionen;
		this.anleitung = anleitung;
		this.erstelldatum = timestamp;
		this.zutateninformation = zutateninformation;
		this.kategorie = kategorie;
		this.ernaehrungsform = ernaehrungsform;
		this.bild  = bildid;
		this.id = id;
	}

	public void setName(String name) {

	}

	public String getName() {
		return name;
	}

	public void setBild(String bild) {

	}

	public String getBild() {
		return bild;
	}

	public void setAnzahlPortionen(int anzahlPortionen) {

	}

	public int getAnzahlPortionen() {
		return anzahlPortionen;
	}

	public void setAnleitung(String anleitung) {

	}

	public String getAnleitung() {
		return anleitung;
	}

	public void setErstelldatum(Timestamp erstelldatum) {

	}

	public Timestamp getErstelldatum() {
		return erstelldatum;
	}

	public void setKategorien(String kategorien) {

	}

	public String getKategorien() {
		return kategorie;
	}

	public void setErnaehrungsformen(String ernaehrungsformen) {

	}

	public String getErnaehrungsform() {
		return ernaehrungsform;
	}

	/*public void setNaehrwert(double naehwert) {

	}

	public double getNaehrwert() {
		return naehrwerte;
	}*/

	public void setZutateninformation(ArrayList<Zutateninformation> zutateninformation) {

	}

	public ArrayList<Zutateninformation> getZutateninformation() {
		return zutateninformation;
	}

}
