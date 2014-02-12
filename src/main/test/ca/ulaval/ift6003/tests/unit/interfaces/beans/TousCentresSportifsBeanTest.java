package ca.ulaval.ift6003.tests.unit.interfaces.beans;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.interfaces.beans.centressportifs.TousCentresSportifsBean;

public class TousCentresSportifsBeanTest {

	private TousCentresSportifsBean tousCentresDefaut;
	private InventaireApplication mockInventaireApplication;

	@Before
	public void setUp() throws Exception {
		mockInventaireApplication = mock(InventaireApplication.class);
		tousCentresDefaut = new TousCentresSportifsBean();
		tousCentresDefaut.setInventaireApplication(mockInventaireApplication);
	}

	@Test
	public void siTousCentresSportifsExistentAlorsQuandSonUtilisateurApplicationFacadeEstChangeAlorsSaNouvelleFacadeEstValide() {

		tousCentresDefaut.getListeCentresSportifs();
		verify(mockInventaireApplication).getListeCentresSportifs();

	}

}
