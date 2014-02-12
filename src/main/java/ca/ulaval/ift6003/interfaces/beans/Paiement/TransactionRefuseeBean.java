package ca.ulaval.ift6003.interfaces.beans.Paiement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

@RequestScoped
@ManagedBean(name = "transactionRefuseeBean")
public class TransactionRefuseeBean extends ControllerBean {

	public TransactionRefuseeBean() {

	}

	public String getMessageErreur() {
		String msg = (String) getSessionContext().get("messageErreur");
		getSessionContext().remove("messageErreur");
		return msg;
	}

}
