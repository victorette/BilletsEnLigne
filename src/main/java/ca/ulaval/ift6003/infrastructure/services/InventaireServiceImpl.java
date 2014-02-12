package ca.ulaval.ift6003.infrastructure.services;

import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaisonRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatch;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatchRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportif;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.CentreSportifService;
import ca.ulaval.ift6003.domaine.modele.inventaire.Inventaire;
import ca.ulaval.ift6003.domaine.modele.inventaire.InventaireService;
import ca.ulaval.ift6003.domaine.modele.inventaire.Match;
import ca.ulaval.ift6003.domaine.modele.inventaire.MatchService;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeInventaireFlyweightFactory;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeSaison;
import ca.ulaval.ift6003.domaine.modele.inventaire.entrees.EntreeUnMatch;
import ca.ulaval.ift6003.domaine.modele.specifications.PreferencesBilletsSpecification;
import ca.ulaval.ift6003.domaine.modele.utilisateur.PreferencesBillets;
import ca.ulaval.ift6003.domaine.shared.Specification;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import java.util.List;
import java.util.Map;

@SessionScoped
@ManagedBean(name = "inventaireService")
public class InventaireServiceImpl implements InventaireService {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{centreSportifRepository}")
    private CentreSportifRepository centreSportifRepository;
    @ManagedProperty(value = "#{billetUnMatchRepository}")
    private BilletUnMatchRepository billetUnMatchRepository;
    @ManagedProperty(value = "#{billetSaisonRepository}")
    private BilletSaisonRepository billetSaisonRepository;
    @ManagedProperty(value = "#{matchService}")
    private MatchService matchService;
    @ManagedProperty(value = "#{centreSportifService}")
    private CentreSportifService centreSportifService;

    @Override
    public Inventaire<EntreeUnMatch> inventaireBilletsMatchsFutur() {
        Map<String, Match> matchs = matchService.getMapMatchsAVenir();
        List<BilletUnMatch> billetsRelies = billetUnMatchRepository.selectTousAyantMatchIDs(matchs.keySet());
        return inventaireUnMatchPourBilletsDonnes(billetsRelies);
    }

    @Override
    public Inventaire<EntreeUnMatch> inventaireBilletsMatchsFutursFiltre(PreferencesBillets preferences) {
        return filtrerInventaireSelonPreferences(inventaireBilletsMatchsFutur(), preferences);
    }

    @Override
    public Inventaire<EntreeSaison> inventaireBilletsSaison() {
        List<BilletSaison> billets = billetSaisonRepository.selectTous();
        return inventaireSaisonPourBilletsDonnes(billets);
    }

    @Override
    public Inventaire<EntreeUnMatch> inventaireUnMatchPourBilletsDonnes(List<BilletUnMatch> billets) {
        Map<String, Match> matchsRelies = matchService.getTousMatchsReliesAuxBillets(billets);
        Map<String, CentreSportif> mapCentres = centreSportifService.getTousCentresReliesAuxMatchs(matchsRelies);
        Inventaire<EntreeUnMatch> inventaire = new Inventaire<>();

        for (BilletUnMatch billet : billets) {
            Match match = matchsRelies.get(billet.getMatch_id());
            CentreSportif centre = mapCentres.get(match.getNomCentreSportif());
            inventaire.ajouter(EntreeInventaireFlyweightFactory.get(billet, match, centre));
        }

        return inventaire;
    }

    @Override
    public Inventaire<EntreeSaison> inventaireSaisonPourBilletsDonnes(List<BilletSaison> billets) {
        Map<String, CentreSportif> mapCentres = centreSportifService.getCentresReliesBilletsSaisons(billets);
        Inventaire<EntreeSaison> inventaire = new Inventaire<>();

        for (BilletSaison billet : billets) {
            CentreSportif centre = mapCentres.get(billet.getNomCentreSportif());
            inventaire.ajouter(EntreeInventaireFlyweightFactory.get(billet, centre));
        }
        return inventaire;// To change body of implemented methods use File |
        // Settings | File Templates.
    }

    private Inventaire<EntreeUnMatch> filtrerInventaireSelonPreferences(Inventaire<EntreeUnMatch> inventaire, PreferencesBillets preferences) {
        Specification<EntreeUnMatch> filtreSelonPreferences = new PreferencesBilletsSpecification(preferences);
        List<EntreeUnMatch> entreesFiltrees = inventaire.filtrerEntrees(filtreSelonPreferences);
        return new Inventaire<>(entreesFiltrees);
    }

    public void setBilletSaisonRepository(BilletSaisonRepository billetSaisonRepository) {
        this.billetSaisonRepository = billetSaisonRepository;
    }

    public void setBilletUnMatchRepository(BilletUnMatchRepository billetUnMatchRepository) {
        this.billetUnMatchRepository = billetUnMatchRepository;
    }

    public void setCentreSportifRepository(CentreSportifRepository centreSportifRepository) {
        this.centreSportifRepository = centreSportifRepository;
    }

    public void setCentreSportifService(CentreSportifService centreSportifService) {
        this.centreSportifService = centreSportifService;
    }

    public void setMatchService(MatchService matchService) {
        this.matchService = matchService;
    }
}