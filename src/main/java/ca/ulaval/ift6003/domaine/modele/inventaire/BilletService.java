package ca.ulaval.ift6003.domaine.modele.inventaire;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ulaval.ift6003.domaine.modele.exceptions.QuantiteInsuffisante;

public interface BilletService extends Serializable {

	void ajouterNouveauBilletUnMatch(BilletUnMatch billet);

	void ajouterNouveauBilletSaison(BilletSaison billet);

	Map<String, Integer> compterNombreBilletsPourChaqueMatchID(Set<String> integers);

	Map<String, Integer> quantiteDispoParTypeBillet();

	List<BilletUnMatch> getBilletsUnMatchSelonType(String type) throws QuantiteInsuffisante;

	List<BilletSaison> getBilletsSaisonSelonType(String type) throws QuantiteInsuffisante;

	int getNombreMaximumDisponiblePourTypeBillet(String type);

	List<BilletUnMatch> retirerBilletsUnMatchDeLInventaire(Map<String, Integer> nbDesiresParTypes);

	List<BilletSaison> retirerBilletsSaisonDeLInventaire(Map<String, Integer> nbDesiresParTypes);

	Map<String, Integer> billetsToujoursDisponibles(List<String> types);
}
