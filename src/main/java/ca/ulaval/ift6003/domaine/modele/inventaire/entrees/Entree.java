package ca.ulaval.ift6003.domaine.modele.inventaire.entrees;

import ca.ulaval.ift6003.domaine.shared.ValueObject;

public abstract class Entree implements ValueObject<Entree> {

	protected String billetID;
	protected String nomCentreSportif;
	protected String ville;
	protected String nomSection;
	protected String categorieSiege;
	protected double prix;
	protected String type;

	public String getNomCentreSportif() {
		return nomCentreSportif;
	}

	public void setNomCentreSportif(String nomCentreSportif) {
		this.nomCentreSportif = nomCentreSportif;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getNomSection() {
		return nomSection;
	}

	public void setNomSection(String nomSection) {
		this.nomSection = nomSection;
	}

	public String getCategorieSiege() {
		return categorieSiege;
	}

	public void setCategorieSiege(String categorieSiege) {
		this.categorieSiege = categorieSiege;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public String getBilletID() {
		return billetID;
	}

	public void setBilletID(String billetID) {
		this.billetID = billetID;
	}

	@Override
	public abstract boolean memeValeurQue(Entree autre);

	protected abstract String getValeur();

}
