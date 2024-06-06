package co.edu.uco.tutorspace.crosscutting.helpers;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlHelper {

	private SqlHelper() {
		super();
	}
	
	public static boolean isNull(final Connection connection) {
		return ObjectHelper.getObjectHelper().isNull(connection);
	}
	
	public static boolean isOpen(final Connection connection) {
		try {
			return !isNull(connection) && !connection.isClosed();
		} catch (final SQLException exception) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.H00002);
			var mensajeTecnico = "";
			throw new Crosscutting
		}
	}
}
