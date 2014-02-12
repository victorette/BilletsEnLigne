package ca.ulaval.ift6003.application;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIEntiteInexistante;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;

public interface InventaireApplication extends Serializable {

	List<EntreeUnMatchDTO> produireInventaireBilletsUnMatch();

	List<EntreeUnMatchDTO> produireInventaireBilletsUnMatchFiltre();

	List<EntreeSaisonDTO> produireInventaireBilletsSaison();

	List<String> getListeIdMatchs();

	List<String> getListeNomsCentresSportifs();

	List<BilletUnMatch> getListeBilletsUnMatch();

	List<BilletSaison> getListeBilletsSaison();

	List<CentreSportif> getListeCentresSportifs();

	List<Match> getListeMatchs();

	List<String> getSectionsLieesSelonMatchId(String match_id) throws UIEntiteInexistante;

	Map<String, Integer> nombreDeBilletsTotalParMatchsAVenir();

	List<String> getSectionsCentreSportif(String centreNom);

	int getNombreMaximumDisponiblePourTypeBillet(String type);

	int enregistrerTransaction(Map<String, Integer> nbDesiresParTypes);

	List<BilletUnMatch> getBilletsUnMatchEnReventeParLUtilisateur();

	List<BilletSaison> getBilletsSaisonEnReventeParLUtilisateur();

	void retirerBilletSaisonParDesReventesID(String billetID) throws UIEntiteInexistante;

	void retirerBilletUnMatchDesReventesParID(String billetID) throws UIEntiteInexistante;

	void modifierBilletsUnMatchEnRevente(List<BilletUnMatch> billetsUnMatch) throws UIEntiteInexistante;

	void modifierBilletsSaisonEnRevente(List<BilletSaison> billetsSaison) throws UIEntiteInexistante;

}
