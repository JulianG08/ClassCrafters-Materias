package co.edu.uco.taskpeak.data.dao;

import co.edu.uco.taskpeak.data.dao.general.ActualizarDAO;
import co.edu.uco.taskpeak.data.dao.general.ConsultarDAO;
import co.edu.uco.taskpeak.data.dao.general.CrearDAO;
import co.edu.uco.taskpeak.data.dao.general.EliminarDAO;
import co.edu.uco.taskpeak.entity.TareaUsuarioEntity;

public interface TareaUsuarioDAO
        extends CrearDAO<TareaUsuarioEntity>, ActualizarDAO<TareaUsuarioEntity>, EliminarDAO, ConsultarDAO<TareaUsuarioEntity> {

}
