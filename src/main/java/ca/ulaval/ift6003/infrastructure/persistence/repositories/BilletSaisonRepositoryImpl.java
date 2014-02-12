package ca.ulaval.ift6003.infrastructure.persistence.repositories;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaisonRepository;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;

@ApplicationScoped
@ManagedBean(name = "billetSaisonRepository", eager = true)
public class BilletSaisonRepositoryImpl extends RepositoryImpl<BilletSaison> implements BilletSaisonRepository {

	private static final long serialVersionUID = 1L;

	public BilletSaisonRepositoryImpl() {
		NOM_FICHIER = new java.io.File("").getAbsolutePath() + Consts.FICHIER_BILLETS_SAISON;
		convertisseur = new Convertisseur<>(BilletSaison.class);
	}

	@Override
	public void insererTous(List<BilletSaison> tousBillets) {
		ecrireDansFichier(tousBillets);
	}

}
