package ca.ulaval.ift6003.application.frontiere.constantesEtEnumsUI;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Permissions;

public final class UIPermissions {

	public static final String VOIR_TOUS_BILLETS = Permissions.VOIR_TOUS_BILLETS;
	public static final String VOIR_TOUS_CENTRES_SPORTIFS = Permissions.VOIR_TOUS_CENTRES_SPORTIFS;
	public static final String VOIR_TOUS_MATCHS = Permissions.VOIR_TOUS_MATCHS;
	public static final String VOIR_TOUS_UTILISATEURS = Permissions.VOIR_TOUS_UTILISATEURS;
	public static final String VOIR_INVENTAIRE = Permissions.VOIR_INVENTAIRE;
	public static final String ACHAT_BILLET = Permissions.ACHAT_BILLET;
	public static final String REVENTE_BILLET = Permissions.REVENTE_BILLET;
	public static final String CREATION_CENTRE_SPORTIF = Permissions.CREATION_CENTRE_SPORTIF;
	public static final String CREATION_BILLET = Permissions.CREATION_BILLET;
	public static final String CREATION_MATCH = Permissions.CREATION_MATCH;
	public static final String MODIFICATION_COMPTE = Permissions.MODIFICATION_COMPTE;
	public static final String SUPPRESSION_CENTRE_SPORTIF = Permissions.SUPPRESSION_CENTRE_SPORTIF;
	public static final String SUPPRESSION_BILLET = Permissions.SUPPRESSION_BILLET;
	public static final String SUPPRESSION_MATCH = Permissions.SUPPRESSION_MATCH;
	public static final String APPLIQUER_PREFERENCES_BILLETS = Permissions.APPLIQUER_PREFERENCES_BILLETS;

	private UIPermissions() {
		throw new AssertionError();
	}
}