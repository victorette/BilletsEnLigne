package ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;

public class InventaireTest {

	private Inventaire<EntreeUnMatch> unInventaireUnMatch;
	private Inventaire<EntreeUnMatch> unInventaireUnMatchMemeValeur;
	private Inventaire<EntreeUnMatch> unInventaireUnMatchDifferent;

	private final List<EntreeUnMatch> ENTREE_UN_MATCH = new ArrayList<>();

	private final EntreeUnMatch ENTREE_MATCH = new EntreeUnMatch();
	private final EntreeUnMatch DEUXIEME_ENTREE_MATCH = new EntreeUnMatch();

	@SuppressWarnings("deprecation")
	private Date LA_DATE = new Date(1999, 06, 13);
	@SuppressWarnings("deprecation")
	private Date LA_DATE2 = new Date(1999, 06, 13);

	@Before
	public void setUp() throws Exception {
		unInventaireUnMatch = new Inventaire<EntreeUnMatch>();
		unInventaireUnMatchMemeValeur = new Inventaire<EntreeUnMatch>();
		unInventaireUnMatchDifferent = new Inventaire<EntreeUnMatch>();

		ENTREE_MATCH.setType("general");
	}

	@Test
	public void quandOnCompareDeuxInventaireQuiOntLaMemeValeurAlorsMemeValeurQueRetourneLesMemesValeurs() {
		creerUnInventaireUnMatch();
		creerUnInventaireUnMatchMemeValeur();

		unInventaireUnMatchMemeValeur.setEntrees(unInventaireUnMatch.getEntrees());

		assertTrue(unInventaireUnMatch.memeValeurQue(unInventaireUnMatchMemeValeur));
	}

	@Test
	public void quandOnCompareDeuxInventaireQuiNOntPasLaMemeValeurMaisLeMemeNombreDEntreeAlorsMemeValeurQueNeRetournePasLesMemesValeurs() {
		preparerEntreeMatch();
		preparerEntreeDeuxiemeMatch();
		creerUnInventaireUnMatch();
		creerUnInventaireUnMatchDifferent();

		assertFalse(unInventaireUnMatch.memeValeurQue(unInventaireUnMatchDifferent));

	}

	@Test
	public void quandOnCompareDeuxInventaireQuiNontPasLaMemeTailleEtPasLaMemeValeurMemeValeurQueNeRetournePasLesMemesValeurs() {
		creerUnInventaireUnMatch();
		creerUnInventaireUnMatchDifferent();

		assertFalse(unInventaireUnMatch.memeValeurQue(unInventaireUnMatchDifferent));
	}

	@Test
	public void quandOnDemandeLaTaillerAlorsLaTailleEstRetournee() {
		creerUnInventaireUnMatch();
		assertEquals(unInventaireUnMatch.size(), 0);
	}

	@Test
	public void quandOnDemandeLeNombreDentreeDeTypeCaNousRetourneLeBonNombre() {
		ENTREE_UN_MATCH.add(ENTREE_MATCH);
		creerUnInventaireUnMatch();

		unInventaireUnMatch.nombreEntreesDeType("general");

		assertEquals(unInventaireUnMatch.nombreEntreesDeType("general"), 1);
	}

	private void creerUnInventaireUnMatch() {
		unInventaireUnMatch = new Inventaire<EntreeUnMatch>(ENTREE_UN_MATCH);
	}

	private void creerUnInventaireUnMatchMemeValeur() {
		unInventaireUnMatchMemeValeur.setEntrees(unInventaireUnMatch.getEntrees());
	}

	private void creerUnInventaireUnMatchDifferent() {
		unInventaireUnMatchDifferent.ajouter(DEUXIEME_ENTREE_MATCH);
	}

	private void preparerEntreeMatch() {
		ENTREE_MATCH.setVille("Quebec");
		ENTREE_MATCH.setNomCentreSportif("nom");
		ENTREE_MATCH.setNomSection("section");
		ENTREE_MATCH.setCategorieSiege("cate");
		ENTREE_MATCH.setPrix(33.00);
		ENTREE_MATCH.setType("type");
		ENTREE_MATCH.setSportType("st");
		ENTREE_MATCH.setSportSexe("ss");
		ENTREE_MATCH.setEquipeVisiteur("eq");
		ENTREE_MATCH.setMatchID("1");
		ENTREE_MATCH.setDate(LA_DATE);
		ENTREE_UN_MATCH.add(ENTREE_MATCH);
	}

	private void preparerEntreeDeuxiemeMatch() {
		DEUXIEME_ENTREE_MATCH.setVille("Paris");
		DEUXIEME_ENTREE_MATCH.setNomCentreSportif("dnom");
		DEUXIEME_ENTREE_MATCH.setNomSection("dsection");
		DEUXIEME_ENTREE_MATCH.setCategorieSiege("dcate");
		DEUXIEME_ENTREE_MATCH.setPrix(34.00);
		DEUXIEME_ENTREE_MATCH.setType("dtype");
		DEUXIEME_ENTREE_MATCH.setSportType("dst");
		DEUXIEME_ENTREE_MATCH.setSportSexe("dss");
		DEUXIEME_ENTREE_MATCH.setEquipeVisiteur("deq");
		DEUXIEME_ENTREE_MATCH.setMatchID("d1");
		DEUXIEME_ENTREE_MATCH.setDate(LA_DATE2);
	}
}
