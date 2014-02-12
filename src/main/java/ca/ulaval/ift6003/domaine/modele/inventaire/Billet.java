package ca.ulaval.ift6003.domaine.modele.inventaire;

public abstract class Billet implements Cloneable {

	protected String billetId;
	protected String nomSection;
	protected String nomSiege;
	protected String categorie_siege;
	protected double prix;

	public String getBilletId() {
		return billetId;
	}

	public void setBilletId(String billetId) {
		this.billetId = billetId;
	}

	public String getNomSection() {
		return nomSection;
	}

	public void setNomSection(String nomSection) {
		this.nomSection = nomSection;
	}

	public String getNomSiege() {
		return nomSiege;
	}

	public void setNomSiege(String nomSiege) {
		this.nomSiege = nomSiege;
	}

	public String getCategorie_siege() {
		return categorie_siege;
	}

	public void setCategorie_siege(String categorie_siege) {
		this.categorie_siege = categorie_siege;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public abstract String getType();
}
