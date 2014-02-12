package ca.ulaval.ift6003.infrastructure.persistence.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatchRepository;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;

@ApplicationScoped
@ManagedBean(name = "billetUnMatchRepository", eager = true)
public class BilletUnMatchRepositoryImpl extends RepositoryImpl<BilletUnMatch> implements BilletUnMatchRepository {

	private static final long serialVersionUID = 1L;

	public BilletUnMatchRepositoryImpl() {
		NOM_FICHIER = new java.io.File("").getAbsolutePath() + Consts.FICHIER_BILLETS_UNMATCH;
		convertisseur = new Convertisseur<>(BilletUnMatch.class);
	}

	@Override
	public void supprimerTousAyantMatchID(String matchId) {
		List<BilletUnMatch> billets = selectTous();
		billets = supprimerBilletsParmisListeAyantMatchId(billets, matchId);

		String xml = convertisseur.listeToXML(billets);
		xmlReaderWriter.ecrireDansFichierXML(xml, NOM_FICHIER);
	}

	@Override
	public List<BilletUnMatch> selectTousAyantMatchIDs(Set<String> matchIDs) {
		List<BilletUnMatch> tousBillets = selectTous();

		List<BilletUnMatch> billetsLiesAuxMatchs = new ArrayList<>();
		for (BilletUnMatch billet : tousBillets) {
			if (matchIDs.contains(billet.getMatch_id())) {
				billetsLiesAuxMatchs.add(billet);
			}
		}
		return billetsLiesAuxMatchs;
	}

	private List<BilletUnMatch> supprimerBilletsParmisListeAyantMatchId(List<BilletUnMatch> billets, String matchId) {
		List<BilletUnMatch> nouvelleListe = new ArrayList<>();

		for (BilletUnMatch billet : billets) {
			if (!billet.getMatch_id().equals(matchId)) {
				nouvelleListe.add(billet);
			}
		}
		return nouvelleListe;
	}

	@Override
	public void insererTous(List<BilletUnMatch> tousBillets) {
		ecrireDansFichier(tousBillets);
	}

}
