package ca.ulaval.ift6003.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import ca.ulaval.ift6003.tests.unit.application.frontiere.DTOassembleurs.AssembleurServiceTest;
import ca.ulaval.ift6003.tests.unit.application.frontiere.DTOassembleurs.EntreeSaisonDTOAssembleurTest;
import ca.ulaval.ift6003.tests.unit.application.frontiere.DTOassembleurs.EntreeUnMatchDTOAssembleurTest;
import ca.ulaval.ift6003.tests.unit.application.impl.AdminApplicationImplTest;
import ca.ulaval.ift6003.tests.unit.application.impl.InventaireApplicationImplTest;
import ca.ulaval.ift6003.tests.unit.application.impl.PanierApplicationImplTest;
import ca.ulaval.ift6003.tests.unit.application.impl.UtilisateurManagementImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.factories.EntreeInventaireFlyweightFactoryTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.entites.BilletSaisonTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.entites.BilletUnMatchTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.entites.CentreSportifTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.entites.MatchTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.entites.UtilisateurTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.exceptions.DroitsInsuffisantsTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.exceptions.EntiteNonTrouveeTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.exceptions.IdentifiantDejaExistantTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.exceptions.QuantiteInsuffisanteTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.specifications.PreferencesBilletsSpecificationTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects.EntreeSaisonTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects.EntreeUnMatchTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects.InventaireTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects.MapNextIDTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects.PanierTest;
import ca.ulaval.ift6003.tests.unit.domaine.modele.valueobjects.PreferencesBilletsTest;
import ca.ulaval.ift6003.tests.unit.domaine.repositories.impl.BilletSaisonRepositoryImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.repositories.impl.BilletUnMatchRepositoryImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.repositories.impl.CentreSportifRepositoryImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.repositories.impl.GestionIDRepositoryImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.repositories.impl.MatchRepositoryImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.repositories.impl.UtilisateurRepositoryImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.services.impl.BilletServiceImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.services.impl.CentreSportifServiceImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.services.impl.InventaireServiceImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.services.impl.MatchServiceImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.services.impl.PanierServiceImplTest;
import ca.ulaval.ift6003.tests.unit.domaine.services.impl.PermissionsManagerTest;
import ca.ulaval.ift6003.tests.unit.domaine.services.impl.UtilisateurServiceImplTest;
import ca.ulaval.ift6003.tests.unit.infrastructure.persistence.ConvertisseurTest;
import ca.ulaval.ift6003.tests.unit.infrastructure.persistence.EntityContainerTest;
import ca.ulaval.ift6003.tests.unit.infrastructure.persistence.XMLReaderWriterTest;
import ca.ulaval.ift6003.tests.unit.infrastructure.utils.DateUtilityTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.PanierBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.TousBilletsBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.TousCentresSportifsBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.TousMatchsBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.TousUtilisateursBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.UtilisateurSessionBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.Paiement.PaiementInstantBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.Paiement.PaiementPanierBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly.CreationBilletSaisonBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly.CreationBilletUnMatchBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly.CreationCentreSportifBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly.CreationMatchBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly.SuppressionBilletBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly.SuppressionCentreBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.adminonly.SuppressionMatchBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.compte.CreationCompteBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.compte.ModificationCompteBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire.InventaireTableDataModelTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire.InventaireTableTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire.saison.InventaireSaisonBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire.unmatch.InventaireUnMatchBeanTest;
import ca.ulaval.ift6003.tests.unit.interfaces.beans.inventaire.unmatch.InventaireUnMatchFiltreOptionsTest;
import ca.ulaval.ift6003.tests.unit.interfaces.filters.CreationEntiteFilterTest;
import ca.ulaval.ift6003.tests.unit.interfaces.filters.LoginFilterTest;
import ca.ulaval.ift6003.tests.unit.interfaces.filters.ModificationCompteFilterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		// Application
		// frontiere

		AssembleurServiceTest.class,
		EntreeSaisonDTOAssembleurTest.class,
		EntreeUnMatchDTOAssembleurTest.class,
		// impl
		AdminApplicationImplTest.class,
		InventaireApplicationImplTest.class,
		PanierApplicationImplTest.class,
		UtilisateurManagementImplTest.class,
		//
		//

		// Domaine
		// factories
		EntreeInventaireFlyweightFactoryTest.class,
		// modele
		BilletSaisonTest.class,
		BilletUnMatchTest.class,
		CentreSportifTest.class,
		MatchTest.class,
		UtilisateurTest.class,
		DroitsInsuffisantsTest.class,
		EntiteNonTrouveeTest.class,
		IdentifiantDejaExistantTest.class,
		QuantiteInsuffisanteTest.class,
		PreferencesBilletsSpecificationTest.class,
		EntreeSaisonTest.class,
		EntreeUnMatchTest.class,
		InventaireTest.class,
		MapNextIDTest.class,
		PanierTest.class,
		PreferencesBilletsTest.class,
		// repositories
		// impl
		BilletSaisonRepositoryImplTest.class,
		BilletUnMatchRepositoryImplTest.class,
		CentreSportifRepositoryImplTest.class,
		GestionIDRepositoryImplTest.class,
		MatchRepositoryImplTest.class,
		UtilisateurRepositoryImplTest.class,
		//

		// services
		BilletServiceImplTest.class,
		CentreSportifServiceImplTest.class,
		InventaireServiceImplTest.class,
		MatchServiceImplTest.class,
		PanierServiceImplTest.class,
		PermissionsManagerTest.class,
		UtilisateurServiceImplTest.class,
		// shared

		// Infrastructure
		// persistence
		ConvertisseurTest.class,
		EntityContainerTest.class,
		XMLReaderWriterTest.class,
		// utils
		DateUtilityTest.class,
		//
		//

		// Interfaces
		// beans
		// adminonly
		CreationBilletSaisonBeanTest.class, CreationBilletUnMatchBeanTest.class,
		CreationCentreSportifBeanTest.class, CreationMatchBeanTest.class,
		SuppressionBilletBeanTest.class, SuppressionCentreBeanTest.class,
		SuppressionMatchBeanTest.class,
		// compte
		CreationCompteBeanTest.class,
		ModificationCompteBeanTest.class,
		// inventaire
		// saison
		InventaireSaisonBeanTest.class,
		// unmatch
		InventaireUnMatchBeanTest.class, InventaireUnMatchFiltreOptionsTest.class,
		//
		InventaireTableDataModelTest.class, InventaireTableTest.class,
		// Paiement
		PaiementInstantBeanTest.class, PaiementPanierBeanTest.class,
		//
		PanierBeanTest.class, TousBilletsBeanTest.class, TousCentresSportifsBeanTest.class,
		TousMatchsBeanTest.class, TousUtilisateursBeanTest.class, UtilisateurSessionBeanTest.class,
		// filters
		CreationEntiteFilterTest.class, LoginFilterTest.class, ModificationCompteFilterTest.class
//
//
})
public class AllUnitTests {

}