package ca.ulaval.ift6003.tests.unit.interfaces.beans;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.impl.UtilisateurManagementImpl;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.interfaces.beans.compte.TousUtilisateursBean;

public class TousUtilisateursBeanTest {

	private TousUtilisateursBean tousUtilisateursDefaut;
	private UtilisateurManagement mockUtilisateurManagement;

	@Before
	public void setUp() throws Exception {
		tousUtilisateursDefaut = new TousUtilisateursBean();
		mockUtilisateurManagement = mock(UtilisateurManagementImpl.class);
		tousUtilisateursDefaut.setUtilisateurManagement(mockUtilisateurManagement);
	}

	@Test
	public void siTousUtilisateursExistentAlorsQuandSonUtilisateurManagerFacadeEstChangeAlorsSaNouvelleFacadeEstValide()
			throws Exception {
		assertNotNull(tousUtilisateursDefaut);
		assertNull(tousUtilisateursDefaut.getGuiMessageHandler());

	}

	@Test
	public void siTousUtilisateursExistentAlorsQuandSonUtilisateurManagerFacade() throws Exception {
		List<Utilisateur> listeUtilisateurs = tousUtilisateursDefaut.getListeUtilisateurs();
		assertNotNull(listeUtilisateurs);

	}

}
