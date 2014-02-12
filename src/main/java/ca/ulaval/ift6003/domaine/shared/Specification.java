package ca.ulaval.ift6003.domaine.shared;

public interface Specification<T> {

	boolean estSatisfaitePar(T t);

	Specification<T> and(Specification<T> specification);

	Specification<T> or(Specification<T> specification);

	Specification<T> not(Specification<T> specification);

}
