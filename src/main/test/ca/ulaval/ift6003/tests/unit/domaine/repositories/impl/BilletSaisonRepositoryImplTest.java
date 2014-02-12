package ca.ulaval.ift6003.tests.unit.domaine.repositories.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletSaisonRepositoryImpl;

public class BilletSaisonRepositoryImplTest {
	BilletSaisonRepositoryImpl billetSaisonRepositoryImpl;

	@Before
	public void setUp() throws Exception {
		billetSaisonRepositoryImpl = new BilletSaisonRepositoryImpl();

	}

	@Test
	public void billetSaisonReferentielQuandCreationAlorsVide() {
		assertNotNull(billetSaisonRepositoryImpl);
		assertEquals(null, billetSaisonRepositoryImpl.getXmlReaderWriter());
	}

}
