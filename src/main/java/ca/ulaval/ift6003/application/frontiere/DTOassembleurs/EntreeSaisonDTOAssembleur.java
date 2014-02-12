package ca.ulaval.ift6003.application.frontiere.DTOassembleurs;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;

import java.io.Serializable;

public class EntreeSaisonDTOAssembleur extends EntreeDTOAssembleur<EntreeSaisonDTO, EntreeSaison> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public EntreeSaisonDTO ajouterSpecifique(EntreeSaison entree, EntreeSaisonDTO dto) {
		dto.annee = entree.getAnnee();
		return dto;
	}

	@Override
	public EntreeSaisonDTO creerDTOVide() {
		return new EntreeSaisonDTO();
	}

}
