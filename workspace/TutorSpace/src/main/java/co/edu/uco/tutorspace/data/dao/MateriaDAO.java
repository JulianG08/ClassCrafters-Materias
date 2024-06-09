package co.edu.uco.tutorspace.data.dao;

import java.util.UUID;

import co.edu.uco.tutorspace.entity.MateriaEntity;

public interface MateriaDAO extends CrearDAO<MateriaEntity>, ActualizarDAO<MateriaEntity>, EliminarDAO<UUID>, ConsultarDAO<MateriaEntity> {
	
}
