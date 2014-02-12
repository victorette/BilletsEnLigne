package ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;

public class EntreeUnMatchTest {

	private EntreeUnMatch uneEntreeUnMatch;
	private EntreeUnMatch uneEntreeUnMatchMemeValeur;
	private EntreeUnMatch uneEntreeUnMatchDifferente;

	private final String SPORT_TYPE = "typesport";
	private final String SPORT_SEXE = "typesexe";
	@SuppressWarnings("deprecation")
	private final Date DATE = new Date(2012, 12, 12);
	private final String EQUIPE_VISITEUR = "equipevisiteur";
	private final String CATEGORIE_SIEGE = "categoriesiege";
	private final String NOM_CENTRE_SPORTIF = "centresportif";
	private final String NOM_SECTION = "section";
	private final double PRIX = 25.00;
	private final String TYPE = "type";
	private final String VILLE = "ville";

	private final String SPORT_TYPE_DIFFERENT = "typesportdifferent";
	private final String SPORT_SEXE_DIFFERENT = "typesexedifferent";
	@SuppressWarnings("deprecation")
	private final Date DATE_DIFFERENTE = new Date(1999, 06, 24);
	private final String EQUIPE_VISITEUR_DIFFERENTE = "equipevisiteurdifferente";
	private final String CATEGORIE_SIEGE_DIFFERENTE = "categoriesiegedifferente";
	private final String NOM_CENTRE_SPORTIF_DIFFERENT = "centresportifdifferent";
	private final String NOM_SECTION_DIFFERENTE = "sectiondifferente";
	private final double PRIX_DIFFERENT = 30.00;
	private final String TYPE_DIFFERENT = "typedifferent";
	private final String VILLE_DIFFERENTE = "villedifferente";

	@Before
	public void setUp() {
		uneEntreeUnMatch = new EntreeUnMatch();
		uneEntreeUnMatchMemeValeur = new EntreeUnMatch();
		uneEntreeUnMatchDifferente = new EntreeUnMatch();
	}

	@Test
	public void siLesDeuxEntreesUnMatchsOntLaMemeValeurAlorsMemeValeurQueRetourneLesMemesValeurs() {
		creerUneEntreeUnMatch();
		copierUneEntreeUnMatchDansUneEntreeUnMatchMemeValeur();
		assertTrue(uneEntreeUnMatch.memeValeurQue(uneEntreeUnMatchMemeValeur));
	}

	@Test
	public void siLesDeuxEntreesUnMatchsNOntPasLaMemeValeurAlorsMemeValeurQueNeRetournePasLesMemesValeurs() {
		creerUneEntreeUnMatch();
		creerUneEntreeUnMatchDifferente();

		assertFalse(uneEntreeUnMatch.memeValeurQue(uneEntreeUnMatchDifferente));
	}

	private void creerUneEntreeUnMatch() {
		uneEntreeUnMatch.setSportType(SPORT_TYPE);
		uneEntreeUnMatch.setSportSexe(SPORT_SEXE);
		uneEntreeUnMatch.setDate(DATE);
		uneEntreeUnMatch.setEquipeVisiteur(EQUIPE_VISITEUR);
		uneEntreeUnMatch.setCategorieSiege(CATEGORIE_SIEGE);
		uneEntreeUnMatch.setNomCentreSportif(NOM_CENTRE_SPORTIF);
		uneEntreeUnMatch.setNomSection(NOM_SECTION);
		uneEntreeUnMatch.setPrix(PRIX);
		uneEntreeUnMatch.setType(TYPE);
		uneEntreeUnMatch.setVille(VILLE);
	}

	private void copierUneEntreeUnMatchDansUneEntreeUnMatchMemeValeur() {
		uneEntreeUnMatchMemeValeur.setSportType(uneEntreeUnMatch.getSportType());
		uneEntreeUnMatchMemeValeur.setSportSexe(uneEntreeUnMatch.getSportSexe());
		uneEntreeUnMatchMemeValeur.setDate(uneEntreeUnMatch.getDate());
		uneEntreeUnMatchMemeValeur.setEquipeVisiteur(uneEntreeUnMatch.getEquipeVisiteur());
		uneEntreeUnMatchMemeValeur.setMatchID(uneEntreeUnMatch.getMatchID());
		uneEntreeUnMatchMemeValeur.setCategorieSiege(uneEntreeUnMatch.getCategorieSiege());
		uneEntreeUnMatchMemeValeur.setNomCentreSportif(uneEntreeUnMatch.getNomCentreSportif());
		uneEntreeUnMatchMemeValeur.setNomSection(uneEntreeUnMatch.getNomSection());
		uneEntreeUnMatchMemeValeur.setPrix(uneEntreeUnMatch.getPrix());
		uneEntreeUnMatchMemeValeur.setType(uneEntreeUnMatch.getType());
		uneEntreeUnMatchMemeValeur.setVille(uneEntreeUnMatch.getVille());
		uneEntreeUnMatchMemeValeur.setBilletID(uneEntreeUnMatch.getBilletID());
	}

	private void creerUneEntreeUnMatchDifferente() {
		uneEntreeUnMatchDifferente.setSportType(SPORT_TYPE_DIFFERENT);
		uneEntreeUnMatchDifferente.setSportSexe(SPORT_SEXE_DIFFERENT);
		uneEntreeUnMatchDifferente.setDate(DATE_DIFFERENTE);
		uneEntreeUnMatchDifferente.setEquipeVisiteur(EQUIPE_VISITEUR_DIFFERENTE);
		uneEntreeUnMatchDifferente.setCategorieSiege(CATEGORIE_SIEGE_DIFFERENTE);
		uneEntreeUnMatchDifferente.setNomCentreSportif(NOM_CENTRE_SPORTIF_DIFFERENT);
		uneEntreeUnMatchDifferente.setNomSection(NOM_SECTION_DIFFERENTE);
		uneEntreeUnMatchDifferente.setPrix(PRIX_DIFFERENT);
		uneEntreeUnMatchDifferente.setType(TYPE_DIFFERENT);
		uneEntreeUnMatchDifferente.setVille(VILLE_DIFFERENTE);
	}

}
