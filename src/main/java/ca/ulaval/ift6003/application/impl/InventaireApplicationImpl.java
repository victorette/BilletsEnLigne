package ca.ulaval.ift6003.application.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.DTOassembleurs.AssembleurService;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.inventaire.Billet;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaisonRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletService;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatchRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifService;
import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.InventaireService;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.domaine.modele.inventaire.MatchRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.MatchService;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;
import ca.ulaval.ift6003.domaine.modele.panieretachats.TransactionService;

@SessionScoped
@ManagedBean(name = "inventaireApplication")
public class InventaireApplicationImpl implements InventaireApplication {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{inventaireService}")
	private InventaireService inventaireService;
	@ManagedProperty(value = "#{billetService}")
	protected BilletService billetService;
	@ManagedProperty(value = "#{matchService}")
	protected MatchService matchService;
	@ManagedProperty(value = "#{transactionService}")
	protected TransactionService transactionService;
	@ManagedProperty(value = "#{centreSportifService}")
	protected CentreSportifService centreSportifService;
	@ManagedProperty(value = "#{matchRepository}")
	protected MatchRepository matchRepository;
	@ManagedProperty(value = "#{billetUnMatchRepository}")
	protected BilletUnMatchRepository billetUnMatchRepository;
	@ManagedProperty(value = "#{billetSaisonRepository}")
	protected BilletSaisonRepository billetSaisonRepository;
	@ManagedProperty(value = "#{centreSportifRepository}")
	protected CentreSportifRepository centreSportifRepository;
	@ManagedProperty(value = "#{utilisateurManagement}")
	protected UtilisateurManagement utilisateurManagement;

	@Override
	public List<EntreeUnMatchDTO> produireInventaireBilletsUnMatch() {
		return AssembleurService.convertirInventaireUnMatchEnDTOs(inventaireService.inventaireBilletsMatchsFutur(), billetService.quantiteDispoParTypeBillet());
	}

	@Override
	public List<EntreeUnMatchDTO> produireInventaireBilletsUnMatchFiltre() {
		Inventaire<EntreeUnMatch> inventaireFiltre = inventaireService.inventaireBilletsMatchsFutursFiltre(utilisateurManagement.preferencesUtilisateurs());
		return AssembleurService.convertirInventaireUnMatchEnDTOs(inventaireFiltre, billetService.quantiteDispoParTypeBillet());
	}

	@Override
	public List<EntreeSaisonDTO> produireInventaireBilletsSaison() {
		return AssembleurService.convertirInventaireSaisonEnDTOs(inventaireService.inventaireBilletsSaison(), billetService.quantiteDispoParTypeBillet());
	}

	@Override
	public List<String> getListeIdMatchs() {
		return matchService.getListeIdMatchs();
	}

	@Override
	public List<String> getListeNomsCentresSportifs() {
		return centreSportifService.getListeNomsCentresSportifs();
	}

	@Override
	public List<BilletUnMatch> getListeBilletsUnMatch() {
		return billetUnMatchRepository.selectTous();
	}

	@Override
	public List<BilletSaison> getListeBilletsSaison() {
		return billetSaisonRepository.selectTous();
	}

	@Override
	public List<BilletUnMatch> getBilletsUnMatchEnReventeParLUtilisateur() {
		return conserverCeuxDeLUtilisateurSeulement(getListeBilletsUnMatch());
	}

	@Override
	public List<BilletSaison> getBilletsSaisonEnReventeParLUtilisateur() {
		return conserverCeuxDeLUtilisateurSeulement(getListeBilletsSaison());
	}

	private <T extends Billet> List<T> conserverCeuxDeLUtilisateurSeulement(List<T> billets) {
		// billets mis en revente par l'utilisateur
		Set<String> enReventeParUtilisateur = utilisateurManagement.getBilletsEnReventeParUtilisateur();
		List<T> aConserver = new ArrayList<T>();

		for (T billet : billets) {
			if (enReventeParUtilisateur.contains(billet.getBilletId())) {
				aConserver.add(billet);
			}
		}

		return aConserver;
	}

	@Override
	public List<CentreSportif> getListeCentresSportifs() {
		return centreSportifRepository.selectTous();
	}

	@Override
	public List<Match> getListeMatchs() {
		return matchRepository.selectTous();
	}

	@Override
	public List<String> getSectionsLieesSelonMatchId(String match_id) throws UIEntiteInexistante {
		try {
			return matchService.sectionsDuCentreSportifLieAUnMatch(match_id);
		} catch (EntiteNonTrouvee e) {
			throw new UIEntiteInexistante();
		}
	}

	@Override
	public Map<String, Integer> nombreDeBilletsTotalParMatchsAVenir() {
		Map<String, Match> mapMatchs = matchService.getMapMatchsAVenir();
		return billetService.compterNombreBilletsPourChaqueMatchID(mapMatchs.keySet());
	}

	@Override
	public int enregistrerTransaction(Map<String, Integer> nbDesiresParTypes) {
		int numTransaction = transactionService.getTransactionID();

		// do nothing with them for now, eventually store them in transactions
		// file
		List<BilletUnMatch> billetsUnMatch = billetService.retirerBilletsUnMatchDeLInventaire(nbDesiresParTypes);
		List<BilletSaison> billetsSaison = billetService.retirerBilletsSaisonDeLInventaire(nbDesiresParTypes);

		utilisateurManagement.envoyerCourrielConfirmation(numTransaction);
		return numTransaction;
	}

	@Override
	public int getNombreMaximumDisponiblePourTypeBillet(String type) {
		return billetService.getNombreMaximumDisponiblePourTypeBillet(type);
	}

	@Override
	public List<String> getSectionsCentreSportif(String centreNom) {
		return centreSportifService.getSectionsCentreSportif(centreNom);
	}

	public void setInventaireService(InventaireService inventaireService) {
		this.inventaireService = inventaireService;
	}

	public void setBilletSaisonRepository(BilletSaisonRepository billetSaisonRepository) {
		this.billetSaisonRepository = billetSaisonRepository;
	}

	public void setBilletService(BilletService billetService) {
		this.billetService = billetService;
	}

	public void setBilletUnMatchRepository(BilletUnMatchRepository billetUnMatchRepository) {
		this.billetUnMatchRepository = billetUnMatchRepository;
	}

	public void setCentreSportifRepository(CentreSportifRepository centreSportifRepository) {
		this.centreSportifRepository = centreSportifRepository;
	}

	public void setCentreSportifService(CentreSportifService centreSportifService) {
		this.centreSportifService = centreSportifService;
	}

	public void setMatchRepository(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}

	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}

	public void setUtilisateurManagement(UtilisateurManagement utilisateurManagement) {
		this.utilisateurManagement = utilisateurManagement;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public TransactionService getTransactionService() {
		return this.transactionService;
	}

	@Override
	public void retirerBilletSaisonParDesReventesID(String billetID) throws UIEntiteInexistante {
		try {
			billetSaisonRepository.supprimerParIdentifiantUnique(billetID);
		} catch (EntiteNonTrouvee e) {
			throw new UIEntiteInexistante();
		}
		utilisateurManagement.retirerDesReventesDeLUtilisateur(billetID);
	}

	@Override
	public void retirerBilletUnMatchDesReventesParID(String billetID) throws UIEntiteInexistante {
		try {
			billetUnMatchRepository.supprimerParIdentifiantUnique(billetID);
		} catch (EntiteNonTrouvee e) {
			throw new UIEntiteInexistante();
		}
		utilisateurManagement.retirerDesReventesDeLUtilisateur(billetID);
	}

	@Override
	public void modifierBilletsUnMatchEnRevente(List<BilletUnMatch> billetsUnMatch) throws UIEntiteInexistante {
		try {
			for (BilletUnMatch billet : billetsUnMatch) {
				billetUnMatchRepository.update(billet);
			}
		} catch (EntiteNonTrouvee e) {
			throw new UIEntiteInexistante();
		}
	}

	@Override
	public void modifierBilletsSaisonEnRevente(List<BilletSaison> billetsSaison) throws UIEntiteInexistante {
		try {
			for (BilletSaison billet : billetsSaison) {
				billetSaisonRepository.update(billet);
			}
		} catch (EntiteNonTrouvee e) {
			throw new UIEntiteInexistante();
		}
	}

}
