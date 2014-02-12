package ca.ulaval.ift6003.domaine.shared;

public abstract class AbstractSpecification<T> implements Specification<T> {

	@Override
	public abstract boolean estSatisfaitePar(T t);

	@Override
	public Specification<T> and(final Specification<T> specification) {
		return new AndSpecification<T>(this, specification);
	}

	@Override
	public Specification<T> or(final Specification<T> specification) {
		return new OrSpecification<T>(this, specification);
	}

	@Override
	public Specification<T> not(final Specification<T> specification) {
		return new NotSpecification<T>(specification);
	}
}
