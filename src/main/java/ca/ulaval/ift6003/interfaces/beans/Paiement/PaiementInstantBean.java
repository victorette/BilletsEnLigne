package ca.ulaval.ift6003.interfaces.beans.Paiement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.application.frontiere.dtos.EntreeDTO;

@RequestScoped
@ManagedBean(name = "paiementInstantBean")
public class PaiementInstantBean extends PaiementBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntreeDTO entreeChoisie;

	public PaiementInstantBean() {
	};

	public PaiementInstantBean(EntreeDTO entreeDTO) {
		entreeChoisie = entreeDTO;
	}

	@PostConstruct
	public void init() {
		entreeChoisie = obtenirBilletSelectionne();
		this.utilisateurActif = utilisateurManagement.getUtilisateurActif();
	}

	@Override
	public double getPrixTotal() {
		return entreeChoisie.prix * getNombreArticles();
	}

	@Override
	public int getNombreArticles() {
		return entreeChoisie.nombreBilletsDesires;
	}

	private EntreeDTO obtenirBilletSelectionne() {
		EntreeDTO dto = (EntreeDTO) getSessionContext().get("entreeChoisie");
		return dto;
	}

	private void effacerEntreeChoisieDeLaSession() {
		getSessionContext().remove("entreeChoisie");
	}

	@Override
	public Map<String, Integer> getNombreDesiresParType() {
		// vérifier si le type de billet choisi (en tenant compte de la
		// quantité) est toujours disponible
		Map<String, Integer> typesEtQuantites = new HashMap<>();
		typesEtQuantites.put(entreeChoisie.type, entreeChoisie.nombreBilletsDesires);
		effacerEntreeChoisieDeLaSession();
		return typesEtQuantites;
	}

}
