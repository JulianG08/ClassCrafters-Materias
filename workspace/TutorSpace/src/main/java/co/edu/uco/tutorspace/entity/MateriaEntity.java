package co.edu.uco.tutorspace.entity;

import java.util.UUID;

import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.UUIDHelper;

public final class MateriaEntity {

    private UUID id;
    private String nombre;
    private String institucion;

    public MateriaEntity() {
        setId(UUIDHelper.getDefault());
        setNombre(TextHelper.EMPTY);
        setInstitucion(TextHelper.EMPTY);
    }

    public MateriaEntity(final UUID id, final String nombre, final String institucion) {
        setId(id);
        setNombre(nombre);
        setInstitucion(institucion);
    }

    public static final MateriaEntity build() {
        return new MateriaEntity();
    }

    public final UUID getId() {
        return id;
    }

    public final MateriaEntity setId(UUID id) {
        this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
        return this;
    }

    public final String getNombre() {
        return nombre;
    }

    public final MateriaEntity setNombre(String nombre) {
        this.nombre = TextHelper.applyTrim(nombre);
        return this;
    }

    public final String getInstitucion() {
        return institucion;
    }

    public final MateriaEntity setInstitucion(String institucion) {
        this.institucion = TextHelper.applyTrim(institucion);
        return this;
    }
}
