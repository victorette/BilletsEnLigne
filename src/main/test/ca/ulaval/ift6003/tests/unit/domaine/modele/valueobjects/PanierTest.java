package ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.panieretachats.Panier;

public class PanierTest {

	private Panier unPanier;
	private Panier unPanierMemeValeur;
	private Panier unPanierDifferent;

	private BilletSaison unBilletSaison;
	private BilletSaison unBilletSaisonDifferent;
	private BilletUnMatch unBilletUnMatch;
	private BilletUnMatch unBilletUnMatchDifferent;

	private final Map<String, BilletUnMatch> BILLET_UN_MATCH = new HashMap<String, BilletUnMatch>();
	private final Map<String, BilletSaison> BILLET_SAISON = new HashMap<String, BilletSaison>();

	private final Map<String, BilletUnMatch> BILLET_UN_MATCH_DIFFERENT = new HashMap<String, BilletUnMatch>();
	private final Map<String, BilletSaison> BILLET_SAISON_DIFFERENT = new HashMap<String, BilletSaison>();

	private final double UN_PRIX = 25.00;
	private final double UN_PRIX_DIFFERENT = 30.00;
	private final String TYPE_BILLET_SAISON = "categorie" + UN_PRIX + "1992" + "centre";
	private final String TYPE_BILLET_MATCH = "categorie" + UN_PRIX + "1";

	@Before
	public void setUp() throws Exception {
		unPanier = new Panier();
		unPanierMemeValeur = new Panier();
		unPanierDifferent = new Panier();

		unBilletSaison = new BilletSaison("1", "1992", "centre", "section", "siege", "categorie",
				UN_PRIX);
		unBilletUnMatch = new BilletUnMatch("1", "1", "section", "siege", "categorie", UN_PRIX);
		unBilletSaisonDifferent = new BilletSaison("2", "2012", "centre2", "section2", "siege2",
				"categorie2", UN_PRIX_DIFFERENT);
		unBilletUnMatchDifferent = new BilletUnMatch("2", "2", "section2", "siege2", "categorie2",
				UN_PRIX_DIFFERENT);

		BILLET_UN_MATCH.put("1", unBilletUnMatch);
		BILLET_UN_MATCH_DIFFERENT.put("2", unBilletUnMatchDifferent);
		BILLET_SAISON.put("1", unBilletSaison);
		BILLET_SAISON_DIFFERENT.put("2", unBilletSaisonDifferent);

	}

	@Test
	public void siLesDeuxPaniersOntLaMemeValeurAlorsMemeValeurQueRetourneLesMemesValeurs() {
		creerUnPanier();
		creerUnPanierMemeValeur();
		assertTrue(unPanier.memeValeurQue(unPanierMemeValeur));
	}

	@Test
	public void siLesDeuxPaniersNOntPasLaMemeValeurAlorsMemeValeurQueNeRetournePasLesMemesValeurs() {
		creerUnPanier();
		creerUnPanierDifferent();
		assertFalse(unPanier.memeValeurQue(unPanierDifferent));
	}

	@Test
	public void siJeVideLesBilletsSaisonAlorsIlNyAPlusDeBilletsSaisonsDansLePanier() {
		creerUnPanier();
		unPanier.viderBilletsSaison();
		assertTrue(unPanier.getBilletsSaison().isEmpty());
	}

	@Test
	public void siJeVideLesBilletsUnMatchAlorsIlNyAPlusDeBilletsUnMatchDansLePanier() {
		creerUnPanier();
		unPanier.viderBilletsUnMatch();
		assertTrue(unPanier.getBilletsUnMatch().isEmpty());
	}

	@Test
	public void siJajouteUnBilletSaisonDansMonPanierVideAlorsIlNeLestPlus() {
		unPanier.ajouterBilletSaison(unBilletSaison);
		assertFalse(unPanier.getBilletsSaison().isEmpty());
	}

	@Test
	public void siJajouteUneListeBilletSaisonDansMonPanierVideAlorsIlNeLestPlus() throws Exception {

		List<BilletSaison> billetsSaison = new LinkedList<BilletSaison>();
		billetsSaison.add(unBilletSaison);

		unPanier.ajouterBilletsSaison(billetsSaison);

		assertFalse(unPanier.getBilletsSaison().isEmpty());
	}

	@Test
	public void siJajouteUneListeBilletUnMatchDansMonPanierVideAlorsIlNeLestPlus() throws Exception {

		List<BilletUnMatch> billetsMatch = new LinkedList<BilletUnMatch>();
		billetsMatch.add(unBilletUnMatch);

		unPanier.ajouterBilletsUnMatch(billetsMatch);

		assertFalse(unPanier.getBilletsUnMatch().isEmpty());
	}

	@Test
	public void siJajouteUnBilletUnMatchDansMonPanierVideAlorsIlNeLestPlus() {
		unPanier.ajouterBilletUnMatch(unBilletUnMatch);
		assertFalse(unPanier.getBilletsUnMatch().isEmpty());
	}

	@Test
	public void siJeVideMonPanierDeTousBilletsAlorsIlNyAPlusDeBillets() {
		creerUnPanier();
		unPanier.vider();
		assertTrue(unPanier.getBilletsUnMatch().isEmpty());
		assertTrue(unPanier.getBilletsSaison().isEmpty());
	}

	@Test
	public void panierVideAlorsPrixTotalEtNombreBilletsZero() throws Exception {

		assertNotNull(unPanier);
		assertEquals(0.0, unPanier.prixTotal(), 1.0);
		assertEquals(0, unPanier.nombreBilletsTotal());
	}

	@Test
	public void siCreerUnPanierAlorsObtenirDeuxBillets() throws Exception {

		creerUnPanier();
		assertEquals(2, unPanier.nombreBilletsTotal());
	}

	@Test
	public void siCreerUnPanierAlorsPrixTotalEgalPrixBillet() throws Exception {

		creerUnPanier();
		assertEquals(UN_PRIX, unPanier.prixTotal(), UN_PRIX);
	}

	@Test
	public void siBilletIdVideAlorsNonTrouveDansUnPanier() throws Exception {

		String billetId = "";

		boolean contientbillet = unPanier.contient(billetId);

		assertFalse(contientbillet);
	}

	@Test
	public void quandDiminuerQuantiteDeTypeAlorsNombreBilletsDiminue() throws Exception {

		int quantiteAConserver = 0;
		creerUnPanier();
		assertEquals(2, unPanier.nombreBilletsTotal());

		unPanier.diminuerQuantiteDeType(quantiteAConserver, TYPE_BILLET_SAISON);
		assertEquals(1, unPanier.nombreBilletsTotal());

		unPanier.diminuerQuantiteDeType(quantiteAConserver, TYPE_BILLET_MATCH);
		assertEquals(0, unPanier.nombreBilletsTotal());
	}

	@Test
	public void quandCreerPanierAlorsExtraireBilletsSaison() throws Exception {
		creerUnPanier();

		Map<String, BilletSaison> billetSaison = unPanier.getBilletsSaison();

		assertNotNull(billetSaison);
		assertEquals(1, billetSaison.size());
	}

	@Test
	public void quandCreerPanierAlorsExtraireBilletUnMatch() throws Exception {
		creerUnPanier();

		Map<String, BilletUnMatch> billetUnMatch = unPanier.getBilletsUnMatch();

		assertNotNull(billetUnMatch);
		assertEquals(1, billetUnMatch.size());
	}

	@Test
	public void quandCreerPanierAlorsExtraireListeBilletsSaison() throws Exception {
		creerUnPanier();

		List<BilletSaison> listeBilletSaison = unPanier.getListeBilletsSaison();
		assertNotNull(listeBilletSaison);
		assertEquals(1, listeBilletSaison.size());
	}

	@Test
	public void quandCreerPanierAlorsExtraireListeBilletUnMatch() throws Exception {
		creerUnPanier();

		List<BilletUnMatch> listeBilletUnMatch = unPanier.getListeBilletsUnMatch();

		assertNotNull(listeBilletUnMatch);
		assertEquals(1, listeBilletUnMatch.size());
	}

	@Test
	public void nombreDeBilletsDeTypeSaison() throws Exception {
		creerUnPanier();

		int nombreBilletsSaison = unPanier.nombreDeBilletsDeType(TYPE_BILLET_SAISON);

		assertEquals(1, nombreBilletsSaison);
	}

	@Test
	public void nombreDeBilletsDeTypeMatch() throws Exception {
		creerUnPanier();

		int nombreBilletsMatch = unPanier.nombreDeBilletsDeType(TYPE_BILLET_MATCH);

		assertEquals(1, nombreBilletsMatch);
	}

	@Test
	public void quandRetirerTousBilletsDeTypeAlorsNombreBilletsDiminue() throws Exception {
		creerUnPanier();

		assertEquals(2, unPanier.nombreBilletsTotal());

		unPanier.retirerTousBilletsDeType(TYPE_BILLET_SAISON);
		assertEquals(1, unPanier.nombreBilletsTotal());

		unPanier.retirerTousBilletsDeType(TYPE_BILLET_MATCH);
		assertEquals(0, unPanier.nombreBilletsTotal());

	}

	@Test
	public void quandViderPanierAlorsNombreBilletsEgalZero() throws Exception {
		creerUnPanier();
		assertEquals(2, unPanier.nombreBilletsTotal());
		unPanier.vider();

		assertEquals(0, unPanier.nombreBilletsTotal());
	}

	@Test
	public void quandViderBilletsSaisonAlorsNombreBilletsDiminue() throws Exception {
		creerUnPanier();

		assertEquals(2, unPanier.nombreBilletsTotal());

		unPanier.viderBilletsSaison();
		assertEquals(1, unPanier.nombreBilletsTotal());

	}

	@Test
	public void quandViderBilletsMatchAlorsNombreBilletsDiminue() throws Exception {

		creerUnPanier();

		assertEquals(2, unPanier.nombreBilletsTotal());

		unPanier.viderBilletsUnMatch();
		assertEquals(1, unPanier.nombreBilletsTotal());

	}

	private void creerUnPanier() {
		unPanier = new Panier(BILLET_UN_MATCH, BILLET_SAISON);
	}

	private void creerUnPanierMemeValeur() {
		unPanierMemeValeur.setBilletsSaison(unPanier.getBilletsSaison());
		unPanierMemeValeur.setBilletsUnMatch(unPanier.getBilletsUnMatch());
	}

	private void creerUnPanierDifferent() {
		unPanierDifferent.setBilletsSaison(BILLET_SAISON_DIFFERENT);
		unPanierDifferent.setBilletsUnMatch(BILLET_UN_MATCH_DIFFERENT);
	}

}
