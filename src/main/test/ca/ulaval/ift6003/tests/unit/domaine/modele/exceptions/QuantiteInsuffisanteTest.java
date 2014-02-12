package ca.ulaval.ift6003.tests.unit.domaine.modele.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.exceptions.QuantiteInsuffisante;

public class QuantiteInsuffisanteTest {
	QuantiteInsuffisante quantiteInsuffisante;

	@Before
	public void setUp() throws Exception {
		String msg = "";

		quantiteInsuffisante = new QuantiteInsuffisante(msg);
	}

	@Test
	public void testQuantiteInsuffisante() throws Exception {

		assertNotNull(quantiteInsuffisante);
		assertNull(quantiteInsuffisante.getCause());
		assertEquals("ca.ulaval.ift6003.domaine.modele.exceptions.QuantiteInsuffisante: ",
				quantiteInsuffisante.toString());
		assertEquals("", quantiteInsuffisante.getMessage());
		assertEquals("", quantiteInsuffisante.getLocalizedMessage());
	}

}
