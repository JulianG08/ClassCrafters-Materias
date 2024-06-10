package co.edu.uco.tutorspace.data.dao.factory;

import co.edu.uco.tutorspace.data.dao.entity.UsuarioDAO;
import co.edu.uco.tutorspace.data.dao.entity.MateriaDAO;
import co.edu.uco.tutorspace.data.dao.factory.concrete.PostgreSqlDAOFactory;

public interface DAOFactory {
    static DAOFactory getFactory() {
        return new PostgreSqlDAOFactory();
    }

    void cerrarConexion();

    void iniciarTransaccion();

    void confirmarTransaccion();

    void cancelarTransaccion();

    UsuarioDAO getUsuarioDAO();

    MateriaDAO getMateriaDAO();

  
}

