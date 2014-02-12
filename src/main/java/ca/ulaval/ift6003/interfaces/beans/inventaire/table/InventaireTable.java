package ca.ulaval.ift6003.interfaces.beans.inventaire.table;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;

public class InventaireTable<DTOTYPE extends EntreeDTO> {

	private InventaireTableDataModel<DTOTYPE> inventaireDataModel;
	private DTOTYPE entreeChoisie;
	private List<DTOTYPE> filteredDTOs;
	private List<DTOTYPE> tableDTOs;

	public InventaireTable(List<DTOTYPE> dtos) {
		tableDTOs = dtos;
		entreeChoisie = null;
		inventaireDataModel = new InventaireTableDataModel<>(tableDTOs);
	}

	public InventaireTable() {
		tableDTOs = new ArrayList<>();
		entreeChoisie = null;
		inventaireDataModel = new InventaireTableDataModel<>(tableDTOs);
	}

	@SuppressWarnings("unchecked")
	public void onRowSelect(SelectEvent event) {
		entreeChoisie = (DTOTYPE) event.getObject();
	}

	public void onRowUnselect(UnselectEvent event) {
		entreeChoisie = null;
	}

	public InventaireTableDataModel<DTOTYPE> getInventaireDataModel() {
		return inventaireDataModel;
	}

	public void setInventaireDataModel(InventaireTableDataModel<DTOTYPE> inventaireDataModel) {
		this.inventaireDataModel = inventaireDataModel;
	}

	public DTOTYPE getEntreeChoisie() {
		return entreeChoisie;
	}

	public void setEntreeChoisie(DTOTYPE entreeChoisie) {
		this.entreeChoisie = entreeChoisie;
	}

	public List<DTOTYPE> getFilteredDTOs() {
		return filteredDTOs;
	}

	public void setFilteredDTOs(List<DTOTYPE> filteredDTOs) {
		this.filteredDTOs = filteredDTOs;
	}

	public List<DTOTYPE> getTableDTOs() {
		return tableDTOs;
	}

	public void setTableDTOs(List<DTOTYPE> tableDTOs) {
		this.tableDTOs = tableDTOs;
	}

	public boolean uneEntreeEstChoisie() {
		return entreeChoisie != null;
	}

}
