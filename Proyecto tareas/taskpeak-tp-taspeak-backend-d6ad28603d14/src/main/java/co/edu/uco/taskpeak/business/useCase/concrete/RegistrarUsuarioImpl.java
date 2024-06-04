package co.edu.uco.taskpeak.business.useCase.concrete;

import co.edu.uco.taskpeak.business.domain.UsuarioDomain;
import co.edu.uco.taskpeak.crosscutting.exceptions.TaskPeakException;
import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Lugar;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.data.dao.factory.DAOFactory;
import co.edu.uco.taskpeak.dto.TareaDTO;
import co.edu.uco.taskpeak.entity.UsuarioEntity;

import java.util.List;

public class RegistrarUsuarioImpl {

    private final DAOFactory factory;

    public RegistrarUsuarioImpl(DAOFactory factory) {
        this.factory = factory;
    }

    public void ejecutar(UsuarioDomain usuario) {
        try {
            if (!isUsuarioRegistrado(usuario)) {
                throw new TaskPeakException("El usuario no esta registrado en la web.", "El usuario debe estar registrado en la web.", Lugar.BUSINESS);
            }

            validarDatosUsuario(usuario);

            if (existeTareaConMismoNombre(usuario)) {
                throw new TaskPeakException("Ya existe una tarea con el mismo nombre para este usuario.", "No deben existir tareas con el mismo nombre y usuario asignado.", Lugar.BUSINESS);
            }

            if (!esDescripcionClaraYCategoriaDefinida(usuario)) {
                throw new TaskPeakException("La tarea debe tener una descripcion clara y una categoria definida.", "La tarea debe tener una descripcion clara y categoria tarea definidos.", Lugar.BUSINESS);
            }

            registrarUsuario(usuario);

        } catch (Exception e) {
            throw new TaskPeakException("Error al registrar el usuario.", "Ocurrio un error al registrar el usuario.", Lugar.BUSINESS, e);
        }
    }

    private boolean isUsuarioRegistrado(UsuarioDomain usuario) {
        return factory.getUsuarioDAO().exists(usuario.getId());
    }

    private void validarDatosUsuario(UsuarioDomain usuario) {
        if (TextHelper.isEmpty(usuario.getNombre()) || TextHelper.isEmpty(usuario.getApellido()) ||
                TextHelper.isEmpty(usuario.getCorreo()) || TextHelper.isEmpty(usuario.getPassword())) {
            throw new TaskPeakException("Todos los campos son obligatorios.", "Los datos deben cumplir las reglas de obligatoriedad.", Lugar.BUSINESS);
        }

        if (!TextHelper.isValidEmail(usuario.getCorreo())) {
            throw new TaskPeakException("El formato del correo no es valido.", "El correo debe tener un formato valido.", Lugar.BUSINESS);
        }

        if (!TextHelper.isValidPasswordLength(usuario.getPassword())) {
            throw new TaskPeakException("La contraseña debe tener al menos 8 caracteres.", "La contraseña debe cumplir con la longitud minima.", Lugar.BUSINESS);
        }
    }

    private boolean existeTareaConMismoNombre(UsuarioDomain usuario) {
        List<TareaDTO> tareas = factory.getTareaDAO().buscarPorUsuario(usuario.getId());
        for (TareaDTO tarea : tareas) {
            if (tarea.getNombreTarea().equalsIgnoreCase(usuario.getNombre())) {
                return true;
            }
        }
        return false;
    }

    private boolean esDescripcionClaraYCategoriaDefinida(UsuarioDomain usuario) {
        List<TareaDTO> tareas = factory.getTareaDAO().buscarPorUsuario(usuario.getId());
        for (TareaDTO tarea : tareas) {
            if (TextHelper.isEmpty(tarea.getDescripcionTarea()) || tarea.getCategoria() == null) {
                return false;
            }
        }
        return true;
    }

    private void registrarUsuario(UsuarioDomain usuario) {
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getPassword());
        factory.getUsuarioDAO().create(usuarioEntity);
    }
}
