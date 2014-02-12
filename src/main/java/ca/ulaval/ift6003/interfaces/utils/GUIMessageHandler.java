package ca.ulaval.ift6003.interfaces.utils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SessionScoped
@ManagedBean(name = "guiMessageHandler")
public class GUIMessageHandler implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{facesBroker}")
	private FacesBroker facesBroker;

	public GUIMessageHandler() {

	}

	public void addMessage(String sommaire, String message) {
		facesBroker.getContext().addMessage(null, new FacesMessage(sommaire, message));
		setKeepMessages(true);
	}

	public FacesBroker getFacesBroker() {
		return facesBroker;
	}

	public void setFacesBroker(FacesBroker facesBroker) {
		this.facesBroker = facesBroker;
	}

	private void setKeepMessages(boolean keep) {
		facesBroker.getContext().getExternalContext().getFlash().setKeepMessages(keep);
	}

}
