package ca.ulaval.ift6003.domaine.modele.inventaire;

import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.Entree;
import ca.ulaval.ift6003.domaine.shared.Specification;
import ca.ulaval.ift6003.domaine.shared.ValueObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventaire<T extends Entree> implements ValueObject<Inventaire<T>> {

	List<T> entrees;

	public Inventaire() {
		entrees = new ArrayList<>();
	}

	public Inventaire(List<T> entrees) {
		this.entrees = entrees;
	}

	public void ajouter(T entree) {
		entrees.add(entree);
	}

	public List<T> getEntrees() {
		return entrees;
	}

	public void setEntrees(List<T> entrees) {
		this.entrees = entrees;
	}

	public List<T> filtrerEntrees(Specification<T> specification) {
		List<T> bonnesEntrees = new ArrayList<>();

		for (T entree : entrees) {
			if (specification.estSatisfaitePar(entree)) {
				bonnesEntrees.add(entree);
			}
		}

		return bonnesEntrees;
	}

	public Map<String, Integer> nombreEntreesParType() {
		Map<String, Integer> nbParType = new HashMap<>();

		for (Entree entree : entrees) {
			String type = entree.getType();
			if (!nbParType.containsKey(type)) {
				nbParType.put(type, 1);
			} else {
				nbParType.put(type, nbParType.get(type) + 1);
			}
		}

		return nbParType;
	}

	public int nombreEntreesDeType(String type) {
		int nombre = 0;

		for (Entree entree : entrees)
			if (type.equals(entree.getType()))
				nombre++;

		return nombre;
	}

	public List<T> entreesDistinctes() {
		Map<String, T> mapTypesEntrees = new HashMap<>();

		for (T entree : entrees) {
			String type = entree.getType();
			if (!mapTypesEntrees.containsKey(type)) { // si jamais rencontré
				mapTypesEntrees.put(type, entree); // on l'ajoute
			}
		}
		// on retourne les entrées distinctes
		return new ArrayList<>(mapTypesEntrees.values());
	}

	public int size() {
		return entrees.size();
	}

	@Override
	public boolean memeValeurQue(Inventaire<T> autre) {
		// compared les 2 listes d'entrées
		List<T> autresEntrees = autre.getEntrees();

		if (entrees.size() != autresEntrees.size()) {
			return false;
		}

		for (int i = 0; i < entrees.size(); i++) {
			if (!entrees.get(i).memeValeurQue(autresEntrees.get(i))) {
				return false;
			}
		}
		return true; // if all is good, they are the same value
	}

}
