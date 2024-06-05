package co.edu.uco.tutorspace.dto;

import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;

public final class MateriaDTO {

	private int id;
	private String nombre;
	private String institucion;
	
	public MateriaDTO() {
		setNombre(TextHelper.EMPTY);
		setInstitucion(TextHelper.EMPTY);
	}
	
	public MateriaDTO(final int id, final String nombre, final String institucion) {
		setId(id);
		setNombre(nombre);
		setInstitucion(institucion);
	}
	
	public static final MateriaDTO build() {
		return new MateriaDTO();
	}
	
	public final int getId() {
		return id;
	}
	
	public final MateriaDTO setId(final int id) {
		this.id = id;
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
