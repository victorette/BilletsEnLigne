package ca.ulaval.ift6003.tests.unit.interfaces.beans;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.interfaces.beans.matchs.TousMatchsBean;

public class TousMatchsBeanTest {

	private TousMatchsBean tousMatchsDefaut;
	private InventaireApplication mockInventaireApplication;

	@Before
	public void setUp() throws Exception {
		mockInventaireApplication = mock(InventaireApplication.class);
		tousMatchsDefaut = new TousMatchsBean();
		tousMatchsDefaut.setInventaireApplication(mockInventaireApplication);
	}

	@Test
	public void siTousMatchsExistentAlorsQuandSonUtilisateurApplicationFacadeEstChangeAlorsSaNouvelleFacadeEstValide() {
		tousMatchsDefaut.getListeMatchs();
		verify(mockInventaireApplication).getListeMatchs();
	}

}
