package ca.ulaval.ift6003.interfaces.beans.compte;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIIdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;

@RequestScoped
@ManagedBean(name = "creationCompteBean")
public class CreationCompteBean extends CompteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public CreationCompteBean() {
		this.utilisateurActif = new Utilisateur();
	}

	public String creerUtilisateurClicked() {
		try {
			utilisateurManagement.inscrireNouvelUtilisateur(utilisateurActif);
			guiMessageHandler.addMessage("Création de compte réussie", "Vous pouvez maintenant vous authentifier");
			return "creation-compte-reussie";
		} catch (UIIdentifiantDejaExistant e) {
			guiMessageHandler.addMessage("Error!", "Le nom utilisateurActif choisit existe déjà.");
			return "creation-compte-echouee";
		}
	}

	public String annulerCreationCompteClicked() {
		return "creation-compte-annulee";
	}

}
