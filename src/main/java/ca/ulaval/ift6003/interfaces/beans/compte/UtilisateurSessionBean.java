package ca.ulaval.ift6003.interfaces.beans.compte;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.constantesEtEnumsUI.UIPermissions;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

@SessionScoped
@ManagedBean(name = "utilisateurSessionBean")
public class UtilisateurSessionBean extends ControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{utilisateurManagement}")
	protected UtilisateurManagement utilisateurManagement;
	private String nomUtilisateur;
	private String motDePasse;

	public UtilisateurSessionBean() {

	}

	public boolean isUtilisateurActifConnecte() {
		return utilisateurManagement.utilisateurEstConnecte();
	}

	public String getNomUtilisateurActif() {
		return utilisateurManagement.nomDeLutilisateur();
	}

	public boolean utilisateurActifPeutCreerCentreSportif() {
		return utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.CREATION_CENTRE_SPORTIF);
	}

	public boolean utilisateurActifPeutCreerBillet() {
		return utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.CREATION_BILLET);
	}

	public boolean utilisateurActifPeutCreerCentreMatch() {
		return utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.CREATION_MATCH);
	}

	public boolean utilisateurActifPeutModifierInfosCompte() {
		return utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.MODIFICATION_COMPTE);
	}

	public boolean utilisateurActifPeutSupprimerBillet() {
		return utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.SUPPRESSION_BILLET);
	}

	public boolean utilisateurActifPeutEffacerMatch() {
		return utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.SUPPRESSION_MATCH);
	}

	public boolean utilisateurActifPeutEffacerCentreSportif() {
		return utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.SUPPRESSION_CENTRE_SPORTIF);
	}

	public boolean utilisateurActifPeutAcheterBillet() {
		return utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.ACHAT_BILLET);
	}

	public String loginClicked() {
		try {
			utilisateurManagement.loginUtilisateurParCredentiels(nomUtilisateur, motDePasse);
			return "login-reussi";
		} catch (UIEntiteInexistante e) {
			guiMessageHandler.addMessage("Erreur!", "The username or password you provided does not match our records.");
			return "login-echoue";
		}
	}

	public String loginAnonymeClicked() {
		utilisateurManagement.loginUtilisateurAnonyme();
		return "login-anonyme-reussi";
	}

	public String logoutClicked() {
		utilisateurManagement.logoutUtilisateurActif();
		return "logout-reussi";
	}

	public String gotoCreationCompteClicked() {
		return "goto-creation-compte";
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public void setUtilisateurManagement(UtilisateurManagement utilisateurManagement) {
		this.utilisateurManagement = utilisateurManagement;
	}
}
