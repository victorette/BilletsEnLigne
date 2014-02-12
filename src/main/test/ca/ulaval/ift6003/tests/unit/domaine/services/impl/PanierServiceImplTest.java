package ca.ulaval.ift6003.tests.unit.domaine.services.impl;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.exceptions.QuantiteInsuffisante;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.panieretachats.Panier;
import ca.ulaval.ift6003.infrastructure.services.PanierServiceImpl;

public class PanierServiceImplTest {

	private Panier mockPanier;
	private PanierServiceImpl panierServiceImpl;

	@Before
	public void setup() {
		mockPanier = mock(Panier.class);
		panierServiceImpl = new PanierServiceImpl();
	}

	@Test(expected = QuantiteInsuffisante.class)
	public void siNombreBilletsSaisonInferieurQuantiteDesireeAlorsErreur() throws Exception {

		List<BilletSaison> billetsDuBonType = new LinkedList<BilletSaison>();
		int quantiteDesiree = 1;

		panierServiceImpl.ajouterBilletsSaisonDansPanier(mockPanier, billetsDuBonType, quantiteDesiree);
	}

	@Test
	public void siNombreBilletsSaisonEgalQuantiteDesireeAlorsAjouterDansPanier() throws Exception {

		List<BilletSaison> billetsDuBonType = new LinkedList<BilletSaison>();
		billetsDuBonType.add(new BilletSaison());
		int quantiteDesiree = 1;

		panierServiceImpl.ajouterBilletsSaisonDansPanier(mockPanier, billetsDuBonType, quantiteDesiree);

		verify(mockPanier).ajouterBilletsSaison(billetsDuBonType);
	}

	@Test(expected = QuantiteInsuffisante.class)
	public void siNombreBilletsMatchDansPanierInferieurQuantiteDesireeAlorsErreur() throws Exception {

		List<BilletUnMatch> billetsDuBonType = new LinkedList<BilletUnMatch>();
		int quantiteDesiree = 1;

		panierServiceImpl.ajouterBilletsUnMatchDansPanier(mockPanier, billetsDuBonType, quantiteDesiree);

	}

	@Test
	public void siNombreBilletsMatchEgalQuantiteDesireeAlorsAjouterDansPanier() throws Exception {
		PanierServiceImpl panierServiceImpl = new PanierServiceImpl();

		List<BilletUnMatch> billetsDuBonType = new LinkedList<BilletUnMatch>();
		billetsDuBonType.add(new BilletUnMatch());
		int quantiteDesiree = 1;

		panierServiceImpl.ajouterBilletsUnMatchDansPanier(mockPanier, billetsDuBonType, quantiteDesiree);

		verify(mockPanier).ajouterBilletsUnMatch(billetsDuBonType);
	}

}
