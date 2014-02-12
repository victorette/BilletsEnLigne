package ca.ulaval.ift6003.tests.unit.domaine.modele.entites;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.utilisateur.InfosBancaires;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;

public class UtilisateurTest {

	private static final InfosBancaires INFOS_BANCAIRES = new InfosBancaires();
	private final String NOUVEAU_NOM_UTILISATEUR = "NouveauNomUtilisateur";
	private final String NOUVEAU_MOT_PASSE = "NouveauMotDePasse";
	private final String NOUVEAU_PRENOM = "NouveauPrenom";
	private final String NOUVEAU_NOM = "NouveauNom";
	private final String NOUVEAU_COURRIEL = "NouveauCourriel";
	private List<String> sportsTypes;
	private final PreferencesBillets NOUVEAU_PREFERENCES_BILLETS = new PreferencesBillets(sportsTypes, 2, true, "siege");

	private final String NOM_UTILISATEUR = "NomUtilisateur";
	private final String MOT_PASSE = "MotDePasse";
	private final String PRENOM = "Prenom";
	private final String NOM = "Nom";
	private final String COURRIEL = "Courriel";
	private final PreferencesBillets PREFERENCES_BILLETS = new PreferencesBillets();
	private final Set<String> PERMISSIONS = new HashSet<>();
	private List<String> BILLETS_REVENTE = new ArrayList<>();

	private Utilisateur utilisateurDefaut;
	private Utilisateur utilisateurSpecifique;

	@Before
	public void initialisationTests() {
		utilisateurDefaut = new Utilisateur();
		utilisateurSpecifique = new Utilisateur(NOM_UTILISATEUR, MOT_PASSE, COURRIEL, PRENOM, NOM, PREFERENCES_BILLETS, PERMISSIONS, INFOS_BANCAIRES, BILLETS_REVENTE);
	}

	@Test
	public void siDeuxUtilisateursOntLeMemeNomUtilisateurAlorsMemeEntiteQueRetourneVrai() {
		utilisateurDefaut.setNomUtilisateur(NOM_UTILISATEUR);
		assertTrue(utilisateurDefaut.memeEntiteQue(utilisateurSpecifique));
	}

	@Test
	public void siDeuxUtilisateursNontPasLeMemeNomAlorsMemeEntiteQueRetourneFaux() {
		utilisateurDefaut.setNomUtilisateur(NOUVEAU_NOM_UTILISATEUR);
		assertFalse(utilisateurDefaut.memeEntiteQue(utilisateurSpecifique));
	}

	@Test
	public void siUnutilisateurExistantQuandSonNomUtilisateurEstChangeAlorsNouveauEstValide() {
		utilisateurDefaut.setNomUtilisateur(NOUVEAU_NOM_UTILISATEUR);
		assertEquals(utilisateurDefaut.getNomUtilisateur(), NOUVEAU_NOM_UTILISATEUR);
	}

	@Test
	public void siUnutilisateurExistantQuandSesPreferencesBilletsSontChangesAlorsNouveauEstValide() {
		utilisateurDefaut.setPreferencesBillets(NOUVEAU_PREFERENCES_BILLETS);
		assertEquals(utilisateurDefaut.getPreferencesBillets(), NOUVEAU_PREFERENCES_BILLETS);
	}

	@Test
	public void siUnutilisateurExistantQuandSonMotPasseEstChangeAlorsNouveauEstValide() {
		utilisateurDefaut.setMotDePasse(NOUVEAU_MOT_PASSE);
		assertEquals(utilisateurDefaut.getMotDePasse(), NOUVEAU_MOT_PASSE);
	}

	@Test
	public void siUnutilisateurExistantQuandSonPrenomEstChangeAlorsNouveauEstValide() {
		utilisateurDefaut.setPrenom(NOUVEAU_PRENOM);
		assertEquals(utilisateurDefaut.getPrenom(), NOUVEAU_PRENOM);
	}

	@Test
	public void siUnutilisateurExistantQuandSonNomFamilleEstChangeAlorsNouveauEstValide() {
		utilisateurDefaut.setNom(NOUVEAU_NOM);
		assertEquals(utilisateurDefaut.getNom(), NOUVEAU_NOM);
	}

	@Test
	public void siUnutilisateurExistantQuandSonCourrielEstChangeAlorsNouveauEstValide() {
		utilisateurDefaut.setCourriel(NOUVEAU_COURRIEL);
		assertEquals(utilisateurDefaut.getCourriel(), NOUVEAU_COURRIEL);
	}

	@Test
	public void siUnutilisateurEstCreeAvecInformationsAlorsValeursSontValides() {
		assertEquals(utilisateurSpecifique.getNomUtilisateur(), NOM_UTILISATEUR);
		assertEquals(utilisateurSpecifique.getMotDePasse(), MOT_PASSE);
		assertEquals(utilisateurSpecifique.getPrenom(), PRENOM);
		assertEquals(utilisateurSpecifique.getNom(), NOM);
		assertEquals(utilisateurSpecifique.getPermissions(), PERMISSIONS);
		assertEquals(utilisateurSpecifique.getCourriel(), COURRIEL);

	}
}
