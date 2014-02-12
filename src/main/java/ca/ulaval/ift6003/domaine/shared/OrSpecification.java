package ca.ulaval.ift6003.domaine.shared;

public class OrSpecification<T> extends AbstractSpecification<T> {

	private Specification<T> spec1;
	private Specification<T> spec2;

	public OrSpecification(final Specification<T> spec1, final Specification<T> spec2) {
		this.spec1 = spec1;
		this.spec2 = spec2;
	}

	@Override
	public boolean estSatisfaitePar(final T t) {
		return spec1.estSatisfaitePar(t) || spec2.estSatisfaitePar(t);
	}
}
