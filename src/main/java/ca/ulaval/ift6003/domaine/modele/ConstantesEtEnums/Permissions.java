package ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums;

public final class Permissions {

	public static final String VOIR_TOUS_BILLETS = "VOIR_TOUS_BILLETS";
	public static final String VOIR_TOUS_CENTRES_SPORTIFS = "VOIR_TOUS_CENTRES_SPORTIFS";
	public static final String VOIR_TOUS_MATCHS = "VOIR_TOUS_MATCHS";
	public static final String VOIR_TOUS_UTILISATEURS = "VOIR_TOUS_UTILISATEURS";
	public static final String VOIR_INVENTAIRE = "VOIR_INVENTAIRE";
	public static final String ACHAT_BILLET = "ACHAT_BILLET";
	public static final String REVENTE_BILLET = "REVENTE_BILLET";
	public static final String CREATION_CENTRE_SPORTIF = "CREATION_CENTRE_SPORTIF";
	public static final String CREATION_BILLET = "CREATION_BILLET";
	public static final String CREATION_MATCH = "CREATION_MATCH";
	public static final String MODIFICATION_COMPTE = "MODIFICATION_COMPTE";
	public static final String SUPPRESSION_CENTRE_SPORTIF = "SUPPRESSION_CENTRE_SPORTIF";
	public static final String SUPPRESSION_BILLET = "SUPPRESSION_BILLET";
	public static final String SUPPRESSION_MATCH = "SUPPRESSION_MATCH";
	public static final String APPLIQUER_PREFERENCES_BILLETS = "APPLIQUER_PREFERENCES_BILLETS";

	private Permissions() {
		// this prevents even the native class from
		// calling this ctor as well :
		throw new AssertionError();
	}
}