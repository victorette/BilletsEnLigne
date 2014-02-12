package ca.ulaval.ift6003.domaine.modele.exceptions;

public class EntiteNonTrouvee extends Exception {

	private static final long serialVersionUID = 1L;

	public EntiteNonTrouvee() {

	}

	public EntiteNonTrouvee(String identifiant) {
		super("Entité non existante ::: " + identifiant);
	}
}
