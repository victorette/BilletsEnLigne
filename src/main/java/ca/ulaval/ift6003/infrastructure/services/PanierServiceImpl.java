package ca.ulaval.ift6003.infrastructure.services;

import ca.ulaval.ift6003.domaine.modele.exceptions.QuantiteInsuffisante;
import ca.ulaval.ift6003.domaine.modele.inventaire.Billet;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.panieretachats.Panier;
import ca.ulaval.ift6003.domaine.modele.panieretachats.PanierService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.util.ArrayList;
import java.util.List;

@SessionScoped
@ManagedBean(name = "panierService")
public class PanierServiceImpl implements PanierService {

	private static final long serialVersionUID = 1L;

	public void ajouterBilletsUnMatchDansPanier(Panier panier, List<BilletUnMatch> billetsDuBonType, int quantiteDesiree) throws QuantiteInsuffisante {
		List<BilletUnMatch> billetsPossibles = filtrerSelonDejaDansPanier(panier, billetsDuBonType);
		if (billetsPossibles.size() < quantiteDesiree) {
			throw new QuantiteInsuffisante();
		} else {
			panier.ajouterBilletsUnMatch(prendreBonNombreDeBillets(billetsPossibles, quantiteDesiree));
		}
	}

	public void ajouterBilletsSaisonDansPanier(Panier panier, List<BilletSaison> billetsDuBonType, int quantiteDesiree) throws QuantiteInsuffisante {
		List<BilletSaison> billetsPossibles = filtrerSelonDejaDansPanier(panier, billetsDuBonType);
		if (billetsPossibles.size() < quantiteDesiree) {
			throw new QuantiteInsuffisante();
		} else {
			panier.ajouterBilletsSaison(prendreBonNombreDeBillets(billetsPossibles, quantiteDesiree));
		}
	}

	private <T> List<T> prendreBonNombreDeBillets(List<T> billetsPossibles, int quantiteDesiree) {
		return billetsPossibles.subList(0, quantiteDesiree);
	}

	private <T extends Billet> List<T> filtrerSelonDejaDansPanier(Panier panier, List<T> billetsDuBonType) {
		List<T> aConserver = new ArrayList<>();
		for (T billet : billetsDuBonType) {
			if (!panier.contient(billet.getBilletId()))
				aConserver.add(billet);
		}
		return aConserver;
	}

}
