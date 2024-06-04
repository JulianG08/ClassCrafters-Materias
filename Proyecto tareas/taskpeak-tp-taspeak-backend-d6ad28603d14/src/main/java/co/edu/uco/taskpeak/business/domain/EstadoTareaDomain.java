package co.edu.uco.taskpeak.business.domain;

import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;

import java.util.UUID;

public class EstadoTareaDomain {

    private UUID id;
    private String nombreEstado;

    public EstadoTareaDomain(final UUID id, final String nombreEstado) {
        setId(id);
        setNombreEstado(nombreEstado);
    }

    public static EstadoTareaDomain crear(final UUID id, final String nombreEstado) {
        return new EstadoTareaDomain(id, nombreEstado);
    }

    public static EstadoTareaDomain crear() {
        return new EstadoTareaDomain();
    }

    private EstadoTareaDomain() {
        setNombreEstado(TextHelper.EMPTY);
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setNombreEstado(String nombreEstado) {
        this.nombreEstado = TextHelper.applyTrim(nombreEstado);
    }

    public UUID getId() {
        return id;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }
}
