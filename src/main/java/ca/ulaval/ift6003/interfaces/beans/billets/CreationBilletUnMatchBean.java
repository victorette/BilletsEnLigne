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
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.interfaces.beans.adminonly.AdminControllerBean;

@ViewScoped
@ManagedBean(name = "creationBilletUnMatchBean")
public class CreationBilletUnMatchBean extends AdminControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{inventaireApplication}")
	protected InventaireApplication inventaireApplication;

	private BilletUnMatch billet;
	private int nombreBillets;

	public CreationBilletUnMatchBean() {

	}

	@PostConstruct
	public void init() {
		this.nombreBillets = 1;
		this.billet = new BilletUnMatch();
	}

	public void creerBilletClicked() {
		try {
			creerBilletSelonChoix();
			guiMessageHandler.addMessage("Création de billet réussie", "Le billet a été enregistré");
		} catch (UIDroitsInsuffisants e) {
			guiMessageHandler.addMessage("Création de billet échouée", e.getMessage());
		}
		this.billet = new BilletUnMatch();
	}

	public void revendreBilletClicked() {
		try {
			revendreBilletSelonChoix();
			guiMessageHandler.addMessage("Mise en revente du billet réussie", "Le billet a été enregistré");
		} catch (UIDroitsInsuffisants e) {
			guiMessageHandler.addMessage("Mise en revente du billet échouée", e.getMessage());
		}
		this.billet = new BilletUnMatch();
	}

	public String annulerCreationBilletClicked() {
		return "creation-billet-match-annulee";
	}

	public BilletUnMatch getBillet() {
		return billet;
	}

	public void setBillet(BilletUnMatch billet) {
		this.billet = billet;
	}

	public List<String> getListeNomsSections() {
		if (billet.getMatch_id() == null) {
			return new ArrayList<>();
		}
		try {
			return inventaireApplication.getSectionsLieesSelonMatchId(billet.getMatch_id());
		} catch (UIEntiteInexistante e) {
			return new ArrayList<>();
		}
	}

	public List<String> getListeMatchsIds() {
		return inventaireApplication.getListeIdMatchs();
	}

	public int getNombreBillets() {
		return nombreBillets;
	}

	public void setNombreBillets(int nombreBillets) {
		this.nombreBillets = nombreBillets;
	}

	private void creerBilletSelonChoix() throws UIDroitsInsuffisants {
		if (billet.getCategorie_siege().equals(Consts.SIEGE_GENERAL)) {
			adminApplication.ajouterPlusieursNouveauxBilletsUnMatch(billet, nombreBillets);
		} else {
			adminApplication.ajouterNouveauBilletUnMatch(billet);
		}
	}

	private void revendreBilletSelonChoix() throws UIDroitsInsuffisants {
		if (billet.getCategorie_siege().equals(Consts.SIEGE_GENERAL)) {
			adminApplication.revendrePlusieursBilletsUnMatch(billet, nombreBillets);
		} else {
			adminApplication.revendreBilletUnMatch(billet);
		}
	}

	public void setInventaireApplication(InventaireApplication inventaireApplication) {
		this.inventaireApplication = inventaireApplication;
	}
}