package ca.ulaval.ift6003.tests.unit.domaine.modele.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.exceptions.DroitsInsuffisants;

public class DroitsInsuffisantsTest {
	DroitsInsuffisants droitsInsuffisants;

	@Before
	public void setUp() throws Exception {
		String msg = "";
		droitsInsuffisants = new DroitsInsuffisants(msg);
	}

	@Test
	public void testDroitsInsuffisants() throws Exception {

		assertNotNull(droitsInsuffisants);
		assertNull(droitsInsuffisants.getCause());
		assertEquals(
				"ca.ulaval.ift6003.domaine.modele.exceptions.DroitsInsuffisants: Droits insuffisants ::: ",
				droitsInsuffisants.toString());
		assertEquals("Droits insuffisants ::: ", droitsInsuffisants.getMessage());
		assertEquals("Droits insuffisants ::: ", droitsInsuffisants.getLocalizedMessage());
	}

}
