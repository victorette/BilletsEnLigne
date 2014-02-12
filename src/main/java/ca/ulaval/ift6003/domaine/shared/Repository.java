package ca.ulaval.ift6003.domaine.shared;

import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Repository<T extends Entity<T>> extends Serializable {

    List<T> selectTous();

    Map<String, T> selectTousMap();

    T selectParIdentifiantUnique(String identifiant) throws EntiteNonTrouvee;

    void inserer(T entite);

    void update(T entite) throws EntiteNonTrouvee;

    void supprimerTousAyantIndentifiant(Set<String> identifiants);

    void supprimerParIdentifiantUnique(String identifiant) throws EntiteNonTrouvee;

}