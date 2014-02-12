package ca.ulaval.ift6003.application.frontiere.exceptionsUI;

public class UIEntiteInexistante extends Exception {

	private static final long serialVersionUID = 1L;

	public UIEntiteInexistante() {
		super("Aucune entité trouvée avec cet identifiant.");
	}

	public UIEntiteInexistante(String msg) {
		super(msg);
	}

}
