package ca.ulaval.ift6003.interfaces.beans.Paiement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.PanierApplication;
import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;

@RequestScoped
@ManagedBean(name = "paiementPanierBean")
public class PaiementPanierBean extends PaiementBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{panierApplication}")
	protected PanierApplication panierApplication;

	@PostConstruct
	public void init() {
		this.utilisateurActif = utilisateurManagement.getUtilisateurActif();
	}

	@Override
	public double getPrixTotal() {
		return panierApplication.prixTotalDuPanier();
	}

	@Override
	public int getNombreArticles() {
		return panierApplication.nombreTotalDeBillets();
	}

	public void setPanierApplication(PanierApplication panierApplication) {
		this.panierApplication = panierApplication;
	}

	@Override
	public Map<String, Integer> getNombreDesiresParType() {
		// vérifier si le type de billet choisi (en tenant compte de la
		// quantité) est toujours disponible
		List<EntreeDTO> entrees = panierApplication.getToutesEntreesDuPanier();

		Map<String, Integer> typesEtQuantites = new HashMap<>();

		for (EntreeDTO entree : entrees) {
			typesEtQuantites.put(entree.getType(), entree.getNombreBilletsDesires());
		}

		return typesEtQuantites;
	}

}
