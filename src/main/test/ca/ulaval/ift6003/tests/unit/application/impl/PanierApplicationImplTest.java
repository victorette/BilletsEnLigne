package ca.ulaval.ift6003.tests.unit.application.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIQuantiteInsuffisante;
import ca.ulaval.ift6003.application.impl.PanierApplicationImpl;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;
import ca.ulaval.ift6003.domaine.modele.panieretachats.Panier;
import ca.ulaval.ift6003.infrastructure.services.BilletServiceImpl;
import ca.ulaval.ift6003.infrastructure.services.InventaireServiceImpl;
import ca.ulaval.ift6003.infrastructure.services.PanierServiceImpl;

public class PanierApplicationImplTest {

	private PanierApplicationImpl panierApplication;

	private UtilisateurManagement mockUtilisateurManagement;
	private BilletServiceImpl mockBilletService;
	private PanierServiceImpl mockPanierService;
	private InventaireServiceImpl mockInventaireService;

	private Panier mockPanier;
	private EntreeSaisonDTO mockEntreeSaisonDTO;

	private final String TYPE = "type";
	private final List<BilletUnMatch> BILLETS_UN_MATCH = new ArrayList<>();
	private final List<BilletSaison> BILLETS_SAISON = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		panierApplication = new PanierApplicationImpl();

		mockUtilisateurManagement = mock(UtilisateurManagement.class);
		mockInventaireService = mock(InventaireServiceImpl.class);
		mockPanierService = mock(PanierServiceImpl.class);
		mockBilletService = mock(BilletServiceImpl.class);
		mockPanier = mock(Panier.class);

		mockEntreeSaisonDTO = mock(EntreeSaisonDTO.class);

		panierApplication.setUtilisateurManagement(mockUtilisateurManagement);
		panierApplication.setInventaireService(mockInventaireService);
		panierApplication.setPanierService(mockPanierService);
		panierApplication.setBilletService(mockBilletService);
	}

	@Test
	public void quandOnDemandeListeEntreesUnMatchDeMonPanierAlorsLinventaireEstDemande() {
		preparerListeEntreeUnMatch();

		panierApplication.listeEntreesUnMatchDeMonPanier();

		verifierLinventaireUnMatch();
		verifierLaQuantiteDispo();
	}

	@Test
	public void quandOnDemandeListeEntreesSaisonDeMonPanierAlorsLinventaireEstDemandeAvecLaBonneQuantite() {
		preparerListeEntreeSaison();

		panierApplication.listeEntreesSaisonDeMonPanier();

		verifierLinventaireSaison();
		verifierLaQuantiteDispo();
	}

	@Test
	public void quandJeDemandeLeNombreMaximumDisponiblePourTypeBilletAlorsLeServiceEstAppeleEtMeRetourneLeNombre() {
		panierApplication.getNombreMaximumDisponiblePourTypeBillet(TYPE);
		verify(mockBilletService).getNombreMaximumDisponiblePourTypeBillet(TYPE);
	}

	@Test
	public void quandDemandeNombreTotalBilletsAlorsDemandePanierEtDemandeLeNombre() {
		preparerPanier();

		panierApplication.nombreTotalDeBillets();

		verifierPanier();
		verify(mockPanier).nombreBilletsTotal();
	}

	@Test
	public void quandJeDemandeDeViderMonPanierAlorsDemandePanierEtDemandeVider() {
		preparerPanier();

		panierApplication.viderMonPanier();

		verifierPanier();
		verify(mockPanier).vider();
	}

	@Test
	public void quandJeDemandeDeRetirerTousLesBilletsDeTypeAlorsDemandePanierEtDemandeDeRetirerTousLesBilletsDeType() {
		preparerPanier();

		panierApplication.retirerTousBilletsDeType(TYPE);

		verifierPanier();
		verify(mockPanier).retirerTousBilletsDeType(TYPE);
	}

	@Test
	public void quandJeDemandePrixTotalDuPanierAlorsDemandePanierEtDemandePrixTotal() {
		preparerPanier();

		panierApplication.prixTotalDuPanier();

		verifierPanier();
		verify(mockPanier).prixTotal();
	}

	@Test(expected = UIQuantiteInsuffisante.class)
	public void quandNombreBilletDesireDepasseNombreDisponibleAlorsErreur()
			throws UIQuantiteInsuffisante {

		when(mockEntreeSaisonDTO.getNombreBilletsDisponibles()).thenReturn(1);
		when(mockEntreeSaisonDTO.getNombreBilletsDesires()).thenReturn(2);

		panierApplication.acheterInstantanement(mockEntreeSaisonDTO);
	}

	@Test
	public void QuandAjouterBilletsSaisonAuPanierAlorsVerifierTypeEtNombreBillets()
			throws Exception {

		panierApplication.ajouterBilletsSaisonAuPanier(mockEntreeSaisonDTO);

		verify(mockEntreeSaisonDTO).getType();
		verify(mockEntreeSaisonDTO).getNombreBilletsDesires();
	}

	@Test
	public void QuandAjouterBilletsMatchAuPanierAlorsVerifierTypeEtNombreBillets() throws Exception {

		panierApplication.ajouterBilletsUnMatchAuPanier(mockEntreeSaisonDTO);

		verify(mockEntreeSaisonDTO).getType();
		verify(mockEntreeSaisonDTO).getNombreBilletsDesires();

	}

	private void preparerListeEntreeUnMatch() {
		when(mockUtilisateurManagement.getPanier()).thenReturn(mockPanier);
		when(mockPanier.getListeBilletsUnMatch()).thenReturn(BILLETS_UN_MATCH);
		when(mockInventaireService.inventaireUnMatchPourBilletsDonnes(BILLETS_UN_MATCH))
				.thenReturn(new Inventaire<EntreeUnMatch>());
		when(mockBilletService.quantiteDispoParTypeBillet()).thenReturn(
				new HashMap<String, Integer>());
	}

	private void preparerListeEntreeSaison() {
		when(mockUtilisateurManagement.getPanier()).thenReturn(mockPanier);
		when(mockPanier.getListeBilletsSaison()).thenReturn(BILLETS_SAISON);
		when(mockInventaireService.inventaireSaisonPourBilletsDonnes(BILLETS_SAISON)).thenReturn(
				new Inventaire<EntreeSaison>());
		when(mockBilletService.quantiteDispoParTypeBillet()).thenReturn(
				new HashMap<String, Integer>());
	}

	private void preparerPanier() {
		when(mockUtilisateurManagement.getPanier()).thenReturn(mockPanier);
	}

	private void verifierLinventaireUnMatch() {
		verify(mockInventaireService).inventaireUnMatchPourBilletsDonnes(BILLETS_UN_MATCH);
	}

	private void verifierLinventaireSaison() {
		verify(mockInventaireService).inventaireSaisonPourBilletsDonnes(BILLETS_SAISON);
	}

	private void verifierLaQuantiteDispo() {
		verify(mockBilletService).quantiteDispoParTypeBillet();
	}

	private void verifierPanier() {
		verify(mockUtilisateurManagement).getPanier();
	}

}
