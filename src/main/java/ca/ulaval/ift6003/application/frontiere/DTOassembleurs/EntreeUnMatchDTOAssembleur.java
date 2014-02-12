package ca.ulaval.ift6003.application.frontiere.DTOassembleurs;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;

import java.io.Serializable;

public class EntreeUnMatchDTOAssembleur extends EntreeDTOAssembleur<EntreeUnMatchDTO, EntreeUnMatch> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public EntreeUnMatchDTO ajouterSpecifique(EntreeUnMatch entree, EntreeUnMatchDTO dto) {
		dto.sportType = entree.getSportType();
		dto.sportSexe = entree.getSportSexe();
		dto.equipeVisiteur = entree.getEquipeVisiteur();
		dto.matchID = entree.getMatchID();
		dto.date = entree.getDate();
		return dto;
	}

	@Override
	public EntreeUnMatchDTO creerDTOVide() {
		return new EntreeUnMatchDTO();
	}

}
