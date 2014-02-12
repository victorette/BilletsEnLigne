package ca.ulaval.ift6003.domaine.shared;

public interface Entity<T> {

	String identifiantUnique();

	boolean memeEntiteQue(T autre);

}
