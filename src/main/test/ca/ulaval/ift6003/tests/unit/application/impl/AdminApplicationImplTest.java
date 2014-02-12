package ca.ulaval.ift6003.tests.unit.application.impl;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.frontiere.exceptionsUI.UIDroitsInsuffisants;
import ca.ulaval.ift6003.application.impl.AdminApplicationImpl;
import ca.ulaval.ift6003.application.impl.UtilisateurManagementImpl;
import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Permissions;
import ca.ulaval.ift6003.domaine.modele.exceptions.DroitsInsuffisants;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletService;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifService;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.domaine.modele.inventaire.MatchService;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletSaisonRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.BilletUnMatchRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.CentreSportifRepositoryImpl;
import ca.ulaval.ift6003.infrastructure.persistence.repositories.MatchRepositoryImpl;

public class AdminApplicationImplTest {

	private AdminApplicationImpl adminApplicationFacadeImpl;

	private BilletSaison mockBilletSaison;
	private BilletUnMatch mockBilletUnMatch;
	private CentreSportif mockCentreSportif;
	private Match mockMatch;

	private UtilisateurManagementImpl mockUtilisateurManagerFacadeImpl;

	private BilletSaisonRepositoryImpl mockBilletSaisonRepositoryImpl;
	private BilletUnMatchRepositoryImpl mockBilletUnMatchRepositoryImpl;
	private CentreSportifRepositoryImpl mockCentreSportifRepositoryImpl;
	private MatchRepositoryImpl mockMatchRepositoryImpl;

	private CentreSportifService mockCentreSportifService;
	private BilletService mockBilletService;
	private MatchService mockMatchService;

	private final int NOMBRE_BILLETS_SAISON = 5;
	private final int NOMBRE_BILLETS_UN_MATCH = 6;
	private final String BILLET_ID = "1";
	private final String NOM_CENTRE = "";
	private final String MATCH_ID = "1";

	@Before
	public void setup() {
		adminApplicationFacadeImpl = new AdminApplicationImpl();

		mockBilletSaison = mock(BilletSaison.class);
		mockBilletUnMatch = mock(BilletUnMatch.class);
		mockCentreSportif = mock(CentreSportif.class);
		mockMatch = mock(Match.class);

		mockUtilisateurManagerFacadeImpl = mock(UtilisateurManagementImpl.class);

		mockBilletSaisonRepositoryImpl = mock(BilletSaisonRepositoryImpl.class);
		mockBilletUnMatchRepositoryImpl = mock(BilletUnMatchRepositoryImpl.class);
		mockCentreSportifRepositoryImpl = mock(CentreSportifRepositoryImpl.class);
		mockMatchRepositoryImpl = mock(MatchRepositoryImpl.class);

		mockCentreSportifService = mock(CentreSportifService.class);
		mockBilletService = mock(BilletService.class);
		mockMatchService = mock(MatchService.class);

		adminApplicationFacadeImpl.setUtilisateurManagement(mockUtilisateurManagerFacadeImpl);

		adminApplicationFacadeImpl.setMatchRepository(mockMatchRepositoryImpl);
		adminApplicationFacadeImpl.setBilletSaisonRepository(mockBilletSaisonRepositoryImpl);
		adminApplicationFacadeImpl.setBilletUnMatchRepository(mockBilletUnMatchRepositoryImpl);
		adminApplicationFacadeImpl.setCentreSportifRepository(mockCentreSportifRepositoryImpl);

		adminApplicationFacadeImpl.setCentreSportifService(mockCentreSportifService);
		adminApplicationFacadeImpl.setBilletService(mockBilletService);
		adminApplicationFacadeImpl.setMatchService(mockMatchService);

	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsCreationBilletSaisonVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.CREATION_BILLET);
		adminApplicationFacadeImpl.ajouterNouveauBilletSaison(mockBilletSaison);
	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsCreationBilletMatchVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.CREATION_BILLET);
		adminApplicationFacadeImpl.ajouterNouveauBilletUnMatch(mockBilletUnMatch);
	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsCreationCentreSportifVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.CREATION_CENTRE_SPORTIF);
		adminApplicationFacadeImpl.ajouterNouveauCentreSportif(mockCentreSportif);
	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsCreationMatchVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.CREATION_MATCH);
		adminApplicationFacadeImpl.ajouterNouveauMatch(mockMatch);
	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsCreationPlusieursBilletsSaisonVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.CREATION_BILLET);
		adminApplicationFacadeImpl.ajouterPlusieursNouveauxBilletsSaison(mockBilletSaison, NOMBRE_BILLETS_SAISON);	
	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsCreationPlusieursBilletsUnMatchVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.CREATION_BILLET);
		adminApplicationFacadeImpl.ajouterPlusieursNouveauxBilletsUnMatch(mockBilletUnMatch, NOMBRE_BILLETS_UN_MATCH);
	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsSupprimerBilletSaisonVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.SUPPRESSION_BILLET);
		adminApplicationFacadeImpl.effacerBilletSaisonParID(BILLET_ID);
	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsSupprimerBilletUnMatchVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.SUPPRESSION_BILLET);
		adminApplicationFacadeImpl.effacerBilletUnMatchParID(BILLET_ID);
	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsSupprimerCentreSportifVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.SUPPRESSION_CENTRE_SPORTIF);
		adminApplicationFacadeImpl.effacerCentreSportifParNom(NOM_CENTRE);
	}

	@Test(expected = UIDroitsInsuffisants.class)
	public void siDroitsInsuffisantsAlorsEffacerMatchParIDVaEchouer() throws Exception {
		doThrow(new DroitsInsuffisants()).when(mockUtilisateurManagerFacadeImpl).authoriserUtilisateur(Permissions.SUPPRESSION_MATCH);
		adminApplicationFacadeImpl.effacerMatchParID(MATCH_ID);
	}

	@Test
	public void siCreationBilletSaisonAlorsAppelServiceCorrespondant() throws Exception {
		adminApplicationFacadeImpl.ajouterNouveauBilletSaison(mockBilletSaison);
		verify(mockBilletService).ajouterNouveauBilletSaison(mockBilletSaison);

	}

	@Test
	public void siCreationBilletMatchAlorsAppelServiceCorrespondant() throws Exception {
		adminApplicationFacadeImpl.ajouterNouveauBilletUnMatch(mockBilletUnMatch);
		verify(mockBilletService).ajouterNouveauBilletUnMatch(mockBilletUnMatch);
	}

	@Test
	public void siCreationCentreSportifAlorsAppelServiceCorrespondant() throws Exception {
		adminApplicationFacadeImpl.ajouterNouveauCentreSportif(mockCentreSportif);
		verify(mockCentreSportifService).ajouterNouveauCentreSportif(mockCentreSportif);
	}

	@Test
	public void siEffacerMatchAlorsAppelSupprimerMatchReferentiel() throws Exception {
		adminApplicationFacadeImpl.effacerMatchParID(MATCH_ID);
		verify(mockMatchRepositoryImpl).supprimerParIdentifiantUnique(MATCH_ID);
	}

	@Test
	public void siEffacerMatchAlorsAppelSupprimerSesBilletsReferentiel() throws Exception {
		adminApplicationFacadeImpl.effacerMatchParID(MATCH_ID);
		verify(mockBilletUnMatchRepositoryImpl).supprimerTousAyantMatchID(MATCH_ID);
	}

	@Test
	public void siCreationMatchAlorsAppelServiceCorrespondant() throws Exception {
		adminApplicationFacadeImpl.ajouterNouveauMatch(mockMatch);
		verify(mockMatchService).ajouterNouveauMatch(mockMatch);
	}

	@Test
	public void siAjouterPlusieursBilletsSaisonAlorsPlusieursAppelsService() throws Exception {
		adminApplicationFacadeImpl.ajouterPlusieursNouveauxBilletsSaison(mockBilletSaison, NOMBRE_BILLETS_SAISON);
		verify(mockBilletService, times(NOMBRE_BILLETS_SAISON)).ajouterNouveauBilletSaison(mockBilletSaison.clone());
	}

	@Test
	public void siAjouterPlusieursBilletsMatchAlorsPlusieursAppelsService() throws Exception {
		adminApplicationFacadeImpl.ajouterPlusieursNouveauxBilletsUnMatch(mockBilletUnMatch, NOMBRE_BILLETS_UN_MATCH);
		verify(mockBilletService, times(NOMBRE_BILLETS_UN_MATCH)).ajouterNouveauBilletUnMatch(mockBilletUnMatch.clone());
	}

	@Test
	public void siSupprimerBilletSaisonAlorsSupprimerDuReferentiel() throws Exception {
		adminApplicationFacadeImpl.effacerBilletSaisonParID(BILLET_ID);
		verify(mockBilletSaisonRepositoryImpl).supprimerParIdentifiantUnique(BILLET_ID);
	}

	@Test
	public void siSupprimerBilletUnMatchAlorsSupprimerDuReferentiel() throws Exception {
		adminApplicationFacadeImpl.effacerBilletUnMatchParID(BILLET_ID);
		verify(mockBilletUnMatchRepositoryImpl).supprimerParIdentifiantUnique(BILLET_ID);
	}

	@Test
	public void siSupprimerCentreSportifAlorsSupprimerDuReferentiel() throws Exception {
		adminApplicationFacadeImpl.effacerCentreSportifParNom(NOM_CENTRE);
		verify(mockCentreSportifRepositoryImpl).supprimerParIdentifiantUnique(NOM_CENTRE);
	}

}
