package ca.ulaval.ift6003.interfaces.beans.inventaire.unmatch;

import ca.ulaval.ift6003.application.frontiere.constantesEtEnumsUI.UIPermissions;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIQuantiteInsuffisante;
import ca.ulaval.ift6003.interfaces.beans.inventaire.InventaireBean;
import ca.ulaval.ift6003.interfaces.beans.inventaire.table.InventaireTable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;
import java.util.Map;

@ViewScoped
@ManagedBean(name = "inventaireUnMatchBean")
public class InventaireUnMatchBean extends InventaireBean<EntreeUnMatchDTO> implements Serializable {

    private static final long serialVersionUID = 1L;
    private InventaireUnMatchFiltreOptions filtreOptions;

    private boolean utiliserPreferences;

    public InventaireUnMatchBean() {
        filtreOptions = new InventaireUnMatchFiltreOptions();
        inventaireTable = new InventaireTable<>();
        utiliserPreferences = false;
    }

    @PostConstruct
    public void init() {
        inventaireTable = construireTable();
    }

    @Override
    public void ajouterAuPanier() throws UIQuantiteInsuffisante {
        panierApplication.ajouterBilletsUnMatchAuPanier(inventaireTable.getEntreeChoisie());
    }

    protected InventaireTable<EntreeUnMatchDTO> construireTable() {
        if (utiliserPreferences) {
            return new InventaireTable<>(inventaireApplication.produireInventaireBilletsUnMatchFiltre());
        } else {
            return new InventaireTable<>(inventaireApplication.produireInventaireBilletsUnMatch());
        }
    }

    public void toggleFiltre(boolean utiliserPreferences) {
        this.utiliserPreferences = utiliserPreferences;
        inventaireTable = construireTable();
    }

    public Map<String, Integer> getNbBilletsParMatchs() {
        return inventaireApplication.nombreDeBilletsTotalParMatchsAVenir();
    }

    public boolean doitAfficherFiltreParPreferences() {
        return utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.APPLIQUER_PREFERENCES_BILLETS);
    }

    public InventaireUnMatchFiltreOptions getFiltreOptions() {
        return filtreOptions;
    }

    public void setFiltreOptions(InventaireUnMatchFiltreOptions filtreOptions) {
        this.filtreOptions = filtreOptions;
    }

}
