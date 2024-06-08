package co.edu.uco.tutorspace.business.domain;

import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;

public class MateriaDomain {

    private int id;
    private String nombre;
    private String institucion;

    private MateriaDomain(int id, String nombre, String institucion) {
        setId(id);
        setNombre(nombre);
        setInstitucion(institucion);
    }

    public static MateriaDomain create(int id, String nombre, String institucion) {
        return new MateriaDomain(id, nombre, institucion);
    }

    public static MateriaDomain create() {
        return new MateriaDomain(0, TextHelper.EMPTY, TextHelper.EMPTY);
    }

    public int getId() {
        return id;
    }

    public MateriaDomain setId(int id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public MateriaDomain setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public String getInstitucion() {
        return institucion;
    }

    public MateriaDomain setInstitucion(String institucion) {
        this.institucion = TextHelper.applyTrim(institucion);
        return this;
    }
}
