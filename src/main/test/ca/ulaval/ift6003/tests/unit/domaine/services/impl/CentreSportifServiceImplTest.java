package ca.ulaval.ift6003.tests.unit.domaine.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.CentreSportifRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.services.CentreSportifServiceImpl;

public class CentreSportifServiceImplTest {

	private CentreSportifServiceImpl centreSportifService;
	private CentreSportifRepositoryImpl mockCentreSportifRepository;
	private CentreSportif mockNouveauCentreSportif, mockCentreSportifExistant;

	@Before
	public void setup() {
		centreSportifService = new CentreSportifServiceImpl();

		mockNouveauCentreSportif = mock(CentreSportif.class);
		mockCentreSportifExistant = mock(CentreSportif.class);

		mockCentreSportifRepository = mock(CentreSportifRepositoryImpl.class);

		centreSportifService.setCentreSportifRepository(mockCentreSportifRepository);
	}

	@Test
	public void siCentreSportifServiceEstCreeAlorsReferentielCentreSportifEstInitialise()
			throws Exception {

		assertNotNull(centreSportifService);

		assertEquals(null, mockCentreSportifRepository.getXmlReaderWriter());
	}

	@Test
	public void siAjouterNouveauCentreSportifAlorsRepositoryAppelleAvecBonCentre() throws Exception {

		when(mockNouveauCentreSportif.getNom()).thenReturn("Nouveau");
		doThrow(new EntiteNonTrouvee()).when(mockCentreSportifRepository)
				.selectParIdentifiantUnique("Nouveau");
		centreSportifService.ajouterNouveauCentreSportif(mockNouveauCentreSportif);
		verify(mockCentreSportifRepository).inserer(mockNouveauCentreSportif);
	}

	@Test(expected = IdentifiantDejaExistant.class)
	public void siAjouterExistantCentreSportifAlorsErreurEstGeneree() throws Exception {

		when(mockCentreSportifRepository.selectParIdentifiantUnique("Existant")).thenReturn(
				mockCentreSportifExistant);

		centreSportifService.ajouterNouveauCentreSportif(mockCentreSportifExistant);

	}

	@Test
	public void siConsulterListeNomsAlorsReferentielCentresSportifsEstAppele() throws Exception {
		centreSportifService.getListeNomsCentresSportifs();
		verify(mockCentreSportifRepository).selectTous();
	}

	@Test
	public void siAjouterNouveauCentreSportifQuandConsulterListeNomsAlorsListeNonVide()
			throws Exception {

		when(mockNouveauCentreSportif.getNom()).thenReturn("Nouveau");
		doThrow(new EntiteNonTrouvee()).when(mockCentreSportifRepository)
				.selectParIdentifiantUnique("Nouveau");

		centreSportifService.ajouterNouveauCentreSportif(mockNouveauCentreSportif);
		List<String> listeNomsCentresSportifs = centreSportifService.getListeNomsCentresSportifs();
		assertNotNull(listeNomsCentresSportifs);

	}

	@Test
	public void siNouveauCentreAlorsEntiteNonTouveEtSectionsVides() throws Exception {

		when(mockNouveauCentreSportif.getNom()).thenReturn("Nouveau");
		doThrow(new EntiteNonTrouvee()).when(mockCentreSportifRepository)
				.selectParIdentifiantUnique("Nouveau");

		centreSportifService.ajouterNouveauCentreSportif(mockNouveauCentreSportif);
		List<String> sectionsCentreSportif = centreSportifService
				.getSectionsCentreSportif(mockNouveauCentreSportif.getNom());

		assertEquals(sectionsCentreSportif.size(), 0);
	}

	@Test
	public void siNouveauCentreAlorsSectionsNonVides() throws Exception {

		when(mockNouveauCentreSportif.getNom()).thenReturn("Nouveau");
		when(mockCentreSportifRepository.selectParIdentifiantUnique("Nouveau")).thenReturn(
				mockNouveauCentreSportif);

		List<String> sectionsCentreSportif = centreSportifService
				.getSectionsCentreSportif(mockNouveauCentreSportif.getNom());

		assertNotNull(sectionsCentreSportif);
	}

	@Test
	public void siChercherTousLesCentresAlorsParcourirReferentielEntier() throws Exception {

		Set<String> centresID = new HashSet<String>();

		centreSportifService.selectTousAyantIDs(centresID);

		verify(mockCentreSportifRepository).selectTousMap();
	}

	@Test
	public void siAjouterNouveauCentreQuandSelectionnerToutAlorsNouveauEstSelectionne()
			throws Exception {

		Map<String, CentreSportif> map = new HashMap<>();
		map.put("Nouveau", mockNouveauCentreSportif);

		when(mockCentreSportifRepository.selectTousMap()).thenReturn(map);
		when(mockNouveauCentreSportif.getNom()).thenReturn("Nouveau");

		Set<String> centresID = new HashSet<String>();
		centresID.add(mockNouveauCentreSportif.getNom());

		Map<String, CentreSportif> tousAyantIDs = new HashMap<String, CentreSportif>();

		tousAyantIDs = centreSportifService.selectTousAyantIDs(centresID);

		assertEquals(tousAyantIDs.get("Nouveau"), mockNouveauCentreSportif);
	}

	@Test
	public void quandTousCentresReliesAuxMatchsAlorsRetourneCentresNonVides() {
		Map<String, Match> matchs = new HashMap<>();

		Map<String, CentreSportif> centresRelies = centreSportifService
				.getTousCentresReliesAuxMatchs(matchs);

		assertNotNull(centresRelies);
		verify(mockCentreSportifRepository).selectTousMap();
	}

	@Test
	public void quandGetCentresReliesBilletsSaisonsAlorsRetourneCentresNonVides() {
		List<BilletSaison> billets = new ArrayList<BilletSaison>();

		Map<String, CentreSportif> centresRelies = centreSportifService
				.getCentresReliesBilletsSaisons(billets);

		assertNotNull(centresRelies);
		verify(mockCentreSportifRepository).selectTousMap();
	}

}
