package co.edu.uco.tutorspace.data.dao.general;

import java.sql.SQLException;
import java.util.List;

public interface ConsultarDAO<E> {
    List<E> consultar(E entidad) throws SQLException;
}
