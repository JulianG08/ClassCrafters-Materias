package co.edu.uco.tutorspace.data.dao.sql;

import co.edu.uco.tutorspace.crosscutting.helpers.SqlHelper;

import java.sql.Connection;

public class SqlConnection {

	private Connection connection;

	protected SqlConnection(final Connection connection) {
		if (connection == null) {
			throw new NullPointerException("The connection is null");
		}
		this.connection = connection;
	}

	protected Connection getConnection() {
		return connection;
	}

	private void setConnection(final Connection connection) {
		if (SqlHelper.isOpen(connection)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = "No es posible crear el DAO deseado, dado que la conexion SQL esta cerrada";
			throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario);
		}
		this.connection = connection;
	}
}
