package ca.ulaval.ift6003.application.impl;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ca.ulaval.ift6003.application.AdminApplication;
import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIIdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Permissions;
import ca.ulaval.ift6003.domaine.modele.exceptions.DroitsInsuffisants;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaisonRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletService;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatchRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifService;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.domaine.modele.inventaire.MatchRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.MatchService;
import ca.ulaval.ift6003.domaine.shared.Repository;

@SessionScoped
@ManagedBean(name = "adminApplication")
public class AdminApplicationImpl implements AdminApplication, Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{billetService}")
	protected BilletService billetService;
	@ManagedProperty(value = "#{matchService}")
	protected MatchService matchService;
	@ManagedProperty(value = "#{centreSportifService}")
	protected CentreSportifService centreSportifService;

	@ManagedProperty(value = "#{matchRepository}")
	protected MatchRepository matchRepository;
	@ManagedProperty(value = "#{billetUnMatchRepository}")
	protected BilletUnMatchRepository billetUnMatchRepository;
	@ManagedProperty(value = "#{billetSaisonRepository}")
	protected BilletSaisonRepository billetSaisonRepository;
	@ManagedProperty(value = "#{centreSportifRepository}")
	protected CentreSportifRepository centreSportifRepository;
	@ManagedProperty(value = "#{utilisateurManagement}")
	protected UtilisateurManagement utilisateurManagement;

	public AdminApplicationImpl() {

	}

	@Override
	public void ajouterNouveauCentreSportif(CentreSportif centreSportif) throws UIDroitsInsuffisants, UIIdentifiantDejaExistant {
		authoriserUtilisateur(Permissions.CREATION_CENTRE_SPORTIF);
		try {
			centreSportifService.ajouterNouveauCentreSportif(centreSportif);
		} catch (IdentifiantDejaExistant e) {
			throw new UIIdentifiantDejaExistant();
		}
	}

	@Override
	public void ajouterNouveauMatch(Match match) throws UIDroitsInsuffisants {
		authoriserUtilisateur(Permissions.CREATION_MATCH);
		matchService.ajouterNouveauMatch(match);
	}

	@Override
	public void ajouterNouveauBilletUnMatch(BilletUnMatch billet) throws UIDroitsInsuffisants {
		authoriserUtilisateur(Permissions.CREATION_BILLET);
		billetService.ajouterNouveauBilletUnMatch(billet);
	}

	@Override
	public void ajouterNouveauBilletSaison(BilletSaison billet) throws UIDroitsInsuffisants {
		authoriserUtilisateur(Permissions.CREATION_BILLET);
		billetService.ajouterNouveauBilletSaison(billet);
	}

	@Override
	public void ajouterPlusieursNouveauxBilletsUnMatch(BilletUnMatch billet, int nombre) throws UIDroitsInsuffisants {
		authoriserUtilisateur(Permissions.CREATION_BILLET);
		for (int i = 0; i < nombre; i++) {
			ajouterNouveauBilletUnMatch(billet.clone());
		}
	}

	@Override
	public void ajouterPlusieursNouveauxBilletsSaison(BilletSaison billet, int nombre) throws UIDroitsInsuffisants {
		authoriserUtilisateur(Permissions.CREATION_BILLET);
		for (int i = 0; i < nombre; i++) {
			ajouterNouveauBilletSaison(billet.clone());
		}
	}

	@Override
	public void revendreBilletUnMatch(BilletUnMatch billet) throws UIDroitsInsuffisants {
		authoriserUtilisateur(Permissions.ACHAT_BILLET);
		billetService.ajouterNouveauBilletUnMatch(billet);
		utilisateurManagement.ajouterAuxReventesDeLUtilisateur(billet);
	}

	@Override
	public void revendrePlusieursBilletsUnMatch(BilletUnMatch billet, int nombreBillets) throws UIDroitsInsuffisants {
		for (int i = 0; i < nombreBillets; i++) {
			revendreBilletUnMatch(billet);
		}
	}

	@Override
	public void revendrePlusieursBilletsSaison(BilletSaison billet, int nombreBillets) throws UIDroitsInsuffisants {
		// TODO Auto-generated method stub

	}

	@Override
	public void revendreBilletSaison(BilletSaison billet) throws UIDroitsInsuffisants {
		authoriserUtilisateur(Permissions.ACHAT_BILLET);
		billetService.ajouterNouveauBilletSaison(billet);
		utilisateurManagement.ajouterAuxReventesDeLUtilisateur(billet);
	}

	@Override
	public void effacerBilletUnMatchParID(String billetID) throws UIDroitsInsuffisants, UIEntiteInexistante {
		authoriserUtilisateur(Permissions.SUPPRESSION_BILLET);
		supprimerParIdentifiant(billetUnMatchRepository, billetID, Consts.NOM_ENTITE_BILLET);
	}

	@Override
	public void effacerBilletSaisonParID(String billetID) throws UIDroitsInsuffisants, UIEntiteInexistante {
		authoriserUtilisateur(Permissions.SUPPRESSION_BILLET);
		supprimerParIdentifiant(billetSaisonRepository, billetID, Consts.NOM_ENTITE_BILLET);
	}

	@Override
	public void effacerCentreSportifParNom(String nomCentre) throws UIDroitsInsuffisants, UIEntiteInexistante {
		authoriserUtilisateur(Permissions.SUPPRESSION_CENTRE_SPORTIF);
		supprimerParIdentifiant(centreSportifRepository, nomCentre, Consts.NOM_ENTITE_CENTRE_SPORTIF);
	}

	@Override
	public void effacerMatchParID(String matchID) throws UIDroitsInsuffisants, UIEntiteInexistante {
		authoriserUtilisateur(Permissions.SUPPRESSION_MATCH);
		supprimerParIdentifiant(matchRepository, matchID, Consts.NOM_ENTITE_MATCH);
		billetUnMatchRepository.supprimerTousAyantMatchID(matchID);
	}

	@SuppressWarnings("rawtypes")
	private void supprimerParIdentifiant(Repository repository, String identifiant, String nomEntite) throws UIEntiteInexistante {
		try {
			repository.supprimerParIdentifiantUnique(identifiant);
		} catch (EntiteNonTrouvee e) {
			throw new UIEntiteInexistante();
		}
	}

	private void authoriserUtilisateur(String permission) throws UIDroitsInsuffisants {
		try {
			utilisateurManagement.authoriserUtilisateur(permission);
		} catch (DroitsInsuffisants e) {
			throw new UIDroitsInsuffisants();
		}
	}

	public void setBilletSaisonRepository(BilletSaisonRepository billetSaisonRepository) {
		this.billetSaisonRepository = billetSaisonRepository;
	}

	public void setBilletService(BilletService billetService) {
		this.billetService = billetService;
	}

	public void setBilletUnMatchRepository(BilletUnMatchRepository billetUnMatchRepository) {
		this.billetUnMatchRepository = billetUnMatchRepository;
	}

	public void setCentreSportifRepository(CentreSportifRepository centreSportifRepository) {
		this.centreSportifRepository = centreSportifRepository;
	}

	public void setCentreSportifService(CentreSportifService centreSportifService) {
		this.centreSportifService = centreSportifService;
	}

	public void setMatchRepository(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	public void setUtilisateurManagement(UtilisateurManagement utilisateurManagement) {
		this.utilisateurManagement = utilisateurManagement;
	}

}
