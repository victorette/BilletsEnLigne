package ca.ulaval.ift6003.tests.unit.interfaces.beans.compte;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.impl.UtilisateurManagementImpl;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.interfaces.beans.compte.ModificationCompteBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class ModificationCompteBeanTest {

	private final String BON_MODIFICATIONCOMPTEREUSSIE_MSG = "modification-compte-reussie";

	private ModificationCompteBean modificationBean;
	private UtilisateurManagementImpl mockUtilisateurManager;
	private GUIMessageHandler mockGuiMessageHandler;
	private Utilisateur mockUtilisateur;

	@Before
	public void setup() {

		modificationBean = new ModificationCompteBean();

		mockUtilisateurManager = mock(UtilisateurManagementImpl.class);
		mockUtilisateur = mock(Utilisateur.class);
		mockGuiMessageHandler = mock(GUIMessageHandler.class);

		modificationBean.setUtilisateurManagement(mockUtilisateurManager);
		modificationBean.setUtilisateurActif(mockUtilisateur);
		modificationBean.setGuiMessageHandler(mockGuiMessageHandler);
	}

	@Test
	public void siEventConfirmerModificationAlorsServiceEstContactePourModification() throws UIEntiteInexistante {
		modificationBean.confirmerModificationClicked();
		verify(mockUtilisateurManager).modifierCompteUtilisateurExistant((Utilisateur) anyObject());
	}

	@Test
	public void siModificationCompteReussieAlorsBonRoutingMessageEnvoye() {
		// r??ussit toujours
		String routingMessage = modificationBean.confirmerModificationClicked();
		assertEquals(routingMessage, BON_MODIFICATIONCOMPTEREUSSIE_MSG);
	}

}
