package co.edu.uco.taskpeak.entity;

import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import java.util.UUID;

public final class UsuarioEntity {
    private UUID id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;

    public UsuarioEntity(final UUID id, final String nombre, final String apellido, final String correo, final String password) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
        setPassword(password);
    }
    protected static final UsuarioEntity build() {
        return new UsuarioEntity(null);
    }
    public static final UsuarioEntity build(final UUID id) {
        return new UsuarioEntity(id);
    }

    public static UsuarioEntity build(UUID id, String nombre, String apellido, String correo, String password) {
        return new UsuarioEntity(id, nombre, apellido, correo, password);
    }

    private final void setId(final UUID id) {
        this.id = ObjectHelper.getObjectHelper().getDefault(id, null);
    }

    private final void setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    private final void setApellido(final String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
    }

    private final void setCorreo(final String correo) {
        this.correo = TextHelper.applyTrim(correo);
    }

    private final void setPassword(final String password) {
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
