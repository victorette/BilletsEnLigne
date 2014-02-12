package ca.ulaval.ift6003.tests.unit.domaine.services.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.CentreSportifRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.GestionIDRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.MatchRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.services.MatchServiceImpl;

public class MatchServiceImplTest {

	private final Match UN_MATCH = new Match();
	private final Match UN_AUTRE_MATCH = new Match("matchId", "", "", null, "nomCentreSportif", "", "");

	private MatchServiceImpl matchServiceImpl;
	private MatchRepositoryImpl mockMatchRepositoryImpl;
	private GestionIDRepositoryImpl mockGestionIDRepositoryImpl;
	private CentreSportifRepositoryImpl mockCentreSportifRepositoryImpl;

	@Before
	public void setup() {
		matchServiceImpl = new MatchServiceImpl();
		mockMatchRepositoryImpl = mock(MatchRepositoryImpl.class);
		mockGestionIDRepositoryImpl = mock(GestionIDRepositoryImpl.class);
		mockCentreSportifRepositoryImpl = mock(CentreSportifRepositoryImpl.class);

		matchServiceImpl.setMatchRepository(mockMatchRepositoryImpl);
		matchServiceImpl.setGestionIDRepository(mockGestionIDRepositoryImpl);
		matchServiceImpl.setCentreSportifRepository(mockCentreSportifRepositoryImpl);

	}

	@Test
	public void quandAjouterMatchAlorsNextIDEstDemanderEtRepositoryAppellerAvecBonMatch() {
		matchServiceImpl.ajouterNouveauMatch(UN_MATCH);
		verify(mockGestionIDRepositoryImpl).selectEtUpdateNextId(Consts.NOM_ENTITE_MATCH);
		verify(mockMatchRepositoryImpl).inserer(UN_MATCH);
	}

	@Test
	public void siAjouterMatchQuandListeIdMatchsAlorsReferentielSollicite() throws Exception {

		matchServiceImpl.ajouterNouveauMatch(UN_MATCH);
		List<String> listeIdMatchs = matchServiceImpl.getListeIdMatchs();

		assertNotNull(listeIdMatchs);
		verify(mockMatchRepositoryImpl).selectTous();
	}

	@Test
	public void siListeDansReferentielVideQuandListeIdMatchsAlorsResultatVide() throws Exception {

		List<Match> listeMatchs = new LinkedList<>();

		when(mockMatchRepositoryImpl.selectTous()).thenReturn(listeMatchs);
		List<String> listeIdMatchs = matchServiceImpl.getListeIdMatchs();

		assertTrue(listeIdMatchs.isEmpty());
	}

	@Test
	public void siChercherMatchsAvenirQuandSolliciterServiceAlorsReferentielAppele() throws Exception {

		Map<String, Match> mapMatchsAVenir = matchServiceImpl.getMapMatchsAVenir();

		assertNotNull(mapMatchsAVenir);
		verify(mockMatchRepositoryImpl).selectTousMap();
	}

	@Test
	public void siListeMatchsReliesAuxBilletQuandSolliciterServiceAlorsReferentielAppele() throws Exception {

		List<BilletUnMatch> billets = new LinkedList<BilletUnMatch>();

		Map<String, Match> matchsReliesAuxBillets = matchServiceImpl.getTousMatchsReliesAuxBillets(billets);

		assertNotNull(matchsReliesAuxBillets);
		verify(mockMatchRepositoryImpl).selectTousMap();
	}

	@Test
	public void siAutreMatchLieCentreSportifQuandChercherSectionsDuCentreSportifAlorsNonVide() throws Exception {

		List<String> sections = new ArrayList<>();
		sections.add("section1");

		when(mockMatchRepositoryImpl.selectParIdentifiantUnique(UN_AUTRE_MATCH.getMatchId())).thenReturn(UN_AUTRE_MATCH);

		when(mockCentreSportifRepositoryImpl.selectParIdentifiantUnique(UN_AUTRE_MATCH.getNomCentreSportif())).thenReturn(new CentreSportif("", "", null, sections));

		List<String> sectionsDuCentreSportifLieAUnMatch = matchServiceImpl.sectionsDuCentreSportifLieAUnMatch(UN_AUTRE_MATCH.getMatchId());
		assertNotNull(sectionsDuCentreSportifLieAUnMatch);
	}

	@Test
	public void SiListeMatchsQuandselectTousAyantIDAlorsListeNonVide() throws Exception {

		Set<String> matchIDs = new HashSet<String>();

		Map<Integer, Match> mapMatchs = matchServiceImpl.selectTousAyantIDs(matchIDs);

		assertNotNull(mapMatchs);
	}

}
