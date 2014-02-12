package ca.ulaval.ift6003.domaine.modele.specifications;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;
import ca.ulaval.ift6003.domaine.shared.AbstractSpecification;
import ca.ulaval.ift6003.infrastructure.utils.DateUtility;

import java.util.Date;
import java.util.List;

public class PreferencesBilletsSpecification extends AbstractSpecification<EntreeUnMatch> {

	private PreferencesBillets preferences;

	public PreferencesBilletsSpecification(PreferencesBillets preferences) {
		this.preferences = preferences;
	}

	@Override
	public boolean estSatisfaitePar(EntreeUnMatch e) {
		boolean bonneCategorie = categorieSiegeSatisfaite(preferences.getCategorieSiege(), e);
		boolean bonTypesSports = sportsTypesSatisfaits(preferences.getSportsTypes(), e);
		boolean bonneDate = daterangeSatisfait(preferences.getNombreJours(), e);
		boolean bonLocal = localOuNonSatisfait(preferences.isLocalSeulement(), e);

		return bonneCategorie && bonTypesSports && bonneDate && bonLocal;
	}

	private boolean localOuNonSatisfait(boolean localSeulement, EntreeUnMatch e) {
		return !localSeulement || e.getNomCentreSportif().equals(Consts.CENTRE_SPORTIF_ULAVAL);
	}

	private boolean daterangeSatisfait(int nombreJours, EntreeUnMatch e) {
		Date aujourdhui = DateUtility.getCurrentDateTime();
		Date dateLimite = DateUtility.ajouterJours(aujourdhui, nombreJours);
		return e.getDate().after(aujourdhui) && e.getDate().before(dateLimite);
	}

	private boolean sportsTypesSatisfaits(List<String> sportsTypes, EntreeUnMatch e) {
		boolean OK = false;

		// si le sport de l'entrée est contenu dans les préférences, elle est
		// OK.
		for (String sportType : sportsTypes) {
			if (e.getSportType().equals(sportType)) {
				OK = true;
				break;
			}
		}
		return OK;
	}

	private boolean categorieSiegeSatisfaite(String categorieSiege, EntreeUnMatch e) {
		if (categorieSiege.equals("tous")) {
			return e.getCategorieSiege().equals(Consts.SIEGE_GENERAL) || e.getCategorieSiege().equals(Consts.SIEGE_RESERVE);
		} else {
			return e.getCategorieSiege().equals(categorieSiege);
		}
	}
}
