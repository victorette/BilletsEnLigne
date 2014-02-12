package ca.ulaval.ift6003.infrastructure.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifService;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;

@SessionScoped
@ManagedBean(name = "centreSportifService")
public class CentreSportifServiceImpl implements CentreSportifService {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{centreSportifRepository}")
	private CentreSportifRepository centreSportifRepository;

	public void ajouterNouveauCentreSportif(CentreSportif centreSportif) throws IdentifiantDejaExistant {
		if (nomCentreSportifUnique(centreSportif.getNom()))
			centreSportifRepository.inserer(centreSportif);
		else
			throw new IdentifiantDejaExistant();

	}

	public List<String> getListeNomsCentresSportifs() {
		List<String> noms = new ArrayList<>();
		for (CentreSportif centre : centreSportifRepository.selectTous())
			noms.add(centre.getNom());

		return noms;
	}

	private boolean nomCentreSportifUnique(String nom) {
		try {
			@SuppressWarnings("unused")
			CentreSportif centre = centreSportifRepository.selectParIdentifiantUnique(nom);
			return false;
		} catch (EntiteNonTrouvee e) {
			return true;
		}
	}

	public List<String> getSectionsCentreSportif(String centreNom) {
		try {
			CentreSportif centre = centreSportifRepository.selectParIdentifiantUnique(centreNom);
			return centre.getSections();
		} catch (EntiteNonTrouvee e) {
			return new ArrayList<>();
		}

	}

	public Map<String, CentreSportif> selectTousAyantIDs(Set<String> centresID) {
		Map<String, CentreSportif> tous = centreSportifRepository.selectTousMap();
		Set<String> tousIDs = tous.keySet();
		Map<String, CentreSportif> ayantID = new HashMap<>();

		for (String id : centresID) {
			if (tousIDs.contains(id))
				ayantID.put(id, tous.get(id));
		}
		return ayantID;
	}

	@Override
	public Map<String, CentreSportif> getTousCentresReliesAuxMatchs(Map<String, Match> matchs) {
		Map<String, CentreSportif> centres = centreSportifRepository.selectTousMap();
		Map<String, CentreSportif> centresRelies = new HashMap<>();
		for (Match match : matchs.values()) {
			String centreNom = match.getNomCentreSportif();
			if (centres.containsKey(centreNom))
				centresRelies.put(centreNom, centres.get(centreNom));
		}

		return centresRelies;
	}

	public Map<String, CentreSportif> getCentresReliesBilletsSaisons(List<BilletSaison> billets) {
		Map<String, CentreSportif> centres = centreSportifRepository.selectTousMap();
		Map<String, CentreSportif> centresRelies = new HashMap<>();

		for (BilletSaison billet : billets) {
			String nomCentre = billet.getNomCentreSportif();
			if (!centresRelies.containsKey(nomCentre))
				centresRelies.put(nomCentre, centres.get(nomCentre));

		}

		return centresRelies;
	}

	public void setCentreSportifRepository(CentreSportifRepository centreSportifRepository) {
		this.centreSportifRepository = centreSportifRepository;
	}
}
