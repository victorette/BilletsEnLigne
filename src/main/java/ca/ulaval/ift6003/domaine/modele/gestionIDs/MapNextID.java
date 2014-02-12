package ca.ulaval.ift6003.domaine.modele.gestionIDs;

import ca.ulaval.ift6003.domaine.shared.ValueObject;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("mapnextid")
public class MapNextID implements ValueObject<MapNextID> {

	private int nextBilletId;
	private int nextMatchId;
	private int nextTransactionId;

	public MapNextID() {

	}

	public int getNextIdPourEntite(String nomEntite) {
		if (nomEntite.equals("match")) {
			return nextMatchId;
		} else if (nomEntite.equals("billet")) {
			return nextBilletId;
		} else {
			return nextTransactionId;
		}

	}

	public void setNextIdPourEntite(String nomEntite, int nextId) {
		if (nomEntite.equals("match")) {
			nextMatchId = nextId;
		} else if (nomEntite.equals("billet")) {
			nextBilletId = nextId;
		} else {
			nextTransactionId = nextId;
		}
	}

	public int getNextBilletId() {
		return nextBilletId;
	}

	public void setNextBilletId(int nextBilletId) {
		this.nextBilletId = nextBilletId;
	}

	public int getNextMatchId() {
		return nextMatchId;
	}

	public void setNextMatchId(int nextMatchId) {
		this.nextMatchId = nextMatchId;
	}

	@Override
	public boolean memeValeurQue(MapNextID autre) {
		return nextBilletId == autre.getNextBilletId() && nextMatchId == autre.getNextMatchId() && nextTransactionId == autre.getNextTransactionId();
	}

	public int getNextTransactionId() {
		return nextTransactionId;
	}

	public void setNextTransactionId(int nextTransactionId) {
		this.nextTransactionId = nextTransactionId;
	}

}
