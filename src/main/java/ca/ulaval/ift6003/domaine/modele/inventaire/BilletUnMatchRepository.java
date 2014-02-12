package ca.ulaval.ift6003.domaine.modele.inventaire;

import java.util.List;
import java.util.Set;

import ca.ulaval.ift6003.domaine.shared.Repository;

public interface BilletUnMatchRepository extends Repository<BilletUnMatch> {

	void supprimerTousAyantMatchID(String matchId);

	List<BilletUnMatch> selectTousAyantMatchIDs(Set<String> matchIDs);

	void insererTous(List<BilletUnMatch> tousBillets);

}