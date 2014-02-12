package ca.ulaval.ift6003.tests.unit.domaine.repositories.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;
import ca.ulaval.ift6003.infrastructure.persistence.XMLReaderWriter;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.MatchRepositoryImpl;

public class MatchRepositoryImplTest {

	private final String XML_QUELCONQUE = "<bidon>bleh<bidon>";
	private final Match UN_MATCH = new Match();
	private final Match UN_AUTRE_MATCH = new Match();
	private final String UN_ID = "1";
	private final String UN_AUTRE_ID = "2";
	private final String UN_FAUX_ID = "11";
	private final String FICHIER_MATCHS = new java.io.File("").getAbsolutePath() + Consts.FICHIER_MATCHS;

	private List<Match> LISTE_MATCHS;

	private MatchRepositoryImpl matchRepositoryImpl;
	private Convertisseur<Match> mockConvertisseur;
	private XMLReaderWriter mockXmlReaderWriter;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		matchRepositoryImpl = new MatchRepositoryImpl();
		mockConvertisseur = mock(Convertisseur.class);
		mockXmlReaderWriter = mock(XMLReaderWriter.class);

		matchRepositoryImpl.setConvertisseur(mockConvertisseur);
		matchRepositoryImpl.setXmlReaderWriter(mockXmlReaderWriter);

		LISTE_MATCHS = preparerListe();
		when(mockXmlReaderWriter.lireFichierXML(anyString())).thenReturn(XML_QUELCONQUE);
		when(mockConvertisseur.XMLToListe(XML_QUELCONQUE)).thenReturn(LISTE_MATCHS);
	}

	@Test
	public void quandSelectTousMatchsAlorsFichierEstLu() {
		matchRepositoryImpl.selectTous();
		verify(mockXmlReaderWriter).lireFichierXML(anyString());
	}

	@Test
	public void quandSelectTousMatchsAlorsConvertisseurAppellerAvecXMLResultant() {
		when(mockXmlReaderWriter.lireFichierXML(anyString())).thenReturn(XML_QUELCONQUE);
		matchRepositoryImpl.selectTous();
		verify(mockConvertisseur).XMLToListe(XML_QUELCONQUE);
	}

	@Test
	public void quandSelectTousMatchsMAPAlorsFichierEstLu() {
		matchRepositoryImpl.selectTousMap();
		verify(mockXmlReaderWriter).lireFichierXML(anyString());
	}

	@Test
	public void quandSelectTousMatchsMAPAlorsConvertisseurAppellerAvecXMLResultant() {
		when(mockXmlReaderWriter.lireFichierXML(anyString())).thenReturn(XML_QUELCONQUE);
		matchRepositoryImpl.selectTousMap();
		verify(mockConvertisseur).XMLToListe(XML_QUELCONQUE);
	}

	@Test
	public void quandSelectMatchParIDSiExisteAlorsObtientMatch() throws EntiteNonTrouvee {
		Match resultat = matchRepositoryImpl.selectParIdentifiantUnique(UN_ID);
		assertEquals(UN_MATCH, resultat);
	}

	@Test(expected = EntiteNonTrouvee.class)
	public void quandSelectMatchParIDSiNExistePASAlorsObtientNULLMatch() throws EntiteNonTrouvee {
		matchRepositoryImpl.selectParIdentifiantUnique(UN_FAUX_ID);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandInsereUnMatchAlorsMatchEstAjouterAuXMLViaConvertisseur() {
		matchRepositoryImpl.inserer(UN_MATCH);
		verify(mockConvertisseur).listeToXML((List<Match>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandInsereUnMatchAlorsBonXMLEstEcritDansFichier() {
		when(mockConvertisseur.listeToXML((List<Match>) anyObject())).thenReturn(XML_QUELCONQUE);
		matchRepositoryImpl.inserer(UN_MATCH);
		verify(mockXmlReaderWriter).ecrireDansFichierXML(XML_QUELCONQUE, FICHIER_MATCHS);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandSupprimeUnMatchAlorsListeSansCeMatchEstConvertieEnXML() throws EntiteNonTrouvee {
		matchRepositoryImpl.supprimerParIdentifiantUnique(UN_ID);
		verify(mockConvertisseur).listeToXML((List<Match>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandSupprimeUnBilletAlorsBonXMLEstEcritDansFichier() throws EntiteNonTrouvee {
		when(mockConvertisseur.listeToXML((List<Match>) anyObject())).thenReturn(XML_QUELCONQUE);
		matchRepositoryImpl.supprimerParIdentifiantUnique(UN_ID);
		verify(mockXmlReaderWriter).ecrireDansFichierXML(XML_QUELCONQUE, FICHIER_MATCHS);
	}

	private List<Match> preparerListe() {
		List<Match> matchs = new ArrayList<Match>();
		UN_MATCH.setMatchId(UN_ID);
		UN_AUTRE_MATCH.setMatchId(UN_AUTRE_ID);

		matchs.add(UN_MATCH);
		matchs.add(UN_AUTRE_MATCH);
		return matchs;
	}

}
