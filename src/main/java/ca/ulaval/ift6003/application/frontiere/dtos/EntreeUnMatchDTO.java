package ca.ulaval.ift6003.application.frontiere.dtos;

import java.util.Date;

public class EntreeUnMatchDTO extends EntreeDTO {

	public String sportType;
	public String sportSexe;
	public Date date;
	public String equipeVisiteur;
	public String matchID;

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

	public String getSportSexe() {
		return sportSexe;
	}

	public void setSportSexe(String sportSexe) {
		this.sportSexe = sportSexe;
	}

	public String getSportType() {
		return sportType;
	}

	public void setSportType(String sportType) {
		this.sportType = sportType;
	}
}
