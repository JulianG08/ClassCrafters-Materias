package co.edu.uco.taskpeak.business.fachade.concrete;

import co.edu.uco.taskpeak.business.assembler.dto.concrete.UsuarioDTODomainAssembler;
import co.edu.uco.taskpeak.business.fachade.ConsultarUsuariosFacha;
import co.edu.uco.taskpeak.business.useCase.ConsultarUsuarios;
import co.edu.uco.taskpeak.business.useCase.concrete.ConsultarUsuariosImpl;
import co.edu.uco.taskpeak.crosscutting.exceptions.TaskPeakException;
import co.edu.uco.taskpeak.crosscutting.exceptions.custom.BusinessTaskPeakException;
import co.edu.uco.taskpeak.data.dao.factory.DAOFactory;
import co.edu.uco.taskpeak.dto.UsuarioDTO;

import java.util.List;

public class ConsultarUsuariosFachaImpl implements ConsultarUsuariosFacha {
    private DAOFactory factory;

    public ConsultarUsuariosFachaImpl(){
        factory = DAOFactory.getFactory();
    }
    @Override
    public final List<UsuarioDTO> execute(final UsuarioDTO usuario) {
        try {
            var usuarioDomain = UsuarioDTODomainAssembler.obtenerInstancia().ensamblarDominio(usuario);
            final ConsultarUsuarios useCase = new ConsultarUsuariosImpl(factory);
            var resultados = useCase.ejecutar(usuarioDomain);
            return UsuarioDTODomainAssembler.obtenerInstancia()
                    .ensamblarListaDTO(resultados);

        }catch (TaskPeakException exception){
            throw exception;
        }catch (Exception exception){
            var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion de los paises";
            var mensajeTecnico = "Se ha presentado un problema INESPERADO tratando de consultar la informacion de los paises";

            throw new BusinessTaskPeakException(mensajeTecnico, mensajeUsuario);
        } finally {
            factory.cerrarConexion();
        }
    }
}
