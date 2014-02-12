package ca.ulaval.ift6003.interfaces.beans.compte;

import javax.faces.bean.ManagedProperty;

import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.domaine.modele.utilisateur.InfosBancaires;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

public abstract class CompteBean extends ControllerBean {

	@ManagedProperty(value = "#{utilisateurManagement}")
	protected UtilisateurManagement utilisateurManagement;
	protected Utilisateur utilisateurActif;

	public Utilisateur getUtilisateurActif() {
		return utilisateurActif;
	}

	public void setUtilisateurActif(Utilisateur utilisateurActif) {
		this.utilisateurActif = utilisateurActif;
	}

	public void setUtilisateurManagement(UtilisateurManagement utilisateurManagement) {
		this.utilisateurManagement = utilisateurManagement;
	}

	public InfosBancaires getInfosBancaires() {
		return utilisateurActif.getInfosBancaires();
	}

	public void setInfosBancaires(InfosBancaires infosBancaires) {
		utilisateurActif.setInfosBancaires(infosBancaires);
	}
}
