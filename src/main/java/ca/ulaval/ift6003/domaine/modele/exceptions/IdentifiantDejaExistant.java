package ca.ulaval.ift6003.domaine.modele.exceptions;

public class IdentifiantDejaExistant extends Exception {

	private static final long serialVersionUID = 1L;

	public IdentifiantDejaExistant() {
		super("Identifiant déjà existant.");
	}

	public IdentifiantDejaExistant(String msg) {
		super(msg);
	}
}
