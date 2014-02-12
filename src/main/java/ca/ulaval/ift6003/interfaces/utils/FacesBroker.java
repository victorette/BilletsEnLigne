package ca.ulaval.ift6003.interfaces.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.ObjectStreamException;
import java.io.Serializable;

@ManagedBean(name = "facesBroker")
@SessionScoped
public class FacesBroker implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final FacesBroker INSTANCE = new FacesBroker();

	public FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

	private Object readResolve() throws ObjectStreamException {
		return INSTANCE;
	}
}