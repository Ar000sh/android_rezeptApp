package Controller;

import com.google.firebase.Timestamp;

import Model.Ernaehrungsform;
import Model.Kategorie;
import Model.RezeptRepo;
import Model.ZutatRepo;
import Model.Rezept;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Model.Zutat;
import Model.Zutateninformation;

public class RezeptController {

	private RezeptRepo rezeptRepo;

	private ZutatRepo zutatRepo;

	public Rezept rezeptErstellen(String id,String name, int anzahlPortionen, String anleitung,ArrayList<Zutateninformation> zutateninformation,String kategorie,String ernaehrungsform,String bildid) {
		Timestamp timestamp = new Timestamp(new Date());
		return new Rezept(id,name,anzahlPortionen,anleitung,timestamp,zutateninformation,kategorie,ernaehrungsform,bildid);
	}

	public Rezept rezeptBearbeiten(String name, List<Zutateninformation> zutat, Byte bild, int anzahlPortionen, String anleitung, List<Kategorie> kategorie, List<Ernaehrungsform> ernaehrungsform) {
		return null;
	}

	public boolean rezeptMelden(Rezept rezept, String meldegrund) {
		return false;
	}

	public void rezeptLoeschen(Rezept rezept) {

	}

	public Zutat zutatErstellen(String name, double naehrwerte) {
		return null;
	}

}
