package co.edu.uco.taskpeak.data.dao;

import co.edu.uco.taskpeak.data.dao.general.ActualizarDAO;
import co.edu.uco.taskpeak.data.dao.general.ConsultarDAO;
import co.edu.uco.taskpeak.data.dao.general.CrearDAO;
import co.edu.uco.taskpeak.data.dao.general.EliminarDAO;
import co.edu.uco.taskpeak.entity.UsuarioEntity;

import java.util.UUID;

public interface UsuarioDAO
        extends CrearDAO<UsuarioEntity>, ActualizarDAO<UsuarioEntity>, EliminarDAO, ConsultarDAO<UsuarioEntity> {
    boolean exists(UUID id);
    void create(UsuarioEntity usuario);
}
