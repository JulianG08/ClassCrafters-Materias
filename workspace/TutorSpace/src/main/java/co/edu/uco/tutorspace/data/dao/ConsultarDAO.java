package co.edu.uco.tutorspace.data.dao;

import java.util.List;

public interface ConsultarDAO <E> {

	List <E> consultar (E data);
}
