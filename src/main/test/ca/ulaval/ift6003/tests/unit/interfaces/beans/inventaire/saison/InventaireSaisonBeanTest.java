package ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire.saison;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.impl.InventaireApplicationImpl;
import ca.ulaval.ift6003.interfaces.beans.inventaire.saison.InventaireSaisonBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class InventaireSaisonBeanTest {

	private InventaireSaisonBean inventaireBean;
	private InventaireApplicationImpl mockUtilisateurApplication;
	private GUIMessageHandler mockGuiMessageHandler;

	@Before
	public void setup() {
		inventaireBean = new InventaireSaisonBean();

		mockUtilisateurApplication = mock(InventaireApplicationImpl.class);
		mockGuiMessageHandler = mock(GUIMessageHandler.class);

		inventaireBean.setGuiMessageHandler(mockGuiMessageHandler);
		inventaireBean.setInventaireApplication(mockUtilisateurApplication);

		inventaireBean.init(); // appell?? automatiquement par JSF
	}

	@Test
	public void filtreOptionsNONNUllesApresInit() {
		assertFalse(inventaireBean.getFiltreOptions() == null);
	}

	@Test
	public void filtreOptionsTableNONNUllesApresInit() {
		assertFalse(inventaireBean.getFiltreOptions() == null);
	}

}
