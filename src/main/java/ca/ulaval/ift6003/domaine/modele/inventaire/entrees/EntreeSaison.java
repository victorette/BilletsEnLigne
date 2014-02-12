package ca.ulaval.ift6003.domaine.modele.inventaire.entrees;

public class EntreeSaison extends Entree {

	private String annee;

	public EntreeSaison() {

	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	@Override
	public boolean memeValeurQue(Entree autre) {
		return getValeur().equals(autre.getValeur());
	}

	@Override
	protected String getValeur() {
		return nomCentreSportif + ville + nomSection + categorieSiege + Double.toString(prix) + type + annee;
	}
}
