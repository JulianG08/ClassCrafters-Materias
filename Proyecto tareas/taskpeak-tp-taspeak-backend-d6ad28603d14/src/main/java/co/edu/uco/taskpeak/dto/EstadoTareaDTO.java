package co.edu.uco.taskpeak.dto;

import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;

import java.util.UUID;

public final class EstadoTareaDTO {

    private UUID id;
    private String nombreEstado;

    public EstadoTareaDTO(final UUID id,final String nombreEstado) {
        setId(id);
        setNombreEstado(nombreEstado);
    }

    public EstadoTareaDTO(){
        setId(UUID.randomUUID());
        setNombreEstado(TextHelper.EMPTY);
    }

    public static final EstadoTareaDTO build(){
        return new EstadoTareaDTO();
    }
    public final UUID getId() {
        return id;
    }

    public final EstadoTareaDTO setId(UUID id) {
        this.id = ObjectHelper.getObjectHelper().getDefault(id, UUID.randomUUID());
        return this;
    }

    public final String getNombreEstado() {
        return nombreEstado;
    }

    public final EstadoTareaDTO setNombreEstado(String nombreEstado) {
        this.nombreEstado = TextHelper.applyTrim(nombreEstado);
        return this;
    }
}
