package co.edu.uco.tutorspace.controller.response;

import java.util.ArrayList;

import co.edu.uco.tutorspace.dto.MateriaDTO;

public class MateriaResponse extends Response<MateriaDTO> {

	public MateriaResponse() {
		setMensajes(new ArrayList<String>());
		setDatos(new ArrayList<>());
	}
}
