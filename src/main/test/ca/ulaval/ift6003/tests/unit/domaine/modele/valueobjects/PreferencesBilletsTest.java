package ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Sports;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PreferencesBilletsTest {

	private PreferencesBillets unePreferencesBillets;
	private PreferencesBillets unePreferencesBilletsMemeValeur;
	private PreferencesBillets unePreferencesBilletsDifferente;

	private final String CATEGORIE_SIEGE = "categoriesiege";
	private final boolean LOCAL_SEULEMENT = true;
	private final int NOMBRE_JOURS = 1;
	private final List<String> SPORTS_TYPES = new ArrayList<>();

	private final String CATEGORIE_SIEGE_DIFFERENTE = "categoriesiege";
	private final boolean LOCAL_SEULEMENT_DIFFERENT = true;
	private final int NOMBRE_JOURS_DIFFERENTS = 1;
	private final List<String> SPORTS_TYPES_DIFFERENTS = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		unePreferencesBillets = new PreferencesBillets();
		unePreferencesBilletsMemeValeur = new PreferencesBillets();
		unePreferencesBilletsDifferente = new PreferencesBillets();

		SPORTS_TYPES.add(Sports.SOCCER);
		SPORTS_TYPES.add(Sports.FOOTBALL);

		SPORTS_TYPES_DIFFERENTS.add(Sports.FOOTBALL);
		SPORTS_TYPES_DIFFERENTS.add(Sports.BASKETBALL);
	}

	@Test
	public void siLesDeuxPreferencesBilletsOntLaMemeValeurAlorsMemeValeurQueRetourneLesMemesValeurs() {
		creerUnePreferencesBillets();
		creerUnePreferencesBilletsMemeValeur();
		assertTrue(unePreferencesBillets.memeValeurQue(unePreferencesBilletsMemeValeur));

	}

	@Test
	public void siLesDeuxPreferencesBilletsNOntPasLaMemeValeurAlorsMemeValeurQueNeRetournePasLesMemesValeurs() {
		creerUnePreferencesBillets();
		creerUnePreferencesBilletsDifferent();

		assertFalse(unePreferencesBillets.memeValeurQue(unePreferencesBilletsDifferente));
	}

	private void creerUnePreferencesBillets() {
		unePreferencesBillets = new PreferencesBillets(SPORTS_TYPES, NOMBRE_JOURS, LOCAL_SEULEMENT, CATEGORIE_SIEGE);
	}

	private void creerUnePreferencesBilletsMemeValeur() {
		unePreferencesBilletsMemeValeur.setCategorieSiege(unePreferencesBillets.getCategorieSiege());
		unePreferencesBilletsMemeValeur.setLocalSeulement(unePreferencesBillets.isLocalSeulement());
		unePreferencesBilletsMemeValeur.setNombreJours(unePreferencesBillets.getNombreJours());
		unePreferencesBilletsMemeValeur.setSportsTypes(unePreferencesBillets.getSportsTypes());
	}

	private void creerUnePreferencesBilletsDifferent() {
		unePreferencesBilletsDifferente.setCategorieSiege(CATEGORIE_SIEGE_DIFFERENTE);
		unePreferencesBilletsDifferente.setLocalSeulement(LOCAL_SEULEMENT_DIFFERENT);
		unePreferencesBilletsDifferente.setNombreJours(NOMBRE_JOURS_DIFFERENTS);
		unePreferencesBilletsDifferente.setSportsTypes(SPORTS_TYPES_DIFFERENTS);
	}

}
