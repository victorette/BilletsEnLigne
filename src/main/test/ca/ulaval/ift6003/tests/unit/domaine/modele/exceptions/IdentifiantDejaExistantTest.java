package ca.ulaval.ift6003.tests.unit.domaine.modele.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;

public class IdentifiantDejaExistantTest {
	IdentifiantDejaExistant identifiantDejaExistant;

	@Before
	public void setUp() throws Exception {
		String msg = "";
		identifiantDejaExistant = new IdentifiantDejaExistant(msg);

	}

	@Test
	public void testIdentifiantDejaExistant_2() throws Exception {

		assertNotNull(identifiantDejaExistant);
		assertNull(identifiantDejaExistant.getCause());
		assertEquals("ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant: ",
				identifiantDejaExistant.toString());
		assertEquals("", identifiantDejaExistant.getMessage());
		assertEquals("", identifiantDejaExistant.getLocalizedMessage());
	}

}
