package ca.ulaval.ift6003.tests.unit.domaine.modele.entites;

import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CentreSportifTest {

	private final String NOUVEAU_NOM_CENTRE_SPORTIF = "NouveauCentreSportif";
	private final String NOUVELLE_VILLE_CENTRE_SPORTIF = "NouvelleVille";
	private final List<String> NOUVEAUXTERRAINS = new ArrayList<String>();
	private final List<String> NOUVELLESSECTIONS = new ArrayList<String>();

	private final String NOM_CENTRE_SPORTIF = "CentreSportif";
	private final String VILLE_CENTRE_SPORTIF = "Ville";
	private final List<String> TERRAINS = new ArrayList<String>();
	private final List<String> SECTIONS = new ArrayList<String>();

	private CentreSportif centreSportifDefaut;
	private CentreSportif centreSportifSpecifique;

	@Before
	public void initialisationTests() {
		centreSportifDefaut = new CentreSportif();
		centreSportifSpecifique = new CentreSportif(NOM_CENTRE_SPORTIF, VILLE_CENTRE_SPORTIF, TERRAINS, SECTIONS);
	}

	@Test
	public void siUnCentreSportifExistantQuandSonNomEstChangeAlorsNouveauValide() {
		centreSportifDefaut.setNom(NOUVEAU_NOM_CENTRE_SPORTIF);
		assertEquals(centreSportifDefaut.getNom(), NOUVEAU_NOM_CENTRE_SPORTIF);
	}

	@Test
	public void siDeuxCentreSportifOntLeMemeNomAlorsMemeEntiteQueRetourneVrai() {
		centreSportifDefaut.setNom(NOM_CENTRE_SPORTIF);
		assertEquals(centreSportifDefaut.memeEntiteQue(centreSportifSpecifique), true);
	}

	@Test
	public void siDeuxCentreSportifOntLeMemeNomAlorsMemeEntiteQueRetourneFaux() {
		centreSportifDefaut.setNom(NOUVEAU_NOM_CENTRE_SPORTIF);
		assertEquals(centreSportifDefaut.memeEntiteQue(centreSportifSpecifique), false);
	}

	@Test
	public void siUnCentreSportifExistantQuandSaVilleEstChangeAlorsNouveauValide() {
		centreSportifDefaut.setVille(NOUVELLE_VILLE_CENTRE_SPORTIF);
		assertEquals(centreSportifDefaut.getVille(), NOUVELLE_VILLE_CENTRE_SPORTIF);
	}

	@Test
	public void siUnCentreSportifExistantQuandSesTerrainsOntChangeAlorsNouveauxTerrainsValides() {
		centreSportifDefaut.setTerrains(NOUVEAUXTERRAINS);
		assertEquals(centreSportifDefaut.getTerrains(), NOUVEAUXTERRAINS);
	}

	@Test
	public void siUnCentreSportifExistantQuandSesSectionsChangentAlorsNouvellesSectionsValides() {
		centreSportifDefaut.setSections(NOUVELLESSECTIONS);
		assertEquals(centreSportifDefaut.getSections(), NOUVELLESSECTIONS);
	}

	@Test
	public void siUnCentreSportifEstCreeAvecInformationsAlorsValeursSontValides() {
		assertEquals(centreSportifSpecifique.getNom(), NOM_CENTRE_SPORTIF);
		assertEquals(centreSportifSpecifique.getVille(), VILLE_CENTRE_SPORTIF);
	}

}
