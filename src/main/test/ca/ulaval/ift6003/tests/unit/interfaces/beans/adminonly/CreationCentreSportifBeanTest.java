package ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIIdentifiantDejaExistant;
import ca.ulaval.ift6003.application.impl.AdminApplicationImpl;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.interfaces.beans.centressportifs.CreationCentreSportifBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class CreationCentreSportifBeanTest {

	private final String BON_CREATIONCENTREANNULEEMSG = "creation-nomCentreSportif-annulee";
	private final String NOMS_SECTIONS = "section1\nsection2\nsection3";
	private CentreSportif mockCentreSportif;

	private CreationCentreSportifBean creationCentreSportifBean;
	private GUIMessageHandler mockGuiMessageHandler;
	private AdminApplicationImpl mockAdminApplication;

	@Before
	public void setup() {
		creationCentreSportifBean = new CreationCentreSportifBean();

		mockAdminApplication = mock(AdminApplicationImpl.class);
		mockGuiMessageHandler = mock(GUIMessageHandler.class);
		mockCentreSportif = mock(CentreSportif.class);

		creationCentreSportifBean.setGuiMessageHandler(mockGuiMessageHandler);
		creationCentreSportifBean.setAdminApplication(mockAdminApplication);
		creationCentreSportifBean.setSectionsString(NOMS_SECTIONS);
		creationCentreSportifBean.setCentreSportif(mockCentreSportif);
	}

	@Test
	public void apresInstantiationDuBeanLeCentreNestPasNull() {
		assertFalse(creationCentreSportifBean.getCentreSportif() == null);
	}

	@Test
	public void siEventCreationCentreAlorsServiceEstContactePourCreationDuBonCentre() throws UIDroitsInsuffisants, UIIdentifiantDejaExistant {
		creationCentreSportifBean.creerCentreSportifClicked();
		verify(mockAdminApplication).ajouterNouveauCentreSportif(mockCentreSportif);
	}

	@Test
	public void leCentreEstReinitialiseApresEventCreationMatch() throws UIDroitsInsuffisants, UIIdentifiantDejaExistant {
		// ajoutDuNouveauCentreSportifVaReussir();
		creationCentreSportifBean.creerCentreSportifClicked();
		assertFalse(mockCentreSportif == creationCentreSportifBean.getCentreSportif());
	}

	@Test
	public void siEventAnnulerCreationAlorsBonRoutingMessageEnvoye() {
		String routingMessage = creationCentreSportifBean.annulerCreationCentreSportifClicked();
		assertEquals(routingMessage, BON_CREATIONCENTREANNULEEMSG);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void siIdentifiantExistantAlorsCreationCentreSportifVaEchouer() throws UIIdentifiantDejaExistant, UIDroitsInsuffisants {
		identifiantExistant();
		creationCentreSportifBean.creerCentreSportifClicked();
		verify(mockCentreSportif).setSections((List<String>) anyObject());
		verifyNoMoreInteractions(mockCentreSportif);

	}

	@Test
	public void siIdentifiantNonExistantAlorsCreationCentreSportifVaReussir() throws UIDroitsInsuffisants, UIIdentifiantDejaExistant {
		identifiantNonExistant();
		creationCentreSportifBean.creerCentreSportifClicked();
		verify(mockAdminApplication).ajouterNouveauCentreSportif(mockCentreSportif);
	}

	private void identifiantExistant() throws UIIdentifiantDejaExistant, UIDroitsInsuffisants {

		doThrow(new UIIdentifiantDejaExistant()).when(mockAdminApplication).ajouterNouveauCentreSportif(mockCentreSportif);
	}

	private void identifiantNonExistant() throws UIDroitsInsuffisants, UIIdentifiantDejaExistant {

		doThrow(new UIIdentifiantDejaExistant()).doNothing().when(mockAdminApplication).ajouterNouveauCentreSportif(mockCentreSportif);

	}
	// FIXME verifier...
	// private void ajoutDuNouveauCentreSportifVaEchouer() {
	// when(mockAdminApplication.ajouterNouveauCentreSportif(mockCentreSportif)).thenReturn(false);
	// }

}
