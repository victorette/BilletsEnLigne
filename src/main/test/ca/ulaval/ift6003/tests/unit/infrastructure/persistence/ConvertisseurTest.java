package ca.ulaval.ift6003.tests.unit.infrastructure.persistence;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.infrastructure.persistence.Convertisseur;

public class ConvertisseurTest {

	Convertisseur<Utilisateur> convertisseur;
	List<Utilisateur> liste;
	Utilisateur utilisateur;
	
	@Before
	public void setUp() throws Exception {
		convertisseur = new Convertisseur<>(Utilisateur.class);
		liste = new ArrayList<>();
		utilisateur = new Utilisateur();
	}

	@Test
	public void siConversionDeListeVersXMLAlorsResultatEstValide() {
		liste.add(utilisateur);
		String resultat = convertisseur.listeToXML(liste);
		assertNotEquals(resultat.length(), 0);
	}

	@Test
	public void siConversionDeXMLVersListeAlorsResultatEstValide() {
		// Oui, ca fait pitie...
		String stringAConvertir = "<entites><utilisateur>"
									+ "<nomUtilisateur></nomUtilisateur>"
									+ "<motDePasse></motDePasse>"
									+ "<prenom></prenom>"
									+ "<nom></nom>"
									+ "<courriel></courriel>"
									+ "<preferencesBillets>"
									+ "<sportsTypes>"
									+ "<string>soccer</string>"
									+ "<string>football</string>"
									+ "<string>basketball</string>"
									+ "<string>rubgy</string>"
									+ "<string>volleyball</string>"
									+ "</sportsTypes>"
									+ "<nombreJours>365</nombreJours>"
									+ "<categorieSiege>tous</categorieSiege>"
									+ "<localSeulement>false</localSeulement>"
									+ "</preferencesBillets>"
									+ "</utilisateur>"
									+ "</entites>";
		liste = convertisseur.XMLToListe(stringAConvertir);
		assertEquals(liste.size(), 1);
	}
	
	@Test
	public void siConversionXMLVideVersListeAlorsListeEstAussiVide() {
		String stringAConvertir = "";
		liste = convertisseur.XMLToListe(stringAConvertir);
		assertEquals(liste.size(), 0);
	}
}
