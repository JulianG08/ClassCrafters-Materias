package co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.impl;

import java.util.HashMap;
import java.util.Map;

import co.edu.uco.tutorspace.crosscutting.exceptions.customs.CrossCuttingTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalog;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.Mensaje;
import co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper;

public class MessageCatalogExternalService implements MessageCatalog {

	private final Map<String, Mensaje> mensajes=new HashMap<>();

	@Override
	public final void inicializar() {	
		mensajes.clear();
		mensajes.put(CodigoMensaje.M00007.getIdentificador(),new Mensaje(CodigoMensaje.M00007, "La transacción se ha completado satisfactoriamente"));
	}

	@Override
	public final String obtenerContendidoMensaje(final CodigoMensaje codigo, String... parametros) {
		return obtenerMensaje(codigo,parametros).getContendio();
	}

	@Override
	public final Mensaje obtenerMensaje(final CodigoMensaje codigo, String... parametros) {
		if(ObjectHelper.getObjectHelper().isNull(codigo)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico =  MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00001);
			throw new CrossCuttingTutorSpaceException(mensajeTecnico, mensajeUsuario);
		}
		
		if(codigo.isBase()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00005,codigo.getIdentificador());
			throw new CrossCuttingTutorSpaceException(mensajeTecnico, mensajeUsuario);
		}
		
		if(!mensajes.containsKey(codigo.getIdentificador())) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006,codigo.getIdentificador());
			throw new CrossCuttingTutorSpaceException(mensajeTecnico, mensajeUsuario);
		}
		
		return mensajes.get(codigo.getIdentificador());
	}
}
