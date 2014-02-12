package ca.ulaval.ift6003.interfaces.beans.matchs;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import java.util.List;

@RequestScoped
@ManagedBean(name = "tousMatchsBean")
public class TousMatchsBean extends ControllerBean {

    @ManagedProperty(value = "#{inventaireApplication}")
    protected InventaireApplication inventaireApplication;

    public TousMatchsBean() {

    }

    public List<Match> getListeMatchs() {
        return inventaireApplication.getListeMatchs();
    }

    public void setInventaireApplication(InventaireApplication inventaireApplication) {
        this.inventaireApplication = inventaireApplication;
    }
}
