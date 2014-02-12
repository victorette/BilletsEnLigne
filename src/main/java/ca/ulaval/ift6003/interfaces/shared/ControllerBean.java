package ca.ulaval.ift6003.interfaces.shared;

import ca.ulaval.ift6003.interfaces.utils.GUIMessageHandler;

import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import java.util.Map;

public abstract class ControllerBean {

	@ManagedProperty(value = "#{guiMessageHandler}")
	protected GUIMessageHandler guiMessageHandler;

	public Flash getFlashContext() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	public Map<String, Object> getSessionContext() {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
	}

	public GUIMessageHandler getGuiMessageHandler() {
		return guiMessageHandler;
	}

	public void setGuiMessageHandler(GUIMessageHandler guiMessageHandler) {
		this.guiMessageHandler = guiMessageHandler;
	}

}
