package co.edu.uco.taskpeak.entity;

import java.util.UUID;

public class TareaUsuarioEntity {
    private UUID id;
    private TareaEntity tarea;
    private CategoriaTareaEntity categoria;
    public TareaUsuarioEntity(UUID id) {
        setId(id);
        setTarea(TareaEntity.build());
        setCategoria(CategoriaTareaEntity.build());
    }
    public TareaUsuarioEntity(UUID id, TareaEntity tarea, CategoriaTareaEntity categoria) {
        setId(id);
        setTarea(tarea);
        setCategoria(categoria);
    }

    protected static final TareaUsuarioEntity build(UUID uuid, String tarea, String categoria) {
        return new TareaUsuarioEntity(UUID.randomUUID());
    }
    public static final TareaUsuarioEntity build(final UUID id) {
        return new TareaUsuarioEntity(id);
    }

    public static final TareaUsuarioEntity build(UUID id, TareaEntity tarea, CategoriaTareaEntity categoria) {
        return new TareaUsuarioEntity(id, tarea, categoria);
    }

    private final void setId(final UUID id) {
        this.id = id;
    }

    private final void setTarea(final TareaEntity tarea) {
        this.tarea = tarea;
    }

    private final void setCategoria(final CategoriaTareaEntity categoria) {
        this.categoria = categoria;
    }

    public UUID getId() {
        return id;
    }

    public TareaEntity getTarea() {
        return tarea;
    }

    public CategoriaTareaEntity getCategoria() {
        return categoria;
    }
}
