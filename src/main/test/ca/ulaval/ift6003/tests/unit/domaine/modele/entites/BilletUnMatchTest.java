package ca.ulaval.ift6003.tests.unit.domaine.modele.entites;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;

public class BilletUnMatchTest {

	private final String UN_BILLET_ID = "2";
	private final String NOUVELLE_VALEUR_BILLET_ID = "5";
	private final String NOUVELLE_VALEUR_MATCH_ID = "5";
	private final String NOUVEAU_NOM_SECTION = "nouvellesection";
	private final String NOUVEAU_NOM_SIEGE = "nouveausiege";
	private final String NOUVELLE_CATEGORIE_SIEGE = "nouvellecategoriesiege";
	private final double NOUVEAU_PRIX = 19.99;

	private final String MATCH_ID = "15";
	private final String NOM_SECTION = "section";
	private final String NOM_SIEGE = "siege";
	private final String CATEGORIE_SIEGE = "categoriesiege";
	private final double PRIX = 25.00;

	private BilletUnMatch billetDefaut;
	private BilletUnMatch billetSpecifique;
	private BilletUnMatch billetSpecifiqueClone;

	@Before
	public void initialisationTests() {
		billetDefaut = new BilletUnMatch();
		billetSpecifique = new BilletUnMatch(UN_BILLET_ID, MATCH_ID, NOM_SECTION, NOM_SIEGE, CATEGORIE_SIEGE, PRIX);
		billetSpecifiqueClone = new BilletUnMatch();
	}

	@Test
	public void siOnCloneUnBilletAlorsLesDeuxBilletsOntLesMemesValeursSaufBilletId() {
		billetSpecifiqueClone = billetSpecifique.clone();

		String billetSpecifiqueModifie = billetSpecifique.toString().replaceAll("^Billet\\s[0-9]*\\s", "");
		String billetSpecifiqueCloneModifie = billetSpecifiqueClone.toString().replaceAll("^Billet\\s[0-9]*\\s", "");

		assertEquals(billetSpecifiqueModifie, billetSpecifiqueCloneModifie);
	}

	@Test
	public void SiOnCompareLaValeurIDDeDeuxBilletsDifferentsAlorsMemeEntiteQueRetourneFaux() {
		billetDefaut.setBilletId(NOUVELLE_VALEUR_BILLET_ID);
		assertEquals(billetSpecifique.memeEntiteQue(billetDefaut), false);
	}

	@Test
	public void SiOnCompareLaValeurIDDeDeuxBilletsIdentiqueAlorsMemeEntiteQueRetourneVrai() {
		billetDefaut.setBilletId(NOUVELLE_VALEUR_BILLET_ID);
		billetSpecifique.setBilletId(NOUVELLE_VALEUR_BILLET_ID);
		assertEquals(billetSpecifique.memeEntiteQue(billetDefaut), true);
		assertEquals(billetDefaut.identifiantUnique(), billetSpecifique.identifiantUnique());
	}

	@Test
	public void siUnBilletExistantQuandSonIdEstChangeAlorsNouvelIdEstValide() {
		billetDefaut.setBilletId(NOUVELLE_VALEUR_BILLET_ID);
		assertEquals(billetDefaut.getBilletId(), NOUVELLE_VALEUR_BILLET_ID);
	}

	@Test
	public void siUnBilletExistantQuandSonMatchIdEstChangeAlorsNouveauMatchIdEstValide() {
		billetDefaut.setMatch_id(NOUVELLE_VALEUR_MATCH_ID);
		assertEquals(billetDefaut.getMatch_id(), NOUVELLE_VALEUR_MATCH_ID);
	}

	@Test
	public void siUnBilletExistantQuandSonMatchIdEstChangeAlorsNouveauNomSectionEstValide() {
		billetDefaut.setNomSection(NOUVEAU_NOM_SECTION);
		assertEquals(billetDefaut.getNomSection(), NOUVEAU_NOM_SECTION);
	}

	@Test
	public void siUnBilletExistantQuandSonMatchIdEstChangeAlorsNouveauNomSiegeEstValide() {
		billetDefaut.setNomSiege(NOUVEAU_NOM_SIEGE);
		assertEquals(billetDefaut.getNomSiege(), NOUVEAU_NOM_SIEGE);
	}

	@Test
	public void siUnBilletExistantQuandSonMatchIdEstChangeAlorsNouvelleCategorieSiegeEstValide() {
		billetDefaut.setCategorie_siege(NOUVELLE_CATEGORIE_SIEGE);
		assertEquals(billetDefaut.getCategorie_siege(), NOUVELLE_CATEGORIE_SIEGE);
	}

	@Test
	public void siUnBilletExistantQuandSonMatchIdEstChangeAlorsNouveauPrixEstValide() {
		billetDefaut.setPrix(NOUVEAU_PRIX);
		assertEquals(billetDefaut.getPrix(), NOUVEAU_PRIX, 1.0e-10);
	}

	@Test
	public void siUnBilletEstCreeAvecInformationsAlorsValeursSontValides() {
		assertEquals(billetSpecifique.getMatch_id(), MATCH_ID);
		assertEquals(billetSpecifique.getNomSection(), NOM_SECTION);
	}
	
	@Test
	public void siLeTypeDuBilletEstDemandeAlorsLaBonneValeurEstRetournee() {
		String typeDuBillet = CATEGORIE_SIEGE + Double.toString(PRIX) + MATCH_ID;
		assertEquals(billetSpecifique.getType(), typeDuBillet);
	}
}
