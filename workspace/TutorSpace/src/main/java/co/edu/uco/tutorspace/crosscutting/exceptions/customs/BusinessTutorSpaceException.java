package co.edu.uco.tutorspace.crosscutting.exceptions.customs;

import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.enums.Lugar;

public class BusinessTutorSpaceException extends TutorSpaceException {

	private static final long serialVersionUID = 1L;
    private static final Lugar lugar = Lugar.BUSINESS;

    public BusinessTutorSpaceException(final String mensajeUsuario) {
        super(mensajeUsuario, lugar);
    }
    public BusinessTutorSpaceException(final String mensajeTecnico, final String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, lugar);
    }
    public BusinessTutorSpaceException(final String mensajeTecnico, final String mensajeUsuario, final Throwable excepcionRaiz) {
        super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
    }
}
