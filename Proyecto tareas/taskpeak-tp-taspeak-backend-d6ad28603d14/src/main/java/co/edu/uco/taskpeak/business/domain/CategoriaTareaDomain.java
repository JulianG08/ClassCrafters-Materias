package co.edu.uco.taskpeak.business.domain;

import co.edu.uco.taskpeak.dto.UsuarioDTO;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;

import java.util.UUID;

public class CategoriaTareaDomain {

    private UUID id;
    private UsuarioDTO usuario;
    private String nombreCategoria;
    private String descripcion;

    public CategoriaTareaDomain(final UUID id, final UsuarioDTO usuario, final String nombreCategoria, final String descripcion) {
        setId(id);
        setUsuario(usuario);
        setNombreCategoria(nombreCategoria);
        setDescripcion(descripcion);
    }

    public static CategoriaTareaDomain crear(final UUID id, final UsuarioDTO usuario, final String nombreCategoria, final String descripcion) {
        return new CategoriaTareaDomain(id, usuario, nombreCategoria, descripcion);
    }

    public static CategoriaTareaDomain crear() {
        return new CategoriaTareaDomain();
    }

    private CategoriaTareaDomain() {
        setNombreCategoria(TextHelper.EMPTY);
        setDescripcion(TextHelper.EMPTY);
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    private void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = TextHelper.applyTrim(nombreCategoria);
    }

    private void setDescripcion(String descripcion) {
        this.descripcion = TextHelper.applyTrim(descripcion);
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
