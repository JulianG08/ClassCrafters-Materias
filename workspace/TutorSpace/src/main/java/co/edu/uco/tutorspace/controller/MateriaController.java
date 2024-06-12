package co.edu.uco.tutorspace.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.tutorspace.business.facade.impl.materia.ConsultarMateriasFacade;
import co.edu.uco.tutorspace.business.facade.impl.materia.RegistrarMateriaFacade;
import co.edu.uco.tutorspace.controller.response.MateriaResponse;
import co.edu.uco.tutorspace.crosscutting.exceptions.TutorSpaceException;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.tutorspace.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.tutorspace.dto.MateriaDTO;

@RestController
@RequestMapping("/materia")
@CrossOrigin(origins = "http://localhost:8080")
public final class MateriaController {

	@GetMapping("/materias")
    public ResponseEntity<MateriaResponse> consultar(){

        var httpStatusCode = HttpStatus.ACCEPTED;
        var materiaResponse = new MateriaResponse();

        try {
            var materiaDto = MateriaDTO.build();
            var facade = new ConsultarMateriasFacade();

            materiaResponse.setDatos(facade.execute(materiaDto));
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00059);
            materiaResponse.getMensajes().add(mensajeUsuario);

        } catch (final TutorSpaceException exception){
            httpStatusCode = HttpStatus.BAD_REQUEST;
            materiaResponse.getMensajes().add(exception.getMensajeUsuario());
            exception.printStackTrace();

        } catch (final Exception exception){
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
            materiaResponse.getMensajes().add(mensajeUsuario);

            exception.printStackTrace();
        }
        return new ResponseEntity<>(materiaResponse, httpStatusCode);
    }

    @PostMapping("/crearmateria")
    public ResponseEntity<MateriaResponse> registrar(@RequestBody MateriaDTO materia) {

        var httpStatusCode = HttpStatus.ACCEPTED;
        var materiaResponse = new MateriaResponse();

        try {
            var facade = new RegistrarMateriaFacade();
            facade.execute(materia);
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00061);
            materiaResponse.getMensajes().add(mensajeUsuario);

        } catch (final TutorSpaceException excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            materiaResponse.getMensajes().add(excepcion.getMensajeUsuario());
            excepcion.printStackTrace();
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00057);
            materiaResponse.getMensajes().add(mensajeUsuario);

            excepcion.printStackTrace();
        }

        return new ResponseEntity<>(materiaResponse, httpStatusCode);
    }
}
