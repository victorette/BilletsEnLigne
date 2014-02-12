package ca.ulaval.ift6003.interfaces.beans.matchs;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.interfaces.beans.adminonly.AdminControllerBean;

@RequestScoped
@ManagedBean(name = "suppressionMatchBean")
public class SuppressionMatchBean extends AdminControllerBean {

	public void supprimerMatch(String MatchID) {
		try {
			adminApplication.effacerMatchParID(MatchID);
			guiMessageHandler.addMessage("Supression réussie!", "Le match " + MatchID + " n'existe plus");
			guiMessageHandler.addMessage("Supression réussie!", "Les billets liés au match " + MatchID + " n'existent plus");
		} catch (UIDroitsInsuffisants | UIEntiteInexistante e) {
			guiMessageHandler.addMessage("Supression échoue!", e.getMessage());
		}

	}

}
