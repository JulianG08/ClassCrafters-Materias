package co.edu.uco.tutorspace.crosscutting.exceptions;

import co.edu.uco.tutorspace.crosscutting.exceptions.enums.Lugar;
import co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;

public class TutorSpaceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	protected String mensajeUsuario;
	protected Lugar lugar;

	public TutorSpaceException(String mensajeTecnico, String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz) {
		super(mensajeTecnico, excepcionRaiz);
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}

	public TutorSpaceException(final String mensajeUsuario, final Lugar lugar) {
		super(mensajeUsuario);
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}

	public TutorSpaceException(String mensajeTecnico, String mensajeUsuario, Lugar lugar) {
		super(mensajeTecnico);
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}

	private final void setMensajeUsuario(final String mensajeUsuario) {
		this.mensajeUsuario = TextHelper.applyTrim(mensajeUsuario);
	}

	private final void setLugar(final Lugar lugar) {
		this.lugar = ObjectHelper.getObjectHelper().getDefault(lugar, Lugar.DEFAULT);
	}

	public final String getMensajeUsuario() {
		return mensajeUsuario;
	}

	public final Lugar getLugar() {
		return lugar;
	}
}
