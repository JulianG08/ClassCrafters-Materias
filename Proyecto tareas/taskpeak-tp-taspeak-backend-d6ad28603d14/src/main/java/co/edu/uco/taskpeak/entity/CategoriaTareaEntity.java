package co.edu.uco.taskpeak.entity;

import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;

import java.util.UUID;

public final class CategoriaTareaEntity {
    private UUID id;
    private UsuarioEntity usuario;
    private String nombreCategoria;
    private String descripcion;

    private CategoriaTareaEntity(final UUID id) {
        setId(id);
        setUsuario(UsuarioEntity.build());
        setNombreCategoria(TextHelper.EMPTY);
        setDescripcion(TextHelper.EMPTY);
    }

    private CategoriaTareaEntity(final UUID id, final UsuarioEntity usuario, final String nombreCategoria, final String descripcion) {
        setId(id);
        setUsuario(usuario);
        setNombreCategoria(nombreCategoria);
        setDescripcion(descripcion);
    }
    public static final CategoriaTareaEntity build(final UUID id) {
        return new CategoriaTareaEntity(id);
    }

    public static final CategoriaTareaEntity build(final UUID id, final UsuarioEntity usuario, final String nombreCategoria, final String descripcion) {
        return new CategoriaTareaEntity(id, usuario, nombreCategoria, descripcion);
    }

    protected static final CategoriaTareaEntity build() {
        return new CategoriaTareaEntity(UUID.randomUUID());
    }

    private final void setId(final UUID id) {
        this.id = ObjectHelper.getObjectHelper().getDefault(id, UUID.randomUUID());
    }

    private final void setUsuario(final UsuarioEntity usuario) {
        this.usuario = ObjectHelper.getObjectHelper().getDefault(usuario, UsuarioEntity.build());
    }

    private final void setNombreCategoria(final String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    private final void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public UUID getId() {
        return id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
