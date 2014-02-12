package ca.ulaval.ift6003.infrastructure.services;

import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Consts;
import ca.ulaval.ift6003.domaine.modele.exceptions.EntiteNonTrouvee;
import ca.ulaval.ift6003.domaine.modele.exceptions.IdentifiantDejaExistant;
import ca.ulaval.ift6003.domaine.modele.utilisateur.Utilisateur;
import ca.ulaval.ift6003.domaine.modele.utilisateur.UtilisateurRepository;
import ca.ulaval.ift6003.domaine.modele.utilisateur.UtilisateurService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.io.Serializable;

@SessionScoped
@ManagedBean(name = "utilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService, Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{utilisateurRepository}")
    private UtilisateurRepository utilisateurRepository;

    public void inscrireNouvelUtilisateur(Utilisateur utilisateur) throws IdentifiantDejaExistant {
        if (!nomUtilisateurUnique(utilisateur.getNomUtilisateur())) {
            throw new IdentifiantDejaExistant("Nom utilisateur déjà utilisé!");
        } else {
            utilisateurRepository.inserer(utilisateur);
        }
    }

    public Utilisateur loginUtilisateurAnonyme() {
        Utilisateur anonyme = new Utilisateur();
        anonyme.setPermissions(PermissionsManager.permissionsUtilisateurAnonyme());
        return anonyme;
    }

    public Utilisateur loginUtilisateurParCredentiels(String nomUtilisateur, String motDePasse) throws EntiteNonTrouvee {
        Utilisateur utilisateur = utilisateurRepository.selectParCredentiels(nomUtilisateur, motDePasse);
        if (utilisateurIsAdmin(utilisateur)) {
            utilisateur.setPermissions(PermissionsManager.permissionsAdmin());
        } else {
            utilisateur.setPermissions(PermissionsManager.permissionsUtilisateurInscrit());
        }
        return utilisateur;
    }

    private boolean utilisateurIsAdmin(Utilisateur utilisateur) {
        return utilisateur.getNomUtilisateur().equals(Consts.UTILISATEUR_ADMIN);
    }

    public void setUtilisateurRepository(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    private boolean nomUtilisateurUnique(String nomUtilisateur) {
        try {
            Utilisateur utilisateur = utilisateurRepository.selectParIdentifiantUnique(nomUtilisateur);
            return utilisateur == null;
        } catch (EntiteNonTrouvee e) {
            return true;
        }
    }
}
