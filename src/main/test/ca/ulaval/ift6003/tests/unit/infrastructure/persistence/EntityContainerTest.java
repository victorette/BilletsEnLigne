package ca.ulaval.ift6003.tests.unit.infrastructure.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.infrastructure.persistence.EntityContainer;

public class EntityContainerTest {

	EntityContainer<Utilisateur> entityContainer ;
	List<Utilisateur> liste;
	
	@Before
	public void setUp() {
		liste = new ArrayList<>();
	}

	@Test
	public void siAucuneListeFournieAlorsGetEntitiesRetourneNonNull() {
		entityContainer = new EntityContainer<>();

		liste = entityContainer.getEntites();
		
		assertNotNull(liste);
	}

	@Test
	public void siUneListeEstFournieAlorsLaValeurRetourneeEstValide() {
		liste.add(new Utilisateur());
		entityContainer = new EntityContainer<>(liste);
		
		assertEquals(entityContainer.getEntites(), liste);
	}
}
