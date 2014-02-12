package ca.ulaval.ift6003.interfaces.beans.inventaire.saison;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIQuantiteInsuffisante;
import ca.ulaval.ift6003.interfaces.beans.inventaire.InventaireBean;
import ca.ulaval.ift6003.interfaces.beans.inventaire.table.InventaireTable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import java.io.Serializable;

@ViewScoped
@ManagedBean(name = "inventaireSaisonBean")
public class InventaireSaisonBean extends InventaireBean<EntreeSaisonDTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private InventaireSaisonFiltreOptions filtreOptions;

	public InventaireSaisonBean() {
		filtreOptions = new InventaireSaisonFiltreOptions();
		inventaireTable = new InventaireTable<>();
	}

	@PostConstruct
	public void init() {
		inventaireTable = construireTable();
	}

	@Override
	public void ajouterAuPanier() throws UIQuantiteInsuffisante {
		panierApplication.ajouterBilletsSaisonAuPanier(inventaireTable.getEntreeChoisie());
	}

	protected InventaireTable<EntreeSaisonDTO> construireTable() {
		return new InventaireTable<>(inventaireApplication.produireInventaireBilletsSaison());
	}

    public InventaireSaisonFiltreOptions getFiltreOptions() {
        return filtreOptions;
    }

    public void setFiltreOptions(InventaireSaisonFiltreOptions filtreOptions) {
        this.filtreOptions = filtreOptions;
    }
}
