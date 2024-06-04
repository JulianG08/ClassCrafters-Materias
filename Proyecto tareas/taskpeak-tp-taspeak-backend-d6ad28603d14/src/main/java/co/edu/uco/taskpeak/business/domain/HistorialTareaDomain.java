package co.edu.uco.taskpeak.business.domain;

import co.edu.uco.taskpeak.dto.EstadoTareaDTO;
import co.edu.uco.taskpeak.dto.TareaDTO;
import co.edu.uco.taskpeak.dto.UsuarioDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class HistorialTareaDomain {

    private UUID id;
    private TareaDTO tarea;
    private EstadoTareaDTO estadoAnterior;
    private EstadoTareaDTO estadoNuevo;
    private UsuarioDTO editor;
    private LocalDateTime fechaActualizacion;

    public HistorialTareaDomain(final UUID id, final TareaDTO tarea, final EstadoTareaDTO estadoAnterior,
                                final EstadoTareaDTO estadoNuevo, final UsuarioDTO editor,
                                final LocalDateTime fechaActualizacion) {
        setId(id);
        setTarea(tarea);
        setEstadoAnterior(estadoAnterior);
        setEstadoNuevo(estadoNuevo);
        setEditor(editor);
        setFechaActualizacion(fechaActualizacion);
    }

    public static HistorialTareaDomain crear(final UUID id, final TareaDTO tarea, final EstadoTareaDTO estadoAnterior,
                                             final EstadoTareaDTO estadoNuevo, final UsuarioDTO editor,
                                             final LocalDateTime fechaActualizacion) {
        return new HistorialTareaDomain(id, tarea, estadoAnterior, estadoNuevo, editor, fechaActualizacion);
    }

    public static HistorialTareaDomain crear() {
        return new HistorialTareaDomain();
    }

    private HistorialTareaDomain() {}

    private void setId(UUID id) {
        this.id = id;
    }

    private void setTarea(TareaDTO tarea) {
        this.tarea = tarea;
    }

    private void setEstadoAnterior(EstadoTareaDTO estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    private void setEstadoNuevo(EstadoTareaDTO estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    private void setEditor(UsuarioDTO editor) {
        this.editor = editor;
    }

    private void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UUID getId() {
        return id;
    }

    public TareaDTO getTarea() {
        return tarea;
    }

    public EstadoTareaDTO getEstadoAnterior() {
        return estadoAnterior;
    }

    public EstadoTareaDTO getEstadoNuevo() {
        return estadoNuevo;
    }

    public UsuarioDTO getEditor() {
        return editor;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
}
