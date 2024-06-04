package co.edu.uco.taskpeak.api.response;

import java.util.ArrayList;
import java.util.List;

public abstract class Response <T>{
    private List<String> mensajes = new ArrayList<>();
    private List<T> data;

    public final List<String> getMensajes() {
        return mensajes;
    }

    public final void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }

    public final List<T> getData() {
        return data;
    }

    public final void setData(List<T> data) {
        this.data = data;
    }
}
