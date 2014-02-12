package ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.application.impl.AdminApplicationImpl;
import ca.ulaval.ift6003.application.impl.InventaireApplicationImpl;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.interfaces.beans.billets.CreationBilletUnMatchBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class CreationBilletUnMatchBeanTest {

	private final String BON_CREATIONBILLETANNULEEMSG = "creation-billet-match-annulee";
	private final int NOMBRE_BILLETS_VOULU = 4;
	private final String UN_ID_QUELCONQUE = "3";

	private CreationBilletUnMatchBean creationBilletBean;
	private AdminApplicationImpl mockAdminApplication;
	private InventaireApplicationImpl mockUtilisateurApplication;
	private GUIMessageHandler mockGuiMessageHandler;
	private BilletUnMatch mockBillet;

	@Before
	public void setup() {
		creationBilletBean = new CreationBilletUnMatchBean();

		mockAdminApplication = mock(AdminApplicationImpl.class);
		mockUtilisateurApplication = mock(InventaireApplicationImpl.class);
		mockGuiMessageHandler = mock(GUIMessageHandler.class);
		mockBillet = mock(BilletUnMatch.class);

		creationBilletBean.setGuiMessageHandler(mockGuiMessageHandler);
		creationBilletBean.setAdminApplication(mockAdminApplication);
		creationBilletBean.setInventaireApplication(mockUtilisateurApplication);
		creationBilletBean.setBillet(mockBillet);
	}

	@Test
	public void siEventCreationNBilletsAlorsServiceEstContactePourCreationDeNBilletsEtAvecBonBillet() throws UIDroitsInsuffisants {
		adminDesireCreerMultiplesBilletsGeneraux();
		creationBilletBean.creerBilletClicked();
		verify(mockAdminApplication).ajouterPlusieursNouveauxBilletsUnMatch(mockBillet, NOMBRE_BILLETS_VOULU);
	}

	@Test
	public void siEventCreationBilletReserveAlorsServiceEstContactePourCreationDuBillet() throws UIDroitsInsuffisants {
		adminDesireCreerUnBilletReserve();
		creationBilletBean.creerBilletClicked();
		verify(mockAdminApplication).ajouterNouveauBilletUnMatch(mockBillet);

	}

	@Test
	public void siEventAnnulerCreationAlorsBonRoutingMessageEnvoye() {
		String routingMessage = creationBilletBean.annulerCreationBilletClicked();
		assertEquals(routingMessage, BON_CREATIONBILLETANNULEEMSG);
	}

	@Test
	public void siMatchIDEstNullQuandDemandeSectionsLieesAuMatchAlorsRecoitListeVide() {
		champsMatchIDEstVide();
		List<String> sections = creationBilletBean.getListeNomsSections();
		assertTrue(sections.isEmpty());
	}

	@Test
	public void siMatchIDEstNONNullQuandDemandeSectionsLieesAlorsFacadeAppelleAvecBonMatchID() throws UIEntiteInexistante {
		when(mockBillet.getMatch_id()).thenReturn(UN_ID_QUELCONQUE);
		creationBilletBean.getListeNomsSections();
		verify(mockUtilisateurApplication).getSectionsLieesSelonMatchId(UN_ID_QUELCONQUE);
	}

	private void champsMatchIDEstVide() {
		when(mockBillet.getMatch_id()).thenReturn(null);
	}

	private void adminDesireCreerUnBilletReserve() {
		when(mockBillet.getCategorie_siege()).thenReturn("reserve");
	}

	private void adminDesireCreerMultiplesBilletsGeneraux() {
		creationBilletBean.setNombreBillets(NOMBRE_BILLETS_VOULU);
		when(mockBillet.getCategorie_siege()).thenReturn("general");
	}

	@Test
	public void siOncreeUnNombreDeBilletAlorsLeNombreDeBilletCreeCorrespond() {
		creationBilletBean.setNombreBillets(NOMBRE_BILLETS_VOULU);
		assertEquals(creationBilletBean.getNombreBillets(), NOMBRE_BILLETS_VOULU);
	}

}
