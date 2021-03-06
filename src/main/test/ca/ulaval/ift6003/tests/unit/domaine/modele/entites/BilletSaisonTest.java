package ca.ulaval.ift6003.tests.unit.domaine.modele.entites;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;

public class BilletSaisonTest {

	private final String UN_BILLET_ID = "2";
	private final String NOUVELLE_VALEUR_BILLET_ID = "5";
	private final String NOUVELLE_ANNEE = "2014";
	private final String NOUVEAU_NOM_CENTRE_SPORTIF = "nouveaunom";
	private final String NOUVEAU_NOM_SECTION = "nouvellesection";
	private final String NOUVEAU_NOM_SIEGE = "nouveausiege";
	private final String NOUVELLE_CATEGORIE_SIEGE = "nouvellecategoriesiege";
	private final double NOUVEAU_PRIX = 199.99;

	private final String ANNEE = "2015";
	private final String NOM_CENTRE_SPORTIF = "centresportif";
	private final String NOM_SECTION = "section";
	private final String NOM_SIEGE = "siege";
	private final String CATEGORIE_SIEGE = "categoriesiege";
	private final double PRIX = 25.00;

	private BilletSaison billetDefaut;
	private BilletSaison billetSpecifique;
	private BilletSaison billetSpecifiqueClone;

	@Before
	public void initialisationTests() {
		billetDefaut = new BilletSaison();
		billetSpecifique = new BilletSaison(UN_BILLET_ID, ANNEE, NOM_CENTRE_SPORTIF, NOM_SECTION, NOM_SIEGE, CATEGORIE_SIEGE, PRIX);
		billetSpecifiqueClone = new BilletSaison();
	}

	@Test
	public void siOnCloneUnBilletAlorsLesDeuxBilletsOntLesMemesValeursSaufBilletId() {

		billetSpecifiqueClone = billetSpecifique.clone();
				
		String billetSpecifiqueModifie = billetSpecifique.toString().replaceAll("Billet\\s[0-9]*\\s", "");
		String billetSpecifiqueCloneModifie = billetSpecifiqueClone.toString().replaceAll("Billet\\s[0-9]*\\s", "");
				
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
	public void siUnBilletExistantQuandSonMatchIdEstChangeAlorsNouveauCentreSportifEstValide() {
		billetDefaut.setNomCentreSportif(NOUVEAU_NOM_CENTRE_SPORTIF);
		assertEquals(billetDefaut.getNomCentreSportif(), NOUVEAU_NOM_CENTRE_SPORTIF);
	}

	@Test
	public void siUnBilletExistantQuandSonMatchIdEstChangeAlorsNouvelleAnneeEstValide() {
		billetDefaut.setAnnee(NOUVELLE_ANNEE);
		assertEquals(billetDefaut.getAnnee(), NOUVELLE_ANNEE);
	}

	@Test
	public void siUnBilletEstCreeAvecInformationsAlorsValeursSontValides() {
		assertEquals(billetSpecifique.getAnnee(), ANNEE);
		assertEquals(billetSpecifique.getNomSection(), NOM_SECTION);
	}

	@Test
	public void siLeTypeDuBilletEstDemandeAlorsLaBonneValeurEstRetournee() {
		String typeDuBillet = CATEGORIE_SIEGE + Double.toString(PRIX) + ANNEE + NOM_CENTRE_SPORTIF;
		assertEquals(billetSpecifique.getType(), typeDuBillet);
	}
}
