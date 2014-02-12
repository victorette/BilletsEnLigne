package ca.ulaval.ift6003.application.frontiere.DTOassembleurs;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;

public class AssembleurService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final EntreeUnMatchDTOAssembleur entreeUnMatchDTOAssembleur = new EntreeUnMatchDTOAssembleur();
	private static final EntreeSaisonDTOAssembleur entreeSaisonDTOAssembleur = new EntreeSaisonDTOAssembleur();

	public static List<EntreeUnMatchDTO> convertirInventaireUnMatchEnDTOs(Inventaire<EntreeUnMatch> inventaire, Map<String, Integer> quantiteDispoParType) {
		return entreeUnMatchDTOAssembleur.inventaireToDTOs(inventaire, quantiteDispoParType);
	}

	public static List<EntreeSaisonDTO> convertirInventaireSaisonEnDTOs(Inventaire<EntreeSaison> inventaire, Map<String, Integer> quantiteDispoParType) {
		return entreeSaisonDTOAssembleur.inventaireToDTOs(inventaire, quantiteDispoParType);
	}
}
