package ca.ulaval.ift6003.application.frontiere.DTOassembleurs;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.Entree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class EntreeDTOAssembleur<INVDTO extends EntreeDTO, ENTREE extends Entree> implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<INVDTO> inventaireToDTOs(Inventaire<ENTREE> inventaire, Map<String, Integer> quantiteDispoParType) {
		Map<String, Integer> nbChoisisParType = inventaire.nombreEntreesParType();

		List<INVDTO> dtos = new ArrayList<>();

		for (ENTREE entree : inventaire.entreesDistinctes()) {
			int nombreChoisiPourCeType = nbChoisisParType.get(entree.getType());
			int quantiteDispoPourCeType = quantiteDispoParType.get(entree.getType());
			dtos.add(entreeToDTO(entree, nombreChoisiPourCeType, quantiteDispoPourCeType));
		}

		return dtos;
	}

	public abstract INVDTO ajouterSpecifique(ENTREE entree, INVDTO dto);

	public abstract INVDTO creerDTOVide();

	private INVDTO entreeToDTO(ENTREE entree, int nombreChoisiPourCeType, int nombreDispoPourCeType) {
		INVDTO dto = creerDTOVide();

		dto.billetID = entree.getBilletID();
		dto.nomCentreSportif = entree.getNomCentreSportif();
		dto.ville = entree.getVille();
		dto.nomSection = entree.getNomSection();
		dto.categorieSiege = entree.getCategorieSiege();
		dto.prix = entree.getPrix();
		dto.nombreBilletsDesires = nombreChoisiPourCeType;
		dto.nombreBilletsDisponibles = nombreDispoPourCeType;
		dto.type = entree.getType();
		dto.prixTotal = dto.prix * dto.nombreBilletsDesires;

		dto = ajouterSpecifique(entree, dto);

		return dto;
	}

}
