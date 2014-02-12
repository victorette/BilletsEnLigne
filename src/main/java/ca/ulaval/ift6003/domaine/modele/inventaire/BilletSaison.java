package ca.ulaval.ift6003.domaine.modele.inventaire;

import java.io.Serializable;

import ca.ulaval.ift6003.domaine.shared.Entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("billetSaison")
public class BilletSaison extends Billet implements Serializable, Entity<BilletSaison>, Cloneable {

	private static final long serialVersionUID = 1L;

	protected String nomCentreSportif;
	private String annee;

	public BilletSaison() {
		this.billetId = "";
		this.nomSection = "";
		this.nomSiege = "";
		this.categorie_siege = "";
		this.prix = 0.00;
		this.annee = "";
	}

	public BilletSaison(String billetId, String annee, String nomCentreSportif, String nomSection, String nomSiege, String categorie_siege, double prix) {
		this.billetId = billetId;
		this.annee = annee;
		this.nomCentreSportif = nomCentreSportif;
		this.nomSection = nomSection;
		this.nomSiege = nomSiege;
		this.categorie_siege = categorie_siege;
		this.prix = prix;
	}

	@Override
	public String toString() {
		return ("Nom Centre Sportif " + nomCentreSportif + " Billet " + billetId + " Match " + annee + " " + nomSection + " Siege " + nomSiege + " Catégorie " + "" + "Siège "
				+ categorie_siege + " Prix " + prix);

	}

	@Override
	public BilletSaison clone() {
		BilletSaison b = new BilletSaison();

		b.setNomCentreSportif(nomCentreSportif);
		b.setCategorie_siege(categorie_siege);
		b.setAnnee(annee);
		b.setNomSection(nomSection);
		b.setNomSiege(nomSiege);
		b.setPrix(prix);

		return b;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getNomCentreSportif() {
		return nomCentreSportif;
	}

	public void setNomCentreSportif(String nomCentreSportif) {
		this.nomCentreSportif = nomCentreSportif;
	}

	@Override
	public String getType() {
		return categorie_siege + Double.toString(prix) + annee + nomCentreSportif;
	}

	@Override
	public boolean memeEntiteQue(BilletSaison autre) {
		return this.getBilletId().equals(autre.getBilletId());
	}

	@Override
	public String identifiantUnique() {
		return billetId;
	}

}
