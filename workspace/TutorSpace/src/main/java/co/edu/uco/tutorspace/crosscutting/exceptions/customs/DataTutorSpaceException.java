package co.edu.uco.tutorspace.crosscutting.exceptions.customs;

import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.enums.Lugar;

public class DataTutorSpaceException extends TutorSpaceException {

	private static final long serialVersionUID = 1L;
	private static final Lugar lugar = Lugar.DATA;

	public DataTutorSpaceException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar );
	}
	
	public DataTutorSpaceException(final String mensajeUsuario, final String mensajeTecnico) {
		super(mensajeTecnico, mensajeUsuario, lugar);
	}
	
	public DataTutorSpaceException(final String mensajeTecnico, final String mensajeUsuario, final Throwable exceptionRaiz) {
		super(mensajeTecnico,mensajeUsuario, lugar, exceptionRaiz);
	}
}
