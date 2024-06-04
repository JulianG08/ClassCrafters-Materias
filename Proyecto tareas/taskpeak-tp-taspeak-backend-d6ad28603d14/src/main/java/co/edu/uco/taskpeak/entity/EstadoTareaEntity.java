package co.edu.uco.taskpeak.entity;

import co.edu.uco.taskpeak.crosscutting.helpers.NumericHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;

import java.util.UUID;

public class EstadoTareaEntity {
    private int id;
    private String nombreEstado;

    private EstadoTareaEntity(final int id) {
        setId(id);
        setNombreEstado(TextHelper.EMPTY);
    }

    private EstadoTareaEntity(final int id, final String nombreEstado) {
        setId(id);
        setNombreEstado(nombreEstado);
    }
    public static final EstadoTareaEntity build(final int id) {
        return new EstadoTareaEntity(id);
    }

    protected static final EstadoTareaEntity build() {
        return new EstadoTareaEntity(NumericHelper.ZERO);
    }

    public static final EstadoTareaEntity build(final int id, final String nombreEstado) {
        return new EstadoTareaEntity(id, nombreEstado);
    }
    private final void setId(final int id) {
        this.id = id;
    }

    private void setNombreEstado(final String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public final int getId() {
        return id;
    }

    public final String getNombreEstado() {
        return nombreEstado;
    }
}
