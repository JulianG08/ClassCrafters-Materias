package co.edu.uco.tutorspace.business.domain;

import java.util.UUID;

import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.UUIDHelper;

public final class MateriaDomain {

    private UUID id;
    private String nombre;
    private String institucion;

    public MateriaDomain(final UUID id, final String nombre, String institucion) {
		setId(id);
		setNombre(nombre);
		setInstitucion(institucion);
    }

	public static final MateriaDomain build(final UUID id, final String nombre, String institucion) {
		return new MateriaDomain(id, nombre, institucion);
	}
	
	public static final MateriaDomain build(final UUID id) {
		return new MateriaDomain(id, TextHelper.EMPTY, TextHelper.EMPTY);
	}
	
	public static final MateriaDomain build() {
		return new MateriaDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, TextHelper.EMPTY);
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
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
	
	private void setInstitucion(final String institucion) {
		this.institucion = TextHelper.applyTrim(institucion);
	}
}
