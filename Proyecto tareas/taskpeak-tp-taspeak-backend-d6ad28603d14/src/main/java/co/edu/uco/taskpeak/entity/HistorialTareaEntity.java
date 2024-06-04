package co.edu.uco.taskpeak.entity;

import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;

import java.time.LocalDateTime;
import java.util.UUID;

public class HistorialTareaEntity {
    private UUID id;
    private TareaEntity tarea;
    private EstadoTareaEntity estadoAnterior;
    private EstadoTareaEntity estadoNuevo;
    private UsuarioEntity editor;
    private LocalDateTime fechaActualizacion;

    public HistorialTareaEntity(UUID id) {
        setId(id);
        setTarea(TareaEntity.build());
        setEstadoAnterior(EstadoTareaEntity.build());
        setEstadoNuevo(EstadoTareaEntity.build());
        setEditor(UsuarioEntity.build());
        setFechaActualizacion(DateHelper.DEFAULT);
    }

    public HistorialTareaEntity(UUID id, TareaEntity tarea, EstadoTareaEntity estadoAnterior, EstadoTareaEntity estadoNuevo, UsuarioEntity editor, LocalDateTime fechaActualizacion) {
        setId(id);
        setTarea(tarea);
        setEstadoAnterior(estadoAnterior);
        setEstadoNuevo(estadoNuevo);
        setEditor(editor);
        setFechaActualizacion(fechaActualizacion);
    }

    protected static final HistorialTareaEntity build() {
        return new HistorialTareaEntity(UUID.randomUUID());
    }

    public static final HistorialTareaEntity build(final UUID id) {
        return new HistorialTareaEntity(id);
    }

    public static final HistorialTareaEntity build(UUID id, TareaEntity tarea, EstadoTareaEntity estadoAnterior, EstadoTareaEntity estadoNuevo, UsuarioEntity editor, LocalDateTime fechaActualizacion) {
        return new HistorialTareaEntity(id, tarea, estadoAnterior, estadoNuevo, editor, fechaActualizacion);
    }


    private final void setId(final UUID id) {
        this.id = id;
    }

    private final void setTarea(final TareaEntity tarea) {
        this.tarea = tarea;
    }

    private final void setEstadoAnterior(final EstadoTareaEntity estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    private final void setEstadoNuevo(final EstadoTareaEntity estadoNuevo) {
        this.estadoNuevo = estadoNuevo;
    }

    private final void setEditor(final UsuarioEntity editor) {
        this.editor = editor;
    }

    private final void  setFechaActualizacion(final LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public UUID getId() {
        return id;
    }

    public TareaEntity getTarea() {
        return tarea;
    }

    public EstadoTareaEntity getEstadoAnterior() {
        return estadoAnterior;
    }

    public EstadoTareaEntity getEstadoNuevo() {
        return estadoNuevo;
    }

    public UsuarioEntity getEditor() {
        return editor;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }
}
