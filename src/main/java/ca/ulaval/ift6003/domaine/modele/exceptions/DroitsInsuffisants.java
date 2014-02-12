package ca.ulaval.ift6003.domaine.modele.exceptions;

public class DroitsInsuffisants extends Exception {

	private static final long serialVersionUID = 1L;

	public DroitsInsuffisants() {
		super("Droits insuffisants");
	}

	public DroitsInsuffisants(String msg) {
		super("Droits insuffisants ::: " + msg);
	}
}
