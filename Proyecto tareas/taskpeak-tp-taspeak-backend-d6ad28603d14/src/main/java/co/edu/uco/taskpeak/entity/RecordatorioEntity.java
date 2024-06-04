package co.edu.uco.taskpeak.entity;

import co.edu.uco.taskpeak.crosscutting.helpers.DateHelper;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecordatorioEntity {
    private UUID id;
    private TareaEntity tarea;

    private LocalDateTime fechaRecordatorio;


    public RecordatorioEntity(UUID id) {
        setId(id);
        setTarea(TareaEntity.build());
        setFechaRecordatorio(DateHelper.DEFAULT);
    }
    public RecordatorioEntity(UUID id, TareaEntity tarea, LocalDateTime fechaRecordatorio) {
        setId(id);
        setTarea(tarea);
        setFechaRecordatorio(fechaRecordatorio);
    }

    protected static final RecordatorioEntity build() {
        return new RecordatorioEntity(UUID.randomUUID());
    }
    public static final RecordatorioEntity build(final UUID id) {
        return new RecordatorioEntity(id);
    }

    public static final RecordatorioEntity build(UUID id, TareaEntity tarea, LocalDateTime fechaRecordatorio) {
        return new RecordatorioEntity(id, tarea, fechaRecordatorio);
    }

    private final void setId(final UUID id) {
        this.id = id;
    }

    private final void setTarea(final TareaEntity tarea) {
        this.tarea = tarea;
    }

    private final void setFechaRecordatorio(final LocalDateTime fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public final UUID getId() {
        return id;
    }

    public final TareaEntity getTarea() {
        return tarea;
    }

    public final LocalDateTime getFechaRecordatorio() {
        return fechaRecordatorio;
    }


}
