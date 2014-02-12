package ca.ulaval.ift6003.interfaces.beans.centressportifs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIIdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.interfaces.beans.adminonly.AdminControllerBean;

@RequestScoped
@ManagedBean(name = "creationCentreSportifBean")
public class CreationCentreSportifBean extends AdminControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private CentreSportif centreSportif;
	private String sectionsString;

	public CreationCentreSportifBean() {
		initialiserCentreSportif();
	}

	public void creerCentreSportifClicked() {
		ajouterSectionsAuCentreSportif();

		try {
			adminApplication.ajouterNouveauCentreSportif(centreSportif);
			guiMessageHandler.addMessage("Création de nomCentreSportif réussie", "Le nomCentreSportif a été enregistré");
			initialiserCentreSportif();
		} catch (UIDroitsInsuffisants | UIIdentifiantDejaExistant e) {
			guiMessageHandler.addMessage("Création de nomCentreSportif échouée", e.getMessage());
		}
	}

	private void initialiserCentreSportif() {
		this.centreSportif = new CentreSportif();
	}

	private void ajouterSectionsAuCentreSportif() {
		String lines[] = sectionsString.split("\\r?\\n");
		List<String> sections = new ArrayList<>();
		for (String line : lines) {
			if (line != null && !line.equals("")) {
				sections.add(line);
			}
		}
		centreSportif.setSections(sections);

	}

	public String annulerCreationCentreSportifClicked() {
		return "creation-nomCentreSportif-annulee";
	}

	public CentreSportif getCentreSportif() {
		return centreSportif;
	}

	public void setCentreSportif(CentreSportif centreSportif) {
		this.centreSportif = centreSportif;
	}

	public String getSectionsString() {
		return sectionsString;
	}

	public void setSectionsString(String sectionsString) {
		this.sectionsString = sectionsString;
	}

}
