package ca.ulaval.ift6003.domaine.modele.inventaire;

import java.io.Serializable;
import java.util.Date;

import ca.ulaval.ift6003.domaine.shared.Entity;
import ca.ulaval.ift6003.infrastructure.utils.DateUtility;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("match")
public class Match implements Entity<Match>, Serializable {

	private static final long serialVersionUID = 1L;

	private String matchId;
	private String sportType;
	private String sportSexe;
	private Date date;
	private String nomCentreSportif;
	private String equipeMaison;
	private String equipeVisiteur;

	public Match() {
		this.matchId = "";
		this.sportType = "";
		this.sportSexe = "";
		this.date = null;
		this.nomCentreSportif = "";
		this.equipeMaison = "";
		this.equipeVisiteur = "";
	}

	public Match(String matchId, String sportType, String sportSexe, Date date, String nomCentreSportif, String equipeMaison, String equipeVisiteur) {
		this.matchId = matchId;
		this.sportType = sportType;
		this.sportSexe = sportSexe;
		this.date = date;
		this.nomCentreSportif = nomCentreSportif;
		this.equipeMaison = equipeMaison;
		this.equipeVisiteur = equipeVisiteur;
	}

	public String getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = matchId;
	}

	public String getNomCentreSportif() {
		return nomCentreSportif;
	}

	public void setNomCentreSportif(String nomCentreSportif) {
		this.nomCentreSportif = nomCentreSportif;
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

	public String getEquipeMaison() {
		return equipeMaison;
	}

	public void setEquipeMaison(String equipeMaison) {
		this.equipeMaison = equipeMaison;
	}

	public String getEquipeVisiteur() {
		return equipeVisiteur;
	}

	public void setEquipeVisiteur(String equipeVisiteur) {
		this.equipeVisiteur = equipeVisiteur;
	}

	public boolean estAVenir() {
		return DateUtility.dateEstAVenir(date);
	}

	@Override
	public boolean memeEntiteQue(Match other) {
		return this.getMatchId().equals(other.getMatchId());
	}

	@Override
	public String identifiantUnique() {
		return matchId;
	}

}
