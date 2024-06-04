package co.edu.uco.taskpeak.dto;

import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;

import java.time.LocalDateTime;
import java.util.UUID;

public final class RecordatorioDTO {
    private UUID id;
    private TareaDTO tarea;
    private LocalDateTime fechaRecordatorio;

    public RecordatorioDTO() {
        setId(UUID.randomUUID());
        setTarea(TareaDTO.build());
        setFechaRecordatorio(DateHelper.DEFAULT);
    }

    public RecordatorioDTO(UUID id, TareaDTO tarea, LocalDateTime fechaRecordatorio) {
        setId(id);
        setTarea(tarea);
        setFechaRecordatorio(fechaRecordatorio);
    }

    public static final RecordatorioDTO build() {
        return new RecordatorioDTO();
    }

    public static final RecordatorioDTO build(final UUID id) {
        return new RecordatorioDTO(id, TareaDTO.build(), DateHelper.DEFAULT);
    }

    public static final RecordatorioDTO build(UUID id, TareaDTO tarea, LocalDateTime fechaRecordatorio) {
        return new RecordatorioDTO(id, tarea, fechaRecordatorio);
    }

    public final UUID getId() {
        return id;
    }

    public final RecordatorioDTO setId(final UUID id) {
        this.id = id;
        return this;
    }

    public final TareaDTO getTarea() {
        return tarea;
    }

    public final RecordatorioDTO setTarea(final TareaDTO tarea) {
        this.tarea = tarea;
        return this;
    }

    public final LocalDateTime getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public final RecordatorioDTO setFechaRecordatorio(final LocalDateTime fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
        return this;
    }
}
