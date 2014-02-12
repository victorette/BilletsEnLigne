package ca.ulaval.ift6003.domaine.modele.inventaire;

import java.io.Serializable;

import ca.ulaval.ift6003.domaine.shared.Entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("billetUnMatch")
public class BilletUnMatch extends Billet implements Serializable, Entity<BilletUnMatch>, Cloneable {

	private static final long serialVersionUID = 1L;

	private String match_id;

	public BilletUnMatch() {
		this.billetId = "";
		this.match_id = "";
		this.nomSection = "";
		this.nomSiege = "";
		this.categorie_siege = "";
		this.prix = 0.00;
	}

	public BilletUnMatch(String billetID, String match_id, String nomSection, String nomSiege, String categorie_siege, double prix) {
		this.billetId = billetID;
		this.match_id = match_id;
		this.nomSection = nomSection;
		this.nomSiege = nomSiege;
		this.categorie_siege = categorie_siege;
		this.prix = prix;
	}

	public String getMatch_id() {
		return match_id;
	}

	public void setMatch_id(String match_id) {
		this.match_id = match_id;
	}

	@Override
	public String toString() {
		return ("Billet " + billetId + " Match " + match_id + " " + nomSection + " Siege " + nomSiege + " Catégorie Siège " + categorie_siege + " Prix " + prix);

	}

	@Override
	public BilletUnMatch clone() {
		BilletUnMatch b = new BilletUnMatch();

		b.setCategorie_siege(categorie_siege);
		b.setMatch_id(match_id);
		b.setNomSection(nomSection);
		b.setNomSiege(nomSiege);
		b.setPrix(prix);

		return b;
	}

	@Override
	public String getType() {
		return categorie_siege + Double.toString(prix) + match_id;
	}

	@Override
	public boolean memeEntiteQue(BilletUnMatch autre) {
		return this.getBilletId().equals(autre.getBilletId());
	}

	@Override
	public String identifiantUnique() {
		return billetId;
	}

}
