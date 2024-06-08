package co.edu.uco.tutorspace.crosscutting.exceptions;

import co.edu.uco.tutorspace.crosscutting.exceptions.enums.Lugar;
import co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;

public class TutorSpaceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String mensajeUsuario;
    private final Lugar lugar;

    public TutorSpaceException(String mensajeTecnico, String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz) {
        super(mensajeTecnico, excepcionRaiz);
        this.mensajeUsuario = TextHelper.applyTrim(mensajeUsuario);
        this.lugar = ObjectHelper.getObjectHelper().getDefaultValue(lugar, Lugar.DEFAULT);
    }

    public TutorSpaceException(String mensajeUsuario, Lugar lugar) {
        super(mensajeUsuario);
        this.mensajeUsuario = TextHelper.applyTrim(mensajeUsuario);
        this.lugar = ObjectHelper.getObjectHelper().getDefaultValue(lugar, Lugar.DEFAULT);
    }

    public TutorSpaceException(String mensajeTecnico, String mensajeUsuario, Lugar lugar) {
        super(mensajeTecnico);
        this.mensajeUsuario = TextHelper.applyTrim(mensajeUsuario);
        this.lugar = ObjectHelper.getObjectHelper().getDefaultValue(lugar, Lugar.DEFAULT);
    }

    public String getMensajeUsuario() {
        return mensajeUsuario;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public static TutorSpaceException buildDatabaseException(String mensajeTecnico, String mensajeUsuario, Throwable excepcionRaiz) {
        return new TutorSpaceException(mensajeTecnico, mensajeUsuario, Lugar.BASE_DE_DATOS, excepcionRaiz);
    }
}
