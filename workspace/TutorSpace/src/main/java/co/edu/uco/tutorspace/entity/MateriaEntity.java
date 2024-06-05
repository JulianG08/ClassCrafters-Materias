package co.edu.uco.tutorspace.entity;

import co.edu.uco.tutorspace.crosscutting.helpers.NumericHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;

public final class MateriaEntity {

	private int id;
	private String nombre;
	private String institucion;
	
	private MateriaEntity(final int id) {
		setId(id);
		setNombre(TextHelper.EMPTY);
		setInstitucion(TextHelper.EMPTY);
	}
	
	private MateriaEntity(final int id, final String nombre, final String institucion) {
		setId(id);
		setNombre(nombre);
		setInstitucion(institucion);
	}
	
	public static final MateriaEntity build(final int id) {
		return new MateriaEntity(id);
	}
	
	public static final MateriaEntity build(final int id, final String nombre, final String institucion) {
		return new MateriaEntity(id, nombre, institucion);
	}
	
	protected static final MateriaEntity build() {
		return new MateriaEntity(NumericHelper.ZERO);
	}
	
	private final void setId(final int id) {
		this.id = id;
	}
	
	private final void setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}
	
	private final void setInstitucion(final String institucion) {
		this.institucion = TextHelper.applyTrim(institucion);
	}
	
	public final int getId() {
		return id;
	}
	
	public final String getNombre() {
		return nombre;
	}
	
	public final String getInstitucion() {
		return institucion;
	}
}
