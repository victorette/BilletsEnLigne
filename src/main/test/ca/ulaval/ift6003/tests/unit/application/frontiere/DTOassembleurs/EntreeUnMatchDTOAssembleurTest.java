package ca.ulaval.ift6003.tests.unit.application.frontiere.DTOassembleurs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.DTOassembleurs.EntreeUnMatchDTOAssembleur;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;

public class EntreeUnMatchDTOAssembleurTest {

	EntreeUnMatchDTOAssembleur entreeUnMatchDTOAssembleur;
	EntreeUnMatch mockEntreeUnMatch;
	EntreeUnMatchDTO mockEntreeUnMatchDTO;

	@Before
	public void setUp() throws Exception {
		entreeUnMatchDTOAssembleur = new EntreeUnMatchDTOAssembleur();
		mockEntreeUnMatch = mock(EntreeUnMatch.class);
		mockEntreeUnMatchDTO = mock(EntreeUnMatchDTO.class);
	}

	@Test
	public void quandEntreeMatchDTOAssembleurAlorsDTONonVide() throws Exception {

		EntreeUnMatchDTO entreeUnMatchDTO = entreeUnMatchDTOAssembleur.ajouterSpecifique(
				mockEntreeUnMatch, mockEntreeUnMatchDTO);

		assertNotNull(entreeUnMatchDTO);

		assertEquals(null, entreeUnMatchDTO.getType());
		assertEquals(0, entreeUnMatchDTO.getNombreBilletsDesires());
		assertEquals(null, entreeUnMatchDTO.getBilletID());
		assertEquals(null, entreeUnMatchDTO.getCategorieSiege());
		assertEquals(null, entreeUnMatchDTO.getNomCentreSportif());
		assertEquals(null, entreeUnMatchDTO.getNomSection());
		assertEquals(0.0, entreeUnMatchDTO.getPrix(), 1.0);
		assertEquals(0.0, entreeUnMatchDTO.getPrixTotal(), 1.0);
		assertEquals(null, entreeUnMatchDTO.getVille());
		assertEquals(0, entreeUnMatchDTO.getNombreBilletsDisponibles());
	}

	@Test
	public void quandCreerDTOVideAlorsEntreeMatchNonVide() throws Exception {

		EntreeUnMatchDTO entreeUnMatchDTO = entreeUnMatchDTOAssembleur.creerDTOVide();

		assertNotNull(entreeUnMatchDTO);
		assertEquals(null, entreeUnMatchDTO.getDate());
		assertEquals(null, entreeUnMatchDTO.getSportSexe());
		assertEquals(null, entreeUnMatchDTO.getEquipeVisiteur());
		assertEquals(null, entreeUnMatchDTO.getMatchID());
		assertEquals(null, entreeUnMatchDTO.getSportType());
		assertEquals(null, entreeUnMatchDTO.getType());
		assertEquals(0, entreeUnMatchDTO.getNombreBilletsDesires());
		assertEquals(null, entreeUnMatchDTO.getBilletID());
		assertEquals(null, entreeUnMatchDTO.getCategorieSiege());
		assertEquals(null, entreeUnMatchDTO.getNomCentreSportif());
		assertEquals(null, entreeUnMatchDTO.getNomSection());
		assertEquals(0.0, entreeUnMatchDTO.getPrix(), 1.0);
		assertEquals(0.0, entreeUnMatchDTO.getPrixTotal(), 1.0);
		assertEquals(null, entreeUnMatchDTO.getVille());
		assertEquals(0, entreeUnMatchDTO.getNombreBilletsDisponibles());
	}

}
