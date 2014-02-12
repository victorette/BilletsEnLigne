package ca.ulaval.ift6003.tests.unit.domaine.modele.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;

public class EntiteNonTrouveeTest {
	EntiteNonTrouvee entiteNonTrouvee;

	@Before
	public void setUp() throws Exception {
		entiteNonTrouvee = new EntiteNonTrouvee();
	}

	@Test
	public void testEntiteNonTrouvee() throws Exception {

		assertNotNull(entiteNonTrouvee);
		assertNull(entiteNonTrouvee.getCause());
		assertEquals("ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee",
				entiteNonTrouvee.toString());
		assertNull(entiteNonTrouvee.getMessage());
		assertNull(entiteNonTrouvee.getLocalizedMessage());
	}

}
