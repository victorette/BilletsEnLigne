package ca.ulaval.ift6003.domaine.modele.utilisateur;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Sports;
import ca.ulaval.ift6003.domaine.shared.ValueObject;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("preferencesBillets")
public class PreferencesBillets implements ValueObject<PreferencesBillets> {

	private List<String> sportsTypes;
	private int nombreJours;
	private String categorieSiege;
	private boolean localSeulement;

	public PreferencesBillets(List<String> sportsTypes, int nombreJours, boolean localSeulement, String categorieSiege) {
		this.sportsTypes = sportsTypes;
		this.nombreJours = nombreJours;
		this.localSeulement = localSeulement;
		this.categorieSiege = categorieSiege;
	}

	public PreferencesBillets() {
		this.sportsTypes = construireListeSports();
		this.nombreJours = 365;
		this.localSeulement = false;
		this.categorieSiege = "tous";
	}

	private List<String> construireListeSports() {
		List<String> sports = new ArrayList<>();
		sports.add(Sports.SOCCER);
		sports.add(Sports.FOOTBALL);
		sports.add(Sports.BASKETBALL);
		sports.add(Sports.RUGBY);
		sports.add(Sports.VOLLEYBALL);
		return sports;
	}

	public List<String> getSportsTypes() {
		return sportsTypes;
	}

	public void setSportsTypes(List<String> sportsTypes) {
		this.sportsTypes = sportsTypes;
	}

	public String getCategorieSiege() {
		return categorieSiege;
	}

	public void setCategorieSiege(String categorieSiege) {
		this.categorieSiege = categorieSiege;
	}

	public boolean isLocalSeulement() {
		return localSeulement;
	}

	public void setLocalSeulement(boolean localSeulement) {
		this.localSeulement = localSeulement;
	}

	@Override
	public boolean memeValeurQue(PreferencesBillets autre) {
		return getValeur().equals(autre.getValeur());
	}

	public String getValeur() {
		String sports = "";
		for (String sport : sportsTypes) {
			sports += sport;
		}
		return Integer.toString(nombreJours) + categorieSiege + Boolean.toString(localSeulement) + sports;
	}

	public int getNombreJours() {
		return nombreJours;
	}

	public void setNombreJours(int nombreJours) {
		this.nombreJours = nombreJours;
	}
}
