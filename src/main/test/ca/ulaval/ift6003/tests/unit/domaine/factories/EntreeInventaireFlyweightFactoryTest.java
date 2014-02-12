package ca.ulaval.ift6003.tests.unit.domaine.factories;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeInventaireFlyweightFactory;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;

public class EntreeInventaireFlyweightFactoryTest {
	private BilletSaison mockBilletSaison;
	private BilletUnMatch mockBilletUnMatch;
	private CentreSportif mockCentreSportif;
	Match mockMatch;

	@Before
	public void setUp() throws Exception {
		mockBilletSaison = mock(BilletSaison.class);
		mockBilletUnMatch = mock(BilletUnMatch.class);
		mockCentreSportif = mock(CentreSportif.class);
		mockMatch = mock(Match.class);
	}

	@Test
	public void siCreerPoidsMoucheFactoryAlorsNonVide() throws Exception {

		EntreeInventaireFlyweightFactory entreeInventaireFlyweightFactory = EntreeInventaireFlyweightFactory.getInstance();
		assertNotNull(entreeInventaireFlyweightFactory);
	}

	@Test
	public void siBilletMatchEtCentreSportifQuandChercherPoidsMoucheEntreeMatchAlorsNonVide() {
		EntreeUnMatch entreeMatch = EntreeInventaireFlyweightFactory.get(mockBilletUnMatch, mockMatch, mockCentreSportif);
		assertNotNull(entreeMatch);
	}

	@Test
	public void siBilletSaisonEtCentreSportifQuandChercherPoidsMoucheEntreeSaisonAlorsNonVide() throws Exception {

		EntreeSaison entreeSaison = EntreeInventaireFlyweightFactory.get(mockBilletSaison, mockCentreSportif);
		assertNotNull(entreeSaison);
	}

}
