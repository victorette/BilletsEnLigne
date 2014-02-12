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
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;
import ca.ulaval.ift6003.infrastructure.persistence.XMLReaderWriter;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.UtilisateurRepositoryImpl;

public class UtilisateurRepositoryImplTest {

	private final String XML_QUELCONQUE = "<bidon>bleh<bidon>";
	private final Utilisateur UN_UTILISATEUR = new Utilisateur();
	private final Utilisateur UN_AUTRE_UTILISATEUR = new Utilisateur();
	private final String UN_NOM = "MartinB";
	private final String UN_AUTRE_NOM = "GeorgesG";
	private final String UN_FAUX_NOM = "Pomme";
	private final String UN_MOT_DE_PASSE = "1234";
	private final String UN_FAUX_MOT_DE_PASSE = "4321";
	private final String FICHIER_UTILISATEUR = new java.io.File("").getAbsolutePath() + Consts.FICHIER_UTILISATEURS;

	private List<Utilisateur> LISTE_UTILISATEUR;
	private UtilisateurRepositoryImpl utilisateurRepositoryImpl;
	private Convertisseur<Utilisateur> mockConvertisseur;
	private XMLReaderWriter mockXmlReaderWriter;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		utilisateurRepositoryImpl = new UtilisateurRepositoryImpl();
		mockConvertisseur = mock(Convertisseur.class);
		mockXmlReaderWriter = mock(XMLReaderWriter.class);

		utilisateurRepositoryImpl.setConvertisseur(mockConvertisseur);
		utilisateurRepositoryImpl.setXmlReaderWriter(mockXmlReaderWriter);

		LISTE_UTILISATEUR = preparerListe();

		when(mockXmlReaderWriter.lireFichierXML(anyString())).thenReturn(XML_QUELCONQUE);
		when(mockConvertisseur.XMLToListe(XML_QUELCONQUE)).thenReturn(LISTE_UTILISATEUR);
	}

	@Test
	public void quandSelectTousBilletsAlorsFichierEstLu() {
		utilisateurRepositoryImpl.selectTous();
		verify(mockXmlReaderWriter).lireFichierXML(anyString());
	}

	@Test
	public void quandSelectTousBilletsAlorsConvertisseurAppellerAvecXMLResultant() {
		utilisateurRepositoryImpl.selectTous();
		verify(mockConvertisseur).XMLToListe(XML_QUELCONQUE);
	}

	@Test
	public void quandSelectUtilisateurParNomSiExisteAlorsObtientUtilisateur() throws EntiteNonTrouvee {
		Utilisateur resultat = utilisateurRepositoryImpl.selectParIdentifiantUnique(UN_NOM);
		assertEquals(resultat, UN_UTILISATEUR);
	}

	@Test(expected = EntiteNonTrouvee.class)
	public void quandSelectUtilisateurParNomSiNexistePasAlorsObtientNull() throws EntiteNonTrouvee {
		utilisateurRepositoryImpl.selectParIdentifiantUnique(UN_FAUX_NOM);
	}

	@Test
	public void quandSelectUtilisateurParCredentielsSiExistentAlorsObtientUtilisateur() throws EntiteNonTrouvee {
		Utilisateur resultat = utilisateurRepositoryImpl.selectParCredentiels(UN_NOM, UN_MOT_DE_PASSE);
		assertEquals(resultat, UN_UTILISATEUR);
	}

	@Test(expected = EntiteNonTrouvee.class)
	public void quandSelectUtilisateurCredentielsSiNexistentPasAlorsObtientNull() throws EntiteNonTrouvee {
		utilisateurRepositoryImpl.selectParCredentiels(UN_NOM, UN_FAUX_MOT_DE_PASSE);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandInsereUnUtilisateurAlorsUtilisateurEstAjouterAuXMLViaConvertisseur() {
		utilisateurRepositoryImpl.inserer(UN_UTILISATEUR);
		verify(mockConvertisseur).listeToXML((List<Utilisateur>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandInsereUnUtilisateurAlorsBonXMLEstEcritDansFichier() {
		when(mockConvertisseur.listeToXML((List<Utilisateur>) anyObject())).thenReturn(XML_QUELCONQUE);
		utilisateurRepositoryImpl.inserer(UN_UTILISATEUR);
		verify(mockXmlReaderWriter).ecrireDansFichierXML(XML_QUELCONQUE, FICHIER_UTILISATEUR);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandModifieUnUtilisateurAlorsUtilisateurModifieEstAjouterAuXMLViaConvertisseur() throws EntiteNonTrouvee {
		utilisateurRepositoryImpl.update(UN_UTILISATEUR);
		verify(mockConvertisseur).listeToXML((List<Utilisateur>) anyObject());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void quandModifieUBilletAlorsBonXMLEstEcritDansFichier() throws EntiteNonTrouvee {
		when(mockConvertisseur.listeToXML((List<Utilisateur>) anyObject())).thenReturn(XML_QUELCONQUE);
		utilisateurRepositoryImpl.update(UN_UTILISATEUR);
		verify(mockXmlReaderWriter).ecrireDansFichierXML(XML_QUELCONQUE, FICHIER_UTILISATEUR);
	}

	private List<Utilisateur> preparerListe() {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		UN_UTILISATEUR.setNomUtilisateur(UN_NOM);
		UN_UTILISATEUR.setMotDePasse(UN_MOT_DE_PASSE);
		UN_AUTRE_UTILISATEUR.setNomUtilisateur(UN_AUTRE_NOM);
		UN_AUTRE_UTILISATEUR.setMotDePasse(UN_MOT_DE_PASSE);

		utilisateurs.add(UN_UTILISATEUR);
		utilisateurs.add(UN_AUTRE_UTILISATEUR);
		return utilisateurs;
	}

}
