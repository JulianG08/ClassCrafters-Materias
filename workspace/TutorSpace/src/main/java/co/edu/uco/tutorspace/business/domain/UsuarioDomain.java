package co.edu.uco.tutorspace.business.domain;

import java.util.UUID;

import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.UUIDHelper;

public final class UsuarioDomain {
	
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

	public UsuarioDomain(final UUID id, final String nombre, String apellido, final String tipoDocumento,final long numeroDocumento, final String tipoUsuario, final String correoElectronico, final String clave, final String telefono, final String direccion, final String institucion) {
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
	
	public static final UsuarioDomain build(final UUID id, final String nombre, String apellido, final String tipoDocumento, final long numeroDocumento, final String tipoUsuario, final String correoElectronico, final String clave, final String telefono, final String direccion, final String institucion) {
		return new UsuarioDomain(id, nombre, apellido, tipoDocumento, numeroDocumento, tipoUsuario, correoElectronico, clave, telefono, direccion, institucion);
	}
	
	public static final UsuarioDomain build(final UUID id) {
		return new UsuarioDomain(id, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, 0, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY);
	}
	
	public static final UsuarioDomain build() {
		return new UsuarioDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, 0, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY, TextHelper.EMPTY);
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
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public long getNumeroDocumento() {
		return numeroDocumento;
	}
	
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	
	public String getClave() {
		return clave;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public String getInstitucion() {
		return institucion;
	}
	
	private void setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}
	
	private void setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}
	
	private void setApellido(final String apellido) {
		this.apellido = TextHelper.applyTrim(apellido);
	}
	
	private void setTipoDocumento(final String tipoDocumento) {
		this.tipoDocumento =  TextHelper.applyTrim(tipoDocumento);
	}
	
	private void setNumeroDocumento(long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
	private void setTipoUsuario(final String tipoUsuario) {
		this.tipoUsuario = TextHelper.applyTrim(tipoUsuario);
	}
	
	private void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = TextHelper.applyTrim(correoElectronico);
	}
	
	private void setClave(String clave) {
		this.clave = TextHelper.applyTrim(clave);
	}
	
	private void setTelefono(String telefono) {
		this.telefono = TextHelper.applyTrim(telefono);
	}
	
	private void setDireccion(String direccion) {
		this.direccion = TextHelper.applyTrim(direccion);
	}
	
	private void setInstitucion(String institucion) {
		this.institucion = TextHelper.applyTrim(institucion);
	}
}
