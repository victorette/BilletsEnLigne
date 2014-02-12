package ca.ulaval.ift6003.interfaces.beans.Paiement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ca.ulaval.ift6003.infrastructure.utils.DateUtility;
import ca.ulaval.ift6003.interfaces.shared.ControllerBean;

@RequestScoped
@ManagedBean(name = "transactionAccepteeBean")
public class TransactionAccepteeBean extends ControllerBean {

	public TransactionAccepteeBean() {

	}

	public int getNumeroTransaction() {
		int num = (int) getSessionContext().get("numTransaction");
		getSessionContext().remove("numTransaction");
		return num;
	}

	public String getDateTransaction() {
		return DateUtility.getCurrentDateTimeString();
	}
}
