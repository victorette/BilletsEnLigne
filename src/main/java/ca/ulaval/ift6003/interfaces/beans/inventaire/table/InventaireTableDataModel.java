package ca.ulaval.ift6003.interfaces.beans.inventaire.table;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.util.List;

public class InventaireTableDataModel<T extends EntreeDTO> extends ListDataModel<T> implements SelectableDataModel<T> {

	public InventaireTableDataModel() {

	}

	public InventaireTableDataModel(List<T> data) {
		super(data);
	}

	@Override
	public T getRowData(String rowKey) {
		@SuppressWarnings("unchecked")
		List<T> dtos = (List<T>) getWrappedData();

		for (T dto : dtos) {
			if (dto.type.equals(rowKey)) {
				return dto;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(T rowDTO) {
		return rowDTO.type;
	}

}
