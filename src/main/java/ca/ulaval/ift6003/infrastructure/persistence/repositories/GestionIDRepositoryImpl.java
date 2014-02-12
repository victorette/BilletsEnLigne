package ca.ulaval.ift6003.infrastructure.persistence.repositories;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.gestionIDs.GestionIDRepository;
import ca.ulaval.ift6003.domaine.modele.gestionIDs.MapNextID;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;
import ca.ulaval.ift6003.infrastructure.persistence.XMLReaderWriter;

@ApplicationScoped
// session ?
@ManagedBean(name = "gestionIDRepository", eager = true)
public class GestionIDRepositoryImpl implements GestionIDRepository {

	private final String NOM_FICHIER = new java.io.File("").getAbsolutePath() + Consts.FICHIER_MAPNEXTID;

	@ManagedProperty(value = "#{xmlReaderWriter}")
	private XMLReaderWriter xmlReaderWriter;
	private Convertisseur<MapNextID> convertisseur;

	public GestionIDRepositoryImpl() {
		convertisseur = new Convertisseur<>(MapNextID.class);
	}

	public MapNextID selectTous() {
		String xml = xmlReaderWriter.lireFichierXML(NOM_FICHIER);
		return convertisseur.XMLToListe(xml).get(0);
	}

	public int selectEtUpdateNextId(String nomEntite) {
		MapNextID mapNextIds = selectTous();
		int nextIdPourEntite = mapNextIds.getNextIdPourEntite(nomEntite);

		mapNextIds.setNextIdPourEntite(nomEntite, nextIdPourEntite + 1);
		ecrireMapNextID(mapNextIds);

		return nextIdPourEntite;
	}

	private void ecrireMapNextID(MapNextID mapNextIds) {
		List<MapNextID> liste = Arrays.asList(mapNextIds);
		String xml = convertisseur.listeToXML(liste);
		xmlReaderWriter.ecrireDansFichierXML(xml, NOM_FICHIER);
	}

	public void setXmlReaderWriter(XMLReaderWriter xmlReaderWriter) {
		this.xmlReaderWriter = xmlReaderWriter;
	}

	public void setConvertisseur(Convertisseur<MapNextID> convertisseur) {
		this.convertisseur = convertisseur;
	}

}
