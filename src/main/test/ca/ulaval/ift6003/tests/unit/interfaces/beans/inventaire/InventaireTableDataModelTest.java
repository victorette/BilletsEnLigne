package ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.interfaces.beans.inventaire.table.InventaireTableDataModel;

public class InventaireTableDataModelTest {
	InventaireTableDataModel<EntreeDTO> inventaireTableDataModel;
	List<EntreeDTO> listeEntreeDTO;

	@Before
	public void setUp() throws Exception {
		listeEntreeDTO = new LinkedList<EntreeDTO>();
		inventaireTableDataModel = new InventaireTableDataModel<EntreeDTO>(listeEntreeDTO);
	}

	@Test
	public void inventaireAppeleAlorsInitialisationAffichage() {
		assertNotNull(inventaireTableDataModel);
		assertEquals(0, inventaireTableDataModel.getRowCount());
		assertEquals(0, inventaireTableDataModel.getRowIndex());
		assertEquals(false, inventaireTableDataModel.isRowAvailable());
	}

	@Test
	public void selectionLigneInventaireVideAlorsPasAffichage() throws Exception {
		String rowKey = "";

		EntreeDTO entreeDTO = inventaireTableDataModel.getRowData(rowKey);

		assertNull(entreeDTO);
	}

	@Test
	public void entreeInventaireVideAlorsSelectionLigneNonRemplie() throws Exception {

		Object rowDTOType = inventaireTableDataModel.getRowKey(new EntreeSaisonDTO());
		assertNull(rowDTOType);
	}

}
