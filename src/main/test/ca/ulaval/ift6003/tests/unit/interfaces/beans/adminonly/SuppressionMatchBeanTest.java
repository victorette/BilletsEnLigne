package ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.impl.AdminApplicationImpl;
import ca.ulaval.ift6003.interfaces.beans.matchs.SuppressionMatchBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class SuppressionMatchBeanTest {

	private final String UN_ID = "1";
	private final String BON_ID = "1";
	private SuppressionMatchBean suppressionMatchBean;

	private AdminApplicationImpl mockFacade;
	private GUIMessageHandler mockGuiMessageHandler;

	@Before
	public void setUp() throws Exception {
		suppressionMatchBean = new SuppressionMatchBean();

		mockGuiMessageHandler = mock(GUIMessageHandler.class);
		mockFacade = mock(AdminApplicationImpl.class);

		suppressionMatchBean.setGuiMessageHandler(mockGuiMessageHandler);
		suppressionMatchBean.setAdminApplication(mockFacade);
	}

	@Test
	public void verifierQueLaFacadeEstAppeleAvecLeBonID() throws UIDroitsInsuffisants, UIEntiteInexistante {
		suppressionMatchBean.supprimerMatch(BON_ID);
		verify(mockFacade).effacerMatchParID(BON_ID);
	}

	@Test
	public void verifierQueUnMessageHandlerEstBienCreeQuandLaSuppressionNeReussitPas() throws UIDroitsInsuffisants, UIEntiteInexistante {
		doThrow(new UIEntiteInexistante()).when(mockFacade).effacerMatchParID(UN_ID);
		suppressionMatchBean.supprimerMatch(BON_ID);
		verify(mockGuiMessageHandler, times(1)).addMessage(anyString(), anyString());
	}

	@Test
	public void verifierQueDeuxMessageHandlerSontBienCreeQuandLaSuppressionReussit() throws UIDroitsInsuffisants, UIEntiteInexistante {

		doThrow(new UIDroitsInsuffisants()).doNothing().when(mockFacade).effacerMatchParID(BON_ID);
		doThrow(new UIEntiteInexistante()).doNothing().when(mockFacade).effacerMatchParID(BON_ID);
		suppressionMatchBean.supprimerMatch(BON_ID);
		verify(mockGuiMessageHandler, times(1)).addMessage(anyString(), anyString());
	}
}
