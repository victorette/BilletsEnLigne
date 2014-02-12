package ca.ulaval.ift6003.interfaces.beans.Paiement;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.PanierApplication;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIQuantiteInsuffisante;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

@RequestScoped
@ManagedBean(name = "panierBean")
public class PanierBean extends ControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String UNMATCH = "unmatch";
	public static final String SAISON = "saison";

	@ManagedProperty(value = "#{panierApplication}")
	private PanierApplication panierApplication;

	public List<EntreeUnMatchDTO> getListeBilletsUnMatch() {
		return panierApplication.listeEntreesUnMatchDeMonPanier();
	}

	public List<EntreeSaisonDTO> getListeBilletsSaison() {
		return panierApplication.listeEntreesSaisonDeMonPanier();
	}

	public double getPrixTotalDuPanier() {
		return panierApplication.prixTotalDuPanier();
	}

	public void viderPanier() {
		panierApplication.viderMonPanier();
	}

	public void changerQuantiteBilletUnMatch(EntreeDTO dto) {
		modifierQuantite(dto, UNMATCH);
	}

	public void changerQuantiteBilletSaison(EntreeSaisonDTO dto) {
		modifierQuantite(dto, SAISON);
	}

	private void modifierQuantite(EntreeDTO dto, String type) {
		if (dto.getNombreBilletsDesires() < 0) {
			guiMessageHandler.addMessage("Erreur!", "Vous devez entrer un nombre positif.");
			return;
		}
		if (dto.getNombreBilletsDesires() == 0) {
			retirerBillet(dto);
			return;
		}
		try {
			if (type.equals(UNMATCH)) {
				panierApplication.changerQuantiteBilletUnMatchDuPanier(dto);
			} else {
				panierApplication.changerQuantiteBilletSaisonDuPanier(dto);
			}
			guiMessageHandler.addMessage("Succes!", "Quantité changée");
		} catch (UIQuantiteInsuffisante e) {
			int nbDispo = panierApplication.getNombreMaximumDisponiblePourTypeBillet(dto.type);
			guiMessageHandler.addMessage("Erreur", e.getMessage() + " seulement " + nbDispo
					+ " disponibles!");
		}
	}

	public void retirerBillet(EntreeDTO dto) {
		panierApplication.retirerTousBilletsDeType(dto.type);
	}

	public void setPanierApplication(PanierApplication panierApplication) {
		this.panierApplication = panierApplication;
	}
}
