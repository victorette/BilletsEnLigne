package ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire.unmatch;

import ca.ulaval.ift6003.interfaces.beans.inventaire.unmatch.InventaireUnMatchFiltreOptions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class InventaireUnMatchFiltreOptionsTest {

	private InventaireUnMatchFiltreOptions filtreOptions;

	@Before
	public void setup() {
		filtreOptions = new InventaireUnMatchFiltreOptions();
	}

	@Test
	public void quandCreeFiltreOptionsAlorsSportTypeOptionsNeSontPasNulles() {
		assertFalse(filtreOptions.getSportTypeOptions() == null);
	}

	@Test
	public void quandCreeFiltreOptionsAlorsSportSexeOptionsNeSontPasNulles() {
		assertFalse(filtreOptions.getSportSexeOptions() == null);
	}

	@Test
	public void quandCreeFiltreOptionsAlorsCategorieSiegeOptionsNeSontPasNulles() {
		assertFalse(filtreOptions.getCategorieSiegeOptions() == null);
	}

}
