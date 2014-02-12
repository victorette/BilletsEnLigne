package ca.ulaval.ift6003.application;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIIdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;

public interface AdminApplication {

	void ajouterNouveauCentreSportif(CentreSportif centreSportif) throws UIDroitsInsuffisants, UIIdentifiantDejaExistant;

	void ajouterNouveauMatch(Match match) throws UIDroitsInsuffisants;

	void ajouterNouveauBilletUnMatch(BilletUnMatch billet) throws UIDroitsInsuffisants;

	void ajouterNouveauBilletSaison(BilletSaison billet) throws UIDroitsInsuffisants;

	void ajouterPlusieursNouveauxBilletsUnMatch(BilletUnMatch billet, int nombre) throws UIDroitsInsuffisants;

	void ajouterPlusieursNouveauxBilletsSaison(BilletSaison billet, int nombre) throws UIDroitsInsuffisants;

	void effacerBilletUnMatchParID(String billetID) throws UIDroitsInsuffisants, UIEntiteInexistante;

	void effacerBilletSaisonParID(String billetID) throws UIDroitsInsuffisants, UIEntiteInexistante;

	void effacerCentreSportifParNom(String nomCentre) throws UIDroitsInsuffisants, UIEntiteInexistante;

	void effacerMatchParID(String matchID) throws UIDroitsInsuffisants, UIEntiteInexistante;

	void revendrePlusieursBilletsUnMatch(BilletUnMatch billet, int nombreBillets) throws UIDroitsInsuffisants;

	void revendreBilletUnMatch(BilletUnMatch billet) throws UIDroitsInsuffisants;

	void revendrePlusieursBilletsSaison(BilletSaison billet, int nombreBillets) throws UIDroitsInsuffisants;

	void revendreBilletSaison(BilletSaison billet) throws UIDroitsInsuffisants;

}
