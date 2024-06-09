package co.edu.uco.tutorspace.data.dao.concrete;

import java.sql.Connection;

import co.edu.uco.tutorspace.crosscutting.exceptions.customs.DataTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.crosscutting.helpers.SqlHelper;

public class SqlConnection {

	private Connection conexion;

	protected SqlConnection(final Connection conexion) {
		setConexion(conexion);
	}
	
	protected SqlConnection() {
		super();
	}
	protected final Connection getConexion() {
		return conexion;
	}

	protected final void setConexion(final Connection conexion) {
		if (!SqlHelper.isOpen(conexion)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00026);
			throw new DataTutorSpaceException(mensajeTecnico,mensajeUsuario);
		}
		this.conexion = conexion;
	}
}
