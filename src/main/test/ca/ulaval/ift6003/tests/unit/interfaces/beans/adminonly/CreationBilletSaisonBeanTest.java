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
import ca.ulaval.ift6003.application.impl.AdminApplicationImpl;
import ca.ulaval.ift6003.application.impl.InventaireApplicationImpl;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.interfaces.beans.billets.CreationBilletSaisonBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class CreationBilletSaisonBeanTest {

	private final String BON_CREATIONBILLETANNULEEMSG = "creation-billet-annulee";
	private final String NOM_CENTRE_SPORTIF = "nomCentreSportif";
	private final int NOMBRE_BILLETS_VOULU = 4;

	private CreationBilletSaisonBean creationBilletSaisonBean;
	private AdminApplicationImpl mockAdminApplication;
	private InventaireApplicationImpl mockUtilisateurApplication;
	private GUIMessageHandler mockGuiMessageHandler;
	private BilletSaison mockBillet;

	@Before
	public void setup() {
		creationBilletSaisonBean = new CreationBilletSaisonBean();

		mockAdminApplication = mock(AdminApplicationImpl.class);
		mockUtilisateurApplication = mock(InventaireApplicationImpl.class);
		mockGuiMessageHandler = mock(GUIMessageHandler.class);
		mockBillet = mock(BilletSaison.class);

		creationBilletSaisonBean.setGuiMessageHandler(mockGuiMessageHandler);
		creationBilletSaisonBean.setAdminApplication(mockAdminApplication);
		creationBilletSaisonBean.setInventaireApplication(mockUtilisateurApplication);
		creationBilletSaisonBean.setBillet(mockBillet);
	}

	@Test
	public void siEventCreationNBilletsAlorsServiceEstContactePourCreationDeNBilletsEtAvecBonBillet() throws UIDroitsInsuffisants {
		adminDesireCreerMultiplesBilletsGeneraux();
		creationBilletSaisonBean.creerBilletClicked();

		verify(mockAdminApplication).ajouterPlusieursNouveauxBilletsSaison(mockBillet, NOMBRE_BILLETS_VOULU);

	}

	@Test
	public void siEventCreationBilletReserveAlorsServiceEstContactePourCreationDuBillet() throws UIDroitsInsuffisants {
		adminDesireCreerUnBilletReserve();
		creationBilletSaisonBean.creerBilletClicked();

		verify(mockAdminApplication).ajouterNouveauBilletSaison(mockBillet);

	}

	@Test
	public void siNomCentreEstNullOuVideQuandDemandeListeNomsSectionAlorsNeContactePasApplication() {
		when(mockBillet.getNomCentreSportif()).thenReturn(null);
		creationBilletSaisonBean.getListeNomsSections();
		List<String> RESULTAT = creationBilletSaisonBean.getListeNomsSections();
		assertTrue(RESULTAT.isEmpty());
	}

	@Test
	public void siNomCentreEstRempliAlorsApplicationEstAppelleAvecCenomPourObtenirLesSections() {
		when(mockBillet.getNomCentreSportif()).thenReturn(NOM_CENTRE_SPORTIF);
		creationBilletSaisonBean.getListeNomsSections();
		verify(mockUtilisateurApplication).getSectionsCentreSportif(mockBillet.getNomCentreSportif());
	}

	@Test
	public void verifGetListeNomCentreSportif() {
		creationBilletSaisonBean.getListeNomsCentresSportifs();
		verify(mockUtilisateurApplication).getListeNomsCentresSportifs();
	}

	@Test
	public void siEventAnnulerCreationAlorsBonRoutingMessageEnvoye() {
		String routingMessage = creationBilletSaisonBean.annulerCreationBilletClicked();
		assertEquals(routingMessage, BON_CREATIONBILLETANNULEEMSG);
	}

	private void adminDesireCreerUnBilletReserve() {
		when(mockBillet.getCategorie_siege()).thenReturn("reserve");
	}

	private void adminDesireCreerMultiplesBilletsGeneraux() {
		creationBilletSaisonBean.setNombreBillets(NOMBRE_BILLETS_VOULU);
		when(mockBillet.getCategorie_siege()).thenReturn("general");
	}

	@Test
	public void siOncreeUnNombreDeBilletAlorsLeNombreDeBilletCreeCorrespond() {
		creationBilletSaisonBean.setNombreBillets(NOMBRE_BILLETS_VOULU);
		assertEquals(creationBilletSaisonBean.getNombreBillets(), NOMBRE_BILLETS_VOULU);
	}

}
