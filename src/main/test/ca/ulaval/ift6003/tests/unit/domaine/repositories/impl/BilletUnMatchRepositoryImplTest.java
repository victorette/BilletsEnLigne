package ca.ulaval.ift6003.tests.unit.domaine.repositories.impl;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;
import ca.ulaval.ift6003.infrastructure.persistence.XMLReaderWriter;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletUnMatchRepositoryImpl;

public class BilletUnMatchRepositoryImplTest {

	private final String XML_QUELCONQUE = "<bidon>bleh<bidon>";
	private final BilletUnMatch UN_BILLET = new BilletUnMatch();
	private final String BILLET_ID = "1";
	private final String MATCH_ID = "2";
	private final String FICHIER_BILLETS = new java.io.File("").getAbsolutePath() + Consts.FICHIER_BILLETS_UNMATCH;

	private Convertisseur<BilletUnMatch> mockConvertisseur;
	private BilletUnMatchRepositoryImpl billetRepository;
	private XMLReaderWriter mockXmlReaderWriter;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		billetRepository = new BilletUnMatchRepositoryImpl();
		mockConvertisseur = mock(Convertisseur.class);
		mockXmlReaderWriter = mock(XMLReaderWriter.class);

		billetRepository.setConvertisseur(mockConvertisseur);
		billetRepository.setXmlReaderWriter(mockXmlReaderWriter);
	}

	@Test
	public void quandSelectTousBilletsAlorsFichierEstLu() {
		billetRepository.selectTous();
		verify(mockXmlReaderWriter).lireFichierXML(anyString());
	}

	@Test
	public void quandSelectTousBilletsAlorsConvertisseurAppellerAvecXMLResultant() {
		when(mockXmlReaderWriter.lireFichierXML(anyString())).thenReturn(XML_QUELCONQUE);
		billetRepository.selectTous();
		verify(mockConvertisseur).XMLToListe(XML_QUELCONQUE);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandInsereUnBilletAlorsBilletEstAjouterAuXMLViaConvertisseur() {
		billetRepository.inserer(UN_BILLET);
		verify(mockConvertisseur).listeToXML((List<BilletUnMatch>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandInsereUnBilletAlorsBonXMLEstEcritDansFichier() {
		when(mockConvertisseur.listeToXML((List<BilletUnMatch>) anyObject())).thenReturn(XML_QUELCONQUE);
		billetRepository.inserer(UN_BILLET);
		verify(mockXmlReaderWriter).ecrireDansFichierXML(XML_QUELCONQUE, FICHIER_BILLETS);
	}

	@Test(expected = EntiteNonTrouvee.class)
	public void quandSupprimeUnBilletAlorsListeSansCeBilletEstConvertieEnXML() throws EntiteNonTrouvee {

		billetRepository.supprimerParIdentifiantUnique(BILLET_ID);

	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandSupprimePlusieursBilletsAlorsListeSansCesBilletsEstConvertieEnXML() {
		billetRepository.supprimerTousAyantMatchID(MATCH_ID);
		verify(mockConvertisseur).listeToXML((List<BilletUnMatch>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandSupprimePlusieursBilletsAlorsBonXMLEstEcritDansFichier() {
		when(mockConvertisseur.listeToXML((List<BilletUnMatch>) anyObject())).thenReturn(XML_QUELCONQUE);
		billetRepository.supprimerTousAyantMatchID(MATCH_ID);
		verify(mockXmlReaderWriter).ecrireDansFichierXML(XML_QUELCONQUE, FICHIER_BILLETS);
	}

}
