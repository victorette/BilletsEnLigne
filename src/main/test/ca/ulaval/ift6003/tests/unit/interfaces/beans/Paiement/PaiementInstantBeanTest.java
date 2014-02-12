package ca.ulaval.ift6003.tests.unit.interfaces.beans.Paiement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import ca.ulaval.ift6003.interfaces.beans.Paiement.PaiementInstantBean;

public class PaiementInstantBeanTest {
	private PaiementInstantBean paiementInstantBean;
	private EntreeDTO mockEntreeDTO;

	@Before
	public void setUp() throws Exception {
		mockEntreeDTO = mock(EntreeDTO.class);
		paiementInstantBean = new PaiementInstantBean(mockEntreeDTO);

	}

	@Test
	public void testGetNombreArticles() throws Exception {
		when(mockEntreeDTO.getNombreBilletsDesires()).thenReturn(1);
		int nombrearticles = paiementInstantBean.getNombreArticles();

		assertEquals(0, nombrearticles);
	}

	@Test
	public void testGetPrixTotal() throws Exception {

		when(mockEntreeDTO.getPrix()).thenReturn(1.0);
		when(mockEntreeDTO.getNombreBilletsDesires()).thenReturn(1);

		double prixtotal = paiementInstantBean.getPrixTotal();
		assertEquals(1.0, prixtotal, 1.1);
	}

}
