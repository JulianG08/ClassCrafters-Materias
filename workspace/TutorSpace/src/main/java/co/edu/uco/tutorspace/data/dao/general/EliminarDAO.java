package co.edu.uco.tutorspace.data.dao.general;

import java.sql.SQLException;

public interface EliminarDAO {
    void eliminar(int id) throws SQLException;
}
