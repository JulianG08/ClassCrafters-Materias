package co.edu.uco.tutorspace.crosscutting.exceptions.customs;

import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.enums.Lugar;

public class ControllerTutorSpaceException extends TutorSpaceException {

	private static final long serialVersionUID = 1L;
    private static final Lugar lugar = Lugar.CONTROLLER;

    public ControllerTutorSpaceException(final String mensajeUsuario) {
        super(mensajeUsuario, lugar);
    }

    public ControllerTutorSpaceException(final String mensajeTecnico, final String mensajeUsuario) {
        super(mensajeTecnico, mensajeUsuario, lugar);
    }

    public ControllerTutorSpaceException(final String mensajeTecnico, final String mensajeUsuario, final Throwable excepcionRaiz) {
        super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
    }
}
