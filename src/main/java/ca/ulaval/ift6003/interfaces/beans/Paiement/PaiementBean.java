package ca.ulaval.ift6003.interfaces.beans.Paiement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedProperty;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIQuantiteInsuffisante;
import ca.ulaval.ift6003.domaine.modele.utilisateur.InfosBancaires;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

public abstract class PaiementBean extends ControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	protected InfosBancaires infosBancaires;
	@ManagedProperty(value = "#{utilisateurManagement}")
	protected UtilisateurManagement utilisateurManagement;
	@ManagedProperty(value = "#{inventaireApplication}")
	protected InventaireApplication inventaireApplication;
	protected Utilisateur utilisateurActif;

	public String confirmerPaiement() {
		Map<String, Integer> nbDisposParTypes = getNombreDisponiblesParType();
		Map<String, Integer> nbDesiresParTypes = getNombreDesiresParType();

		try {
			validerQuantites(nbDisposParTypes, nbDesiresParTypes);
			int numTransaction = inventaireApplication.enregistrerTransaction(nbDesiresParTypes);
			placerNumTransactionDansSession(numTransaction);
			return "/secured-pages/confirmation-paiement?faces-redirect=true";
		} catch (UIQuantiteInsuffisante e) {
			placerMessageErreurDansSession(e.getMessage());
			return "/secured-pages/articles-non-disponibles?faces-redirect=true";
		}
	}

	public abstract Map<String, Integer> getNombreDesiresParType();

	public abstract double getPrixTotal();

	public abstract int getNombreArticles();

	public InfosBancaires getInfosBancaires() {
		return utilisateurActif.getInfosBancaires();
	}

	public void setInfosBancaires(InfosBancaires infosBancaires) {
		this.infosBancaires = infosBancaires;
	}

	public UtilisateurManagement getUtilisateurManagement() {
		return utilisateurManagement;
	}

	public void setUtilisateurManagement(UtilisateurManagement utilisateurManagement) {
		this.utilisateurManagement = utilisateurManagement;
	}

	private void placerNumTransactionDansSession(int numTransaction) {
		getSessionContext().put("numTransaction", numTransaction);
	}

	private void placerMessageErreurDansSession(String message) {
		getSessionContext().put("messageErreur", message);
	}

	public InventaireApplication getInventaireApplication() {
		return inventaireApplication;
	}

	public void setInventaireApplication(InventaireApplication inventaireApplication) {
		this.inventaireApplication = inventaireApplication;
	}

	private void validerQuantites(Map<String, Integer> nbDisposParType,
			Map<String, Integer> nbDesiresParTypes) throws UIQuantiteInsuffisante {
		for (Map.Entry<String, Integer> entry : nbDesiresParTypes.entrySet()) {
			String type = entry.getKey();
			int nombreDesireDeCeType = entry.getValue();
			if (!nbDisposParType.containsKey(type)
					|| nbDisposParType.get(type) < nombreDesireDeCeType) {
				int nombreRestant = !nbDisposParType.containsKey(type) ? 0 : nbDisposParType
						.get(type);
				throw new UIQuantiteInsuffisante("Billets de type : " + type
						+ " disponibles en quantité insuffisante. Seulement " + nombreRestant
						+ " restants.");
			}
		}
	}

	private Map<String, Integer> getNombreDisponiblesParType() {
		Map<String, Integer> nbDisposParType = new HashMap<>();
		Set<String> typesDesires = getNombreDesiresParType().keySet();
		for (String type : typesDesires) {
			nbDisposParType.put(type, inventaireApplication
					.getNombreMaximumDisponiblePourTypeBillet(type));
		}
		return nbDisposParType;
	}

}
