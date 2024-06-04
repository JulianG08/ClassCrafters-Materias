package co.edu.uco.taskpeak.dto;

import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import java.util.UUID;

public final class CategoriaTareaDTO {
    private UUID id;
    private UsuarioDTO usuario;
    private String nombreCategoria;
    private String descripcion;

    public CategoriaTareaDTO() {
        setId(UUID.randomUUID());
        setUsuario(UsuarioDTO.build());
        setNombreCategoria(TextHelper.EMPTY);
        setDescripcion(TextHelper.EMPTY);
    }

    public CategoriaTareaDTO(final UUID id, final UsuarioDTO usuario, final String nombreCategoria, final String descripcion) {
        setId(id);
        setUsuario(usuario);
        setNombreCategoria(nombreCategoria);
        setDescripcion(descripcion);
    }

    public static final CategoriaTareaDTO build() {

        return new CategoriaTareaDTO();
    }

    public CategoriaTareaDTO setId(final UUID id) {
        this.id = ObjectHelper.getObjectHelper().getDefault(id, UUID.randomUUID());
        return this;
    }

    public CategoriaTareaDTO setUsuario(final UsuarioDTO usuario) {
        this.usuario = ObjectHelper.getObjectHelper().getDefault(usuario, UsuarioDTO.build());
        return this;
    }

    public CategoriaTareaDTO setNombreCategoria(final String nombreCategoria) {
        this.nombreCategoria = TextHelper.applyTrim(nombreCategoria);
        return this;
    }

    public CategoriaTareaDTO setDescripcion(final String descripcion) {
        this.descripcion = TextHelper.applyTrim(descripcion);
        return this;
    }

    public UUID getId() {
        return id;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
