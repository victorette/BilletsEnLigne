package ca.ulaval.ift6003.infrastructure.services;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ca.ulaval.ift6003.domaine.modele.gestionIDs.GestionIDRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletSaisonRepository;
import ca.ulaval.ift6003.domaine.modele.inventaire.BilletUnMatchRepository;
import ca.ulaval.ift6003.domaine.modele.panieretachats.TransactionService;

@SessionScoped
@ManagedBean(name = "transactionService")
public class TransactionServiceImpl implements TransactionService {

	@ManagedProperty(value = "#{billetUnMatchRepository}")
	private BilletUnMatchRepository billetUnMatchRepository;
	@ManagedProperty(value = "#{billetSaisonRepository}")
	private BilletSaisonRepository billetSaisonRepository;
	@ManagedProperty(value = "#{gestionIDRepository}")
	private GestionIDRepository gestionIDRepository;

	@Override
	public int getTransactionID() {
		return gestionIDRepository.selectEtUpdateNextId("transaction");
	}

	public void setBilletSaisonRepository(BilletSaisonRepository billetSaisonRepository) {
		this.billetSaisonRepository = billetSaisonRepository;
	}

	public void setBilletUnMatchRepository(BilletUnMatchRepository billetUnMatchRepository) {
		this.billetUnMatchRepository = billetUnMatchRepository;
	}

	public void setGestionIDRepository(GestionIDRepository gestionIDRepository) {
		this.gestionIDRepository = gestionIDRepository;
	}
}
