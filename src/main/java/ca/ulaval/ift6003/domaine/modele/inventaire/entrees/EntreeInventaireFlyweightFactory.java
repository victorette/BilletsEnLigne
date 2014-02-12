package ca.ulaval.ift6003.domaine.modele.inventaire.entrees;

import java.util.HashMap;
import java.util.Map;

import ca.ulaval.ift6003.domaine.modele.inventaire.Billet;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;

// singleton + flyweight + factory
public class EntreeInventaireFlyweightFactory {

	private static EntreeInventaireFlyweightFactory instance = null;
	private static Map<String, Entree> flyweights = new HashMap<>();

	public static synchronized EntreeInventaireFlyweightFactory getInstance() {
		if (instance == null) {
			instance = new EntreeInventaireFlyweightFactory();
		}
		return instance;
	}

	private EntreeInventaireFlyweightFactory() {

	}

	public static synchronized EntreeUnMatch get(BilletUnMatch billet, Match match, CentreSportif centre) {
		String hashkey = computeHashKey(billet, centre);

		EntreeUnMatch entree = (EntreeUnMatch) flyweights.get(hashkey);
		if (entree == null) {
			entree = creerEntreeUnMatch(billet, match, centre);
			flyweights.put(hashkey, entree);
		}
		return entree;
	}

	public static synchronized EntreeSaison get(BilletSaison billet, CentreSportif centre) {
		String hashkey = computeHashKey(billet, centre);

		EntreeSaison entree = (EntreeSaison) flyweights.get(hashkey);
		if (entree == null) {
			entree = creerEntreeSaison(billet, centre);
			flyweights.put(hashkey, entree);
		}
		return entree;
	}

	private static EntreeUnMatch creerEntreeUnMatch(BilletUnMatch billet, Match match, CentreSportif centre) {
		EntreeUnMatch entree = new EntreeUnMatch();
		entree = (EntreeUnMatch) ajouterCommun(entree, billet, centre);

		entree.setSportType(match.getSportType());
		entree.setSportSexe(match.getSportSexe());
		entree.setDate(match.getDate());
		entree.setEquipeVisiteur(match.getEquipeVisiteur());
		entree.setMatchID(match.getMatchId());

		return entree;
	}

	private static EntreeSaison creerEntreeSaison(BilletSaison billet, CentreSportif centre) {
		EntreeSaison entree = new EntreeSaison();
		entree = (EntreeSaison) ajouterCommun(entree, billet, centre);

		entree.setAnnee(billet.getAnnee());

		return entree;
	}

	private static Entree ajouterCommun(Entree entree, Billet billet, CentreSportif centre) {
		entree.setBilletID(billet.getBilletId());
		entree.setNomCentreSportif(centre.getNom());
		entree.setVille(centre.getVille());
		entree.setNomSection(billet.getNomSection());
		entree.setCategorieSiege(billet.getCategorie_siege());
		entree.setPrix(billet.getPrix());
		entree.setType(billet.getType());

		return entree;
	}

	public static String computeHashKey(Billet billet, CentreSportif centre) {
		return centre.toString() + billet.toString();
	}

}
