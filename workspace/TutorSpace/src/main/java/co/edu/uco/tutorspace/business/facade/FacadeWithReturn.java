package co.edu.uco.tutorspace.business.facade;

public interface FacadeWithReturn <T, K> {

	K execute(T dto);
}
