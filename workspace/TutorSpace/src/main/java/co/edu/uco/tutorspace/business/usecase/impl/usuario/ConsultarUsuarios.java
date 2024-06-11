package co.edu.uco.tutorspace.business.usecase.impl.usuario;

import java.util.List;

import co.edu.uco.tutorspace.business.assembler.entity.impl.UsuarioAssemblerEntity;
import co.edu.uco.tutorspace.business.domain.UsuarioDomain;
import co.edu.uco.tutorspace.business.usecase.UseCaseWithReturn;
import co.edu.uco.tutorspace.crosscutting.exceptions.customs.BusinessTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper;
import co.edu.uco.tutorspace.data.dao.factory.DAOFactory;

public class ConsultarUsuarios implements UseCaseWithReturn<UsuarioDomain, List<UsuarioDomain>> {

	private DAOFactory factory;

    public ConsultarUsuarios(final DAOFactory factory){
        if (ObjectHelper.getObjectHelper().isNull(factory)){
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046);
            throw new BusinessTutorSpaceException(mensajeTecnico,mensajeUsuario);
        }
        this.factory=factory;
    }

    @Override
    public List<UsuarioDomain> execute(final UsuarioDomain data) {
        var usuarioEntityFilter = UsuarioAssemblerEntity.getInstance().toEntity(data);
        var resultadosEntity = factory.getUsuarioDAO().consultar(usuarioEntityFilter);
        return UsuarioAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
    }
}
