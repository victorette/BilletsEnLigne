package ca.ulaval.ift6003.infrastructure.persistence.repositories;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.domaine.modele.inventaire.MatchRepository;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean(name = "matchRepository", eager = true)
public class MatchRepositoryImpl extends RepositoryImpl<Match> implements MatchRepository {

	private static final long serialVersionUID = 1L;

	public MatchRepositoryImpl() {
		NOM_FICHIER = new java.io.File("").getAbsolutePath() + Consts.FICHIER_MATCHS;
		convertisseur = new Convertisseur<>(Match.class);
	}

}
