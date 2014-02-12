package ca.ulaval.ift6003.tests.unit.interfaces.beans.Paiement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.InventaireApplication;
import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.application.impl.InventaireApplicationImpl;
import ca.ulaval.ift6003.application.impl.UtilisateurManagementImpl;
import ca.ulaval.ift6003.domaine.modele.utilisateur.InfosBancaires;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.interfaces.beans.Paiement.PaiementPanierBean;

public class PaiementPanierBeanTest {

	PaiementPanierBean paiementPanierBean;
	InventaireApplicationImpl mockInventaireApplication;
	InfosBancaires mockInfosBancaires;
	UtilisateurManagementImpl mockUtilisateurManagementImpl;
	Utilisateur mockUtilisateurActif;

	@Before
	public void setUp() throws Exception {

		paiementPanierBean = new PaiementPanierBean();

		mockInventaireApplication = mock(InventaireApplicationImpl.class);
		mockInfosBancaires = mock(InfosBancaires.class);
		mockUtilisateurManagementImpl = mock(UtilisateurManagementImpl.class);
		mockUtilisateurActif = mock(Utilisateur.class);

		paiementPanierBean.setInfosBancaires(mockInfosBancaires);
		paiementPanierBean.setInventaireApplication(mockInventaireApplication);
		paiementPanierBean.setUtilisateurManagement(mockUtilisateurManagementImpl);
	}

	@Test(expected = Exception.class)
	public void parametresInterfaceVidesQuandConfirmerPaiementAlorsErreur() throws Exception {

		paiementPanierBean.confirmerPaiement();

	}

	@Test(expected = Exception.class)
	public void parametresInterfaceVidesQuandChercherInfosBancairesAlorsErreur() throws Exception {

		when(mockUtilisateurActif.getInfosBancaires()).thenReturn(mockInfosBancaires);
		paiementPanierBean.getInfosBancaires();

	}

	@Test
	public void testInventaireApplication() throws Exception {

		InventaireApplication inventaire = paiementPanierBean.getInventaireApplication();
		assertNotNull(inventaire);
	}

	@Test
	public void testUtilisateurManagement() throws Exception {

		UtilisateurManagement utilisateur = paiementPanierBean.getUtilisateurManagement();

		assertNotNull(utilisateur);
		assertEquals(false, utilisateur.utilisateurEstConnecte());
		assertEquals(null, utilisateur.getUtilisateurActif());
		assertEquals(null, utilisateur.getPanier());
	}

}
