package co.edu.uco.taskpeak.data.dao;

import co.edu.uco.taskpeak.data.dao.general.*;
import co.edu.uco.taskpeak.entity.EstadoTareaEntity;

public interface EstadoTareaDAO
    extends CrearDAO<EstadoTareaEntity>, ActualizarDAO<EstadoTareaEntity>, EliminarIntDAO, ConsultarDAO<EstadoTareaEntity>{

}
