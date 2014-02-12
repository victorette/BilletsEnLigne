package ca.ulaval.ift6003.interfaces.beans.compte;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;

@RequestScoped
@ManagedBean(name = "modificationCompteBean")
public class ModificationCompteBean extends CompteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private PreferencesBillets preferences;

	@PostConstruct
	public void init() {
		this.utilisateurActif = utilisateurManagement.getUtilisateurActif();
		this.preferences = utilisateurActif.getPreferencesBillets();
	}

	public String confirmerModificationClicked() {
		try {
			utilisateurManagement.modifierCompteUtilisateurExistant(utilisateurActif);
			guiMessageHandler.addMessage("Modification du compte réussie", "");
			return "modification-compte-reussie";
		} catch (UIEntiteInexistante e) {
			guiMessageHandler.addMessage("Echec", e.getMessage());
			return "modification-compte-echoue";
		}
	}

	public PreferencesBillets getPreferences() {
		return preferences;
	}

	public void setPreferences(PreferencesBillets preferences) {
		this.preferences = preferences;
	}

}
