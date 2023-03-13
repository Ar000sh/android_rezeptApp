package Model;

public class Zutateninformation {
// ist double
	private String mengenangabe;

	private Zutat zutat;

	private String mengeneinheit;

	public Zutateninformation(String mengenangabe, String mengeneinheit , Zutat zutat) {
		this.mengenangabe = mengenangabe;
		this.mengeneinheit = mengeneinheit;
		this.zutat = zutat;
	}


	public void setMengenangabe(double mengenangabe) {

	}

	public String getMengenangabe() {
		return mengenangabe;
	}

	public void setZutat(Zutat zutat) {

	}

	public Zutat getZutat() {
		return zutat;
	}

	public void setMengeneinheit(Mengeneinheit mengeneinheit) {

	}

	public String getMengeneinheit() {
		return mengeneinheit;
	}

}
