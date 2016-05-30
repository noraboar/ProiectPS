package models;

public class Produs {

	private String nume;
	private int pret;
	private int stoc;
	

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getPret() {
		return pret;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}

	public int getStoc() {
		return stoc;
	}

	public void setStoc(int stoc) {
		this.stoc = stoc;
	}

	public String toString() {
		return "Produs: " + nume+" Stoc "+stoc+" Pret "+pret+"RON";
	}
}
