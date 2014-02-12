package ca.ulaval.ift6003.application;

import java.util.List;
import java.util.Set;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIIdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.exceptions.DroitsInsuffisants;
import ca.ulaval.ift6003.domaine.modele.inventaire.Billet;
import ca.ulaval.ift6003.domaine.modele.panieretachats.Panier;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;

public interface UtilisateurManagement {

	void inscrireNouvelUtilisateur(Utilisateur utilisateur) throws UIIdentifiantDejaExistant;

	void loginUtilisateurParCredentiels(String nomUtilisateur, String motDePasse) throws UIEntiteInexistante;

	void loginUtilisateurAnonyme();

	void logoutUtilisateurActif();

	void modifierCompteUtilisateurExistant(Utilisateur utilisateur) throws UIEntiteInexistante;

	boolean utilisateurEstConnecte();

	List<Utilisateur> getListeUtilisateurs();

	PreferencesBillets preferencesUtilisateurs();

	void authoriserUtilisateur(String permission) throws DroitsInsuffisants;

	boolean utilisateurActifALeDroitDe(String permission);

	boolean utilisateurActifALeDroitDe(Set<String> permissions);

	Utilisateur getUtilisateurActif();

	Panier getPanier();

	String nomDeLutilisateur();

	void envoyerCourrielConfirmation(int numTransaction);

	void ajouterAuxReventesDeLUtilisateur(Billet billet);

	void retirerDesReventesDeLUtilisateur(String billetID);

	Set<String> getBilletsEnReventeParUtilisateur();
}
