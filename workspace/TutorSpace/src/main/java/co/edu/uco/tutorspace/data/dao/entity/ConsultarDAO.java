package co.edu.uco.tutorspace.data.dao.entity;

import java.util.List;

public interface ConsultarDAO <E> {

	List <E> consultar (E data);
}
