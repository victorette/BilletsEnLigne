package ca.ulaval.ift6003.domaine.modele.inventaire;

import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface MatchService extends Serializable {

	void ajouterNouveauMatch(Match match);

	List<String> getListeIdMatchs();

	Map<String, Match> getMapMatchsAVenir();

	Map<String, Match> getTousMatchsReliesAuxBillets(List<BilletUnMatch> billets);

	List<String> sectionsDuCentreSportifLieAUnMatch(String match_id) throws EntiteNonTrouvee;
}
