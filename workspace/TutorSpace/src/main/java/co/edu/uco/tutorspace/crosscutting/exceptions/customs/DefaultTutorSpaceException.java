package co.edu.uco.tutorspace.crosscutting.exceptions.customs;

import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.enums.Lugar;

public class DefaultTutorSpaceException extends TutorSpaceException {

	private static final long serialVersionUID = -3662331984905572117L;
    private static final Lugar lugar = Lugar.DEFAULT;

    public DefaultTutorSpaceException(final String mensajeUsuario , Lugar lugar) {
        super(mensajeUsuario, lugar);
    }

    public DefaultTutorSpaceException(final String mensajeUsuario,final String mensajeTecnico) {
        super(mensajeTecnico, mensajeUsuario, lugar);
    }

    public DefaultTutorSpaceException(final String mensajeTecnico,final String mensajeUsuario, final Throwable exceptionRaiz) {
        super(mensajeTecnico,mensajeUsuario, lugar, exceptionRaiz);
    }
}
