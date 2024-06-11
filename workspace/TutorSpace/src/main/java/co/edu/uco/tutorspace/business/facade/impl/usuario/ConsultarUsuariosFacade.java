package co.edu.uco.tutorspace.business.facade.impl.usuario;

import co.edu.uco.tutorspace.business.assembler.dto.impl.UsuarioAssemblerDTO;
import co.edu.uco.tutorspace.business.facade.FacadeWithReturn;
import co.edu.uco.tutorspace.business.usecase.impl.usuario.ConsultarUsuarios;
import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.customs.BusinessTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.data.dao.factory.DAOFactory;
import co.edu.uco.tutorspace.dto.UsuarioDTO;

import java.util.List;

public final class ConsultarUsuariosFacade implements FacadeWithReturn<UsuarioDTO, List<UsuarioDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarUsuariosFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<UsuarioDTO> execute(final UsuarioDTO dto) {
        try {
            var usecase = new ConsultarUsuarios(daoFactory);
            var usuarioDomain = UsuarioAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = usecase.execute(usuarioDomain);
            return UsuarioAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
        } catch (final TutorSpaceException exception) {
            throw exception;
        } catch (final Exception exception) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}

