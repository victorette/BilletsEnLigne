package ca.ulaval.ift6003.infrastructure.persistence.repositories;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifRepository;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
@ManagedBean(name = "centreSportifRepository", eager = true)
public class CentreSportifRepositoryImpl extends RepositoryImpl<CentreSportif> implements CentreSportifRepository {

	private static final long serialVersionUID = 1L;

	public CentreSportifRepositoryImpl() {
		NOM_FICHIER = new java.io.File("").getAbsolutePath() + Consts.CENTRESSPORTIFS;
		convertisseur = new Convertisseur<>(CentreSportif.class);
	}

	public Map<String, CentreSportif> selectTousAyantIDs(Set<String> IDs) {
		Map<String, CentreSportif> tous = selectTousMap();
		Set<String> tousIDs = tous.keySet();

		Map<String, CentreSportif> ayantID = new HashMap<>();

		for (String id : IDs) {
			if (tousIDs.contains(id))
				ayantID.put(id, tous.get(id));
		}
		return ayantID;
	}
}
