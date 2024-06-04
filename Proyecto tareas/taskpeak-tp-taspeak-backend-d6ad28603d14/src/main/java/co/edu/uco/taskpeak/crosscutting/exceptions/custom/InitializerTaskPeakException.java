package co.edu.uco.taskpeak.crosscutting.exceptions.custom;

import co.edu.uco.taskpeak.crosscutting.exceptions.TaskPeakException;
import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Lugar;

public final class InitializerTaskPeakException extends TaskPeakException {

	private static final long serialVersionUID = 1L;
	private static final Lugar lugar = Lugar.INITIALIZER;

	public InitializerTaskPeakException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar);
	}

	public InitializerTaskPeakException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeTecnico, mensajeUsuario, lugar);
	}

	public InitializerTaskPeakException(final String mensajeTecnico, final String mensajeUsuario,
										final Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}
}