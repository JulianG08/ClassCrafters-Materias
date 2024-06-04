package co.edu.uco.taskpeak.business.useCase.concrete;

import co.edu.uco.taskpeak.business.domain.UsuarioDomain;
import co.edu.uco.taskpeak.business.useCase.CrearUsuarios;
import co.edu.uco.taskpeak.crosscutting.exceptions.TaskPeakException;
import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Lugar;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.data.dao.factory.DAOFactory;
import co.edu.uco.taskpeak.entity.UsuarioEntity;

public class CrearUsuariosImpl implements CrearUsuarios {

    private final DAOFactory factory;

    public CrearUsuariosImpl(DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public void ejecutar(UsuarioDomain usuario) {
        if (!esUsuarioRegistradoEnWeb(usuario)) {
            throw new TaskPeakException("El usuario no esta registrado en la web.", Lugar.BUSINESS);
        }

        if (!cumpleReglasDeDatos(usuario)) {
            throw new TaskPeakException("Los datos no cumplen las reglas de obligatoriedad, formato, longitud y rango.", Lugar.BUSINESS);
        }

        if (existeTareaConMismoNombreYUsuarioAsignado(usuario)) {
            throw new TaskPeakException("Ya existe una tarea con el mismo nombre y usuario asignado.", Lugar.BUSINESS);
        }

        if (!esDescripcionClaraYCategoriaDefinida(usuario)) {
            throw new TaskPeakException("La tarea debe tener una descripcion clara y categoría definida.", Lugar.BUSINESS);
        }

        registrarUsuario(usuario);
    }

    private boolean esUsuarioRegistradoEnWeb(UsuarioDomain usuario) {
        return factory.getUsuarioDAO().exists(usuario.getId());
    }

    private boolean cumpleReglasDeDatos(UsuarioDomain usuario) {
        return !TextHelper.isNullOrEmpty(usuario.getNombre())
                && !TextHelper.isNullOrEmpty(usuario.getApellido())
                && !TextHelper.isNullOrEmpty(usuario.getCorreo())
                && !TextHelper.isNullOrEmpty(usuario.getPassword());
    }

    private boolean existeTareaConMismoNombreYUsuarioAsignado(UsuarioDomain usuario) {
        return factory.getTareaDAO().buscarPorUsuario(usuario.getId()).stream()
                .anyMatch(tarea -> tarea.getNombreTarea().equals(usuario.getNombre()));
    }

    private boolean esDescripcionClaraYCategoriaDefinida(UsuarioDomain usuario) {
        return factory.getTareaDAO().buscarPorUsuario(usuario.getId()).stream()
                .allMatch(tarea -> !TextHelper.isEmpty(tarea.getDescripcionTarea()) && tarea.getCategoria() != null);
    }

    private void registrarUsuario(UsuarioDomain usuario) {
        UsuarioEntity usuarioEntity = UsuarioEntity.build(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getCorreo(), usuario.getPassword());
        factory.getUsuarioDAO().create(usuarioEntity);
    }
}
