package ca.ulaval.ift6003.infrastructure.services;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.gestionIDs.GestionIDRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.domaine.modele.inventaire.MatchRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.MatchService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;
import java.util.*;

@SessionScoped
@ManagedBean(name = "matchService")
public class MatchServiceImpl implements MatchService, Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{matchRepository}")
	private MatchRepository matchRepository;
	@ManagedProperty(value = "#{gestionIDRepository}")
	private GestionIDRepository gestionIDRepository;
	@ManagedProperty(value = "#{centreSportifRepository}")
	private CentreSportifRepository centreSportifRepository;

	public void ajouterNouveauMatch(Match match) {
		int nextId = gestionIDRepository.selectEtUpdateNextId(Consts.NOM_ENTITE_MATCH);
		match.setMatchId(Integer.toString(nextId));
		matchRepository.inserer(match);
	}

	public List<String> getListeIdMatchs() {
		List<String> ids = new ArrayList<>();
		for (Match match : matchRepository.selectTous())
			ids.add(match.getMatchId());

		return ids;
	}

	public Map<String, Match> getMapMatchsAVenir() {
		Map<String, Match> matchs = matchRepository.selectTousMap();
		return filtrerMatchsAVenir(matchs);
	}

	private Map<String, Match> filtrerMatchsAVenir(Map<String, Match> matchs) {
		Map<String, Match> matchsAVenir = new HashMap<>();

		for (Map.Entry<String, Match> entry : matchs.entrySet()) {
			Match match = entry.getValue();

			if (match.estAVenir())
				matchsAVenir.put(match.getMatchId(), match);
		}

		return matchsAVenir;
	}

	public Map<Integer, Match> selectTousAyantIDs(Set<String> matchIDs) {
		Map<String, Match> tousMatchs = getMapMatchsAVenir();
		Set<String> tousMatchsIDs = tousMatchs.keySet();

		Map<Integer, Match> ayantMatchID = new HashMap<>();

		for (String id : matchIDs) {
			if (tousMatchsIDs.contains(id))
				ayantMatchID.put(Integer.parseInt(id), tousMatchs.get(id));
		}
		return ayantMatchID;
	}

	public List<String> sectionsDuCentreSportifLieAUnMatch(String match_id) throws EntiteNonTrouvee {
		Match match = matchRepository.selectParIdentifiantUnique(match_id);
		CentreSportif centreSportif = centreSportifRepository.selectParIdentifiantUnique(match.getNomCentreSportif());
		return centreSportif.getSections();
	}

	public Map<String, Match> getTousMatchsReliesAuxBillets(List<BilletUnMatch> billets) {
		Map<String, Match> matchs = matchRepository.selectTousMap();
		Map<String, Match> matchsRelies = new HashMap<>();

		for (BilletUnMatch billet : billets) {
			String matchID = billet.getMatch_id();
			if (matchs.containsKey(matchID))
				matchsRelies.put(matchID, matchs.get(matchID));
		}

		return matchsRelies;
	}

	public void setCentreSportifRepository(CentreSportifRepository centreSportifRepository) {
		this.centreSportifRepository = centreSportifRepository;
	}

	public void setGestionIDRepository(GestionIDRepository gestionIDRepository) {
		this.gestionIDRepository = gestionIDRepository;
	}

	public void setMatchRepository(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}
}
