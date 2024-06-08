package co.edu.uco.tutorspace.entity;

import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;

public final class MateriaEntity {

    private int id;
    private String nombre;
    private String institucion;

    private MateriaEntity(int id, String nombre, String institucion) {
        setId(id);
        setNombre(nombre);
        setInstitucion(institucion);
    }

    private MateriaEntity(int id) {
        this(id, TextHelper.EMPTY, TextHelper.EMPTY);
    }

    public static MateriaEntity build(int id, String nombre, String institucion) {
        return new MateriaEntity(id, nombre, institucion);
    }

    public static MateriaEntity build(int id) {
        return new MateriaEntity(id);
    }

    public static MateriaEntity build() {
        return new MateriaEntity(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = TextHelper.applyTrim(institucion);
    }
}
