package co.edu.uco.tutorspace.controller.response;

import java.util.ArrayList;

import co.edu.uco.tutorspace.dto.UsuarioDTO;

public class UsuarioResponse extends Response<UsuarioDTO>{

	public UsuarioResponse() {
		setMensajes(new ArrayList<String>());
		setDatos(new ArrayList<>());
	}
}
