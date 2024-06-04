package co.edu.uco.taskpeak.api.response;

import co.edu.uco.taskpeak.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.List;

public class UsuarioResponse extends Response<UsuarioDTO> {
    public static final UsuarioResponse build(List<String> mensajes, List<UsuarioDTO> data){
        UsuarioResponse instance = new UsuarioResponse();
        instance.setMensajes(mensajes);
        instance.setData(data);
        return instance;
    }
    public static final UsuarioResponse build(List<UsuarioDTO> data){
        UsuarioResponse instance = new UsuarioResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setData(data);
        return instance;
    }

    public static final UsuarioResponse build() {
        UsuarioResponse instance = new UsuarioResponse();
        instance.setMensajes(new ArrayList<>());
        instance.setData(new ArrayList<>());
        return instance;
    }
}
