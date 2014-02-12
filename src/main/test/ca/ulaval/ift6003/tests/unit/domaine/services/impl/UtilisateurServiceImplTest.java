package ca.ulaval.ift6003.tests.unit.domaine.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.domaine.modele.utilisateur.UtilisateurRepository;
import ca.ulaval.ift6003.infrastructure.services.UtilisateurServiceImpl;

public class UtilisateurServiceImplTest {

	private final Utilisateur UN_UTILISATEUR = new Utilisateur();

	private UtilisateurServiceImpl utilisateurService;
	private UtilisateurRepository mockUtilisateurRepositoryImpl;

	@Before
	public void setup() {
		utilisateurService = new UtilisateurServiceImpl();
		mockUtilisateurRepositoryImpl = mock(UtilisateurRepository.class);

		utilisateurService.setUtilisateurRepository(mockUtilisateurRepositoryImpl);
	}

	@Test
	public void quandAjouterUtilisateurAlorsRepositoryAppelleAvecBonUtilisateur()
			throws IdentifiantDejaExistant {
		// voir UtilisateurService.nomUtilisateurNexistePasDeja()
		utilisateurService.inscrireNouvelUtilisateur(UN_UTILISATEUR);
		verify(mockUtilisateurRepositoryImpl).inserer(UN_UTILISATEUR);
	}

	@Test
	public void quandLoginUtilisateurAnonymeAlorsConnexionAnonyme() throws Exception {

		Utilisateur utilisateur = utilisateurService.loginUtilisateurAnonyme();

		assertNotNull(utilisateur);
		assertEquals("", utilisateur.getPrenom());
		assertEquals("", utilisateur.getNom());
		assertEquals("", utilisateur.getCourriel());
		assertEquals("", utilisateur.identifiantUnique());
		assertEquals("", utilisateur.getNomUtilisateur());
		assertEquals("", utilisateur.getMotDePasse());
	}

	@Test
	public void quandLoginUtilisateurParCredentielsAlorsConnexionCree() throws Exception {

		String nomUtilisateur = "";
		String motDePasse = "";

		when(mockUtilisateurRepositoryImpl.selectParCredentiels(nomUtilisateur, motDePasse))
				.thenReturn(UN_UTILISATEUR);

		Utilisateur utilisateur = utilisateurService.loginUtilisateurParCredentiels(nomUtilisateur,
				motDePasse);

		assertNotNull(utilisateur);
	}
}
