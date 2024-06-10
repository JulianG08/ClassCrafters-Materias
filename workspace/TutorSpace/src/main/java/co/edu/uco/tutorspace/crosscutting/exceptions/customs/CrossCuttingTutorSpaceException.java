package co.edu.uco.tutorspace.crosscutting.exceptions.customs;

import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.enums.Lugar;

public class CrossCuttingTutorSpaceException extends TutorSpaceException {

	private static final long serialVersionUID = 1L;
	private static final Lugar lugar = Lugar.CROSSCUTTING;

	public CrossCuttingTutorSpaceException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar);
	}
	
	public CrossCuttingTutorSpaceException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, lugar);
	}
	
	public CrossCuttingTutorSpaceException(final String mensajeTecnico, final String mensajeUsuario, final Throwable exceptionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, exceptionRaiz);
	}
}
