package ca.ulaval.ift6003.tests.unit.domaine.repositories.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;
import ca.ulaval.ift6003.infrastructure.persistence.XMLReaderWriter;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.CentreSportifRepositoryImpl;

public class CentreSportifRepositoryImplTest {

	private final String XML_QUELCONQUE = "<bidon>bleh<bidon>";
	private final CentreSportif UN_CENTRE = new CentreSportif();
	private final CentreSportif UN_AUTRE_CENTRE = new CentreSportif();
	private final String UN_NOM = "Colis";
	private final String UN_AUTRE_NOM = "Centre";
	private final String UN_FAUX_NOM = "Pomme";
	private final String NOM_FICHIER = new java.io.File("").getAbsolutePath()
			+ Consts.CENTRESSPORTIFS;

	private List<CentreSportif> LISTE_CENTRES;
	private CentreSportifRepositoryImpl centreSportifRepositoryImpl;
	private Convertisseur<CentreSportif> mockConvertisseur;
	private XMLReaderWriter mockXmlReaderWriter;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		centreSportifRepositoryImpl = new CentreSportifRepositoryImpl();
		mockConvertisseur = mock(Convertisseur.class);
		mockXmlReaderWriter = mock(XMLReaderWriter.class);

		centreSportifRepositoryImpl.setConvertisseur(mockConvertisseur);
		centreSportifRepositoryImpl.setXmlReaderWriter(mockXmlReaderWriter);

		LISTE_CENTRES = preparerListe();

		when(mockXmlReaderWriter.lireFichierXML(anyString())).thenReturn(XML_QUELCONQUE);
		when(mockConvertisseur.XMLToListe(XML_QUELCONQUE)).thenReturn(LISTE_CENTRES);
	}

	@Test
	public void quandSelectTousCentresAlorsFichierEstLu() {
		centreSportifRepositoryImpl.selectTous();
		verify(mockXmlReaderWriter).lireFichierXML(anyString());
	}

	@Test
	public void quandSelectTousCentresAlorsConvertisseurAppellerAvecXMLResultant() {
		centreSportifRepositoryImpl.selectTous();
		verify(mockConvertisseur).XMLToListe(XML_QUELCONQUE);
	}

	@Test
	public void quandSelectCentreSportifParNomSiExisteAlorsObtientCentreSportif()
			throws EntiteNonTrouvee {
		CentreSportif resultat = centreSportifRepositoryImpl.selectParIdentifiantUnique(UN_NOM);
		assertEquals(resultat, UN_CENTRE);
	}

	@Test(expected = EntiteNonTrouvee.class)
	public void quandSelectUtilisateurParNomSiNexistePasAlorsObtientException()
			throws EntiteNonTrouvee {
		centreSportifRepositoryImpl.selectParIdentifiantUnique(UN_FAUX_NOM);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandInsereUnCentreAlorsCentreEstAjouterAuXMLViaConvertisseur() {
		centreSportifRepositoryImpl.inserer(UN_CENTRE);
		verify(mockConvertisseur).listeToXML((List<CentreSportif>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandInsereUnCentreAlorsBonXMLEstEcritDansFichier() {
		when(mockConvertisseur.listeToXML((List<CentreSportif>) anyObject())).thenReturn(
				XML_QUELCONQUE);
		centreSportifRepositoryImpl.inserer(UN_CENTRE);
		verify(mockXmlReaderWriter).ecrireDansFichierXML(XML_QUELCONQUE, NOM_FICHIER);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandSupprimeUnCentreAlorsListeSansCeCentreEstConvertieEnXML()
			throws EntiteNonTrouvee {
		centreSportifRepositoryImpl.supprimerParIdentifiantUnique(UN_NOM);
		verify(mockConvertisseur).listeToXML((List<CentreSportif>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandSupprimeUnBilletAlorsBonXMLEstEcritDansFichier() throws EntiteNonTrouvee {
		when(mockConvertisseur.listeToXML((List<CentreSportif>) anyObject())).thenReturn(
				XML_QUELCONQUE);
		centreSportifRepositoryImpl.supprimerParIdentifiantUnique(UN_NOM);
		verify(mockXmlReaderWriter).ecrireDansFichierXML(XML_QUELCONQUE, NOM_FICHIER);
	}

	private List<CentreSportif> preparerListe() {
		List<CentreSportif> centres = new ArrayList<CentreSportif>();
		UN_CENTRE.setNom(UN_NOM);
		UN_AUTRE_CENTRE.setNom(UN_AUTRE_NOM);

		centres.add(UN_CENTRE);
		centres.add(UN_AUTRE_CENTRE);
		return centres;
	}

	@Test
	public void quandCentreSportifRepositoryImplAlorsXMLEstNonVide() throws Exception {

		assertNotNull(centreSportifRepositoryImpl);
		assertEquals(mockXmlReaderWriter, centreSportifRepositoryImpl.getXmlReaderWriter());
	}

	@Test
	public void quandSelectionnerCentreSportifAlorsReferentielNonVide() throws Exception {

		Set<String> IDs = new HashSet<String>();

		Map<String, CentreSportif> mapCentreSportif = centreSportifRepositoryImpl
				.selectTousAyantIDs(IDs);

		assertNotNull(mapCentreSportif);
	}

}
