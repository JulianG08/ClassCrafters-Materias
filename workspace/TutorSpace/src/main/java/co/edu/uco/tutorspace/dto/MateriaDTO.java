package co.edu.uco.tutorspace.dto;

import java.util.UUID;

import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.UUIDHelper;

public final class MateriaDTO {

	private UUID id;
	private String nombre;
	private String institucion;
	
	public MateriaDTO() {
		setNombre(TextHelper.EMPTY);
		setInstitucion(TextHelper.EMPTY);
	}
	
	public MateriaDTO(final UUID id, final String nombre, final String institucion) {
		setId(id);
		setNombre(nombre);
		setInstitucion(institucion);
	}
	
	public static final MateriaDTO build() {
		return new MateriaDTO();
	}
	
	public final UUID getId() {
		return id;
	}
	
	public final MateriaDTO setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
		return this;
	}
	
	public final String getNombre() {
		return nombre;
	}
	
	public final MateriaDTO setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
	public final String getInstitucion() {
		return institucion;
	}
	
	public final MateriaDTO setInstitucion(final String institucion) {
		this.institucion = TextHelper.applyTrim(institucion);
		return this;
	}
}
