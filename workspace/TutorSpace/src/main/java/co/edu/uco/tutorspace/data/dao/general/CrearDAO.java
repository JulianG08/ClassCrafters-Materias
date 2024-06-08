package co.edu.uco.tutorspace.data.dao.general;

import java.sql.SQLException;

public interface CrearDAO<E> {
    void crear(E entidad) throws SQLException;
}
