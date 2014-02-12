package ca.ulaval.ift6003.domaine.modele.exceptions;

public class QuantiteInsuffisante extends Exception {

	private static final long serialVersionUID = 1L;

	public QuantiteInsuffisante() {
		super("Quantité insuffisante");
	}

	public QuantiteInsuffisante(String msg) {
		super(msg);
	}

}
