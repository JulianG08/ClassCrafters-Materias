package co.edu.uco.taskpeak.business.fachade;

import co.edu.uco.taskpeak.dto.UsuarioDTO;

import java.util.List;

public interface ConsultarUsuariosFacha {
    List<UsuarioDTO> execute(UsuarioDTO usuario);
}
