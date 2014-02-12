package ca.ulaval.ift6003.domaine.modele.panieretachats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.ift6003.domaine.modele.inventaire.Billet;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.shared.ValueObject;

public class Panier implements ValueObject<Panier> {

	private Map<String, BilletUnMatch> billetsUnMatch;
	private Map<String, BilletSaison> billetsSaison;

	public Panier(Map<String, BilletUnMatch> billetsUnMatch, Map<String, BilletSaison> billetsSaison) {
		this.billetsUnMatch = billetsUnMatch;
		this.billetsSaison = billetsSaison;
	}

	public Panier() {
		billetsUnMatch = new HashMap<>();
		billetsSaison = new HashMap<>();
	}

	public void vider() {
		viderBilletsUnMatch();
		viderBilletsSaison();
	}

	public void viderBilletsUnMatch() {
		billetsUnMatch = new HashMap<>();
	}

	public void viderBilletsSaison() {
		billetsSaison = new HashMap<>();
	}

	public void ajouterBilletUnMatch(BilletUnMatch billet) {
		billetsUnMatch.put(billet.getBilletId(), billet);
	}

	public void ajouterBilletSaison(BilletSaison billet) {
		billetsSaison.put(billet.getBilletId(), billet);
	}

	public void ajouterBilletsUnMatch(List<BilletUnMatch> billets) {
		for (BilletUnMatch billet : billets) {
			ajouterBilletUnMatch(billet);
		}
	}

	public void ajouterBilletsSaison(List<BilletSaison> billets) {
		for (BilletSaison billet : billets) {
			ajouterBilletSaison(billet);
		}
	}

	public Map<String, BilletSaison> getBilletsSaison() {
		return billetsSaison;
	}

	public void setBilletsSaison(Map<String, BilletSaison> billetsSaison) {
		this.billetsSaison = billetsSaison;
	}

	public Map<String, BilletUnMatch> getBilletsUnMatch() {
		return billetsUnMatch;
	}

	public void setBilletsUnMatch(Map<String, BilletUnMatch> billetsUnMatch) {
		this.billetsUnMatch = billetsUnMatch;
	}

	public List<BilletUnMatch> getListeBilletsUnMatch() {
		List<BilletUnMatch> billets = new ArrayList<>();
		billets.addAll(billetsUnMatch.values());
		return billets;
	}

	public List<BilletSaison> getListeBilletsSaison() {
		List<BilletSaison> billets = new ArrayList<>();
		billets.addAll(billetsSaison.values());
		return billets;
	}

	public int nombreDeBilletsDeType(String type) {
		int nombre = 0;
		for (Billet billet : getTousBillets()) {
			if (billet.getType().equals(type)) {
				nombre++;
			}
		}
		return nombre;
	}

	public void retirerTousBilletsDeType(String type) {
		conserverSeulementQuantiteDeType(0, type);
	}

	public void diminuerQuantiteDeType(int quantiteAConserver, String type) {
		conserverSeulementQuantiteDeType(quantiteAConserver, type);
	}

	public double prixTotal() {
		double montantTotal = 0.00;
		for (Billet billet : getTousBillets()) {
			montantTotal += billet.getPrix();
		}
		return montantTotal;
	}

	public boolean contient(String billetId) {
		return billetsUnMatch.containsKey(billetId) || billetsSaison.containsKey(billetId);
	}

	public int nombreBilletsTotal() {
		return getTousBillets().size();
	}

	@Override
	public boolean memeValeurQue(Panier autre) {
		return toutesListesBilletsDesPaniersSontDeMemeTaille(autre) && toutesListesDesPaniersContiennentMemesBilletsIDs(autre);
	}

	private List<Billet> getTousBillets() {
		List<Billet> tousBillets = new ArrayList<>();
		tousBillets.addAll(getListeBilletsSaison());
		tousBillets.addAll(getListeBilletsUnMatch());
		return tousBillets;
	}

	private void conserverSeulementQuantiteDeType(int quantiteDuTypeAConserver, String type) {
		billetsUnMatch = filtrerBilletsSelonType(getListeBilletsUnMatch(), quantiteDuTypeAConserver, type);
		billetsSaison = filtrerBilletsSelonType(getListeBilletsSaison(), quantiteDuTypeAConserver, type);
	}

	private <T extends Billet> Map<String, T> filtrerBilletsSelonType(List<T> billets, int quantiteDuTypeAConserver, String type) {
		Map<String, T> aConserver = new HashMap<>();
		int quantiteDuType = 0;

		for (T billet : billets) {
			if (!billet.getType().equals(type)) { // d'un type différent, on
													// conserve
				aConserver.put(billet.getBilletId(), billet);
			} else if (quantiteDuType < quantiteDuTypeAConserver) { // du meme
																	// type. On
																	// conserve
																	// si limite
																	// non
																	// atteinte;
				aConserver.put(billet.getBilletId(), billet);
				quantiteDuType++;
			}
		}
		return aConserver;
	}

	private boolean toutesListesBilletsDesPaniersSontDeMemeTaille(Panier autre) {
		Map<String, BilletUnMatch> autreUnMatch = autre.getBilletsUnMatch();
		Map<String, BilletSaison> autreSaison = autre.getBilletsSaison();

		return billetsUnMatch.size() == autreUnMatch.size() && billetsSaison.size() == autreSaison.size();
	}

	private boolean toutesListesDesPaniersContiennentMemesBilletsIDs(Panier autre) {
		Map<String, BilletUnMatch> autreUnMatch = autre.getBilletsUnMatch();
		Map<String, BilletSaison> autreSaison = autre.getBilletsSaison();

		return billetsUnMatch.keySet().equals(autreUnMatch.keySet()) && billetsSaison.keySet().equals(autreSaison.keySet());
	}

}
