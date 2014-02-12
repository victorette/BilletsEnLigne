package ca.ulaval.ift6003.infrastructure.persistence.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedProperty;

import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.shared.Entity;
import ca.ulaval.ift6003.domaine.shared.Repository;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;
import ca.ulaval.ift6003.infrastructure.persistence.XMLReaderWriter;

public abstract class RepositoryImpl<T extends Entity<T>> implements Repository<T> {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{xmlReaderWriter}")
	protected XMLReaderWriter xmlReaderWriter;
	protected Convertisseur<T> convertisseur;

	protected String NOM_FICHIER;

	@Override
	public List<T> selectTous() {
		String xml = xmlReaderWriter.lireFichierXML(NOM_FICHIER);
		return convertisseur.XMLToListe(xml);
	}

	@Override
	public Map<String, T> selectTousMap() {
		// placer les entités dans un map où le l'identifiant unique est la clé
		// (accélérer l'accès)
		Map<String, T> map = new HashMap<>();

		for (T entite : selectTous()) {
			map.put(entite.identifiantUnique(), entite);
		}
		return map;
	}

	@Override
	public T selectParIdentifiantUnique(String identifiant) throws EntiteNonTrouvee {
		List<T> entites = selectTous();
		for (T entite : entites) {
			if (entite.identifiantUnique().equals(identifiant)) {
				return entite;
			}
		}
		throw new EntiteNonTrouvee(identifiant);
	}

	@Override
	public void inserer(T entite) {
		List<T> entites = selectTous();
		entites.add(entite);
		ecrireDansFichier(entites);
	}

	@Override
	public void update(T entite) throws EntiteNonTrouvee {
		List<T> entites = selectTous();
		entites = modifierParmisListe(entites, entite);
		ecrireDansFichier(entites);
	}

	@Override
	public void supprimerTousAyantIndentifiant(Set<String> identifiants) {
		List<T> entites = selectTous();
		entites = supprimerParmisListeParIdentifiants(entites, identifiants);
		ecrireDansFichier(entites);
	}

	@Override
	public void supprimerParIdentifiantUnique(String identifiant) throws EntiteNonTrouvee {
		List<T> entites = selectTous();
		entites = supprimerParmisListeParIdentifiant(entites, identifiant);
		ecrireDansFichier(entites);
	}

	private List<T> supprimerParmisListeParIdentifiant(List<T> entites, String identifiant) throws EntiteNonTrouvee {
		Integer index = indexParmisListe(entites, identifiant);
		entites.remove((int) index);
		return entites;
	}

	private List<T> modifierParmisListe(List<T> entites, T entite) throws EntiteNonTrouvee {
		Integer index = indexParmisListe(entites, entite.identifiantUnique());
		entites.set(index, entite);
		return entites;
	}

	private Integer indexParmisListe(List<T> entites, String identifiant) throws EntiteNonTrouvee {
		T entite;

		for (int i = 0; i < entites.size(); i++) {
			entite = entites.get(i);
			if (entite.identifiantUnique().equals(identifiant)) {
				return i; // on retourne l'index de l'objet
			}
		}
		throw new EntiteNonTrouvee(identifiant);
	}

	protected void ecrireDansFichier(List<T> entites) {
		String xml = convertisseur.listeToXML(entites);
		xmlReaderWriter.ecrireDansFichierXML(xml, NOM_FICHIER);
	}

	private List<T> supprimerParmisListeParIdentifiants(List<T> entites, Set<String> identifiantsASupprimer) {
		List<T> aConserver = new ArrayList<>();

		for (T entite : entites) {
			if (!identifiantsASupprimer.contains(entite.identifiantUnique())) {
				aConserver.add(entite);
			}
		}
		return aConserver;
	}

	public XMLReaderWriter getXmlReaderWriter() {
		return xmlReaderWriter;
	}

	public void setXmlReaderWriter(XMLReaderWriter xmlReaderWriter) {
		this.xmlReaderWriter = xmlReaderWriter;
	}

	public Convertisseur<T> getConvertisseur() {
		return convertisseur;
	}

	public void setConvertisseur(Convertisseur<T> convertisseur) {
		this.convertisseur = convertisseur;
	}
}
