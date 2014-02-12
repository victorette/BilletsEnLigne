package ca.ulaval.ift6003.domaine.modele.inventaire;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ca.ulaval.ift6003.domaine.shared.Entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("nomCentreSportif")
public class CentreSportif implements Entity<CentreSportif>, Serializable {

	private static final long serialVersionUID = 1L;
	private String nom;
	private String ville;
	private List<String> terrains;
	private List<String> sections;

	public CentreSportif() {
		this.nom = "";
		this.ville = "";
		this.terrains = new ArrayList<String>();
		this.sections = new ArrayList<String>();
		peuplerListeTerrains();
	}

	public CentreSportif(String nom, String ville, List<String> terrains, List<String> sections) {
		this.nom = nom;
		this.ville = ville;
		this.terrains = terrains;
		this.sections = sections;
	}

	private void peuplerListeTerrains() {
		this.terrains.add("terrain1");
		this.terrains.add("terrain2");
		this.terrains.add("terrain3");
		this.terrains.add("terrain4");
		this.terrains.add("terrain5");
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<String> getTerrains() {
		return terrains;
	}

	public void setTerrains(List<String> terrains) {
		this.terrains = terrains;
	}

	public List<String> getSections() {
		return sections;
	}

	public void setSections(List<String> sections) {
		this.sections = sections;
	}

	@Override
	public boolean memeEntiteQue(CentreSportif autre) {
		return nom.equals(autre.getNom());
	}

	@Override
	public String identifiantUnique() {
		return nom;
	}

}
