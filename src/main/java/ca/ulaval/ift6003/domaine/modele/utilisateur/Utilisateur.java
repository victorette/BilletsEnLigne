package ca.ulaval.ift6003.domaine.modele.utilisateur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ca.ulaval.ift6003.domaine.modele.inventaire.Billet;
import ca.ulaval.ift6003.domaine.shared.Entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("utilisateur")
public class Utilisateur implements Entity<Utilisateur> {

	protected String nomUtilisateur;
	protected String motDePasse;
	protected String prenom;
	protected String nom;
	protected String courriel;
	protected PreferencesBillets preferencesBillets;
	protected InfosBancaires infosBancaires;
	private List<String> billetsRevente;
	@XStreamOmitField
	protected Set<String> permissions;

	public Utilisateur() {
		this.nomUtilisateur = "";
		this.motDePasse = "";
		this.prenom = "";
		this.nom = "";
		this.courriel = "";
		this.billetsRevente = new ArrayList<>();
		this.preferencesBillets = new PreferencesBillets();
		this.infosBancaires = new InfosBancaires();
		this.permissions = new HashSet<>();
	}

	public Utilisateur(String nom_utilisateur, String mot_passe, String courriel, String prenom, String nom, PreferencesBillets preferences_billets, Set<String> permissions,
			InfosBancaires infosBancaires, List<String> billetsRevente) {
		this.nomUtilisateur = nom_utilisateur;
		this.motDePasse = mot_passe;
		this.courriel = courriel;
		this.prenom = prenom;
		this.nom = nom;
		this.preferencesBillets = preferences_billets;
		this.infosBancaires = infosBancaires;
		this.permissions = permissions;
		this.billetsRevente = billetsRevente;
	}

	public boolean aLeDroitDe(String permission) {
		return permissions.contains(permission);
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public PreferencesBillets getPreferencesBillets() {
		return preferencesBillets;
	}

	public void setPreferencesBillets(PreferencesBillets preferencesBillets) {
		this.preferencesBillets = preferencesBillets;
	}

	@Override
	public boolean memeEntiteQue(Utilisateur autre) {
		return identifiantUnique().equals(autre.identifiantUnique());
	}

	@Override
	public String identifiantUnique() {
		return nomUtilisateur;
	}

	public void ajouterRevente(Billet billet) {
		billetsRevente.add(billet.getBilletId());
	}

	public void retirerRevente(String billetID) {
		billetsRevente.remove(billetID);
	}

	public InfosBancaires getInfosBancaires() {
		return infosBancaires;
	}

	public void setInfosBancaires(InfosBancaires infosBancaires) {
		this.infosBancaires = infosBancaires;
	}

	public List<String> getBilletsRevente() {
		return billetsRevente;
	}

	public void setBilletsRevente(List<String> billetsRevente) {
		this.billetsRevente = billetsRevente;
	}

}
