package co.edu.uco.tutorspace.business.facade.impl.materia;

import co.edu.uco.tutorspace.business.assembler.dto.impl.MateriaAssemblerDTO;
import co.edu.uco.tutorspace.business.facade.FacadeWithReturn;
import co.edu.uco.tutorspace.business.usecase.impl.materia.ConsultarMaterias;
import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.customs.BusinessTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.data.dao.factory.DAOFactory;
import co.edu.uco.tutorspace.dto.MateriaDTO;

import java.util.List;

public final class ConsultarMateriasFacade implements FacadeWithReturn<MateriaDTO, List<MateriaDTO>> {

    private final DAOFactory daoFactory;

    public ConsultarMateriasFacade() {
        daoFactory = DAOFactory.getFactory();
    }

    @Override
    public List<MateriaDTO> execute(final MateriaDTO dto) {
        try {
            var usecase = new ConsultarMaterias(daoFactory);
            var materiaDomain = MateriaAssemblerDTO.getInstance().toDomain(dto);
            var resultadosDomain = usecase.execute(materiaDomain);
            return MateriaAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
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
