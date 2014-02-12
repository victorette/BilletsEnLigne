package ca.ulaval.ift6003.interfaces.beans.billets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.interfaces.beans.adminonly.AdminControllerBean;

@ViewScoped
@ManagedBean(name = "creationBilletSaisonBean")
public class CreationBilletSaisonBean extends AdminControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{inventaireApplication}")
	protected InventaireApplication inventaireApplication;

	private BilletSaison billet;
	private int nombreBillets;

	@PostConstruct
	public void init() {
		this.nombreBillets = 1;
		this.billet = new BilletSaison();
	}

	public void creerBilletClicked() {
		try {
			creerBilletSelonChoix();
			guiMessageHandler.addMessage("Création de billet réussie", "Le billet a été enregistré");
		} catch (UIDroitsInsuffisants e) {
			guiMessageHandler.addMessage("Création de billet échouée", e.getMessage());
		}

		this.billet = new BilletSaison();
	}

	public void revendreBilletClicked() {
		try {
			revendreBilletSelonChoix();
			guiMessageHandler.addMessage("Mise en revente du billet réussie", "Le billet a été enregistré");
		} catch (UIDroitsInsuffisants e) {
			guiMessageHandler.addMessage("Mise en revente du billet échouée", e.getMessage());
		}
		this.billet = new BilletSaison();
	}

	private void revendreBilletSelonChoix() throws UIDroitsInsuffisants {
		if (billet.getCategorie_siege().equals(Consts.SIEGE_GENERAL)) {
			adminApplication.revendrePlusieursBilletsSaison(billet, nombreBillets);
		} else {
			adminApplication.revendreBilletSaison(billet);
		}
	}

	public String annulerCreationBilletClicked() {
		return "creation-billet-annulee";
	}

	public BilletSaison getBillet() {
		return billet;
	}

	public void setBillet(BilletSaison billet) {
		this.billet = billet;
	}

	public List<String> getListeNomsSections() {
		String nomCentre = billet.getNomCentreSportif();
		if (nomCentre == null || nomCentre.equals("")) {
			return new ArrayList<>();
		} else {
			return inventaireApplication.getSectionsCentreSportif(billet.getNomCentreSportif());
		}
	}

	public List<String> getListeNomsCentresSportifs() {
		return inventaireApplication.getListeNomsCentresSportifs();
	}

	public int getNombreBillets() {
		return nombreBillets;
	}

	public void setNombreBillets(int nombreBillets) {
		this.nombreBillets = nombreBillets;
	}

	private void creerBilletSelonChoix() throws UIDroitsInsuffisants {
		if (billet.getCategorie_siege().equals("general")) {
			adminApplication.ajouterPlusieursNouveauxBilletsSaison(billet, nombreBillets);
		} else {
			adminApplication.ajouterNouveauBilletSaison(billet);
		}
	}

	public void setInventaireApplication(InventaireApplication inventaireApplication) {
		this.inventaireApplication = inventaireApplication;
	}
}
