package co.edu.uco.taskpeak.dto;

import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.entity.TareaEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class HistorialTareaDTO {
    private UUID id;
    private TareaDTO tarea;
    private EstadoTareaDTO estadoAnterior;
    private EstadoTareaDTO estadoNuevo;
    private UsuarioDTO editor;
    private LocalDateTime fechaActualizacion;

    public HistorialTareaDTO(final UUID id,final TareaDTO tarea,final EstadoTareaDTO estadoAnterior,final EstadoTareaDTO estadoNuevo, final UsuarioDTO editor, final LocalDateTime fechaActualizacion) {
        setId(id);
        setTarea(tarea);
        setEstadoAnterior(estadoAnterior);
        setEstadoNuevo(estadoNuevo);
        setEditor(editor);
        setFechaActualizacion(fechaActualizacion);
    }

    public HistorialTareaDTO(){
        setId(UUID.randomUUID());
        setTarea(TareaDTO.build());
        setEstadoAnterior(EstadoTareaDTO.build());
        setEstadoNuevo(EstadoTareaDTO.build());
        setEditor(UsuarioDTO.build());
        setFechaActualizacion(DateHelper.DEFAULT);
    }

    public static final HistorialTareaDTO build(){
        return new HistorialTareaDTO();
    }

    public UUID getId() {
        return id;
    }

    public final HistorialTareaDTO setId(UUID id) {
        this.id = ObjectHelper.getObjectHelper().getDefault(id, UUID.randomUUID());
        return this;
    }

    public final TareaDTO getTarea() {
        return tarea;
    }

    public final HistorialTareaDTO setTarea(final TareaDTO tarea) {
        this.tarea = ObjectHelper.getObjectHelper().getDefault(tarea, TareaDTO.build());
        return this;
    }

    public final EstadoTareaDTO getEstadoAnterior() {
        return estadoAnterior;
    }

    public final HistorialTareaDTO setEstadoAnterior(final EstadoTareaDTO estadoAnterior) {
        this.estadoAnterior = ObjectHelper.getObjectHelper().getDefault(estadoAnterior, EstadoTareaDTO.build());
        return this;
    }

    public final EstadoTareaDTO getEstadoNuevo() {
        return estadoNuevo;
    }

    public final HistorialTareaDTO setEstadoNuevo(final EstadoTareaDTO estadoNuevo) {
        this.estadoNuevo = ObjectHelper.getObjectHelper().getDefault(estadoNuevo,EstadoTareaDTO.build());
        return this;
    }

    public final UsuarioDTO getEditor() {
        return editor;
    }

    public final HistorialTareaDTO setEditor(final UsuarioDTO editor) {
        this.editor = ObjectHelper.getObjectHelper().getDefault(editor,UsuarioDTO.build());
        return this;
    }

    public final LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public final HistorialTareaDTO setFechaActualizacion(final LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }
}
