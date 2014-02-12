package ca.ulaval.ift6003.domaine.modele.utilisateur;

import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.shared.Repository;

public interface UtilisateurRepository extends Repository<Utilisateur> {

	Utilisateur selectParCredentiels(String nomUtilisateur, String motDePasse) throws EntiteNonTrouvee;

}