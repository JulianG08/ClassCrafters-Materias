package co.edu.uco.tutorspace.business.usecase;

public interface UseCaseWithOutReturn<T> {

	void execute(T data);
}
