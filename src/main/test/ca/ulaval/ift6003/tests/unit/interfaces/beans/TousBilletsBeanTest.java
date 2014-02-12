package ca.ulaval.ift6003.tests.unit.interfaces.beans;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.interfaces.beans.billets.TousBilletsBean;

public class TousBilletsBeanTest {

	private TousBilletsBean tousBilletsBean;
	private InventaireApplication mockInventaireApplication;

	@Before
	public void setUp() throws Exception {

		mockInventaireApplication = mock(InventaireApplication.class);
		tousBilletsBean = new TousBilletsBean();
		tousBilletsBean.setInventaireApplication(mockInventaireApplication);

	}

	@Test
	public void siListeBilletsMatchEstInvoqueeAlorsInventaireInvoqueMemeListe() {

		tousBilletsBean.getListeBilletsUnMatch();
		verify(mockInventaireApplication).getListeBilletsUnMatch();

	}

	@Test
	public void siListeBilletsSaisonEstInvoqueeAlorsInventaireInvoqueMemeListe() {

		tousBilletsBean.getListeBilletsSaison();
		verify(mockInventaireApplication).getListeBilletsSaison();

	}

}
