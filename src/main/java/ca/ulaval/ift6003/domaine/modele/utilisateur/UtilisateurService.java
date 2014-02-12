package ca.ulaval.ift6003.domaine.modele.utilisateur;

import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;

import java.io.Serializable;

public interface UtilisateurService extends Serializable {

	void inscrireNouvelUtilisateur(Utilisateur utilisateur) throws IdentifiantDejaExistant;

	Utilisateur loginUtilisateurAnonyme();

	Utilisateur loginUtilisateurParCredentiels(String nomUtilisateur, String motDePasse) throws EntiteNonTrouvee;

}
