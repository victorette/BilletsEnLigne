package ca.ulaval.ift6003.interfaces.beans.billets;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import java.util.List;

@RequestScoped
@ManagedBean(name = "tousBilletsBean")
public class TousBilletsBean extends ControllerBean {

    @ManagedProperty(value = "#{inventaireApplication}")
    protected InventaireApplication inventaireApplication;

    public TousBilletsBean() {

    }

    public List<BilletUnMatch> getListeBilletsUnMatch() {
        return inventaireApplication.getListeBilletsUnMatch();
    }

    public List<BilletSaison> getListeBilletsSaison() {
        return inventaireApplication.getListeBilletsSaison();
    }

    public void setInventaireApplication(InventaireApplication inventaireApplication) {
        this.inventaireApplication = inventaireApplication;
    }
}
