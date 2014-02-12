package ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;

public class EntreeSaisonTest {

	private EntreeSaison uneEntreeSaison;
	private EntreeSaison uneEntreeSaisonMemeValeur;
	private EntreeSaison uneEntreeSaisonDifferente;

	private final String ANNEE = "1994";
	private final String CATEGORIE_SIEGE = "categoriesiege";
	private final String NOM_CENTRE_SPORTIF = "centresportif";
	private final String NOM_SECTION = "section";
	private final double PRIX = 25.00;
	private final String TYPE = "type";
	private final String VILLE = "ville";

	private final String ANNEE_DIFFERENTE = "2008";
	private final String CATEGORIE_SIEGE_DIFFERENTE = "categoriesiegedifferente";
	private final String NOM_CENTRE_SPORTIF_DIFFERENT = "centresportifdifferent";
	private final String NOM_SECTION_DIFFERENTE = "sectiondifferente";
	private final double PRIX_DIFFERENT = 30.00;
	private final String TYPE_DIFFERENT = "typedifferent";
	private final String VILLE_DIFFERENTE = "villedifferente";

	@Before
	public void setUp() {
		uneEntreeSaison = new EntreeSaison();
		uneEntreeSaisonMemeValeur = new EntreeSaison();
		uneEntreeSaisonDifferente = new EntreeSaison();
	}

	@Test
	public void siLesDeuxEntreesSaisonsOntLaMemeValeurAlorsMemeValeurQueRetourneLesMemesValeurs() {
		creerUneEntreeSaison();
		copierUneEntreeSaisonDansUneEntreeSaisonMemeValeur();
		assertTrue(uneEntreeSaison.memeValeurQue(uneEntreeSaisonMemeValeur));
	}

	@Test
	public void siLesDeuxEntreesSaisonsNOntPasLaMemeValeurAlorsMemeValeurQueNeRetournePasLesMemesValeurs() {
		creerUneEntreeSaison();
		creerUneEntreeSaisonDifferente();

		assertFalse(uneEntreeSaison.memeValeurQue(uneEntreeSaisonDifferente));
	}

	private void creerUneEntreeSaison() {
		uneEntreeSaison.setAnnee(ANNEE);
		uneEntreeSaison.setCategorieSiege(CATEGORIE_SIEGE);
		uneEntreeSaison.setNomCentreSportif(NOM_CENTRE_SPORTIF);
		uneEntreeSaison.setNomSection(NOM_SECTION);
		uneEntreeSaison.setPrix(PRIX);
		uneEntreeSaison.setType(TYPE);
		uneEntreeSaison.setVille(VILLE);
	}

	private void copierUneEntreeSaisonDansUneEntreeSaisonMemeValeur() {
		uneEntreeSaisonMemeValeur.setAnnee(uneEntreeSaison.getAnnee());
		uneEntreeSaisonMemeValeur.setCategorieSiege(uneEntreeSaison.getCategorieSiege());
		uneEntreeSaisonMemeValeur.setNomCentreSportif(uneEntreeSaison.getNomCentreSportif());
		uneEntreeSaisonMemeValeur.setNomSection(uneEntreeSaison.getNomSection());
		uneEntreeSaisonMemeValeur.setPrix(uneEntreeSaison.getPrix());
		uneEntreeSaisonMemeValeur.setType(uneEntreeSaison.getType());
		uneEntreeSaisonMemeValeur.setVille(uneEntreeSaison.getVille());
		uneEntreeSaisonMemeValeur.setBilletID(uneEntreeSaison.getBilletID());
	}

	private void creerUneEntreeSaisonDifferente() {
		uneEntreeSaisonDifferente.setAnnee(ANNEE_DIFFERENTE);
		uneEntreeSaisonDifferente.setCategorieSiege(CATEGORIE_SIEGE_DIFFERENTE);
		uneEntreeSaisonDifferente.setNomCentreSportif(NOM_CENTRE_SPORTIF_DIFFERENT);
		uneEntreeSaisonDifferente.setNomSection(NOM_SECTION_DIFFERENTE);
		uneEntreeSaisonDifferente.setPrix(PRIX_DIFFERENT);
		uneEntreeSaisonDifferente.setType(TYPE_DIFFERENT);
		uneEntreeSaisonDifferente.setVille(VILLE_DIFFERENTE);
	}

}
