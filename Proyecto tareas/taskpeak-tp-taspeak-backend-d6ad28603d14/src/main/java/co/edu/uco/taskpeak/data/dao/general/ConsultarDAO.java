package co.edu.uco.taskpeak.data.dao.general;

import java.util.List;

public interface ConsultarDAO<E> {
	List<E> consultar(E entidad);
}
