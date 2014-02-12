package ca.ulaval.ift6003.application.frontiere.exceptionsUI;

public class UIIdentifiantDejaExistant extends Exception {

	private static final long serialVersionUID = 1L;

	public UIIdentifiantDejaExistant() {
		super("Cet identifiant est déjà utilisé");
	}

	public UIIdentifiantDejaExistant(String msg) {
		super(msg);
	}

}
