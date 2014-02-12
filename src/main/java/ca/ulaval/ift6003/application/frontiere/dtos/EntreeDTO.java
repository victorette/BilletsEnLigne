package ca.ulaval.ift6003.application.frontiere.dtos;

public abstract class EntreeDTO {

	public String billetID;
	public String nomCentreSportif;
	public String ville;
	public String nomSection;
	public String categorieSiege;
	public double prix;
	public int nombreBilletsDisponibles;
	public int nombreBilletsDesires;
	public double prixTotal;
	public String type;

	public String getBilletID() {
		return billetID;
	}

	public void setBilletID(String billetID) {
		this.billetID = billetID;
	}

	public String getCategorieSiege() {
		return categorieSiege;
	}

	public void setCategorieSiege(String categorieSiege) {
		this.categorieSiege = categorieSiege;
	}

	public int getNombreBilletsDesires() {
		return nombreBilletsDesires;
	}

	public void setNombreBilletsDesires(int nombreBilletsDesires) {
		this.nombreBilletsDesires = nombreBilletsDesires;
	}

	public int getNombreBilletsDisponibles() {
		return nombreBilletsDisponibles;
	}

	public void setNombreBilletsDisponibles(int nombreBilletsDisponibles) {
		this.nombreBilletsDisponibles = nombreBilletsDisponibles;
	}

	public String getNomCentreSportif() {
		return nomCentreSportif;
	}

	public void setNomCentreSportif(String nomCentreSportif) {
		this.nomCentreSportif = nomCentreSportif;
	}

	public String getNomSection() {
		return nomSection;
	}

	public void setNomSection(String nomSection) {
		this.nomSection = nomSection;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
}
