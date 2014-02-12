package ca.ulaval.ift6003.tests.unit.application.frontiere.DTOassembleurs;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.DTOassembleurs.AssembleurService;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;

public class AssembleurServiceTest {

	Inventaire<EntreeSaison> mockinventaireEntreeSaison;
	Inventaire<EntreeUnMatch> mockinventaireEntreeMatch;
	Map<String, Integer> quantiteDispoParType = new HashMap<String, Integer>();
	AssembleurService assembleurService;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {

		mockinventaireEntreeSaison = mock(Inventaire.class);
		mockinventaireEntreeMatch = mock(Inventaire.class);

		assembleurService = new AssembleurService();
		assertNotNull(assembleurService);
	}

	@Test
	public void convertirInventaireUnMatchAlorsListeEntreeSaisonNonVide() {

		List<EntreeSaisonDTO> listeEntreeSaisonDTO = AssembleurService.convertirInventaireSaisonEnDTOs(mockinventaireEntreeSaison, quantiteDispoParType);

		assertNotNull(listeEntreeSaisonDTO);
		assertEquals(0, listeEntreeSaisonDTO.size());
	}

	@Test
	public void convertirInventaireUnMatchAlorsListeEntreeMatchNonVide() throws Exception {

		List<EntreeUnMatchDTO> listeEntreeMatchDTO = AssembleurService.convertirInventaireUnMatchEnDTOs(mockinventaireEntreeMatch, quantiteDispoParType);

		assertNotNull(listeEntreeMatchDTO);
		assertEquals(0, listeEntreeMatchDTO.size());
	}

}
