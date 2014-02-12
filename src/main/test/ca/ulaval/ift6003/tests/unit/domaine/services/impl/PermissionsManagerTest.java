package ca.ulaval.ift6003.tests.unit.domaine.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import ca.ulaval.ift6003.infrastructure.services.PermissionsManager;

public class PermissionsManagerTest {

	@Test
	public void siPermissionsAdminAlorsVerifierExactitude() throws Exception {

		Set<String> permissionAdmin = PermissionsManager.permissionsAdmin();

		assertNotNull(permissionAdmin);
		assertEquals(15, permissionAdmin.size());
		assertTrue(permissionAdmin.contains("SUPPRESSION_CENTRE_SPORTIF"));
		assertTrue(permissionAdmin.contains("VOIR_TOUS_MATCHS"));
		assertTrue(permissionAdmin.contains("VOIR_TOUS_BILLETS"));
		assertTrue(permissionAdmin.contains("ACHAT_BILLET"));
		assertTrue(permissionAdmin.contains("VOIR_INVENTAIRE"));
		assertTrue(permissionAdmin.contains("SUPPRESSION_MATCH"));
		assertTrue(permissionAdmin.contains("CREATION_MATCH"));
		assertTrue(permissionAdmin.contains("VOIR_TOUS_CENTRES_SPORTIFS"));
		assertTrue(permissionAdmin.contains("CREATION_BILLET"));
		assertTrue(permissionAdmin.contains("REVENTE_BILLET"));
		assertTrue(permissionAdmin.contains("MODIFICATION_COMPTE"));
		assertTrue(permissionAdmin.contains("SUPPRESSION_BILLET"));
		assertTrue(permissionAdmin.contains("APPLIQUER_PREFERENCES_BILLETS"));
		assertTrue(permissionAdmin.contains("CREATION_CENTRE_SPORTIF"));
		assertTrue(permissionAdmin.contains("VOIR_TOUS_UTILISATEURS"));
	}

	@Test
	public void siPermissionsUtilisateurAnonymeAlorsVerifierExactitude() throws Exception {

		Set<String> permissionsUtilisateurAnonyme = PermissionsManager
				.permissionsUtilisateurAnonyme();

		assertNotNull(permissionsUtilisateurAnonyme);
		assertEquals(5, permissionsUtilisateurAnonyme.size());
		assertTrue(permissionsUtilisateurAnonyme.contains("VOIR_TOUS_CENTRES_SPORTIFS"));
		assertTrue(permissionsUtilisateurAnonyme.contains("VOIR_TOUS_MATCHS"));
		assertTrue(permissionsUtilisateurAnonyme.contains("VOIR_TOUS_BILLETS"));
		assertTrue(permissionsUtilisateurAnonyme.contains("VOIR_INVENTAIRE"));
		assertTrue(permissionsUtilisateurAnonyme.contains("VOIR_TOUS_UTILISATEURS"));
	}

	@Test
	public void siPermissionsUtilisateurInscritAlorsVerifierExactitude() throws Exception {

		Set<String> permissionsUtilisateurInscrit = PermissionsManager
				.permissionsUtilisateurInscrit();

		assertNotNull(permissionsUtilisateurInscrit);
		assertEquals(9, permissionsUtilisateurInscrit.size());
		assertTrue(permissionsUtilisateurInscrit.contains("VOIR_TOUS_CENTRES_SPORTIFS"));
		assertTrue(permissionsUtilisateurInscrit.contains("REVENTE_BILLET"));
		assertTrue(permissionsUtilisateurInscrit.contains("VOIR_TOUS_MATCHS"));
		assertTrue(permissionsUtilisateurInscrit.contains("MODIFICATION_COMPTE"));
		assertTrue(permissionsUtilisateurInscrit.contains("VOIR_TOUS_BILLETS"));
		assertTrue(permissionsUtilisateurInscrit.contains("ACHAT_BILLET"));
		assertTrue(permissionsUtilisateurInscrit.contains("APPLIQUER_PREFERENCES_BILLETS"));
		assertTrue(permissionsUtilisateurInscrit.contains("VOIR_INVENTAIRE"));
		assertTrue(permissionsUtilisateurInscrit.contains("VOIR_TOUS_UTILISATEURS"));
	}

}