package ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.interfaces.beans.inventaire.table.InventaireTable;
import ca.ulaval.ift6003.interfaces.beans.inventaire.table.InventaireTableDataModel;

public class InventaireTableTest {
	private InventaireTable<EntreeDTO> inventaireTableDTOs;

	List<EntreeDTO> listeEntreeDTO;
	EntreeSaisonDTO entreeSaisonDTO;
	EntreeDTO entreeDTO;

	private final String NOMCENTRESPORTIF = "nomCentreSportif";
	private final String BILLETID = "billetid";

	@Before
	public void setUp() throws Exception {

		listeEntreeDTO = new LinkedList<EntreeDTO>();

		inventaireTableDTOs = new InventaireTable<EntreeDTO>(listeEntreeDTO);

		inventaireTableDTOs.setInventaireDataModel(new InventaireTableDataModel<EntreeDTO>());

	}

	@Test
	public void entreeSaisonQuandSpecifierBilletEtCentreAlorsInventaireNonVide() throws Exception {
		affecterEntreeSaisonAvecBilletEtCentre();
		entreeDTO = inventaireTableDTOs.getEntreeChoisie();
		assertNotNull(entreeDTO);
		assertNotNull(entreeDTO.getBilletID());
		assertNotNull(entreeDTO.getNomCentreSportif());

	}

	@Test
	public void SpecifierFiltreQuandFiltrerInventaireAlorsNonVide() throws Exception {
		affecterFiltre();

		List<EntreeDTO> filteredDTOs = inventaireTableDTOs.getFilteredDTOs();

		assertNotNull(filteredDTOs);
		assertEquals(0, filteredDTOs.size());
	}

	@Test
	public void inventaireDTOQuandInventaireModeleAlorsNonVide() throws Exception {

		inventaireTableDTOs.setInventaireDataModel(new InventaireTableDataModel<EntreeDTO>());

		InventaireTableDataModel<EntreeDTO> inventaireDataModel = inventaireTableDTOs.getInventaireDataModel();
		assertNotNull(inventaireDataModel);
		assertEquals(-1, inventaireDataModel.getRowCount());
		assertEquals(-1, inventaireDataModel.getRowIndex());
		assertFalse(inventaireDataModel.isRowAvailable());
		assertNull(inventaireDataModel.getRowData());
		assertNull(inventaireDataModel.getWrappedData());
	}

	@Test
	public void inventaireDTOQuandDTOAlorsNonVide() throws Exception {

		inventaireTableDTOs.setInventaireDataModel(new InventaireTableDataModel<EntreeDTO>());

		List<EntreeDTO> tableDTOs = inventaireTableDTOs.getTableDTOs();

		assertNotNull(tableDTOs);
		assertEquals(0, tableDTOs.size());
	}

	private void affecterEntreeSaisonAvecBilletEtCentre() {
		entreeSaisonDTO = new EntreeSaisonDTO();

		entreeSaisonDTO.billetID = BILLETID;
		entreeSaisonDTO.nomCentreSportif = NOMCENTRESPORTIF;
		inventaireTableDTOs.setEntreeChoisie(entreeSaisonDTO);
	}

	private void affecterFiltre() {
		inventaireTableDTOs.setFilteredDTOs(listeEntreeDTO);

	}

}
