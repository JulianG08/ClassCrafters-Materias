package co.edu.uco.taskpeak.dto;

import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import java.util.UUID;

public final class UsuarioDTO {
    private UUID id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;

    public UsuarioDTO() {
        setId(null);
        setNombre(TextHelper.EMPTY);
        setApellido(TextHelper.EMPTY);
        setCorreo(TextHelper.EMPTY);
        setPassword(TextHelper.EMPTY);
    }
    public UsuarioDTO(final UUID id,final String nombre,final String apellido,final String correo,final String password) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setPassword(password);
    }
    public static final UsuarioDTO build() {
        return new UsuarioDTO();
    }
    public static final UsuarioDTO build(UUID id) {
        return new UsuarioDTO(id,TextHelper.EMPTY, TextHelper.EMPTY,TextHelper.EMPTY,TextHelper.EMPTY);
    }
    public static final UsuarioDTO build(final UUID id,final String nombre,final String apellido,final String correo,final String password) {
        return new UsuarioDTO(id,nombre,apellido,correo,password);
    }
    public UsuarioDTO setId(final UUID id) {
        this.id = ObjectHelper.getObjectHelper().getDefault(id, null);
        return this;
    }
    private UsuarioDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    private UsuarioDTO setCorreo(String correo) {
        this.correo = TextHelper.applyTrim(correo);
        return this;
    }
    private UsuarioDTO setPassword(final String password) {
        this.password = TextHelper.applyTrim(password);
        return this;
    }
    private UsuarioDTO setApellido(final String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
        return this;
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public String getCorreo() {
        return correo;
    }

    public UUID getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
}
