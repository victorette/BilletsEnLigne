package ca.ulaval.ift6003.tests.unit.domaine.services.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.exceptions.QuantiteInsuffisante;
import ca.ulaval.ift6003.domaine.modele.gestionIDs.GestionIDRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatchRepository;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletSaisonRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletUnMatchRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.services.BilletServiceImpl;

public class BilletServiceImplTest {

	private final String MATCH_ID = "1";
	private final String TYPE_BILLET = "Saison";
	private final BilletUnMatch Un_BILLET_UN_MATCH = new BilletUnMatch();
	private final BilletSaison Un_BILLET_SAISON = new BilletSaison();

	private BilletServiceImpl billetService;
	private BilletUnMatchRepository mockBilletUnMatchRepositoryImpl;
	private BilletSaisonRepositoryImpl mockBilletSaisonRepositoryImpl;
	private GestionIDRepository mockGestionIDRepository;

	private List<BilletUnMatch> listBilletUnMatch;
	private BilletUnMatch mockBilletUnMatch;
	private List<BilletSaison> listBilletSaison;
	private BilletSaison mockBilletSaison;

	@Before
	public void setup() {
		billetService = new BilletServiceImpl();
		mockBilletUnMatchRepositoryImpl = mock(BilletUnMatchRepositoryImpl.class);
		mockBilletSaisonRepositoryImpl = mock(BilletSaisonRepositoryImpl.class);
		mockGestionIDRepository = mock(GestionIDRepository.class);
		mockBilletUnMatch = mock(BilletUnMatch.class);
		mockBilletSaison = mock(BilletSaison.class);

		listBilletUnMatch = new ArrayList<BilletUnMatch>();
		listBilletUnMatch.add(mockBilletUnMatch);
		listBilletSaison = new ArrayList<BilletSaison>();
		listBilletSaison.add(mockBilletSaison);

		billetService.setGestionIDRepository(mockGestionIDRepository);
	}

	@Test
	public void quandAjouterBilletUnMatchAlorsNextIDEstDemanderEtRepositoryAppellerAvecBonBillet() {
		billetService.setBilletUnMatchRepository(mockBilletUnMatchRepositoryImpl);

		billetService.ajouterNouveauBilletUnMatch(Un_BILLET_UN_MATCH);
		verify(mockGestionIDRepository).selectEtUpdateNextId(Consts.NOM_ENTITE_BILLET);
		verify(mockBilletUnMatchRepositoryImpl).inserer(Un_BILLET_UN_MATCH);
	}

	@Test
	public void quandAjouterBilletSaisonAlorsNextIDEstDemanderEtRepositoryAppellerAvecBonBillet() {
		billetService.setBilletSaisonRepository(mockBilletSaisonRepositoryImpl);

		billetService.ajouterNouveauBilletSaison(Un_BILLET_SAISON);
		verify(mockGestionIDRepository).selectEtUpdateNextId(Consts.NOM_ENTITE_BILLET);
		verify(mockBilletSaisonRepositoryImpl).inserer(Un_BILLET_SAISON);
	}

	@Test
	public void quandCompteNombreBilletsPourChaqueMatchIDAlorsRetourneBonneMapNonVide() {
		Set<String> matchIDs = new HashSet<String>();
		matchIDs.add(MATCH_ID);
		billetService.setBilletUnMatchRepository(mockBilletUnMatchRepositoryImpl);

		when(mockBilletUnMatchRepositoryImpl.selectTousAyantMatchIDs(matchIDs)).thenReturn(listBilletUnMatch);
		when(mockBilletUnMatch.getMatch_id()).thenReturn(MATCH_ID);
		Map<String, Integer> nbParMatch = billetService.compterNombreBilletsPourChaqueMatchID(matchIDs);

		assertNotNull(nbParMatch);
	}

	@Test
	public void quandQuantiteDispoParTypeBilletAlorsRetourneMapNonVide() {
		billetService.setBilletUnMatchRepository(mockBilletUnMatchRepositoryImpl);
		billetService.setBilletSaisonRepository(mockBilletSaisonRepositoryImpl);

		when(mockBilletUnMatchRepositoryImpl.selectTous()).thenReturn(listBilletUnMatch);
		when(mockBilletSaisonRepositoryImpl.selectTous()).thenReturn(listBilletSaison);
		Map<String, Integer> nombreParType = billetService.quantiteDispoParTypeBillet();

		assertNotNull(nombreParType);
	}

	@Test
	public void quandGetNombreMaximumDisponiblePourTypeBilletAlorsRetourneDeux() {
		billetService.setBilletUnMatchRepository(mockBilletUnMatchRepositoryImpl);
		billetService.setBilletSaisonRepository(mockBilletSaisonRepositoryImpl);

		when(mockBilletUnMatchRepositoryImpl.selectTous()).thenReturn(listBilletUnMatch);
		when(mockBilletSaisonRepositoryImpl.selectTous()).thenReturn(listBilletSaison);
		when(mockBilletSaison.getType()).thenReturn(TYPE_BILLET);
		when(mockBilletUnMatch.getType()).thenReturn(TYPE_BILLET);
		int nombreMaximumDisponible = billetService.getNombreMaximumDisponiblePourTypeBillet(TYPE_BILLET);

		assertEquals(nombreMaximumDisponible, 2);
	}

	@Test
	public void quandGetBilletsUnMatchSelonTypeAlorsListeBilletNonVide() throws QuantiteInsuffisante {
		billetService.setBilletUnMatchRepository(mockBilletUnMatchRepositoryImpl);

		when(mockBilletUnMatchRepositoryImpl.selectTous()).thenReturn(listBilletUnMatch);
		when(mockBilletUnMatch.getType()).thenReturn(TYPE_BILLET);
		List<BilletUnMatch> listeBillet = billetService.getBilletsUnMatchSelonType(TYPE_BILLET);

		assertNotNull(listeBillet);
	}

	@Test
	public void quandGetBilletsSaisonSelonTypeAlorsListeBilletNonVide() throws QuantiteInsuffisante {
		billetService.setBilletSaisonRepository(mockBilletSaisonRepositoryImpl);

		when(mockBilletSaisonRepositoryImpl.selectTous()).thenReturn(listBilletSaison);
		when(mockBilletSaison.getType()).thenReturn(TYPE_BILLET);
		List<BilletSaison> listeBillet = billetService.getBilletsSaisonSelonType(TYPE_BILLET);

		assertNotNull(listeBillet);
	}
}
