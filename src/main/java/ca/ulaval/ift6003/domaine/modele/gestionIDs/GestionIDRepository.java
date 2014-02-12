package ca.ulaval.ift6003.domaine.modele.gestionIDs;


public interface GestionIDRepository {

	public MapNextID selectTous();

	public int selectEtUpdateNextId(String identifiant);

}