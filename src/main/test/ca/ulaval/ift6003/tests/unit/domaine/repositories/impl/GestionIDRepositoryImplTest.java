package ca.ulaval.ift6003.tests.unit.domaine.repositories.impl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.gestionIDs.MapNextID;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;
import ca.ulaval.ift6003.infrastructure.persistence.XMLReaderWriter;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.GestionIDRepositoryImpl;

public class GestionIDRepositoryImplTest {

	private final String XML_QUELCONQUE = "<bidon>bleh<bidon>";
	private final String NOM_ENTITE = "nom";
	private final int NEXT_ID = 9;

	private GestionIDRepositoryImpl gestionIDRepository;
	private Convertisseur<MapNextID> mockConvertisseurMapNextID;
	private XMLReaderWriter mockXmlReaderWriter;

	private MapNextID mockMapNextIds;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		gestionIDRepository = new GestionIDRepositoryImpl();
		mockConvertisseurMapNextID = mock(Convertisseur.class);
		mockXmlReaderWriter = mock(XMLReaderWriter.class);
		mockMapNextIds = mock(MapNextID.class);

		gestionIDRepository.setConvertisseur(mockConvertisseurMapNextID);
		gestionIDRepository.setXmlReaderWriter(mockXmlReaderWriter);

		List<MapNextID> liste = listeContenantMockMapNextID();
		when(mockConvertisseurMapNextID.XMLToListe(XML_QUELCONQUE)).thenReturn(liste);
		when(mockXmlReaderWriter.lireFichierXML(anyString())).thenReturn(XML_QUELCONQUE);
	}

	@Test
	public void quandSelectTousIDsAlorsFichierEstLu() {
		gestionIDRepository.selectTous();
		verify(mockXmlReaderWriter).lireFichierXML(anyString());
	}

	@Test
	public void quandSelectTousIDsAlorsConvertisseurAppellerAvecXMLResultant() {
		gestionIDRepository.selectTous();
		verify(mockConvertisseurMapNextID).XMLToListe(XML_QUELCONQUE);
	}

	@Test
	public void quandSelectNextIDAlorsNextIDestUtilisePuisMisAJour() {
		when(mockMapNextIds.getNextIdPourEntite(NOM_ENTITE)).thenReturn(NEXT_ID);

		gestionIDRepository.selectEtUpdateNextId(NOM_ENTITE);

		verify(mockMapNextIds).getNextIdPourEntite(NOM_ENTITE);
		verify(mockMapNextIds).setNextIdPourEntite(NOM_ENTITE, NEXT_ID + 1);
	}

	private List<MapNextID> listeContenantMockMapNextID() {
		List<MapNextID> liste = new ArrayList<MapNextID>();
		liste.add(mockMapNextIds);
		return liste;
	}

}
