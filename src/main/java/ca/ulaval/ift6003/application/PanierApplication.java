package ca.ulaval.ift6003.application;

import java.util.List;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeSaisonDTO;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeUnMatchDTO;
import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIQuantiteInsuffisante;

public interface PanierApplication {

	int nombreTotalDeBillets();

	List<EntreeUnMatchDTO> listeEntreesUnMatchDeMonPanier();

	List<EntreeSaisonDTO> listeEntreesSaisonDeMonPanier();

	int getNombreMaximumDisponiblePourTypeBillet(String type);

	void ajouterBilletsUnMatchAuPanier(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante;

	void ajouterBilletsSaisonAuPanier(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante;

	void changerQuantiteBilletSaisonDuPanier(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante;

	void changerQuantiteBilletUnMatchDuPanier(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante;

	void viderMonPanier();

	void retirerTousBilletsDeType(String type);

	double prixTotalDuPanier();

	void acheterInstantanement(EntreeDTO entreeChoisie) throws UIQuantiteInsuffisante;

	List<EntreeDTO> getToutesEntreesDuPanier();

}
