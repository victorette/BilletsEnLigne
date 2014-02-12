package ca.ulaval.ift6003.tests.unit.interfaces.beans;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIQuantiteInsuffisante;
import ca.ulaval.ift6003.application.impl.PanierApplicationImpl;
import ca.ulaval.ift6003.interfaces.beans.Paiement.PanierBean;
import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

public class PanierBeanTest {

	private PanierBean unPanierBean;
	private PanierApplicationImpl mockPanierApplication;
	private GUIMessageHandler mockGUIMessageHandler;
	private EntreeDTO mockDTO;
	private EntreeSaisonDTO mockSaisonDTO;

	@Before
	public void setUp() throws Exception {
		unPanierBean = new PanierBean();

		mockPanierApplication = mock(PanierApplicationImpl.class);
		mockGUIMessageHandler = mock(GUIMessageHandler.class);
		mockDTO = mock(EntreeDTO.class);
		mockSaisonDTO = mock(EntreeSaisonDTO.class);
		unPanierBean.setGuiMessageHandler(mockGUIMessageHandler);
		unPanierBean.setPanierApplication(mockPanierApplication);
	}

	@Test
	public void siListeBilletUnMatchInvoqueAlorsPanierApplicationInvoque() {
		unPanierBean.getListeBilletsUnMatch();
		verify(mockPanierApplication).listeEntreesUnMatchDeMonPanier();
	}

	@Test
	public void siListeBilletSaisonInvoqueAlorsPanierApplicationInvoque() {
		unPanierBean.getListeBilletsSaison();
		verify(mockPanierApplication).listeEntreesSaisonDeMonPanier();
	}

	@Test
	public void siPrixTotalDeMonPanierEstInvoqueAlorsPanierApplicationInvoque() {
		unPanierBean.getPrixTotalDuPanier();
		verify(mockPanierApplication).prixTotalDuPanier();
	}

	@Test
	public void siViderPanierEstInvoqueAlorsPanierApplicationInvoque() {
		unPanierBean.viderPanier();
		verify(mockPanierApplication).viderMonPanier();
	}

	@Test
	public void quandJeQuantiteBilletSaisonNegativeAlorsPanierApplicationNeModifiePasLaQuantite()
			throws UIQuantiteInsuffisante {
		when(mockSaisonDTO.getNombreBilletsDesires()).thenReturn(-1);
		unPanierBean.changerQuantiteBilletSaison(mockSaisonDTO);
		verify(mockGUIMessageHandler).addMessage("Erreur!", "Vous devez entrer un nombre positif.");

	}

	@Test
	public void quandJeQuantiteBilletSaisonZeroAlorsPanierApplicationRetirerBillets()
			throws UIQuantiteInsuffisante {
		when(mockSaisonDTO.getNombreBilletsDesires()).thenReturn(0);
		unPanierBean.changerQuantiteBilletSaison(mockSaisonDTO);
		verify(mockPanierApplication).retirerTousBilletsDeType(mockSaisonDTO.type);

	}

	@Test
	public void quandJeModifieQuantiteBilletSaisonAlorsPanierApplicationModifieLaQuantite()
			throws UIQuantiteInsuffisante {
		when(mockSaisonDTO.getNombreBilletsDesires()).thenReturn(10);

		unPanierBean.changerQuantiteBilletSaison(mockSaisonDTO);
		verify(mockPanierApplication).changerQuantiteBilletSaisonDuPanier(mockSaisonDTO);
	}

	@Test
	public void quandJeModifieQuantiteBilletUnMatchAlorsPanierApplicationModifieLaQuantite()
			throws UIQuantiteInsuffisante {
		when(mockDTO.getNombreBilletsDesires()).thenReturn(1);
		unPanierBean.changerQuantiteBilletUnMatch(mockDTO);
		verify(mockPanierApplication).changerQuantiteBilletUnMatchDuPanier(mockDTO);
	}

}
