package co.edu.uco.taskpeak.business.useCase;

import co.edu.uco.taskpeak.business.domain.UsuarioDomain;

import java.util.List;

public interface ConsultarUsuarios {
    List<UsuarioDomain> ejecutar(UsuarioDomain usuario);
}
