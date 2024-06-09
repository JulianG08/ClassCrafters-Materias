package co.edu.uco.tutorspace.data.dao.factory.concrete;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.tutorspace.crosscutting.exceptions.customs.DataTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.crosscutting.helpers.SqlHelper;
import co.edu.uco.tutorspace.data.dao.entity.MateriaDAO;
import co.edu.uco.tutorspace.data.dao.entity.concrete.SqlConnection;
import co.edu.uco.tutorspace.data.dao.entity.concrete.postgresql.MateriaPostgreSqlDAO;
import co.edu.uco.tutorspace.data.dao.factory.DAOFactory;

public class PostgreSqlDAOFactory extends SqlConnection implements DAOFactory {

	public PostgreSqlDAOFactory() {
        super();
        abrirConexion();
    }

    private void abrirConexion() {
        final String connectionUrl = "jdbc:postgresql://localhost:5432/Zbanky?user=postgres&password=653200";
        try {
            setConexion(DriverManager.getConnection(connectionUrl));
        } catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00024);

            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025);

            throw new DataTutorSpaceException(mensajeTecnico, mensajeUsuario, excepcion);
        }
    }


    @Override
    public void cerrarConexion() {
        SqlHelper.close(getConexion());
    }

    @Override
    public void iniciarTransaccion() {
        SqlHelper.initTransaction(getConexion());
    }

    @Override
    public void confirmarTransaccion() {
        SqlHelper.commit(getConexion());
    }

    @Override
    public void cancelarTransaccion() {
        SqlHelper.rollback(getConexion());
    }

    @Override
    public MateriaDAO getMateriaDAO() {
        return new MateriaPostgreSqlDAO(getConexion());
    }
}
