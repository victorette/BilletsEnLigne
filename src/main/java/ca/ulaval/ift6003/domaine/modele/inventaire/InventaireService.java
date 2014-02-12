package ca.ulaval.ift6003.domaine.modele.inventaire;

import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;

import java.io.Serializable;
import java.util.List;

public interface InventaireService extends Serializable {

	Inventaire<EntreeUnMatch> inventaireBilletsMatchsFutur();

	Inventaire<EntreeSaison> inventaireBilletsSaison();

	Inventaire<EntreeUnMatch> inventaireBilletsMatchsFutursFiltre(PreferencesBillets preferences);

	Inventaire<EntreeUnMatch> inventaireUnMatchPourBilletsDonnes(List<BilletUnMatch> billets);

	Inventaire<EntreeSaison> inventaireSaisonPourBilletsDonnes(List<BilletSaison> listeBilletsUnMatch);
}
