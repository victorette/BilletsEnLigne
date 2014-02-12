package ca.ulaval.ift6003.domaine.modele.utilisateur;

public class InfosBancaires {

	private String typeCarte;
	private String numeroCarte;
	private String moisExpiration;
	private String anneeExpiration;

	public InfosBancaires(String typeCarte, String numeroCarte, String moisExpiration, String anneeExpiration) {
		this.typeCarte = typeCarte;
		this.numeroCarte = numeroCarte;
		this.moisExpiration = moisExpiration;
		this.anneeExpiration = anneeExpiration;
	}

	public InfosBancaires() {

	}

	public String getTypeCarte() {
		return typeCarte;
	}

	public void setTypeCarte(String typeCarte) {
		this.typeCarte = typeCarte;
	}

	public String getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public String getMoisExpiration() {
		return moisExpiration;
	}

	public void setMoisExpiration(String moisExpiration) {
		this.moisExpiration = moisExpiration;
	}

	public String getAnneeExpiration() {
		return anneeExpiration;
	}

	public void setAnneeExpiration(String anneeExpiration) {
		this.anneeExpiration = anneeExpiration;
	}

}
