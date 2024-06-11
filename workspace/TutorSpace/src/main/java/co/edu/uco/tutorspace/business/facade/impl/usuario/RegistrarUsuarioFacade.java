package co.edu.uco.tutorspace.business.facade.impl.usuario;

import co.edu.uco.tutorspace.business.assembler.dto.impl.UsuarioAssemblerDTO;
import co.edu.uco.tutorspace.business.facade.FacadeWithOutReturn;
import co.edu.uco.tutorspace.business.usecase.impl.usuario.RegistrarUsuario;
import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.customs.BusinessTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.data.dao.factory.DAOFactory;
import co.edu.uco.tutorspace.dto.UsuarioDTO;

public final class RegistrarUsuarioFacade implements FacadeWithOutReturn<UsuarioDTO> {

    private final DAOFactory daoFactory;

    public RegistrarUsuarioFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(UsuarioDTO dto) {
        daoFactory.iniciarTransaccion();
        try {
            var useCase = new RegistrarUsuario(daoFactory);
            var usuarioDomain = UsuarioAssemblerDTO.getInstance().toDomain(dto);
            useCase.execute(usuarioDomain);
            daoFactory.confirmarTransaccion();
        } catch (final TutorSpaceException exception) {
            daoFactory.cancelarTransaccion();
            throw exception;
        } catch (final Exception exception) {
            daoFactory.cancelarTransaccion();
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00053);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario, exception);
        } finally {
            daoFactory.cerrarConexion();
        }
    }
}
