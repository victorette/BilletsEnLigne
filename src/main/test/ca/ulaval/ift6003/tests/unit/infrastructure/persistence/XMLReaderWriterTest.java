package ca.ulaval.ift6003.tests.unit.infrastructure.persistence;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.infrastructure.persistence.XMLReaderWriter;

public class XMLReaderWriterTest {
	XMLReaderWriter xmlReaderWriter;

	@Before
	public void setUp() throws Exception {
		xmlReaderWriter = new XMLReaderWriter();
	}

	@Test
	public void testEcrireDansFichierXML() throws Exception {

		String xml = " ";
		String nomFichier = " ";

		xmlReaderWriter.ecrireDansFichierXML(xml, nomFichier);

	}

	@Test
	public void testLireFichierXML() throws Exception {

		String nomFichier = " ";

		String result = xmlReaderWriter.lireFichierXML(nomFichier);
		assertNotNull(result);
	}

}
