package co.edu.uco.tutorspace.dto;

import java.util.UUID;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.UUIDHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper;

public final class UsuarioDTO {

    private UUID id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private Long numeroDocumento;
    private String tipoUsuario;
    private String correoElectronico;
    private String clave;
    private String telefono;
    private String direccion;
    private String institucion;

    public UsuarioDTO() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
        setApellido(TextHelper.EMPTY);
        setTipoDocumento(TextHelper.EMPTY);
        setNumeroDocumento(0L);
        setTipoUsuario(TextHelper.EMPTY);
        setCorreoElectronico(TextHelper.EMPTY);
        setClave(TextHelper.EMPTY);
        setTelefono(TextHelper.EMPTY);
        setDireccion(TextHelper.EMPTY);
        setInstitucion(TextHelper.EMPTY);
    }

    public UsuarioDTO(final UUID id, final String nombre, final String apellido, final String tipoDocumento,
                      final Long numeroDocumento, final String tipoUsuario, final String correoElectronico,
                      final String clave, final String telefono, final String direccion, final String institucion) {
        setId(id);
        setNombre(nombre);
        setApellido(apellido);
        setTipoDocumento(tipoDocumento);
        setNumeroDocumento(numeroDocumento);
        setTipoUsuario(tipoUsuario);
        setCorreoElectronico(correoElectronico);
        setClave(clave);
        setTelefono(telefono);
        setDireccion(direccion);
        setInstitucion(institucion);
    }

    public static UsuarioDTO build() {
        return new UsuarioDTO();
    }

    public UUID getId() {
        return id;
    }

    public UsuarioDTO setId(final UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public UsuarioDTO setNombre(final String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public String getApellido() {
        return apellido;
    }

    public UsuarioDTO setApellido(final String apellido) {
        this.apellido = TextHelper.applyTrim(apellido);
        return this;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public UsuarioDTO setTipoDocumento(final String tipoDocumento) {
        this.tipoDocumento = TextHelper.applyTrim(tipoDocumento);
        return this;
    }

    public Long getNumeroDocumento() {
        return numeroDocumento;
    }

    public UsuarioDTO setNumeroDocumento(final Long numeroDocumento) {
        this.numeroDocumento = ObjectHelper.getObjectHelper().getDefaultValue(numeroDocumento, 0L);
        return this;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public UsuarioDTO setTipoUsuario(final String tipoUsuario) {
        this.tipoUsuario = TextHelper.applyTrim(tipoUsuario);
        return this;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public UsuarioDTO setCorreoElectronico(final String correoElectronico) {
        this.correoElectronico = TextHelper.applyTrim(correoElectronico);
        return this;
    }

    public String getClave() {
        return clave;
    }

    public UsuarioDTO setClave(final String clave) {
        this.clave = TextHelper.applyTrim(clave);
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public UsuarioDTO setTelefono(final String telefono) {
        this.telefono = TextHelper.applyTrim(telefono);
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public UsuarioDTO setDireccion(final String direccion) {
        this.direccion = TextHelper.applyTrim(direccion);
        return this;
    }

    public String getInstitucion() {
        return institucion;
    }

    public UsuarioDTO setInstitucion(final String institucion) {
        this.institucion = TextHelper.applyTrim(institucion);
        return this;
    }
}