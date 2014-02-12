package ca.ulaval.ift6003.tests.unit.application.frontiere.DTOassembleurs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.DTOassembleurs.EntreeSaisonDTOAssembleur;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;

public class EntreeSaisonDTOAssembleurTest {
	EntreeSaisonDTOAssembleur entreeSaisonDTOAssembleur;
	EntreeSaison mockEntreeSaison;
	EntreeSaisonDTO mockEntreeSaisonDTO;

	@Before
	public void setUp() throws Exception {
		entreeSaisonDTOAssembleur = new EntreeSaisonDTOAssembleur();
		mockEntreeSaison = mock(EntreeSaison.class);
		mockEntreeSaisonDTO = mock(EntreeSaisonDTO.class);

	}

	@Test
	public void quandEntreeSaisonDTOAssembleurAlorsDTONonVide() {

		EntreeSaisonDTO entreeSaisonDTO = entreeSaisonDTOAssembleur.ajouterSpecifique(
				mockEntreeSaison, mockEntreeSaisonDTO);

		assertNotNull(entreeSaisonDTO);

		assertEquals(null, entreeSaisonDTO.getType());
		assertEquals(0, entreeSaisonDTO.getNombreBilletsDesires());
		assertEquals(null, entreeSaisonDTO.getBilletID());
		assertEquals(null, entreeSaisonDTO.getCategorieSiege());
		assertEquals(null, entreeSaisonDTO.getNomCentreSportif());
		assertEquals(null, entreeSaisonDTO.getNomSection());
		assertEquals(0.0, entreeSaisonDTO.getPrix(), 1.0);
		assertEquals(0.0, entreeSaisonDTO.getPrixTotal(), 1.0);
		assertEquals(null, entreeSaisonDTO.getVille());
		assertEquals(0, entreeSaisonDTO.getNombreBilletsDisponibles());
	}

	@Test
	public void quandCreerDTOVideAlorsEntreeSaisonNonVide() throws Exception {

		EntreeSaisonDTO entreeSaisonDTO = entreeSaisonDTOAssembleur.creerDTOVide();

		assertNotNull(entreeSaisonDTO);
		assertEquals(null, entreeSaisonDTO.getAnnee());
		assertEquals(null, entreeSaisonDTO.getType());
		assertEquals(0, entreeSaisonDTO.getNombreBilletsDesires());
		assertEquals(null, entreeSaisonDTO.getBilletID());
		assertEquals(null, entreeSaisonDTO.getCategorieSiege());
		assertEquals(null, entreeSaisonDTO.getNomCentreSportif());
		assertEquals(null, entreeSaisonDTO.getNomSection());
		assertEquals(0.0, entreeSaisonDTO.getPrix(), 1.0);
		assertEquals(0.0, entreeSaisonDTO.getPrixTotal(), 1.0);
		assertEquals(null, entreeSaisonDTO.getVille());
		assertEquals(0, entreeSaisonDTO.getNombreBilletsDisponibles());
	}

}
