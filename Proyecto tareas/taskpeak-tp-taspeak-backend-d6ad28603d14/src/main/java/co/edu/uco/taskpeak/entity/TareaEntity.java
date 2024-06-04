package co.edu.uco.taskpeak.entity;

import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Prioridad;
import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;
import co.edu.uco.taskpeak.dto.UsuarioDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class TareaEntity {
    private UUID id;
    private UsuarioEntity usuario;
    private String nombreTarea;
    private String descripcionTarea;
    private EstadoTareaEntity estado;
    private Prioridad prioridad;
    private LocalDateTime fechaLimite;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaActualizacion;

    public TareaEntity(UUID id) {
        setId(id);
        setUsuario(UsuarioEntity.build());
        setNombreTarea(TextHelper.EMPTY);
        setDescripcionTarea(TextHelper.EMPTY);
        setEstado(EstadoTareaEntity.build());
        setPrioridad(Prioridad.MEDIA);
        setFechaLimite(DateHelper.DEFAULT);
        setFechaCreacion(DateHelper.DEFAULT);
        setUltimaActualizacion(DateHelper.DEFAULT);
    }
    public TareaEntity(UUID id, UsuarioEntity usuario, String nombreTarea, String descripcionTarea, EstadoTareaEntity estado, Prioridad prioridad, LocalDateTime fechaLimite, LocalDateTime fechaCreacion, LocalDateTime ultimaActualizacion) {
        setId(id);
        setUsuario(usuario);
        setNombreTarea(nombreTarea);
        setDescripcionTarea(descripcionTarea);
        setEstado(estado);
        setPrioridad(prioridad);
        setFechaLimite(fechaLimite);
        setFechaCreacion(fechaCreacion);
        setUltimaActualizacion(ultimaActualizacion);
    }
    protected static final TareaEntity build() {
        return new TareaEntity(UUID.randomUUID());
    }
    public static final TareaEntity build(final UUID id) {
        return new TareaEntity(id);
    }

    public static final TareaEntity build(UUID id, UsuarioEntity usuario, String nombreTarea, String descripcionTarea, EstadoTareaEntity estado, Prioridad prioridad, LocalDateTime fechaLimite, LocalDateTime fechaCreacion, LocalDateTime ultimaActualizacion) {
        return new TareaEntity(id, usuario, nombreTarea, descripcionTarea, estado,prioridad,fechaLimite,fechaCreacion,ultimaActualizacion);
    }

    private void setId(final UUID id) {
        this.id = id;
    }

    private final void setUsuario(final UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    private final void setNombreTarea(final String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    private final void setDescripcionTarea(final String descripcionTarea) {
        this.descripcionTarea = ObjectHelper.getObjectHelper().getDefault(descripcionTarea,TextHelper.EMPTY);
    }

    private final void setEstado(final EstadoTareaEntity estado) {
        this.estado = estado;
    }

    private final void setPrioridad(final Prioridad prioridad) {
        this.prioridad = ObjectHelper.getObjectHelper().getDefault(prioridad,Prioridad.MEDIA);
    }

    private final void setFechaLimite(final LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    private final void setFechaCreacion(final LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    private final void setUltimaActualizacion(final LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public UUID getId() {
        return id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public EstadoTareaEntity getEstado() {
        return estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }
}