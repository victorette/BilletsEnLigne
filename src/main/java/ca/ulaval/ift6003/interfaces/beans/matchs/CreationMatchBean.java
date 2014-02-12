package ca.ulaval.ift6003.interfaces.beans.matchs;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.interfaces.beans.adminonly.AdminControllerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import java.io.Serializable;
import java.util.List;

@RequestScoped
@ManagedBean(name = "creationMatchBean")
public class CreationMatchBean extends AdminControllerBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManagedProperty(value = "#{inventaireApplication}")
    protected InventaireApplication inventaireApplication;

    private String nomCentreSportif;
    private Match match;

    public CreationMatchBean() {
        initialiserMatch();
    }

    public void creerMatchClicked() {
        try {
            adminApplication.ajouterNouveauMatch(match);
            guiMessageHandler.addMessage("Création de match réussie", "Le match a été enregistré");
            initialiserMatch();
        } catch (UIDroitsInsuffisants e) {
            guiMessageHandler.addMessage("Création de match échouée", e.getMessage());
        }
    }

    private void initialiserMatch() {
        this.match = new Match();
    }

    public String annulerCreationMatchClicked() {
        return "creation-match-annulee";
    }

    public List<String> getListeNomsCentresSportifs() {
        return inventaireApplication.getListeNomsCentresSportifs();
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getNomCentreSportif() {
        return nomCentreSportif;
    }

    public void setNomCentreSportif(String nomCentreSportif) {
        this.nomCentreSportif = nomCentreSportif;
    }

    public void setInventaireApplication(InventaireApplication inventaireApplication) {
        this.inventaireApplication = inventaireApplication;
    }
}
