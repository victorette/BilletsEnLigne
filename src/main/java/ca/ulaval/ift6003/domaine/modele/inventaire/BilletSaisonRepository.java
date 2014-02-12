package ca.ulaval.ift6003.domaine.modele.inventaire;

import java.util.List;

import ca.ulaval.ift6003.domaine.shared.Repository;

public interface BilletSaisonRepository extends Repository<BilletSaison> {

	void insererTous(List<BilletSaison> tousBillets);

}