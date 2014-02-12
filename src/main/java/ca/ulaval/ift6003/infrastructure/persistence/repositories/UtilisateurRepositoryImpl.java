package ca.ulaval.ift6003.infrastructure.persistence.repositories;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.domaine.modele.utilisateur.UtilisateurRepository;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean(name = "utilisateurRepository", eager = true)
public class UtilisateurRepositoryImpl extends RepositoryImpl<Utilisateur> implements UtilisateurRepository {

	private static final long serialVersionUID = 1L;

	public UtilisateurRepositoryImpl() {
		NOM_FICHIER = new java.io.File("").getAbsolutePath() + Consts.FICHIER_UTILISATEURS;
		convertisseur = new Convertisseur<>(Utilisateur.class);
	}

	public Utilisateur selectParCredentiels(String nomUtilisateur, String motDePasse) throws EntiteNonTrouvee {
		Utilisateur utilisateur = selectParIdentifiantUnique(nomUtilisateur);
		if (!utilisateur.getMotDePasse().equals(motDePasse)) {
			throw new EntiteNonTrouvee(nomUtilisateur);
		}
		return utilisateur;
	}

}
