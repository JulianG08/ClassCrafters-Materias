package co.edu.uco.taskpeak.business.domain;

import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Prioridad;
import co.edu.uco.taskpeak.dto.EstadoTareaDTO;
import co.edu.uco.taskpeak.dto.UsuarioDTO;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;

import java.time.LocalDateTime;
import java.util.UUID;

public class TareaDomain {

    private UUID id;
    private UsuarioDTO usuario;
    private String nombreTarea;
    private String descripcionTarea;
    private EstadoTareaDTO estado;
    private Prioridad prioridad;
    private LocalDateTime fechaLimite;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaActualizacion;

    public TareaDomain(final UUID id, final UsuarioDTO usuario, final String nombreTarea, final String descripcionTarea,
                       final EstadoTareaDTO estado, final Prioridad prioridad, final LocalDateTime fechaLimite,
                       final LocalDateTime fechaCreacion, final LocalDateTime ultimaActualizacion) {
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

    public static TareaDomain crear(final UUID id, final UsuarioDTO usuario, final String nombreTarea, final String descripcionTarea,
                                    final EstadoTareaDTO estado, final Prioridad prioridad, final LocalDateTime fechaLimite,
                                    final LocalDateTime fechaCreacion, final LocalDateTime ultimaActualizacion) {
        return new TareaDomain(id, usuario, nombreTarea, descripcionTarea, estado, prioridad, fechaLimite, fechaCreacion, ultimaActualizacion);
    }

    public static TareaDomain crear() {
        return new TareaDomain();
    }

    private TareaDomain() {
        setNombreTarea(TextHelper.EMPTY);
        setDescripcionTarea(TextHelper.EMPTY);
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    private void setNombreTarea(String nombreTarea) {
        this.nombreTarea = TextHelper.applyTrim(nombreTarea);
    }

    private void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = TextHelper.applyTrim(descripcionTarea);
    }

    private void setEstado(EstadoTareaDTO estado) {
        this.estado = estado;
    }

    private void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    private void setFechaLimite(LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    private void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    private void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public UUID getId() {
        return id;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public EstadoTareaDTO getEstado() {
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
