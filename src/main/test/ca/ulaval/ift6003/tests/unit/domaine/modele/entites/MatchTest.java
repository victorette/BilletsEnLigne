package ca.ulaval.ift6003.tests.unit.domaine.modele.entites;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.infrastructure.utils.DateUtility;

public class MatchTest {

	private final String NOUVELLE_VALEUR_MATCH_ID = "5";
	private final String NOUVEAU_SPORT_TYPE = "NouveauSportType";
	private final String NOUVEAU_SPORT_SEXE = "NouveauSportSexe";
	private final Date NOUVELLE_DATE_MATCH = DateUtility.getCurrentDateTime();
	private final String NOUVEAU_NOM_CENTRE_SPORTIF = "NouveauCentreSportif";
	private final String NOUVELLE_EQUIPE_MAISON = "NouvelleEquipeMaison";
	private final String NOUVELLE_EQUIPE_VISITEUR = "NouvelleEquipeVisiteur";

	private final String VALEUR_MATCH_ID = "50";
	private final String SPORT_TYPE = "SportType";
	private final String SPORT_SEXE = "SportSexe";
	private final Date DATE_MATCH = DateUtility.getCurrentDateTime();
	private final String NOM_CENTRE_SPORTIF = "CentreSportif";
	private final String EQUIPE_MAISON = "EquipeMaison";
	private final String EQUIPE_VISITEUR = "EquipeVisiteur";

	private Match matchDefaut;
	private Match matchSpecifique;

	@Before
	public void initialisationTests() {
		matchDefaut = new Match();
		matchSpecifique = new Match(VALEUR_MATCH_ID, SPORT_TYPE, SPORT_SEXE, DATE_MATCH, NOM_CENTRE_SPORTIF, EQUIPE_MAISON, EQUIPE_VISITEUR);
	}

	@Test
	public void siDeuxMatchOntLeMemeIdAlorsMemeEntiteQueRetourneVrai() {
		matchDefaut.setMatchId(VALEUR_MATCH_ID);
		assertEquals(matchDefaut.memeEntiteQue(matchSpecifique), true);
	}

	@Test
	public void siDeuxMatchNontPasLeMemeIdAlorsMemeEntiteQueRetourneFaux() {
		matchDefaut.setMatchId(NOUVELLE_VALEUR_MATCH_ID);
		assertEquals(matchDefaut.memeEntiteQue(matchSpecifique), false);
	}

	@Test
	public void siUnMatchExistantQuandSonIdEstChangeAlorsNouvelIdEstValide() {
		matchDefaut.setMatchId(NOUVELLE_VALEUR_MATCH_ID);
		assertEquals(matchDefaut.getMatchId(), NOUVELLE_VALEUR_MATCH_ID);
	}

	@Test
	public void siUnMatchExistantQuandSonSportTypeEstChangeAlorsNouveauEstValide() {
		matchDefaut.setSportType(NOUVEAU_SPORT_TYPE);
		assertEquals(matchDefaut.getSportType(), NOUVEAU_SPORT_TYPE);
	}

	@Test
	public void siUnMatchExistantQuandSonSportSexeEstChangeAlorsNouveauEstValide() {
		matchDefaut.setSportSexe(NOUVEAU_SPORT_SEXE);
		assertEquals(matchDefaut.getSportSexe(), NOUVEAU_SPORT_SEXE);
	}

	@Test
	public void siUnMatchExistantQuandSaDateEstChangeAlorsNouveauEstValide() {
		matchDefaut.setDate(NOUVELLE_DATE_MATCH);
		assertEquals(matchDefaut.getDate(), NOUVELLE_DATE_MATCH);
	}

	@Test
	public void siUnMatchExistantQuandSonCentreSportifEstChangeAlorsNouveauEstValide() {
		matchDefaut.setNomCentreSportif(NOUVEAU_NOM_CENTRE_SPORTIF);
		assertEquals(matchDefaut.getNomCentreSportif(), NOUVEAU_NOM_CENTRE_SPORTIF);
	}

	@Test
	public void siUnMatchExistantQuandSonEquipeMaisonEstChangeAlorsNouveauEstValide() {
		matchDefaut.setEquipeMaison(NOUVELLE_EQUIPE_MAISON);
		assertEquals(matchDefaut.getEquipeMaison(), NOUVELLE_EQUIPE_MAISON);
	}

	@Test
	public void siUnMatchExistantQuandSonEquipeVisiteurEstChangeAlorsNouveauEstValide() {
		matchDefaut.setEquipeVisiteur(NOUVELLE_EQUIPE_VISITEUR);
		assertEquals(matchDefaut.getEquipeVisiteur(), NOUVELLE_EQUIPE_VISITEUR);
	}

	@Test
	public void siUnMatchEstCreeAvecInformationsAlorsValeursSontValides() {
		assertEquals(matchSpecifique.getMatchId(), VALEUR_MATCH_ID);
		assertEquals(matchSpecifique.getSportType(), SPORT_TYPE);
		assertEquals(matchSpecifique.getEquipeVisiteur(), EQUIPE_VISITEUR);
		assertEquals(matchSpecifique.getEquipeMaison(), EQUIPE_MAISON);
		assertEquals(matchSpecifique.getNomCentreSportif(), NOM_CENTRE_SPORTIF);
		assertEquals(matchSpecifique.getDate(), DATE_MATCH);
		assertEquals(matchSpecifique.getSportSexe(), SPORT_SEXE);
		assertEquals(matchSpecifique.getSportType(), SPORT_TYPE);
	}

}
