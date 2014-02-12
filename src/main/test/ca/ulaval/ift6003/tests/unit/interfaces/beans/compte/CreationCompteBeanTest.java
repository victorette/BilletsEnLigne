package ca.ulaval.ift6003.tests.unit.interfaces.beans.compte;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIIdentifiantDejaExistant;
import ca.ulaval.ift6003.application.impl.UtilisateurManagementImpl;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.interfaces.beans.compte.CreationCompteBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class CreationCompteBeanTest {

	private final String BON_CREATIONCOMPTEECHOUE_MSG = "creation-compte-echouee";
	private final String BON_CREATIONCOMPTEREUSSI_MSG = "creation-compte-reussie";
	private final String BON_CREATIONCOMPTEANNULEE_MSG = "creation-compte-annulee";

	private CreationCompteBean compteBean;
	private GUIMessageHandler mockGuiMessageHandler;
	private UtilisateurManagementImpl mockUtilisateurManager;
	private Utilisateur mockUtilisateur;

	@Before
	public void setup() {
		compteBean = new CreationCompteBean();

		mockUtilisateurManager = mock(UtilisateurManagementImpl.class);
		mockGuiMessageHandler = mock(GUIMessageHandler.class);
		mockUtilisateur = new Utilisateur();

		compteBean.setGuiMessageHandler(mockGuiMessageHandler);
		compteBean.setUtilisateurManagement(mockUtilisateurManager);
		compteBean.setUtilisateurActif(mockUtilisateur);
	}

	@Test
	public void siEventCreerUtilisateurAlorsServiceEstContactePourCreation() throws UIIdentifiantDejaExistant {
		compteBean.creerUtilisateurClicked();
		verify(mockUtilisateurManager).inscrireNouvelUtilisateur((Utilisateur) anyObject());
	}

	@Test
	public void siCreationCompteReussieAlorsBonRoutingMessageEnvoye() {

		String routingMessage = compteBean.creerUtilisateurClicked();
		assertEquals(routingMessage, BON_CREATIONCOMPTEREUSSI_MSG);
	}

	@Test
	public void siCreationCompteEchoueeAlorsBonRoutingMessageEnvoye() throws UIIdentifiantDejaExistant {

		doThrow(new UIIdentifiantDejaExistant()).when(mockUtilisateurManager).inscrireNouvelUtilisateur((Utilisateur) anyObject());

		String routingMessage = compteBean.creerUtilisateurClicked();
		assertEquals(routingMessage, BON_CREATIONCOMPTEECHOUE_MSG);
	}

	@Test
	public void siEventAnnulerCreationAlorsBonRoutingMessageEnvoye() {
		String routingMessage = compteBean.annulerCreationCompteClicked();
		assertEquals(routingMessage, BON_CREATIONCOMPTEANNULEE_MSG);
	}

}
