package ca.ulaval.ift6003.interfaces.beans.inventaire;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.application.PanierApplication;
import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.constantesEtEnumsUI.UIPermissions;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIQuantiteInsuffisante;
import ca.ulaval.ift6003.interfaces.beans.inventaire.table.InventaireTable;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

public abstract class InventaireBean<DTOTYPE extends EntreeDTO> extends ControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{inventaireApplication}")
	protected InventaireApplication inventaireApplication;
	@ManagedProperty(value = "#{utilisateurManagement}")
	protected UtilisateurManagement utilisateurManagement;
	@ManagedProperty(value = "#{panierApplication}")
	protected PanierApplication panierApplication;
	protected InventaireTable<DTOTYPE> inventaireTable;

	public void ajouterBilletAuPanier() {
		try {
			ajouterAuPanier();
			guiMessageHandler.addMessage("Succes", "Ajouté(s) au panier!");
		} catch (UIQuantiteInsuffisante e) {
			guiMessageHandler.addMessage("Achat impossible !", e.getMessage());
		}
	}

	public String acheterInstantanement() {
		try {
			EntreeDTO selected = inventaireTable.getEntreeChoisie();
			panierApplication.acheterInstantanement(selected);
			placerBilletDansSession(selected);
			return "/secured-pages/inscrits-admin/paiement-instant.xhtml?faces-redirect=true";
		} catch (UIQuantiteInsuffisante e) {
			guiMessageHandler.addMessage("Achat impossible !", e.getMessage());
			return null;
		}
	}

	public abstract void ajouterAuPanier() throws UIQuantiteInsuffisante;

	public boolean doitAfficherSommaireBillet() {
		return inventaireTable.uneEntreeEstChoisie();
	}

	public boolean doitAfficherImageStade() {
		EntreeDTO entreeChoisie = inventaireTable.getEntreeChoisie();
		return doitAfficherSommaireBillet() && estStadeUlaval(entreeChoisie) && aUneSection(entreeChoisie);
	}

	public String getImageSection() {
		EntreeDTO entreeChoisie = inventaireTable.getEntreeChoisie();
		return "stade" + entreeChoisie.getNomSection() + ".png";
	}

	public boolean doitAfficherAchatBillet() {
		return doitAfficherSommaireBillet() && utilisateurManagement.utilisateurActifALeDroitDe(UIPermissions.ACHAT_BILLET);
	}

	public InventaireTable<DTOTYPE> getInventaireTable() {
		return inventaireTable;
	}

	public void setInventaireTable(InventaireTable<DTOTYPE> inventaireTable) {
		this.inventaireTable = inventaireTable;
	}

	public void setInventaireApplication(InventaireApplication inventaireApplication) {
		this.inventaireApplication = inventaireApplication;
	}

	public void setPanierApplication(PanierApplication panierApplication) {
		this.panierApplication = panierApplication;
	}

	public void setUtilisateurManagement(UtilisateurManagement utilisateurManagement) {
		this.utilisateurManagement = utilisateurManagement;
	}

	private void placerBilletDansSession(EntreeDTO selected) {
		getSessionContext().put("entreeChoisie", selected);
	}

	private boolean estStadeUlaval(EntreeDTO entree) {
		return entree.getNomCentreSportif().equals("Stade ULAVAL");
	}

	private boolean aUneSection(EntreeDTO entree) {
		return entree.getNomSection() != null && !entree.getNomSection().equals("---");
	}
}
