package co.edu.uco.taskpeak.dto;

import co.edu.uco.taskpeak.crosscutting.exceptions.enums.Prioridad;
import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper;
import co.edu.uco.taskpeak.crosscutting.helpers.TextHelper;

import java.time.LocalDateTime;
import java.util.UUID;

public final class TareaDTO {
    private UUID id;
    private UsuarioDTO usuario;
    private String nombreTarea;
    private String descripcionTarea;
    private EstadoTareaDTO estado;
    private Prioridad prioridad;
    private LocalDateTime fechaLimite;
    private LocalDateTime fechaCreacion;
    private LocalDateTime ultimaActualizacion;
    private CategoriaTareaDTO categoria;

    public TareaDTO() {
        setId(UUID.randomUUID());
        setUsuario(UsuarioDTO.build());
        setNombreTarea(TextHelper.EMPTY);
        setDescripcionTarea(TextHelper.EMPTY);
        setEstado(EstadoTareaDTO.build());
        setPrioridad(Prioridad.MEDIA);
        setFechaLimite(DateHelper.DEFAULT);
        setFechaCreacion(DateHelper.DEFAULT);
        setUltimaActualizacion(DateHelper.DEFAULT);
        setCategoria(CategoriaTareaDTO.build());
    }

    public TareaDTO(UUID id, UsuarioDTO usuario, String nombreTarea, String descripcionTarea, EstadoTareaDTO estado, Prioridad prioridad, LocalDateTime fechaLimite, LocalDateTime fechaCreacion, LocalDateTime ultimaActualizacion, CategoriaTareaDTO categoria) {
        setId(id);
        setUsuario(usuario);
        setNombreTarea(nombreTarea);
        setDescripcionTarea(descripcionTarea);
        setEstado(estado);
        setPrioridad(prioridad);
        setFechaLimite(fechaLimite);
        setFechaCreacion(fechaCreacion);
        setUltimaActualizacion(ultimaActualizacion);
        setCategoria(categoria);
    }

    public static final TareaDTO build() {
        return new TareaDTO();
    }

    public static final TareaDTO build(final UUID id) {
        return new TareaDTO(id, UsuarioDTO.build(), TextHelper.EMPTY, TextHelper.EMPTY, EstadoTareaDTO.build(), Prioridad.MEDIA, DateHelper.DEFAULT, DateHelper.DEFAULT, DateHelper.DEFAULT, CategoriaTareaDTO.build());
    }

    public static final TareaDTO build(UUID id, UsuarioDTO usuario, String nombreTarea, String descripcionTarea, EstadoTareaDTO estado, Prioridad prioridad, LocalDateTime fechaLimite, LocalDateTime fechaCreacion, LocalDateTime ultimaActualizacion, CategoriaTareaDTO categoria) {
        return new TareaDTO(id, usuario, nombreTarea, descripcionTarea, estado, prioridad, fechaLimite, fechaCreacion, ultimaActualizacion, categoria);
    }

    public final UUID getId() {
        return id;
    }

    public final TareaDTO setId(final UUID id) {
        this.id = id;
        return this;
    }

    public final UsuarioDTO getUsuario() {
        return usuario;
    }

    public final TareaDTO setUsuario(final UsuarioDTO usuario) {
        this.usuario = usuario;
        return this;
    }

    public final String getNombreTarea() {
        return nombreTarea;
    }

    public final TareaDTO setNombreTarea(final String nombreTarea) {
        this.nombreTarea = TextHelper.applyTrim(nombreTarea);
        return this;
    }

    public final String getDescripcionTarea() {
        return descripcionTarea;
    }

    public final TareaDTO setDescripcionTarea(final String descripcionTarea) {
        this.descripcionTarea = TextHelper.applyTrim(descripcionTarea);
        return this;
    }

    public final EstadoTareaDTO getEstado() {
        return estado;
    }

    public final TareaDTO setEstado(final EstadoTareaDTO estado) {
        this.estado = estado;
        return this;
    }

    public final Prioridad getPrioridad() {
        return prioridad;
    }

    public final TareaDTO setPrioridad(final Prioridad prioridad) {
        this.prioridad = ObjectHelper.getObjectHelper().getDefault(prioridad, Prioridad.MEDIA);
        return this;
    }

    public final LocalDateTime getFechaLimite() {
        return fechaLimite;
    }

    public final TareaDTO setFechaLimite(final LocalDateTime fechaLimite) {
        this.fechaLimite = fechaLimite;
        return this;
    }

    public final LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public final TareaDTO setFechaCreacion(final LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public final LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public final TareaDTO setUltimaActualizacion(final LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
        return this;
    }

    public final CategoriaTareaDTO getCategoria() {
        return categoria;
    }

    public final TareaDTO setCategoria(final CategoriaTareaDTO categoria) {
        this.categoria = categoria;
        return this;
    }
}