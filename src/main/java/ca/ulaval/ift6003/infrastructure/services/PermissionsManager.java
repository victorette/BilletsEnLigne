package ca.ulaval.ift6003.infrastructure.services;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Permissions;

import java.util.HashSet;
import java.util.Set;

public class PermissionsManager {

	public static synchronized Set<String> permissionsUtilisateurAnonyme() {
		Set<String> permissions = new HashSet<>();

		permissions.add(Permissions.VOIR_TOUS_BILLETS);
		permissions.add(Permissions.VOIR_TOUS_CENTRES_SPORTIFS);
		permissions.add(Permissions.VOIR_TOUS_MATCHS);
		permissions.add(Permissions.VOIR_TOUS_UTILISATEURS);
		permissions.add(Permissions.VOIR_INVENTAIRE);

		return permissions;
	}

	public static synchronized Set<String> permissionsUtilisateurInscrit() {
		Set<String> permissions = permissionsUtilisateurAnonyme();

		permissions.add(Permissions.MODIFICATION_COMPTE);
		permissions.add(Permissions.APPLIQUER_PREFERENCES_BILLETS);
		permissions.add(Permissions.ACHAT_BILLET);
		permissions.add(Permissions.REVENTE_BILLET);

		return permissions;
	}

	public static synchronized Set<String> permissionsAdmin() {
		Set<String> permissions = permissionsUtilisateurInscrit();

		permissions.add(Permissions.CREATION_BILLET);
		permissions.add(Permissions.CREATION_CENTRE_SPORTIF);
		permissions.add(Permissions.CREATION_MATCH);
		permissions.add(Permissions.SUPPRESSION_BILLET);
		permissions.add(Permissions.SUPPRESSION_CENTRE_SPORTIF);
		permissions.add(Permissions.SUPPRESSION_MATCH);

		return permissions;
	}

}
