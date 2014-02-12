package ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.impl.AdminApplicationImpl;
import ca.ulaval.ift6003.application.impl.InventaireApplicationImpl;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.interfaces.beans.matchs.CreationMatchBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class CreationMatchBeanTest {

	private final String BON_CREATIONBILLETANNULEEMSG = "creation-match-annulee";

	private CreationMatchBean creationMatchBean;
	private InventaireApplicationImpl mockUtilisateurApplication;
	private AdminApplicationImpl mockAdminApplication;
	private GUIMessageHandler mockGuiMessageHandler;
	private Match mockMatch;

	@Before
	public void setup() {
		creationMatchBean = new CreationMatchBean();

		mockUtilisateurApplication = mock(InventaireApplicationImpl.class);
		mockAdminApplication = mock(AdminApplicationImpl.class);
		mockGuiMessageHandler = mock(GUIMessageHandler.class);
		mockMatch = mock(Match.class);

		creationMatchBean.setGuiMessageHandler(mockGuiMessageHandler);
		creationMatchBean.setInventaireApplication(mockUtilisateurApplication);
		creationMatchBean.setAdminApplication(mockAdminApplication);
		creationMatchBean.setMatch(mockMatch);
	}

	@Test
	public void apresInstantiationDuBeanLeMatchNestPasNull() {
		assertTrue(!(creationMatchBean.getMatch() == null));
	}

	@Test
	public void siEventCreationMatchAlorsServiceEstContactePourCreationDuBonMatch() throws UIDroitsInsuffisants {
		creationMatchBean.creerMatchClicked();
		verify(mockAdminApplication).ajouterNouveauMatch(mockMatch);
	}

	@Test
	public void leMatchEstReinitialiseApresEventCreationMatch() {
		creationMatchBean.creerMatchClicked();
		assertFalse(mockMatch == creationMatchBean.getMatch());
	}

	@Test
	public void siEventAnnulerCreationAlorsBonRoutingMessageEnvoye() {
		String routingMessage = creationMatchBean.annulerCreationMatchClicked();
		assertEquals(routingMessage, BON_CREATIONBILLETANNULEEMSG);
	}

	@Test
	public void quandDemandeListeNomsCentresSportifsAlorsServiceEstContactePourListeAJour() {
		creationMatchBean.getListeNomsCentresSportifs();
		verify(mockUtilisateurApplication).getListeNomsCentresSportifs();
	}

}
