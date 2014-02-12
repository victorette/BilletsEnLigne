package ca.ulaval.ift6003.interfaces.beans.inventaire.saison;

import javax.faces.model.SelectItem;

public class InventaireSaisonFiltreOptions {

	private SelectItem[] categorieSiegeOptions;

	public InventaireSaisonFiltreOptions() {
		creerFiltreOptions();
	}

	private void creerFiltreOptions() {
		categorieSiegeOptions = creerOptionsCategorieSiege();
	}

	private SelectItem[] creerOptionsCategorieSiege() {
		return new SelectItem[] { new SelectItem("", "Select"), new SelectItem("reserve", "réservé"), new SelectItem("general", "général") };
	}

	public SelectItem[] getCategorieSiegeOptions() {
		return categorieSiegeOptions;
	}

}
