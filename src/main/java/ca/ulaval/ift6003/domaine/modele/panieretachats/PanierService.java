package ca.ulaval.ift6003.domaine.modele.panieretachats;

import ca.ulaval.ift6003.domaine.modele.exceptions.QuantiteInsuffisante;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;

import java.io.Serializable;
import java.util.List;

public interface PanierService extends Serializable {

	void ajouterBilletsUnMatchDansPanier(Panier panier, List<BilletUnMatch> billetsDuBonType, int quantiteDesiree) throws QuantiteInsuffisante;

	void ajouterBilletsSaisonDansPanier(Panier panier, List<BilletSaison> billetsDuBonType, int quantiteDesiree) throws QuantiteInsuffisante;
}
