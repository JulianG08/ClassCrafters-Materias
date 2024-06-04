package co.edu.uco.taskpeak.data.dao.factory.sql.postgressql;

import co.edu.uco.taskpeak.data.dao.TareaDAO;
import co.edu.uco.taskpeak.data.dao.UsuarioDAO;
import co.edu.uco.taskpeak.data.dao.factory.DAOFactory;
import co.edu.uco.taskpeak.data.dao.sql.postgressql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresSqlDAOFactory extends DAOFactory {
    private Connection connection;

    public PostgresSqlDAOFactory() {
        obtenerConexion();
    }

    @Override
    protected void obtenerConexion() {
        try {
            String url = "jdbc:postgresql://monorail.proxy.rlwy.net:10610/railway";
            String user = "postgres";
            String password = "GLJQDLNLEXdRkHZANIMAThpXtbQxqUzz";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void iniciarTransaccion() {
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirmarTransaccion() {
        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelarTransaccion() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cerrarConexion() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new UsuarioPostgresSqlDAO(connection);
    }

    @Override
    public TareaDAO getTareaDAO() {
        return new TareaPostgresSqlDAO(connection);
    }

    @Override
    public CategoriaTareaPostgresSqlDAO getCategoriaTareaDAO() {
        return new CategoriaTareaPostgresSqlDAO(connection);
    }
    @Override
    public HistorialTareaPostgresSqlDAO getHistorialTareaDAO() {
        return new HistorialTareaPostgresSqlDAO(connection);
    }
     @Override
    public EstadoTareaPostgresSqlDAO getEstadoTareaDAO() {
        return new EstadoTareaPostgresSqlDAO(connection);
    }
    @Override
    public TareaUsuarioPostgresSqlDAO getTareaUsuarioDAO(){
        return new TareaUsuarioPostgresSqlDAO(connection);
    }

    @Override
    public RecordatorioPostgresSqlDAO getRecordatorioDAO() {
        return new RecordatorioPostgresSqlDAO(connection);
    }
}