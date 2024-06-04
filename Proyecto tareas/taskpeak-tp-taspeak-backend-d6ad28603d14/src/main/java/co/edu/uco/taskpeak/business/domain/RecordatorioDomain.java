package co.edu.uco.taskpeak.business.domain;

import co.edu.uco.taskpeak.dto.TareaDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecordatorioDomain {

    private UUID id;
    private TareaDTO tarea;
    private LocalDateTime fechaRecordatorio;

    public RecordatorioDomain(final UUID id, final TareaDTO tarea, final LocalDateTime fechaRecordatorio) {
        setId(id);
        setTarea(tarea);
        setFechaRecordatorio(fechaRecordatorio);
    }

    public static RecordatorioDomain crear(final UUID id, final TareaDTO tarea, final LocalDateTime fechaRecordatorio) {
        return new RecordatorioDomain(id, tarea, fechaRecordatorio);
    }

    public static RecordatorioDomain crear() {
        return new RecordatorioDomain();
    }

    private RecordatorioDomain() {}

    private void setId(UUID id) {
        this.id = id;
    }

    private void setTarea(TareaDTO tarea) {
        this.tarea = tarea;
    }

    private void setFechaRecordatorio(LocalDateTime fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public UUID getId() {
        return id;
    }

    public TareaDTO getTarea() {
        return tarea;
    }

    public LocalDateTime getFechaRecordatorio() {
        return fechaRecordatorio;
    }
}
