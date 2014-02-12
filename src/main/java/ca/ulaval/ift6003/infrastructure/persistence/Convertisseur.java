package ca.ulaval.ift6003.infrastructure.persistence;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.List;

public class Convertisseur<T> {

	private XStream xstream;
	private Class<T> entityClass;

	public Convertisseur(Class<T> entityClass) {
		this.xstream = new XStream();
		this.entityClass = entityClass;
	}

	public String listeToXML(List<T> liste) {
		EntityContainer<T> entites = new EntityContainer<T>(liste);
		processAnnotations();
		return xstream.toXML(entites);
	}

	public List<T> XMLToListe(String xml) {
		if (xml.equals("")) {
			return new ArrayList<T>();
		}

		processAnnotations();

		@SuppressWarnings("unchecked")
		EntityContainer<T> entites = (EntityContainer<T>) xstream.fromXML(xml);

		if (entites.getEntites() == null) {
			return new ArrayList<T>();
		} else {
			return entites.getEntites();
		}
	}

	private void processAnnotations() {
		xstream.processAnnotations(EntityContainer.class);
		xstream.processAnnotations(entityClass);
	}
}
