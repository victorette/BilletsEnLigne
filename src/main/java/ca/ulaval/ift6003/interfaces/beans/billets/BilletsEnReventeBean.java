package ca.ulaval.ift6003.interfaces.beans.billets;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

@RequestScoped
@ManagedBean(name = "billetsEnReventeBean")
public class BilletsEnReventeBean extends ControllerBean {

	@ManagedProperty(value = "#{inventaireApplication}")
	protected InventaireApplication inventaireApplication;
	@ManagedProperty(value = "#{utilisateurManagement}")
	protected UtilisateurManagement utilisateurManagement;

	private List<BilletUnMatch> billetsUnMatch;
	private List<BilletSaison> billetsSaison;

	public BilletsEnReventeBean() {
		billetsSaison = new ArrayList<BilletSaison>();
		billetsUnMatch = new ArrayList<BilletUnMatch>();
	}

	@PostConstruct
	public void init() {
		billetsUnMatch = inventaireApplication.getBilletsUnMatchEnReventeParLUtilisateur();
		billetsSaison = inventaireApplication.getBilletsSaisonEnReventeParLUtilisateur();
	}

	public List<BilletUnMatch> getListeBilletsUnMatch() {
		return billetsUnMatch;
	}

	public List<BilletSaison> getListeBilletsSaison() {
		return billetsSaison;
	}

	public void enregistrerModifications() {
		if (!billetsUnMatch.isEmpty()) {
			try {
				inventaireApplication.modifierBilletsUnMatchEnRevente(billetsUnMatch);
			} catch (UIEntiteInexistante e) {
				e.printStackTrace();
			}
		}
		if (!billetsSaison.isEmpty()) {
			try {
				inventaireApplication.modifierBilletsSaisonEnRevente(billetsSaison);
			} catch (UIEntiteInexistante e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void supprimerBilletSaison(String billetID) {
		try {
			inventaireApplication.retirerBilletSaisonParDesReventesID(billetID);
			guiMessageHandler.addMessage("Supression réussie!", "Le billet Saison " + billetID + " n'existe plus");
		} catch (UIEntiteInexistante e) {
			guiMessageHandler.addMessage("Supression échoue!", e.getMessage());
		}
	}

	public void supprimerBilletUnMatch(String billetID) {
		try {
			inventaireApplication.retirerBilletUnMatchDesReventesParID(billetID);
			guiMessageHandler.addMessage("Supression réussie!", "Le billet UnMatch" + billetID + " n'existe plus");
		} catch (UIEntiteInexistante e) {
			guiMessageHandler.addMessage("Supression échoue!", e.getMessage());
		}
	}

	public void setInventaireApplication(InventaireApplication inventaireApplication) {
		this.inventaireApplication = inventaireApplication;
	}

	public void setUtilisateurManagement(UtilisateurManagement utilisateurManagement) {
		this.utilisateurManagement = utilisateurManagement;
	}

	public List<BilletUnMatch> getBilletsUnMatch() {
		return billetsUnMatch;
	}

	public void setBilletsUnMatch(List<BilletUnMatch> billetsUnMatch) {
		this.billetsUnMatch = billetsUnMatch;
	}

	public List<BilletSaison> getBilletsSaison() {
		return billetsSaison;
	}

	public void setBilletsSaison(List<BilletSaison> billetsSaison) {
		this.billetsSaison = billetsSaison;
	}
}
