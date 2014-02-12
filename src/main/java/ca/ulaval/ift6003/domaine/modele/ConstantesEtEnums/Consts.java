package ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums;

public final class Consts {

	public static final String FICHIER_BILLETS_UNMATCH = "/billets-unmatch.xml";
	public static final String FICHIER_BILLETS_SAISON = "/billets-saison.xml";
	public static final String FICHIER_MATCHS = "/matchs.xml";
	public static final String FICHIER_UTILISATEURS = "/utilisateurs.xml";
	public static final String FICHIER_MAPNEXTID = "/mapnextid.xml";
	public static final String CENTRESSPORTIFS = "/centressportifs.xml";

	public static final String UTILISATEUR_ADMIN = "admin";

	public static final String NOM_ENTITE_MATCH = "match";
	public static final String NOM_ENTITE_BILLET = "billet";
	public static final String NOM_ENTITE_CENTRE_SPORTIF = "centre sportif";

	public static final String SIEGE_RESERVE = "reserve";
	public static final String SIEGE_GENERAL = "general";

	public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

	public static final String CENTRE_SPORTIF_ULAVAL = "Stade Université Laval";

	private Consts() {
		throw new AssertionError();
	}

}
