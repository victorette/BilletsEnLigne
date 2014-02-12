package ca.ulaval.ift6003.tests.unit.application.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.impl.InventaireApplicationImpl;
import ca.ulaval.ift6003.application.impl.UtilisateurManagementImpl;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletSaisonRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletUnMatchRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.CentreSportifRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.MatchRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.services.BilletServiceImpl;
import ca.ulaval.ift6003.infrastructure.services.CentreSportifServiceImpl;
import ca.ulaval.ift6003.infrastructure.services.InventaireServiceImpl;
import ca.ulaval.ift6003.infrastructure.services.MatchServiceImpl;

public class InventaireApplicationImplTest {

	private InventaireApplicationImpl inventaireApplicationImpl;

	private InventaireServiceImpl mockInventaireService;
	private BilletServiceImpl mockBilletService;
	private MatchServiceImpl mockMatchService;
	private CentreSportifServiceImpl mockCentreSportifService;
	private MatchRepositoryImpl mockMatchRepository;
	private BilletUnMatchRepositoryImpl mockBilletUnMatchRepository;
	private BilletSaisonRepositoryImpl mockBilletSaisonRepository;
	private CentreSportifRepositoryImpl mockCentreSportifRepository;
	private UtilisateurManagementImpl mockUtilisateurManagement;

	private final String MATCH_ID = "matchId";
	private final Map<String, Match> MAP_MATCHS = new HashMap<String, Match>();
	private final String CENTRE_NOM = "centrenom";
	private final PreferencesBillets PREFERENCES = new PreferencesBillets();

	@Before
	public void setUp() throws Exception {
		inventaireApplicationImpl = new InventaireApplicationImpl();

		mockInventaireService = mock(InventaireServiceImpl.class);
		mockBilletService = mock(BilletServiceImpl.class);
		mockMatchService = mock(MatchServiceImpl.class);
		mockCentreSportifService = mock(CentreSportifServiceImpl.class);
		mockMatchRepository = mock(MatchRepositoryImpl.class);
		mockBilletUnMatchRepository = mock(BilletUnMatchRepositoryImpl.class);
		mockBilletSaisonRepository = mock(BilletSaisonRepositoryImpl.class);
		mockCentreSportifRepository = mock(CentreSportifRepositoryImpl.class);
		mockUtilisateurManagement = mock(UtilisateurManagementImpl.class);

		inventaireApplicationImpl.setBilletService(mockBilletService);
		inventaireApplicationImpl.setBilletUnMatchRepository(mockBilletUnMatchRepository);
		inventaireApplicationImpl.setBilletSaisonRepository(mockBilletSaisonRepository);
		inventaireApplicationImpl.setCentreSportifRepository(mockCentreSportifRepository);
		inventaireApplicationImpl.setCentreSportifService(mockCentreSportifService);
		inventaireApplicationImpl.setInventaireService(mockInventaireService);
		inventaireApplicationImpl.setMatchRepository(mockMatchRepository);
		inventaireApplicationImpl.setMatchService(mockMatchService);
		inventaireApplicationImpl.setUtilisateurManagement(mockUtilisateurManagement);
	}

	@Test
	public void quandOnDemandeInventaireUnMatchEnDtoAlorsLinventaireEstDemandeAvecLesBonnesQuantitesDeBilletsDisponible() {
		when(mockInventaireService.inventaireBilletsMatchsFutur()).thenReturn(new Inventaire<EntreeUnMatch>());
		when(mockBilletService.quantiteDispoParTypeBillet()).thenReturn(new HashMap<String, Integer>());

		inventaireApplicationImpl.produireInventaireBilletsUnMatch();

		verifierQueServiceProduitLinventaireDesMatchsFuturs();
		avecLesBonnesQuantitesDisponibles();
	}

	@Test
	public void quandOnDemandeInventaireSaisonEnDtoAlorsLinventaireEstDemandeAvecLesBonnesQuantitesDeBilletsDisponible() {
		when(mockInventaireService.inventaireBilletsSaison()).thenReturn(new Inventaire<EntreeSaison>());
		when(mockBilletService.quantiteDispoParTypeBillet()).thenReturn(new HashMap<String, Integer>());

		inventaireApplicationImpl.produireInventaireBilletsSaison();

		verifierQueServiceProduitLinventaireSaison();
		avecLesBonnesQuantitesDisponibles();
	}

	@Test
	public void quandOnDemandeInventaireUnMatchFiltreEnDtoAlorsLinventaireEstDemandeAvecLesBonnesQuantitesDeBilletsDisponible() {
		when(mockUtilisateurManagement.preferencesUtilisateurs()).thenReturn(PREFERENCES);
		when(mockInventaireService.inventaireBilletsMatchsFutursFiltre(PREFERENCES)).thenReturn(new Inventaire<EntreeUnMatch>());
		when(mockBilletService.quantiteDispoParTypeBillet()).thenReturn(new HashMap<String, Integer>());

		inventaireApplicationImpl.produireInventaireBilletsUnMatchFiltre();

		verifierQueUtilisateurManagementProduitLesPreferencesUtilisateurs();
		avecLesBonnesQuantitesDisponibles();
	}

	@Test
	public void quandJedemandeListeIdMatchsMatchServiceMeRetourneLaListeIdMatch() {
		inventaireApplicationImpl.getListeIdMatchs();
		verifierQueMatchServiceProduitLaListeDesIdDesMatchs();
	}

	@Test
	public void quandJeDemandeLaListeDeNomsDesCentresSportifsAlorsLeServiceCentreSportifMeLaRetourne() {
		inventaireApplicationImpl.getListeNomsCentresSportifs();
		verifierQueCentreSportifServiceProduitLaListeDesNomsCentresSportifs();
	}

	@Test
	public void quandJeDemandeLaListeBilletsUnMatchAlorsLeRepositoryBilletUnMatchMeLaRetourne() {
		inventaireApplicationImpl.getListeBilletsUnMatch();
		verifierQueBilletUnMatchRepositorySelectionneTousLesBillets();
	}

	@Test
	public void quandJeDemandeLaListeBilletsSaisonAlorsLeRepositoryBilletSaisonMeLaRetourne() {
		inventaireApplicationImpl.getListeBilletsSaison();
		verifierQueBilletSaisonRepositorySelectionneTousLesBillets();
	}

	@Test
	public void quandJeDemandeLaListeDesCentresSportifsAlorsLeRepositoryCentreSportifMeLaRetourne() {
		inventaireApplicationImpl.getListeCentresSportifs();
		verifierQueCentreSportifRepositorySelectionneTousLesCentres();
	}

	@Test
	public void quandJeDemandeLaListeDesMatchsAlorsLeRepositoryDesMatchsMeLaRetourne() {
		inventaireApplicationImpl.getListeMatchs();
		verifierQueMatchRepositorySelectionneTousLesMatch();
	}

	@Test
	public void quandJeDemandeLesSectionsLieesSelonMatchIdMatchServiceMeLesRetourne() throws UIEntiteInexistante, EntiteNonTrouvee {
		inventaireApplicationImpl.getSectionsLieesSelonMatchId(MATCH_ID);
		verify(mockMatchService).sectionsDuCentreSportifLieAUnMatch(MATCH_ID);
	}

	@Test
	public void quandJeDemandeLeNombreDeBilletsTotalPasMatchsAVenirAlorsBilletServiceMeRetourneLeNombreDeBillets() {
		when(mockMatchService.getMapMatchsAVenir()).thenReturn(MAP_MATCHS);
		inventaireApplicationImpl.nombreDeBilletsTotalParMatchsAVenir();
		verify(mockBilletService).compterNombreBilletsPourChaqueMatchID(MAP_MATCHS.keySet());
	}

	@Test
	public void quandJeDemandeLesSectionsCentreSportifAlorsLeServiceMeLesRetournes() {
		inventaireApplicationImpl.getSectionsCentreSportif(CENTRE_NOM);
		verifierQueCentreSportifServiceProduitLesSectionsCentreSportif();
	}

	private void verifierQueServiceProduitLinventaireDesMatchsFuturs() {
		verify(mockInventaireService).inventaireBilletsMatchsFutur();
	}

	private void verifierQueServiceProduitLinventaireSaison() {
		verify(mockInventaireService).inventaireBilletsSaison();
	}

	private void avecLesBonnesQuantitesDisponibles() {
		verify(mockBilletService).quantiteDispoParTypeBillet();
	}

	private void verifierQueUtilisateurManagementProduitLesPreferencesUtilisateurs() {
		verify(mockUtilisateurManagement).preferencesUtilisateurs();
	}

	private void verifierQueMatchServiceProduitLaListeDesIdDesMatchs() {
		verify(mockMatchService).getListeIdMatchs();
	}

	private void verifierQueCentreSportifServiceProduitLaListeDesNomsCentresSportifs() {
		verify(mockCentreSportifService).getListeNomsCentresSportifs();
	}

	private void verifierQueBilletUnMatchRepositorySelectionneTousLesBillets() {
		verify(mockBilletUnMatchRepository).selectTous();
	}

	private void verifierQueCentreSportifRepositorySelectionneTousLesCentres() {
		verify(mockCentreSportifRepository).selectTous();
	}

	private void verifierQueBilletSaisonRepositorySelectionneTousLesBillets() {
		verify(mockBilletSaisonRepository).selectTous();
	}

	private void verifierQueMatchRepositorySelectionneTousLesMatch() {
		verify(mockMatchRepository).selectTous();
	}

	private void verifierQueCentreSportifServiceProduitLesSectionsCentreSportif() {
		verify(mockCentreSportifService).getSectionsCentreSportif(CENTRE_NOM);
	}
}
