package ca.ulaval.ift6003.application.frontiere.exceptionsUI;

public class UIDroitsInsuffisants extends Exception {

	private static final long serialVersionUID = 1L;

	public UIDroitsInsuffisants() {
		super("Droits insuffisants.");
	}

	public UIDroitsInsuffisants(String msg) {
		super(msg);
	}

}
