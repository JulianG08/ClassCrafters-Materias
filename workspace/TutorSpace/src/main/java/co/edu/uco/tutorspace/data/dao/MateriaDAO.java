package co.edu.uco.tutorspace.data.dao;

import co.edu.uco.tutorspace.data.dao.general.ActualizarDAO;
import co.edu.uco.tutorspace.data.dao.general.ConsultarDAO;
import co.edu.uco.tutorspace.data.dao.general.CrearDAO;
import co.edu.uco.tutorspace.data.dao.general.EliminarDAO;
import co.edu.uco.tutorspace.entity.MateriaEntity;
import java.sql.SQLException;

public interface MateriaDAO extends CrearDAO<MateriaEntity>, ActualizarDAO<MateriaEntity>, EliminarDAO, ConsultarDAO<MateriaEntity> {
    boolean existsByNameAndInstitucion(String nombre, String institucion) throws SQLException;
}
