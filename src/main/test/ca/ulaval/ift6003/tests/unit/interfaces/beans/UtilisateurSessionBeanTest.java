package ca.ulaval.ift6003.tests.unit.interfaces.beans;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.impl.UtilisateurManagementImpl;
import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Permissions;
import ca.ulaval.ift6003.interfaces.beans.compte.UtilisateurSessionBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class UtilisateurSessionBeanTest {

	private final String NOM_UTILISATEUR = "Jean-paul";
	private final boolean VRAI = true;
	private UtilisateurSessionBean unUtilisateurSessionDefaut;

	private UtilisateurManagementImpl mockUtilisateurManager;
	private GUIMessageHandler mockGuiMessageHandler;

	@Before
	public void setUp() throws Exception {
		unUtilisateurSessionDefaut = new UtilisateurSessionBean();
		mockUtilisateurManager = mock(UtilisateurManagementImpl.class);
		mockGuiMessageHandler = mock(GUIMessageHandler.class);

		unUtilisateurSessionDefaut.setUtilisateurManagement(mockUtilisateurManager);
		unUtilisateurSessionDefaut.setGuiMessageHandler(mockGuiMessageHandler);
	}

	@Test
	public void siUtilisateurSessionExisteQuandDemandeNomUtilisateurAlorsObtientBonNom() {
		preparerUtilisateurActifEtSonNom();
		assertEquals(unUtilisateurSessionDefaut.getNomUtilisateurActif(), NOM_UTILISATEUR);
	}

	@Test
	public void siUtilisateurActifEstConnecteAlorsLaFacadeRetourneUnUtilisateurActifConnecte() {
		when(mockUtilisateurManager.utilisateurEstConnecte()).thenReturn(VRAI);
		assertEquals(unUtilisateurSessionDefaut.isUtilisateurActifConnecte(), VRAI);
	}

	@Test
	public void siUtilisateurActifPeutCreeUnCentreSportifAlorsOnRetourneVrai() {
		when(mockUtilisateurManager.utilisateurActifALeDroitDe(Permissions.CREATION_CENTRE_SPORTIF))
				.thenReturn(VRAI);
		assertEquals(unUtilisateurSessionDefaut.utilisateurActifPeutCreerCentreSportif(), VRAI);
	}

	@Test
	public void siUtilisateurActifPeutCreeUnBilletAlorsOnRetourneVrai() {
		when(mockUtilisateurManager.utilisateurActifALeDroitDe(Permissions.CREATION_BILLET))
				.thenReturn(VRAI);
		assertEquals(unUtilisateurSessionDefaut.utilisateurActifPeutCreerBillet(), VRAI);
	}

	@Test
	public void siUtilisateurActifPeutCreeUnCentreMatchAlorsOnRetourneVrai() {
		when(mockUtilisateurManager.utilisateurActifALeDroitDe(Permissions.CREATION_MATCH))
				.thenReturn(VRAI);
		assertEquals(unUtilisateurSessionDefaut.utilisateurActifPeutCreerCentreMatch(), VRAI);
	}

	@Test
	public void siUtilisateurActifPeutModifierInfosCompteAlorsOnRetourneVrai() {
		when(mockUtilisateurManager.utilisateurActifALeDroitDe(Permissions.MODIFICATION_COMPTE))
				.thenReturn(VRAI);
		assertEquals(unUtilisateurSessionDefaut.utilisateurActifPeutModifierInfosCompte(), VRAI);
	}

	@Test
	public void siUtilisateurActifPeutSupprimerBilletAlorsOnRetourneVrai() {
		when(mockUtilisateurManager.utilisateurActifALeDroitDe(Permissions.SUPPRESSION_BILLET))
				.thenReturn(VRAI);
		assertEquals(unUtilisateurSessionDefaut.utilisateurActifPeutSupprimerBillet(), VRAI);
	}

	@Test
	public void siUtilisateurActifPeutEffacerMatchAlorsOnRetourneVrai() {
		when(mockUtilisateurManager.utilisateurActifALeDroitDe(Permissions.SUPPRESSION_MATCH))
				.thenReturn(VRAI);
		assertEquals(unUtilisateurSessionDefaut.utilisateurActifPeutEffacerMatch(), VRAI);
	}

	@Test
	public void siUtilisateurActifPeutEffacerCentreSportifAlorsOnRetourneVrai() {
		when(
				mockUtilisateurManager
						.utilisateurActifALeDroitDe(Permissions.SUPPRESSION_CENTRE_SPORTIF))
				.thenReturn(VRAI);
		assertEquals(unUtilisateurSessionDefaut.utilisateurActifPeutEffacerCentreSportif(), VRAI);
	}

	@Test
	public void testGotoCreationCompteClicked() throws Exception {

		String creationCompte = unUtilisateurSessionDefaut.gotoCreationCompteClicked();

		assertEquals("goto-creation-compte", creationCompte);
	}

	@Test
	public void testIsUtilisateurActifConnecte() throws Exception {

		boolean utilisateurActifConnecte = unUtilisateurSessionDefaut.isUtilisateurActifConnecte();

		// add additional test code here
		assertEquals(false, utilisateurActifConnecte);
	}

	@Test
	public void testLoginAnonymeClicked() throws Exception {

		String loginanonymeClicked = unUtilisateurSessionDefaut.loginAnonymeClicked();
		assertNotNull(loginanonymeClicked);
	}

	@Test
	public void testLoginClicked() throws Exception {

		String loginclicked = unUtilisateurSessionDefaut.loginClicked();
		assertNotNull(loginclicked);
	}

	@Test
	public void testLogoutClicked() throws Exception {

		String logoutclicked = unUtilisateurSessionDefaut.logoutClicked();

		assertNotNull(logoutclicked);
	}

	@Test
	public void testUtilisateurActifPeutAcheterBillet() throws Exception {

		boolean utilisateurPeutAcheterBillet = unUtilisateurSessionDefaut
				.utilisateurActifPeutAcheterBillet();

		// add additional test code here
		assertFalse(utilisateurPeutAcheterBillet);
	}

	@Test
	public void testUtilisateurActifPeutCreerBillet() throws Exception {

		boolean utilisateurPeutCreerBillet = unUtilisateurSessionDefaut
				.utilisateurActifPeutCreerBillet();

		// add additional test code here
		assertEquals(false, utilisateurPeutCreerBillet);
	}

	@Test
	public void testUtilisateurActifPeutCreerCentreMatch() throws Exception {

		boolean utilisateurPeutCreerCentreMatch = unUtilisateurSessionDefaut
				.utilisateurActifPeutCreerCentreMatch();

		// add additional test code here
		assertEquals(false, utilisateurPeutCreerCentreMatch);
	}

	@Test
	public void testUtilisateurActifPeutCreerCentreSportif() throws Exception {

		boolean utilisateurPeutCreerCentreSportif = unUtilisateurSessionDefaut
				.utilisateurActifPeutCreerCentreSportif();

		// add additional test code here
		assertEquals(false, utilisateurPeutCreerCentreSportif);
	}

	@Test
	public void testUtilisateurActifPeutEffacerCentreSportif() throws Exception {

		boolean utilisateurPeutEffacerCentreSportif = unUtilisateurSessionDefaut
				.utilisateurActifPeutEffacerCentreSportif();

		// add additional test code here
		assertEquals(false, utilisateurPeutEffacerCentreSportif);
	}

	@Test
	public void testUtilisateurActifPeutEffacerMatch() throws Exception {

		boolean utilisateurPeutEffacerMatch = unUtilisateurSessionDefaut
				.utilisateurActifPeutEffacerMatch();

		// add additional test code here
		assertEquals(false, utilisateurPeutEffacerMatch);
	}

	@Test
	public void testUtilisateurActifPeutModifierInfosCompte() throws Exception {

		boolean utilisateurPeutModifierInfosCompte = unUtilisateurSessionDefaut
				.utilisateurActifPeutModifierInfosCompte();

		// add additional test code here
		assertEquals(false, utilisateurPeutModifierInfosCompte);
	}

	@Test
	public void testUtilisateurActifPeutSupprimerBillet() throws Exception {

		boolean utilisateurPeutSupprimerBillet = unUtilisateurSessionDefaut
				.utilisateurActifPeutSupprimerBillet();

		// add additional test code here
		assertEquals(false, utilisateurPeutSupprimerBillet);
	}

	private void preparerUtilisateurActifEtSonNom() {
		when(mockUtilisateurManager.nomDeLutilisateur()).thenReturn(NOM_UTILISATEUR);
	}

}

//
// public class LoginBeanTest {
//
// private final String BON_LOGOUT_MSG = "logout-reussi";
// private final String BON_LOGINANONYME_MSG = "login-anonyme-reussi";
// private final String BON_GOTOCREATIONCOMPTE_MSG = "goto-creation-compte";
// private final String BON_LOGINECHOUE_MSG = "login-echoue";
// private final String BON_LOGINREUSSI_MSG = "login-reussi";
// private final String BON_LOGINADMIN_MSG = "login-admin-reussi";
//
// private final String BON_NOMUTILISATEUR = "utilisateurActif";
// private final String BON_MOTDEPASSE = "utilisateurActif";
// private final String MAUVAIS_NOMUTILISATEUR = "bidon";
// private final String MAUVAIS_MOTDEPASSE = "bidon";
//
// private LoginBean loginBean;
// private UtilisateurManagerFacadeImpl mockUtilisateurManager;
// private GUIMessageHandler mockGuiMessageHandler;
//
// @Before
// public void setup() {
// loginBean = new LoginBean();
//
// mockUtilisateurManager = mock(UtilisateurManagerFacadeImpl.class);
// mockGuiMessageHandler = mock(GUIMessageHandler.class);
//
// loginBean.setGuiMessageHandler(mockGuiMessageHandler);
// loginBean.setUtilisateurManagerFacade(mockUtilisateurManager);
// }
//
// @Test
// public void siEventLoginAnonymeAlorsServiceEstContactePourLoginAnonyme() {
// loginBean.loginAnonymeClicked();
// verify(mockUtilisateurManager).loginUtilisateurAnonyme();
// }
//
// @Test
// public void siLoginAnonymeReussitAlorsBonRoutingMessageEnvoye() {
// String routingMessage = loginBean.loginAnonymeClicked();
// assertEquals(routingMessage, BON_LOGINANONYME_MSG);
// }
//
// @Test
// public void siEventLogoutAlorsServiceEstContactePourLogout() {
// loginBean.logoutClicked();
// verify(mockUtilisateurManager).logoutUtilisateurActif();
// }
//
// @Test
// public void siLogoutReussitAlorsBonRoutingMessageEnvoye() {
// String routingMessage = loginBean.logoutClicked();
// assertEquals(routingMessage, BON_LOGOUT_MSG);
// }
//
// @Test
// public void siEventGotoCreationCompteAlorsBonRoutingMessageEnvoye() {
// String routingMessage = loginBean.gotoCreationCompteClicked();
// assertEquals(routingMessage, BON_GOTOCREATIONCOMPTE_MSG);
// }
//
// @Test
// public void siEventLoginAlorsServiceEstContactePourLoginAvecCedentiels() {
// loginBean.loginClicked();
// verify(mockUtilisateurManager).loginUtilisateurParCredentiels(anyString(),
// anyString());
// }
//
// @Test
// public void
// siEventLoginAvecBonCredentielsUtilisateurAlorsBonRoutingMessageEnvoye() {
// preparerReponsesAuxCredentielsUtilisateur();
// loginBean.setNomUtilisateur(BON_NOMUTILISATEUR);
// loginBean.setMotDePasse(BON_MOTDEPASSE);
//
// String routingMessage = loginBean.loginClicked();
//
// assertEquals(routingMessage, BON_LOGINREUSSI_MSG);
// }
//
// @Test
// public void
// siEventLoginAvecMauvaisesCombinaisonsCredentielsAlorsBonRoutingMessageEnvoye()
// {
// preparerReponsesAuxCredentiels();
//
// mauvaiseCombinaisonCredentiels1();
// String routingMessage = loginBean.loginClicked();
// assertEquals(routingMessage, BON_LOGINECHOUE_MSG);
//
// mauvaiseCombinaisonCredentiels2();
// routingMessage = loginBean.loginClicked();
// assertEquals(routingMessage, BON_LOGINECHOUE_MSG);
//
// mauvaiseCombinaisonCredentiels3();
// routingMessage = loginBean.loginClicked();
// assertEquals(routingMessage, BON_LOGINECHOUE_MSG);
// }
//
// private void mauvaiseCombinaisonCredentiels1() {
// loginBean.setNomUtilisateur(BON_NOMUTILISATEUR);
// loginBean.setMotDePasse(MAUVAIS_MOTDEPASSE);
// }
//
// private void mauvaiseCombinaisonCredentiels2() {
// loginBean.setNomUtilisateur(MAUVAIS_NOMUTILISATEUR);
// loginBean.setMotDePasse(MAUVAIS_MOTDEPASSE);
// }
//
// private void mauvaiseCombinaisonCredentiels3() {
// loginBean.setNomUtilisateur(MAUVAIS_NOMUTILISATEUR);
// loginBean.setMotDePasse(BON_MOTDEPASSE);
// }
//
// private void preparerReponsesAuxCredentielsUtilisateur() {
// preparerReponsesAuxCredentiels();
// }
//
// private void preparerReponsesAuxCredentiels() {
// when(mockUtilisateurManager.loginUtilisateurParCredentiels(BON_NOMUTILISATEUR,
// BON_MOTDEPASSE)).thenReturn(true);
// when(mockUtilisateurManager.loginUtilisateurParCredentiels(BON_NOMUTILISATEUR,
// MAUVAIS_MOTDEPASSE)).thenReturn(false);
// when(mockUtilisateurManager.loginUtilisateurParCredentiels(MAUVAIS_NOMUTILISATEUR,
// BON_MOTDEPASSE)).thenReturn(false);
// when(mockUtilisateurManager.loginUtilisateurParCredentiels(MAUVAIS_NOMUTILISATEUR,
// MAUVAIS_MOTDEPASSE)).thenReturn(false);
// }