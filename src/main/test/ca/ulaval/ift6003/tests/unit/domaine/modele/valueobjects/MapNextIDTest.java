package ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.gestionIDs.MapNextID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MapNextIDTest {

	private MapNextID uneMapNextID;
	private MapNextID uneMapNextIDMemeValeur;
	private MapNextID uneMapNextIDDifferente;

	private final int NEXT_BILLET_ID = 1;
	private final int NEXT_MATCH_ID = 2;
	private final int NEXT_ID = 3;
	private final String NOM_ENTITE = "nomentite";
	private final String NOM_ENTITE_EST_MATCH = "match";

	private final int NEXT_BILLET_ID_DIFFERENT = 4;
	private final int NEXT_MATCH_ID_DIFFERENT = 5;
	private final int NEXT_ID_DIFFERENT = 6;
	private final String NOM_ENTITE_DIFFERENT = "nomentitedifferent";

	@Before
	public void setUp() throws Exception {
		uneMapNextID = new MapNextID();
		uneMapNextIDMemeValeur = new MapNextID();
		uneMapNextIDDifferente = new MapNextID();
	}

	@Test
	public void siLesDeuxMapNextIDOntLaMemeValeurAlorsMemeValeurQueRetourneLesMemesValeurs() {
		creerUneMapNextID(NOM_ENTITE);
		copierUneMapNextIDAvecLaMemeValeur(NOM_ENTITE);
		assertTrue(uneMapNextID.memeValeurQue(uneMapNextIDMemeValeur));
	}

	@Test
	public void siLesDeuxsMapNextIDNOntPasLaMemeValeurAlorsMemeValeurQueNeRetournePasLesMemesValeurs() {
		creerUneMapNextID(NOM_ENTITE);
		creerUneMapNextIDAvecDesValeursDifferent(NOM_ENTITE_DIFFERENT);

		assertFalse(uneMapNextID.memeValeurQue(uneMapNextIDDifferente));
	}

	@Test
	public void siLesDeuxMapNextIDOntLaMemeValeurEtQueNomEntiteEstMatchAlorsMemeValeurQueRetourneLesMemesValeurs() {
		creerUneMapNextID(NOM_ENTITE_EST_MATCH);
		copierUneMapNextIDAvecLaMemeValeur(NOM_ENTITE_EST_MATCH);
		assertTrue(uneMapNextID.memeValeurQue(uneMapNextIDMemeValeur));
	}

	private void creerUneMapNextID(String NomEntite) {
		uneMapNextID.setNextBilletId(NEXT_BILLET_ID);
		uneMapNextID.setNextMatchId(NEXT_MATCH_ID);
		uneMapNextID.setNextIdPourEntite(NomEntite, NEXT_ID);
	}

	private void copierUneMapNextIDAvecLaMemeValeur(String NomEntite) {
		uneMapNextIDMemeValeur.setNextBilletId(uneMapNextID.getNextBilletId());
		uneMapNextIDMemeValeur.setNextMatchId(uneMapNextID.getNextMatchId());
		uneMapNextIDMemeValeur.setNextIdPourEntite(NomEntite, uneMapNextID.getNextIdPourEntite(NomEntite));
	}

	private void creerUneMapNextIDAvecDesValeursDifferent(String NomEntite) {
		uneMapNextIDDifferente.setNextBilletId(NEXT_BILLET_ID_DIFFERENT);
		uneMapNextIDDifferente.setNextMatchId(NEXT_MATCH_ID_DIFFERENT);
		uneMapNextIDDifferente.setNextIdPourEntite(NomEntite, NEXT_ID_DIFFERENT);
	}

}
