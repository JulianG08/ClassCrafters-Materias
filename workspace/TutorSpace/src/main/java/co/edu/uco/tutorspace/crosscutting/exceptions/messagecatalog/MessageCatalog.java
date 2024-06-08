package co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog;

import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.Mensaje;

public interface MessageCatalog {

	void inicializar();
	
	String obtenerContendidoMensaje(final CodigoMensaje codigo , String...parametros);
	Mensaje obtenerMensaje(final CodigoMensaje codigo, String...parametros);
}
