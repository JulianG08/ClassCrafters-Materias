package co.edu.uco.taskpeak.business.useCase.concrete;

import co.edu.uco.taskpeak.business.assembler.dto.concrete.UsuarioDTODomainAssembler;
import co.edu.uco.taskpeak.business.assembler.entity.concrete.UsuarioEntityDomainAssembler;
import co.edu.uco.taskpeak.business.domain.UsuarioDomain;
import co.edu.uco.taskpeak.business.useCase.ConsultarUsuarios;
import co.edu.uco.taskpeak.data.dao.factory.DAOFactory;
import co.edu.uco.taskpeak.entity.UsuarioEntity;

import java.util.List;

public class ConsultarUsuariosImpl implements ConsultarUsuarios {
    private final DAOFactory factory;

    public ConsultarUsuariosImpl(final DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public final List<UsuarioDomain> ejecutar(final UsuarioDomain usuario) {
        System.out.println(usuario.getNombre());
        var usuarioEntity = UsuarioEntityDomainAssembler.obtenerInstancia().ensamblarEntidad(usuario);
        var resultados = factory.getUsuarioDAO().consultar(usuarioEntity);

        return UsuarioEntityDomainAssembler.obtenerInstancia().ensamblarListaDominios(resultados);
    }
}
