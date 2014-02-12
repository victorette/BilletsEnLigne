package ca.ulaval.ift6003.application.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIIdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.exceptions.DroitsInsuffisants;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.inventaire.Billet;
import ca.ulaval.ift6003.domaine.modele.panieretachats.Panier;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.domaine.modele.utilisateur.UtilisateurRepository;
import ca.ulaval.ift6003.domaine.modele.utilisateur.UtilisateurService;

@SessionScoped
@ManagedBean(name = "utilisateurManagement")
public class UtilisateurManagementImpl implements UtilisateurManagement, Serializable {

	private static final long serialVersionUID = 1L;

	private static final String EMAIL_PASSWORD = "billets123";
	private static final String EMAIL_USERNAME = "BilletsEnLigneUlaval";

	@ManagedProperty(value = "#{utilisateurService}")
	private UtilisateurService utilisateurService;
	@ManagedProperty(value = "#{utilisateurRepository}")
	private UtilisateurRepository utilisateurRepository;
	private Utilisateur utilisateurActif;
	private Panier panier;

	public UtilisateurManagementImpl() {
		this.utilisateurActif = null;
		this.panier = null;
	}

	@Override
	public void inscrireNouvelUtilisateur(Utilisateur utilisateur) throws UIIdentifiantDejaExistant {
		try {
			utilisateurService.inscrireNouvelUtilisateur(utilisateur);
		} catch (IdentifiantDejaExistant e) {
			throw new UIIdentifiantDejaExistant("Nom utilisateur déjà existant!");
		}
	}

	@Override
	public void loginUtilisateurParCredentiels(String nomUtilisateur, String motDePasse) throws UIEntiteInexistante {
		try {
			utilisateurActif = utilisateurService.loginUtilisateurParCredentiels(nomUtilisateur, motDePasse);
			panier = new Panier();
		} catch (EntiteNonTrouvee e) {
			throw new UIEntiteInexistante("Les crédentiels sont invalides !");
		}
	}

	@Override
	public Set<String> getBilletsEnReventeParUtilisateur() {
		return new HashSet<String>(utilisateurActif.getBilletsRevente());
	}

	@Override
	public void loginUtilisateurAnonyme() {
		utilisateurActif = utilisateurService.loginUtilisateurAnonyme();
		creerNouveauPanier();
	}

	@Override
	public void logoutUtilisateurActif() {
		utilisateurActif = null;
		detruitePanier();
	}

	@Override
	public void modifierCompteUtilisateurExistant(Utilisateur utilisateur) throws UIEntiteInexistante {
		try {
			utilisateurRepository.update(utilisateur);
		} catch (EntiteNonTrouvee e) {
			throw new UIEntiteInexistante("Ce compte utilisateur n'existe pas!");
		}
	}

	@Override
	public boolean utilisateurEstConnecte() {
		return utilisateurActif != null;
	}

	@Override
	public List<Utilisateur> getListeUtilisateurs() {
		return utilisateurRepository.selectTous();
	}

	@Override
	public PreferencesBillets preferencesUtilisateurs() {
		return utilisateurActif.getPreferencesBillets();
	}

	@Override
	public void authoriserUtilisateur(String permission) throws DroitsInsuffisants {
		if (!utilisateurActifALeDroitDe(permission)) {
			throw new DroitsInsuffisants();
		}
	}

	@Override
	public boolean utilisateurActifALeDroitDe(String permission) {
		if (utilisateurActif != null) {
			return utilisateurActif.aLeDroitDe(permission);
		} else {
			return false;
		}
	}

	@Override
	public boolean utilisateurActifALeDroitDe(Set<String> permissions) {
		for (String permission : permissions) {
			if (!utilisateurActifALeDroitDe(permission)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Utilisateur getUtilisateurActif() {
		return utilisateurActif;
	}

	public void setUtilisateurActif(Utilisateur utilisateurActif) {
		this.utilisateurActif = utilisateurActif;
	}

	@Override
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	private void creerNouveauPanier() {
		panier = new Panier();
	}

	private void detruitePanier() {
		panier = null;
	}

	@Override
	public String nomDeLutilisateur() {
		return utilisateurActif.getNomUtilisateur();
	}

	@Override
	public void ajouterAuxReventesDeLUtilisateur(Billet billet) {
		utilisateurActif.ajouterRevente(billet);
		try {
			utilisateurRepository.update(utilisateurActif);
		} catch (EntiteNonTrouvee e) {
			// log error
		}
	}

	@Override
	public void retirerDesReventesDeLUtilisateur(String billetID) {
		utilisateurActif.retirerRevente(billetID);
		try {
			utilisateurRepository.update(utilisateurActif);
		} catch (EntiteNonTrouvee e) {
			// log error
		}
	}

	public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}

	public void setUtilisateurService(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	@Override
	public void envoyerCourrielConfirmation(int numTransaction) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(EMAIL_USERNAME, EMAIL_PASSWORD));
			email.setSSLOnConnect(true);
			email.setFrom("BilletsEnLigneUlaval@gmail.com");
			email.setSubject("BilletsEnLigne - Transaction #" + numTransaction);
			email.setMsg("-- BilletsEnLigne -- Votre transaction a été acceptée. Numéro de transaction :: " + numTransaction);
			email.addTo(utilisateurActif.getCourriel());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			// TODO react to mail sending error ?
		}
	}

}
