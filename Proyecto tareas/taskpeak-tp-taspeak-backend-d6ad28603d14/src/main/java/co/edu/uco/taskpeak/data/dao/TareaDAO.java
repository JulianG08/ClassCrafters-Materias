package co.edu.uco.taskpeak.data.dao;

import co.edu.uco.taskpeak.data.dao.general.ActualizarDAO;
import co.edu.uco.taskpeak.data.dao.general.ConsultarDAO;
import co.edu.uco.taskpeak.data.dao.general.CrearDAO;
import co.edu.uco.taskpeak.data.dao.general.EliminarDAO;
import co.edu.uco.taskpeak.dto.TareaDTO;
import co.edu.uco.taskpeak.entity.TareaEntity;

import java.util.List;
import java.util.UUID;

public interface TareaDAO
        extends CrearDAO<TareaEntity>, ActualizarDAO<TareaEntity>, EliminarDAO, ConsultarDAO<TareaEntity> {
    List<TareaDTO> buscarPorUsuario(UUID usuarioId);

}
