package ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire.unmatch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.PanierApplication;
import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.frontiere.constantesEtEnumsUI.UIPermissions;
import ca.ulaval.ift6003.application.impl.InventaireApplicationImpl;
import ca.ulaval.ift6003.interfaces.beans.inventaire.unmatch.InventaireUnMatchBean;
import ca.ulaval.ift6003.interfaces.beans.inventaire.unmatch.InventaireUnMatchFiltreOptions;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class InventaireUnMatchBeanTest {

	private InventaireUnMatchBean inventaireUnMatch;

	private InventaireApplicationImpl mockUtilisateurApplication;
	private GUIMessageHandler mockGuiMessageHandler;
	private PanierApplication mockPanierApplication;
	private UtilisateurManagement mockUtilisateurManagement;
	private InventaireUnMatchFiltreOptions mockInventaireUnMatchFiltreOptions;

	@Before
	public void setUp() throws Exception {

		mockUtilisateurApplication = mock(InventaireApplicationImpl.class);
		mockUtilisateurManagement = mock(UtilisateurManagement.class);
		mockGuiMessageHandler = mock(GUIMessageHandler.class);
		mockPanierApplication = mock(PanierApplication.class);
		mockInventaireUnMatchFiltreOptions = mock(InventaireUnMatchFiltreOptions.class);

		inventaireUnMatch = new InventaireUnMatchBean();

		inventaireUnMatch.setInventaireApplication(mockUtilisateurApplication);
		inventaireUnMatch.setGuiMessageHandler(mockGuiMessageHandler);
		inventaireUnMatch.setPanierApplication(mockPanierApplication);
		inventaireUnMatch.setUtilisateurManagement(mockUtilisateurManagement);
		inventaireUnMatch.setFiltreOptions(mockInventaireUnMatchFiltreOptions);

		inventaireUnMatch.init(); // automatique par JSF...

	}

	@Test
	public void inventaireTableNONNUllesApresInit() {
		assertNotNull(inventaireUnMatch.getInventaireTable());
	}

	@Test
	public void filtreOptionsTableNONNUllesApresInit() {
		assertNotNull(inventaireUnMatch.getFiltreOptions());
	}

	@Test
	public void testInventaireUnMatchBean() throws Exception {

		assertNotNull(inventaireUnMatch);
		assertFalse(inventaireUnMatch.doitAfficherSommaireBillet());
		assertFalse(inventaireUnMatch.doitAfficherAchatBillet());
		assertNotNull(inventaireUnMatch.getGuiMessageHandler());
	}

	@Test
	public void testAjouterAuPanier() throws Exception {

		inventaireUnMatch.toggleFiltre(true);

		inventaireUnMatch.ajouterAuPanier();

	}

	@Test
	public void testDoitAfficherFiltreParPreferences() throws Exception {

		inventaireUnMatch.toggleFiltre(true);

		boolean doitAfficherFiltreParPreferences = inventaireUnMatch
				.doitAfficherFiltreParPreferences();

		assertFalse(doitAfficherFiltreParPreferences);

		verify(mockUtilisateurManagement, times(1)).utilisateurActifALeDroitDe(
				UIPermissions.APPLIQUER_PREFERENCES_BILLETS);
	}

	@Test
	public void testGetFiltreOptions() throws Exception {

		inventaireUnMatch.toggleFiltre(true);

		InventaireUnMatchFiltreOptions filtreOptions = inventaireUnMatch.getFiltreOptions();

		assertNotNull(filtreOptions);
	}

	@Test
	public void testGetNbBilletsParMatchs() throws Exception {

		inventaireUnMatch.toggleFiltre(true);

		Map<String, Integer> nbBilletsParMatchs = inventaireUnMatch.getNbBilletsParMatchs();

		assertNotNull(nbBilletsParMatchs);
	}

	@Test
	public void testToggleFiltre() throws Exception {

		inventaireUnMatch.toggleFiltre(true);

		boolean utiliserPreferences = true;

		inventaireUnMatch.toggleFiltre(utiliserPreferences);

		verify(mockUtilisateurApplication, times(2)).produireInventaireBilletsUnMatchFiltre();
	}

}
