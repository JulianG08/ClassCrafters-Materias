package co.edu.uco.tutorspace.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.tutorspace.business.facade.impl.usuario.ConsultarUsuariosFacade;
import co.edu.uco.tutorspace.business.facade.impl.usuario.RegistrarUsuarioFacade;
import co.edu.uco.tutorspace.controller.response.UsuarioResponse;
import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.dto.UsuarioDTO;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:8080")
public class UsuarioController {

	@GetMapping("/usuarios")
    public ResponseEntity<UsuarioResponse> consultar(){

        var httpStatusCode = HttpStatus.ACCEPTED;
        var usuarioResponse = new UsuarioResponse();

        try {
            var usuarioDto = UsuarioDTO.build();
            var facade = new ConsultarUsuariosFacade();

            usuarioResponse.setDatos(facade.execute(usuarioDto));
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00059);
            usuarioResponse.getMensajes().add(mensajeUsuario);

        } catch (final TutorSpaceException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            usuarioResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();

        }catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
            usuarioResponse.getMensajes().add(mensajeUsuario);

            exception.printStackTrace();
        }
        return new ResponseEntity<>(usuarioResponse, httpStatusCode);
    }

    @PostMapping("/crearusuario")
    public ResponseEntity<UsuarioResponse> registrar(@RequestBody UsuarioDTO usuario) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var usuarioResponse = new UsuarioResponse();

        try {
            var facade = new RegistrarUsuarioFacade();
            facade.execute(usuario);
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00061);
            usuarioResponse.getMensajes().add(mensajeUsuario);

        } catch (final TutorSpaceException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            usuarioResponse.getMensajes().add(excepcion.getMensajeUsuario());
            excepcion.printStackTrace();
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00057);
            usuarioResponse.getMensajes().add(mensajeUsuario);

            excepcion.printStackTrace();
        }

        return new ResponseEntity<>(usuarioResponse, httpStatusCode);
    }
}
