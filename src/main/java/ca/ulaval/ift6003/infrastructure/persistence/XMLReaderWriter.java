package ca.ulaval.ift6003.infrastructure.persistence;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.*;

@ApplicationScoped
@ManagedBean(name = "xmlReaderWriter")
public class XMLReaderWriter implements Serializable {

	private static final long serialVersionUID = 1L;

	public XMLReaderWriter() {

	}

	public void ecrireDansFichierXML(String xml, String nomFichier) {
		try {
			File fichier = getFichier(nomFichier);

			PrintWriter pw = new PrintWriter(new FileOutputStream(fichier));
			pw.print(xml);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String lireFichierXML(String nomFichier) {
		StringBuilder sb = new StringBuilder();
		String line;

		try {
			File fichier = getFichier(nomFichier);

			BufferedReader br = new BufferedReader(new FileReader(fichier));
			while ((line = br.readLine()) != null) {
				sb.append(line.trim());
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	private File getFichier(String nomFichier) throws IOException {
		// creates a new file if it doesnt exist, and return it
		File fichier = new File(nomFichier);
		if (!fichier.exists()) {
			fichier.createNewFile();
		}
		return fichier;
	}

}
