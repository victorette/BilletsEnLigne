package ca.ulaval.ift6003.tests.unit.application.impl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIIdentifiantDejaExistant;
import ca.ulaval.ift6003.application.impl.UtilisateurManagementImpl;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.panieretachats.Panier;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.domaine.modele.utilisateur.UtilisateurRepository;
import ca.ulaval.ift6003.domaine.modele.utilisateur.UtilisateurService;

public class UtilisateurManagementImplTest {

	private final Utilisateur UTILISATEUR_NON_CONNECTE = null;
	private final Utilisateur UTILISATEUR_USERNAME_DEJAPRIS = new Utilisateur();
	private final Utilisateur UTILISATEUR_INEXISTANT = new Utilisateur();
	private final Utilisateur UTILISATEUR_CONNECTE = new Utilisateur();
	private final Panier UN_PANIER = new Panier();

	private UtilisateurManagementImpl utilisateurManagement;
	private UtilisateurService mockUtilisateurService;
	private UtilisateurRepository mockUtilisateurRepository;
	private Utilisateur mockUtilisateurActif;

	@Before
	public void setup() throws Exception {
		utilisateurManagement = new UtilisateurManagementImpl();
		mockUtilisateurRepository = mock(UtilisateurRepository.class);
		mockUtilisateurService = mock(UtilisateurService.class);
		mockUtilisateurActif = mock(Utilisateur.class);

		utilisateurManagement.setUtilisateurRepository(mockUtilisateurRepository);
		utilisateurManagement.setUtilisateurService(mockUtilisateurService);
		utilisateurManagement.setUtilisateurActif(mockUtilisateurActif);
	}

	@Test
	public void quandDemandePreferencesBilletsAlorsOnLesObtientDeLUtilisateurActif() {
		utilisateurManagement.preferencesUtilisateurs();
		verify(mockUtilisateurActif).getPreferencesBillets();
	}

	@Test
	public void quandDemandeSiUtilisateurConnecteSiIlEstConnecteAlorsRetourneVrai() {
		utilisateurManagement.setUtilisateurActif(UTILISATEUR_CONNECTE);
		assertTrue(utilisateurManagement.utilisateurEstConnecte());
	}

	@Test
	public void quandDemandeSiUtilisateurConnecteSiIlNEstPASConnecteAlorsRetourneFaux() {
		utilisateurManagement.setUtilisateurActif(UTILISATEUR_NON_CONNECTE);
		assertFalse(utilisateurManagement.utilisateurEstConnecte());
	}

	@Test
	public void quandDemandeListeTousUtilisateursAlorsRepositoryContactePourTousUtilisateurs() {
		utilisateurManagement.getListeUtilisateurs();
		verify(mockUtilisateurRepository).selectTous();
	}

	@Test
	public void quandDemandeNomUtilisateurAlorsLeNomEstDemandeSurLutilisateurActif() {
		utilisateurManagement.nomDeLutilisateur();
		verify(mockUtilisateurActif).getNomUtilisateur();
	}

	@Test
	public void quandLoginAnonymeAlorsOnCreeUnUtilisateurAnonyme() {
		utilisateurManagement.loginUtilisateurAnonyme();
		verify(mockUtilisateurService).loginUtilisateurAnonyme();
	}

	@Test
	public void quandLoginAnonymeAlorsCreeUnNouveauPanierVide() {
		utilisateurManagement.loginUtilisateurAnonyme();
		assertEquals(utilisateurManagement.getPanier().nombreBilletsTotal(), 0);
	}

	@Test
	public void quandLogoutUtilisateurActifAlorsUtilisateurEstDeconnecte() {
		utilisateurManagement.logoutUtilisateurActif();
		assertNull(utilisateurManagement.getUtilisateurActif());
	}

	@Test
	public void quandLogoutUtilisateurActifAlorsPanierEstDetruit() {
		utilisateurManagement.setPanier(UN_PANIER);
		utilisateurManagement.logoutUtilisateurActif();
		assertNull(utilisateurManagement.getPanier());
	}

	@Test(expected = UIIdentifiantDejaExistant.class)
	public void quandInscrireNouvelUtilisateurSiUsernameDejaExistantAlorsExceptionAttrapee() throws Exception {
		doThrow(new IdentifiantDejaExistant()).when(mockUtilisateurService).inscrireNouvelUtilisateur(UTILISATEUR_USERNAME_DEJAPRIS);
		utilisateurManagement.inscrireNouvelUtilisateur(UTILISATEUR_USERNAME_DEJAPRIS);
	}

	@Test(expected = UIEntiteInexistante.class)
	public void quandLoginParCredentielsSiUtilisateurNonTrouveAlorsExceptionAttrapee() throws Exception {
		doThrow(new EntiteNonTrouvee()).when(mockUtilisateurService).loginUtilisateurParCredentiels(anyString(), anyString());
		utilisateurManagement.loginUtilisateurParCredentiels("username", "password");
	}

	@Test
	public void quandLoginParCredentielsSiUtilisateurExisteAlorsPanierEstCree() throws UIEntiteInexistante {
		utilisateurManagement.loginUtilisateurParCredentiels("username", "password");
		assertTrue(utilisateurManagement.getPanier() != null);
	}

	@Test(expected = UIEntiteInexistante.class)
	public void quandModifierCompteUtilisateurNonExistantAlorsExceptionEstAttrapee() throws Exception {
		doThrow(new EntiteNonTrouvee()).when(mockUtilisateurRepository).update(UTILISATEUR_INEXISTANT);
		utilisateurManagement.modifierCompteUtilisateurExistant(UTILISATEUR_INEXISTANT);
	}
}
