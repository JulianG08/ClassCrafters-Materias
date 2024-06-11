package co.edu.uco.tutorspace.business.facade.impl.materia;

import co.edu.uco.tutorspace.business.assembler.dto.impl.MateriaAssemblerDTO;
import co.edu.uco.tutorspace.business.facade.FacadeWithOutReturn;
import co.edu.uco.tutorspace.business.usecase.impl.materia.RegistrarMateria;
import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.customs.BusinessTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.data.dao.factory.DAOFactory;
import co.edu.uco.tutorspace.dto.MateriaDTO;

public final class RegistrarMateriaFacade implements FacadeWithOutReturn<MateriaDTO> {

    private final DAOFactory daoFactory;

    public RegistrarMateriaFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public void execute(MateriaDTO dto) {
        daoFactory.iniciarTransaccion();
        try {
            var useCase = new RegistrarMateria(daoFactory);
            var materiaDomain = MateriaAssemblerDTO.getInstance().toDomain(dto);
            useCase.execute(materiaDomain);
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
