package ca.ulaval.ift6003.domaine.modele.inventaire;

import ca.ulaval.ift6003.domaine.shared.Repository;

import java.util.Map;
import java.util.Set;

public interface CentreSportifRepository extends Repository<CentreSportif> {

	Map<String, CentreSportif> selectTousAyantIDs(Set<String> IDs);
}