package co.edu.uco.taskpeak.dto;

import java.util.UUID;

public class TareaUsuarioDTO {
    private UUID id;
    private TareaDTO tarea;
    private CategoriaTareaDTO categoria;
    public TareaUsuarioDTO(UUID id) {
        setId(id);
        setTarea(TareaDTO.build());
        setCategoria(CategoriaTareaDTO.build());
    }
    public TareaUsuarioDTO(UUID id, TareaDTO tarea, CategoriaTareaDTO categoria) {
        setId(id);
        setTarea(tarea);
        setCategoria(categoria);
    }

    protected static final TareaUsuarioDTO build() {
        return new TareaUsuarioDTO(UUID.randomUUID());
    }
    public static final TareaUsuarioDTO build(final UUID id) {
        return new TareaUsuarioDTO(id);
    }

    public static final TareaUsuarioDTO build(UUID id, TareaDTO tarea, CategoriaTareaDTO categoria) {
        return new TareaUsuarioDTO(id, tarea, categoria);
    }

    private final void setId(final UUID id) {
        this.id = id;
    }

    private final void setTarea(final TareaDTO tarea) {
        this.tarea = tarea;
    }

    private final void setCategoria(final CategoriaTareaDTO categoria) {
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

