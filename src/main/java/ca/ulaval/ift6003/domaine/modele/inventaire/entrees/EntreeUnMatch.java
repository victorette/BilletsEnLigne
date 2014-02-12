package ca.ulaval.ift6003.domaine.modele.inventaire.entrees;

import java.util.Date;

public class EntreeUnMatch extends Entree {

	private String sportType;
	private String sportSexe;
	private Date date;
	private String equipeVisiteur;
	private String matchID;

	public EntreeUnMatch() {

	}

	public String getSportType() {
		return sportType;
	}

	public void setSportType(String sportType) {
		this.sportType = sportType;
	}

	public String getSportSexe() {
		return sportSexe;
	}

	public void setSportSexe(String sportSexe) {
		this.sportSexe = sportSexe;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEquipeVisiteur() {
		return equipeVisiteur;
	}

	public void setEquipeVisiteur(String equipeVisiteur) {
		this.equipeVisiteur = equipeVisiteur;
	}

	public String getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
		this.matchID = matchID;
	}

	@Override
	public String getValeur() {
		return nomCentreSportif + ville + nomSection + categorieSiege + Double.toString(prix) + type + sportType + sportSexe + date.toString() + equipeVisiteur + matchID;
	}

	@Override
	public boolean memeValeurQue(Entree autre) {
		return getValeur().equals(autre.getValeur());
	}
}
