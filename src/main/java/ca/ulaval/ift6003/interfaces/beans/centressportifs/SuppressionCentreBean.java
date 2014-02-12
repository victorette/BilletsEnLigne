package ca.ulaval.ift6003.interfaces.beans.centressportifs;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.interfaces.beans.adminonly.AdminControllerBean;

@RequestScoped
@ManagedBean(name = "suppressionCentreSportifBean")
public class SuppressionCentreBean extends AdminControllerBean {

	public SuppressionCentreBean() {

	}

	public void supprimerCentreSportif(String nomCentre) {
		try {
			adminApplication.effacerCentreSportifParNom(nomCentre);
			guiMessageHandler.addMessage("Supression réussie!", "Le centre " + nomCentre + " n'existe plus");
		} catch (UIDroitsInsuffisants | UIEntiteInexistante e) {
			guiMessageHandler.addMessage("Supression échoue!", e.getMessage());
		}
	}

}
