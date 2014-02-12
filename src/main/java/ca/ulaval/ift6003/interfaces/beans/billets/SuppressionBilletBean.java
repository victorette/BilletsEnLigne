package ca.ulaval.ift6003.interfaces.beans.billets;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.interfaces.beans.adminonly.AdminControllerBean;

@RequestScoped
@ManagedBean(name = "suppressionBilletBean")
public class SuppressionBilletBean extends AdminControllerBean {

	public SuppressionBilletBean() {

	}

	public void supprimerBilletUnMatch(String billetID) {
		try {
			adminApplication.effacerBilletUnMatchParID(billetID);
			guiMessageHandler.addMessage("Supression réussie!", "Le billet " + billetID + " n'existe plus");
		} catch (UIDroitsInsuffisants | UIEntiteInexistante e) {
			guiMessageHandler.addMessage("Supression échoue!", e.getMessage());
		}
	}

	public void supprimerBilletSaison(String billetID) {
		try {
			adminApplication.effacerBilletSaisonParID(billetID);
			guiMessageHandler.addMessage("Supression réussie!", "Le billet " + billetID + " n'existe plus");
		} catch (UIDroitsInsuffisants | UIEntiteInexistante e) {
			guiMessageHandler.addMessage("Supression échoue!", e.getMessage());
		}
	}
}
