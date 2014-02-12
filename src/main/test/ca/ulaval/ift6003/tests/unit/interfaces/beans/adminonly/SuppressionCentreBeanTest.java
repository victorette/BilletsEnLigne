package ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.impl.AdminApplicationImpl;
import ca.ulaval.ift6003.interfaces.beans.centressportifs.SuppressionCentreBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class SuppressionCentreBeanTest {

	private final String NOM_BON_CENTRE = "nomBonCentre";
	private final String NOM_MAUVAIS_CENTRE = "nomMauvaisCentre";
	private SuppressionCentreBean suppressionCentreBean;

	private AdminApplicationImpl mockFacade;
	private GUIMessageHandler mockGuiMessageHandler;

	@Before
	public void setUp() throws Exception {
		suppressionCentreBean = new SuppressionCentreBean();

		mockGuiMessageHandler = mock(GUIMessageHandler.class);
		mockFacade = mock(AdminApplicationImpl.class);

		suppressionCentreBean.setGuiMessageHandler(mockGuiMessageHandler);
		suppressionCentreBean.setAdminApplication(mockFacade);
	}

	@Test
	public void verifierQueLaFacadeEstAppeleAvecLeBonID() throws UIDroitsInsuffisants,
			UIEntiteInexistante {
		suppressionCentreBean.supprimerCentreSportif(NOM_BON_CENTRE);
		verify(mockFacade).effacerCentreSportifParNom(NOM_BON_CENTRE);
	}

	@Test
	public void verifierErreurAvecLeMauvaisID() throws UIDroitsInsuffisants, UIEntiteInexistante {

		doThrow(new UIEntiteInexistante()).when(mockFacade).effacerCentreSportifParNom(
				NOM_MAUVAIS_CENTRE);
		suppressionCentreBean.supprimerCentreSportif(NOM_MAUVAIS_CENTRE);
		verify(mockGuiMessageHandler).addMessage(anyString(), anyString());
	}

	@Test
	public void verifierErreurAvecDroitsManquants() throws UIDroitsInsuffisants,
			UIEntiteInexistante {

		doThrow(new UIDroitsInsuffisants()).when(mockFacade).effacerCentreSportifParNom(
				NOM_BON_CENTRE);
		suppressionCentreBean.supprimerCentreSportif(NOM_BON_CENTRE);
		verify(mockGuiMessageHandler).addMessage(anyString(), anyString());
	}
}
