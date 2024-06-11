package co.edu.uco.tutorspace.business.facade;

public interface FacadeWithReturn<T, R> {
    R execute(T dto);
}