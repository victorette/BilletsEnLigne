package ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.impl.AdminApplicationImpl;
import ca.ulaval.ift6003.interfaces.beans.billets.SuppressionBilletBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class SuppressionBilletBeanTest {

	private final String BON_ID = "1";
	private SuppressionBilletBean suppressionBilletBean;

	private AdminApplicationImpl mockFacade;
	private GUIMessageHandler mockGuiMessageHandler;

	@Before
	public void setUp() throws Exception {
		suppressionBilletBean = new SuppressionBilletBean();

		mockGuiMessageHandler = mock(GUIMessageHandler.class);
		mockFacade = mock(AdminApplicationImpl.class);

		suppressionBilletBean.setGuiMessageHandler(mockGuiMessageHandler);
		suppressionBilletBean.setAdminApplication(mockFacade);
	}

	@Test
	public void verifierQueLaFacadeEstAppeleAvecLeBonIDPourLaSuppressionDeBilletUnMatch() throws UIDroitsInsuffisants, UIEntiteInexistante {
		suppressionBilletBean.supprimerBilletUnMatch(BON_ID);
		verify(mockFacade).effacerBilletUnMatchParID(BON_ID);
	}

	@Test
	public void verifierQueLaFacadeEstAppeleAvecLeBonIDPourLaSuppressionDeBilletSaison() throws UIDroitsInsuffisants, UIEntiteInexistante {
		suppressionBilletBean.supprimerBilletSaison(BON_ID);
		verify(mockFacade).effacerBilletSaisonParID(BON_ID);
	}

}
