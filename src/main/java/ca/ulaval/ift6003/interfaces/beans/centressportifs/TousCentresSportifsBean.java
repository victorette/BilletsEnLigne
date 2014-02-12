package ca.ulaval.ift6003.interfaces.beans.centressportifs;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import java.util.List;

@RequestScoped
@ManagedBean(name = "tousCentresSportifsBean")
public class TousCentresSportifsBean extends ControllerBean {

    @ManagedProperty(value = "#{inventaireApplication}")
    protected InventaireApplication inventaireApplication;

    public TousCentresSportifsBean() {

    }

    public List<CentreSportif> getListeCentresSportifs() {
        return inventaireApplication.getListeCentresSportifs();
    }

    public void setInventaireApplication(InventaireApplication inventaireApplication) {
        this.inventaireApplication = inventaireApplication;
    }
}
