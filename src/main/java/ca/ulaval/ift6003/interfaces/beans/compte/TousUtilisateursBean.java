package ca.ulaval.ift6003.interfaces.beans.compte;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

@RequestScoped
@ManagedBean(name = "tousUtilisateursBean")
public class TousUtilisateursBean extends ControllerBean {

	@ManagedProperty(value = "#{utilisateurManagement}")
	private UtilisateurManagement utilisateurManagement;

	public TousUtilisateursBean() {

	}

	public List<Utilisateur> getListeUtilisateurs() {
		return utilisateurManagement.getListeUtilisateurs();
	}

	public void setUtilisateurManagement(UtilisateurManagement utilisateurManagement) {
		this.utilisateurManagement = utilisateurManagement;
	}
}
