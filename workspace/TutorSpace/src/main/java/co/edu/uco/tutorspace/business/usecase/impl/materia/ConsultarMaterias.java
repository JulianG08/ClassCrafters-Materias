package co.edu.uco.tutorspace.business.usecase.impl.materia;

import java.util.List;

import co.edu.uco.tutorspace.business.assembler.entity.impl.MateriaAssemblerEntity;
import co.edu.uco.tutorspace.business.domain.MateriaDomain;
import co.edu.uco.tutorspace.business.usecase.UseCaseWithReturn;
import co.edu.uco.tutorspace.crosscutting.exceptions.customs.BusinessTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper;
import co.edu.uco.tutorspace.data.dao.factory.DAOFactory;

public class ConsultarMaterias implements UseCaseWithReturn<MateriaDomain, List<MateriaDomain>> {

	private DAOFactory factory;

    public ConsultarMaterias(final DAOFactory factory){
        if (ObjectHelper.getObjectHelper().isNull(factory)){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046);
            throw new BusinessTutorSpaceException(mensajeTecnico,mensajeUsuario);
        }
        this.factory=factory;
    }

    @Override
    public List<MateriaDomain> execute(final MateriaDomain data) {
        var materiaEntityFilter = MateriaAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getMateriaDAO().consultar(materiaEntityFilter);
        return MateriaAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}
