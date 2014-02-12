package ca.ulaval.ift6003.infrastructure.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.exceptions.QuantiteInsuffisante;
import ca.ulaval.ift6003.domaine.modele.gestionIDs.GestionIDRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.Billet;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaisonRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletService;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatchRepository;

@SessionScoped
@ManagedBean(name = "billetService")
public class BilletServiceImpl implements BilletService {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{billetUnMatchRepository}")
	private BilletUnMatchRepository billetUnMatchRepository;
	@ManagedProperty(value = "#{billetSaisonRepository}")
	private BilletSaisonRepository billetSaisonRepository;
	@ManagedProperty(value = "#{gestionIDRepository}")
	private GestionIDRepository gestionIDRepository;

	@Override
	public void ajouterNouveauBilletUnMatch(BilletUnMatch billet) {
		updateBilletID(billet);
		updateBilletSection(billet);
		billetUnMatchRepository.inserer(billet);
	}

	@Override
	public void ajouterNouveauBilletSaison(BilletSaison billet) {
		updateBilletID(billet);
		updateBilletSection(billet);
		billetSaisonRepository.inserer(billet);
	}

	@Override
	public Map<String, Integer> compterNombreBilletsPourChaqueMatchID(Set<String> matchIDs) {
		Map<String, Integer> nbParMatch = new HashMap<>();
		// on s'assure qu'il y a une entrée par match, puisque sinon les matchs
		// n'ayant pas de billets n'apparaitrons pas dans la liste.
		for (String matchID : matchIDs) {
			nbParMatch.put(matchID, 0);
		}

		for (BilletUnMatch billet : billetUnMatchRepository.selectTousAyantMatchIDs(matchIDs)) {
			String matchId = billet.getMatch_id();
			int nbActuel = nbParMatch.get(matchId);
			nbParMatch.put(matchId, nbActuel + 1);
		}
		return nbParMatch;
	}

	@Override
	public Map<String, Integer> quantiteDispoParTypeBillet() {
		List<Billet> tousBillets = getTousBilletsDeTousTypes();
		Map<String, Integer> nombreParType = new HashMap<>();

		for (Billet billet : tousBillets) {
			String btype = billet.getType();
			if (!nombreParType.containsKey(btype)) {
				nombreParType.put(btype, 0);
			}
			nombreParType.put(btype, nombreParType.get(btype) + 1);
		}

		return nombreParType;
	}

	@Override
	public List<BilletUnMatch> getBilletsUnMatchSelonType(String type) throws QuantiteInsuffisante {
		return filtrerBilletsSelonType(type, billetUnMatchRepository.selectTous());
	}

	@Override
	public List<BilletSaison> getBilletsSaisonSelonType(String type) throws QuantiteInsuffisante {
		return filtrerBilletsSelonType(type, billetSaisonRepository.selectTous());
	}

	@Override
	public int getNombreMaximumDisponiblePourTypeBillet(String type) {
		List<Billet> tousBillets = getTousBilletsDeTousTypes();

		int nombre = 0;
		for (Billet billet : tousBillets) {
			if (billet.getType().equals(type)) {
				nombre++;
			}
		}
		return nombre;
	}

	private <T extends Billet> List<T> filtrerBilletsSelonType(String type, List<T> tousBillets) {
		List<T> billetsSelonType = new ArrayList<>();
		for (T billet : tousBillets) {
			if (billet.getType().equals(type)) {
				billetsSelonType.add(billet);
			}
		}
		return billetsSelonType;
	}

	private List<Billet> getTousBilletsDeTousTypes() {
		List<Billet> tousBillets = new ArrayList<>();
		tousBillets.addAll(billetSaisonRepository.selectTous());
		tousBillets.addAll(billetUnMatchRepository.selectTous());
		return tousBillets;
	}

	private void updateBilletID(Billet billet) {
		int nextId = gestionIDRepository.selectEtUpdateNextId(Consts.NOM_ENTITE_BILLET);
		billet.setBilletId(Integer.toString(nextId));
	}

	private void updateBilletSection(Billet billet) {
		if (billet.getCategorie_siege().equals(Consts.SIEGE_GENERAL)) {
			billet.setNomSection("---");
		}
	}

	public void setBilletSaisonRepository(BilletSaisonRepository billetSaisonRepository) {
		this.billetSaisonRepository = billetSaisonRepository;
	}

	public void setBilletUnMatchRepository(BilletUnMatchRepository billetUnMatchRepository) {
		this.billetUnMatchRepository = billetUnMatchRepository;
	}

	public void setGestionIDRepository(GestionIDRepository gestionIDRepository) {
		this.gestionIDRepository = gestionIDRepository;
	}

	@Override
	public Map<String, Integer> billetsToujoursDisponibles(List<String> types) {
		Map<String, Integer> nombreParType = new HashMap<>();

		for (String type : types) {
			nombreParType.put(type, getNombreMaximumDisponiblePourTypeBillet(type));
		}
		return nombreParType;
	}

	@Override
	public List<BilletUnMatch> retirerBilletsUnMatchDeLInventaire(Map<String, Integer> nbDesiresParTypes) {
		List<BilletUnMatch> tousBillets = billetUnMatchRepository.selectTous();

		List<BilletUnMatch> billetsRetires = retirerBilletsDeLInventaire(nbDesiresParTypes, tousBillets);
		billetUnMatchRepository.insererTous(tousBillets);

		return billetsRetires;
	}

	@Override
	public List<BilletSaison> retirerBilletsSaisonDeLInventaire(Map<String, Integer> nbDesiresParTypes) {
		List<BilletSaison> tousBillets = billetSaisonRepository.selectTous();

		List<BilletSaison> billetsRetires = retirerBilletsDeLInventaire(nbDesiresParTypes, tousBillets);
		billetSaisonRepository.insererTous(tousBillets);

		return billetsRetires;
	}

	private synchronized <T extends Billet> List<T> retirerBilletsDeLInventaire(Map<String, Integer> nbDesiresParTypes, List<T> tousBillets) {
		List<T> billetsRetires = new ArrayList<>();
		Map<String, Integer> nbRetiresParType = new HashMap<>();

		Iterator<T> it = tousBillets.iterator();

		while (it.hasNext()) {
			T billet = it.next();
			String type = billet.getType();
			// si le type est un type désiré
			if (nbDesiresParTypes.containsKey(type)) {
				int nombreDejaRetire = nbRetiresParType.containsKey(type) ? nbRetiresParType.get(type) : 0;
				int nombreDesire = nbDesiresParTypes.get(type);
				// si on doit encore en retirer de ce type,
				if (nombreDejaRetire < nombreDesire) {
					// incrémenter le nombre retiré de ce type
					nbRetiresParType.put(type, nombreDejaRetire + 1);
					// conserver le billet dans une autre liste
					billetsRetires.add(billet);
					// retirer le billet de la liste orginale
					it.remove();
				}
			}
		}
		return billetsRetires;
	}

}
