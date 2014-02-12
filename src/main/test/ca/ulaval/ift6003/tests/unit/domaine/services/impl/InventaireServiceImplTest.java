package ca.ulaval.ift6003.tests.unit.domaine.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletSaisonRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletUnMatchRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.CentreSportifRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.services.CentreSportifServiceImpl;
import ca.ulaval.ift6003.infrastructure.services.InventaireServiceImpl;
import ca.ulaval.ift6003.infrastructure.services.MatchServiceImpl;

public class InventaireServiceImplTest {
	private InventaireServiceImpl inventaireServiceImpl;
	private CentreSportifRepositoryImpl mockCentreSportifRepositoryImpl;
	private BilletUnMatchRepositoryImpl mockBilletUnMatchRepositoryImpl;
	private BilletSaisonRepositoryImpl mockBilletSaisonRepositoryImpl;
	private CentreSportifServiceImpl mockCentreSportifServiceImpl;
	private MatchServiceImpl mockMatchServiceImpl;
	private PreferencesBillets mockPreferencesBillets;

	@Before
	public void setup() {

		inventaireServiceImpl = new InventaireServiceImpl();
		mockCentreSportifRepositoryImpl = mock(CentreSportifRepositoryImpl.class);
		mockBilletUnMatchRepositoryImpl = mock(BilletUnMatchRepositoryImpl.class);
		mockBilletSaisonRepositoryImpl = mock(BilletSaisonRepositoryImpl.class);
		mockCentreSportifServiceImpl = mock(CentreSportifServiceImpl.class);
		mockMatchServiceImpl = mock(MatchServiceImpl.class);
		mockPreferencesBillets = mock(PreferencesBillets.class);

		inventaireServiceImpl.setMatchService(mockMatchServiceImpl);
		inventaireServiceImpl.setCentreSportifRepository(mockCentreSportifRepositoryImpl);
		inventaireServiceImpl.setBilletSaisonRepository(mockBilletSaisonRepositoryImpl);
		inventaireServiceImpl.setCentreSportifService(mockCentreSportifServiceImpl);
		inventaireServiceImpl.setBilletUnMatchRepository(mockBilletUnMatchRepositoryImpl);

	}

	@Test
	public void siMatchsFutursAlorsInventaireBilletsMatchsFuturs() throws Exception {

		Map<String, Match> matchs = new HashMap<>();

		when(mockMatchServiceImpl.getMapMatchsAVenir()).thenReturn(matchs);

		Inventaire<EntreeUnMatch> inventaireBilletsMatchsFutur = inventaireServiceImpl
				.inventaireBilletsMatchsFutur();

		assertNotNull(inventaireBilletsMatchsFutur);

	}

	@Test
	public void siMatchsFutursAlorsInventaireBilletsMatchsFutursAvecFiltre() throws Exception {

		Map<String, Match> matchs = new HashMap<>();

		when(mockMatchServiceImpl.getMapMatchsAVenir()).thenReturn(matchs);

		when(mockPreferencesBillets.getSportsTypes()).thenReturn(new LinkedList<String>());

		Inventaire<EntreeUnMatch> inventaireBilletsMatchsFuturFiltre = inventaireServiceImpl
				.inventaireBilletsMatchsFutursFiltre(mockPreferencesBillets);

		assertNotNull(inventaireBilletsMatchsFuturFiltre);
	}

	@Test
	public void siBilletsSaisonAlorsInventaireBilletsSaison() throws Exception {

		List<BilletSaison> billetsSaison = new LinkedList<>();
		when(mockBilletSaisonRepositoryImpl.selectTous()).thenReturn(billetsSaison);

		Inventaire<EntreeSaison> inventaireBilletsSaison = inventaireServiceImpl
				.inventaireBilletsSaison();

		assertNotNull(inventaireBilletsSaison);
	}

	@Test
	public void siBilletsSaisonQuandInventaireSaisonPourBilletsDonnesAlorsChercherReferentiel()
			throws Exception {

		List<BilletSaison> billets = new LinkedList<>();

		Inventaire<EntreeSaison> inventaire = inventaireServiceImpl
				.inventaireSaisonPourBilletsDonnes(billets);

		assertNotNull(inventaire);
		verify(mockCentreSportifServiceImpl).getCentresReliesBilletsSaisons(billets);
	}

	@Test
	public void siBilletsSaisonQuandExisteCentreSportifCorrespondantAlorsNouvelleEntreeInventaire()
			throws Exception {

		List<BilletSaison> billets = new LinkedList<>();
		billets.add(new BilletSaison("1", "", "CentreSportif", "", "", "", 0));

		Map<String, CentreSportif> mapCentres = new HashMap<>();
		mapCentres.put("CentreSportif", new CentreSportif());

		when(mockCentreSportifServiceImpl.getCentresReliesBilletsSaisons(billets)).thenReturn(
				mapCentres);

		Inventaire<EntreeSaison> inventaire = inventaireServiceImpl
				.inventaireSaisonPourBilletsDonnes(billets);

		assertEquals(inventaire.getEntrees().size(), 1);
	}

	@Test
	public void siBilletsMatchQuandExisteCentreSportifEtMatchCorrespondantsAlorsNouvelleEntreeInventaire()
			throws Exception {

		List<BilletUnMatch> billets = new LinkedList<>();
		billets.add(new BilletUnMatch("1", "1", "CentreSportif", "", "", 0));

		Map<String, Match> matchsRelies = new HashMap<>();

		matchsRelies.put("1", new Match("1", "", "", null, "CentreSportif", "", ""));

		Map<String, CentreSportif> mapCentres = new HashMap<>();
		mapCentres.put("CentreSportif", new CentreSportif());

		when(mockCentreSportifServiceImpl.getTousCentresReliesAuxMatchs(matchsRelies)).thenReturn(
				mapCentres);

		when(mockMatchServiceImpl.getTousMatchsReliesAuxBillets(billets)).thenReturn(matchsRelies);

		Inventaire<EntreeUnMatch> inventaire = inventaireServiceImpl
				.inventaireUnMatchPourBilletsDonnes(billets);

		assertNotNull(inventaire);
		assertEquals(inventaire.getEntrees().size(), 1);
	}

}
