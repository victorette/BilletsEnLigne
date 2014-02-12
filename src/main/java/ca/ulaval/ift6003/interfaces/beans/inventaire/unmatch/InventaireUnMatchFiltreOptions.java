package ca.ulaval.ift6003.interfaces.beans.inventaire.unmatch;

import javax.faces.model.SelectItem;

public class InventaireUnMatchFiltreOptions {

	private SelectItem[] sportTypeOptions;
	private SelectItem[] sportSexeOptions;
	private SelectItem[] categorieSiegeOptions;

	public InventaireUnMatchFiltreOptions() {
		creerFiltreOptions();
	}

	private void creerFiltreOptions() {
		sportTypeOptions = creerOptionsSportType();
		sportSexeOptions = creerOptionsSportSexe();
		categorieSiegeOptions = creerOptionsCategorieSiege();
	}

	private SelectItem[] creerOptionsCategorieSiege() {
		return new SelectItem[] { new SelectItem("", "Select"), new SelectItem("reserve", "réservé"), new SelectItem("general", "général") };
	}

	private SelectItem[] creerOptionsSportSexe() {
		return new SelectItem[] { new SelectItem("", "Select"), new SelectItem("masculin", "masculin"), new SelectItem("feminin", "feminin") };
	}

	private SelectItem[] creerOptionsSportType() {
		return new SelectItem[] { new SelectItem("", "Select"), new SelectItem("rugby", "rugby"), new SelectItem("football", "football"),
				new SelectItem("basketball", "basketball"), new SelectItem("soccer", "soccer"), new SelectItem("volleyball", "volleyball") };
	}

	public SelectItem[] getSportTypeOptions() {
		return sportTypeOptions;
	}

	public SelectItem[] getSportSexeOptions() {
		return sportSexeOptions;
	}

	public SelectItem[] getCategorieSiegeOptions() {
		return categorieSiegeOptions;
	}

}
