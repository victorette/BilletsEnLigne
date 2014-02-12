package ca.ulaval.ift6003.tests.unit.domaine.modele.specifications;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;
import ca.ulaval.ift6003.domaine.modele.specifications.PreferencesBilletsSpecification;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;
import ca.ulaval.ift6003.infrastructure.utils.DateUtility;

public class PreferencesBilletsSpecificationTest {

	PreferencesBilletsSpecification preferencesBilletsSpecifications,
			autresPreferencesBilletsSpecifications;
	PreferencesBillets mockPreferences, mockAutresPreferences;
	EntreeUnMatch mockEntreeUnMatch;
	List<String> listeSportsTypes;
	String FOOTBALL = "Football";

	@Before
	public void setUp() throws Exception {

		listeSportsTypes = new ArrayList<String>();
		listeSportsTypes.add(FOOTBALL);

		mockPreferences = mock(PreferencesBillets.class);
		mockAutresPreferences = mock(PreferencesBillets.class);

		mockEntreeUnMatch = mock(EntreeUnMatch.class);

		Date aujourdhui = DateUtility.getCurrentDateTime();
		Date plusTard = DateUtility.ajouterJours(aujourdhui, 25);

		when(mockEntreeUnMatch.getDate()).thenReturn(plusTard);
		when(mockEntreeUnMatch.getCategorieSiege()).thenReturn(Consts.SIEGE_GENERAL);
		when(mockEntreeUnMatch.getSportType()).thenReturn("Football");

		when(mockPreferences.getCategorieSiege()).thenReturn("tous");
		when(mockPreferences.getSportsTypes()).thenReturn(listeSportsTypes);

		when(mockAutresPreferences.getCategorieSiege()).thenReturn(Consts.SIEGE_GENERAL);

		preferencesBilletsSpecifications = new PreferencesBilletsSpecification(mockPreferences);
		autresPreferencesBilletsSpecifications = new PreferencesBilletsSpecification(
				mockAutresPreferences);

		preferencesBilletsSpecifications.estSatisfaitePar(mockEntreeUnMatch);
		autresPreferencesBilletsSpecifications.estSatisfaitePar(mockEntreeUnMatch);

	}

	@Test
	public void ContraintesEntreeUnMatchEstVerifieAlorsPreferenceSportEstVerifie() {
		verify(mockEntreeUnMatch).getSportType();
	}

	@Test
	public void siContraintesEntreeUnMatchEstVerifieAlorsCategorieSiegeEstVerifie() {
		verify(mockPreferences).getCategorieSiege();
		verify(mockAutresPreferences).getCategorieSiege();
	}

	@Test
	public void siContraintesEntreeUnMatchEstVerifieAlorsTypesDeSportsEstVerifie() {
		verify(mockPreferences).getSportsTypes();
		verify(mockAutresPreferences).getCategorieSiege();
	}

	@Test
	public void siContraintesEntreeUnMatchEstVerifieAlorsIntervalDeDatesEstVerifie() {
		verify(mockPreferences).getNombreJours();
		verify(mockAutresPreferences).getCategorieSiege();
	}

	@Test
	public void siContraintesEntreeUnMatchEstVerifieAlorsTypeDeMatchEstVerifie() {
		verify(mockPreferences).isLocalSeulement();
		verify(mockAutresPreferences).getCategorieSiege();
	}

}
