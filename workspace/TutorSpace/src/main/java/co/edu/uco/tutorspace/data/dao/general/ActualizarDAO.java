package co.edu.uco.tutorspace.data.dao.general;

import java.sql.SQLException;

public interface ActualizarDAO<E> {
    void actualizar(E entidad) throws SQLException;
}
