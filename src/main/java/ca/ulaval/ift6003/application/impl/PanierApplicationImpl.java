package ca.ulaval.ift6003.application.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ca.ulaval.ift6003.application.PanierApplication;
import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.DTOassembleurs.AssembleurService;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIQuantiteInsuffisante;
import ca.ulaval.ift6003.domaine.modele.exceptions.QuantiteInsuffisante;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletService;
import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.InventaireService;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;
import ca.ulaval.ift6003.domaine.modele.panieretachats.Panier;
import ca.ulaval.ift6003.domaine.modele.panieretachats.PanierService;

@SessionScoped
@ManagedBean(name = "panierApplication")
public class PanierApplicationImpl implements PanierApplication, Serializable {

	private static final long serialVersionUID = 1L;
	public static final String UNMATCH = "unmatch";
	public static final String SAISON = "saison";

	@ManagedProperty(value = "#{panierService}")
	private PanierService panierService;
	@ManagedProperty(value = "#{inventaireService}")
	private InventaireService inventaireService;
	@ManagedProperty(value = "#{utilisateurManagement}")
	protected UtilisateurManagement utilisateurManagement;
	@ManagedProperty(value = "#{billetService}")
	protected BilletService billetService;

	public PanierApplicationImpl() {
	}

	@Override
	public int nombreTotalDeBillets() {
		return getPanier().nombreBilletsTotal();
	}

	@Override
	public List<EntreeUnMatchDTO> listeEntreesUnMatchDeMonPanier() {
		Inventaire<EntreeUnMatch> inventaire = inventaireService.inventaireUnMatchPourBilletsDonnes(getPanier().getListeBilletsUnMatch());
		return AssembleurService.convertirInventaireUnMatchEnDTOs(inventaire, billetService.quantiteDispoParTypeBillet());
	}

	@Override
	public List<EntreeSaisonDTO> listeEntreesSaisonDeMonPanier() {
		Inventaire<EntreeSaison> inventaire = inventaireService.inventaireSaisonPourBilletsDonnes(getPanier().getListeBilletsSaison());
		return AssembleurService.convertirInventaireSaisonEnDTOs(inventaire, billetService.quantiteDispoParTypeBillet());
	}

	@Override
	public int getNombreMaximumDisponiblePourTypeBillet(String type) {
		return billetService.getNombreMaximumDisponiblePourTypeBillet(type);
	}

	@Override
	public void ajouterBilletsUnMatchAuPanier(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante {
		ajouterBillets(entreeChoisie, UNMATCH);
	}

	@Override
	public void ajouterBilletsSaisonAuPanier(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante {
		ajouterBillets(entreeChoisie, SAISON);
	}

	@Override
	public void changerQuantiteBilletSaisonDuPanier(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante {
		modifierQuantite(entreeChoisie, SAISON);
	}

	@Override
	public void changerQuantiteBilletUnMatchDuPanier(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante {
		modifierQuantite(entreeChoisie, UNMATCH);
	}

	@Override
	public void viderMonPanier() {
		getPanier().vider();
	}

	@Override
	public void retirerTousBilletsDeType(String type) {
		getPanier().retirerTousBilletsDeType(type);
	}

	@Override
	public double prixTotalDuPanier() {
		return getPanier().prixTotal();
	}

	@Override
	public void acheterInstantanement(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante {
		if (entreeChoisie.getNombreBilletsDesires() > entreeChoisie.getNombreBilletsDisponibles()) {
			throw new UIQuantiteInsuffisante();
		}
	}

	private void ajouterBillets(EntreeDTO entreeChoisie, String genre) throws UIQuantiteInsuffisante {
		try {
			int nbDesire = entreeChoisie.getNombreBilletsDesires();
			String type = entreeChoisie.getType();
			ajoutDuBonGenre(genre, nbDesire, type);
		} catch (QuantiteInsuffisante e) {
			throw new UIQuantiteInsuffisante();
		}
	}

	private void ajoutDuBonGenre(String genre, int nbDesire, String type) throws QuantiteInsuffisante {
		if (genre.equals(UNMATCH)) {
			panierService.ajouterBilletsUnMatchDansPanier(getPanier(), billetService.getBilletsUnMatchSelonType(type), nbDesire);
		} else {
			panierService.ajouterBilletsSaisonDansPanier(getPanier(), billetService.getBilletsSaisonSelonType(type), nbDesire);
		}
	}

	private void modifierQuantite(EntreeDTO entreeChoisie, String choix) throws UIQuantiteInsuffisante {
		Panier panier = getPanier();
		String type = entreeChoisie.getType();
		int nombreDeCeTypeDansPanier = panier.nombreDeBilletsDeType(type);
		int nombreDesire = entreeChoisie.getNombreBilletsDesires();

		if (nombreDesire < nombreDeCeTypeDansPanier) {
			panier.diminuerQuantiteDeType(nombreDesire, type);
		} else if (nombreDesire > nombreDeCeTypeDansPanier) {
			entreeChoisie.nombreBilletsDesires = nombreDesire - nombreDeCeTypeDansPanier;
			ajouterBillets(entreeChoisie, choix);
		}
	}

	private Panier getPanier() {
		return utilisateurManagement.getPanier();
	}

	public void setInventaireService(InventaireService inventaireService) {
		this.inventaireService = inventaireService;
	}

	public void setPanierService(PanierService panierService) {
		this.panierService = panierService;
	}

	public void setUtilisateurManagement(UtilisateurManagement utilisateurManagement) {
		this.utilisateurManagement = utilisateurManagement;
	}

	public void setBilletService(BilletService billetService) {
		this.billetService = billetService;
	}

	@Override
	public List<EntreeDTO> getToutesEntreesDuPanier() {
		List<EntreeSaisonDTO> dtosSaison = listeEntreesSaisonDeMonPanier();
		List<EntreeUnMatchDTO> dtosUnMatch = listeEntreesUnMatchDeMonPanier();

		List<EntreeDTO> entrees = new ArrayList<>();

		for (EntreeSaisonDTO dto : dtosSaison) {
			entrees.add(dto);
		}

		for (EntreeUnMatchDTO dto : dtosUnMatch) {
			entrees.add(dto);
		}

		return entrees;
	}
}
