package ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums;

public final class Sports {

	public static final String SOCCER = "soccer";
	public static final String FOOTBALL = "football";
	public static final String BASKETBALL = "basketball";
	public static final String RUGBY = "rubgy";
	public static final String VOLLEYBALL = "volleyball";

	private Sports() {
		// this prevents even the native class from
		// calling this ctor as well :
		throw new AssertionError();
	}
}
