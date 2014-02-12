package ca.ulaval.ift6003.application.frontiere.exceptionsUI;

public class UIQuantiteInsuffisante extends Exception {

	private static final long serialVersionUID = 1L;

	public UIQuantiteInsuffisante() {
		super("Quantité insuffisante.");
	}

	public UIQuantiteInsuffisante(String msg) {
		super(msg);
	}

}
