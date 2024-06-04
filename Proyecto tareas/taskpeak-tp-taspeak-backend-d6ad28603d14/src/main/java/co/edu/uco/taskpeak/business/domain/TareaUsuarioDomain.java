package co.edu.uco.taskpeak.business.domain;

import co.edu.uco.taskpeak.dto.CategoriaTareaDTO;
import co.edu.uco.taskpeak.dto.TareaDTO;

import java.util.UUID;

public class TareaUsuarioDomain {

    private UUID id;
    private TareaDTO tarea;
    private CategoriaTareaDTO categoria;

    public TareaUsuarioDomain(final UUID id, final TareaDTO tarea, final CategoriaTareaDTO categoria) {
        setId(id);
        setTarea(tarea);
        setCategoria(categoria);
    }

    public static TareaUsuarioDomain crear(final UUID id, final TareaDTO tarea, final CategoriaTareaDTO categoria) {
        return new TareaUsuarioDomain(id, tarea, categoria);
    }

    public static TareaUsuarioDomain crear() {
        return new TareaUsuarioDomain();
    }

    private TareaUsuarioDomain() {}

    private void setId(UUID id) {
        this.id = id;
    }

    private void setTarea(TareaDTO tarea) {
        this.tarea = tarea;
    }

    private void setCategoria(CategoriaTareaDTO categoria) {
        this.categoria = categoria;
    }

    public UUID getId() {
        return id;
    }

    public TareaDTO getTarea() {
        return tarea;
    }

    public CategoriaTareaDTO getCategoria() {
        return categoria;
    }
}
