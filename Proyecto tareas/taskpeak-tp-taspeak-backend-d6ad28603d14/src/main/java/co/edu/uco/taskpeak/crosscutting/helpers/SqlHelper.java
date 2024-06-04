package co.edu.uco.taskpeak.crosscutting.helpers;

import co.edu.uco.taskpeak.crosscutting.exceptions.custom.CrosscuttingTaskPeakException;
import co.edu.uco.taskpeak.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.taskpeak.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;

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
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = "";
            throw new CrosscuttingTaskPeakException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static void close(final Connection connection) {
        try {
            if (!isOpen(connection)) {
                return;
            }
            connection.close();
        } catch (final SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
            var mensajeTecnico = "";
            throw new CrosscuttingTaskPeakException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static void startTransaction(final Connection connection) {
        try {
            if (isOpen(connection)) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00003);
            var mensajeTecnico = "";
            throw new CrosscuttingTaskPeakException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static void commit(final Connection connection) {
        try {
            if (isOpen(connection)) {
                connection.commit();
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00004);
            var mensajeTecnico = "";
            throw new CrosscuttingTaskPeakException(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    public static void rollback(final Connection connection) {
        try {
            if (isOpen(connection)) {
                connection.rollback();
            }
        } catch (SQLException exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00005);
            var mensajeTecnico = "";
            throw new CrosscuttingTaskPeakException(mensajeTecnico, mensajeUsuario, exception);
        }
    }
}
