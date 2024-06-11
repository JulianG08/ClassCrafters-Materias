package co.edu.uco.tutorspace.business.usecase.impl.materia;

import co.edu.uco.tutorspace.business.domain.MateriaDomain;
import co.edu.uco.tutorspace.business.usecase.UseCaseWithOutReturn;
import co.edu.uco.tutorspace.crosscutting.exceptions.customs.BusinessTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.UUIDHelper;
import co.edu.uco.tutorspace.data.dao.factory.DAOFactory;
import co.edu.uco.tutorspace.entity.MateriaEntity;

import static co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.UUID;

public class RegistrarMateria implements UseCaseWithOutReturn<MateriaDomain> {

	private DAOFactory factory;

    public RegistrarMateria(final DAOFactory factory) {
        if (getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00057);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00062);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        this.factory = factory;
    }

    @Override
    public void execute(final MateriaDomain data) {

        validarNombre(data.getNombre());
        validarMateriaMismoNombre(data.getNombre());

        var materiaEntity = MateriaEntity.build().setId(generarIdentificadorPerfil()).setNombre(data.getNombre()).setInstitucion(data.getInstitucion());

        factory.getMateriaDAO().crear(materiaEntity);
    }

    private void validarNombre(String nombre) {
        if (nombre == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        if (!TextHelper.soloLetras(nombre)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00064);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        if (!validarLongitudAtributo(nombre, 1, 20)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00065);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
    }

    private final UUID generarIdentificadorPerfil() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var materiaEntity = MateriaEntity.build().setId(id);
            var resultados = factory.getMateriaDAO().consultar(materiaEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }

    private void validarMateriaMismoNombre(String nombre) {
        var materiaEntity = MateriaEntity.build().setNombre(nombre);
        var resultados = factory.getMateriaDAO().consultar(materiaEntity);
        if (!resultados.isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00072);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00076);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
    }

    private boolean validarLongitudAtributo(String atributo, int longitudMinima, int longitudMaxima) {
        return TextHelper.longitudMinimaPermitida(atributo, longitudMinima) && TextHelper.longitudMaximaPermitida(atributo, longitudMaxima);
    }
}
