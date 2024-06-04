package co.edu.uco.taskpeak.business.domain;

import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;

import java.util.UUID;

public class UsuarioDomain {
    private UUID id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;

    public UsuarioDomain(final UUID id, final String nombre, final String apellido, final String correo, final String password) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setPassword(password);
    }

    public static UsuarioDomain crear(final UUID id, final String nombre, final String apellido, final String correo, final String password) {
        return new UsuarioDomain(id, nombre, apellido, correo, password);
    }

    public static UsuarioDomain crear() {
        return new UsuarioDomain();
    }

    private UsuarioDomain() {
        setNombre(TextHelper.EMPTY);
        setApellido(TextHelper.EMPTY);
        setCorreo(TextHelper.EMPTY);
        setPassword(TextHelper.EMPTY);
        setId(null);
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    private void setApellido(String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
    }

    private void setCorreo(String correo) {
        this.correo = TextHelper.applyTrim(correo);
    }

    private void setPassword(String password) {
        this.password = TextHelper.applyTrim(password);
    }

    public UUID getId() {
        return id;
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

    public String getPassword() {
        return password;
    }
}
