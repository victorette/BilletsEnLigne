package ca.ulaval.ift6003.domaine.modele.inventaire;

import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CentreSportifService extends Serializable {

	void ajouterNouveauCentreSportif(CentreSportif centreSportif) throws IdentifiantDejaExistant;

	List<String> getListeNomsCentresSportifs();

	List<String> getSectionsCentreSportif(String centreNom);

	Map<String, CentreSportif> getTousCentresReliesAuxMatchs(Map<String, Match> matchs);

	Map<String, CentreSportif> getCentresReliesBilletsSaisons(List<BilletSaison> billets);

}
