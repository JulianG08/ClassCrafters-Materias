package co.edu.uco.tutorspace.business.usecase.impl.usuario;

import co.edu.uco.tutorspace.business.domain.UsuarioDomain;
import co.edu.uco.tutorspace.business.usecase.UseCaseWithOutReturn;
import co.edu.uco.tutorspace.crosscutting.exceptions.customs.BusinessTutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.crosscutting.helpers.TextHelper;
import co.edu.uco.tutorspace.crosscutting.helpers.UUIDHelper;
import co.edu.uco.tutorspace.data.dao.factory.DAOFactory;
import co.edu.uco.tutorspace.entity.UsuarioEntity;

import static co.edu.uco.tutorspace.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.UUID;

public class RegistrarUsuario implements UseCaseWithOutReturn<UsuarioDomain> {

	private DAOFactory factory;

    public RegistrarUsuario(final DAOFactory factory) {
        if (getObjectHelper().isNull(factory)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00057);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00062);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        this.factory = factory;
    }

    @Override
    public void execute(final UsuarioDomain data) {
        validarNombre(data.getNombre());
        validarApellido(data.getApellido());
        validarNumeroDocumento(data.getNumeroDocumento());
        validarTipoUsuario(data.getTipoUsuario());
        validarCorreoElectronico(data.getCorreoElectronico());
        validarClave(data.getClave());
        validarTelefono(data.getTelefono());
        validarDireccion(data.getDireccion());
        validarInstitucion(data.getInstitucion());
        validarUsuarioMismoCorreoElectronico(data.getCorreoElectronico());
        validarUsuarioMismoNumeroDocumento(data.getNumeroDocumento());


        var usuarioEntity = UsuarioEntity.build().setId(generarIdentificadorPerfil()).setNombre(data.getNombre()).setApellido(data.getApellido()).setTipoDocumento(data.getTipoDocumento()).setNumeroDocumento(data.getNumeroDocumento()).setTipoUsuario(data.getTipoUsuario()).setCorreoElectronico(data.getCorreoElectronico()).setClave(data.getClave()).setTelefono(data.getTelefono()).setDireccion(data.getDireccion()).setInstitucion(data.getInstitucion());

        factory.getUsuarioDAO().crear(usuarioEntity);
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

    private void validarApellido(String apellido) {
        if (apellido == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!TextHelper.soloLetras(apellido)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00083);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!validarLongitudAtributo(apellido, 1, 20)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00065);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico,mensajeUsuario);
        }
    }

    private void validarNumeroDocumento(long numeroDocumento) {

        String numeroDocumentoStr = String.valueOf(numeroDocumento);
        if (!TextHelper.contieneSoloDigitos(numeroDocumentoStr)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00086);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00087);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!validarLongitudAtributo(numeroDocumentoStr, 1, 10)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00088);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00089);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
    }

    private void validarTipoUsuario(String tipoUsuario) {
        if (tipoUsuario == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico,mensajeUsuario);
        }
        
        if (!TextHelper.soloLetras(tipoUsuario)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00066);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!validarLongitudAtributo(tipoUsuario, 1, 25)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00067);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico,mensajeUsuario);
        }
    }

    private void validarClave(String clave) {
        if (clave == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!TextHelper.validarClave(clave)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00069);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!validarLongitudAtributo(clave, 8, 30)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00068);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
    }

    private void validarCorreoElectronico(String correoElectronico) {
        if (correoElectronico == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!TextHelper.contieneFormatoCorreo(correoElectronico)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00070);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!validarLongitudAtributo(correoElectronico, 6, 256)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00071);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
    }

    private void validarTelefono(String telefono) {
        if (telefono == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!TextHelper.contieneSoloDigitos(telefono)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00064);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!validarLongitudAtributo(telefono, 1, 10)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00065);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
    }
    
    private void validarDireccion(String direccion) {
        if (direccion == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!TextHelper.soloLetrasDigitosEspacios(direccion)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00064);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!validarLongitudAtributo(direccion, 1, 20)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00065);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
    }
    
    private void validarInstitucion(String institucion) {
        if (institucion == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!TextHelper.soloLetras(institucion)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00083);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico, mensajeUsuario);
        }
        
        if (!validarLongitudAtributo(institucion, 1, 20)) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00065);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
            throw new BusinessTutorSpaceException(mensajeTecnico,mensajeUsuario);
        }
    }

    private final UUID generarIdentificadorPerfil() {
        UUID id = UUIDHelper.generate();
        boolean existeId = true;

        while (existeId) {
            id = UUIDHelper.generate();
            var usuarioEntity = UsuarioEntity.build().setId(id);
            var resultados = factory.getUsuarioDAO().consultar(usuarioEntity);
            existeId = !resultados.isEmpty();
        }
        return id;
    }

    private void validarUsuarioMismoCorreoElectronico(String correoElectronico) {
        var usuarioEntity = UsuarioEntity.build().setCorreoElectronico(correoElectronico);
        var resultados = factory.getUsuarioDAO().consultar(usuarioEntity);
        if (!resultados.isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00073);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00081);
            throw new BusinessTutorSpaceException(mensajeTecnico,mensajeUsuario);
        }
    }

    private void validarUsuarioMismoNumeroDocumento(long numeroDocumento) {
        var usuarioEntity = UsuarioEntity.build().setNumeroDocumento(numeroDocumento);
        var resultados = factory.getUsuarioDAO().consultar(usuarioEntity);
        if (!resultados.isEmpty()) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00074);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00082);
            throw new BusinessTutorSpaceException(mensajeTecnico,mensajeUsuario);
        }
    }

    private boolean validarLongitudAtributo(String atributo, int longitudMinima, int longitudMaxima) {
        return TextHelper.longitudMinimaPermitida(atributo, longitudMinima) &&
                TextHelper.longitudMaximaPermitida(atributo, longitudMaxima);
    }
}
