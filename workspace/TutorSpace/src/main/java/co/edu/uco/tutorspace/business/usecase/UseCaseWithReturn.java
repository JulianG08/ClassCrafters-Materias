package co.edu.uco.tutorspace.business.usecase;

public interface UseCaseWithReturn<T, R> {

	R execute(T data);
}
